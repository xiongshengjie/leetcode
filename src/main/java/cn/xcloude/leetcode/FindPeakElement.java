package cn.xcloude.leetcode;

/**
 * leetcode 162
 * 寻找峰值
 */
public class FindPeakElement {
  public int findPeakElement(int[] nums) {
    if (nums.length == 1) {
      return 0;
    }

    if (nums[0] > nums[1]) {
      return 0;
    }

    if (nums[nums.length - 1] > nums[nums.length - 2]) {
      return nums.length - 1;
    }

    int start = 1, end = nums.length - 2, middle;
    while (start <= end) {
      middle = start + ((end - start) >> 1);
      if (nums[middle] > nums[middle + 1] && nums[middle] > nums[middle - 1]) {
        return middle;
      }

      if (nums[middle] < nums[middle + 1]) {
        start = middle + 1;
      } else if (nums[middle] < nums[middle - 1]) {
        end = middle - 1;
      }
    }

    return -1;
  }
}
