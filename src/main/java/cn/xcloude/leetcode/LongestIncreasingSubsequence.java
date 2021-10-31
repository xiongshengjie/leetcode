package cn.xcloude.leetcode;

/**
 * leetcode 300
 * 最长递增子序列
 */
public class LongestIncreasingSubsequence {
  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    // return lengthOfLisDp(nums);
    return lengthOfLisGreedy(nums);
  }

  // 动态规划
  private int lengthOfLisDp(int[] nums) {
    int[] dp = new int[nums.length];
    dp[0] = 1;
    int result = 1;
    for (int index = 1; index < nums.length; ++index) {
      dp[index] = 1;
      for (int dpIndex = 0; dpIndex < index; ++dpIndex) {
        if (nums[dpIndex] < nums[index] && dp[index] < dp[dpIndex] + 1) {
          dp[index] = dp[dpIndex] + 1;
        }
      }

      if (result < dp[index]) {
        result = dp[index];
      }
    }

    return result;
  }

  // 贪心
  private int lengthOfLisGreedy(int[] nums) {
    int lisLength = 1, length = nums.length;
    // lisLeastLastOfElement[index] 代表长度为 index 的最长递增子序列的末尾的最小值
    // 即增长的最慢的情况下，最后一个元素的值
    int[] lisLeastLastOfElement = new int[length];
    lisLeastLastOfElement[0] = nums[0];

    for (int index = 1; index < length; ++index) {
      if (nums[index] > lisLeastLastOfElement[lisLength - 1]) {
        lisLeastLastOfElement[lisLength] = nums[index];
        ++lisLength;
      } else if (nums[index] < lisLeastLastOfElement[lisLength - 1]) {
        int maxIndex = binarySearch(lisLeastLastOfElement, lisLength, nums[index]);
        lisLeastLastOfElement[maxIndex + 1] = nums[index];
      }
    }

    return lisLength;
  }

  private int binarySearch(int[] array, int length, int target) {
    int start = 0, end = length - 1, middle, result = -1;
    while (start <= end) {
      middle = start + ((end - start) >> 1);
      if (array[middle] == target) {
        return middle - 1;
      } else if (array[middle] < target) {
        result = middle;
        start = middle + 1;
      } else {
        end = middle - 1;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    int[] nums = new int[] {10, 9, 2, 5, 3, 7, 101, 18};
    new LongestIncreasingSubsequence().lengthOfLIS(nums);
  }
}
