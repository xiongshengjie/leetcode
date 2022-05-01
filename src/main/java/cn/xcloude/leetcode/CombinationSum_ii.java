package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 40
 * 组合总和 II
 */
public class CombinationSum_ii {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> result = new ArrayList<>();
    combinationSum2(candidates, target, true, 0, new ArrayList<>(candidates.length), 0, result);
    return result;
  }

  private void combinationSum2(int[] candidates, int target, boolean choosePrevious, int index,
      List<Integer> current, int sum, List<List<Integer>> result) {
    if (sum > target) {
      return;
    }

    if (sum == target) {
      result.add(new ArrayList<>(current));
      return;
    }

    if (index >= candidates.length) {
      return;
    }

    if (choosePrevious || candidates[index] != candidates[index - 1]) {
      current.add(candidates[index]);
      combinationSum2(candidates, target, true, index + 1,
          current, sum + candidates[index], result);
      current.remove(current.size() - 1);
    }
    combinationSum2(candidates, target, false, index + 1, current, sum, result);
  }
}
