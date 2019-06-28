package cn.xcloude.leetcode.DivideTwoIntegers;

public class DivideTwoIntegers {
  public int divide(int dividend, int divisor) {
    if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1) {
      return Integer.MAX_VALUE;
    }
    int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
    int result = 0;
    long tempDividend = Math.abs((long) dividend);
    long tempDivisor = Math.abs((long) divisor);
    while (tempDividend >= tempDivisor) {
      long t = tempDivisor, p = 1;
      while (tempDividend >= (t << 1)) {
        t <<= 1;
        p <<= 1;
      }
      result += p;
      tempDividend -= t;
    }
    return sign == 1 ? result : -result;
  }

  public static void main(String[] args) {
    int dividend = 14;
    int divisor = 2;
    System.out.println(new DivideTwoIntegers().divide(dividend, divisor));
    System.out.println(dividend / divisor);
  }
}
