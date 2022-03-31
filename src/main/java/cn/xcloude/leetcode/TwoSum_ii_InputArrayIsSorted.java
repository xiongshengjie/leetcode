package cn.xcloude.leetcode;

/**
 * leetcode 167
 * 两数之和 II - 输入有序数组
 */
public class TwoSum_ii_InputArrayIsSorted {
  public int[] twoSum(int[] numbers, int target) {
    if (numbers == null || numbers.length <= 0) {
      return null;
    }

    int left = 0, right = numbers.length - 1;
    while (left < right) {
      int sum = numbers[left] + numbers[right];
      if (sum == target) {
        return new int[] {left + 1, right + 1};
      } else if (sum < target) {
        ++left;
      } else {
        --right;
      }
    }

    return null;
  }
}
