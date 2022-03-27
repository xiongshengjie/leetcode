package cn.xcloude.leetcode;

/**
 * leetcode 26
 * 删除有序数组中的重复项
 */
public class RemoveDuplicatesFromSortedArray {
  public int removeDuplicates(int[] nums) {
    if (nums == null || nums.length <= 0) {
      return 0;
    }

    int fast = 0, slow = 0;
    while (fast < nums.length) {
      if (nums[fast] != nums[slow]) {
        nums[++slow] = nums[fast];
      }
      ++fast;
    }

    return slow + 1;
  }
}
