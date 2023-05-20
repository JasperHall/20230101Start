package s202305;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/20 22:08
 */
public class 用栈实现队列 {  // 232


    class MyQueue {

        Stack<Integer> stackIn;
        Stack<Integer> stackOut;

        public MyQueue() {
            stackIn = new Stack<>(); // 负责模拟入队
            stackOut = new Stack<>();  // 负责模拟出队
        }

        /**
         * 模拟入队
         * Push element x to the back of queue.
         * @param x
         */
        public void push(int x) {
            stackIn.push(x);
        }

        /**
         * 模拟出队
         * Removes the element from in front of queue and returns that element.
         * @return
         */
        public int pop() {
            dumpstackIn();  // 调用自定义的方法
            return stackOut.pop();
        }

        /**
         * 模拟获取队尾元素
         * Get the front element.
         * @return
         */
        public int peek() {
            dumpstackIn(); // 调用自定义的方法
            return stackOut.peek();
        }

        /**
         * 判断是否为空
         * Returns whether the queue is empty.
         * @return
         */
        public boolean empty() {
            return stackIn.isEmpty() && stackOut.isEmpty();
        }

        // 如果stackOut为空，那么将stackIn中的元素全部放到stackOut中
        private void dumpstackIn(){
            if (! stackOut.isEmpty()){
                return;  // 如果stackOut不为空, 这直接回到原方法进行操作
            }

            // 如果执行到这一步, 说明上面的if没有进入, 则说明stackOut是空的,也就是无法出栈,
            // 此时需要将stackIn中的挨个弹出, 放入stackOut中,然后回到原方法操作stackOut, 就实现了队列的先进先出
            while (! stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
    }
}
