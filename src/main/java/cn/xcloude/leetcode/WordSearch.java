package cn.xcloude.leetcode;

/**
 * leetcode 79
 * 单词搜索
 */
public class WordSearch {
  public boolean exist(char[][] board, String word) {
    boolean[][] pathRecorder = new boolean[board.length][board[0].length];
    for (int index = 0; index < board.length; ++index) {
      char[] chars = board[index];
      for (int inner = 0; inner < chars.length; ++inner) {
        if (check(board, pathRecorder, index, inner, 0, word)) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean check(char[][] board, boolean[][] pathRecorder, int index, int inner,
      int wordIndex, String word) {
    if (index < 0 || inner < 0 || index >= board.length || inner >= board[0].length
        || board[index][inner] != word.charAt(wordIndex) || pathRecorder[index][inner]) {
      return false;
    }

    wordIndex = wordIndex + 1;
    if (word.length() == wordIndex) {
      return true;
    }

    pathRecorder[index][inner] = true;

    if (check(board, pathRecorder, index + 1, inner, wordIndex, word)
        || check(board, pathRecorder, index - 1, inner, wordIndex, word)
        || check(board, pathRecorder, index, inner + 1, wordIndex, word)
        || check(board, pathRecorder, index, inner - 1, wordIndex, word)) {
      return true;
    }

    pathRecorder[index][inner] = false;
    return false;
  }
}
