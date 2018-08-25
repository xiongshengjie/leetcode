package cn.xcloude.leetcode.AddTwoNumbers;

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class AddTwoNumbers {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    ListNode head1 = l1, head2 = l2, pre = new ListNode(0), temp = pre;
    int remain = 0;
    while (head1 != null && head2 != null) {
      int value = (head1.val + head2.val + remain) % 10;
      remain = (head1.val + head2.val + remain) / 10;
      temp.next = new ListNode(value);
      temp = temp.next;
      head1 = head1.next;
      head2 = head2.next;
      if (head1 == null && head2 != null) {
        head1 = new ListNode(0);
      }
      if (head2 == null && head1 != null) {
        head2 = new ListNode(0);
      }
    }
    if (remain != 0) {
      temp.next = new ListNode(remain);
    }
    return pre.next;
  }

  public static void main(String[] args) {
    new AddTwoNumbers().addTwoNumbers(new ListNode(0),new ListNode(1));
  }
}

