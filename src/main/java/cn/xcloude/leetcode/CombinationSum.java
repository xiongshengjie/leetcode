package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 39
 * 组合总和
 */
public class CombinationSum {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    combinationSum(candidates, target, 0, 0, new ArrayList<>(), result);
    return result;
  }

  private void combinationSum(int[] candidates, int target, int index, int sum,
      List<Integer> current, List<List<Integer>> result) {
    if (sum == target) {
      result.add(new ArrayList<>(current));
      return;
    }

    if (sum > target || index >= candidates.length) {
      return;
    }

    current.add(candidates[index]);
    combinationSum(candidates, target, index, sum + candidates[index], current, result);
    current.remove(current.size() - 1);
    combinationSum(candidates, target, index + 1, sum, current, result);
  }
}
