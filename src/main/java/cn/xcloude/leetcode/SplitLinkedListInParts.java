package cn.xcloude.leetcode;

/**
 * leetcode 725
 * 分隔链表
 */
public class SplitLinkedListInParts {
  public ListNode[] splitListToParts(ListNode head, int k) {
    int length = 0;
    ListNode temp = head;
    while (temp != null) {
      length++;
      temp = temp.next;
    }
    int quotient = length / k, remainder = length % k;

    ListNode[] result = new ListNode[k];
    ListNode current = head;
    for (int i = 0; i < k && current != null; i++) {
      result[i] = current;
      int partSize = quotient + (i < remainder ? 1 : 0);
      for (int j = 1; j < partSize; j++) {
        current = current.next;
      }
      temp = current.next;
      current.next = null;
      current = temp;
    }
    return result;
  }
}
