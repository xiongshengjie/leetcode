package cn.xcloude.leetcode;

public class MaximalRectangle {
  public int maximalRectangle(char[][] matrix) {
    int width, height, max = 0, lastHeight;

    // 对每个元素进行遍历，寻找以它为左上角的元素都为1的最大矩形
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {

        // 记录矩形的最小高度，初始值为无穷大
        lastHeight = Integer.MAX_VALUE;

        // 确定宽度
        for (int w = j; w < matrix[i].length; w++) {
          if (matrix[i][w] == '1') {
            height = 0;
            // 宽度
            width = w - j + 1;
            for (int h = i; h < matrix.length; h++) {
              if (matrix[h][w] == '0') {
                break;
              } else {
                // 高度
                height++;
              }
            }

            // 矩形，如果本次的高度比上次小，取这一次的高度作为最终高度
            if (lastHeight > height) {
              lastHeight = height;
            }
            // 计算面积
            max = Math.max(max, lastHeight * width);
          } else {
            break;
          }
        }
      }
    }
    return max;
  }

  public static void main(String[] args) {
/*    char[][] matrix = new char[][] {"11001011".toCharArray(),
        "11101111".toCharArray(),
        "10011111".toCharArray()};*/
    char[][] matrix = new char[][] {"1".toCharArray()};
    System.out.println(new MaximalRectangle().maximalRectangle(matrix));
  }
}
