package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 1856
 * 子数组最小乘积的最大值
 */
public class MaximumSubArrayMinProduct {
  private static final int MOD = 1000000007;

  public int maxSumMinProduct(int[] nums) {
    long result = Long.MIN_VALUE;

    // 前缀和数组, 数组中 [i, j) 范围的元素和为 prefix[j] - prefix[i]
    long[] prefixSum = new long[nums.length + 1];
    for (int index = 1; index <= nums.length; ++index) {
      prefixSum[index] = prefixSum[index - 1] + nums[index - 1];
    }

    Deque<Integer> stack = new LinkedList<>();
    for (int index = 0; index < nums.length; ++index) {
      // 单调栈有元素需要出栈并处理, 出栈意味着这些元素已经找到了它左右离它最近且比它小的元素了
      // 正常情况下, 如果有相等的元素, 单调栈里应该存储的是一个个链表, 将相等的元素的下标存在一个链表内
      // 这里由于相等的情况下, 相等的元素中, 以最后一个为最小值时一定能够将前面相等的都算上, 可以不使用链表
      while (!stack.isEmpty() && nums[stack.peek()] >= nums[index]) {
        result = Math.max(result, popAndCalculate(nums, prefixSum, stack, index));
      }

      stack.push(index);
    }

    // 剩下的元素出栈并处理
    while (!stack.isEmpty()) {
      result = Math.max(result, popAndCalculate(nums, prefixSum, stack, nums.length));
    }

    return (int) (result % MOD);
  }

  private long popAndCalculate(int[] nums, long[] prefixSum, Deque<Integer> stack, int index) {
    int min = nums[stack.pop()];
    // 以栈顶下标元素为最小值的子数组的左端点, 包含该下标的元素
    int leftGreaterIndex = stack.peek() == null ? 0 : stack.peek() + 1;
    return min * (prefixSum[index] - prefixSum[leftGreaterIndex]);
  }
}
