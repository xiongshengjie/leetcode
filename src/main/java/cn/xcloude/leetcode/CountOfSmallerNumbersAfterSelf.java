package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * leetcode 315
 * 计算右侧小于当前元素的个数
 */
public class CountOfSmallerNumbersAfterSelf {
  /**
   * 原数组下标数组, 跟随原数组排序变动而变动
   * 用来定位应该将个数加到 result 中间哪个元素上
   */
  private int[] indexes;

  /**
   * merge 过程中 {@link #indexes} 的临时存储
   */
  private int[] tempIndexes;

  /**
   * merge 过程中 nums 的临时存储
   */
  private int[] temp;

  private List<Integer> result;

  public List<Integer> countSmaller(int[] nums) {
    if (nums == null || nums.length <= 0) {
      return Collections.emptyList();
    }

    if (nums.length == 1) {
      return Collections.singletonList(0);
    }

    result = new ArrayList<>(nums.length);
    temp = new int[nums.length];
    tempIndexes = new int[nums.length];
    indexes = new int[nums.length];
    for (int i = 0; i < indexes.length; ++i) {
      indexes[i] = i;
      result.add(0);
    }

    mergeSort(nums, 0, nums.length - 1);
    return result;
  }

  private void mergeSort(int[] nums, int start, int end) {
    if (start >= end) {
      return;
    }

    int middle = start + ((end - start) >> 1);
    mergeSort(nums, start, middle);
    mergeSort(nums, middle + 1, end);
    merge(nums, start, middle, end);
  }

  private void merge(int[] nums, int start, int middle, int end) {
    int leftIndex = start, rightIndex = middle + 1, tempIndex = 0;
    while (leftIndex <= middle && rightIndex <= end) {
      if (nums[leftIndex] > nums[rightIndex]) {
        temp[tempIndex] = nums[leftIndex];
        tempIndexes[tempIndex] = indexes[leftIndex];
        result.set(indexes[leftIndex], result.get(indexes[leftIndex]) + (end - rightIndex + 1));
        ++leftIndex;
      } else {
        temp[tempIndex] = nums[rightIndex];
        tempIndexes[tempIndex] = indexes[rightIndex];
        ++rightIndex;
      }
      ++tempIndex;
    }

    while (leftIndex <= middle) {
      temp[tempIndex] = nums[leftIndex];
      tempIndexes[tempIndex] = indexes[leftIndex];
      result.set(indexes[leftIndex], result.get(indexes[leftIndex]) + (end - rightIndex + 1));
      ++leftIndex;
      ++tempIndex;
    }

    while (rightIndex <= end) {
      temp[tempIndex] = nums[rightIndex];
      tempIndexes[tempIndex] = indexes[rightIndex];
      ++rightIndex;
      ++tempIndex;
    }

    for (int i = 0; i < tempIndex; i++) {
      nums[start + i] = temp[i];
      indexes[start + i] = tempIndexes[i];
    }
  }

  public static void main(String[] args) {
    int[] nums = new int[] {5, 2, 6, 7, 82, 17, 27, 57, 13, 45, 67, 46, 264, 262, 16, 26, 136};
    List<Integer> integers = new CountOfSmallerNumbersAfterSelf().countSmaller(nums);
    System.out.println(integers);
  }
}
