package cn.xcloude.leetcode;

public class MaximumSubarray {
  public int maxSubArray(int[] A) {
    int max = A[0], sum = 0;
    for (int i : A) {
      sum += i;
      if (max < sum) {
        max = sum;
      }
      if (sum < 0) {
        sum = 0;
      }
    }
    return max;
  }
}
