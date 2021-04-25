package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTreePreOrderTraversal {
  // 递归
  public ArrayList<Integer> preOrderTraversal(TreeNode root) {
    ArrayList<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    result.add(root.val);

    if (root.left != null) {
      result.addAll(preOrderTraversal(root.left));
    }

    if (root.right != null) {
      result.addAll(preOrderTraversal(root.right));
    }

    return result;
  }

  // 迭代
  public ArrayList<Integer> preOrderTraversalIteratively(TreeNode root) {
    if (root == null) {
      return new ArrayList<>(0);
    }

    ArrayList<Integer> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      result.add(node.val);

      if (node.right != null) {
        stack.push(node.right);
      }

      if (node.left != null) {
        stack.push(node.left);
      }
    }

    return result;
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
    System.out.println(new BinaryTreePreOrderTraversal().preOrderTraversalIteratively(node1));
  }
}
