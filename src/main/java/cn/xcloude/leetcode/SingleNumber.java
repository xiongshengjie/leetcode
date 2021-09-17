package cn.xcloude.leetcode;

/**
 * LC15
 * leetcode 136
 * 只出现一次的数字
 */
public class SingleNumber {
  public int singleNumber(int[] nums) {
    int result = 0;

    for (int num : nums) {
      result ^= num;
    }

    return result;
  }
}
