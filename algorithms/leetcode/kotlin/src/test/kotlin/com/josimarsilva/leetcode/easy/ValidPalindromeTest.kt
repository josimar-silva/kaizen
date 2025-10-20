package com.josimarsilva.leetcode.easy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ValidPalindromeTest {

    @ParameterizedTest
    @MethodSource("validSamples")
    fun `should return true given input is a palindrome`(string: String) {
        assertThat(isPalindrome(string)).isTrue
        assertThat(isPalindromeNaive(string)).isTrue
    }

    private fun validSamples(): Stream<Arguments> = Stream.of(
        Arguments.of("A man, a plan, a canal: Panama"),
        Arguments.of("1Tenet1"),
        Arguments.of("Aboboba"),
        Arguments.of(" "),
        Arguments.of("."),
        Arguments.of("level"),
        Arguments.of(".,"),
    )

    @ParameterizedTest
    @MethodSource("invalidSamples")
    fun `should return false given input is not a palindrome`(string: String) {
        assertThat(isPalindrome(string)).isFalse
        assertThat(isPalindromeNaive(string)).isFalse
    }

    private fun invalidSamples(): Stream<Arguments> = Stream.of(
        Arguments.of("race a car"),
        Arguments.of("race a 1 car"),
        Arguments.of("potatoes"),
    )

}
