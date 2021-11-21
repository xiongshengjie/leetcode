package cn.xcloude.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * leetcode 208
 * 实现 Trie (前缀树)
 */
public class Trie {
  private final TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  public void insert(String word) {
    ++root.pass;
    TrieNode node = root;
    for (int index = 0; index < word.length(); ++index) {
      node = node.char2Son.computeIfAbsent(word.charAt(index), e -> new TrieNode());
      ++node.pass;
    }
    ++node.end;
  }

  public boolean search(String word) {
    return search0(word, node -> node.end > 0);
  }

  public boolean startsWith(String prefix) {
    return search0(prefix, node -> node.pass > 0);
  }

  private boolean search0(String word, Function<TrieNode, Boolean> function) {
    TrieNode node = root;
    for (int index = 0; index < word.length(); ++index) {
      node = node.char2Son.get(word.charAt(index));
      if (node == null) {
        return false;
      }
    }

    return function.apply(node);
  }

  private static final class TrieNode {
    private int pass;
    private int end;
    private final Map<Character, TrieNode> char2Son;

    private TrieNode() {
      this.pass = 0;
      this.end = 0;
      this.char2Son = new HashMap<>();
    }
  }
}
