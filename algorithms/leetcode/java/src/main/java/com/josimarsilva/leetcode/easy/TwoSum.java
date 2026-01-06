package com.josimarsilva.leetcode.easy;

import java.util.HashMap;

/**
 *
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 * </p>
 * <p>
 * You can return the answer in any order.
 * </p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 * </p>
 *
 * <p>
 * <a href="https://leetcode.com/problems/two-sum/description/">Reference</a>
 * </p>
 *
 * @param numbers
 * @param target
 */
record TwoSum(int[] numbers, int target) {

    //Time: O(N) Space: O(N)
    public int[] calculate() {
        if (numbers.length == 0) return new int[0];

        final var remainders = new HashMap<Integer, Integer>();

        for (int i = 0; i < numbers.length; i++) {
            final var remainder = target - numbers[i];
            final var remainderIndex = remainders.get(remainder);

            if (remainderIndex != null) {
                return new int[]{remainderIndex, i};
            }

            remainders.put(numbers[i], i);
        }

        return new int[0];
    }

}
