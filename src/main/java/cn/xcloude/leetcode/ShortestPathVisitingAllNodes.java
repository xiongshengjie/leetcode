package cn.xcloude.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode 847
 * 访问所有节点的最短路径
 */
public class ShortestPathVisitingAllNodes {
  public int shortestPathLength(int[][] graph) {
    int length = graph.length;
    int maxMaskLength = 1 << length;
    int allVisitedMask = (maxMaskLength) - 1;
    boolean[][] seen = new boolean[length][maxMaskLength];
    Queue<int[]> queue = new LinkedList<>();

    for (int node = 0; node < length; ++node) {
      int mask = 1 << node;
      queue.offer(new int[] {node, mask, 0});
      seen[node][mask] = true;
    }

    while (!queue.isEmpty()) {
      int[] tuple = queue.poll();
      int mask = tuple[1];
      int distance = tuple[2];
      if (mask == allVisitedMask) {
        return distance;
      }

      for (int neighbor : graph[tuple[0]]) {
        int neighborMask = mask | (1 << neighbor);
        if (!seen[neighbor][neighborMask]) {
          seen[neighbor][neighborMask] = true;
          queue.offer(new int[] {neighbor, neighborMask, distance + 1});
        }
      }
    }

    return 0;
  }
}
