package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode LCR 147
 * nowcoder JZ30
 * 最小栈
 */
public class MinStack {
  private final Deque<Element> stack;

  public MinStack() {
    this.stack = new LinkedList<>();
  }

  public void push(int x) {
    if (stack.isEmpty()) {
      stack.push(new Element(x, x));
      return;
    }

    Element element = stack.peek();
    stack.push(new Element(x, Math.min(x, element.getMin())));
  }

  public void pop() {
    stack.pop();
  }

  public int top() {
    if (stack.isEmpty()) {
      throw new IllegalStateException("stack is empty");
    }

    return stack.peek().getValue();
  }

  public int getMin() {
    if (stack.isEmpty()) {
      throw new IllegalStateException("stack is empty");
    }

    return stack.peek().getMin();
  }

  private static final class Element {
    private final int value;
    private final int min;

    private Element(int value, int min) {
      this.value = value;
      this.min = min;
    }

    private int getValue() {
      return value;
    }

    private int getMin() {
      return min;
    }
  }
}
