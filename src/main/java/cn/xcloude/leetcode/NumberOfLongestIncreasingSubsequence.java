package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * leetcode 673
 * 最长递增子序列的个数
 */
public class NumberOfLongestIncreasingSubsequence {
  public int findNumberOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    // return findNumberOfLisDp(nums);
    return findNumberOfLisGreedy(nums);
  }

  // 动态规划
  private int findNumberOfLisDp(int[] nums) {
    int[] dp = new int[nums.length], count = new int[nums.length];
    dp[0] = 1;
    count[0] = 1;
    int lisLength = 1, result = 1;
    for (int index = 1; index < nums.length; ++index) {
      dp[index] = 1;
      count[index] = 1;
      for (int dpIndex = 0; dpIndex < index; ++dpIndex) {
        if (nums[dpIndex] < nums[index]) {
          if (dp[index] < dp[dpIndex] + 1) {
            dp[index] = dp[dpIndex] + 1;
            count[index] = count[dpIndex];
          } else if (dp[index] == dp[dpIndex] + 1) {
            count[index] += count[dpIndex];
          }
        }
      }

      if (lisLength < dp[index]) {
        lisLength = dp[index];
        result = count[index];
      } else if (lisLength == dp[index]) {
        result += count[index];
      }
    }

    return result;
  }

  // 贪心
  private int findNumberOfLisGreedy(int[] nums) {
    // 每种长度的递增子序列可能的结尾元素
    List<List<Integer>> lisLastElements = new ArrayList<>();
    // 和上面的数据对应, 每种长度每个可能结尾元素对应的子序列个数的前缀和
    List<List<Integer>> lisCountOfElement = new ArrayList<>();

    for (int num : nums) {
      int lisLastElementsIndex = binarySearch(lisLastElements,
          list -> list.get(list.size() - 1) >= num);
      int lisCount = 1;
      if (lisLastElementsIndex > 0) {
        int lastElementIndex = binarySearch(lisLastElements.get(lisLastElementsIndex - 1),
            integer -> integer < num);
        List<Integer> lisCountOfElements = lisCountOfElement.get(lisLastElementsIndex - 1);
        lisCount = lisCountOfElements.get(lisCountOfElements.size() - 1)
            - lisCountOfElements.get(lastElementIndex);
      }

      if (lisLastElementsIndex == lisLastElements.size()) {
        List<Integer> lastElements = new ArrayList<>();
        lastElements.add(num);
        lisLastElements.add(lastElements);
        List<Integer> counts = new ArrayList<>();
        counts.add(0);
        counts.add(lisCount);
        lisCountOfElement.add(counts);
      } else {
        lisLastElements.get(lisLastElementsIndex).add(num);
        List<Integer> counts = lisCountOfElement.get(lisLastElementsIndex);
        counts.add(counts.get(counts.size() - 1) + lisCount);
      }
    }

    int size = lisCountOfElement.size(), innerSize = lisCountOfElement.get(size - 1).size();
    return lisCountOfElement.get(size - 1).get(innerSize - 1);
  }

  private <T> int binarySearch(List<T> list, Function<T, Boolean> function) {
    int start = 0, end = list.size() - 1, middle;
    while (start <= end) {
      middle = start + ((end - start) >> 1);
      if (function.apply(list.get(middle))) {
        end = middle - 1;
      } else {
        start = middle + 1;
      }
    }

    return start;
  }

  public static void main(String[] args) {
    int[] nums = new int[] {1, 3, 5, 4, 7};
    new NumberOfLongestIncreasingSubsequence().findNumberOfLIS(nums);
  }
}
