package s202303;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/16 10:33
 *
 * https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/
 * 1047. 删除字符串中的所有相邻重复项
 */
public class 删除字符串中的所有相邻重复项 {  // 1047

    class Solution {
        /**
         * 使用 Deque 作为堆栈
         * @param s
         * @return
         */
        public String removeDuplicates(String s) {
            Deque<Character> deque = new LinkedList<>();
            char ch;
            for (int i=0; i<s.length(); i++){
                ch = s.charAt(i);
                if (deque.isEmpty() || deque.peek() != ch){
                    deque.push(ch);
                }else if (deque.peek() == ch){
                    deque.pop();
                }
            }
            String res = "";
            while (! deque.isEmpty()){
                res = deque.pop() + res; // 注意这里加的顺序
            }
            return res;
        }

        /**
         * 拿字符串直接作为栈，省去了栈还要转为字符串的操作
         * @param s
         * @return
         */
        public String removeDuplicates2(String s){
            //res当做栈
            //也可以用StringBuilder来修改字符串, 速度更快
            //StringBuilder res = new StringBuilder();
            StringBuilder res = new StringBuilder();
            //top为res的长度
            int top = -1;
            for (int i=0; i<s.length(); i++){
                char c = s.charAt(i);
                //当 top>0 时, 即栈中有字符, 当前字符如果和栈中字符相等, 弹出栈顶字符, 同时 top--
                if (top >= 0 && res.charAt(top) == c){
                    res.deleteCharAt(top);
                    top--;
                    //否则, 将该字符 入栈, 同时 top++
                }else {
                    res.append(c);
                    top++;
                }
            }
            return  res.toString();
        }

        /**
         * 双指针法  快慢指针
         * @param s
         * @return
         */
        public String removeDuplicates3(String s){
            char[] ch = s.toCharArray();//注意这个方法
            int fast = 0;
            int slow = 0;
            while (fast < s.length()){
                //直接用fast指针覆盖slow指针的值
                ch[slow] = ch[fast];
                //遇到前后相同值的, 就直接跳过, 即slow指针后退一步, 下次循环就可以直接被覆盖掉了
                if (slow > 0 && ch[slow] == ch[slow-1]){
                    slow--;
                }else {
                    slow++;
                }
                fast++;
            }
            return new String(ch, 0, slow);
        }

    }
}
