package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * leetcode 496
 * 下一个更大元素 I
 */
public class NextGreaterElement_i {
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Deque<Integer> stack = new LinkedList<>();
    Map<Integer, Integer> element2NextGreaterElement = new HashMap<>(nums2.length);
    for (int index = nums2.length - 1; index >= 0; --index) {
      int num = nums2[index];
      while (!stack.isEmpty() && stack.peek() <= num) {
        stack.pop();
      }
      element2NextGreaterElement.put(num, stack.isEmpty() ? -1 : stack.peek());
      stack.push(num);
    }

    int[] result = new int[nums1.length];
    for (int index = 0; index < nums1.length; ++index) {
      result[index] = element2NextGreaterElement.get(nums1[index]);
    }

    return result;
  }
}
