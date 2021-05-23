package cn.xcloude.leetcode;

/**
 * leetcode 面试题 17.04
 */
public class MissingNumber {
  public int missingNumber(int[] nums) {
    int length = nums.length;
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }

    return length * (length + 1) / 2 - sum;
  }
}
