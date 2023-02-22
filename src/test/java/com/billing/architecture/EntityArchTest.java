package com.billing.architecture;

import com.billing.architecture.rules.EnumRules;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class EntityArchTest {

    @Test
    public void checkEntityNamingAndLocation() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(PackageMatcherConsts.PROJECT_PACKAGE);

        String entityPackage = PackageMatcherConsts.DATAACCESS_PACKAGE + "entity..";

        classes().that().haveSimpleNameEndingWith("Entity")
                .should().resideInAPackage(entityPackage)
                .check(importedClasses);

        classes().that().resideInAnyPackage(entityPackage)
                .should().haveSimpleNameEndingWith("Entity")
                .orShould(EnumRules.BE_ENUM);

    }
}
