package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 227
 * 基本计算器 II
 */
public class BasicCalculator_ii {
  public int calculate(String s) {
    if (s == null || s.isEmpty()) {
      return -1;
    }

    Character preOperator = null;
    Deque<Integer> operand = new LinkedList<>();
    int index = 0;
    while (index < s.length()) {
      char current = s.charAt(index);
      if (current == '+' || current == '-') {
        if (preOperator != null) {
          calculate(operand, preOperator);
        }
        preOperator = current;
        ++index;
      } else if (current == '*' || current == '/') {
        index = pushNextNumber(index + 1, s, operand);
        calculate(operand, current);
      } else {
        index = pushNextNumber(index, s, operand);
      }
    }

    if (operand.size() > 1) {
      calculate(operand, preOperator);
    }

    return operand.pop();
  }

  private void calculate(Deque<Integer> operandStack, Character operator) {
    Integer first = operandStack.pop();
    Integer second = operandStack.pop();
    int result;
    if (operator == '+') {
      result = second + first;
    } else if (operator == '-') {
      result = second - first;
    } else if (operator == '*') {
      result = second * first;
    } else {
      result = second / first;
    }

    operandStack.push(result);
  }

  private int pushNextNumber(int index, String formula, Deque<Integer> operand) {
    int number = 0;
    while (index < formula.length()) {
      if (formula.charAt(index) == ' ') {
        ++index;
      } else if (Character.isDigit(formula.charAt(index))) {
        number = number * 10 + formula.charAt(index) - '0';
        ++index;
      } else {
        break;
      }
    }

    operand.push(number);
    return index;
  }
}
