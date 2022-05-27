package cn.xcloude.leetcode;

/**
 * leetcode 剑指 offer 26
 * nowcoder JZ26
 * 树的子结构
 */
public class SubTree {
  public boolean isSubStructure(TreeNode root, TreeNode subTreeRoot) {
    if (subTreeRoot == null) {
      return false;
    }

    return traverse(root, subTreeRoot);
  }

  private boolean traverse(TreeNode node, TreeNode subTreeRoot) {
    if (node == null) {
      return false;
    }

    return isSubStructure0(node, subTreeRoot) || traverse(node.left, subTreeRoot)
        || traverse(node.right, subTreeRoot);
  }

  private boolean isSubStructure0(TreeNode node, TreeNode subTreeNode) {
    if (subTreeNode == null) {
      return true;
    }

    if (node == null || node.val != subTreeNode.val) {
      return false;
    }

    return isSubStructure0(node.left, subTreeNode.left)
        && isSubStructure0(node.right, subTreeNode.right);
  }
}
