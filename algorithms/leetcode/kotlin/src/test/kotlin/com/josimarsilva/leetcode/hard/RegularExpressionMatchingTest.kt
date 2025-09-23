package com.josimarsilva.leetcode.hard

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegularExpressionMatchingTest {

    @ParameterizedTest
    @MethodSource("matchingStrings")
    fun `should return true for matching strings`(string: String, pattern: String) {
        assertThat(RegularExpressionMatching().isMatch(string, pattern)).isTrue
    }

    private fun matchingStrings(): Stream<Arguments> = Stream.of(
        Arguments.of("aa", "a*"),
        Arguments.of("ab", ".*"),
        Arguments.of("ab", ".b"),
        Arguments.of("", ""),
        Arguments.of("", "a*"),
        Arguments.of("aab", "c*a*b"),
    )

    @ParameterizedTest
    @MethodSource("nonMatchingStrings")
    fun `should return false for non-matching strings`(string: String, pattern: String) {
        assertThat(RegularExpressionMatching().isMatch(string, pattern)).isFalse
    }

    private fun nonMatchingStrings(): Stream<Arguments> = Stream.of(
        Arguments.of("aa", "a"),
        Arguments.of("bb", ""),
        Arguments.of("", "a")
    )
}
