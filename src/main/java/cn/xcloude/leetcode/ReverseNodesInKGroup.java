package cn.xcloude.leetcode;

/**
 * leetcode 25
 * K 个一组翻转链表
 */
public class ReverseNodesInKGroup {
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || head.next == null || k < 2) {
      return head;
    }

    // return reverseKGroup0(head, k);
    return reverseKGroup1(head, k);
  }

  // 一边遍历一边反转
  private ListNode reverseKGroup1(ListNode head, int k) {
    ListNode temp = head, currentHead, nextHead, newHead = new ListNode(0, head), preTail = newHead;
    while (temp != null) {
      currentHead = temp;
      for (int index = 0; index < k - 1; ++index) {
        temp = temp.next;
        if (temp == null) {
          return newHead.next;
        }
      }

      nextHead = temp.next;
      // 将链表断开, 才能调用 reverse 方法
      temp.next = null;

      preTail.next = reverse(currentHead);
      // 反转后 currentHead 变成了尾节点
      preTail = currentHead;
      preTail.next = nextHead;
      temp = nextHead;
    }

    return newHead.next;
  }

  /**
   * 反转给定链表
   */
  private ListNode reverse(ListNode head) {
    ListNode reverseHead = null;
    while (head != null) {
      ListNode next = head.next;
      head.next = reverseHead;
      reverseHead = head;
      head = next;
    }

    return reverseHead;
  }

  // 先遍历一次获取链表长度, 再对每部分反转
  private ListNode reverseKGroup0(ListNode head, int k) {
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
