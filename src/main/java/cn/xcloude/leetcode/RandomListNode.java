package cn.xcloude.leetcode;

public class RandomListNode {
  public int label;
  public RandomListNode next, random;

  public RandomListNode() {
  }

  public RandomListNode(int x) {
    this.label = x;
  }

  public RandomListNode(int label, RandomListNode next, RandomListNode random) {
    this.label = label;
    this.next = next;
    this.random = random;
  }
}
