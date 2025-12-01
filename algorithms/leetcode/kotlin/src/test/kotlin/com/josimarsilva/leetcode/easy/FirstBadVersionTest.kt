package com.josimarsilva.leetcode.easy;

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FirstBadVersionTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should return the first bad version`(currentVersion: Int, firstBadVersion: Int) {
        val solution = FirstBadVersion(firstBadVersion)

        assertThat(solution.firstBadVersionNaive(currentVersion)).isEqualTo(firstBadVersion)
        assertThat(solution.firstBadVersion(currentVersion)).isEqualTo(firstBadVersion)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of(5, 4),
        Arguments.of(1, 1),
        Arguments.of(2, 2),
        Arguments.of(2126753390, 1702766719),
    )

}
