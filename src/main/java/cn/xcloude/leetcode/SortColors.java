package cn.xcloude.leetcode;

import java.util.Arrays;

/**
 * leetcode 75
 * 荷兰国旗问题, 颜色分类
 */
public class SortColors {
  public void sortColors(int[] nums) {
    int lastLessIndex = -1, firstGreaterIndex = nums.length, target = 1, i = 0;
    while (i < firstGreaterIndex) {
      if (nums[i] < target) {
        ++lastLessIndex;
        swap(nums, lastLessIndex, i);
        ++i;
      } else if (nums[i] > target) {
        --firstGreaterIndex;
        swap(nums, firstGreaterIndex, i);
      } else {
        ++i;
      }
    }
  }

  private void swap(int[] nums, int index, int otherIndex) {
    int temp = nums[index];
    nums[index] = nums[otherIndex];
    nums[otherIndex] = temp;
  }

  public static void main(String[] args) {
    int[] nums = new int[] {2, 2, 1, 0, 0, 1, 2, 1, 0, 2, 1};
    new SortColors().sortColors(nums);
    System.out.println(Arrays.toString(nums));
  }
}
