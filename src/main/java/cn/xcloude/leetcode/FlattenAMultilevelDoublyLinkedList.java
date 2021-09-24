package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

class Node {
  public int val;
  public Node prev;
  public Node next;
  public Node child;
}

/**
 * leetcode 430
 * 扁平化多级双向链表
 */
public class FlattenAMultilevelDoublyLinkedList {
  public Node flatten(Node head) {
    if (head == null) {
      return null;
    }

    // flattenRecursion(head);
    flattenIteratively(head);
    return head;
  }

  private void flattenIteratively(Node head) {
    Deque<Node> stack = new LinkedList<>();
    stack.push(head);
    Node last = new Node();

    while (!stack.isEmpty()) {
      Node node = stack.pop();
      last.next = node;
      node.prev = last;
      last = node;

      if (node.next != null) {
        stack.push(node.next);
      }

      if (node.child != null) {
        stack.push(node.child);
        node.child = null;
      }
    }

    head.prev = null;
  }

  private Node flattenRecursion(Node node) {
    while (node != null) {
      Node next = node.next;
      if (node.child != null) {
        node.next = node.child;
        node.child.prev = node;

        Node childLast = flattenRecursion(node.child);
        node.child = null;
        if (next != null) {
          childLast.next = next;
          next.prev = childLast;
        }

        node = childLast;
      }

      if (node.next == null) {
        return node;
      }
      node = node.next;
    }

    return null;
  }
}
