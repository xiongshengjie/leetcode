package cn.xcloude.leetcode;

/**
 * leetcode 405
 * 数字转换为十六进制数
 */
public class ConvertANumberToHexadecimal {
  public String toHex(int num) {
    if (num == 0) {
      return "0";
    }

    StringBuilder sb = new StringBuilder(8);
    for (int hexIndex = 7; hexIndex >= 0; --hexIndex) {
      int value = (num >> (4 * hexIndex)) & 0xf;
      if (sb.length() > 0 || value > 0) {
        if (value < 10) {
          sb.append(value);
        } else {
          sb.append((char) ('a' + value - 10));
        }
      }
    }

    return sb.toString();
  }
}
