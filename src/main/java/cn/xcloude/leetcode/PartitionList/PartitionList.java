package cn.xcloude.leetcode.PartitionList;

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class PartitionList {
  public ListNode partition(ListNode head, int x) {
    if (head == null) {
      return null;
    }
    ListNode smallNodeHead = new ListNode(0), smallNode = smallNodeHead, largeNodeHead = new ListNode(0), largeNode = largeNodeHead, temp = head;
    while (temp != null) {
      if (temp.val < x) {
        smallNode.next = temp;
        smallNode = smallNode.next;
      } else {
        largeNode.next = temp;
        largeNode = largeNode.next;
      }
      temp = temp.next;
    }
    smallNode.next = largeNodeHead.next;
    largeNode.next = null;
    return smallNodeHead.next;
  }
}

