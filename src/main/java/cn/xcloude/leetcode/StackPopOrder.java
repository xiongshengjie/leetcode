package cn.xcloude.leetcode;

/**
 * leetcode LCR 148
 * nowcoder JZ31
 * 栈的压入弹出序列
 */
public class StackPopOrder {
  public boolean validateBookSequences(int[] putIn, int[] takeOut) {
    int size = 0;
    int takeOutIndex = 0;
    for (int value : putIn) {
      putIn[size] = value;
      while (size >= 0 && putIn[size] == takeOut[takeOutIndex]) {
        --size;
        ++takeOutIndex;
      }
      ++size;
    }

    return size == 0;
  }
}
