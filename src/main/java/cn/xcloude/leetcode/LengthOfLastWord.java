package cn.xcloude.leetcode;

/**
 * leetcode 58
 * 最后一个单词的长度
 */
public class LengthOfLastWord {
  public int lengthOfLastWord(String s) {
    char[] chars = s.toCharArray();
    int temp = 0;

    for (int index = chars.length - 1; index >= 0; --index) {
      if (chars[index] == ' ') {
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
