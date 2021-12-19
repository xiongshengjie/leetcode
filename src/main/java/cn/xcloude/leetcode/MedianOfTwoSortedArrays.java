package cn.xcloude.leetcode;

/**
 * leetcode 4
 * 寻找两个正序数组的中位数
 */
public class MedianOfTwoSortedArrays {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    // return findMedianSortedArraysTraverse(nums1, nums2);
    return findMedianSortedArraysBinarySearch(nums1, nums2);
  }

  // 二分查找
  private double findMedianSortedArraysBinarySearch(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
      return findMedianSortedArrays(nums2, nums1);
    }

    int m = nums1.length;
    int n = nums2.length;
    int left = 0, right = m;
    // median1：前一部分的最大值
    // median2：后一部分的最小值
    int median1 = 0, median2 = 0;

    while (left <= right) {
      // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
      // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
      int i = (left + right) / 2;
      int j = (m + n + 1) / 2 - i;

      // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
      int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
      int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
      int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
      int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

      if (nums_im1 <= nums_j) {
        median1 = Math.max(nums_im1, nums_jm1);
        median2 = Math.min(nums_i, nums_j);
        left = i + 1;
      } else {
        right = i - 1;
      }
    }

    return (m + n) % 2 == 0 ? (median1 + median2) / 2.0D : median1;
  }

  // 遍历
  private double findMedianSortedArraysTraverse(int[] nums1, int[] nums2) {
    int leftIndex = 0, rightIndex = 0, median1 = -1, median2 = -1;
    int count = (nums1.length + nums2.length) / 2;
    while (leftIndex + rightIndex <= count) {
      median1 = median2;
      if (leftIndex < nums1.length
          && (rightIndex >= nums2.length || nums1[leftIndex] < nums2[rightIndex])) {
        median2 = nums1[leftIndex++];
      } else {
        median2 = nums2[rightIndex++];
      }
    }

    if (((nums1.length + nums2.length) & 1) == 0) {
      return (median1 + median2) / 2.0D;
    }
    return median2;
  }
}
