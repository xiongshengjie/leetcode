package cn.xcloude.leetcode;

import java.util.Arrays;

/**
 * leetcode 1652
 * 拆炸弹
 */
public class DefuseTheBomb {
  public int[] decrypt(int[] code, int k) {
    int[] result = new int[code.length];
    if (k == 0) {
      Arrays.fill(result, 0);
      return result;
    }

    int signal, tune, last = 0, abs = Math.abs(k);
    if (k > 0) {
      signal = 1;
      tune = 0;
    } else {
      signal = -1;
      tune = -1;
    }

    for (int index = 1; index <= abs; ++index) {
      last += code[(index * signal + code.length) % code.length];
    }

    result[0] = last;
    for (int index = 1; index < code.length; ++index) {
      last = last - code[index + tune] * signal
          + code[((index + k + tune + code.length) % code.length)] * signal;
      result[index] = last;
    }

    return result;
  }
}
