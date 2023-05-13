package s202302;

/**
 * https://leetcode.cn/problems/reverse-string/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/7 10:20
 */
public class 反转字符串 {
    class Solution {
        /**
         * 双指针
         * 前后指针
         * @param s
         */
        public void reverseString1(char[] s) {
            int i=0, j=s.length-1;
            while (i < j){
                char temp = s[i];
                s[i] = s[j];
                s[j] = temp;
                i++;
                j--;
            }

        }
    }
}
