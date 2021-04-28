package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LC46
 * 二叉树的之字形层序遍历
 */
public class BinaryTreeZigzagLevelOrderTraversal {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return Collections.emptyList();
    }

    List<List<Integer>> result = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    boolean rightOrder = true;
    while (!queue.isEmpty()) {
      int size = queue.size();
      LinkedList<Integer> currentLevelOrder = new LinkedList<>();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();

        if (rightOrder) {
          currentLevelOrder.add(node.val);
        } else {
          currentLevelOrder.addFirst(node.val);
        }

        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      result.add(currentLevelOrder);
      rightOrder = !rightOrder;
    }

    return result;
  }
}
