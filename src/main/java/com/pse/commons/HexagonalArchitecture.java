package com.pse.commons;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>The idea behind Hexagonal Architecture, aka Ports & Adapters Architecture, is to think about our application as
 * the central artefact of a system, where all input and output reaches/leaves the application through a port that
 * isolates the application from external tools, technologies and delivery mechanisms. The application should have no
 * knowledge of who/what is sending input or receiving its output. This is intended to provide some protection against
 * the evolution of technology and business requirements, which can make products obsolete shortly after they are
 * developed, because of technology/vendor lock-down.</p>
 * <p>To put it simply, <b>it has only one goal: isolate the business logic from the delivery mechanisms and tools used
 * by the system.</b></p>
 * <p>Cf. <a href="https://herbertograca.com/2017/09/14/ports-adapters-architecture">this article by Herberto Graca</a></p>
 */
public final class HexagonalArchitecture {

    private HexagonalArchitecture() {}

    /**
     * <p>A port is a consumer agnostic entry and exit point to/from the application. In many languages, it will be an
     * interface. For example, it can be an interface used to perform searches in a search engine. In our application,
     * we will use this interface as an entry and/or exit point with no knowledge of the concrete implementation that
     * will actually be injected where the interface is defined as a type hint.</p>
     */
    @Retention(RUNTIME)
    @Target(TYPE_USE)
    public @interface Port {
        enum Type { DRIVING, DRIVEN }
        Type value();
    }

    /**
     * <p>In an hexagon, left adapters are adapters that are used to call the domain.</p>
     * <p>An adapter is a class that transforms (adapts) an interface into another.</p>
     * <p>For example, an adapter implements an interface A and gets injected an interface B. When the adapter is
     * instantiated it gets injected in its constructor an object that implements interface B. This adapter is then
     * injected wherever interface A is needed and receives method requests that it transforms and proxies to the inner
     * object that implements interface B.</p>
     */
    @Retention(RUNTIME)
    @Target(TYPE_USE)
    public @interface LeftAdapter {}

    /**
     * <p>In an hexagon, right adapters are adapters that are called by the domain.</p>
     * <p>An adapter is a class that transforms (adapts) an interface into another.</p>
     * <p>For example, an adapter implements an interface A and gets injected an interface B. When the adapter is
     * instantiated it gets injected in its constructor an object that implements interface B. This adapter is then
     * injected wherever interface A is needed and receives method requests that it transforms and proxies to the inner
     * object that implements interface B.</p>
     */
    @Retention(RUNTIME)
    @Target(TYPE_USE)
    public @interface RightAdapter {}
}
