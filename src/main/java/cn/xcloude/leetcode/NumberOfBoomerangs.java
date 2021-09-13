package cn.xcloude.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 447
 * 回旋镖的数量
 */
public class NumberOfBoomerangs {
  public int numberOfBoomerangs(int[][] points) {
    int result = 0;
    Map<Integer, Integer> distance2Count = new HashMap<>();
    for (int[] point : points) {
      for (int[] otherPoint : points) {
        int xDistance = point[0] - otherPoint[0];
        int yDistance = point[1] - otherPoint[1];
        int distance = xDistance * xDistance + yDistance * yDistance;
        distance2Count.put(distance, distance2Count.getOrDefault(distance, 0) + 1);
      }

      for (Integer value : distance2Count.values()) {
        result += value * (value - 1);
      }
      distance2Count.clear();
    }

    return result;
  }
}
