package cn.xcloude.leetcode;

/**
 * LC14
 * leetcode 137
 * 只出现一次的数字 II
 */
public class SingleNumber_ii {
  public int singleNumber(int[] nums) {
    int a = 0, b = 0;
    for (int num : nums) {
      b = ~a & (b ^ num);
      a = ~b & (a ^ num);
    }
    return b;
  }
}
