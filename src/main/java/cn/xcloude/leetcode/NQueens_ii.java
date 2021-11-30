package cn.xcloude.leetcode;

/**
 * leetcode 52
 * N 皇后 II
 */
public class NQueens_ii {
  public int totalNQueens(int n) {
    return totalNQueens((1 << n) - 1, 0, 0, 0);
  }

  /**
   * 详细说明见 {@link NQueens#solveNQueens(int, int, int, int, int, int[])}
   *
   * @return 可能的结果个数
   */
  private int totalNQueens(int limit, int columnLimit, int leftSlashLimit, int rightSlashLimit) {
    if (columnLimit == limit) {
      return 1;
    }

    // 每个为 1 的 bit 位表示可以放皇后的位置
    int availablePosition = limit & (~(columnLimit | leftSlashLimit | rightSlashLimit));
    // 真正放皇后的位置, 即 position 中只有一个 bit 为 1
    int position, result = 0;
    while (availablePosition != 0) {
      // 取最右边为 1 的 bit 位
      position = availablePosition & (~availablePosition + 1);
      // 去掉最右边的 1
      availablePosition &= (availablePosition - 1);
      result += totalNQueens(limit, columnLimit | position,
          (leftSlashLimit | position) << 1, (rightSlashLimit | position) >>> 1);
    }

    return result;
  }
}
