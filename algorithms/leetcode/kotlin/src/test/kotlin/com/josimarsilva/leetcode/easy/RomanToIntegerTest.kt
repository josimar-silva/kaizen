package com.josimarsilva.leetcode.easy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RomanToIntegerTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should convert Roman number to Integer`(romanNumber: String, expectedNumber: Int) {
        assertThat(RomanToInteger().romanToInt(romanNumber)).isEqualTo(expectedNumber)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of("III", 3),
        Arguments.of("IV", 4),
        Arguments.of("XXI", 21),
        Arguments.of("XL", 40),
        Arguments.of("LVIII", 58),
        Arguments.of("XC", 90),
        Arguments.of("CD", 400),
        Arguments.of("CM", 900),
        Arguments.of("MCMXCIV", 1994)
    )
}