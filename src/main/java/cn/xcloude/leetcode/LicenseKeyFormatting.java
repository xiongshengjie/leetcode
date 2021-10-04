package cn.xcloude.leetcode;

/**
 * leetcode 482
 * 密钥格式化
 */
public class LicenseKeyFormatting {
  public String licenseKeyFormatting(String s, int k) {
    // return licenseKeyFormattingReverse(s, k);
    return licenseKeyFormattingForward(s, k);
  }

  // 逆向
  private String licenseKeyFormattingReverse(String s, int k) {
    StringBuilder sb = new StringBuilder(s.length() + s.length() / k);
    int groupCount = 0;
    for (int index = s.length() - 1; index >= 0; --index) {
      char ch = s.charAt(index);
      if (ch == '-') {
        continue;
      }

      if (groupCount == k) {
        groupCount = 0;
        sb.append('-');
      }

      if (ch <= 'z' && ch >= 'a') {
        ch = (char) (ch - 'a' + 'A');
      }
      sb.append(ch);
      ++groupCount;
    }

    if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {
      sb.deleteCharAt(sb.length() - 1);
    }

    return sb.reverse().toString();
  }

  // 正向
  private String licenseKeyFormattingForward(String s, int k) {
    int count = 0, index = 0;
    char[] chars = new char[s.length()];
    for (index = 0; index < s.length(); ++index) {
      char ch = s.charAt(index);
      if (ch != '-') {
        if (ch <= 'z' && ch >= 'a') {
          ch = (char) (ch - 'a' + 'A');
        }
        chars[count] = ch;
        ++count;
      }
    }

    StringBuilder sb = new StringBuilder(count + count / k + 1);
    int firstK = count % k;
    index = 0;
    if (firstK != 0) {
      for (index = 0; index < firstK; ++index) {
        sb.append(chars[index]);
      }

      if (index >= count) {
        return sb.toString();
      }
      sb.append('-');
    }

    int groupCount = 0;
    for (; index < count; ++index) {
      if (groupCount == k) {
        groupCount = 0;
        sb.append('-');
      }
      ++groupCount;
      sb.append(chars[index]);

    }

    return sb.toString();
  }
}
