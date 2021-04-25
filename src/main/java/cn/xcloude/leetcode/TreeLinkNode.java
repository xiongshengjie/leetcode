package cn.xcloude.leetcode;

public class TreeLinkNode {
  public int val;
  public TreeLinkNode left, right, next;

  public TreeLinkNode() {
  }

  public TreeLinkNode(int x) {
    val = x;
  }

  public TreeLinkNode(int val, TreeLinkNode left, TreeLinkNode right, TreeLinkNode next) {
    this.val = val;
    this.left = left;
    this.right = right;
    this.next = next;
  }
}
