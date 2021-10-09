package cn.xcloude.leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * leetcode 352
 * 将数据流变为多个不相交区间
 */
public class SummaryRanges {
  private final TreeMap<Integer, Integer> start2End;

  public SummaryRanges() {
    start2End = new TreeMap<>();
  }

  public void addNum(int val) {
    Map.Entry<Integer, Integer> firstGreaterEntry = start2End.ceilingEntry(val + 1);
    Map.Entry<Integer, Integer> lastLowerEntry = start2End.floorEntry(val);
    if (lastLowerEntry != null && lastLowerEntry.getValue() >= val) {
      return;
    }

    boolean linkFirstGreater = firstGreaterEntry != null && firstGreaterEntry.getKey() == val + 1;
    boolean linkLastLower = lastLowerEntry != null && lastLowerEntry.getValue() == val - 1;
    if (linkFirstGreater && linkLastLower) {
      start2End.remove(firstGreaterEntry.getKey());
      start2End.put(lastLowerEntry.getKey(), firstGreaterEntry.getValue());
    } else if (linkFirstGreater) {
      start2End.remove(firstGreaterEntry.getKey());
      start2End.put(val, firstGreaterEntry.getValue());
    } else if (linkLastLower) {
      start2End.put(lastLowerEntry.getKey(), val);
    } else {
      start2End.put(val, val);
    }
  }

  public int[][] getIntervals() {
    int[][] result = new int[start2End.size()][2];
    int index = 0;
    for (Map.Entry<Integer, Integer> entry : start2End.entrySet()) {
      result[index][0] = entry.getKey();
      result[index][1] = entry.getValue();
      ++index;
    }

    return result;
  }
}
