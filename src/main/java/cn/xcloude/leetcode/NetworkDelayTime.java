package cn.xcloude.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 743
 * 网络延迟时间
 */
public class NetworkDelayTime {
  public int networkDelayTime(int[][] times, int n, int k) {
    // 每个元素代表 k 点到 当前下标 这个点的最短时间
    int[] minTimes = new int[n + 1];
    Arrays.fill(minTimes, Integer.MAX_VALUE);
    minTimes[k] = 0;

    // 初始化邻接链表
    List<int[]>[] edges = new List[n + 1];
    for (int[] edge : times) {
      List<int[]> nodeEdges = edges[edge[0]];
      if (nodeEdges == null) {
        nodeEdges = new LinkedList<>();
        edges[edge[0]] = nodeEdges;
      }
      nodeEdges.add(new int[] {edge[1], edge[2]});
    }

    // Dijkstra 的流程
    // 记录距离已经确定了点
    boolean[] visited = new boolean[n + 1];
    int visitCount = 0, minIndex = k, minTime = 0;
    while (visitCount < n && minTime != Integer.MAX_VALUE) {
      visited[minIndex] = true;
      ++visitCount;

      // 当前点的出边
      List<int[]> currentEdges = edges[minIndex];
      if (currentEdges != null) {
        for (int[] edge : currentEdges) {
          if (minTimes[edge[0]] > edge[1] + minTime) {
            minTimes[edge[0]] = edge[1] + minTime;
          }
        }
      }

      // 找当前状态下, 到哪个未访问过的点的距离最近
      minTime = Integer.MAX_VALUE;
      for (int index = 1; index <= n; ++index) {
        if (!visited[index] && minTimes[index] < minTime) {
          minIndex = index;
          minTime = minTimes[index];
        }
      }
    }

    int result = 0;
    for (int index = 1; index <= n; ++index) {
      if (minTimes[index] == Integer.MAX_VALUE) {
        return -1;
      }

      if (minTimes[index] > result) {
        result = minTimes[index];
      }
    }

    return result;
  }
}
