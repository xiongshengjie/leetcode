package cn.xcloude.leetcode;

import java.util.Set;

/**
 * leetcode 36
 * 有效的数独
 */
public class ValidSudoku {
  public boolean isValidSudoku(char[][] board) {
    // Set<Character> set = new HashSet<>();
    // return checkRow(board, set) && checkColumn(board, set) && checkSubBox(board, set);
    return isValidSudoku0(board);
  }

  private boolean isValidSudoku0(char[][] board) {
    int[][] rowChecker = new int[9][9];
    int[][] columnChecker = new int[9][9];
    int[][][] subBoxChecker = new int[3][3][9];

    for (int columnIndex = 0; columnIndex < 9; ++columnIndex) {
      char[] row = board[columnIndex];
      for (int rowIndex = 0; rowIndex < 9; ++rowIndex) {
        char ch = row[rowIndex];
        if (ch != '.') {
          int index = ch - '0' - 1;
          ++rowChecker[columnIndex][index];
          ++columnChecker[rowIndex][index];
          ++subBoxChecker[columnIndex / 3][rowIndex / 3][index];
          if (rowChecker[columnIndex][index] > 1 || columnChecker[rowIndex][index] > 1
              || subBoxChecker[columnIndex / 3][rowIndex / 3][index] > 1) {
            return false;
          }
        }
      }
    }

    return true;
  }

  private boolean checkRow(char[][] board, Set<Character> set) {
    for (char[] row : board) {
      for (char ch : row) {
        if (ch != '.' && !set.add(ch)) {
          return false;
        }
      }
      set.clear();
    }

    return true;
  }

  private boolean checkColumn(char[][] board, Set<Character> set) {
    for (int columnIndex = 0; columnIndex < 9; ++columnIndex) {
      for (char[] row : board) {
        if (row[columnIndex] != '.' && !set.add(row[columnIndex])) {
          return false;
        }
      }
      set.clear();
    }

    return true;
  }

  private boolean checkSubBox(char[][] board, Set<Character> set) {
    for (int rowIndex = 0; rowIndex < 3; ++rowIndex) {
      for (int columnIndex = 0; columnIndex < 3; ++columnIndex) {
        for (int i = 0; i < 3; ++i) {
          for (int j = 0; j < 3; ++j) {
            char ch = board[rowIndex * 3 + i][columnIndex * 3 + j];
            if (ch != '.' && !set.add(ch)) {
              return false;
            }
          }
        }
        set.clear();
      }
    }

    return true;
  }
}
