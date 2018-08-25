package cn.xcloude.leetcode.MergeTwoSortedLists;

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class MergeTwoSortedLists {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    ListNode temp1 = l1, temp2 = l2, head = new ListNode(0), newHead = head;
    while (temp1 != null && temp2 != null) {
      if (temp1.val > temp2.val) {
        head.next = temp2;
        temp2 = temp2.next;
      } else {
        head.next = temp1;
        temp1 = temp1.next;
      }
      head = head.next;
    }
    if (temp1 == null) {
      head.next = temp2;
    }
    if (temp2 == null) {
      head.next = temp1;
    }
    return newHead.next;
  }
}

