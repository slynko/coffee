package com.pse.commons;

import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;
import static java.lang.String.format;

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

    private static boolean isADrivingPort(final JavaClass javaClass) {
        return javaClass.isAnnotatedWith(HexagonalArchitecture.Port.class)
                && javaClass.getAnnotationOfType(HexagonalArchitecture.Port.class).value() == HexagonalArchitecture.Port.Type.DRIVING;
    }

    private static boolean isADrivenPort(final JavaClass javaClass) {
        return javaClass.isAnnotatedWith(HexagonalArchitecture.Port.class)
                && javaClass.getAnnotationOfType(HexagonalArchitecture.Port.class).value() == HexagonalArchitecture.Port.Type.DRIVEN;
    }

    private static final ArchCondition<JavaClass> accessDrivingPorts = new ArchCondition<JavaClass>("access at least one driving port") {
        @Override
        public void check(final JavaClass item, final ConditionEvents events) {
            if (item.getAccessesFromSelf().stream().map(JavaAccess::getTargetOwner).noneMatch(HexagonalArchitectureRules::isADrivingPort)) {
                events.add(SimpleConditionEvent.violated(item, format("Type %s does not implement any driven port", item)));
            }
        }
    };

    private static final ArchCondition<JavaClass> implementADrivenPort = new ArchCondition<JavaClass>("implement a driven port") {
        @Override
        public void check(final JavaClass item, final ConditionEvents events) {
            if (item.getAllInterfaces().stream().noneMatch(HexagonalArchitectureRules::isADrivenPort)) {
                events.add(SimpleConditionEvent.violated(item, format("Type %s does not implement any driven port", item)));
            }
        }
    };

    @ArchTest
    public static final ArchRule left_adapters_should_call_driving_ports = classes().that().areAnnotatedWith(HexagonalArchitecture.LeftAdapter.class)
            .should(accessDrivingPorts)
            .as("Left adapters should call driving ports")
            .because("If a left adapter calls no driving port, that means it is useless");


    @ArchTest
    public static final ArchRule right_adapters_should_implement_driven_ports = classes().that().areAnnotatedWith(HexagonalArchitecture.RightAdapter.class)
            .should(implementADrivenPort)
            .as("Right adapters should implement driven ports")
            .because("If a right adapter does not implement a driven port, that means it is not a right adapter");

}
