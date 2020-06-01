package com.pse.coffee;

import com.pse.commons.HexagonalArchitectureRules;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchRules;
import com.tngtech.archunit.junit.ArchTest;

@AnalyzeClasses(packages = "com.pse.coffee", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {

    @ArchTest
    static final ArchRules hexagonal_architecture = ArchRules.in(HexagonalArchitectureRules.class);

}
