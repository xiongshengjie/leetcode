package cn.xcloude.leetcode;

/**
 * LC5 链表的插入排序
 */
public class InsertionSortList {
  public ListNode insertionSortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode temp = new ListNode(0, head);
    ListNode cur = head.next, curHead, lastSorted = head;
    while (cur != null) {
      if (cur.val >= lastSorted.val) {
        lastSorted = lastSorted.next;
      } else {
        curHead = temp;
        while (cur.val >= curHead.next.val) {
          curHead = curHead.next;
        }
        lastSorted.next = cur.next;
        cur.next = curHead.next;
        curHead.next = cur;
      }

      cur = lastSorted.next;
    }

    return temp.next;
  }

  public static void main(String[] args) {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2, node1);
    ListNode node5 = new ListNode(5, node2);
    ListNode node4 = new ListNode(4, node5);
    ListNode head = new ListNode(3, node4);
    ListNode node = new InsertionSortList().insertionSortList(head);
    while (node != null) {
      System.out.println(node.val);
      node = node.next;
    }
  }
}
