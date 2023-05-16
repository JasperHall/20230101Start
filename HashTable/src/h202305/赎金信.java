package h202305;

/**
 * https://leetcode.cn/problems/ransom-note/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/16 16:08
 */
public class 赎金信 {  // 383
    class Solution {
        public boolean canConstruct(String ransomNote, String magazine) {
            int[] record = new int[26];

            for (int i=0; i<ransomNote.length(); i++){
                record[ransomNote.charAt(i) - 'a']++;
            }
            for (int i=0; i<magazine.length(); i++){
                record[magazine.charAt(i) - 'a'] --;
            }

            for (int i : record) {
                if (i>0){
                    return false;
                }
            }
            return true;
        }
    }
}
