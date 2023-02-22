package com.billing.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.billing.architecture.PackageMatcherConsts.PROJECT_PACKAGE;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class RepositoryArchTest {

    @Test
    public void checkRepositoryLocation() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(PROJECT_PACKAGE);

        String repositoryPackage = PackageMatcherConsts.DATAACCESS_REPOSITORY_PACKAGE;

        classes().that().haveSimpleNameEndingWith("Repository")
                .should().resideInAPackage(repositoryPackage)
                .check(importedClasses);
    }
}
