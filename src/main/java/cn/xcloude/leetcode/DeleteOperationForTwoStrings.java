package cn.xcloude.leetcode;

/**
 * leetcode 583
 * 两个字符串的删除操作
 */
public class DeleteOperationForTwoStrings {
  public int minDistance(String word1, String word2) {
    boolean word1NullOrEmpty = word1 == null || word1.isEmpty();
    boolean word2NullOrEmpty = word2 == null || word2.isEmpty();
    if (word1NullOrEmpty && word2NullOrEmpty) {
      return 0;
    }

    if (word1NullOrEmpty) {
      return word2.length();
    }

    if (word2NullOrEmpty) {
      return word1.length();
    }

    int length = longestCommonSequenceLength(word1, word2);
    return word1.length() + word2.length() - (length << 1);
  }

  private int longestCommonSequenceLength(String word, String otherWord) {
    char[] chars = word.toCharArray();
    char[] otherChars = otherWord.toCharArray();

    int[][] dp = new int[chars.length + 1][otherChars.length + 1];
    for (int index = 1; index <= chars.length; ++index) {
      for (int otherIndex = 1; otherIndex <= otherChars.length; ++otherIndex) {
        if (chars[index - 1] == otherChars[otherIndex - 1]) {
          dp[index][otherIndex] = dp[index - 1][otherIndex - 1] + 1;
        } else {
          dp[index][otherIndex] = Math.max(dp[index][otherIndex - 1], dp[index - 1][otherIndex]);
        }
      }
    }

    return dp[word.length()][otherWord.length()];
  }

  public static void main(String[] args) {
    new DeleteOperationForTwoStrings().minDistance("eat", "sea");
  }
}
