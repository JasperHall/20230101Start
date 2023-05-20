package s202305;

/**
 * https://leetcode.cn/problems/reverse-string/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/17 23:51
 */
public class 反转字符串 {  // 344

    class Solution {
        /**
         * 双指针(头尾指针
         * @param s
         */
        public void reverseString(char[] s) {
            int i=0, j=s.length-1;

            while (i < j){
                char temp = s[i];
                s[i] = s[j];
                s[j] = temp;
                i++;
                j--;
            }
        }

        /**
         * 位运算
         * @param s
         */
        public void reverseString2(char[] s) {
            int l = 0;
            int r = s.length - 1;
            while (l < r) {
                s[l] ^= s[r];  //构造 a ^ b 的结果，并放在 a 中
                s[r] ^= s[l];  //将 a ^ b 这一结果再 ^ b ，存入b中，此时 b = a, a = a ^ b
                s[l] ^= s[r];  //a ^ b 的结果再 ^ a ，存入 a 中，此时 b = a, a = b 完成交换
                l++;
                r--;
            }
        }
    }

}
