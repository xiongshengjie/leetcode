package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 77
 * 组合
 */
public class Combinations {
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    combine(n, k, 1, new ArrayList<>(k), result);
    return result;
  }

  private void combine(int n, int k, int index, List<Integer> current, List<List<Integer>> result) {
    if (current.size() + (n - index + 1) < k) {
      return;
    }

    if (current.size() == k) {
      result.add(new ArrayList<>(current));
      return;
    }

    current.add(index);
    combine(n, k, index + 1, current, result);
    current.remove(current.size() - 1);
    combine(n, k, index + 1, current, result);
  }
}
