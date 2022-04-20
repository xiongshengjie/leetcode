package cn.xcloude.leetcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 1286
 * 字母组合迭代器
 */
public class CombinationIterator {
  private final Strategy strategy;

  public CombinationIterator(String characters, int combinationLength) {
    // this.strategy = new CombinationIterator0(characters, combinationLength);
    this.strategy = new CombinationIterator1(characters, combinationLength);
  }

  public String next() {
    return strategy.next();
  }

  public boolean hasNext() {
    return strategy.hasNext();
  }

  private interface Strategy {
    String next();

    boolean hasNext();
  }

  // 先将所有的满足要求的组合生成并存储下来, 一个个返回
  private static final class CombinationIterator0 implements Strategy {
    private final Iterator<String> iterator;

    private CombinationIterator0(String characters, int combinationLength) {
      List<String> result = new LinkedList<>();
      init(characters, combinationLength, 0, new StringBuilder(characters.length()), result);
      this.iterator = result.iterator();
    }

    private void init(String characters, int combinationLength,
        int index, StringBuilder sb, List<String> result) {
      if (sb.length() > combinationLength) {
        return;
      }

      if (index >= characters.length()) {
        if (sb.length() == combinationLength) {
          result.add(sb.toString());
        }
        return;
      }

      init(characters, combinationLength, index + 1, sb.append(characters.charAt(index)), result);
      init(characters, combinationLength, index + 1, sb.deleteCharAt(sb.length() - 1), result);
    }

    @Override
    public String next() {
      return iterator.next();
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }
  }

  // 边调用边生成
  private static final class CombinationIterator1 implements Strategy {
    private final String characters;
    private final int combinationLength;

    private boolean hasNext = true;
    private int[] next;

    private CombinationIterator1(String characters, int combinationLength) {
      this.characters = characters;
      this.combinationLength = combinationLength;
      this.next = new int[combinationLength];
      for (int index = 0; index < combinationLength; ++index) {
        this.next[index] = index;
      }
    }

    @Override
    public String next() {
      char[] temp = new char[combinationLength];
      for (int index = 0; index < combinationLength; ++index) {
        temp[index] = characters.charAt(next[index]);
      }

      if (next[0] >= characters.length() - combinationLength) {
        hasNext = false;
      }

      if (!hasNext) {
        return new String(temp);
      }

      for (int index = combinationLength - 1; index >= 0; --index) {
        if (next[index] < characters.length() - (combinationLength - index)) {
          next[index] += 1;
          for (int moveIndex = index + 1; moveIndex < combinationLength; ++moveIndex) {
            next[moveIndex] = next[moveIndex - 1] + 1;
          }
          break;
        }
      }

      return new String(temp);
    }

    @Override
    public boolean hasNext() {
      return hasNext;
    }
  }
}
