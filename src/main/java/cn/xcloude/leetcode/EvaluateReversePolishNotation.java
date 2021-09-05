package cn.xcloude.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * LC2
 * leetcode 150
 * 后缀表达式求值
 */
public class EvaluateReversePolishNotation {
  private static final Map<String, BiFunction<Integer, Integer, Integer>> TOKEN_2_FUNCTION;

  static {
    TOKEN_2_FUNCTION = new HashMap<>();
    TOKEN_2_FUNCTION.put("+", Integer::sum);
    TOKEN_2_FUNCTION.put("-", (operand, otherOperand) -> operand - otherOperand);
    TOKEN_2_FUNCTION.put("*", (operand, otherOperand) -> operand * otherOperand);
    TOKEN_2_FUNCTION.put("/", (operand, otherOperand) -> operand / otherOperand);
  }

  public int evalRPN(String[] tokens) {
    if (tokens == null || tokens.length == 0) {
      return 0;
    }

    Deque<String> stack = new ArrayDeque<>(tokens.length);
    for (String token : tokens) {
      BiFunction<Integer, Integer, Integer> function = TOKEN_2_FUNCTION.get(token);
      if (function != null) {
        stack.push(calculate(stack.pop(), stack.pop(), function));
      } else {
        stack.push(token);
      }
    }

    return Integer.parseInt(stack.pop());
  }

  private String calculate(String operand, String otherOperand,
      BiFunction<Integer, Integer, Integer> function) {
    Integer result = function.apply(Integer.valueOf(otherOperand), Integer.valueOf(operand));
    return result.toString();
  }

  public static void main(String[] args) {
    String[] tokens = new String[] {"4", "13", "5", "/", "+"};
    new EvaluateReversePolishNotation().evalRPN(tokens);
  }
}
