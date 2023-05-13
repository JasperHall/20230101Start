package s2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/12/3 12:48
 */
//https://leetcode.cn/problems/merge-strings-alternately/
public class 交替合并字符串 {

    static class Solution {
        public String mergeAlternately1(String word1, String word2) {
            int m = word1.length(), n = word2.length();
            int i = 0, j = 0;
            StringBuilder ans = new StringBuilder();
            while (i<m || j<n){
                if (i<m){
                    ans.append(word1.charAt(i));
                    ++i;
                }
                if (j<n){
                    ans.append(word2.charAt(j));
                    ++j;
                }
            }
            return ans.toString();
        }
    }
}
