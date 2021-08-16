package cn.xcloude.leetcode;

/**
 * leetcode 剑指 offer 51
 * 数组中的逆序对
 */
public class ReversePairs {
  public int reversePairs(int[] nums) {
    int[] temp = new int[nums.length];
    return process(nums, 0, nums.length - 1, temp);
  }

  private int process(int[] nums, int start, int end, int[] temp) {
    if (start >= end) {
      return 0;
    }

    int middle = start + ((end - start) >> 1);

    int leftCount = process(nums, start, middle, temp);
    int rightCount = process(nums, middle + 1, end, temp);
    if (nums[middle] < nums[middle + 1]) {
      return leftCount + rightCount;
    }

    return leftCount + rightCount + merge(nums, start, middle, end, temp);
  }

  private int merge(int[] nums, int start, int middle, int end, int[] temp) {
    int leftIndex = start, rightIndex = middle + 1, tempIndex = 0, result = 0;
    while (leftIndex <= middle && rightIndex <= end) {
      if (nums[leftIndex] <= nums[rightIndex]) {
        temp[tempIndex++] = nums[leftIndex++];
      } else {
        result += (middle - leftIndex + 1);
        temp[tempIndex++] = nums[rightIndex++];
      }
    }

    while (leftIndex <= middle) {
      temp[tempIndex++] = nums[leftIndex++];
    }

    while (rightIndex <= end) {
      temp[tempIndex++] = nums[rightIndex++];
    }

    System.arraycopy(temp, 0, nums, start, end - start + 1);
    return result;
  }

  public static void main(String[] args) {
    int[] nums = new int[] {7, 5, 6, 4, 3};
    System.out.println(new ReversePairs().reversePairs(nums));
  }
}
