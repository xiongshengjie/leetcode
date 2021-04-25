package cn.xcloude.leetcode;

public class RemoveNthNodeFromEndOfList {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) {
      return null;
    }
    ListNode pre = new ListNode(0), newHead = pre, fast = head, slow = head;
    pre.next = head;
    for (int i = 0; i < n; i++) {
      fast = fast.next;
    }
    while (fast != null) {
      pre = pre.next;
      fast = fast.next;
      slow = slow.next;
    }
    pre.next = slow.next;
    return newHead.next;
  }
}
