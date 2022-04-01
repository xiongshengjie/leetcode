package cn.xcloude.leetcode;

/**
 * leetcode 5
 * 最长回文子串
 */
public class LongestPalindromicSubstring {
  public String longestPalindrome(String s) {
    if (s == null || s.length() <= 0) {
      return "";
    }

    int start = 0, end = 0;
    for (int index = 0; index < s.length(); ++index) {
      int longestLength = Math.max(longestPalindrome(s, index, index),
          longestPalindrome(s, index, index + 1));
      if (longestLength > (end - start)) {
        start = index - ((longestLength - 1) >> 1);
        end = index + (longestLength >> 1);
      }
    }

    return s.substring(start, end + 1);
  }

  private int longestPalindrome(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      --left;
      ++right;
    }

    return right - left - 1;
  }
}
