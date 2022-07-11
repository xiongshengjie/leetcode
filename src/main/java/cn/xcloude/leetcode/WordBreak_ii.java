package cn.xcloude.leetcode;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * leetcode 140
 * 单词拆分 II
 */
public class WordBreak_ii {
  public List<String> wordBreak(String s, List<String> wordDict) {
    Set<String> words = new HashSet<>(wordDict);
    // List<String> result = new LinkedList<>();
    // wordBreak(s, words, result, new StringBuilder(s.length() + 32), 0, 0);
    // return result;
    return wordBreak(s, words);
  }

  // 动态规划
  private List<String> wordBreak(String s, Set<String> words) {
    // dp[index] 代表前 index 个字符分割为 words 中的单词的所有情况
    List<String>[] dp = new List[s.length() + 1];
    dp[0] = List.of("");

    for (int index = 1; index <= s.length(); ++index) {
      for (int dpIndex = 0; dpIndex < index; ++dpIndex) {
        if (dp[dpIndex] != null) {
          String word = s.substring(dpIndex, index);
          if (words.contains(word)) {
            if (dp[index] == null) {
              dp[index] = new LinkedList<>();
            }

            for (String dpResult : dp[dpIndex]) {
              dp[index].add(dpResult + word + (index == s.length() ? "" : " "));
            }
          }
        }
      }
    }

    return dp[s.length()] == null ? Collections.emptyList() : dp[s.length()];
  }

  // 递归回溯
  private void wordBreak(String s, Set<String> words, List<String> result,
      StringBuilder sb, int index, int length) {
    if (index > s.length()) {
      if (length == 1) {
        result.add(sb.substring(0, sb.length() - 1));
      }
      return;
    }

    String word = s.substring(index - length, index);
    if (words.contains(word)) {
      sb.append(word);
      sb.append(" ");
      wordBreak(s, words, result, sb, index + 1, 1);
      sb.delete(sb.length() - word.length() - 1, sb.length());
    }

    wordBreak(s, words, result, sb, index + 1, length + 1);
  }
}
