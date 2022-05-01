package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * leetcode 47
 * 全排列 II
 */
public class Permutations_ii {
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    permuteUnique(nums, 0, result);
    return result;
  }

  private void permuteUnique(int[] nums, int index, List<List<Integer>> result) {
    if (index >= nums.length) {
      result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
      return;
    }

    Set<Integer> used = new TreeSet<>();
    for (int currentIndex = index; currentIndex < nums.length; ++currentIndex) {
      if (used.add(nums[currentIndex])) {
        swap(nums, currentIndex, index);
        permuteUnique(nums, index + 1, result);
        swap(nums, currentIndex, index);
      }
    }
  }

  private void swap(int[] nums, int currentIndex, int index) {
    if (currentIndex == index) {
      return;
    }

    nums[currentIndex] ^= nums[index];
    nums[index] ^= nums[currentIndex];
    nums[currentIndex] ^= nums[index];
  }
}
