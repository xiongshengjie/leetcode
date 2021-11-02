package cn.xcloude.leetcode;

/**
 * leetcode 42
 * 接雨水
 */
public class TrappingRainWater {
  public int trap(int[] height) {
    if (height == null || height.length <= 2) {
      return 0;
    }

    int result = 0, left = 0, leftMax = height[0],
        right = height.length - 1, rightMax = height[height.length - 1];
    while (left <= right) {
      if (leftMax < rightMax) {
        if (height[left] < leftMax) {
          result += leftMax - height[left];
        } else {
          leftMax = height[left];
        }
        ++left;
      } else {
        if (height[right] < rightMax) {
          result += rightMax - height[right];
        } else {
          rightMax = height[right];
        }
        --right;
      }
    }

    return result;
  }
}
