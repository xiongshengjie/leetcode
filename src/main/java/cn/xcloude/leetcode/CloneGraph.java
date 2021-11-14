package cn.xcloude.leetcode;

import cn.xcloude.leetcode.graph.Node;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * leetcode 133
 * 克隆图
 */
public class CloneGraph {
  private static final Map<Node, Node> OLD_2_CLONE_RECURSION = new HashMap<>();

  public Node cloneGraph(Node node) {
    if (node == null) {
      return null;
    }

    // return cloneGraphBreadthFirst(node);
    // return cloneGraphDepthFirstIteratively(node);
    return cloneGraphDepthFirstRecursion(node);
  }

  // 深度优先遍历 递归
  private Node cloneGraphDepthFirstRecursion(Node node) {
    Node clone = OLD_2_CLONE_RECURSION.get(node);
    if (clone != null) {
      return clone;
    }

    clone = new Node(node.val);
    OLD_2_CLONE_RECURSION.put(node, clone);

    for (Node neighbor : node.neighbors) {
      clone.neighbors.add(cloneGraphDepthFirstRecursion(neighbor));
    }

    return clone;
  }

  // 深度优先遍历 迭代
  private Node cloneGraphDepthFirstIteratively(Node node) {
    Deque<Node> stack = new LinkedList<>();
    Set<Node> set = new HashSet<>();
    Map<Node, Node> old2Clone = new HashMap<>();
    stack.push(node);
    set.add(node);

    Node result = cloneNode(node, old2Clone);
    while (!stack.isEmpty()) {
      Node current = stack.pop();
      for (Node neighbor : current.neighbors) {
        if (set.add(neighbor)) {
          stack.push(current);
          stack.push(neighbor);
          cloneNode(neighbor, old2Clone);
          break;
        }
      }
    }

    return result;
  }

  private Node cloneNode(Node node, Map<Node, Node> old2Clone) {
    Node clone = old2Clone.computeIfAbsent(node, e -> new Node(e.val));
    for (Node neighbor : node.neighbors) {
      Node neighborClone = old2Clone.computeIfAbsent(neighbor, e -> new Node(e.val));
      clone.neighbors.add(neighborClone);
    }
    return clone;
  }

  // 广度优先遍历
  private Node cloneGraphBreadthFirst(Node node) {
    Queue<Node> queue = new LinkedList<>();
    Map<Node, Node> old2Clone = new HashMap<>();
    queue.add(node);
    Node result = new Node(node.val);
    old2Clone.put(node, result);
    while (!queue.isEmpty()) {
      Node current = queue.poll();

      // 一定有值
      Node clone = old2Clone.get(current);
      for (Node neighbor : current.neighbors) {
        Node neighborClone = old2Clone.get(neighbor);
        if (neighborClone == null) {
          neighborClone = new Node(neighbor.val);
          old2Clone.put(neighbor, neighborClone);
          queue.offer(neighbor);
        }

        clone.neighbors.add(neighborClone);
      }
    }

    return result;
  }
}
