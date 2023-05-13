package s2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/12/7 17:56
 */
//https://leetcode.cn/leetbook/read/array-and-string/cm5e2/
public class 实现strStr {

    class Solution {
        public int strStr(String haystack, String needle) {
            int n = haystack.length(), m = needle.length();
            for (int i = 0; i < n - m + 1; i++) {
                String sub = haystack.substring(i, i + m);
                if (sub.equals(needle)) return i;
            }
            return -1;
        }
    }
}
