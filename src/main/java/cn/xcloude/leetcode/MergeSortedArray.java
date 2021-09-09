package cn.xcloude.leetcode;

/**
 * LC61
 * leetcode 88
 * 合并两个有序数组
 */
public class MergeSortedArray {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    if (n <= 0) {
      return;
    }

    if (m <= 0) {
      System.arraycopy(nums2, 0, nums1, 0, n);
      return;
    }

    int index = m + n - 1;
    --m;
    --n;
    while (m >= 0 && n >= 0) {
      if (nums1[m] > nums2[n]) {
        nums1[index] = nums1[m];
        --m;
      } else {
        nums1[index] = nums2[n];
        --n;
      }
      --index;
    }

    while (n >= 0) {
      nums1[index] = nums2[n];
      --n;
      --index;
    }
  }
}
