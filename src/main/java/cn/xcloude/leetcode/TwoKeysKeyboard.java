package cn.xcloude.leetcode;

/**
 * leetcode 650
 * 只有两个键的键盘
 */
public class TwoKeysKeyboard {
  public int minSteps(int n) {
    // return minStepsDp(n);
    return minStepsMath(n);
  }

  // 数学分析
  private int minStepsMath(int n) {
    int result = 0;
    for (int factor = 2; factor * factor <= n; ++factor) {
      while (n % factor == 0) {
        n /= factor;
        result += factor;
      }
    }

    if (n > 1) {
      result += n;
    }

    return result;
  }

  // 动态规划
  private int minStepsDp(int n) {
    int[] dp = new int[n + 1];
    dp[1] = 0;
    for (int index = 2; index <= n; ++index) {
      dp[index] = index;
      for (int factor = 2; factor * factor <= index; ++factor) {
        if (index % factor == 0) {
          dp[index] = Math.min(dp[index], dp[factor] + index / factor);
          dp[index] = Math.min(dp[index], dp[index / factor] + factor);
        }
      }
    }

    return dp[n];
  }
}
