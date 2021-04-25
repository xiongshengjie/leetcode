package cn.xcloude.leetcode;

/**
 * LC41
 * 将升序数组转化为平衡二叉搜索树
 */
public class ConvertSortedArrayToBinarySearchTree {
  public TreeNode sortedArrayToBST(int[] num) {
    return sortedArrayToBST(num, 0, num.length - 1);
  }

  private TreeNode sortedArrayToBST(int[] num, int start, int end) {
    if (start > end) {
      return null;
    }

    int middle = (start + end + 1) >> 1;
    TreeNode node = new TreeNode(num[middle]);
    node.left = sortedArrayToBST(num, start, middle - 1);
    node.right = sortedArrayToBST(num, middle + 1, end);
    return node;
  }
}
