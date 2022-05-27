package cn.xcloude.leetcode;

/**
 * leetcode 剑指 offer 27
 * JZ27
 * 二叉树的镜像
 */
public class MirrorTree {
  public TreeNode mirrorTree(TreeNode root) {
    mirror(root);
    return root;
  }

  private void mirror(TreeNode node) {
    if (node == null) {
      return;
    }

    TreeNode temp = node.left;
    node.left = node.right;
    node.right = temp;
    mirror(node.left);
    mirror(node.right);
  }
}
