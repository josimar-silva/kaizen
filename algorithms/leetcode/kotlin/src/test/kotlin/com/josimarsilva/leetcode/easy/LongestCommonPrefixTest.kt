package com.josimarsilva.leetcode.easy

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LongestCommonPrefixTest {

    @ParameterizedTest
    @MethodSource("validStrings")
    fun `should return LCP given strings have it`(strings: Array<String>, expectedPrefix: String) {
        assertThat(LongestCommonPrefix().longestCommonPrefix(strings)).isEqualTo(expectedPrefix)
    }

    private fun validStrings(): Stream<Arguments> = Stream.of(
        Arguments.of(arrayOf("flower", "flow", "flight"), "fl"),
        Arguments.of(arrayOf("apple"), "apple"),
        Arguments.of(arrayOf("flow", "flower", "flowing"), "flow"),
        Arguments.of(arrayOf("hello", "hello", "hello"), "hello"),
        Arguments.of(arrayOf("cir", "car"), "c")
    )

    @ParameterizedTest
    @MethodSource("invalidStrings")
    fun `should return empty given strings have it`(strings: Array<String>) {
        assertThat(LongestCommonPrefix().longestCommonPrefix(strings)).isEmpty()
    }

    private fun invalidStrings(): Stream<Arguments> = Stream.of(
        Arguments.of(arrayOf("dog", "racecar", "car")),
        Arguments.of(arrayOf("", "b")),
        Arguments.of(arrayOf("abc", "", "abcd")),
        Arguments.of(arrayOf("a", "b", "c"))
    )

}