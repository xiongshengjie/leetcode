package cn.xcloude.leetcode;

/**
 * leetcode 292
 * Nim 游戏
 */
public class NimGame {
  public boolean canWinNim(int n) {
    // return n % 4 != 0;
    return (n & 3) != 0;
  }
}
