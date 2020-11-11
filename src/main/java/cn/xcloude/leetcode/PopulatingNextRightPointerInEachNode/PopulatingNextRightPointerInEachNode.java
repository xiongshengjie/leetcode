package cn.xcloude.leetcode.PopulatingNextRightPointerInEachNode;

import java.util.LinkedList;
import java.util.Queue;

class TreeLinkNode {
  int val;
  TreeLinkNode left, right, next;

  TreeLinkNode(int x) {
    val = x;
  }
}

public class PopulatingNextRightPointerInEachNode {
  // 空间复杂度为常量
  public void connect(TreeLinkNode root) {
    if (root == null || (root.right == null && root.left == null)) {
      return;
    }

    TreeLinkNode levelStart = root;
    while (levelStart != null) {
      TreeLinkNode temp = levelStart;
      while (temp != null) {
        if (temp.left != null) {
          temp.left.next = temp.right;
        }

        if (temp.right != null && temp.next != null) {
          temp.right.next = temp.next.left;
        }

        temp = temp.next;
      }
      levelStart = levelStart.left;
    }
  }

  // 层次遍历
  public void connectV2(TreeLinkNode root) {
    if (root == null || (root.right == null && root.left == null)) {
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

  public static void main(String[] args) {
    TreeLinkNode root = new TreeLinkNode(0);
    root.left = new TreeLinkNode(1);
    root.right = new TreeLinkNode(2);
    root.left.left = new TreeLinkNode(3);
    root.left.right = new TreeLinkNode(4);
    root.right.left = new TreeLinkNode(5);
    root.right.right = new TreeLinkNode(6);

    new PopulatingNextRightPointerInEachNode().connect(root);
  }
}
