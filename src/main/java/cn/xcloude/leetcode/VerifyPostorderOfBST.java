package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 剑指 offer 33
 * nowcoder JZ33
 * 二叉搜索树的后序遍历序列
 */
public class VerifyPostorderOfBST {
  public boolean verifyPostorder(int[] postorder) {
    if (postorder == null || postorder.length == 0) {
      return true;
    }

    // return verifyPostorderRecursion(postorder, 0, postorder.length - 1);
    return verifyPostorderMonotoneStack(postorder);
  }

  // 递归
  private boolean verifyPostorderRecursion(int[] postorder, int start, int end) {
    if (start >= end) {
      return true;
    }

    int index = start;
    while (index < end && postorder[index] < postorder[end]) {
      ++index;
    }
    int temp = index;
    while (index < end && postorder[index] > postorder[end]) {
      ++index;
    }

    if (index != end) {
      return false;
    }

    return verifyPostorderRecursion(postorder, start, temp - 1)
        && verifyPostorderRecursion(postorder, temp, end - 1);
  }

  // 单调栈
  private boolean verifyPostorderMonotoneStack(int[] postorder) {
    Deque<Integer> stack = new LinkedList<>();
    int recentRoot = Integer.MAX_VALUE;
    for (int index = postorder.length - 1; index >= 0; --index) {
      if (postorder[index] > recentRoot) {
        return false;
      }

      while (!stack.isEmpty() && postorder[index] < stack.peek()) {
        recentRoot = stack.pop();
      }

      stack.push(postorder[index]);
    }

    return true;
  }
}
