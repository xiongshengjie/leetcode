package cn.xcloude.leetcode;

/**
 * leetcode 28
 * 实现 strStr()
 */
public class ImplementStrStr {
  public int strStr(String haystack, String needle) {
    return indexOf(haystack, needle);
  }

  private int indexOf(String content, String pattern) {
    if (pattern.isEmpty()) {
      return 0;
    }

    int[] next = next(pattern);
    int contentIndex = 0, patternIndex = 0;
    while (content.length() - contentIndex >= pattern.length() - patternIndex
        && patternIndex < pattern.length()) {
      if (patternIndex == -1 || content.charAt(contentIndex) == pattern.charAt(patternIndex)) {
        ++contentIndex;
        ++patternIndex;
        if (patternIndex == pattern.length()) {
          return contentIndex - pattern.length();
        }
      } else {
        patternIndex = next[patternIndex];
      }
    }

    return -1;
  }

  /**
   * 求模式串的 next 数组
   *
   * @return -1 代表主串匹配的位置后移一位, 模式串回到 0 位置
   * 其他值代表主串匹配位置不变, 模式串回到该下标
   */
  private int[] next(String pattern) {
    int length = pattern.length(), temp = -1, index = 0;
    int[] next = new int[length];
    next[0] = -1;

    while (index < length - 1) {
      if (temp == -1 || pattern.charAt(temp) == pattern.charAt(index)) {
        if (pattern.charAt(++index) == pattern.charAt(++temp)) {
          next[index] = next[temp];
        } else {
          next[index] = temp;
        }
      } else {
        temp = next[temp];
      }
    }

    return next;
  }
}
