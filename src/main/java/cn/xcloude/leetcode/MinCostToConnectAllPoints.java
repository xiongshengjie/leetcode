package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * leetcode 1584
 * 连接所有点的最小费用
 */
public class MinCostToConnectAllPoints {
  public int minCostConnectPoints(int[][] points) {
    if (points == null || points.length <= 1) {
      return 0;
    }

    List<Edge> edges = new ArrayList<>(points.length * points.length / 2);
    for (int index = 0; index < points.length; ++index) {
      for (int otherIndex = index + 1; otherIndex < points.length; ++otherIndex) {
        edges.add(new Edge(index, otherIndex, Math.abs(points[index][0] - points[otherIndex][0])
            + Math.abs(points[index][1] - points[otherIndex][1])));
      }
    }
    edges.sort(Comparator.comparing(Edge::getDistance));

    NumberOfProvinces.UnionFind unionFind = new NumberOfProvinces.UnionFind(points.length);
    int result = 0, count = 1;
    for (Edge edge : edges) {
      if (unionFind.union(edge.getFrom(), edge.getTo())) {
        result += edge.getDistance();
        ++count;
        if (count == points.length) {
          break;
        }
      }
    }

    return result;
  }

  private static class Edge {
    /**
     * 起点编号
     */
    private final int from;

    /**
     * 终点编号
     */
    private final int to;

    /**
     * 边长
     */
    private final int distance;

    private Edge(int from, int to, int distance) {
      this.from = from;
      this.to = to;
      this.distance = distance;
    }

    private int getFrom() {
      return from;
    }

    private int getTo() {
      return to;
    }

    private int getDistance() {
      return distance;
    }
  }
}
