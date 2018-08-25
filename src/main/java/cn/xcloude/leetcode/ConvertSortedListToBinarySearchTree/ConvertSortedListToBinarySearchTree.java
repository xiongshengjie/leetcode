package cn.xcloude.leetcode.ConvertSortedListToBinarySearchTree;

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

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

