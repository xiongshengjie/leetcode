package cn.xcloude.leetcode;

import java.util.Random;

/**
 * leetcode 215
 * 数组中的第K个最大元素
 */
public class KthLargestElementInAnArray {
  private final Random random = new Random();

  public int findKthLargest(int[] nums, int k) {
    return quickSelect(nums, k, 0, nums.length - 1);
  }

  private int quickSelect(int[] array, int k, int start, int end) {
    int target = array[random.nextInt(start, end + 1)];
    int leftEndIndex = start - 1;
    int rightStartIndex = end + 1;
    int index = start;
    while (index < rightStartIndex) {
      if (array[index] < target) {
        --rightStartIndex;
        swap(array, index, rightStartIndex);
      } else if (array[index] > target) {
        ++leftEndIndex;
        swap(array, index, leftEndIndex);
        ++index;
      } else {
        ++index;
      }
    }

    if (k > leftEndIndex + 1 && k < rightStartIndex + 1) {
      return target;
    }

    if (k <= leftEndIndex + 1) {
      return quickSelect(array, k, start, leftEndIndex);
    } else {
      return quickSelect(array, k, rightStartIndex, end);
    }
  }

  private void swap(int[] array, int index, int otherIndex) {
    int temp = array[index];
    array[index] = array[otherIndex];
    array[otherIndex] = temp;
  }
}
