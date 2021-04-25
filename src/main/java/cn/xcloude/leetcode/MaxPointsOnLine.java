package cn.xcloude.leetcode;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnLine {
  public int maxPoints(Point[] points) {
    if (points == null) {
      return 0;
    }

    int length = points.length;
    if (length <= 2) {
      return length;
    }

    Map<String, Integer> rate2Num = new HashMap<>();
    int result = 0;
    for (int i = 0; i < points.length; i++) {
      rate2Num.clear();
      int duplicateNum = 1;
      int verticalNum = 0;
      int max = 0;
      for (int j = i + 1; j < points.length; j++) {
        int xSub = points[i].x - points[j].x;
        int ySub = points[i].y - points[j].y;
        if (xSub == 0) {
          if (ySub == 0) {
            duplicateNum++;
            continue;
          }
        }
        int div = calCommonDiv(xSub, ySub);
        String key = xSub / div + ":" + ySub / div;
        int num = rate2Num.getOrDefault(key, 0) + 1;
        rate2Num.put(key, num);
        max = Math.max(max, num);
      }
      result = Math.max(result, max + duplicateNum + verticalNum);
    }
    return result;
  }

  private int calCommonDiv(int a, int b) {
    return (b == 0) ? a : calCommonDiv(b, a % b);
  }
}
