package cn.xcloude.leetcode;

/**
 * leetcode 344
 * 反转字符串
 */
public class ReverseString {
  public void reverseString(char[] s) {
    if (s == null || s.length <= 1) {
      return;
    }

    int left = 0, right = s.length - 1;
    while (left < right) {
      char temp = s[left];
      s[left] = s[right];
      s[right] = temp;
      ++left;
      --right;
    }
  }
}
