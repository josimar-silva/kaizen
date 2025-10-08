package com.josimarsilva.leetcode.easy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LongestPalindromeTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return the longest palindrome`(string: String, expectedPalindromeLength: Int) {
        assertThat(LongestPalindrome().longestPalindrome(string)).isEqualTo(expectedPalindromeLength)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of("abccccdd", 7), // dccaccd
        Arguments.of("a", 1), // a
        Arguments.of("", 0), // Empty string
        Arguments.of("aaaaa", 5), // All same characters
        Arguments.of("abc", 1), // All unique characters
        Arguments.of("abccbaA", 7), // Mixed case characters
        Arguments.of("aabbc", 5), // Two distinct characters, one odd, one even
        Arguments.of("aabbcc", 6), // Two distinct characters, both even
        Arguments.of("bananas", 5) // Longer string with various characters
    )
}