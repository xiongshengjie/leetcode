package cn.xcloude.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LC54
 * 不同的二叉搜索树
 */
public class UniqueBinarySearchTrees {
  Map<Integer, Integer> n2Num = new HashMap<>(32);

  public int numTrees(int n) {
    // return numTreesRecursion(n);
    return numTreesDynamic(n);
  }

  // 动态规划
  private int numTreesDynamic(int n) {
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;

    for (int i = 2; i <= n; i++) {
      for (int j = 1; j <= i; j++) {
        dp[i] += dp[j - 1] * dp[i - j];
      }
    }

    return dp[n];
  }

  // 递归
  private int numTreesRecursion(int n) {
    if (n == 0 || n == 1) {
      return 1;
    }

    if (n == 2) {
      return 2;
    }

    Integer num = n2Num.get(n);
    if (num != null) {
      return num;
    }

    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += numTrees(i) * numTrees(n - i - 1);
    }

    n2Num.put(n, sum);
    return sum;
  }

  public static void main(String[] args) {
    UniqueBinarySearchTrees instance = new UniqueBinarySearchTrees();
    System.out.println(instance.numTrees(1));
    System.out.println(instance.numTrees(2));
    System.out.println(instance.numTrees(3));
    System.out.println(instance.numTrees(4));
    System.out.println(instance.numTrees(19));
  }
}
