package cn.xcloude.leetcode;

/**
 * leetcode 718
 * 最长重复子数组
 */
public class MaximumLengthOfRepeatedSubarray {
  public int findLength(int[] nums1, int[] nums2) {
    // dp[nums1Index][nums2Index] 代表 nums1 数组的 nums1Index 到结束
    // 与 nums2 数组的 nums2Index 到结束的最长公共前缀
    // 由于 dp[nums1Index] 只依赖 dp[nums1Index + 1] 的数据
    // 所以可以只存储 nums1Index + 1 行的数据, 计算第 nums1Index 的数据即可
    // 注意 nums2Index 需要从最小到最大遍历, 避免覆盖
    int[] dp = new int[nums2.length + 1];
    int result = 0;
    for (int index = nums1.length - 1; index >= 0; --index) {
      for (int otherIndex = 0; otherIndex < nums2.length; ++otherIndex) {
        if (nums1[index] == nums2[otherIndex]) {
          dp[otherIndex] = dp[otherIndex + 1] + 1;
          if (result < dp[otherIndex]) {
            result = dp[otherIndex];
          }
        } else {
          dp[otherIndex] = 0;
        }
      }
    }

    return result;
  }
}
