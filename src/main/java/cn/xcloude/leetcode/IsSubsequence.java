package cn.xcloude.leetcode;

/**
 * leetcode 392
 * 判断子序列
 */
public class IsSubsequence {
  public boolean isSubsequence(String s, String t) {
    if (s == null || t == null || t.length() < s.length()) {
      return false;
    }

    int sIndex = 0, tIndex = 0;
    while (sIndex < s.length() && t.length() - tIndex >= s.length() - sIndex) {
      if (s.charAt(sIndex) == t.charAt(tIndex)) {
        ++sIndex;
      }
      ++tIndex;
    }

    return sIndex == s.length();
  }
}
