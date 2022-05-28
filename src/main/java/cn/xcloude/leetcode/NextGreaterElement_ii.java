package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 503
 * 下一个更大元素 II
 */
public class NextGreaterElement_ii {
  public int[] nextGreaterElements(int[] nums) {
    Deque<Integer> stack = new LinkedList<>();
    int[] result = new int[nums.length];
    for (int index = 0; index < nums.length; ++index) {
      while (!stack.isEmpty() && nums[index] > nums[stack.peek()]) {
        result[stack.pop()] = nums[index];
      }
      stack.push(index);
    }

    for (int index = 0; index < stack.peek(); ++index) {
      while (!stack.isEmpty() && nums[index] > nums[stack.peek()]) {
        result[stack.pop()] = nums[index];
      }
    }

    while (!stack.isEmpty()) {
      result[stack.pop()] = -1;
    }

    return result;
  }
}
