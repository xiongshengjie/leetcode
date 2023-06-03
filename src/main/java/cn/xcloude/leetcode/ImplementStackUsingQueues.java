package cn.xcloude.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode 225
 * 用队列实现栈
 */
public class ImplementStackUsingQueues {
  /**
   * 两个队列实现栈
   */
  static class MyStack {
    private Queue<Integer> queue;
    private Queue<Integer> transfer;

    public MyStack() {
      queue = new LinkedList<>();
      transfer = new LinkedList<>();
    }

    public void push(int x) {
      transfer.offer(x);
      while (!queue.isEmpty()) {
        transfer.offer(queue.poll());
      }
      Queue<Integer> temp = queue;
      queue = transfer;
      transfer = temp;
    }

    public int pop() {
      return queue.poll();
    }

    public int top() {
      return queue.peek();
    }

    public boolean empty() {
      return queue.isEmpty();
    }
  }

  /**
   * 一个队列实现栈
   */
  static class MyStack1 {
    private final Queue<Integer> queue;

    public MyStack1() {
      queue = new LinkedList<>();
    }

    public void push(int x) {
      int size = queue.size();
      queue.offer(x);
      for (int count = 0; count < size; ++count) {
        queue.offer(queue.poll());
      }
    }

    public int pop() {
      return queue.poll();
    }

    public int top() {
      return queue.peek();
    }

    public boolean empty() {
      return queue.isEmpty();
    }
  }
}
