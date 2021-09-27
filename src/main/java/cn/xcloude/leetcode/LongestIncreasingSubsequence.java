package cn.xcloude.leetcode;

/**
 * leetcode 300
 * 最长递增子序列
 */
public class LongestIncreasingSubsequence {
  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    return lengthOfLisDp(nums);
  }

  // 动态规划
  public int lengthOfLisDp(int[] nums) {
    int[] dp = new int[nums.length];
    dp[0] = 1;
    int result = 1;
    for (int index = 1; index < nums.length; ++index) {
      dp[index] = 1;
      for (int dpIndex = 0; dpIndex < index; ++dpIndex) {
        if (nums[dpIndex] < nums[index] && dp[index] < dp[dpIndex] + 1) {
          dp[index] = dp[dpIndex] + 1;
        }
      }

      if (result < dp[index]) {
        result = dp[index];
      }
    }

    return result;
  }
}
