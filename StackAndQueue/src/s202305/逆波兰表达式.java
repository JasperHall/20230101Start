package s202305;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/evaluate-reverse-polish-notation/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/22 11:51
 */
public class 逆波兰表达式 {  // 150

    class Solution {
        public int evalRPN(String[] tokens) {
            Deque<Integer> stack = new LinkedList<>();

            for (String token : tokens) {
                if ("+".equals(token)){  // leetcode 内置jdk的问题，不能使用==判断字符串是否相等
                    stack.push(stack.pop() + stack.pop());
                }else if ("-".equals(token)){
                    stack.push(-stack.pop() + stack.pop());  // 注意 - 和/ 需要特殊处理
                }else if ("*".equals(token)){
                    stack.push(stack.pop() * stack.pop());
                }else if ("/".equals(token)){
                    int temp1 = stack.pop();  // 转换一下数据类型
                    int temp2 = stack.pop();
                    stack.push(temp2 / temp1);  // 注意这里除法的顺序
                }else {
                    stack.push(Integer.valueOf(token));
                }
            }
            return stack.pop();
        }
    }
}
