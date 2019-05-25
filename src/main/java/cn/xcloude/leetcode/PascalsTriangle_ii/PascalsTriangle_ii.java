package cn.xcloude.leetcode.PascalsTriangle_ii;

import java.util.ArrayList;

public class PascalsTriangle_ii {
  public ArrayList<Integer> getRow(int rowIndex) {
    ArrayList<Integer> row = new ArrayList<Integer>();
    if (rowIndex < 0) {
      return row;
    }

    for (int i = 0; i < rowIndex + 1; i++) {
      for (int j = i - 1; j > 0; j--) {
        row.set(j, row.get(j) + row.get(j - 1));
      }
      row.add(1);
    }

    return row;
  }

  public static void main(String[] args) {
    new PascalsTriangle_ii().getRow(5);
  }
}
