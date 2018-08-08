package cn.xcloude.leetcode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class RemoveDuplicatesFromSortedList {
  public ListNode deleteDuplicates(ListNode head) {
    if(head == null){
      return null;
    }
    ListNode temp = head;
    while(temp != null){
      while(temp.next != null && temp.val == temp.next.val){
        ListNode x = temp.next.next;
        temp.next = null;
        temp.next = x;
      }
      temp = temp.next;
    }

    return head;
  }
}

