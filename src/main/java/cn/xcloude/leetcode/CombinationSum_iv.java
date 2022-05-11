package cn.xcloude.leetcode;

import java.util.Arrays;

/**
 * leetcode 377
 * 组合总和 Ⅳ
 */
public class CombinationSum_iv {
  public int combinationSum4(int[] nums, int target) {
    // return combinationSum40(nums, target);
    return combinationSum41(nums, target);
  }

  // 动态规划
  private int combinationSum41(int[] nums, int target) {
    int[] dp = new int[target + 1];
    dp[0] = 1;

    for (int currentTarget = 1; currentTarget <= target; ++currentTarget) {
      for (int num : nums) {
        if (currentTarget >= num) {
          dp[currentTarget] += dp[currentTarget - num];
        }
      }
    }

    return dp[target];
  }

  // 带备忘录的递归
  private int combinationSum40(int[] nums, int target) {
    int[][] memorandum = new int[nums.length][target];
    for (int[] ints : memorandum) {
      Arrays.fill(ints, -1);
    }
    return combinationSum4(nums, target, 0, 0, memorandum);
  }

  private int combinationSum4(int[] nums, int target, int current, int index, int[][] memorandum) {
    if (current == target) {
      return 1;
    }

    if (current > target) {
      return 0;
    }

    if (memorandum[index][current] != -1) {
      return memorandum[index][current];
    }

    int result = 0;
    for (int currentIndex = 0; currentIndex < nums.length; ++currentIndex) {
      result += combinationSum4(nums, target,
          current + nums[currentIndex], currentIndex, memorandum);
    }

    memorandum[index][current] = result;
    return result;
  }
}
