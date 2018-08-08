package cn.xcloude.leetcode;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class ReorderList {
  public void reorderList(ListNode head) {
    if (head == null) {
      return;
    }
    ListNode first = head, last = head, mid = head;
    while (last.next != null && last.next.next != null) {
      mid = mid.next;
      last = last.next.next;
    }
    //mid后倒置
    ListNode rightHead = mid.next, temp, to = mid.next;
    mid.next = null;
    if (rightHead != null && rightHead.next != null) {
      temp = rightHead.next;
      while (temp != null) {
        rightHead.next = temp.next;
        temp.next = to;
        to = temp;
        temp = rightHead.next;
      }
    }
    //合并
    ListNode right, left;
    while (first != null && to != null) {
      right = first.next;
      left = to.next;
      first.next = to;
      to.next = right;
      first = right;
      to = left;
    }

  }
}
