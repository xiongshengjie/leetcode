package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 90
 * 子集 II
 */
public class Subsets_ii {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    subsetsWithDup(nums, true, 0, new ArrayList<>(nums.length), result);
    return result;
  }

  private void subsetsWithDup(int[] nums, boolean choosePrevious, int index,
      List<Integer> current, List<List<Integer>> result) {
    if (index >= nums.length) {
      result.add(new ArrayList<>(current));
      return;
    }

    if (choosePrevious || nums[index] != nums[index - 1]) {
      current.add(nums[index]);
      subsetsWithDup(nums, true, index + 1, current, result);
      current.remove(current.size() - 1);
    }
    subsetsWithDup(nums, false, index + 1, current, result);
  }
}
