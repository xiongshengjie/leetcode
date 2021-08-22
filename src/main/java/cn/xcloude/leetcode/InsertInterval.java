package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LC93
 * 插入区间
 */
public class InsertInterval {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    if (intervals.length == 0) {
      intervals = new int[1][newInterval.length];
      intervals[0] = newInterval;
      return intervals;
    }

    // 二分查找找 start 和 end 分别应该在哪个位置
    int startIndex = binarySearch(intervals, newInterval[0], 0);
    int endIndex = binarySearch(intervals, newInterval[1], 1);

    List<int[]> temp = new ArrayList<>(intervals.length + 1);
    int index = 0;
    // startIndex 之前的直接放到结果集
    while (index < startIndex) {
      temp.add(intervals[index++]);
    }

    // 找交集的下限
    int left = intervals[index][0];
    if (startIndex < 0) {
      left = newInterval[0];
    } else if (intervals[startIndex][1] < newInterval[0]) {
      temp.add(intervals[startIndex]);
      left = newInterval[0];
    }

    // 找交集的上限
    int right;
    if (endIndex + 1 >= intervals.length || intervals[endIndex + 1][0] > newInterval[1]) {
      right = newInterval[1];
      index = endIndex + 1;
    } else {
      right = intervals[endIndex + 1][1];
      index = endIndex + 2;
    }
    temp.add(new int[] {left, right});

    // 大于 endIndex 的直接加到结果集
    while (index < intervals.length) {
      temp.add(intervals[index++]);
    }

    int[][] result = new int[temp.size()][2];
    for (int i = 0; i < temp.size(); i++) {
      result[i] = temp.get(i);
    }

    return result;
  }

  /**
   * 二分查找
   *
   * @return 如果没找到, 返回的是最后一个比 target 小的位置
   */
  private int binarySearch(int[][] intervals, int target, int limit) {
    int start = 0, end = intervals.length - 1, middle = 0, middleNum;
    while (start <= end) {
      middle = (start + end) >> 1;
      middleNum = intervals[middle][limit];
      if (middleNum == target) {
        break;
      } else if (middleNum > target) {
        end = middle - 1;
      } else {
        start = middle + 1;
      }
    }

    if (intervals[middle][limit] > target) {
      return middle - 1;
    }
    return middle;
  }

  public static void main(String[] args) {
    int[][] intervals = new int[][] {{1, 5}};
    int[] interval = new int[] {0, 3};
    new InsertInterval().insert(intervals, interval);
  }
}
