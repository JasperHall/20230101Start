package DataStructure;

import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/15 14:55
 */
public class 用两个栈实现队列 {

    Stack<Integer> stack1 = new Stack<Integer>();  // 辅助
    Stack<Integer> stack2 = new Stack<Integer>();  // 实际存放

    public void push(int node) {
        if(stack2.isEmpty()){
            stack2.push(node);
        } else {
            while (!stack2.isEmpty()){
                int temp = stack2.pop();
                stack1.push(temp);
            }
            stack2.push(node);
            while (!stack1.isEmpty()){
                int temp = stack1.pop();
                stack2.push(temp);
            }
        }
    }

    public int pop() {
        return stack2.pop();
    }
}
