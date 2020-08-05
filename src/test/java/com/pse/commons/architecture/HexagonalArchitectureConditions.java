package com.pse.commons.architecture;

import org.assertj.core.api.Condition;

import java.lang.reflect.Modifier;

import static com.pse.commons.architecture.HexagonalArchitecture.Port.Type.DRIVEN;
import static com.pse.commons.architecture.HexagonalArchitecture.Port.Type.DRIVING;

public class HexagonalArchitectureConditions {

    private HexagonalArchitectureConditions() {}

    public static Condition<? super Class<?>> aDrivenPort() {
        return new Condition<>(type -> isAbstract(type) && hasDrivenPortAnnotation(type),
                "A port (abstract and annotated with @HexagonalArchitecture.Port(DRIVEN)");
    }

    public static Condition<? super Class<?>> aDrivingPort() {
        return new Condition<>(type -> isAbstract(type) && hasDrivingPortAnnotation(type),
                "A port (abstract and annotated with @HexagonalArchitecture.Port(DRIVING)");
    }

    public static Condition<? super Class<?>> aLeftAdapter() {
        return new Condition<>(HexagonalArchitectureConditions::hasLeftAdapterAnnotation,
                "A left adapter (annotated with @HexagonalArchitecture.LeftAdapter)");
    }

    public static Condition<? super Class<?>> aRightAdapter() {
        return new Condition<>(HexagonalArchitectureConditions::hasRightAdapterAnnotation,
                "A right adapter (annotated with @HexagonalArchitecture.RightAdapter)");
    }

    private static boolean isAbstract(final Class<?> javaClass) {
        return Modifier.isAbstract(javaClass.getModifiers());
    }

    private static boolean hasDrivingPortAnnotation(final Class<?> javaClass) {
        return hasPortAnnotationWithType(javaClass, DRIVING);
    }

    private static boolean hasDrivenPortAnnotation(final Class<?> javaClass) {
        return hasPortAnnotationWithType(javaClass, DRIVEN);
    }

    private static boolean hasPortAnnotationWithType(final Class<?> javaClass, final HexagonalArchitecture.Port.Type portType) {
        return javaClass.getDeclaredAnnotation(HexagonalArchitecture.Port.class) != null
                && javaClass.getDeclaredAnnotation(HexagonalArchitecture.Port.class).value() == portType;
    }

    private static boolean hasLeftAdapterAnnotation(final Class<?> javaClass) {
        return javaClass.getDeclaredAnnotation(HexagonalArchitecture.LeftAdapter.class) != null;
    }

    private static boolean hasRightAdapterAnnotation(final Class<?> javaClass) {
        return javaClass.getDeclaredAnnotation(HexagonalArchitecture.RightAdapter.class) != null;
    }

}
