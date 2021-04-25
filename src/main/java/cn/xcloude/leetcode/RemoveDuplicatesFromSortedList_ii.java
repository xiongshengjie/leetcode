package cn.xcloude.leetcode;

public class RemoveDuplicatesFromSortedList_ii {
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode newHead = new ListNode(head.val - 1), temp = head, lastNode = newHead;
    newHead.next = head;
    while (temp != null && temp.next != null) {

      if (temp.val != temp.next.val) {
        lastNode = temp;
      } else {
        while (temp.next != null && temp.val == temp.next.val) {
          temp = temp.next;
          lastNode.next = temp.next;
        }
      }
      temp = temp.next;

    }

    return newHead.next;
  }
}
