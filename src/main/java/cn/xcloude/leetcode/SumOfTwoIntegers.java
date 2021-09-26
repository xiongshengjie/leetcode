package cn.xcloude.leetcode;

/**
 * leetcode 371
 * 两整数之和
 */
public class SumOfTwoIntegers {
  public int getSum(int a, int b) {
    int temp;
    while (b != 0) {
      temp = a;
      a = a ^ b;
      b = (temp & b) << 1;
    }

    return a;
  }
}
