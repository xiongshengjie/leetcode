package cn.xcloude.leetcode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * leetcode 1514
 * 概率最大的路径
 */
public class PathWithMaximumProbability {
  public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
    double[] maxProbabilityResult = new double[n];
    maxProbabilityResult[start] = 1;
    List<Edge>[] node2Edges = new List[n];

    for (int index = 0; index < edges.length; ++index) {
      addEdge(edges[index][0], new Edge(edges[index][1], succProb[index]), node2Edges);
      addEdge(edges[index][1], new Edge(edges[index][0], succProb[index]), node2Edges);
    }

    // 遍历实现
    // dijkstra(n, start, end, maxProbabilityResult, node2Edges);
    // 堆实现
    // dijkstraHeap(start, end, maxProbabilityResult, node2Edges);
    // 线段树实现
    dijkstraSegmentTree(start, end, maxProbabilityResult, node2Edges);

    return maxProbabilityResult[end];
  }

  private void dijkstraSegmentTree(int start, int end, double[] maxProbabilityResult,
      List<Edge>[] node2Edges) {
    int length = maxProbabilityResult.length;
    // 线段树, 值代表当前这些点(构成了线段)上概率最大的点
    int[] segmentTree = new int[length << 2];
    initTree(0, 0, length - 1, segmentTree, maxProbabilityResult);

    while (start != end && maxProbabilityResult[start] > 0) {
      double nodeProbability = maxProbabilityResult[start];
      // -1 代表该节点已访问过, 即概率已经最大, 后续不需要更新
      maxProbabilityResult[start] = -1;
      updateTree(start, 0, 0, length - 1, segmentTree, maxProbabilityResult);
      List<Edge> nodeEdges = node2Edges[start];
      if (nodeEdges != null) {
        for (Edge edge : nodeEdges) {
          double probability = edge.probability * nodeProbability;
          if (maxProbabilityResult[edge.to] < probability && maxProbabilityResult[edge.to] != -1) {
            maxProbabilityResult[edge.to] = probability;
            updateTree(edge.to, 0, 0, length - 1, segmentTree, maxProbabilityResult);
          }
        }
      }

      start = segmentTree[0];
    }
  }

  private void initTree(int treeIndex, int left, int right, int[] segmentTree,
      double[] maxProbabilityResult) {
    if (left == right) {
      segmentTree[treeIndex] = left;
      return;
    }

    int mid = left + ((right - left) >> 1);
    int leftIndex = (treeIndex << 1) + 1;
    initTree(leftIndex, left, mid, segmentTree, maxProbabilityResult);
    int rightIndex = (treeIndex << 1) + 2;
    initTree(rightIndex, mid + 1, right, segmentTree, maxProbabilityResult);
    segmentTree[treeIndex] =
        maxProbabilityResult[segmentTree[leftIndex]] > maxProbabilityResult[segmentTree[rightIndex]]
            ? segmentTree[leftIndex] : segmentTree[rightIndex];
  }

  private void updateTree(int node, int treeIndex, int left, int right, int[] segmentTree,
      double[] maxProbabilityResult) {
    if (left == right) {
      return;
    }

    int mid = left + ((right - left) >> 1);
    int leftIndex = (treeIndex << 1) + 1;
    int rightIndex = (treeIndex << 1) + 2;
    if (node <= mid) {
      updateTree(node, leftIndex, left, mid, segmentTree, maxProbabilityResult);
    } else {
      updateTree(node, rightIndex, mid + 1, right, segmentTree, maxProbabilityResult);
    }

    segmentTree[treeIndex] =
        maxProbabilityResult[segmentTree[leftIndex]] > maxProbabilityResult[segmentTree[rightIndex]]
            ? segmentTree[leftIndex] : segmentTree[rightIndex];
  }

  private void dijkstraHeap(int start, int end, double[] maxProbabilityResult,
      List<Edge>[] node2Edges) {
    PriorityQueue<Edge> heap = new PriorityQueue<>(
        Comparator.comparing(e -> e.probability, Comparator.reverseOrder()));
    heap.add(new Edge(start, 1));
    while (!heap.isEmpty()) {
      Edge node = heap.poll();
      if (node.to == end) {
        break;
      }

      if (node.probability < maxProbabilityResult[node.to]) {
        continue;
      }

      List<Edge> nodeEdges = node2Edges[node.to];
      if (nodeEdges != null) {
        for (Edge edge : nodeEdges) {
          double probability = edge.probability * node.probability;
          if (maxProbabilityResult[edge.to] < probability) {
            maxProbabilityResult[edge.to] = probability;
            heap.add(new Edge(edge.to, probability));
          }
        }
      }
    }
  }

  private void dijkstra(int n, int start, int end,
      double[] maxProbabilityResult, List<Edge>[] node2Edge) {
    boolean[] visited = new boolean[n];
    int visitCount = 0;
    double maxProbability = 1;
    while (visitCount < n && maxProbability > 0 && start != end) {
      visited[start] = true;
      ++visitCount;
      List<Edge> nodeEdges = node2Edge[start];
      if (nodeEdges != null) {
        for (Edge edge : nodeEdges) {
          double probability = edge.probability * maxProbability;
          if (!visited[edge.to] && maxProbabilityResult[edge.to] < probability) {
            maxProbabilityResult[edge.to] = probability;
          }
        }
      }

      maxProbability = 0;
      for (int index = 0; index < n; ++index) {
        if (!visited[index] && maxProbabilityResult[index] > maxProbability) {
          maxProbability = maxProbabilityResult[index];
          start = index;
        }
      }
    }
  }

  private void addEdge(int node, Edge e, List<Edge>[] node2Edges) {
    List<Edge> edges = node2Edges[node];
    if (edges == null) {
      edges = new LinkedList<>();
      node2Edges[node] = edges;
    }
    edges.add(e);
  }

  private static final class Edge {
    private final int to;
    private final double probability;

    private Edge(int to, double probability) {
      this.to = to;
      this.probability = probability;
    }
  }

  public static void main(String[] args) {
    int[][] edges = {{0, 3}, {1, 7}, {1, 2}, {0, 9}};
    double[] pro = {0.31, 0.9, 0.86, 0.36};
    new PathWithMaximumProbability().maxProbability(10, edges, pro, 2, 3);
  }
}
