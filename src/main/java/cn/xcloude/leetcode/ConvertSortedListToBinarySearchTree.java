package cn.xcloude.leetcode;

public class ConvertSortedListToBinarySearchTree {
  public TreeNode sortedListToBST(ListNode head) {
    return toBST(head, null);
  }

  public TreeNode toBST(ListNode head, ListNode tail) {
    if (head == tail) {
      return null;
    }
    ListNode fast = head, slow = head;
    while (fast != tail && fast.next != tail) {
      fast = fast.next.next;
      slow = slow.next;
    }
    TreeNode root = new TreeNode(slow.val);
    root.left = toBST(head, slow);
    root.right = toBST(slow.next, tail);
    return root;
  }
}
