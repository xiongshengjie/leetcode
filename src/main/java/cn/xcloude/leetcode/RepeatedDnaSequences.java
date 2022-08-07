package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * leetcode 187
 * 重复的DNA序列
 */
public class RepeatedDnaSequences {
  private static final int SUBSTRING_LENGTH = 10;
  private static final int MASK = 0xFFFFF;
  private static final Map<Character, Integer> char2Code = Map.of('A', 0, 'C', 1, 'G', 2, 'T', 3);

  public List<String> findRepeatedDnaSequences(String s) {
    // return findRepeatedDnaSequencesSubstring(s);
    return findRepeatedDnaSequencesBit(s);
  }

  // Hash 表 + 位运算
  private List<String> findRepeatedDnaSequencesBit(String s) {
    if (s.length() <= SUBSTRING_LENGTH) {
      return Collections.emptyList();
    }

    int hashCode = 0;
    for (int index = 0; index < SUBSTRING_LENGTH; ++index) {
      hashCode = (hashCode << 2) | char2Code.get(s.charAt(index));
    }

    List<String> result = new LinkedList<>();
    Map<Integer, Integer> hashCode2Count = new HashMap<>(s.length() - SUBSTRING_LENGTH + 1);
    hashCode2Count.put(hashCode, 1);
    for (int index = 1; index <= s.length() - SUBSTRING_LENGTH; ++index) {
      hashCode = ((hashCode << 2) | char2Code.get(s.charAt(index + SUBSTRING_LENGTH - 1))) & MASK;
      Integer count = hashCode2Count.get(hashCode);

      if (count == null) {
        hashCode2Count.put(hashCode, 1);
      } else if (count == 1) {
        result.add(s.substring(index, index + SUBSTRING_LENGTH));
        hashCode2Count.put(hashCode, 2);
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
