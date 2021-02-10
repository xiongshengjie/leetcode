package cn.xcloude.leetcode.BinaryTreeFromRootToLeafSumEqualsGivenNum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }

  @Override
  public String toString() {
    return String.valueOf(val);
  }
}

/**
 * LC37
 * 二叉树根节点到叶子节点和为指定值的路径
 */
public class BinaryTreeFromRootToLeafSumEqualsGivenNum {
  public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
    if (root == null) {
      return new ArrayList<>();
    }

    ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();
    pathSum(paths, path, root, 0, sum);
    return paths;
  }

  // 递归
  private void pathSum(ArrayList<ArrayList<Integer>> paths, ArrayList<Integer> path, TreeNode root,
      int sum, int result) {
    sum += root.val;
    path.add(root.val);

    if (root.left != null) {
      pathSum(paths, path, root.left, sum, result);
    }

    if (root.right != null) {
      pathSum(paths, path, root.right, sum, result);
    }

    if (root.left == null && root.right == null && sum == result) {
      paths.add((ArrayList<Integer>) path.clone());
    }

    path.remove(path.size() - 1);
  }

  // 迭代
  private void pathSumIteratively(ArrayList<ArrayList<Integer>> paths, ArrayList<Integer> path,
      TreeNode root, int sum, int result) {
    Stack<TreeNode> stack = new Stack<>();
    Map<TreeNode, Integer> node2Sum = new HashMap<>();
    Map<TreeNode, ArrayList<Integer>> node2Path = new HashMap<>();
    push(path, sum, stack, node2Sum, node2Path, root);

    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      path = node2Path.get(node);
      sum = node2Sum.get(node);

      if (node.right == null && node.left == null && sum == result) {
        paths.add(path);
      }

      if (node.right != null) {
        push(path, sum, stack, node2Sum, node2Path, node.right);
      }

      if (node.left != null) {
        push(path, sum, stack, node2Sum, node2Path, node.left);
      }
    }
  }

  private void push(ArrayList<Integer> path, int sum, Stack<TreeNode> stack,
      Map<TreeNode, Integer> node2Sum, Map<TreeNode,
      ArrayList<Integer>> node2Path, TreeNode node) {
    stack.push(node);
    node2Sum.put(node, sum + node.val);
    ArrayList<Integer> nextPath = new ArrayList<>(path);
    nextPath.add(node.val);
    node2Path.put(node, nextPath);
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1, new TreeNode(2, null, null), null);
    ArrayList<ArrayList<Integer>> paths =
        new BinaryTreeFromRootToLeafSumEqualsGivenNum().pathSum(root, 1);
    System.out.println(paths);
  }
}
