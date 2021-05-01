package cn.xcloude.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC49
 * 判断二叉树是否相等
 */
public class SameTree {
  public boolean isSameTree(TreeNode p, TreeNode q) {
    return isSameTreeIteratively(p, q);
  }

  // 递归实现
  private boolean isSameTreeRecursion(TreeNode p, TreeNode q) {
    if (p == null) {
      return q == null;
    }

    if (q == null) {
      return false;
    }

    if (p.val != q.val) {
      return false;
    }

    return isSameTreeRecursion(p.left, q.left) && isSameTreeRecursion(p.right, q.right);
  }

  // 迭代实现
  private boolean isSameTreeIteratively(TreeNode p, TreeNode q) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(p);
    queue.offer(q);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode pNode = queue.poll();
        TreeNode qNode = queue.poll();

        if (pNode == null && qNode == null) {
          continue;
        }

        if (pNode == null || qNode == null) {
          return false;
        }

        if (pNode.val != qNode.val) {
          return false;
        }

        queue.offer(pNode.left);
        queue.offer(qNode.left);
        queue.offer(pNode.right);
        queue.offer(qNode.right);
      }
    }
    return true;
  }
}
