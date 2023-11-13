package cn.xcloude.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 437
 * nowcoder JZ84
 * 路径总和 III
 */
public class PathSum_iii {
  public int pathSum(TreeNode root, int targetSum) {
    // return traversal(root, targetSum);
    return pathSumPrefixSum(root, targetSum);
  }

  // 前缀和
  private int pathSumPrefixSum(TreeNode root, int targetSum) {
    Map<Long, Integer> prefixSum2Count = new HashMap<>();
    prefixSum2Count.put(0L, 1);

    return traversal(root, prefixSum2Count, 0L, targetSum);
  }

  private int traversal(TreeNode node, Map<Long, Integer> prefixSum2Count,
      Long prefixSum, int targetSum) {
    if (node == null) {
      return 0;
    }

    prefixSum += node.val;
    int result = prefixSum2Count.getOrDefault(prefixSum - targetSum, 0);
    Integer count = prefixSum2Count.getOrDefault(prefixSum, 0);
    prefixSum2Count.put(prefixSum, count + 1);
    result = result + traversal(node.left, prefixSum2Count, prefixSum, targetSum)
        + traversal(node.right, prefixSum2Count, prefixSum, targetSum);
    prefixSum2Count.put(prefixSum, count);
    return result;
  }

  // 递归遍历
  private int traversal(TreeNode node, int targetSum) {
    if (node == null) {
      return 0;
    }

    return pathSum0(node, targetSum)
        + traversal(node.left, targetSum) + traversal(node.right, targetSum);
  }

  private int pathSum0(TreeNode node, int targetSum) {
    if (node == null) {
      return 0;
    }

    int result = pathSum0(node.left, targetSum - node.val)
        + pathSum0(node.right, targetSum - node.val);
    return node.val == targetSum ? result + 1 : result;
  }
}
