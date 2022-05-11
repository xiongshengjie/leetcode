package cn.xcloude.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * leetcode 139
 * 单词拆分
 */
public class WordBreak {
  public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> words = new HashSet<>(wordDict);
    // return wordBreak(s, words, 0, 0, new Boolean[s.length()][s.length() + 1]);
    return wordBreak0(s, words);
  }

  // 动态规划
  private boolean wordBreak0(String s, Set<String> words) {
    // dp[index] 代表前 index 个字符是否能够完全分割为 words 中的单词
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;

    for (int index = 1; index <= s.length(); ++index) {
      for (int dpIndex = index - 1; dpIndex >= 0; --dpIndex) {
        if (dp[dpIndex] && words.contains(s.substring(dpIndex, index))) {
          dp[index] = true;
          break;
        }
      }
    }

    return dp[s.length()];
  }

  // 带备忘录的递归
  private boolean wordBreak(String s, Set<String> words, int index, int length,
      Boolean[][] memorandum) {
    if (index >= s.length()) {
      return words.contains(s.substring(index - length, index));
    }

    if (memorandum[index][length] != null) {
      return memorandum[index][length];
    }

    if (words.contains(s.substring(index - length, index))) {
      if (wordBreak(s, words, index + 1, 1, memorandum)) {
        memorandum[index][length] = true;
        return true;
      }
    }

    memorandum[index][length] = wordBreak(s, words, index + 1, length + 1, memorandum);
    return memorandum[index][length];
  }
}
