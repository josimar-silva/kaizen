package com.josimarsilva.leetcode.easy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddBinaryTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should sum two binary as String`(a: String, b: String, expectedResult: String) {
        assertThat(addBinary(a, b)).isEqualTo(expectedResult)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of("11", "1", "100"),
        Arguments.of("1010", "1011", "10101"),
    )

}