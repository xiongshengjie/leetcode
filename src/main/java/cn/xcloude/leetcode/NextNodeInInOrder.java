package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * nowcoder JZ8
 * 二叉树的下一个结点
 */
public class NextNodeInInOrder {
  public TreeLinkNode getNext(TreeLinkNode pNode) {
    if (pNode == null) {
      return null;
    }

    // return getNext0(pNode);
    return getNext1(pNode);
  }

  // 中序遍历
  private TreeLinkNode getNext0(TreeLinkNode pNode) {
    TreeLinkNode root = pNode;
    while (root.next != null) {
      root = root.next;
    }

    Deque<TreeLinkNode> stack = new LinkedList<>();
    TreeLinkNode node = root;
    TreeLinkNode pre = null;
    while (!stack.isEmpty() || node != null) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }

      node = stack.pop();
      if (pre == pNode) {
        return node;
      }
      pre = node;
      node = node.right;
    }

    return null;
  }

  // 分情况讨论
  // 有右子树, 下一结点是右子树中的最左结点
  // 无右子树, 且结点是该结点父结点的左子树, 则下一结点是该结点的父结点
  // 无右子树, 且结点是该结点父结点的右子树, 则一直沿着父结点追朔, 直到找到某个结点是其父结点的左子树
  // 其他情况返回 null
  private TreeLinkNode getNext1(TreeLinkNode pNode) {
    if (pNode.right != null) {
      pNode = pNode.right;
      while (pNode.left != null) {
        pNode = pNode.left;
      }

      return pNode;
    }

    // 第二三种情况可以统一逻辑
    while (pNode.next != null) {
      if (pNode.next.left == pNode) {
        return pNode.next;
      }
      pNode = pNode.next;
    }

    return null;
  }
}
