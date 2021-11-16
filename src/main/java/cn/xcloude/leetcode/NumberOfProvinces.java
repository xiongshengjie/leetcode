package cn.xcloude.leetcode;

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

  private static class UnionFind {
    private final int[] parent;

    private UnionFind(int size) {
      this.parent = new int[size];
      for (int index = 0; index < size; ++index) {
        parent[index] = index;
      }
    }

    private int find(int element) {
      while (parent[element] != element) {
        parent[element] = parent[parent[element]];
        element = parent[element];
      }

      return element;
    }

    private void union(int element, int otherElement) {
      int parent = find(element);
      int otherParent = find(otherElement);
      if (parent != otherParent) {
        this.parent[otherParent] = parent;
      }
    }
  }
}
