package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode 187
 * 重复的DNA序列
 */
public class RepeatedDnaSequences {
  private static final int SUBSTRING_LENGTH = 10;
  private static final Map<Character, Integer> char2Int = new HashMap<Character, Integer>() {{
    put('A', 0);
    put('C', 1);
    put('G', 2);
    put('T', 3);
  }};

  public List<String> findRepeatedDnaSequences(String s) {
    // return findRepeatedDnaSequencesSubstring(s);
    return findRepeatedDnaSequencesBit(s);
  }

  // Hash 表 + 位运算
  private List<String> findRepeatedDnaSequencesBit(String s) {
    List<String> result = new ArrayList<>();
    int length = s.length();
    if (length <= SUBSTRING_LENGTH) {
      return result;
    }

    int window = 0;
    for (int index = 0; index < SUBSTRING_LENGTH - 1; ++index) {
      window = (window << 2) | char2Int.get(s.charAt(index));
    }

    Map<Integer, Integer> window2Count = new HashMap<>();
    for (int index = 0; index <= length - SUBSTRING_LENGTH; ++index) {
      window = ((window << 2) | char2Int.get(s.charAt(index + SUBSTRING_LENGTH - 1)))
          & ((1 << (SUBSTRING_LENGTH * 2)) - 1);
      Integer count = window2Count.getOrDefault(window, 0);
      ++count;
      window2Count.put(window, count);
      if (count == 2) {
        result.add(s.substring(index, index + SUBSTRING_LENGTH));
      }
    }

    return result;
  }

  // Hash 表 + substring
  private List<String> findRepeatedDnaSequencesSubstring(String s) {
    List<String> result = new ArrayList<>();
    Map<String, Integer> substring2Count = new HashMap<>();
    for (int index = 0; index <= s.length() - SUBSTRING_LENGTH; ++index) {
      String substring = s.substring(index, index + SUBSTRING_LENGTH);
      Integer count = substring2Count.getOrDefault(substring, 0);
      ++count;
      substring2Count.put(substring, count);
      if (count == 2) {
        result.add(substring);
      }
    }

    return result;
  }
}
