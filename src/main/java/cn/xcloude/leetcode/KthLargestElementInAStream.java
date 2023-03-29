package cn.xcloude.leetcode;

/**
 * leetcode 703
 * 数据流中的第 K 大元素
 */
public class KthLargestElementInAStream {
  private final int[] elements;
  private final int k;

  private int size;

  public KthLargestElementInAStream(int k, int[] nums) {
    elements = new int[k];
    this.k = k;
    for (int num : nums) {
      add(num);
    }
  }

  public int add(int val) {
    if (size < k) {
      elements[size] = val;
      heapInsert(elements, size++);
      return elements[0];
    }

    if (elements[0] >= val) {
      return elements[0];
    }

    elements[0] = val;
    heapify(elements, 0, size);
    return elements[0];
  }

  private void heapInsert(int[] nums, int index) {
    while (nums[(index - 1) / 2] > nums[index]) {
      swap(nums, index, (index - 1) / 2);
      index = (index - 1) / 2;
    }
  }

  private void heapify(int[] nums, int index, int size) {
    int left = (index << 1) + 1;
    while (left < size) {
      int larger = (left + 1 < size && nums[left + 1] < nums[left]) ? left + 1 : left;
      larger = ((nums[larger] > nums[index])) ? index : larger;
      if (larger == index) {
        break;
      }
      swap(nums, larger, index);
      index = larger;
      left = (index << 1) + 1;
    }
  }

  private void swap(int[] nums, int index, int otherIndex) {
    int temp = nums[otherIndex];
    nums[otherIndex] = nums[index];
    nums[index] = temp;
  }
}
