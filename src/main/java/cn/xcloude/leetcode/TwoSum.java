package cn.xcloude.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 1
 * 两数之和
 */
public class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    if (nums == null || nums.length <= 0) {
      return null;
    }

    int length = nums.length;
    Map<Integer, Integer> num2Index = new HashMap<>((int) (length / 0.75));
    for (int index = 0; index < length; ++index) {
      Integer otherIndex = num2Index.get(target - nums[index]);
      if (otherIndex != null) {
        return new int[] {index, otherIndex};
      } else {
        num2Index.put(nums[index], index);
      }
    }

    return null;
  }
}
