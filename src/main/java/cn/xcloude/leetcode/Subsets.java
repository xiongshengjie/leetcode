package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 78
 * 子集
 */
public class Subsets {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    subsets(nums, 0, new ArrayList<>(nums.length), result);
    return result;
  }

  private void subsets(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
    if (index >= nums.length) {
      result.add(new ArrayList<>(current));
      return;
    }

    current.add(nums[index]);
    subsets(nums, index + 1, current, result);
    current.remove(current.size() - 1);
    subsets(nums, index + 1, current, result);
  }
}
