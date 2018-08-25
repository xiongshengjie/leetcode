package cn.xcloude.leetcode.ReverseLinkedList_ii;

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class ReverseLinkedList_ii {
  public ListNode reverseBetween(ListNode head, int m, int n) {
    if (head == null) {
      return null;
    }
    ListNode mListNode = head, mLast = null, newHead = new ListNode(0);
    mLast = newHead;
    newHead.next = head;
    for (int i = 1; i < m; i++) {
      mLast = mListNode;
      mListNode = mListNode.next;
    }

    for (int i = 0; i < n - m; i++) {
      ListNode temp = mListNode.next;
      mListNode.next = temp.next;
      temp.next = mLast.next;
      mLast.next = temp;
    }

    return newHead.next;


  }
}

