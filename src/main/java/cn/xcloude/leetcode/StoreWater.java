package cn.xcloude.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LCP 33
 * 蓄水
 */
public class StoreWater {
  public int storeWater(int[] bucket, int[] vat) {
    int upgradeCount = 0;
    PriorityQueue<Element> maxHeap =
        new PriorityQueue<>(Comparator.comparing(Element::getCount, Comparator.reverseOrder()));
    for (int index = 0; index < bucket.length; ++index) {
      if (vat[index] == 0) {
        continue;
      }

      if (bucket[index] == 0) {
        ++upgradeCount;
        maxHeap.add(new Element(bucket[index] + 1, vat[index]));
      } else {
        maxHeap.add(new Element(bucket[index], vat[index]));
      }
    }

    if (maxHeap.isEmpty()) {
      return 0;
    }

    int result = upgradeCount + maxHeap.peek().getCount();
    while (result > upgradeCount && maxHeap.peek().getCount() > 2) {
      Element element = maxHeap.poll();
      int currentCount = upgradeCount + element.getCount();
      if (currentCount < result) {
        result = currentCount;
      }

      element.upgradeBucket();
      ++upgradeCount;
      maxHeap.add(element);
    }

    return result;
  }

  private static final class Element {
    private int bucket;
    private final int vat;
    private int count;

    private Element(int bucket, int vat) {
      this.bucket = bucket;
      this.vat = vat;
      calculateCount();
    }

    private int getCount() {
      return count;
    }

    private void calculateCount() {
      this.count = (int) Math.ceil((double) vat / bucket);
    }

    private void upgradeBucket() {
      ++bucket;
      calculateCount();
    }
  }
}
