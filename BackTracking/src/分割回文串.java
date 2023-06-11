import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * https://leetcode.cn/problems/palindrome-partitioning/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/11 11:34
 */
public class 分割回文串 {   // 131. 分割回文串

    class Solution {
        List<List<String>> result = new ArrayList<>();
        Deque<String> dequePath = new LinkedList<>();

        public List<List<String>> partition(String s) {
            backTracking(s, 0);
            return result;
        }
        private void backTracking(String s, int startIndex){
            // 如果起始位置大于s的大小，说明找到了一组分割方案
            if (startIndex >= s.length()){
                result.add(new ArrayList<>(dequePath));
                return;
            }

            for (int i=startIndex; i<s.length(); i++){
                // 如果是回文字符串则记录
                if (isPalindrome(s, startIndex, i)){
                    String str = s.substring(startIndex, i+1);
                    dequePath.addLast(str);
                }else {  // 如果不是则直接跳过
                    continue;
                }

                //起始位置后移，保证不重复
                backTracking(s, i + 1);  // 寻找i+1为起始位置的子串
                dequePath.removeLast();  // 回溯过程，弹出本次已经填在的子串
            }
        }

        // 判断是否是回文串的方法
        private boolean isPalindrome(String s, int startIndex, int end){
            for (int i=startIndex, j=end; i<j; i++,j--){
                if (s.charAt(i) != s.charAt(j)){
                    return false;
                }
            }
            return true;
        }
    }
}
