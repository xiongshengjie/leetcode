package cn.xcloude.leetcode;

/**
 * leetcode 990
 * 等式方程的可满足性
 */
public class SatisfiabilityOfEqualityEquations {
  public boolean equationsPossible(String[] equations) {
    NumberOfProvinces.UnionFind unionFind = new NumberOfProvinces.UnionFind(26);
    for (String equation : equations) {
      if (equation.charAt(1) == '=') {
        unionFind.union(encode(equation, 0), encode(equation, 3));
      }
    }

    for (String equation : equations) {
      if (equation.charAt(1) == '!'
          && unionFind.find(encode(equation, 0)) == unionFind.find(encode(equation, 3))) {
        return false;
      }
    }

    return true;
  }

  private int encode(String equation, int index) {
    return equation.charAt(index) - 'a';
  }
}
