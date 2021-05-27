package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class RecoverBinarySearchTree {
  private TreeNode preNode, preWrong, lastWrong;

  public void recoverTree(TreeNode root) {
    // recoverTreeRecursion(root);
    recoverTreeIteratively(root);
  }

  // 递归实现
  private void recoverTreeRecursion(TreeNode root) {
    inorder(root);
    swap(preWrong, lastWrong);
  }

  private void inorder(TreeNode node) {
    if (node == null) {
      return;
    }

    inorder(node.left);
    if (preNode != null && preNode.val > node.val) {
      if (preWrong == null) {
        preWrong = preNode;
      }

      if (lastWrong == null) {
        lastWrong = node;
      } else {
        lastWrong = node;
        return;
      }
    }
    preNode = node;

    inorder(node.right);
  }

  private void swap(TreeNode preWrong, TreeNode lastWrong) {
    int val = preWrong.val;
    preWrong.val = lastWrong.val;
    lastWrong.val = val;
  }

  // 迭代实现
  private void recoverTreeIteratively(TreeNode root) {
    if (root == null) {
      return;
    }

    Deque<TreeNode> stack = new LinkedList<>();
    TreeNode node = root, preNode = null, preWrong = null, lastWrong = null;
    while (node != null || !stack.isEmpty()) {
      if (node != null) {
        stack.push(node);
        node = node.left;
      } else {
        node = stack.pop();
        if (preNode != null && preNode.val > node.val) {
          if (preWrong == null) {
            preWrong = preNode;
          }

          if (lastWrong == null) {
            lastWrong = node;
          } else {
            lastWrong = node;
            break;
          }
        }
        preNode = node;
        node = node.right;
      }
    }

    swap(preWrong, lastWrong);
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    TreeNode left = new TreeNode(3);
    TreeNode right = new TreeNode(2);

    root.left = left;
    left.right = right;
    new RecoverBinarySearchTree().recoverTree(root);
  }
}
