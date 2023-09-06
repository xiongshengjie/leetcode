package cn.xcloude.leetcode;

/**
 * leetcode 328
 * 奇偶链表
 */
public class OddEvenLinkedList {
  public ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode evenHead = head.next;
    ListNode oddNode = head;
    ListNode evenNode = head.next;
    ListNode node = head.next.next;
    while (node != null) {
      oddNode.next = node;
      oddNode = oddNode.next;
      evenNode.next = node.next;
      evenNode = evenNode.next;
      node = node.next != null ? node.next.next : null;
    }

    oddNode.next = evenHead;
    return head;
  }
}
