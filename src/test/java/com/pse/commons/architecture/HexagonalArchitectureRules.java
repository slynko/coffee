package com.pse.commons.architecture;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAccess;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;

import static com.tngtech.archunit.lang.SimpleConditionEvent.violated;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;
import static java.lang.String.format;

public final class HexagonalArchitectureRules {

    private HexagonalArchitectureRules() {}

    @ArchTest
    private static final ArchRule isolated_domain = classes().that().resideInAPackage("..domain..")
            .should().onlyDependOnClassesThat().resideInAnyPackage(
                    "java..",
                    "lombok",
                    "org.joda.money",
                    "org.slf4j",
                    "com.pse..domain..",
                    "com.pse.commons.architecture"
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

    @ArchTest
    public static final ArchRule left_adapters_should_call_driving_ports = classes().that().areAnnotatedWith(HexagonalArchitecture.LeftAdapter.class)
            .should(accessDrivingPorts())
            .as("Left adapters should call driving ports")
            .because("If a left adapter calls no driving port, that means it is useless");

    @ArchTest
    public static final ArchRule right_adapters_should_implement_driven_ports = classes().that().areAnnotatedWith(HexagonalArchitecture.RightAdapter.class)
            .should(implementADrivenPort())
            .as("Right adapters should implement driven ports")
            .because("If a right adapter does not implement a driven port, that means it is not a right adapter");

    @ArchTest
    public static final ArchRule driving_ports_should_be_suffixed_with_Api = classes().that(areDrivingPorts())
            .should().haveSimpleNameEndingWith("Api")
            .as("Driving ports should be suffixed with 'Api'")
            .because("It makes them easier to identify whilst browsing the source code");

    @ArchTest
    public static final ArchRule driven_ports_should_be_suffixed_with_Spi = classes().that(areDrivenPorts())
            .should().haveSimpleNameEndingWith("Spi")
            .as("Driven ports should be suffixed with 'Spi'")
            .because("It makes them easier to identify whilst browsing the source code");

    private static boolean isADrivingPort(final JavaClass javaClass) {
        return javaClass.isAnnotatedWith(HexagonalArchitecture.Port.class)
                && javaClass.getAnnotationOfType(HexagonalArchitecture.Port.class).value() == HexagonalArchitecture.Port.Type.DRIVING;
    }

    private static boolean isADrivenPort(final JavaClass javaClass) {
        return javaClass.isAnnotatedWith(HexagonalArchitecture.Port.class)
                && javaClass.getAnnotationOfType(HexagonalArchitecture.Port.class).value() == HexagonalArchitecture.Port.Type.DRIVEN;
    }

    private static DescribedPredicate<? super JavaClass> areDrivingPorts() {
        return new DescribedPredicate<JavaClass>("Driving port") {
            @Override
            public boolean apply(final JavaClass input) {
                return isADrivingPort(input);
            }
        };
    }

    private static DescribedPredicate<? super JavaClass> areDrivenPorts() {
        return new DescribedPredicate<JavaClass>("Driven port") {
            @Override
            public boolean apply(final JavaClass input) {
                return isADrivenPort(input);
            }
        };
    }

    private static ArchCondition<JavaClass> accessDrivingPorts() {
        return new ArchCondition<JavaClass>("access at least one driving port") {
            @Override
            public void check(final JavaClass item, final ConditionEvents events) {
                if (item.getAccessesFromSelf().stream().map(JavaAccess::getTargetOwner).noneMatch(HexagonalArchitectureRules::isADrivingPort)) {
                    events.add(violated(item, format("Type %s does not implement any driven port", item)));
                }
            }
        };
    }

    private static ArchCondition<JavaClass> implementADrivenPort() {
        return new ArchCondition<JavaClass>("implement a driven port") {
            @Override
            public void check(final JavaClass item, final ConditionEvents events) {
                if (item.getAllInterfaces().stream().noneMatch(HexagonalArchitectureRules::isADrivenPort)) {
                    events.add(violated(item, format("Type %s does not implement any driven port", item)));
                }
            }
        };
    }
}
