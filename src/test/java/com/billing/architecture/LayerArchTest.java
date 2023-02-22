package com.billing.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.PackageMatcher;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.billing.architecture.PackageMatcherConsts.PROJECT_PACKAGE;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LayerArchTest {


    @Test
    /*
    This code validates that we don't have any wrong dependencies in our code(between our maven modules containing business logic).
    We generate layered architecture restrictions for each 2nd layer module(bookings, payments, ...) and validate the usages.
     */
    public void checkMultiModuleLayeredArchitecture() {

        Set<String> modules = getBusinessLogicModules();
        JavaClasses importedClasses = new ClassFileImporter().importPackages(PROJECT_PACKAGE);
        for (String module : modules) {
            Set<String> otherModules = new HashSet<>(modules);
            otherModules.remove(module);

            Architectures.LayeredArchitecture layeredArchitecture = layeredArchitecture().consideringAllDependencies()
                    .withOptionalLayers(true)
                    .layer("Api").definedBy(generatePackage(PackageMatcherConsts.API_PACKAGE, module))
                    .layer("Application").definedBy(generatePackage(PackageMatcherConsts.APPLICATION_PACKAGE, module))
                    .layer("DomainService").definedBy(generatePackage(PackageMatcherConsts.DOMAINSERVICE_PACKAGE, module))
                    .layer("DataAccess").definedBy(generatePackage(PackageMatcherConsts.DATAACCESS_PACKAGE, module))
                    .layer("Domain").definedBy(generatePackage(PackageMatcherConsts.DOMAIN_PACKAGE, module))
                    .layer("Repository").definedBy(generatePackage(PackageMatcherConsts.DATAACCESS_REPOSITORY_PACKAGE, module))
                    .layer("DataAccessClient").definedBy(generatePackage(PackageMatcherConsts.DATAACCESS_CLIENT_PACKAGE, module))
                    .layer("Other-Application").definedBy(generatePackages(PackageMatcherConsts.APPLICATION_PACKAGE, otherModules))
                    .layer("Other-DomainService").definedBy(generatePackages(PackageMatcherConsts.DOMAINSERVICE_PACKAGE, otherModules))
                    .layer("Other-DataAccess").definedBy(generatePackages(PackageMatcherConsts.DATAACCESS_PACKAGE, otherModules))
                    .layer("Other-Domain").definedBy(generatePackages(PackageMatcherConsts.DOMAIN_PACKAGE, otherModules))

                    .whereLayer("Application").mayNotBeAccessedByAnyLayer()
                    .whereLayer("Api").mayOnlyBeAccessedByLayers("Application")
                    .whereLayer("DomainService").mayOnlyBeAccessedByLayers("Application")
                    .whereLayer("DataAccess").mayOnlyBeAccessedByLayers("DomainService")
                    .whereLayer("Domain").mayOnlyBeAccessedByLayers("DataAccess", "DomainService", "Application", "Other-Application", "Other-DomainService", "Other-Domain", "Other-DataAccess")
                    .whereLayer("Repository").mayOnlyBeAccessedByLayers("DataAccess")
                    .whereLayer("DataAccessClient").mayOnlyBeAccessedByLayers("DataAccess")
                    .whereLayer("Other-Application").mayOnlyBeAccessedByLayers("Other-Application", "Other-DomainService", "Application", "DomainService")
                    .whereLayer("Other-DomainService").mayOnlyBeAccessedByLayers("Other-Application")
                    .whereLayer("Other-DataAccess").mayOnlyBeAccessedByLayers("Other-DomainService")
                    .whereLayer("Other-Domain").mayOnlyBeAccessedByLayers("Other-DataAccess", "Other-DomainService", "Other-Application", "Application", "DomainService", "Domain", "DataAccess");

            layeredArchitecture.check(importedClasses);
        }
    }

    private Set<String> getBusinessLogicModules() {
        PackageMatcher matcher = PackageMatcher.of("com.ppro.interview.billing.(*)..");
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.ppro.interview.billing");

        var list = new HashSet<String>();

        for (var clazz : importedClasses) {
            Optional<PackageMatcher.Result> match = matcher.match(clazz.getPackageName());
            if (match.isPresent() && match.get().getNumberOfGroups() == 1) {
                list.add(match.get().getGroup(1));
            }
        }

        list.removeAll(Arrays.asList("runner", "infrastructure", "architecture", "api"));
        assertFalse(list.isEmpty(), "Safety check to confirm we detected at least one module");

        return list;
    }

    private String generatePackage(String apiPackage, String module) {
        return apiPackage.replaceFirst("\\*", module);
    }

    private String[] generatePackages(String apiPackage, Set<String> modules) {
        String[] packages = new String[modules.size()];
        int i = 0;
        for (String module : modules) {
            packages[i++] = apiPackage.replaceFirst("\\*", module);
        }
        return packages;
    }
}
