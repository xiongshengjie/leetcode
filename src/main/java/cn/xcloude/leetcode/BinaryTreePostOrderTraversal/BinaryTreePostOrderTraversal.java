package cn.xcloude.leetcode.BinaryTreePostOrderTraversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class BinaryTreePostOrderTraversal {
  public ArrayList<Integer> postOrderTraversal(TreeNode root) {
    ArrayList<Integer> result = new ArrayList<>();
    postOrderTraversalIteratively1(root, result);
    return result;
  }

  // 递归实现
  private void postOrderTraversal(TreeNode node, ArrayList<Integer> result) {
    if (node == null) {
      return;
    }

    postOrderTraversal(node.left, result);
    postOrderTraversal(node.right, result);
    result.add(node.val);
  }

  // 迭代实现, 根右左访问, 然后倒序
  private void postOrderTraversalIteratively(TreeNode root, ArrayList<Integer> result) {
    if (root == null) {
      return;
    }

    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      result.add(node.val);

      if (node.left != null) {
        stack.push(node.left);
      }

      if (node.right != null) {
        stack.push(node.right);
      }
    }

    Collections.reverse(result);
  }

  // 迭代实现
  private void postOrderTraversalIteratively1(TreeNode root, ArrayList<Integer> result) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode pre = null;
    TreeNode node = root;
    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      node = stack.peek();
      if (node.right == null || pre == node.right) {
        result.add(node.val);
        stack.pop();
        pre = node;
        node = null;
      } else {
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
    System.out.println(new BinaryTreePostOrderTraversal().postOrderTraversal(node1));
  }
}
