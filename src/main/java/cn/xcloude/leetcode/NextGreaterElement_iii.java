package cn.xcloude.leetcode;

/**
 * leetcode 556
 * 下一个更大元素 III
 */
public class NextGreaterElement_iii {
  public int nextGreaterElement(int n) {
    char[] digits = Integer.toString(n).toCharArray();
    int swapIndex = digits.length - 2;
    while (swapIndex > -1 && digits[swapIndex + 1] <= digits[swapIndex]) {
      --swapIndex;
    }

    if (swapIndex == -1) {
      return -1;
    }

    int nextGreaterIndex = digits.length - 1;
    while (nextGreaterIndex > swapIndex + 1 && digits[nextGreaterIndex] <= digits[swapIndex]) {
      --nextGreaterIndex;
    }

    swap(digits, swapIndex, nextGreaterIndex);

    for (int left = swapIndex + 1, right = digits.length - 1; left < right; ++left, --right) {
      swap(digits, left, right);
    }

    long result = Long.parseLong(new String(digits));
    return result > Integer.MAX_VALUE ? -1 : (int) result;
  }

  private void swap(char[] digits, int index, int otherIndex) {
    char temp = digits[index];
    digits[index] = digits[otherIndex];
    digits[otherIndex] = temp;
  }
}
