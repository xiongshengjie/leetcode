package cn.xcloude.leetcode;

import java.util.Arrays;
import java.util.BitSet;

/**
 * leetcode 面试题 05.08
 */
public class DrawLineLCCI {
  public int[] drawLine(int length, int w, int x1, int x2, int y) {
    int lengthInBit = length << 5;
    BitSet screen = new BitSet(lengthInBit);
    int startBit = w * y;
    for (int i = x1; i <= x2; i++) {
      screen.set(startBit + i);
    }

    return bits2Ints(screen, length, startBit + x1, startBit + x2);
  }

  private int[] bits2Ints(BitSet bs, int length, int startBit, int endBit) {
    int[] temp = new int[length];

    int startInt = startBit / 32;
    int endInt = endBit / 32;
    temp[startInt] = toInt(bs, startInt);
    temp[endInt] = toInt(bs, endInt);

    for (int i = startInt + 1; i < endInt; i++) {
      temp[i] = -1;
    }

    return temp;
  }

  private int toInt(BitSet bs, int position) {
    int result = 0;
    int realPosition = position << 5;
    for (int j = 0; j < 32; j++) {
      if (bs.get(realPosition + j)) {
        result |= 1 << (31 - j);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(new DrawLineLCCI().drawLine(1, 32, 30, 31, 0)));
    System.out.println(Arrays.toString(new DrawLineLCCI().drawLine(3, 96, 0, 95, 0)));
  }
}
