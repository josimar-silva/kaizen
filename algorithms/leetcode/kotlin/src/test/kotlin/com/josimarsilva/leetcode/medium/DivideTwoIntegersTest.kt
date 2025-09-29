package com.josimarsilva.leetcode.medium

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DivideTwoIntegersTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should divide two integers`(dividend: Int, divisor: Int, expectedResult: Int) {
        assertThat(DivideTwoIntegers().divide(dividend, divisor)).isEqualTo(expectedResult)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(10, 3, 3),
        Arguments.of(9, 3, 3),
        Arguments.of(7, -3, -2),
        Arguments.of(-10, -3, 3),
        Arguments.of(-9, -3, 3),
        Arguments.of(Int.MIN_VALUE, -1, Int.MAX_VALUE),
        Arguments.of(Int.MIN_VALUE, 1, Int.MIN_VALUE),
        Arguments.of(Int.MAX_VALUE, -1, Int.MIN_VALUE+1),
        Arguments.of(Int.MAX_VALUE, 1, Int.MAX_VALUE),
    )

}