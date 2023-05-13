package s202303;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/16 12:18
 *
 * https://leetcode.cn/problems/evaluate-reverse-polish-notation/
 */
public class 逆波兰表达式求值 {

    class Solution {
        public int evalRPN(String[] tokens) {

            Deque<Integer> stack = new LinkedList<>();

            for (String s : tokens){
                if ("+".equals(s)){// leetcode 内置jdk的问题，不能使用==判断字符串是否相等
                    stack.push(stack.pop() + stack.pop());  // 注意 - 和/ 需要特殊处理
                }else if ("-".equals(s)){
                    stack.push(-stack.pop()+stack.pop());
                }else if ("*".equals(s)){
                    stack.push(stack.pop() * stack.pop());
                }else if ("/".equals(s)){
                    int temp1 = stack.pop();
                    int temp2 = stack.pop();
                    stack.push(temp2 / temp1);
                }else {
                    stack.push(Integer.valueOf(s));
                }
            }
            return stack.pop();
        }
    }
}
