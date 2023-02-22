package com.billing.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ApplicationServiceArchTest {

    @Test
    public void checkDataAccessClientLocation() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(PackageMatcherConsts.PROJECT_PACKAGE);

        String applicationServicePackage = PackageMatcherConsts.APPLICATION_PACKAGE + "service";

        classes().that()
                .resideInAPackage(applicationServicePackage)
                .should().haveSimpleNameEndingWith("ApplicationService")
                .check(importedClasses);
    }
}
