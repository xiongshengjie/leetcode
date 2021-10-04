package cn.xcloude.leetcode;

/**
 * leetcode 223
 * 矩形面积
 */
public class RectangleArea {
  public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
    int areaA = (ax2 - ax1) * (ay2 - ay1);
    int areaB = (bx2 - bx1) * (by2 - by1);

    int overlapWidth = Math.min(ay2, by2) - Math.max(ay1, by1);
    if (overlapWidth <= 0) {
      return areaA + areaB;
    }

    int overlapLength = Math.min(ax2, bx2) - Math.max(bx1, ax1);
    if (overlapLength <= 0) {
      return areaA + areaB;
    }

    return areaA + areaB - overlapLength * overlapWidth;
  }
}
