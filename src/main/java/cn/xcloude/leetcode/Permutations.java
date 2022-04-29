package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * leetcode 46
 * 全排列
 */
public class Permutations {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    permute(nums, 0, result);
    return result;
  }

  private void permute(int[] nums, int index, List<List<Integer>> result) {
    if (index >= nums.length) {
      result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    for (int currentIndex = index; currentIndex < nums.length; ++currentIndex) {
      swap(nums, currentIndex, index);
      permute(nums, index + 1, result);
      swap(nums, currentIndex, index);
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
