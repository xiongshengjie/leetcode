package cn.xcloude.leetcode;

/**
 * leetcode 1739
 * 放置盒子
 */
public class BuildingBoxes {
  public int minimumBoxes(int n) {
    int floorCount = 1;
    int currentFloorMaxBoxes = 1;
    int maxBoxes = 0;
    while (maxBoxes + currentFloorMaxBoxes < n) {
      maxBoxes += currentFloorMaxBoxes;

      ++floorCount;
      currentFloorMaxBoxes += floorCount;
    }

    int additionBoxes = 1;
    int additionTouchFloorBoxes = 1;
    while (maxBoxes + additionBoxes < n) {
      ++additionTouchFloorBoxes;
      additionBoxes += additionTouchFloorBoxes;
    }

    return floorCount * (floorCount - 1) / 2 + additionTouchFloorBoxes;
  }
}
