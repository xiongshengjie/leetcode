package cn.xcloude.leetcode.MinimumWindowSubstring;

public class MinimumWindowSubstring {
  public String minWindow(String S, String T) {
    int[] count = new int[128];
    for (int i = 0; i < T.length(); i++) {
      count[T.charAt(i)]++;
    }

    int begin = 0, end = 0, windowLength = Integer.MAX_VALUE, counter = T.length(), head = 0;
    while (end < S.length()) {
      if (count[S.charAt(end++)]-- > 0) {
        counter--;
      }

      while (counter == 0) {
        if (windowLength > end - begin) {
          head = begin;
          windowLength = end - begin;
        }
        if (count[S.charAt(begin++)]++ == 0) {
          counter++;
        }

      }
    }
    return windowLength == Integer.MAX_VALUE ? "" : S.substring(head, head + windowLength);
  }

  public static void main(String[] args) {
    String s = "a", t = "b";
    new MinimumWindowSubstring().minWindow(s, t);
  }
}
