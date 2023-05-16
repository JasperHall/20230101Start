package h202305;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/valid-anagram/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/16 14:17
 */
public class 有效字母的异位词 {  //242
    class Solution {
        /**
         * 排序法
         * @param s
         * @param t
         * @return
         */
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()){  //长度不相等肯定不为真
                return false;
            }

            char[] str1 = s.toCharArray();
            char[] str2 = t.toCharArray();
            Arrays.sort(str1);
            Arrays.sort(str2);
            return Arrays.equals(str1, str2);
        }

        /**
         * 字典法
         * @param s
         * @param t
         * @return
         */
        public boolean isAnagram2(String s, String t) {
            int[] record = new int[26];

            for (int i=0; i<s.length(); i++){
                record[s.charAt(i) - 'a']++;  //并不需要记住字符a的ASCII，只要求出一个相对数值就可以了
            }

            for (int i=0; i<t.length(); i++){
                record[t.charAt(i) - 'a']--;
            }

            for (int i : record) {
                if (i != 0){
                    return false;  // record数组如果有的元素不为零0，说明字符串s和t 一定是谁多了字符或者谁少了字符。
                }
            }
            return true;  // record数组所有元素都为零0，说明字符串s和t是字母异位词
        }
    }
}
