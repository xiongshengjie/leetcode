package cn.xcloude.leetcode;

import java.util.Arrays;

/**
 * leetcode 213
 * 打家劫舍 II
 */
public class HouseRobber_ii {
  public int rob(int[] nums) {
    if (nums == null || nums.length <= 0) {
      return 0;
    }

    if (nums.length == 1) {
      return nums[0];
    }

    if (nums.length == 2) {
      return Math.max(nums[0], nums[1]);
    }

    /*
    int[] memorandum = new int[nums.length];
    return Math.max(rob(nums, nums.length - 1, 2, initMemorandum(memorandum)) + nums[0],
        rob(nums, nums.length, 1, initMemorandum(memorandum)));
    */

    // return Math.max(rob(nums, 0, nums.length - 1), rob(nums, 1, nums.length));
    return Math.max(rob0(nums, 0, nums.length - 1), rob0(nums, 1, nums.length));
  }

  // 动态规划, 由于 dp 数组当前值只和当前位置的前两个元素有关, 可以优化为两个变量
  private int rob0(int[] nums, int start, int end) {
    int first = nums[start];
    int second = Math.max(nums[start], nums[start + 1]);

    for (int index = start + 2; index < end; ++index) {
      int temp = second;
      second = Math.max(first + nums[index], second);
      first = temp;
    }

    return second;
  }

  // 动态规划
  private int rob(int[] nums, int start, int end) {
    // dp[count] 代表前 count 个房子中能偷盗的最大金额
    int[] dp = new int[nums.length];
    dp[start] = nums[start];
    dp[start + 1] = Math.max(nums[start], nums[start + 1]);

    for (int index = start + 2; index < end; ++index) {
      dp[index] = Math.max(dp[index - 2] + nums[index], dp[index - 1]);
    }

    return dp[end - 1];
  }

  // 带备忘录的递归
  private int rob(int[] nums, int maxIndex, int index, int[] memorandum) {
    if (index >= maxIndex) {
      return 0;
    }

    if (memorandum[index] != -1) {
      return memorandum[index];
    }

    memorandum[index] = Math.max(rob(nums, maxIndex, index + 1, memorandum),
        nums[index] + rob(nums, maxIndex, index + 2, memorandum));
    return memorandum[index];
  }

  private int[] initMemorandum(int[] memorandum) {
    Arrays.fill(memorandum, -1);
    return memorandum;
  }
}
