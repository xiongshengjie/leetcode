package cn.xcloude.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * leetcode 120
 * 三角形最小路径和
 */
public class Triangle {
  public int minimumTotal(List<List<Integer>> triangle) {
    return minimumTotal2(triangle);
    // return minimumTotal0(triangle);
    // return minimumTotal1(triangle);
  }

  // 动态规划
  private int minimumTotal0(List<List<Integer>> triangle) {
    int size = triangle.size();
    // dp[floor][index] 代表第 floor 层的第 index 个元素为顶点的三角形的最小路径和
    int[][] dp = new int[size][size];
    List<Integer> lastFloors = triangle.get(size - 1);
    for (int index = 0; index < size; ++index) {
      dp[size - 1][index] = lastFloors.get(index);
    }

    for (int floor = size - 2; floor >= 0; --floor) {
      List<Integer> floors = triangle.get(floor);
      for (int index = 0; index <= floor; ++index) {
        dp[floor][index] =
            Math.min(dp[floor + 1][index], dp[floor + 1][index + 1]) + floors.get(index);
      }
    }

    return dp[0][0];
  }

  // 动态规划, 优化为一维数组
  private int minimumTotal2(List<List<Integer>> triangle) {
    int size = triangle.size();
    // dp[index] 代表当前层的第 index 个元素为顶点的三角形的最小路径和
    int[] dp = new int[size];
    List<Integer> lastFloors = triangle.get(size - 1);
    for (int index = 0; index < size; ++index) {
      dp[index] = lastFloors.get(index);
    }

    for (int floor = size - 2; floor >= 0; --floor) {
      List<Integer> floors = triangle.get(floor);
      for (int index = 0; index <= floor; ++index) {
        dp[index] = Math.min(dp[index], dp[index + 1]) + floors.get(index);
      }
    }

    return dp[0];
  }

  // 带备忘录的递归
  private int minimumTotal1(List<List<Integer>> triangle) {
    int[][] memorandum = new int[triangle.size()][triangle.size()];
    for (int[] ints : memorandum) {
      Arrays.fill(ints, Integer.MAX_VALUE);
    }
    return minimumTotal1(triangle, 0, 0, memorandum);
  }

  private int minimumTotal1(List<List<Integer>> triangle, int floor, int index,
      int[][] memorandum) {
    if (floor >= triangle.size()) {
      return 0;
    }

    if (memorandum[floor][index] != Integer.MAX_VALUE) {
      return memorandum[floor][index];
    }

    int min = Math.min(minimumTotal1(triangle, floor + 1, index, memorandum),
        minimumTotal1(triangle, floor + 1, index + 1, memorandum))
        + triangle.get(floor).get(index);
    memorandum[floor][index] = min;
    return min;
  }
}
