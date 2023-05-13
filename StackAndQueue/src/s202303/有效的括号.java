package s202303;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/16 9:30
 *
 * https://leetcode.cn/problems/valid-parentheses/
 */
public class 有效的括号 {

    class Solution {
        public boolean isValid(String s) {
            Deque<Character> deque = new LinkedList<>();
            char ch;
            for (int i=0; i<s.length(); i++){
                ch = s.charAt(i);
                //碰到左括号, 就把相应的右括号入栈
                if (ch == '('){
                    deque.push(')');
                }else if (ch == '{'){
                    deque.push('}');
                }else if (ch == '['){
                    deque.push(']');
                }else if (deque.isEmpty() || deque.peek() != ch){
                    return false;
                }else { //如果是右括号判断是否和栈顶元素匹配
                    deque.pop();
                }
            }
            //最后判断栈中的元素是否匹配
            return deque.isEmpty();
        }
    }
}
