package com.pse.commons;

import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public final class HexagonalArchitectureRules {

    private HexagonalArchitectureRules() {}

    @ArchTest
    private static final ArchRule isolated_domain = classes().that().resideInAPackage("..domain..")
            .should().onlyDependOnClassesThat().resideInAnyPackage(
                    "", // Enum inherited methods and Arrays are in the default package :'(
                    "java..",
                    "org.slf4j",
                    "..domain..",
                    "com.pse.commons"
            )
            .as("The domain of the hexagon should not depend on infrastructure and technology")
            .because("business rules and technology have distinct lifecycles." +
                    " Modifications to one should have minimal impact on the other.");

    @ArchTest
    private static final ArchRule independent_adapters = slices()
            .matching("..infra.(*)..").namingSlices("adapter '$1'")
            .should().notDependOnEachOther()
            .as("Adapters around the domain of the hexagon should not depend on one another")
            .because("every business process should be under the control of the domain.");

}
