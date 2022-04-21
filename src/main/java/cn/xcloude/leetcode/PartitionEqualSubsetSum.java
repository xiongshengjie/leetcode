package cn.xcloude.leetcode;

/**
 * leetcode 416
 * 分割等和子集
 */
public class PartitionEqualSubsetSum {
  public boolean canPartition(int[] nums) {
    int sum = 0, max = 0;
    for (int num : nums) {
      sum += num;
      if (max < num) {
        max = num;
      }
    }

    int target;
    if (sum % 2 != 0 || (target = sum / 2) < max) {
      return false;
    }

    // return canPartitionWithMemorandum(nums, target);
    return canPartitionDp(nums, target);
  }

  // 动态规划
  private boolean canPartitionDp(int[] nums, int target) {
    boolean[][] dp = new boolean[nums.length][target + 1];
    dp[0][nums[0]] = true;
    for (int index = 0; index < nums.length; ++index) {
      dp[index][0] = true;
    }

    for (int index = 1; index < nums.length; ++index) {
      for (int sum = 1; sum <= target; ++sum) {
        if (sum >= nums[index]) {
          dp[index][sum] = dp[index - 1][sum] || dp[index - 1][sum - nums[index]];
        } else {
          dp[index][sum] = dp[index - 1][sum];
        }
      }
    }

    return dp[nums.length - 1][target];
  }

  // 带备忘录的回溯
  private boolean canPartitionWithMemorandum(int[] nums, int target) {
    Boolean[][] memorandum = new Boolean[nums.length][target + 1];
    return canPartition(nums, target, 0, 0, 0, memorandum);
  }

  private boolean canPartition(int[] nums, int target, int index, int subsetSum,
      int otherSubsetSum, Boolean[][] memorandum) {
    if (index == nums.length) {
      return subsetSum == otherSubsetSum;
    }

    if (target < subsetSum || target < otherSubsetSum) {
      return false;
    }

    if (memorandum[index][subsetSum] != null) {
      return memorandum[index][subsetSum];
    }

    memorandum[index][subsetSum] = canPartition(nums, target, index + 1, subsetSum + nums[index],
        otherSubsetSum, memorandum)
        || canPartition(nums, target, index + 1, subsetSum,
        otherSubsetSum + nums[index], memorandum);
    return memorandum[index][subsetSum];
  }
}
