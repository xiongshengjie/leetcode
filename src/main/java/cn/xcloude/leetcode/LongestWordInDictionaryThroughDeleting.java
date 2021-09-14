package cn.xcloude.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * leetcode 524
 * 通过删除字母匹配到字典里最长单词
 */
public class LongestWordInDictionaryThroughDeleting {
  public String findLongestWord(String s, List<String> dictionary) {
    if (s == null || s.isEmpty() || dictionary == null || dictionary.isEmpty()) {
      return "";
    }

    // return findLongestWordDirectly(s, dictionary);
    return findLongestWordDp(s, dictionary);
  }

  private String findLongestWordDp(String s, List<String> dictionary) {
    char[] chars = s.toCharArray();
    int length = chars.length, lengthAddOne = length + 1;
    int[][] dp = new int[lengthAddOne][26];
    Arrays.fill(dp[length], length);

    for (int index = length - 1; index >= 0; --index) {
      for (int charSetIndex = 0; charSetIndex < 26; charSetIndex++) {
        if (chars[index] == 'a' + charSetIndex) {
          dp[index][charSetIndex] = index;
        } else {
          dp[index][charSetIndex] = dp[index + 1][charSetIndex];
        }
      }
    }

    String result = "";
    int fromIndex, index;
    for (String word : dictionary) {
      chars = word.toCharArray();
      fromIndex = 0;
      for (index = 0; index < chars.length; ++index) {
        if ((fromIndex = dp[fromIndex][chars[index] - 'a'] + 1) == lengthAddOne) {
          break;
        }
      }

      if (index == chars.length && (word.length() > result.length()
          || (word.length() == result.length() && word.compareTo(result) < 0))) {
        result = word;
      }
    }
    return result;
  }

  private String findLongestWordDirectly(String s, List<String> dictionary) {
    String result = "";
    int fromIndex, index;
    char[] chars;
    for (String word : dictionary) {
      chars = word.toCharArray();
      fromIndex = 0;
      for (index = 0; index < chars.length; ++index) {
        if ((fromIndex = s.indexOf(chars[index], fromIndex) + 1) == 0) {
          break;
        }
      }

      if (index == chars.length && (word.length() > result.length()
          || (word.length() == result.length() && word.compareTo(result) < 0))) {
        result = word;
      }
    }
    return result;
  }
}
