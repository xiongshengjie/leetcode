package cn.xcloude.leetcode;

public class MedianOfTwoSortedArrays {
  public double findMedianSortedArrays(int A[], int B[]) {
    int sizeA = A.length;
    int sizeB = B.length;
    int[] result = new int[sizeA+sizeB];
    int i = 0,j = 0,k = 0;
    while (i < sizeA && j < sizeB){
      if(A[i] < B[j]){
        result[k] = A[i];
        k++;
        i++;
      }else if(A[i] >= B[j]){
        result[k] = B[j];
        k++;
        j++;
      }
    }
    if(i < sizeA){
      for(;i < sizeA ; i++){
        result[k] = A[i];
        k++;
      }
    }else if(j < sizeB){
      for(;j < sizeB ; j++){
        result[k] = B[j];
        k++;
      }
    }
    int medium = result.length/2;
    if(result.length%2 == 0){
      return ((double)(result[medium-1]+result[medium]))/2;
    }else {
      return result[medium];
    }
  }
}
