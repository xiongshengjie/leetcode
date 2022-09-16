package cn.xcloude.leetcode;

/**
 * leetcode 198
 * 打家劫舍
 *
 * @see HouseRobber_ii
 */
public class HouseRobber {
  public int rob(int[] nums) {
    if (nums == null || nums.length <= 0) {
      return 0;
    }

    if (nums.length == 1) {
      return nums[0];
    }

    int first = nums[0];
    int second = Math.max(nums[0], nums[1]);

    for (int index = 2; index < nums.length; ++index) {
      int temp = second;
      second = Math.max(first + nums[index], second);
      first = temp;
    }

    return second;
  }
}
