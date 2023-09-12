/**
 * https://leetcode.cn/problems/palindromic-substrings/description/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/12 9:51
 */
public class 回文子串 {  // 647. 回文子串

    class Solution {
        public int countSubstrings(String s) {
            char[] chars = s.toCharArray();
            int len = chars.length;
            boolean[][] dp = new boolean[len][len];

            int result = 0;
            for (int i=len-1; i>=0; i--){
                for (int j=i; j<len; j++){
                    if (chars[i] == chars[j]){
                        if (j-i <= 1){  // 情况一 和 情况二
                            result++;
                            dp[i][j] = true;
                        } else if (dp[i+1][j-1]){ // 情况三
                            result++;
                            dp[i][j] = true;
                        }
                    }
                }
            }
            return result;
        }

        /**
         * 双指针法
         * @param s
         * @return
         */
        public int countSubstrings2(String s) {
            int len, ans = 0;
            if (s == null || (len = s.length()) < 1) return 0;
            //总共有2 * len - 1个中心点
            for (int i = 0; i < 2 * len - 1; i++) {
                //通过遍历每个回文中心，向两边扩散，并判断是否回文字串
                //有两种情况，left == right，right = left + 1，这两种回文中心是不一样的
                int left = i / 2, right = left + i % 2;
                while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                    //如果当前是一个回文串，则记录数量
                    ans++;
                    left--;
                    right++;
                }
            }
            return ans;
        }

    }
}
