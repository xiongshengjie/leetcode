package cn.xcloude.leetcode;

/**
 * leetcode 414
 * 第三大的数
 */
public class ThirdMaximumNumber {
  public int thirdMax(int[] nums) {
    Integer max = null, secondMax = null, thirdMax = null;
    for (int num : nums) {
      if (max == null || num > max) {
        thirdMax = secondMax;
        secondMax = max;
        max = num;
      } else if (num < max && (secondMax == null || num > secondMax)) {
        thirdMax = secondMax;
        secondMax = num;
      } else if (secondMax != null && num < secondMax
          && (thirdMax == null || num > thirdMax)) {
        thirdMax = num;
      }
    }

    return thirdMax == null ? max : thirdMax;
  }
}
