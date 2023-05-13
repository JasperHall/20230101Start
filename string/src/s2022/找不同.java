package s2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/12/7 15:39
 */
//https://leetcode.cn/problems/find-the-difference/
public class 找不同 {

    static class Solution {
        //方法一：计数
        public char findTheDifference1(String s, String t) {
            int [] cnt = new int[26];
            for (int i=0; i<s.length(); i++){
                char ch = s.charAt(i);
                cnt[ch - 'a']++;
            }

            for (int i=0; i<t.length(); i++){
                char ch = t.charAt(i);
                cnt[ch - 'a']--;
                if (cnt[ch-'a']<0){
                    return ch;
                }
            }
            return ' ';
        }
        //方法二：求和
        public char findTheDifference2(String s, String t) {
            int as = 0, at = 0;
            for (int i=0; i<s.length(); i++){
                as += s.charAt(i);
            }
            for (int i=0; i<t.length(); i++){
                at += t.charAt(i);
            }
            return (char) (at-as);
        }


    }
}
