package cn.xcloude.leetcode;

/**
 * leetcode 92
 * 反转链表 II
 */
public class ReverseLinkedList_ii {
  public ListNode reverseBetween(ListNode head, int left, int right) {
    // return reverse(head, left, right);
    return reverseRecursion(head, left, right, 1);
  }

  private ListNode reverse(ListNode head, int left, int right) {
    int index = 1;
    ListNode reverseHead = null, dummy = new ListNode(0, head),
        leftLast = dummy, reverseTail = head;
    while (head != null && index <= right) {
      if (index < left) {
        leftLast = leftLast.next;
        head = head.next;
        reverseTail = head;
      } else if (index <= right) {
        ListNode next = head.next;
        head.next = reverseHead;
        reverseHead = head;
        head = next;
      }
      ++index;
    }

    leftLast.next = reverseHead;
    reverseTail.next = head;
    return dummy.next;
  }

  private ListNode reverseRecursion(ListNode node, int left, int right, int index) {
    if (index >= right || node == null || node.next == null) {
      return node;
    }

    if (index < left) {
      node.next = reverseRecursion(node.next, left, right, index + 1);
      return node;
    } else {
      ListNode head = reverseRecursion(node.next, left, right, index + 1);
      ListNode next = node.next.next;
      node.next.next = node;
      node.next = next;
      return head;
    }
  }
}
