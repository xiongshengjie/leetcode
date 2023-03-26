package cn.xcloude.leetcode;

/**
 * leetcode 53
 * 最大子数组和
 */
public class MaximumSubarray {
  public int maxSubArray(int[] nums) {
    int max = nums[0], sum = 0;
    for (int num : nums) {
      sum += num;
      if (max < sum) {
        max = sum;
      }
      if (sum < 0) {
        sum = 0;
      }
    }
    return max;
  }
}
