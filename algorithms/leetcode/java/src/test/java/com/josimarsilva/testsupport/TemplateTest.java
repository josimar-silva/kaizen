package com.josimarsilva.testsupport;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TemplateTest {

    @ParameterizedTest
    @MethodSource("getSamples")
    public void shouldParseBooleanString(String booleanAsString, Boolean expectedResult) {
        assertThat(Boolean.valueOf(booleanAsString)).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> getSamples() {
        return Stream.of(
                Arguments.of("true", true),
                Arguments.of("false", false)
        );
    }

}
