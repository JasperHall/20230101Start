package s202305;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/22 0:16
 */
public class 删除字符串中的所有相邻重复项 {  // 1047
    class Solution {
        public String removeDuplicates(String s) {
            Deque<Character> deque = new LinkedList<>();
            char ch;
            for (int i=0; i<s.length(); i++){
                ch = s.charAt(i);
                if (deque.isEmpty() || deque.peek()!=ch){
                    deque.push(ch);
                }else if (deque.peek() == ch){
                    deque.pop();
                }
            }
            String res = "";
            while (!deque.isEmpty()){  // 不是空就一直加长字符串
                res = deque.pop() + res; // 注意这里 相加 的顺序
            }
            return res;
        }
    }
}
