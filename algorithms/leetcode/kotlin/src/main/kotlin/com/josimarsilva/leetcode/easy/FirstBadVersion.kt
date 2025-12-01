package com.josimarsilva.leetcode.easy

/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
 * which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad.
 * Implement a function to find the first bad version.
 * You should minimize the number of calls to the API.
 *
 * Constraints:
 *  1 <= bad <= n <= 231 - 1
 *
 */
open class VersionControl(val badVersion: Int) {

    fun isBadVersion(version: Int) = version >= badVersion
}

class FirstBadVersion(badVersion: Int) : VersionControl(badVersion) {

    //Time: O(log N) Space: O(1)
    fun firstBadVersion(latestVersion: Int): Int {
        var left = 1
        var right = latestVersion

        while (left < right) {
            val midPosition = left + (right - left) / 2
            if (isBadVersion(midPosition)) {
                right = midPosition
            } else {
                left = midPosition + 1
            }
        }

        return left
    }

    //Time: O(N) Space: O(1)
    fun firstBadVersionNaive(currentVersion: Int): Int {
        var firstBadVersion = currentVersion
        for (i in currentVersion downTo 0) {
            if (isBadVersion(i)) {
                firstBadVersion = i
            }
        }

        return firstBadVersion
    }
}
