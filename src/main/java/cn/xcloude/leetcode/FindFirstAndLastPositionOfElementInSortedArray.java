package cn.xcloude.leetcode;

/**
 * LC116
 * leetcode 34
 * 求目标值的区间
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
  public int[] searchRange(int[] nums, int target) {
    if (nums == null || nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) {
      return new int[] {-1, -1};
    }

    int minTargetIndex = getIndex(nums, target, true);
    int maxTargetIndex = getIndex(nums, target, false);
    return new int[] {minTargetIndex, maxTargetIndex};
  }

  /**
   * find min or max {@code index} which {@code nums[index] == target} from nums
   *
   * @param min {@code true} for min index
   * @return -1 if not found
   */
  private int getIndex(int[] nums, int target, boolean min) {
    int start = 0, end = nums.length - 1, middle, index = -1;
    while (start <= end) {
      middle = start + ((end - start) >> 1);
      if (nums[middle] == target) {
        index = middle;

        if (min) {
          end = middle - 1;
        } else {
          start = middle + 1;
        }
      } else if (nums[middle] > target) {
        end = middle - 1;
      } else {
        start = middle + 1;
      }
    }

    return index;
  }
}
