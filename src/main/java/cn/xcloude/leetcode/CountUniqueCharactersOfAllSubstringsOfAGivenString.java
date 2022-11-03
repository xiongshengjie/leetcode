package cn.xcloude.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode 828
 * 统计子串中的唯一字符
 */
public class CountUniqueCharactersOfAllSubstringsOfAGivenString {
  public int uniqueLetterString(String s) {
    int length = s.length();
    Map<Character, List<Integer>> char2Indexes = new HashMap<>(32);
    for (int index = 0; index < length; ++index) {
      List<Integer> indexes = char2Indexes.computeIfAbsent(s.charAt(index), key -> {
        List<Integer> initIndexes = new ArrayList<>();
        initIndexes.add(-1);
        return initIndexes;
      });
      indexes.add(index);
    }

    int result = 0;
    for (List<Integer> indexes : char2Indexes.values()) {
      indexes.add(length);
      for (int index = 1; index < indexes.size() - 1; ++index) {
        result += (indexes.get(index) - indexes.get(index - 1))
            * (indexes.get(index + 1) - indexes.get(index));
      }
    }

    return result;
  }
}
