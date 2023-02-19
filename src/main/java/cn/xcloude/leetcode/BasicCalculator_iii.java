package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * lintcode 849
 * 基础计算器 III
 */
public class BasicCalculator_iii {
  public int calculate(String s) {
    if (s == null || s.isEmpty()) {
      return -1;
    }

    Deque<Integer> operand = new LinkedList<>();
    Deque<Character> operator = new LinkedList<>();
    int index = 0;
    while (index < s.length()) {
      char current = s.charAt(index);
      if (current == ' ') {
        ++index;
        continue;
      }

      if (Character.isDigit(current)) {
        int number = 0;
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
          number = number * 10 + s.charAt(index) - '0';
          ++index;
        }
        operand.push(number);
      } else if (current == '(') {
        operator.push(current);
        ++index;
      } else if (current == ')') {
        while (operator.peek() != '(') {
          operand.push(calculate(operator.pop(), operand.pop(), operand.pop()));
        }
        operator.pop();
        ++index;
      } else if (current == '+' || current == '-' || current == '*' || current == '/') {
        while (!operator.isEmpty() && isPrecedence(current, operator.peek())) {
          operand.push(calculate(operator.pop(), operand.pop(), operand.pop()));
        }
        operator.push(current);
        ++index;
      }
    }

    while (!operator.isEmpty()) {
      operand.push(calculate(operator.pop(), operand.pop(), operand.pop()));
    }

    return operand.pop();
  }

  private boolean isPrecedence(char current, Character topOperator) {
    if (topOperator == '(' || topOperator == ')') {
      return false;
    }

    return (topOperator != '+' && topOperator != '-') || (current != '*' && current != '/');
  }

  private Integer calculate(Character operator, Integer first, Integer second) {
    if (operator == '+') {
      return second + first;
    } else if (operator == '-') {
      return second - first;
    } else if (operator == '*') {
      return second * first;
    } else {
      return second / first;
    }
  }
}
