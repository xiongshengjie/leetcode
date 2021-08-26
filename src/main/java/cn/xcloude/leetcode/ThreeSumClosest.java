package cn.xcloude.leetcode;

import java.util.Arrays;

/**
 * LC133
 * leetcode 16
 * 最接近的三数之和
 */
public class ThreeSumClosest {
  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);

    int maxIndex, minIndex, sum, result = 10000000;
    for (int index = 0; index < nums.length; ++index) {
      if (index > 0 && nums[index] == nums[index - 1]) {
        continue;
      }

      minIndex = index + 1;
      maxIndex = nums.length - 1;

      while (minIndex < maxIndex) {
        sum = nums[index] + nums[minIndex] + nums[maxIndex];
        if (sum == target) {
          return target;
        } else if (sum > target) {
          --maxIndex;
        } else {
          ++minIndex;
        }

        if (Math.abs(sum - target) < Math.abs(result - target)) {
          result = sum;
        }
      }
    }

    return result;
  }
}
