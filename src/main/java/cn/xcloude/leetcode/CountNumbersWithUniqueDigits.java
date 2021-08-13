package cn.xcloude.leetcode;

/**
 * leetcode 357
 * 计算各个位数不同的数字个数
 */
public class CountNumbersWithUniqueDigits {
  public int countNumbersWithUniqueDigits(int n) {
    if (n == 0) {
      return 1;
    }

    if (n > 10) {
      return 0;
    }

    int[] result = new int[n];
    result[0] = 10;
    int sum = 9;
    for (int current = 1; current < n; ++current) {
      sum *= (9 - current + 1);
      result[current] = result[current - 1] + sum;
    }

    return result[n - 1];
  }
}
