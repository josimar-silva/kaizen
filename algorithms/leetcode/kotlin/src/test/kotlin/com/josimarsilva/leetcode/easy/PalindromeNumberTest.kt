package com.josimarsilva.leetcode.easy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PalindromeNumberTest {

    @ParameterizedTest
    @MethodSource("palindromeNumbers")
    fun `should return true for palindrome numbers`(number: Int) {
        assertThat(PalindromeNumber().isPalindrome(number)).isTrue()
    }

    private fun palindromeNumbers(): Stream<Arguments> = Stream.of(
        Arguments.of(121),
        Arguments.of(0),
        Arguments.of(1),
        Arguments.of(1001)
    )

    @ParameterizedTest
    @MethodSource("nonPalindromeNumbers")
    fun `should return false for non-palindrome numbers`(number: Int) {
        assertThat(PalindromeNumber().isPalindrome(number)).isFalse()
    }

    private fun nonPalindromeNumbers(): Stream<Arguments> = Stream.of(
        Arguments.of(123),
        Arguments.of(-121),
        Arguments.of(10),
        Arguments.of(123456)
    )
}