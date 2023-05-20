package s202305;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/reverse-string-ii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/18 0:49
 */
public class 反转字符串II {  // 541
    class Solution {
        public String reverseStr(String s, int k) {
            char[] chars = s.toCharArray(); // 将字符串转为数组
            System.out.println("看看字符串转为数组后是什么样的 : "+ Arrays.toString(chars));

            for (int i=0; i<chars.length; i+=2*k){  // 注意这里i的变化方式
                int start = i;
                // 判断尾数够不够k个来取决end指针的位置
                int end = Math.min(chars.length-1, start+k-1); // 取小值, 比如+k后的长度大于了原来的字符串长度, 则反转的最后位置就按照字符串的长度全部反转
                while (start < end){
                    char temp = chars[start];
                    chars[start] = chars[end];
                    chars[end] = temp;

                    start++;
                    end--;
                }
            }
            return new String(chars);
        }
    }
}
