package cn.xcloude.leetcode;

/**
 * leetcode 434
 * 字符串中的单词数
 */
public class NumberOfSegmentsInAString {
  public int countSegments(String s) {
    if (s == null || s.isEmpty()) {
      return 0;
    }

    int result = 0;
    for (int index = 0; index < s.length(); ++index) {
      if ((index == 0 || s.charAt(index - 1) == ' ') && s.charAt(index) != ' ') {
        ++result;
      }
    }

    return result;
  }
}
