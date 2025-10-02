package com.josimarsilva.projecteuler.java.common;

public record Range(int start, int end) {
    public static Range from(int start, int end) {
        return new Range(start, end);
    }
}
