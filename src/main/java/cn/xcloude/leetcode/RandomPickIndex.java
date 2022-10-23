package cn.xcloude.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * leetcode 398
 * 随机数索引
 */
// 水塘抽样实现
public class RandomPickIndex {
  private final int[] nums;
  private final Random random;

  public RandomPickIndex(int[] nums) {
    this.nums = nums;
    this.random = new Random();
  }

  public int pick(int target) {
    int count = 0;
    int result = 0;
    for (int index = 0; index < nums.length; ++index) {
      if (nums[index] == target) {
        ++count;
        if (random.nextInt(count) == 0) {
          result = index;
        }
      }
    }

    return result;
  }
}

// Map 实现
class RandomPickIndex0 {
  private final Map<Integer, List<Integer>> target2Indexes;
  private final Random random;

  public RandomPickIndex0(int[] nums) {
    this.target2Indexes = new TreeMap<>();
    for (int index = 0; index < nums.length; ++index) {
      List<Integer> indexes = target2Indexes.computeIfAbsent(
          nums[index], key -> new LinkedList<>());
      indexes.add(index);
    }

    this.random = new Random();
  }

  public int pick(int target) {
    List<Integer> indexes = target2Indexes.get(target);
    return indexes.get(random.nextInt(indexes.size()));
  }
}
