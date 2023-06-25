package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 剑指 Offer 36
 * nowcoder JZ36
 * 二叉搜索树与双向链表
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
  private TreeNode head = new TreeNode();
  private TreeNode last = head;

  public TreeNode treeToDoublyList(TreeNode root) {
    if (root == null) {
      return null;
    }

    traverse(root);
    head = head.right;
    head.left = last;
    last.right = head;
    return head;

    // return traverseIteratively(root);
  }

  // 递归
  private void traverse(TreeNode node) {
    if (node == null) {
      return;
    }

    traverse(node.left);

    last.right = node;
    node.left = last;
    last = node;

    traverse(node.right);
  }

  // 迭代
  private TreeNode traverseIteratively(TreeNode root) {
    Deque<TreeNode> stack = new LinkedList<>();
    TreeNode node = root;
    TreeNode head = new TreeNode();
    TreeNode last = head;
    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      node = stack.pop();
      TreeNode right = node.right;

      last.right = node;
      node.left = last;
      last = node;

      node = right;
    }

    head = head.right;
    head.left = last;
    last.right = head;
    return head;
  }
}
