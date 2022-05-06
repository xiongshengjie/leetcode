package cn.xcloude.leetcode;

/**
 * leetcode 494
 * 目标和
 */
public class TargetSum {
  public int findTargetSumWays(int[] nums, int target) {
    // return findTargetSumWays(nums, target, 0, 0, new int[nums.length][2000]);
    // return findTargetSumWays0(nums, target);
    return findTargetSumWays1(nums, target);
  }

  // 动态规划
  private int findTargetSumWays0(int[] nums, int target) {
    // 直接将 target 作为 dp 数组的第二维的话, 会存在负数, 不好处理, 当然也可以使用 zigzag 转换去处理
    // 所以要想办法将第二维转成一定是正数的情况, 这里以 添加负号 的元素的和作为第二维, dp 表示组合的个数
    // 分析如下, 假设所有元素的和为 sum, 所有添加负号的元素的和为 negativeSum
    // 则所有添加正号的元素的和为 sum - negativeSum
    // 如果符合整体表达式的值为 target, 则 (sum - negativeSum) - negativeSum = target
    // 即 negativeSum = (sum - target) / 2
    // 只要找到一组元素的和为 negativeSum, 那么就存在一个表达式的值为 target
    // 所以将 dp 的二维转换为了 negativeSum, 一定为正数
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }

    target = sum - target;
    if (target < 0 || target % 2 != 0) {
      return 0;
    }
    target >>= 1;

    // dp[index][target] 代表前 index 个元素中 和 等于 target 的组合个数
    int[][] dp = new int[nums.length + 1][target + 1];
    dp[0][0] = 1;

    for (int index = 1; index <= nums.length; ++index) {
      for (int current = 0; current <= target; ++current) {
        if (current < nums[index - 1]) {
          dp[index][current] = dp[index - 1][current];
        } else {
          dp[index][current] = dp[index - 1][current] + dp[index - 1][current - nums[index - 1]];
        }
      }
    }

    return dp[nums.length][target];
  }

  // 动态规划, 优化为 一维数组
  private int findTargetSumWays1(int[] nums, int target) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }

    target = sum - target;
    if (target < 0 || target % 2 != 0) {
      return 0;
    }
    target >>= 1;

    int[] dp = new int[target + 1];
    dp[0] = 1;

    for (int num : nums) {
      for (int current = target; current >= num; --current) {
        dp[current] += dp[current - num];
      }
    }

    return dp[target];
  }

  // 带备忘录的递归
  private int findTargetSumWays(int[] nums, int target, int index, int current,
      int[][] memorandum) {
    if (index == nums.length) {
      if (target == current) {
        return 1;
      }
      return 0;
    }

    int temp = encodeZigZag(current);
    if (memorandum[index][temp] != 0) {
      return memorandum[index][temp];
    }

    memorandum[index][temp] =
        findTargetSumWays(nums, target, index + 1, current + nums[index], memorandum)
            + findTargetSumWays(nums, target, index + 1, current - nums[index], memorandum);
    return memorandum[index][temp];
  }

  private static int encodeZigZag(final int n) {
    return (n << 1) ^ (n >> 31);
  }
}
