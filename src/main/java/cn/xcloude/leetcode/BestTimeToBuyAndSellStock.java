package cn.xcloude.leetcode;

/**
 * LC30
 * leetcode 121
 * 买卖股票的最佳时机
 */
public class BestTimeToBuyAndSellStock {
  public int maxProfit(int[] prices) {
    int result = 0, min = prices[0];
    for (int price : prices) {
      if (price < min) {
        min = price;
      } else {
        result = Math.max(result, price - min);
      }
    }
    return result;
  }
}
