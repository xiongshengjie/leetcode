package cn.xcloude.leetcode;

/**
 * leetcode 50
 * Pow(x, n)
 */
public class PowXN {
  public double myPow(double x, int n) {
    if (n == 0 || x == 1) {
      return 1;
    }

    long exponent = n;
    if (exponent < 0) {
      exponent = -exponent;
      x = 1 / x;
    }

    // return powRecursion(x, exponent);
    return powIteratively(x, exponent);
  }

  // 迭代
  private double powIteratively(double base, long exponent) {
    double result = 1d, temp = base;
    while (exponent > 0) {
      if ((exponent & 1) == 1) {
        result *= temp;
      }

      exponent >>= 1;
      temp *= temp;
    }

    return result;
  }

  // 递归
  private double powRecursion(double base, long exponent) {
    if (exponent == 1) {
      return base;
    }

    long quotient = exponent >> 1;
    long remainder = exponent & 1;
    double result = powRecursion(base, quotient);
    return remainder == 0 ? (result * result) : (result * result * base);
  }
}
