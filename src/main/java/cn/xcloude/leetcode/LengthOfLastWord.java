package cn.xcloude.leetcode;

/**
 * leetcode 58
 * 最后一个单词的长度
 */
public class LengthOfLastWord {
  public int lengthOfLastWord(String s) {
    int temp = 0;
    for (int index = s.length() - 1; index >= 0; --index) {
      if (s.charAt(index) == ' ') {
        if (temp > 0) {
          return temp;
        }
      } else {
        ++temp;
      }
    }

    return temp;
  }
}
