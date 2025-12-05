package com.josimarsilva.leetcode.easy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ValidAnagramTest {

    @ParameterizedTest
    @MethodSource("validSamples")
    fun `should return true for valid anagrams`(s: String, t: String) {
        assertThat(isAnagram(s, t)).isTrue
        assertThat(isAnagramUsingMap(s, t)).isTrue
    }

    private fun validSamples(): Stream<Arguments> = Stream.of(
        Arguments.of("anagram", "nagaram"),
        Arguments.of("iamlordvoldemort", "tommarvoloriddle"),
        Arguments.of("tenet", "tente"),
    )

    @ParameterizedTest
    @MethodSource("invalidSamples")
    fun `should return false for invalid anagrams`(s: String, t: String) {
        assertThat(isAnagram(s, t)).isFalse
        assertThat(isAnagramUsingMap(s, t)).isFalse
    }

    private fun invalidSamples(): Stream<Arguments> = Stream.of(
        Arguments.of("banana", "ananas"),
        Arguments.of("bee", "see"),
        Arguments.of("a", "b"),
    )

}
