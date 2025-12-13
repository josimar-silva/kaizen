package com.josimarsilva.leetcode.easy

import java.util.Stack

/**
 * Implement a first in first out (FIFO) queue using only two stacks.
 * The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 *
 * Implement the MyQueue class:
 *
 * void push(int x) Pushes element x to the back of the queue.
 * int pop() Removes the element from the front of the queue and returns it.
 * int peek() Returns the element at the front of the queue.
 * boolean empty() Returns true if the queue is empty, false otherwise.

 * Notes:
 * - You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * - Depending on your language, the stack may not be supported natively.
 *   You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 *
 *   Ref.: https://leetcode.com/problems/implement-queue-using-stacks
 *
 */
class MyQueue() {

    private val write = Stack<Int>()
    private val read = Stack<Int>()
    private var front = -1

    fun push(number: Int) {
        if(write.isEmpty())  {
            front = number
        }

        write.push(number)
    }

    fun pop(): Int {
        moveElements()
        return read.pop()
    }

    private fun moveElements() {
        if (read.isEmpty()) {
            while (write.isNotEmpty()) {
                read.push(write.pop())
            }
        }
    }

    fun peek(): Int {
        return if(read.isNotEmpty()) return read.peek() else front
    }

    fun empty(): Boolean {
        return write.isEmpty() && read.isEmpty()
    }

}
