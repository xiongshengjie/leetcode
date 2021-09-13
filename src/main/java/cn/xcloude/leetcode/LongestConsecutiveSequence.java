package cn.xcloude.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * LC23
 * leetcode 128
 * 最长的连续元素序列长度
 */
public class LongestConsecutiveSequence {
  public int longestConsecutive(int[] nums) {
    Set<Integer> set = new HashSet<>((int) (nums.length / 0.75));
    for (int num : nums) {
      set.add(num);
    }

    int result = 0;
    for (Integer num : set) {
      if (!set.contains(num - 1)) {
        int currentResult = 1;
        int currentNum = num + 1;
        while (set.contains(currentNum)) {
          ++currentResult;
          ++currentNum;
        }

        if (currentResult > result) {
          result = currentResult;
        }
      }
    }

    return result;
  }
}
