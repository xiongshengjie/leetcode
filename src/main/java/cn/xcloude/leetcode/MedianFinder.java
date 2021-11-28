package cn.xcloude.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode 295
 * 数据流的中位数
 */
public class MedianFinder {
  /**
   * 存储流中较小 半部分的数据
   */
  private final PriorityQueue<Integer> maxHeap;

  /**
   * 存储流中较大 半部分的数据
   */
  private final PriorityQueue<Integer> minHeap;

  public MedianFinder() {
    maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    minHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {
    if (minHeap.isEmpty() || minHeap.peek() <= num) {
      minHeap.offer(num);
      if (minHeap.size() - maxHeap.size() >= 2) {
        maxHeap.offer(minHeap.poll());
      }
    } else {
      maxHeap.offer(num);
      if (maxHeap.size() > minHeap.size()) {
        minHeap.offer(maxHeap.poll());
      }
    }
  }

  public double findMedian() {
    Integer min = minHeap.peek();
    if (min == null) {
      return 0;
    }

    if (minHeap.size() == maxHeap.size()) {
      return (min + maxHeap.peek()) / 2d;
    } else {
      return min;
    }
  }
}
