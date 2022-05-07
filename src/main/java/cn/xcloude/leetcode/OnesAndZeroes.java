package cn.xcloude.leetcode;

import java.util.Arrays;

/**
 * leetcode 474
 * 一和零
 */
public class OnesAndZeroes {
  public int findMaxForm(String[] strs, int m, int n) {
    int[] zeros = new int[strs.length], ones = new int[strs.length];
    for (int index = 0; index < strs.length; ++index) {
      for (int charIndex = 0; charIndex < strs[index].length(); ++charIndex) {
        if (strs[index].charAt(charIndex) == '0') {
          ++zeros[index];
        }
      }
      ones[index] = strs[index].length() - zeros[index];
    }

    // return findMaxForm(zeros, ones, m, n, 0, 0, 0, generateMemorandum(strs, m, n));
    return findMaxForm(zeros, ones, m, n);
  }

  // 动态规划
  private int findMaxForm(int[] zeros, int[] ones, int maxZeros, int maxOnes) {
    int[][] dp = new int[maxZeros + 1][maxOnes + 1];
    for (int index = 0; index < zeros.length; ++index) {
      for (int currentZeros = maxZeros; currentZeros >= zeros[index]; --currentZeros) {
        for (int currentOnes = maxOnes; currentOnes >= ones[index]; --currentOnes) {
          dp[currentZeros][currentOnes] = Math.max(dp[currentZeros][currentOnes],
              dp[currentZeros - zeros[index]][currentOnes - ones[index]] + 1);
        }
      }
    }

    return dp[maxZeros][maxOnes];
  }

  // 带备忘录的递归
  private int findMaxForm(int[] zeros, int[] ones, int maxZeros, int maxOnes, int index,
      int currentZeros, int currentOnes, int[][][] memorandum) {
    if (index >= zeros.length) {
      return 0;
    }

    if (memorandum[index][currentZeros][currentOnes] != -1) {
      return memorandum[index][currentZeros][currentOnes];
    }

    if (currentZeros + zeros[index] > maxZeros || currentOnes + ones[index] > maxOnes) {
      memorandum[index][currentZeros][currentOnes] = findMaxForm(zeros, ones, maxZeros, maxOnes,
          index + 1, currentZeros, currentOnes, memorandum);
    } else {
      memorandum[index][currentZeros][currentOnes] = Math.max(findMaxForm(zeros, ones, maxZeros,
              maxOnes, index + 1, currentZeros, currentOnes, memorandum),
          findMaxForm(zeros, ones, maxZeros, maxOnes, index + 1, currentZeros + zeros[index],
              currentOnes + ones[index], memorandum) + 1);
    }

    return memorandum[index][currentZeros][currentOnes];
  }

  private int[][][] generateMemorandum(String[] strs, int m, int n) {
    int[][][] memorandum = new int[strs.length][m + 1][n + 1];
    for (int index = 0; index < strs.length; ++index) {
      for (int zeros = 0; zeros <= m; ++zeros) {
        Arrays.fill(memorandum[index][zeros], -1);
      }
    }
    return memorandum;
  }
}
