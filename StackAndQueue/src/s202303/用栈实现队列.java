package s202303;

import java.util.Stack;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/12 23:33
 *
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 */
public class 用栈实现队列 {

    class MyQueue {
        Stack<Integer> stackIn;
        Stack<Integer> stackOut;

        /** Initialize your data structure here. */
        public MyQueue() {
            stackIn = new Stack<>();//负责入栈
            stackOut = new Stack<>();//负责出栈
        }
        /** Push element x to the back of queue. */
        public void push(int x) {
            stackIn.push(x);
        }
        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            dumpstackIn();//调用自定义的方法
            return stackOut.pop();
        }
        /** Get the front element. */
        public int peek() {
            dumpstackIn();//调用自定义的方法
            return stackOut.peek();
        }
        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stackIn.isEmpty() && stackOut.isEmpty();
        }

        // 如果stackOut为空，那么将stackIn中的元素全部放到stackOut中
        private void dumpstackIn(){
            if (! stackOut.isEmpty()){
                return;
            }
            while (! stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
    }

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */
}
