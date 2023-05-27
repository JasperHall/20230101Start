package s202305;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/valid-parentheses/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/21 23:34
 */
public class 有效的括号 {  // 20

    class Solution {
        public boolean isValid(String s) {
            Deque<Character> deque = new LinkedList<>();  // 注意Deque对象的声明, 泛型使用Character
            char ch;
            for (int i=0; i<s.length(); i++){
                ch = s.charAt(i);
                // 碰到 左括号, 就把相应的右括号入栈
                if (ch == '('){
                    deque.push(')');
                }else if (ch == '{'){
                    deque.push('}');
                }else if (ch == '['){
                    deque.push(']');
                }else if (deque.isEmpty() || deque.peek() != ch){
                    // 如果此时deque是空这说明进右括号的时候是空,则绝对返回false,
                    // 如果deque.peek() != ch 则说明栈顶元素不相等, 绝对返回false
                    return false;
                }else {
                    //进到这里说明是右括号, 且有对应的左括号, 弹出就好了
                    deque.pop();   // Deque的poll和pop都是弹出
                }
            }
            return deque.isEmpty(); // 最后判断栈中的元素是否匹配, 最后是空则就是都匹配完成, 返回true
        }
    }
}
