package DataStructure;

import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/4c776177d2c04c2494f2555c9fcc1e49?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/15 15:14
 */
public class 包含min函数的栈 {

    // 用于栈的push 和 pop
    Stack<Integer> s1 = new Stack<Integer>();
    // 用于存储最小min
    Stack<Integer> s2 = new Stack<Integer>();

    public void push(int node) {
        s1.push(node);
        //空或者新元素较小，则入栈
        if(s2.isEmpty() || s2.peek() > node){
            s2.push(node);
        } else{
            //重复加入栈顶
            s2.push(s2.peek());
        }
    }

    public void pop() {
        s1.pop();
        s2.pop();
    }

    public int top() {
        return s1.peek();
    }

    public int min() {
        return s2.peek();
    }
}
