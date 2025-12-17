package com.josimarsilva.leetcode.easy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IsSubsequenceTest {

    @ParameterizedTest
    @MethodSource("validSamples")
    fun `should return true given valid subsequence`(string: String, target: String) {
        assertThat(isSubsequence(string, target)).isTrue
    }

    private fun validSamples(): Stream<Arguments> = Stream.of(
        Arguments.of("abc", "abcde"),
        Arguments.of("ta", "potato"),
        Arguments.of("banana", "banana"),
        Arguments.of("", "banana"),
        Arguments.of("", "  "),
    )

    @ParameterizedTest
    @MethodSource("invalidSamples")
    fun `should return false given valid subsequence`(string: String, target: String) {
        assertThat(isSubsequence(string, target)).isFalse
    }

    private fun invalidSamples(): Stream<Arguments> = Stream.of(
        Arguments.of("axc", "abcde"),
        Arguments.of("apple", "potato"),
        Arguments.of("acb", "ahbgdc"),
    )

    @ParameterizedTest
    @MethodSource("validSamplesFollowup")
    fun `should return true given a list of strings contains all valid subsequences`(strings: List<String>, target: String) {
        assertThat(isSubsequenceFollowUp(strings, target)).isTrue
    }

    private fun validSamplesFollowup(): Stream<Arguments> = Stream.of(
        Arguments.of(listOf("abc", "bc", "cd", "de"), "abcde"),
        Arguments.of(listOf("ta", "ot", "to"), "potato"),
        Arguments.of(listOf("banana"), "banana"),
        Arguments.of(listOf("", "ana"), "banana"),
        Arguments.of(listOf("yy", "yyyyy", "yx"), "yyyyyxxx"),
        Arguments.of(listOf(""), "  "),
    )

    @ParameterizedTest
    @MethodSource("invalidSamplesFollowup")
    fun `should return false given list of strings is contains invalid subsequences`(strings: List<String>, target: String) {
        assertThat(isSubsequenceFollowUp(strings, target)).isFalse
    }

    private fun invalidSamplesFollowup(): Stream<Arguments> = Stream.of(
        Arguments.of(listOf("axc", "xde", "abx"), "abcde"),
        Arguments.of(listOf("apple", "otas"), "potato"),
        Arguments.of(listOf("acb"), "ahbgdc"),
    )

}
