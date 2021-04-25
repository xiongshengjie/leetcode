package cn.xcloude.leetcode;

/**
 * LC44
 * 从前序和中序遍历构造二叉树
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
  }

  private TreeNode buildTree(int[] preorder, int[] inorder, int preHead, int preTail, int inHead,
      int inTail) {
    if (preHead > preTail) {
      return null;
    }

    int currentNodeVal = preorder[preHead];
    int currentNodeIndexInInorder = 0;
    for (int i = inHead; i <= inTail; i++) {
      if (inorder[i] == currentNodeVal) {
        currentNodeIndexInInorder = i;
        break;
      }
    }

    TreeNode currentNode = new TreeNode(currentNodeVal);
    currentNode.left = buildTree(preorder, inorder, preHead + 1,
        preHead + (currentNodeIndexInInorder - inHead), inHead,
        currentNodeIndexInInorder - 1);
    currentNode.right = buildTree(preorder, inorder,
        preHead + (currentNodeIndexInInorder - inHead) + 1, preTail,
        currentNodeIndexInInorder + 1, inTail);
    return currentNode;
  }

  public static void main(String[] args) {
    int preorder[] = new int[] {3, 9, 20, 15, 7};
    int inorder[] = new int[] {9, 3, 15, 20, 7};
    new ConstructBinaryTreeFromPreorderAndInorderTraversal().buildTree(preorder, inorder);
  }
}
