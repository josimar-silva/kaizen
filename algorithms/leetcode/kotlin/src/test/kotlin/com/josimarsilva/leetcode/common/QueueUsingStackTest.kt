package com.josimarsilva.leetcode.common;

import com.josimarsilva.leetcode.easy.MyQueue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QueueUsingStackTest {

    @Test
    fun `should push a Number to the queue`() {
        // given
        val aNumber = 1
        val queue = MyQueue()

        // when
        queue.push(aNumber)

        // then
        assertThat(queue.peek()).isEqualTo(aNumber)
        assertThat(queue.pop()).isEqualTo(aNumber)
        assertThat(queue.empty()).isTrue
    }

    @Test
    fun `should return true given an empty queue`() {
        assertThat(MyQueue().empty()).isTrue
    }

    @Test
    fun `should return false given a queue with values`() {
        // given
        val queue = MyQueue()
        queue.push(1)
        queue.push(2)

        // then
        assertThat(queue.empty()).isFalse
    }

    @Test
    fun `should return peek the last element given a queue with multiple elements`() {
        // given
        val queue = MyQueue()

        // when
        for (i in 1..10) { queue.push(i) }

        // then
        assertThat(queue.peek()).isEqualTo(1)
    }

}
