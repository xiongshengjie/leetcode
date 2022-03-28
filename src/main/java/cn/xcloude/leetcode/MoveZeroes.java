package cn.xcloude.leetcode;

/**
 * leetcode 283
 * 移动零
 */
public class MoveZeroes {
  public void moveZeroes(int[] nums) {
    if (nums == null || nums.length <= 0) {
      return;
    }

    int fast = 0, slow = 0;
    while (fast < nums.length) {
      if (nums[fast] != 0) {
        int temp = nums[fast];
        nums[fast] = nums[slow];
        nums[slow++] = temp;
      }
      ++fast;
    }
  }
}
