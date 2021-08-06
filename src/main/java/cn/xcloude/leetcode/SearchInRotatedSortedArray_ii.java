package cn.xcloude.leetcode;

/**
 * LC 68
 * 旋转排序数组搜索 ii
 */
public class SearchInRotatedSortedArray_ii {
  public boolean search(int[] nums, int target) {
    int start = 0, end = nums.length - 1, middle, middleNum;
    while (start <= end) {
      middle = (start + end) >> 1;
      middleNum = nums[middle];
      // 等于的情况先处理掉
      if (middleNum == target || target == nums[start] || target == nums[end]) {
        return true;
      }

      // 无法判断是在左边递增区域还是右边递增区域
      // start 和 end 都往中间移动一位, 再继续二分
      if (middleNum == nums[start] && middleNum == nums[end]) {
        ++start;
        --end;
      } else if (middleNum >= nums[start]) { // 在左边递增区域
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

    return false;
  }

  public static void main(String[] args) {
    int[] nums = new int[] {2, 2, 2, 0, 0, 1};
    System.out.println(new SearchInRotatedSortedArray_ii().search(nums, 0));
  }
}
