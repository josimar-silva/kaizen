package com.josimarsilva.leetcode.easy;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.josimarsilva.leetcode.easy.FloodFill.newInstance;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FloodFillTest {

    @ParameterizedTest
    @MethodSource("getSamples")
    public void shouldChangePixelsColorToGivenColor(int[][] image, int sr, int sc, int color, int[][] expectedImage) {
        assertThat(newInstance().floodFill(image, sr, sc, color)).isEqualTo(expectedImage);
    }

    private static Stream<Arguments> getSamples() {
        return Stream.of(
                Arguments.of(new int[0][0], 0, 0, 0, new int[0][0]),
                Arguments.of(new int[][]{
                        new int[]{1, 1, 1},
                        new int[]{1, 1, 0},
                        new int[]{1, 0, 1},
                }, 1, 1, 2, new int[][]{
                        new int[]{2, 2, 2},
                        new int[]{2, 2, 0},
                        new int[]{2, 0, 1},
                }),
                Arguments.of(new int[][]{
                        new int[]{1, 1, 1},
                        new int[]{1, 1, 1}
                }, 1, 0, 2, new int[][]{
                        new int[]{2, 2, 2},
                        new int[]{2, 2, 2}
                })
        );
    }

}
