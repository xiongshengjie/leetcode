package cn.xcloude.leetcode;

import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * leetcode 2276
 * 统计区间中的整数数目
 */
// 珂朵莉树
public class CountIntervals {
  private int count;

  /**
   * key 为区间左端点, value 为区间右端点
   */
  private final TreeMap<Integer, Integer> intervals;

  public CountIntervals() {
    intervals = new TreeMap<>();
  }

  public void add(int left, int right) {
    Entry<Integer, Integer> entry;
    while ((entry = intervals.floorEntry(right)) != null && entry.getValue() >= left) {
      if (entry.getValue() > right) {
        right = entry.getValue();
      }

      if (entry.getKey() < left) {
        left = entry.getKey();
      }

      intervals.remove(entry.getKey());
      count -= (entry.getValue() - entry.getKey() + 1);
    }

    intervals.put(left, right);
    count += (right - left + 1);
  }

  public int count() {
    return count;
  }
}

// 线段树
class CountIntervals0 {
  private final SegmentTreeNode root;

  public CountIntervals0() {
    root = new SegmentTreeNode(1, (int) 1e9);
  }

  public void add(int left, int right) {
    root.add(left, right);
  }

  public int count() {
    return root.count;
  }

  private static class SegmentTreeNode {
    private final int start;
    private final int end;
    private int count;

    private SegmentTreeNode leftNode;
    private SegmentTreeNode rightNode;

    public SegmentTreeNode(int start, int end) {
      this.start = start;
      this.end = end;
    }

    private void add(int left, int right) {
      if (count == end - start + 1) {
        return;
      }
      if (left <= start && right >= end) {
        setCountAndChildren(end - start + 1);
        return;
      }

      int mid = start + ((end - start) >> 2);
      if (leftNode == null) {
        leftNode = new SegmentTreeNode(start, mid);
      }
      if (rightNode == null) {
        rightNode = new SegmentTreeNode(mid + 1, end);
      }

      if (left <= mid) {
        leftNode.add(left, right);
      }
      if (right > mid) {
        rightNode.add(left, right);
      }

      setCountAndChildren(leftNode.count + rightNode.count);
    }

    private void setCountAndChildren(int count) {
      this.count = count;
      // help gc
      if (count == end - start + 1) {
        leftNode = null;
        rightNode = null;
      }
    }
  }
}
