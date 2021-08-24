package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * LC94
 * 合并区间
 */
public class MergeIntervals {
  private static final Comparator<int[]> COMPARATOR = Comparator.comparing(interval -> interval[0]);

  public int[][] merge(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return new int[0][2];
    }

    Arrays.sort(intervals, COMPARATOR);

    List<int[]> list = new ArrayList<>(intervals.length);
    list.add(intervals[0]);
    for (int index = 1; index < intervals.length; index++) {
      int[] interval = intervals[index];
      int[] processedInterval = list.get(list.size() - 1);
      if (interval[0] > processedInterval[1]) {
        list.add(interval);
      } else {
        processedInterval[1] = Math.max(processedInterval[1], interval[1]);
      }
    }

    return list.toArray(new int[list.size()][]);
  }
}
