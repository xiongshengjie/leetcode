package cn.xcloude.leetcode.BinaryTreeLevelOrderTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;
}

/**
 * LC47
 * 求二叉树的层序遍历
 */
public class BinaryTreeLevelOrderTraversal {
  public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }

    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      ArrayList<Integer> levelOrder = new ArrayList<>(size);
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
        levelOrder.add(node.val);
      }
      result.add(levelOrder);
    }

    return result;
  }
}
