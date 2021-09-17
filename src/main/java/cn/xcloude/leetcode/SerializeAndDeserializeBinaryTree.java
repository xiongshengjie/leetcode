package cn.xcloude.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * leetcode 297
 * 二叉树的序列化与反序列化
 */
public class SerializeAndDeserializeBinaryTree {
  private static final String DELIMITER = "_";
  private static final String NULL = "#";

  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    serialize0(root, sb);
    return sb.toString();
  }

  private void serialize0(TreeNode node, StringBuilder sb) {
    if (node == null) {
      sb.append(NULL);
      return;
    }

    sb.append(node.val).append(DELIMITER);
    serialize0(node.left, sb);
    sb.append(DELIMITER);
    serialize0(node.right, sb);
  }

  public TreeNode deserialize(String data) {
    String[] values = data.split(DELIMITER);
    AtomicInteger counter = new AtomicInteger(0);
    return deserialize0(values, counter);
  }

  private TreeNode deserialize0(String[] values, AtomicInteger counter) {
    String value = values[counter.getAndIncrement()];
    if (NULL.equals(value)) {
      return null;
    }

    TreeNode node = new TreeNode(Integer.parseInt(value));
    node.left = deserialize0(values, counter);
    node.right = deserialize0(values, counter);

    return node;
  }

  public static void main(String[] args) {
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node3 = new TreeNode(3, node4, node5);
    TreeNode node2 = new TreeNode(2);
    TreeNode root = new TreeNode(1, node2, node3);
    String data = new SerializeAndDeserializeBinaryTree().serialize(root);
    TreeNode node = new SerializeAndDeserializeBinaryTree().deserialize(data);
    System.out.println(node);
  }
}
