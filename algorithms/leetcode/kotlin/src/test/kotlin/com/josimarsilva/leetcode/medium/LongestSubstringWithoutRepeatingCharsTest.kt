package com.josimarsilva.leetcode.medium

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LongestSubstringWithoutRepeatingCharsTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should find the length of the longest unique substring`(string: String, expectedLength: Int) {
        assertThat(LongestSubstringWithoutRepeatingChars()
            .lengthOfLongestSubstring(string)).isEqualTo(expectedLength)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of("abcabcbb", 3), // "abc"
        Arguments.of("bbbbb", 1), // "b"
        Arguments.of("pwwkew", 3), // "wke"
        Arguments.of("ab ab ab", 3), // "ab "
        Arguments.of("a a", 2), // "a "
    )

}