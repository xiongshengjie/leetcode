package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 239
 * 滑动窗口最大值
 */
public class SlidingWindowMaximum {
  public int[] maxSlidingWindow(int[] nums, int k) {
    // 单调队列
    Deque<Integer> deque = new LinkedList<>();
    for (int index = 0; index < k; ++index) {
      expand(nums, deque, index);
    }

    int[] result = new int[nums.length - k + 1];
    result[0] = nums[deque.getFirst()];
    for (int index = k; index < nums.length; ++index) {
      reduce(deque, index - k);
      expand(nums, deque, index);

      result[index - k + 1] = nums[deque.getFirst()];
    }

    return result;
  }

  /**
   * 窗口左端点右移一位
   *
   * @param deque     窗口内数据单调递减的数的下标
   * @param leftIndex 调整后窗口左端点的下标
   */
  private void reduce(Deque<Integer> deque, int leftIndex) {
    if (!deque.isEmpty() && deque.peekFirst() <= leftIndex) {
      deque.pollFirst();
    }
  }

  /**
   * 窗口右端点右移一位
   *
   * @param nums  数组
   * @param deque 窗口内数据单调递减的数的下标
   * @param index 右边添加到窗口的值的下标
   */
  private void expand(int[] nums, Deque<Integer> deque, int index) {
    while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[index]) {
      deque.pollLast();
    }
    deque.offerLast(index);
  }
}
