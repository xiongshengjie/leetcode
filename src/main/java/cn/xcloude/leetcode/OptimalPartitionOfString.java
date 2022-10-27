package cn.xcloude.leetcode;

/**
 * leetcode 2405
 * 子字符串的最优划分
 */
public class OptimalPartitionOfString {
  public int partitionString(String s) {
    int appeared = 0;
    int result = 1;
    for (int index = 0; index < s.length(); ++index) {
      // 相当于 s.charAt(index) - 'a'
      int bitPosition = s.charAt(index) & 31;
      if (((appeared >> bitPosition) & 1) == 1) {
        ++result;
        appeared = 0;
      }
      appeared |= 1 << bitPosition;
    }

    return result;
  }
}
