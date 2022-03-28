package cn.xcloude.leetcode;

/**
 * leetcode 27
 * 移除元素
 */
public class RemoveElement {
  public int removeElement(int[] nums, int val) {
    if (nums == null || nums.length <= 0) {
      return 0;
    }

    int right = nums.length - 1, left = 0;
    while (right >= left) {
      if (nums[left] == val) {
        while (right > left && nums[right] == val) {
          --right;
        }
        nums[left] = nums[right--];
      } else {
        ++left;
      }
    }

    return left;
  }
}
