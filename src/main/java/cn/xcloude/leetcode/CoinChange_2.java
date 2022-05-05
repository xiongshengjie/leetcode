package cn.xcloude.leetcode;

import java.util.Arrays;

/**
 * leetcode 518
 * 零钱兑换 II
 */
public class CoinChange_2 {
  public int change(int amount, int[] coins) {
    // return change2(amount, coins);
    // return change0(amount, coins);
    return change1(amount, coins);
  }

  // 动态规划
  private int change0(int amount, int[] coins) {
    // dp[index][amount] 代表在前 index 个 coin 中, 能够拼出 amount 的组合数
    int[][] dp = new int[coins.length + 1][amount + 1];

    // amount 为 0 时, 不管多少硬币都只有一种选择, 就是一个都不选, 所以都应该初始化为 1
    // 这里只给 dp[0][0] 赋值, 后面的循环会逐渐把其他的 dp[index][0] 填充起来
    dp[0][0] = 1;

    for (int index = 1; index <= coins.length; ++index) {
      for (int currentAmount = 0; currentAmount <= amount; ++currentAmount) {
        if (currentAmount < coins[index - 1]) {
          dp[index][currentAmount] = dp[index - 1][currentAmount];
        } else {
          dp[index][currentAmount] = dp[index - 1][currentAmount]
              + dp[index][currentAmount - coins[index - 1]];
        }
      }
    }

    return dp[coins.length][amount];
  }

  // 动态规划, 优化为一维 dp 数组
  private int change1(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;

    for (int coin : coins) {
      for (int currentAmount = coin; currentAmount <= amount; ++currentAmount) {
        dp[currentAmount] += dp[currentAmount - coin];
      }
    }

    return dp[amount];
  }

  // 带备忘录的递归
  private int change2(int amount, int[] coins) {
    int[][] memorandum = new int[coins.length][amount];
    for (int[] ints : memorandum) {
      Arrays.fill(ints, -1);
    }
    return change(amount, coins, 0, 0, memorandum);
  }

  private int change(int amount, int[] coins, int index, int currentAmount, int[][] memorandum) {
    if (currentAmount == amount) {
      return 1;
    }

    if (currentAmount > amount || index >= coins.length) {
      return 0;
    }

    if (memorandum[index][currentAmount] != -1) {
      return memorandum[index][currentAmount];
    }

    memorandum[index][currentAmount] =
        change(amount, coins, index, currentAmount + coins[index], memorandum)
            + change(amount, coins, index + 1, currentAmount, memorandum);
    return memorandum[index][currentAmount];
  }
}
