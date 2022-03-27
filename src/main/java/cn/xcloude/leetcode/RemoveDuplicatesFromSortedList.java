package cn.xcloude.leetcode;

/**
 * leetcode 83
 * 删除排序链表中的重复元素
 */
public class RemoveDuplicatesFromSortedList {
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode fast = head, slow = head;
    while (fast != null) {
      if (slow.val != fast.val) {
        slow.next = fast;
        slow = slow.next;
      }
      fast = fast.next;
    }
    slow.next = null;

    return head;
  }
}
