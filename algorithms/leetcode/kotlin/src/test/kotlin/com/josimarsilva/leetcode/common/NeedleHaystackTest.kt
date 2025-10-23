package com.josimarsilva.leetcode.common;

import com.josimarsilva.leetcode.easy.strStrNaive
import com.josimarsilva.leetcode.easy.strStrNative
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NeedleHaystackTest {

    @ParameterizedTest
    @MethodSource("samples")
    fun `should find a needle in the haystack`(haystack: String, needle: String, expectedLocation: Int) {
        assertThat(strStrNaive(haystack, needle)).isEqualTo(expectedLocation)
        assertThat(strStrNative(haystack, needle)).isEqualTo(expectedLocation)
    }

    private fun samples(): Stream<Arguments> = Stream.of(
        Arguments.of("sadbutsad", "sad", 0),
        Arguments.of("leetcode", "leeto", -1),
        Arguments.of("", "blank", -1),
        Arguments.of("blank", "", 0),
        Arguments.of("", "", 0),
        Arguments.of("ahugestackofwordspoemsandaislopthattheinternettoday", "today", 46),
    )

}
