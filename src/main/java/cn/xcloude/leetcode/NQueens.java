package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 51
 * N 皇后
 */
public class NQueens {
  private final List<List<String>> result = new LinkedList<>();
  private final StringBuilder sb = new StringBuilder(10);
  private char[] padding;

  public List<List<String>> solveNQueens(int n) {
    int[] recorder = new int[n];
    Arrays.fill(recorder, -1);
    padding = new char[n];
    Arrays.fill(padding, '.');

    // solveNQueens(n, 0, recorder);
    solveNQueens(0, (1 << n) - 1, 0, 0, 0, recorder);

    return result;
  }

  /**
   * 位运算实现, 只支持 32 皇后以下
   *
   * @param row             当前处理的行
   * @param limit           N 皇后限制, 代表哪些 bit 位可以放皇后, 如 N = 2 时, limit = 000....11
   * @param columnLimit     列限制, 前面的行已经放过皇后的列, 后续的行不能再放皇后
   * @param leftSlashLimit  左斜线限制
   * @param rightSlashLimit 右斜线限制
   * @param recorder        solution 记录
   */
  private void solveNQueens(int row, int limit, int columnLimit, int leftSlashLimit,
      int rightSlashLimit, int[] recorder) {
    if (columnLimit == limit) {
      result.add(generateOneSolution(recorder));
      return;
    }

    // 每个为 1 的 bit 位表示可以放皇后的位置
    int availablePosition = limit & (~(columnLimit | leftSlashLimit | rightSlashLimit));
    // 真正放皇后的位置, 即 position 中只有一个 bit 为 1
    int position;
    while (availablePosition != 0) {
      // 取最右边为 1 的 bit 位
      position = availablePosition & (~availablePosition + 1);
      // 去掉最右边的 1
      availablePosition &= (availablePosition - 1);
      recorder[row] = Integer.bitCount(position - 1);
      solveNQueens(row + 1, limit, columnLimit | position, (leftSlashLimit | position) << 1,
          (rightSlashLimit | position) >>> 1, recorder);
      recorder[row] = -1;
    }
  }

  // 常规递归
  private void solveNQueens(int n, int row, int[] recorder) {
    if (row == n) {
      result.add(generateOneSolution(recorder));
      return;
    }

    for (int column = 0; column < n; ++column) {
      if (validCell(row, column, recorder)) {
        recorder[row] = column;
        solveNQueens(n, row + 1, recorder);
        recorder[row] = -1;
      }
    }
  }

  private boolean validCell(int row, int column, int[] recorder) {
    for (int rowIndex = 0; rowIndex < row; ++rowIndex) {
      if (column == recorder[rowIndex]
          || Math.abs(rowIndex - row) == Math.abs(column - recorder[rowIndex])) {
        return false;
      }
    }

    return true;
  }

  private List<String> generateOneSolution(int[] recorder) {
    List<String> solution = new ArrayList<>(recorder.length);
    for (int column : recorder) {
      sb.append(padding);
      sb.setCharAt(column, 'Q');
      solution.add(sb.toString());
      sb.setLength(0);
    }

    return solution;
  }
}
