package cn.xcloude.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * LC53
 * 不同的二叉搜索树 ii
 */
public class UniqueBinarySearchTrees_ii {
  public List<TreeNode> generateTrees(int n) {
    return generateTreesRecursion(1, n);
  }

  private List<TreeNode> generateTreesRecursion(int head, int tail) {
    if (tail < head) {
      return Collections.singletonList(null);
    }

    if (head == tail) {
      return Collections.singletonList(new TreeNode(head));
    }

    List<TreeNode> temp = new LinkedList<>();
    for (int i = head; i <= tail; i++) {
      List<TreeNode> left = generateTreesRecursion(head, i - 1);
      List<TreeNode> right = generateTreesRecursion(i + 1, tail);

      for (TreeNode leftNode : left) {
        for (TreeNode rightNode : right) {
          TreeNode node = new TreeNode(i);
          node.left = leftNode;
          node.right = rightNode;
          temp.add(node);
        }
      }
    }

    return temp;
  }

  public static void main(String[] args) {
    List<TreeNode> roots = new UniqueBinarySearchTrees_ii().generateTrees(3);
    for (TreeNode root : roots) {
      System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
    }
  }
}
