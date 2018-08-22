package cn.xcloude.leetcode;

public class MinimumWindowSubstring {
  public String minWindow(String S, String T) {
    if (S == null || T == null) {
      return "";
    }

    int windowLength = T.length();
    for (int i = 0; i < S.length() - windowLength; i++) {
      for (int j = windowLength; j < S.length(); j++) {
        String temp = S.substring(i, i + j);
        for (int position = 0; position < windowLength; position++) {
          if (temp.contains(T.substring(position, position + 1))) {
            if (position == windowLength - 1) {
              return temp;
            }
          }
          break;
        }
      }
    }
    return "";
  }

  public static void main(String[] args) {
    String s = "ABCC",t="BC";
    new MinimumWindowSubstring().minWindow(s,t);
  }
}
