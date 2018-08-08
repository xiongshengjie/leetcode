package cn.xcloude.leetcode;

public class GasStation {
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int sum = 0,j = 0;
    boolean flag = true;
    while(j < gas.length){
      flag = true;
      for(int i = j;j == i || j != i%gas.length; i++){
        sum += gas[i%gas.length];
        if((sum = sum - cost[i%gas.length]) < 0){
          j = i + 1;
          sum = 0;
          flag = false;
          break;
        }
      }
      if(flag){
        return j;
      }
    }
    return -1;
  }
}

