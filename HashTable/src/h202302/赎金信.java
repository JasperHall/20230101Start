package h202302;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/ransom-note/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/4 10:56
 */
public class 赎金信 {

    class Solution {
        /**
         * 写法一
         * @param ransomNote
         * @param magazine
         * @return
         */
        public boolean canConstruct1(String ransomNote, String magazine) {
            int[] arr = new int[26];
            int temp;

            for (int i=0; i<magazine.length(); i++){
                temp = magazine.charAt(i) - 'a';
                arr[temp]++;
            }

            for (int i=0; i<ransomNote.length(); i++){
                temp = ransomNote.charAt(i) - 'a';

                if (arr[temp] > 0){
                    arr[temp]--;
                }else {
                    return false;
                }
            }
            return true;
        }

        /**
         * 写法二
         * @param ransomNote
         * @param magazine
         * @return
         */
        public boolean canConstruct2(String ransomNote, String magazine) {
            // 定义一个哈希映射数组
            int[] record = new int[26];

            // 遍历
            for(char c : magazine.toCharArray()){
                record[c - 'a'] += 1;
            }

            for(char c : ransomNote.toCharArray()){
                record[c - 'a'] -= 1;
            }

            // 如果数组中存在负数，说明ransomNote字符串总存在magazine中没有的字符
            for(int i : record){
                if(i < 0){
                    return false;
                }
            }

            return true;
        }
    }
}
