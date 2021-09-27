package cn.xcloude.leetcode;

public class NumberOfLongestIncreasingSubsequence {
  public int findNumberOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    return findNumberOfLisDp(nums);
  }

  // 动态规划
  public int findNumberOfLisDp(int[] nums) {
    int[] dp = new int[nums.length], count = new int[nums.length];
    dp[0] = 1;
    count[0] = 1;
    int lisLength = 1, result = 1;
    for (int index = 1; index < nums.length; ++index) {
      dp[index] = 1;
      count[index] = 1;
      for (int dpIndex = 0; dpIndex < index; ++dpIndex) {
        if (nums[dpIndex] < nums[index]) {
          if (dp[index] < dp[dpIndex] + 1) {
            dp[index] = dp[dpIndex] + 1;
            count[index] = count[dpIndex];
          } else if (dp[index] == dp[dpIndex] + 1) {
            count[index] += count[dpIndex];
          }
        }
      }

      if (lisLength < dp[index]) {
        lisLength = dp[index];
        result = count[index];
      } else if (lisLength == dp[index]) {
        result += count[index];
      }
    }

    return result;
  }
}
