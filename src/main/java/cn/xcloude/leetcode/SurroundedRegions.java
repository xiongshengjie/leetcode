package cn.xcloude.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LC21
 * leetcode 130
 * 被包围的区域
 */
public class SurroundedRegions {
  public void solve(char[][] board) {
    int weight = board.length;
    if (weight <= 0) {
      return;
    }

    int length = board[0].length;
    // depthFirstSearch(board, length, weight);
    breadthFirstSearch(board, length, weight);
  }

  private void breadthFirstSearch(char[][] board, int length, int weight) {
    Queue<int[]> queue = new LinkedList<>();
    int index;
    for (index = 0; index < length; ++index) {
      if (board[0][index] == 'O') {
        board[0][index] = 'A';
        queue.offer(new int[] {0, index});
      }
      if (board[weight - 1][index] == 'O') {
        board[weight - 1][index] = 'A';
        queue.offer(new int[] {weight - 1, index});
      }
    }

    for (index = 1; index < weight - 1; ++index) {
      if (board[index][0] == 'O') {
        board[index][0] = 'A';
        queue.offer(new int[] {index, 0});
      }
      if (board[index][length - 1] == 'O') {
        board[index][length - 1] = 'A';
        queue.offer(new int[] {index, length - 1});
      }
    }

    int x, y;
    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      markUnSurroundedCell(board, cell[0] - 1, cell[1], length, weight, queue);
      markUnSurroundedCell(board, cell[0] + 1, cell[1], length, weight, queue);
      markUnSurroundedCell(board, cell[0], cell[1] - 1, length, weight, queue);
      markUnSurroundedCell(board, cell[0], cell[1] + 1, length, weight, queue);
    }

    captureRegions(board, length, weight);
  }

  private void markUnSurroundedCell(char[][] board, int x, int y,
      int length, int weight, Queue<int[]> queue) {
    if (x < 0 || x >= weight || y < 0 || y >= length || board[x][y] != 'O') {
      return;
    }
    board[x][y] = 'A';
    queue.offer(new int[] {x, y});
  }

  private void depthFirstSearch(char[][] board, int length, int weight) {
    int index;
    for (index = 0; index < length; ++index) {
      markUnSurroundedCell(board, index, 0, length, weight);
      markUnSurroundedCell(board, index, weight - 1, length, weight);
    }

    for (index = 1; index < weight - 1; ++index) {
      markUnSurroundedCell(board, 0, index, length, weight);
      markUnSurroundedCell(board, length - 1, index, length, weight);
    }

    captureRegions(board, length, weight);
  }

  private void captureRegions(char[][] board, int length, int weight) {
    for (int i = 0; i < weight; i++) {
      for (int j = 0; j < length; j++) {
        if (board[i][j] == 'A') {
          board[i][j] = 'O';
        } else if (board[i][j] == 'O') {
          board[i][j] = 'X';
        }
      }
    }
  }

  /**
   * 深度优先遍历标记
   *
   * @param board  二维数组
   * @param x      x 轴方向坐标
   * @param y      y 轴方向坐标
   * @param length 二维数组长度
   * @param weight 二维数组宽度
   */
  private void markUnSurroundedCell(char[][] board, int x, int y, int length, int weight) {
    if (x < 0 || x >= length || y < 0 || y >= weight || board[y][x] != 'O') {
      return;
    }

    board[y][x] = 'A';
    markUnSurroundedCell(board, x + 1, y, length, weight);
    markUnSurroundedCell(board, x - 1, y, length, weight);
    markUnSurroundedCell(board, x, y + 1, length, weight);
    markUnSurroundedCell(board, x, y - 1, length, weight);
  }
}
