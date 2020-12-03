package cn.xcloude.leetcode.ConvertToBaseNegativeTwo;

/**
 * Leetcode 1017
 * 转换数字为 -2 进制
 */
public class ConvertToBaseNegativeTwo {
  public String baseNeg2(int N) {
    if (N == 0) {
      return "0";
    }

    StringBuilder sb = new StringBuilder(64);
    while (N != 0) {
      int mod = N % (-2);
      N /= -2;
      if (mod == -1) {
        sb.append("1");
        ++N;
      } else {
        sb.append(mod);
      }
    }
    return sb.reverse().toString();
  }

  public static void main(String[] args) {
    ConvertToBaseNegativeTwo instance = new ConvertToBaseNegativeTwo();
    System.out.println(instance.baseNeg2(2));
    System.out.println(instance.baseNeg2(3));
    System.out.println(instance.baseNeg2(4));
    System.out.println(instance.baseNeg2(5));
    System.out.println(instance.baseNeg2(6));
  }
}
