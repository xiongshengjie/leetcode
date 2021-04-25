package cn.xcloude.leetcode;

import java.util.ArrayList;

public class PascalsTriangle {
  public ArrayList<ArrayList<Integer>> generate(int numRows) {
    ArrayList<ArrayList<Integer>> pascalTriangle = new ArrayList<>(numRows);
    for (int i = 0; i < numRows; i++) {
      ArrayList<Integer> rows = new ArrayList<>(i);
      rows.add(1);
      for (int j = 1; j < i; j++) {
        rows.add(j, pascalTriangle.get(i - 1).get(j - 1) + pascalTriangle.get(i - 1).get(j));
      }
      if (i > 0) {
        rows.add(1);
      }
      pascalTriangle.add(rows);
    }
    return pascalTriangle;
  }

  public static void main(String[] args) {
    System.out.println(new PascalsTriangle().generate(5));
  }
}
