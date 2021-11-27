package cn.xcloude.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * leetcode 212
 * 单词搜索 II
 */
public class WordSearch_ii {
  private static final char PLACE_HOLDER = '#';
  private TrieNode root;

  public List<String> findWords(char[][] board, String[] words) {
    root = new TrieNode(null);
    for (String word : words) {
      insert(word);
    }

    List<String> result = new LinkedList<>();
    for (int index = 0; index < board.length; ++index) {
      char[] chars = board[index];
      for (int inner = 0; inner < chars.length; ++inner) {
        check(board, index, inner, root, result);
      }
    }

    return result;
  }

  private void check(char[][] board, int index, int inner, TrieNode node, List<String> result) {
    if (index < 0 || inner < 0 || index >= board.length || inner >= board[0].length
        || board[index][inner] == PLACE_HOLDER) {
      return;
    }

    char temp = board[index][inner];
    node = node.char2Son.get(temp);
    if (node == null) {
      return;
    }

    String word = node.word;
    if (word != null) {
      result.add(word);
      node.word = null;

      // 可以精简前缀树
      simplifyTrie(node, word);
    }

    board[index][inner] = PLACE_HOLDER;

    check(board, index + 1, inner, node, result);
    check(board, index - 1, inner, node, result);
    check(board, index, inner + 1, node, result);
    check(board, index, inner - 1, node, result);

    board[index][inner] = temp;
  }

  private void simplifyTrie(TrieNode node, String word) {
    if (!node.char2Son.isEmpty()) {
      return;
    }

    int index;
    for (index = 0; index < word.length(); ++index) {
      node = node.parent;
      if (node.word != null || node.char2Son.size() != 1 || node == root) {
        break;
      }
    }

    node.char2Son.remove(word.charAt(word.length() - index - 1));
  }

  private void insert(String word) {
    TrieNode node = root;
    for (int index = 0; index < word.length(); ++index) {
      char ch = word.charAt(index);
      TrieNode temp = node.char2Son.get(ch);
      if (temp == null) {
        temp = new TrieNode(node);
        node.char2Son.put(ch, temp);
      }
      node = temp;
    }

    node.word = word;
  }

  private static final class TrieNode {
    /**
     * 父节点, 精简前缀树使用
     */
    private final TrieNode parent;
    private String word;
    private final Map<Character, TrieNode> char2Son;

    private TrieNode(TrieNode parent) {
      this.parent = parent;
      this.char2Son = new HashMap<>();
    }
  }
}
