package cn.xcloude.leetcode;

/**
 * leetcode 206
 * 反转链表
 */

public class ReverseLinkedList {
  public ListNode reverseList(ListNode head) {
    return reverse(head);
    // return reverseRecursion(head);
  }

  private ListNode reverse(ListNode head) {
    ListNode reverseHead = null;
    while (head != null) {
      ListNode next = head.next;
      head.next = reverseHead;
      reverseHead = head;
      head = next;
    }

    return reverseHead;
  }

  /**
   * 反转链表递归实现
   *
   * @param node 反转从 node 到结尾的链表
   * @return 反转后的链表的头结点
   */
  private ListNode reverseRecursion(ListNode node) {
    if (node == null || node.next == null) {
      return node;
    }

    ListNode head = reverseRecursion(node.next);
    node.next.next = node;
    node.next = null;
    return head;
  }
}
