package cn.xcloude.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointerInEachNode_ii {
  // 空间复杂度为常量
  public void connect(TreeLinkNode root) {
    if (root == null || (root.left == null && root.right == null)) {
      return;
    }

    TreeLinkNode levelStart = root;
    while (levelStart != null) {
      TreeLinkNode cursor = levelStart;
      levelStart = null;

      // 寻找下一层头节点
      while (cursor != null) {
        if (cursor.left != null) {
          levelStart = cursor.left;
          break;
        } else if (cursor.right != null) {
          levelStart = cursor.right;
          break;
        } else {
          cursor = cursor.next;
        }
      }

      // 赋值 next 指针
      TreeLinkNode temp = levelStart;
      while (cursor != null) {
        if (cursor.left != null && levelStart != cursor.left) {
          temp.next = cursor.left;
          temp = temp.next;
        }

        if (cursor.right != null && levelStart != cursor.right) {
          temp.next = cursor.right;
          temp = temp.next;
        }

        cursor = cursor.next;
      }
    }
  }

  // 层次遍历
  public void connectV2(TreeLinkNode root) {
    if (root == null || (root.left == null && root.right == null)) {
      return;
    }

    Queue<TreeLinkNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size > 0) {
        TreeLinkNode node = queue.poll();
        if (node == null) {
          break;
        }

        if (size > 1) {
          node.next = queue.peek();
        }

        if (node.left != null) {
          queue.offer(node.left);
        }

        if (node.right != null) {
          queue.offer(node.right);
        }
        --size;
      }
    }
  }
}
