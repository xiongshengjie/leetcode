package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 224
 * 基本计算器
 */
public class BasicCalculator {
  public int calculate(String s) {
    if (s == null || s.isEmpty()) {
      return 0;
    }

    Deque<Integer> operator = new LinkedList<>();
    operator.push(1);
    int result = 0;
    int index = 0;
    int sign = 1;
    while (index < s.length()) {
      if (s.charAt(index) == ' ') {
        ++index;
      } else if (s.charAt(index) == '+') {
        sign = operator.peek();
        ++index;
      } else if (s.charAt(index) == '-') {
        sign = -operator.peek();
        ++index;
      } else if (s.charAt(index) == '(') {
        operator.push(sign);
        ++index;
      } else if (s.charAt(index) == ')') {
        operator.pop();
        ++index;
      } else {
        int number = 0;
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
          number = number * 10 + s.charAt(index) - '0';
          ++index;
        }

        result += sign * number;
      }
    }

    return result;
  }
}
