package cn.xcloude.leetcode;

public class ListNode {
  public int val;
  public ListNode next;

  public ListNode(int x) {
    this(x, null);
  }

  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}
