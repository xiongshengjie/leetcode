package cn.xcloude.leetcode;

import java.util.Arrays;

/**
 * leetcode 115
 * 不同的子序列
 */
public class DistinctSubsequences {
  public int numDistinct(String s, String t) {
    // return numDistinctWithMemorandum(s, t);
    return numDistinctDp(s, t);
  }

  // 动态规划
  private int numDistinctDp(String s, String t) {
    int[][] dp = new int[s.length() + 1][t.length() + 1];
    for (int index = 0; index <= s.length(); ++index) {
      dp[index][t.length()] = 1;
    }

    for (int sIndex = s.length() - 1; sIndex >= 0; --sIndex) {
      for (int tIndex = t.length() - 1; tIndex >= 0; --tIndex) {
        if (s.charAt(sIndex) == t.charAt(tIndex)) {
          dp[sIndex][tIndex] = dp[sIndex + 1][tIndex + 1] + dp[sIndex + 1][tIndex];
        } else {
          dp[sIndex][tIndex] = dp[sIndex + 1][tIndex];
        }
      }
    }

    return dp[0][0];
  }

  // 带备忘录的递归
  private int numDistinctWithMemorandum(String s, String t) {
    int[][] memorandum = new int[s.length()][t.length()];
    for (int index = 0; index < s.length(); ++index) {
      int[] inner = new int[t.length()];
      Arrays.fill(inner, -1);
      memorandum[index] = inner;
    }
    return numDistinct0(s, t, 0, 0, memorandum);
  }

  private int numDistinct0(String s, String t, int sIndex, int tIndex, int[][] memorandum) {
    if (t.length() - tIndex > s.length() - sIndex) {
      return 0;
    }

    if (tIndex >= t.length()) {
      return 1;
    }

    if (memorandum[sIndex][tIndex] != -1) {
      return memorandum[sIndex][tIndex];
    }

    if (s.charAt(sIndex) == t.charAt(tIndex)) {
      memorandum[sIndex][tIndex] = numDistinct0(s, t, sIndex + 1, tIndex + 1, memorandum)
          + numDistinct0(s, t, sIndex + 1, tIndex, memorandum);
    } else {
      memorandum[sIndex][tIndex] = numDistinct0(s, t, sIndex + 1, tIndex, memorandum);
    }

    return memorandum[sIndex][tIndex];
  }
}
