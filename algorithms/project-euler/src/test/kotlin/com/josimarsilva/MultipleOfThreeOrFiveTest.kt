package com.josimarsilva

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MultipleOfThreeOrFiveTest {

    @ParameterizedTest
    @MethodSource("multiplesOfThreeAndFive")
    fun `should return sum of multiple of three and five`(max: Int, expectedSum: Int) {
        assertThat(MultipleOfThreeOrFive().sumUpTo(max)).isEqualTo(expectedSum)
    }

    private fun multiplesOfThreeAndFive(): Stream<Arguments> = Stream.of(
        Arguments.of(10, 23),
        Arguments.of(1000, 233168),
    )

}
