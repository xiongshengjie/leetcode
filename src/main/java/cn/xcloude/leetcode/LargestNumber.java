package cn.xcloude.leetcode;

import java.util.Arrays;

/**
 * leetcode 179
 * 最大数
 */
public class LargestNumber {
  public String largestNumber(int[] nums) {
    if (nums == null || nums.length == 0) {
      return "";
    }

    Integer[] numbers = new Integer[nums.length];
    for (int index = 0; index < nums.length; ++index) {
      numbers[index] = nums[index];
    }

    Arrays.sort(numbers, (num, otherNum) -> {
      long powerOfTenGreaterThanNum = getGreaterNumber(num);
      long powerOfTenGreaterThanOtherNum = getGreaterNumber(otherNum);

      return (int) (-powerOfTenGreaterThanOtherNum * num - otherNum
          + powerOfTenGreaterThanNum * otherNum + num);
    });

    if (numbers[0] == 0) {
      return "0";
    }

    StringBuilder sb = new StringBuilder();
    for (Integer number : numbers) {
      sb.append(number);
    }

    return sb.toString();
  }

  private static long getGreaterNumber(Integer number) {
    long powerOfTenGreaterThanNumber = 10L;
    while (powerOfTenGreaterThanNumber <= number) {
      powerOfTenGreaterThanNumber *= 10;
    }

    return powerOfTenGreaterThanNumber;
  }
}
