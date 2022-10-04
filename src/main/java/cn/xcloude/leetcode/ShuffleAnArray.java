package cn.xcloude.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * leetcode 384
 * 打乱数组
 */
public class ShuffleAnArray {
  private final int[] original;
  private final int[] shuffled;

  private final Random random;

  public ShuffleAnArray(int[] nums) {
    this.original = nums;
    this.shuffled = Arrays.copyOf(nums, nums.length);
    this.random = new Random();
  }

  public int[] reset() {
    return original;
  }

  public int[] shuffle() {
    for (int index = 0; index < shuffled.length; ++index) {
      int randomIndex = index + random.nextInt(shuffled.length - index);
      int temp = shuffled[index];
      shuffled[index] = shuffled[randomIndex];
      shuffled[randomIndex] = temp;
    }

    return shuffled;
  }
}
