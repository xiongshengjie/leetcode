package cn.xcloude.leetcode.MergeKSortedLists;

import java.util.ArrayList;

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class MergeKSortedLists {
  public ListNode mergeKLists(ArrayList<ListNode> lists) {
    if (lists.size() <= 0) {
      return null;
    }
    if (lists.size() == 1) {
      return lists.get(0);
    }

    ListNode head = null;

    for (int i = 0; i < lists.size(); i++) {
      head = mergeTwoLists(head, lists.get(i));
    }
    return head;
  }

  public ListNode mergeTwoLists(ListNode first, ListNode second) {
    ListNode head = new ListNode(0), temp = head;
    while (first != null && second != null) {
      if (first.val > second.val) {
        head.next = second;
        second = second.next;
      } else {
        head.next = first;
        first = first.next;
      }
      head = head.next;
    }
    if (first != null) {
      head.next = first;
    }
    if (second != null) {
      head.next = second;
    }
    return temp.next;
  }
}

