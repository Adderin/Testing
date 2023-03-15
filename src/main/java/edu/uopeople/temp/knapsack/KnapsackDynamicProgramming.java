package edu.uopeople.temp.knapsack;

public class KnapsackDynamicProgramming {

    static final int[] WEIGHTS = {1, 4, 6, 2, 5, 10, 8, 3, 9, 1, 4, 2, 5, 8, 9, 1};
    static final int[] VALUES = {10, 5, 30, 8, 12, 30, 50, 10, 2, 10, 40, 80, 100, 25, 10, 5};
    static final int CAPACITY = 20; // Max weight in pounds that can be carried by thief
    static final int ITEMS_NUMBER = WEIGHTS.length;  // number of items in the store

    public static void main(String[] args) {
        // I will use this two-dimensional array for dynamic programming
        int[][] dp = new int[ITEMS_NUMBER + 1][CAPACITY + 1];
        // to calculate number of iterations
        int iterations = 0;

        // first loop to iterate over all items
        for (int i = 1; i <= ITEMS_NUMBER; i++) {
            int weight = WEIGHTS[i - 1];
            int value = VALUES[i - 1];

            // second loop to iterate over knapsack capacity
            for (int j = 1; j <= CAPACITY; j++) {
                if (weight > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight] + value);
                }
                iterations++;
            }
        }

        // reconstruct weight from dp table
        int weight = 0;

        for (int i = ITEMS_NUMBER, j = CAPACITY; i > 0 && j > 0; i--) {
            if (dp[i][j] != dp[i - 1][j]) {
                weight += WEIGHTS[i - 1];
                j -= WEIGHTS[i - 1];
            }
        }

        int value = dp[ITEMS_NUMBER][CAPACITY];

        System.out.println("Weight of the knapsack: " + weight);
        System.out.println("Value of the knapsack's content: " + value);
        System.out.println("Number of iterations needed: " + iterations);
    }
}