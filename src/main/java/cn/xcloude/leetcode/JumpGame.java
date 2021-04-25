package cn.xcloude.leetcode;

public class JumpGame {
  public boolean canJump(int[] A) {
    int maxReach = 0;
    for (int i = 0; i < A.length && i <= maxReach; i++) {
      maxReach = Math.max(maxReach, i + A[i]);
    }
    if (maxReach < A.length - 1) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    new JumpGame().canJump(new int[]{3,3,1,0,4});
  }
}
