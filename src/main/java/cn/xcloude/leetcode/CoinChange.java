package cn.xcloude.leetcode;

import java.util.Arrays;

/**
 * leetcode 322
 * 零钱兑换
 */
public class CoinChange {
  public int coinChange(int[] coins, int amount) {
    // return coinChange(coins, amount, 0, 0, new int[coins.length][amount]);
    // return coinChange0(coins, amount);
    // return coinChange1(coins, amount);
    return coinChangeGreedy(coins, amount);
  }

  // 带备忘录的贪心算法
  private int coinChangeGreedy(int[] coins, int amount) {
    Arrays.sort(coins);
    return coinChangeGreedy(coins, amount, coins.length - 1, 0, new int[coins.length][amount]);
  }

  private int coinChangeGreedy(int[] coins, int amount, int index,
      int currentAmount, int[][] memorandum) {
    if (currentAmount == amount) {
      return 0;
    }

    if (currentAmount > amount || index < 0) {
      return -1;
    }

    if (memorandum[index][currentAmount] != 0) {
      return memorandum[index][currentAmount];
    }

    int result = Integer.MAX_VALUE;
    for (int count = (amount - currentAmount) / coins[index]; count >= 0; --count) {
      int temp = coinChangeGreedy(coins, amount, index - 1,
          currentAmount + count * coins[index], memorandum);
      if (temp > -1 && temp + count < result) {
        result = temp + count;
      }
    }

    memorandum[index][currentAmount] = result == Integer.MAX_VALUE ? -1 : result;
    return memorandum[index][currentAmount];
  }

  private int coinChange0(int[] coins, int amount) {
    // dp[index][amount] 代表在前 index 个 coin 中, 能够拼出 amount 的组合中 coin 数量最少的个数
    int[][] dp = new int[coins.length + 1][amount + 1];
    Arrays.fill(dp[0], 1, amount + 1, -1);

    for (int index = 1; index <= coins.length; ++index) {
      for (int currentAmount = 0; currentAmount <= amount; ++currentAmount) {
        if (currentAmount < coins[index - 1]) {
          dp[index][currentAmount] = dp[index - 1][currentAmount];
        } else {
          int notPickResult = dp[index - 1][currentAmount];
          int pickResult = dp[index][currentAmount - coins[index - 1]];
          if (notPickResult == -1 && pickResult == -1) {
            dp[index][currentAmount] = -1;
          } else if (notPickResult == -1) {
            dp[index][currentAmount] = pickResult + 1;
          } else if (pickResult == -1) {
            dp[index][currentAmount] = notPickResult;
          } else {
            dp[index][currentAmount] = Math.min(notPickResult, pickResult + 1);
          }
        }
      }
    }

    return dp[coins.length][amount];
  }

  // 动态规划, 优化为一维 dp 数组
  private int coinChange1(int[] coins, int amount) {
    // dp[amount] 代表当前这些 coins 中组成 amount 时的最小 coin 数量
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, 1, amount + 1, -1);

    for (int index = 1; index <= coins.length; ++index) {
      for (int currentAmount = coins[index - 1]; currentAmount <= amount; ++currentAmount) {
        int notPickResult = dp[currentAmount];
        int pickResult = dp[currentAmount - coins[index - 1]];
        if (notPickResult == -1 && pickResult == -1) {
          dp[currentAmount] = -1;
        } else if (notPickResult == -1) {
          dp[currentAmount] = pickResult + 1;
        } else if (pickResult == -1) {
          dp[currentAmount] = notPickResult;
        } else {
          dp[currentAmount] = Math.min(notPickResult, pickResult + 1);
        }
      }
    }

    return dp[amount];
  }

  // 带备忘录的递归
  private int coinChange(int[] coins, int amount, int index,
      int currentAmount, int[][] memorandum) {
    if (currentAmount == amount) {
      return 0;
    }

    if (currentAmount < 0 || currentAmount > amount || index >= coins.length) {
      return -1;
    }

    if (memorandum[index][currentAmount] != 0) {
      return memorandum[index][currentAmount];
    }

    int notPickResult = coinChange(coins, amount, index + 1, currentAmount, memorandum);
    int pickResult = coinChange(coins, amount, index, currentAmount + coins[index], memorandum);
    if (notPickResult == -1 && pickResult == -1) {
      memorandum[index][currentAmount] = -1;
    } else if (notPickResult == -1) {
      memorandum[index][currentAmount] = pickResult + 1;
    } else if (pickResult == -1) {
      memorandum[index][currentAmount] = notPickResult;
    } else {
      memorandum[index][currentAmount] = Math.min(pickResult + 1, notPickResult);
    }

    return memorandum[index][currentAmount];
  }
}
