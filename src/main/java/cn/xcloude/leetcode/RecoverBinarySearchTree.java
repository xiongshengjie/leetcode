package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class RecoverBinarySearchTree {
  private TreeNode preNode, preWrong, lastWrong;

  public void recoverTree(TreeNode root) {
    // recoverTreeRecursion(root);
    // recoverTreeIteratively(root);
    recoverTreeMorris(root);
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

  // Morris 遍历实现
  private void recoverTreeMorris(TreeNode root) {
    if (root == null) {
      return;
    }

    // morris 遍历使用
    TreeNode temp, current = root;
    // 找错误节点使用
    TreeNode preNode = null, preWrong = null, lastWrong = null;
    while (current != null) {
      if (current.left == null) {
        if (preNode != null && preNode.val > current.val) {
          if (preWrong == null) {
            preWrong = preNode;
          }
          lastWrong = current;
        }
        preNode = current;

        current = current.right;
      } else {
        temp = current.left;
        while (temp.right != null && temp.right != current) {
          temp = temp.right;
        }

        if (temp.right == null) {
          temp.right = current;
          current = current.left;
        } else {
          if (preNode != null && preNode.val > current.val) {
            if (preWrong == null) {
              preWrong = preNode;
            }
            lastWrong = current;
          }
          preNode = current;

          temp.right = null;
          current = current.right;
        }
      }
    }

    swap(preWrong, lastWrong);
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    root.left = n3;
    n3.right = n2;
    new RecoverBinarySearchTree().recoverTree(root);
  }
}
