package com.josimarsilva.leetcode.common;

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TemplateTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should parse a boolean string`(boolean: String, expectedResult: Boolean) {
        assertThat(boolean.toBoolean()).isEqualTo(expectedResult)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of("true", true),
        Arguments.of("false", false),
    )

}
