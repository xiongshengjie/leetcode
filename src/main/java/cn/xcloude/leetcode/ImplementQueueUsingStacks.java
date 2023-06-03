package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 232
 * 用栈实现队列
 */
public class ImplementQueueUsingStacks {
  static class MyQueue {
    private Deque<Integer> stack;
    private Deque<Integer> transfer;

    public MyQueue() {
      stack = new LinkedList<>();
      transfer = new LinkedList<>();
    }

    public void push(int x) {
      transfer.push(x);
    }

    public int pop() {
      if (stack.isEmpty()) {
        transfer();
      }

      return stack.pop();
    }

    public int peek() {
      if (stack.isEmpty()) {
        transfer();
      }

      return stack.peek();
    }

    public boolean empty() {
      return stack.isEmpty() && transfer.isEmpty();
    }

    private void transfer() {
      while (!transfer.isEmpty()) {
        stack.push(transfer.pop());
      }
    }
  }
}
