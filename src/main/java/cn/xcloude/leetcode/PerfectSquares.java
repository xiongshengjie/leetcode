package cn.xcloude.leetcode;

/**
 * leetcode 279
 * 完全平方数
 */
public class PerfectSquares {
  public int numSquares(int n) {
    int[] dp = new int[n + 1];
    for (int index = 0; index <= n; ++index) {
      dp[index] = index;
    }

    int size = (int) Math.sqrt(n);
    for (int index = 2; index <= size; ++index) {
      int squares = index * index;
      for (int current = squares; current <= n; ++current) {
        dp[current] = Math.min(dp[current], dp[current - squares] + 1);
      }
    }

    return dp[n];
  }
}
