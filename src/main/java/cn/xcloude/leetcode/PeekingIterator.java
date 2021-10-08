package cn.xcloude.leetcode;

import java.util.Iterator;

/**
 * leetcode 284
 * 顶端迭代器
 */
public class PeekingIterator implements Iterator<Integer> {
  private final Iterator<Integer> iterator;
  private Integer nextElement;

  public PeekingIterator(Iterator<Integer> iterator) {
    this.iterator = iterator;
    this.nextElement = iterator.next();
  }

  public Integer peek() {
    return nextElement;
  }

  @Override
  public Integer next() {
    Integer temp = nextElement;
    nextElement = iterator.hasNext() ? iterator.next() : null;
    return temp;
  }

  @Override
  public boolean hasNext() {
    return nextElement != null;
  }
}
