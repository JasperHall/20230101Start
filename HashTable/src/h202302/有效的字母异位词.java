package h202302;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/valid-anagram/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/1 19:40
 */
public class 有效的字母异位词 {

    class Solution {
        /**
         * 字典解法
         * 时间复杂度O(m+n) 空间复杂度O(1)
         * @param s
         * @param t
         * @return
         */
        public boolean isAnagram(String s, String t) {
            int[] record = new int[26];

            for (int i =0; i<s.length(); i++){
                record[s.charAt(i) - 'a']++;//并不需要记住字符a的ASCII，只要求出一个相对数值就可以了
            }
            for (int i=0; i<t.length(); i++){
                record[t.charAt(i) - 'a']--;
            }

            for (int count : record){
                if (count != 0){
                    return false;// record数组如果有的元素不为零0，说明字符串s和t 一定是谁多了字符或者谁少了字符。
                }
            }
            return true;// record数组所有元素都为零0，说明字符串s和t是字母异位词
        }

        /**
         * 排序
         * t 是 s 的异位词等价于「两个字符串排序后相等」。因此我们可以对字符串 s 和 t 分别排序，
         * 看排序后的字符串是否相等即可判断。此外，如果 s 和 t 的长度不同，t 必然不是 ss 的异位词。
         * @param s
         * @param t
         * @return
         */
        public boolean isAnagram2(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            char[] str1 = s.toCharArray();
            char[] str2 = t.toCharArray();
            Arrays.sort(str1);
            Arrays.sort(str2);
            return Arrays.equals(str1, str2);

        }
    }
}
