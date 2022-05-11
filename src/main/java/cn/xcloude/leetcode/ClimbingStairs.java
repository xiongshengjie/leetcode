package cn.xcloude.leetcode;

/**
 * leetcode 70
 * 爬楼梯
 */
public class ClimbingStairs {
  public int climbStairs(int n) {
    int next = 1, nextNext = 1;

    for (int index = 2; index <= n; ++index) {
      next += nextNext;
      nextNext = next - nextNext;
    }

    return next;
  }
}
