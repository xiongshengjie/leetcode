package cn.xcloude.leetcode;

public class ValidPalindrome {
  public boolean isPalindrome(String s) {
    if(s.isEmpty()||s.length() == 1){
      return true;
    }
    int length = s.length();
    for(int i = 0,j = length - 1;i < j;){
      while(i<j&&!Character.isLetterOrDigit(s.charAt(i))){
        i++;
      }
      while(i<j&&!Character.isLetterOrDigit(s.charAt(j))){
        j--;
      }
      if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))){
        return false;
      }else{
        i++;
        j--;
      }
    }
    return true;
  }
}

