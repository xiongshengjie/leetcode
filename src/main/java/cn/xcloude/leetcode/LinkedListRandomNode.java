package cn.xcloude.leetcode;

import java.util.Random;

/**
 * leetcode 382
 * 链表随机节点
 */
public class LinkedListRandomNode {
  private final ListNode head;
  private final Random random;

  public LinkedListRandomNode(ListNode head) {
    this.head = head;
    this.random = new Random();
  }

  public int getRandom() {
    int result = 0;
    int index = 1;
    ListNode current = head;
    while (current != null) {
      int randomValue = random.nextInt(index);
      if (randomValue == 0) {
        result = current.val;
      }
      ++index;
      current = current.next;
    }

    return result;
  }
}
