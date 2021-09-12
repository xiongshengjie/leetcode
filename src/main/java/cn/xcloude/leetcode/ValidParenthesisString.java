package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 678
 * 有效的括号字符串
 */
public class ValidParenthesisString {
  public boolean checkValidString(String s) {
    // return checkValidStringStack(s);
    return checkValidStringGreedy(s);
  }

  // 使用栈实现
  private boolean checkValidStringStack(String s) {
    char[] chars = s.toCharArray();
    Deque<Integer> leftParenthesisStack = new LinkedList<>();
    Deque<Integer> asteriskStack = new LinkedList<>();

    for (int index = 0; index < chars.length; index++) {
      char ch = chars[index];
      if (ch == '(') {
        leftParenthesisStack.push(index);
      } else if (ch == '*') {
        asteriskStack.push(index);
      } else if (ch == ')') {
        if (!leftParenthesisStack.isEmpty()) {
          leftParenthesisStack.pop();
        } else if (!asteriskStack.isEmpty()) {
          asteriskStack.pop();
        } else {
          return false;
        }
      }
    }

    Integer leftParenthesisIndex, asteriskIndex;
    while (!leftParenthesisStack.isEmpty() && !asteriskStack.isEmpty()) {
      leftParenthesisIndex = leftParenthesisStack.pop();
      asteriskIndex = asteriskStack.pop();
      if (leftParenthesisIndex > asteriskIndex) {
        return false;
      }
    }

    return leftParenthesisStack.isEmpty();
  }

  // 贪心算法
  private boolean checkValidStringGreedy(String s) {
    int min = 0, max = 0;
    char[] chars = s.toCharArray();
    for (char ch : chars) {
      if (ch == '(') {
        ++min;
        ++max;
      } else if (ch == ')') {
        if (min > 0) {
          --min;
        }

        --max;
        if (max < 0) {
          return false;
        }
      } else {
        if (min > 0) {
          --min;
        }
        ++max;
      }
    }

    return min == 0;
  }
}
