package cn.xcloude.leetcode.JumpGame_ii;

public class JumpGame_ii {
  public int jump(int[] A) {
    int furthest_cur = 0;
    int furthest_pre = 0;
    int steps = 0;
    for (int i = 0; i < A.length; i++) {
      if (furthest_pre >= A.length) {
        return steps;
      }
      if (i > furthest_pre) {
        furthest_pre = furthest_cur;
        steps++;
      }
      furthest_cur = Math.max(furthest_cur, A[i] + i);
    }
    return steps;
  }

  public static void main(String[] args) {
    System.out.println(new JumpGame_ii().jump(new int[] {4, 1, 6, 4, 4, 4, 3, 2, 8, 1}));
  }
}
