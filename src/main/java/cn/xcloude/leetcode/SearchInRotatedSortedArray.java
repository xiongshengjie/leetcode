package cn.xcloude.leetcode;

/**
 * leetcode 33
 * 旋转排序数组搜索
 */
public class SearchInRotatedSortedArray {
  public int search(int[] nums, int target) {
    int start = 0, end = nums.length - 1, middle, middleNum;
    while (start <= end) {
      middle = (start + end) >> 1;
      middleNum = nums[middle];
      // 等于的情况先处理掉
      if (middleNum == target) {
        return middle;
      } else if (target == nums[start]) {
        return start;
      } else if (target == nums[end]) {
        return end;
      }

      // 在左边递增区域
      if (middleNum > nums[start]) {
        if (middleNum > target && nums[start] < target) {
          end = middle - 1;
        } else {
          start = middle + 1;
        }
      } else { // 在右边递增区域
        if (middleNum < target && nums[end] > target) {
          start = middle + 1;
        } else {
          end = middle - 1;
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 4, 5};
    System.out.println(new SearchInRotatedSortedArray().search(nums, 2));
  }
}
