package cn.xcloude.leetcode;

public class RotateList {
  public ListNode rotateRight(ListNode head, int n) {
    if (head == null) {
      return null;
    }
    ListNode temp = head;
    int len = 1;
    while (temp.next != null) {
      len++;
      temp = temp.next;
    }
    n = len - n % len - 1;
    temp.next = head;
    for (int i = 0; i < n; i++) {
      head = head.next;
    }
    ListNode result = head.next;
    head.next = null;
    return result;
  }
}
