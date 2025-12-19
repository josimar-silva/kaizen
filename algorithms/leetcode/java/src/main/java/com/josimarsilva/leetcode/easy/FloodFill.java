package com.josimarsilva.leetcode.easy;

/**
 * You are given an image represented by an m x n image of integers image, where image[i][j] represents the pixel value of the image.
 * You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].
 * <p>
 * To perform a flood fill:
 * 1. Begin with the starting pixel and change its color to color.
 * 2. Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel,
 * either horizontally or vertically) and shares the same color as the starting pixel.
 * 3. Keep repeating this process by checking neighboring pixels of the updated pixels and
 * modifying their color if it matches the original color of the starting pixel.
 * 4. The process stops when there are no more adjacent pixels of the original color to update.
 * <p>
 * Return the modified image after performing the flood fill.
 * <p>
 * <p>
 * Constraints:
 * - m == image.length
 * - n == image[i].length
 * - 1 <= m, n <= 50
 * - 0 <= image[i][j], color < 216
 * - 0 <= sr < m
 * - 0 <= sc < n
 * <p>
 * <a href="https://leetcode.com/problems/flood-fill">Reference</a>
 */
public class FloodFill {

    public static FloodFill newInstance() {
        return new FloodFill();
    }

    public void dfs(int[][] image, int sr, int sc, int color, int newColor) {
        if (image[sr][sc] != color) return;

        image[sr][sc] = newColor;

        if (sr >= 1) { dfs(image, sr - 1, sc, color, newColor); }                  // ↓
        if (sr + 1 < image.length) { dfs(image, sr + 1, sc, color, newColor); }    // ↑
        if (sc >= 1) { dfs(image, sr, sc - 1, color, newColor); }                  // <-
        if (sc + 1 < image[0].length) { dfs(image, sr, sc + 1, color, newColor); } // ->
    }

    //Time: O(N) Space: O(N)
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image.length == 0) return image;

        final var color = image[sr][sc];

        if (color != newColor) {
            dfs(image, sr, sc, color, newColor);
        }

        return image;
    }

}
