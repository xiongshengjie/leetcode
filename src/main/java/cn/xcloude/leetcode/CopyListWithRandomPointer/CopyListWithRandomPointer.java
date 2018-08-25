package cn.xcloude.leetcode.CopyListWithRandomPointer;

class RandomListNode {
  int label;
  RandomListNode next, random;

  RandomListNode(int x) {
    this.label = x;
  }
}

public class CopyListWithRandomPointer {
  public RandomListNode copyRandomList(RandomListNode head) {
    if (head == null) {
      return null;
    }
    RandomListNode newHead, copy;
    for (RandomListNode p = head; p != null; p = p.next) {
      copy = new RandomListNode(p.label);
      copy.next = p.next;
      p.next = copy;
      p = copy;
    }
    newHead = head.next;
    for (RandomListNode p = head; p != null; p = copy.next) {
      copy = p.next;
      copy.random = (p.random == null) ? null : p.random.next;
    }
    for (RandomListNode p = head, temp = p.next; p != null; temp = temp.next) {
      p.next = temp.next;
      p = p.next;
      temp.next = (p == null) ? null : p.next;
    }
    return newHead;
  }
}

