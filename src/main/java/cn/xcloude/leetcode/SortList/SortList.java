package cn.xcloude.leetcode.SortList;

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class SortList {

  // 分治法+递归
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode mid = getMiddle(head);
    ListNode right = sortList(mid.next);
    mid.next = null;
    ListNode left = sortList(head);
    return mergeTwoLists(left, right);
  }

  // 快慢指针找出中点
  public ListNode getMiddle(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode slow = head, fast = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;

  }

  // 链表合并
  public ListNode mergeTwoLists(ListNode left, ListNode right) {
    ListNode result = new ListNode(0), cur = result;

    while (left != null && right != null) {
      if (left.val < right.val) {
        cur.next = left;
        left = left.next;
      } else {
        cur.next = right;
        right = right.next;
      }
      cur = cur.next;
    }
    cur.next = left == null ? right : left;
    return result.next;
  }
}
