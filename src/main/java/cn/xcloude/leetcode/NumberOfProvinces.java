package cn.xcloude.leetcode;

import java.util.Arrays;

/**
 * leetcode 547
 * 省份数量
 */
public class NumberOfProvinces {
  public int findCircleNum(int[][] isConnected) {
    if (isConnected == null || isConnected.length == 0) {
      return 0;
    }

    UnionFind unionFind = new UnionFind(isConnected.length);
    for (int index = 0; index < isConnected.length; ++index) {
      int[] cities = isConnected[index];
      for (int innerIndex = index + 1; innerIndex < cities.length; ++innerIndex) {
        if (cities[innerIndex] == 1) {
          unionFind.union(index, innerIndex);
        }
      }
    }

    int result = 0;
    for (int index = 0; index < isConnected.length; ++index) {
      if (index == unionFind.find(index)) {
        ++result;
      }
    }

    return result;
  }

  static class UnionFind {
    private final int[] parent;
    private final int[] weight;

    UnionFind(int size) {
      this.parent = new int[size];
      this.weight = new int[size];
      Arrays.fill(weight, 1);
      for (int index = 0; index < size; ++index) {
        parent[index] = index;
      }
    }

    int find(int element) {
      int root = element;
      while (parent[root] != root) {
        root = parent[root];
      }

      while (parent[element] != element) {
        int temp = parent[element];
        parent[element] = root;
        element = temp;
      }

      return root;
    }

    boolean union(int element, int otherElement) {
      int parent = find(element);
      int otherParent = find(otherElement);
      if (parent == otherParent) {
        return false;
      }

      if (weight[parent] < weight[otherParent]) {
        this.parent[parent] = otherParent;
        weight[parent] += weight[otherParent];
      } else {
        this.parent[otherParent] = parent;
        weight[otherElement] += weight[parent];
      }
      return true;
    }
  }
}
