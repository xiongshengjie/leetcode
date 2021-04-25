package cn.xcloude.leetcode;

/**
 * LC43
 * 从中序和后序遍历构造二叉树
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
  }

  private TreeNode buildTree(int[] inorder, int[] postorder, int inHead, int inTail, int postHead,
      int postTail) {
    if (inHead > inTail) {
      return null;
    }

    int currentVal = postorder[postTail];
    int inRoot = 0;
    for (int i = inHead; i <= inTail; i++) {
      if (currentVal == inorder[i]) {
        inRoot = i;
        break;
      }
    }

    TreeNode current = new TreeNode(currentVal);
    current.left = buildTree(inorder, postorder, inHead, inRoot - 1, postHead,
        postHead + (inRoot - inHead) - 1);
    current.right = buildTree(inorder, postorder, inRoot + 1, inTail,
        postHead + (inRoot - inHead), postTail - 1);
    return current;
  }

  public static void main(String[] args) {
    int[] inorder = new int[] {9, 3, 15, 20, 7};
    int[] postorder = new int[] {9, 15, 7, 20, 3};
    new ConstructBinaryTreeFromInorderAndPostorderTraversal().buildTree(inorder, postorder);
  }
}
