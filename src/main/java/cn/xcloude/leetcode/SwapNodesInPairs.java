package cn.xcloude.leetcode;

public class SwapNodesInPairs {
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode temp = head, newHead = new ListNode(0), pre = newHead;
    newHead.next = head;
    int len = 0;
    while (temp != null) {
      len++;
      temp = temp.next;
    }
    for (int i = 0; i < len / 2; i++) {
      ListNode first = pre.next, last = pre.next.next;
      pre.next = last;
      first.next = last.next;
      last.next = first;
      pre = first;
    }

    return newHead.next;
  }
}
