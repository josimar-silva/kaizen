package com.josimarsilva.leetcode.medium

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StringToIntegerTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should convert a string to integer`(string: String, expectedInt: Int) {
        assertThat(StringToInteger().myAtoi(string)).isEqualTo(expectedInt)
    }

    fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(" -042", -42),
        Arguments.of("1", 1),
        Arguments.of("42", 42),
        Arguments.of("222", 222),
        Arguments.of("  3333", 3333),
        Arguments.of("1337c0d3", 1337),
        Arguments.of("0-1", 0),
        Arguments.of("words and 987", 0),
        Arguments.of("00000-42a1234", 0),
        Arguments.of("-91283472332", -2147483648),
        Arguments.of(".1", 0),
        Arguments.of("+-12", 0),
        Arguments.of("2147483648", 2147483647),
        Arguments.of("21474836460", 2147483647),
        Arguments.of("-2147483647", -2147483647),
        Arguments.of("-2147483649", -2147483648),
        Arguments.of("  +  30", 0)
    )

}