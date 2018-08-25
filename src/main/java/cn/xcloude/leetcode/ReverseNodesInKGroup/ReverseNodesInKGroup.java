package cn.xcloude.leetcode.ReverseNodesInKGroup;

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class ReverseNodesInKGroup {
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || head.next == null || k < 2) {
      return head;
    }
    int length = 0;
    ListNode temp = head, newHead = new ListNode(0), node, pre = newHead;
    newHead.next = head;
    while (temp != null) {
      length++;
      temp = temp.next;
    }
    temp = head;
    for (int i = 0; i < length / k; i++) {
      for (int j = 1; j < k; j++) {
        node = temp.next;
        temp.next = node.next;
        node.next = pre.next;
        pre.next = node;
      }
      pre = temp;
      temp = temp.next;
    }
    return newHead.next;


  }
}

