package cn.xcloude.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC51
 * 判断二叉搜索树
 */
public class ValidateBinarySearchTree {
  private TreeNode preNode;

  public boolean isValidBST(TreeNode root) {
    // return isValidBSTRecursion(root);
    return isValidBSTIteratively(root);
  }

  // 递归实现
  private boolean isValidBSTRecursion(TreeNode node) {
    if (node == null) {
      return true;
    }

    if (!isValidBSTRecursion(node.left)) {
      return false;
    }

    if (preNode != null && preNode.val >= node.val) {
      return false;
    }
    preNode = node;

    return isValidBSTRecursion(node.right);
  }

  // 迭代实现
  private boolean isValidBSTIteratively(TreeNode root) {
    if (root == null) {
      return true;
    }

    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode preNode = null, node = root;
    while (!stack.isEmpty() || node != null) {
      if (node != null) {
        stack.push(node);
        node = node.left;
      } else {
        node = stack.pop();
        if (preNode != null && preNode.val >= node.val) {
          return false;
        }
        preNode = node;
        node = node.right;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(4);
    TreeNode n1 = new TreeNode(1);
    TreeNode n3 = new TreeNode(3);
    TreeNode n5 = new TreeNode(5);
    TreeNode n6 = new TreeNode(6);

    root.left = n1;
    root.right = n5;
    n5.left = n3;
    n5.right = n6;

    System.out.println(new ValidateBinarySearchTree().isValidBST(root));
  }
}
