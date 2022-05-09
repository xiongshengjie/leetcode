package cn.xcloude.leetcode;

/**
 * leetcode 1049
 * 最后一块石头的重量 II
 */
public class LastStoneWeight_ii {
  public int lastStoneWeightII(int[] stones) {
    int sum = 0;
    for (int stone : stones) {
      sum += stone;
    }
    int half = sum >> 1;

    // 滚动数组, dp[index][weight] 代表前 index 个石头中不超过 weight 的最大重量
    int[] dp = new int[half + 1];
    for (int stone : stones) {
      for (int weight = half; weight >= stone; --weight) {
        dp[weight] = Math.max(dp[weight], dp[weight - stone] + stone);
      }
    }

    return sum - (dp[half] << 1);
  }
}
