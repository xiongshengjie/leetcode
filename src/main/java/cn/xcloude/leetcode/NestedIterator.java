package cn.xcloude.leetcode;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

interface NestedInteger {
  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  boolean isInteger();

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  Integer getInteger();

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return empty list if this NestedInteger holds a single integer
  List<NestedInteger> getList();
}

/**
 * leetcode 341
 * 扁平化嵌套列表迭代器
 */
public class NestedIterator implements Iterator<Integer> {
  private final Deque<ListIterator<NestedInteger>> stack;

  public NestedIterator(List<NestedInteger> nestedList) {
    stack = new LinkedList<>();
    stack.push(nestedList.listIterator());
  }

  @Override
  public Integer next() {
    Iterator<NestedInteger> iterator = stack.peek();
    NestedInteger nestedInteger = iterator.next();
    return nestedInteger.getInteger();
  }

  @Override
  public boolean hasNext() {
    while (!stack.isEmpty()) {
      ListIterator<NestedInteger> iterator = stack.peek();
      if (!iterator.hasNext()) {
        stack.pop();
        continue;
      }

      NestedInteger nestedInteger = iterator.next();
      if (nestedInteger.isInteger()) {
        iterator.previous();
        return true;
      } else {
        stack.push(nestedInteger.getList().listIterator());
      }
    }

    return false;
  }
}
