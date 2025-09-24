package com.josimarsilva.leetcode.easy

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ValidParenthesesTest {

    @ParameterizedTest
    @DisplayName("${0} should be valid")
    @MethodSource("validStrings")
    fun `should return true for valid strings`(string: String) {
        assertThat(ValidParentheses().isValid(string)).isTrue
    }

    private fun validStrings(): Stream<Arguments> = Stream.of(
        Arguments.of("()"),
        Arguments.of("()[]{}"),
        Arguments.of("([])"),
        Arguments.of("{}")
    )

    @ParameterizedTest
    @MethodSource("invalidStrings")
    fun `should return false for invalid strings`(string: String) {
        assertThat(ValidParentheses().isValid(string)).isFalse
    }

    private fun invalidStrings(): Stream<Arguments> = Stream.of(
        Arguments.of("{"),
        Arguments.of("(("),
        Arguments.of("]]"),
        Arguments.of("(]"),
        Arguments.of("([)]"),
        Arguments.of("(){}}{")
    )

}
