package com.josimarsilva.leetcode.easy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RansomNoteTest {

    @ParameterizedTest
    @MethodSource("validSamples")
    fun `can be construct from original magazine`(ransomNote: String, magazine: String) {
        assertThat(RansomNote().canConstruct(ransomNote, magazine)).isTrue
        assertThat(RansomNote().canConstructWithMap(ransomNote, magazine)).isTrue
    }

    private fun validSamples(): Stream<Arguments> = Stream.of(
        Arguments.of("", ""),
        Arguments.of("aa", "aab"),
        Arguments.of("sab", "aaabbbcccxxxxs"),
    )

    @ParameterizedTest
    @MethodSource("invalidSamples")
    fun `can not be construct from valid magazine`(ransomNote: String, magazine: String) {
        assertThat(RansomNote().canConstruct(ransomNote, magazine)).isFalse
        assertThat(RansomNote().canConstructWithMap(ransomNote, magazine)).isFalse
    }

    private fun invalidSamples(): Stream<Arguments> = Stream.of(
        Arguments.of("a", "b"),
        Arguments.of("b", ""),
        Arguments.of("aa", "ab"),
    )

}