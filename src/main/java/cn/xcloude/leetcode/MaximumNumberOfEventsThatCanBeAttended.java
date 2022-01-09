package cn.xcloude.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * leetcode 1353
 * 最多可以参加的会议数目
 */
public class MaximumNumberOfEventsThatCanBeAttended {
  public int maxEvents(int[][] events) {
    // key 为开始日期, value 为当前开始日期下的所有事件的结束日期
    Map<Integer, List<Integer>> startDay2EndDays = new HashMap<>(events.length);
    // 最大的日期
    int maxDay = 0;
    for (int[] event : events) {
      startDay2EndDays.computeIfAbsent(event[0], e -> new LinkedList<>()).add(event[1]);
      if (event[1] > maxDay) {
        maxDay = event[1];
      }
    }

    // 结束日期最小堆, 每次处理结束日期最早的事件
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    int result = 0;
    for (int day = 1; day <= maxDay; ++day) {
      List<Integer> dayEvents = startDay2EndDays.get(day);
      if (dayEvents != null) {
        minHeap.addAll(dayEvents);
      }

      while (!minHeap.isEmpty()) {
        Integer endDay = minHeap.poll();
        if (endDay >= day) {
          ++result;
          break;
        }
      }
    }

    return result;
  }
}
