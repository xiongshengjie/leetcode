package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 面试题 08.06
 * 汉诺塔问题
 */
public class HanotaLCCI {
  public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
    // hanotaRecursion(A.size(), A, B, C);
    hanotaIteratively(A, B, C);
  }

  /**
   * hanota 递归实现
   *
   * @param n      盘子的数量
   * @param from   盘子目前在的柱子
   * @param helper 借助的柱子
   * @param to     将要移动到的柱子
   */
  private void hanotaRecursion(int n, List<Integer> from, List<Integer> helper, List<Integer> to) {
    if (n == 1) {
      to.add(from.remove(from.size() - 1));
      return;
    }

    hanotaRecursion(n - 1, from, to, helper);
    to.add(from.remove(from.size() - 1));
    hanotaRecursion(n - 1, helper, from, to);
  }

  // hanota 迭代实现
  private void hanotaIteratively(List<Integer> from, List<Integer> helper, List<Integer> to) {
    Deque<Node> stack = new LinkedList<>();
    stack.push(new Node(from.size(), from, helper, to));
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      if (node.n == 1) {
        node.to.add(node.from.remove(node.from.size() - 1));
      } else {
        stack.push(new Node(node.n - 1, node.helper, node.from, node.to));
        stack.push(new Node(1, node.from, node.helper, node.to));
        stack.push(new Node(node.n - 1, node.from, node.to, node.helper));
      }
    }
  }

  private static class Node {
    private final int n;
    private final List<Integer> from;
    private final List<Integer> helper;
    private final List<Integer> to;

    private Node(int n, List<Integer> from, List<Integer> helper, List<Integer> to) {
      this.n = n;
      this.from = from;
      this.helper = helper;
      this.to = to;
    }
  }
}
