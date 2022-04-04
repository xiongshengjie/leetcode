package cn.xcloude.leetcode;

/**
 * leetcode 160
 * 相交链表
 */
public class IntersectionOfTwoLinkedLists {
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }

    ListNode tempA = headA, tempB = headB;
    while (tempA != tempB) {
      tempA = tempA == null ? headB : tempA.next;
      tempB = tempB == null ? headA : tempB.next;
    }

    return tempA;
  }
}
