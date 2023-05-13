package s2022;

/**
 * https://leetcode.cn/problems/reverse-string/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/12/7 18:29
 */
public class 反转字符串 {

    class Solution {
        public char[] reverseString(char[] s) {
            if (s.length==1){return s;}
            int i=0, j=s.length-1;
            while (i<j){
                char a = s[i];
                s[i] = s[j];//把后面赋值给前面
                s[j] = a;//把前面赋值给后面
                i++;
                j--;
            }
            return s;
        }
    }
}
