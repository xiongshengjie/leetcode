package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 剑指 Offer 34
 * nowcoder JZ34
 * 二叉树中和为某一值的路径(二)
 */
public class FindBinaryTreeFromRootToLeafSumEqualsGivenNumPath {
  private final List<List<Integer>> result = new LinkedList<>();

  public List<List<Integer>> pathSum(TreeNode root, int target) {
    traverse(root, target, 0, new LinkedList<>());
    return result;
  }

  private void traverse(TreeNode node, int target, int current, Deque<Integer> path) {
    if (node == null) {
      return;
    }

    current += node.val;
    path.offerLast(node.val);
    if (node.left == null && node.right == null && target == current) {
      result.add(new ArrayList<>(path));
    }

    traverse(node.left, target, current, path);
    traverse(node.right, target, current, path);
    path.pollLast();
  }
}
