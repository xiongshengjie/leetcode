package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * LC55
 * 二叉树的中序遍历
 */
public class BinaryTreeInorderTraversal {
  public ArrayList<Integer> inorderTraversal(TreeNode root) {
    ArrayList<Integer> result = new ArrayList<>();
    inorderTraversalIteratively(root, result);
    return result;
  }

  // 递归实现
  private void inorderTraversal(TreeNode node, ArrayList<Integer> result) {
    if (node == null) {
      return;
    }

    inorderTraversal(node.left, result);
    result.add(node.val);
    inorderTraversal(node.right, result);
  }

  // 迭代实现
  private void inorderTraversalIteratively(TreeNode root, ArrayList<Integer> result) {
    if (root == null) {
      return;
    }

    Stack<TreeNode> stack = new Stack<>();
    TreeNode node = root;
    while (node != null || !stack.isEmpty()) {
      if (node != null) {
        stack.push(node);
        node = node.left;
      } else {
        node = stack.pop();
        result.add(node.val);
        node = node.right;
      }
    }
  }

  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    TreeNode node7 = new TreeNode(7);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.left = node6;
    node3.right = node7;
    System.out.println(new BinaryTreeInorderTraversal().inorderTraversal(node1));
  }
}
