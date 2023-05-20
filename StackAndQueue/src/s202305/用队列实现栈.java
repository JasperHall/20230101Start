package s202305;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/implement-stack-using-queues/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/20 23:22
 */
public class 用队列实现栈 {  // 225

    /**
     * 使用两个Queue实现
     */
    class MyStack1 {

        Queue<Integer> queue1; // 和栈中保持一样元素的队列
        Queue<Integer> queue2; // 辅助队列

        //Initialize your data structure here
        public MyStack1() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        /**
         * 模拟入栈
         * Push element x onto stack.
         * @param x
         */
        public void push(int x) {
            queue2.offer(x); // 先放在辅助队列中  offer入队

            while(! queue1.isEmpty()){ // 当不为空的时候进入
                queue2.offer(queue1.poll()); // 将queue1的元素挨个出队, 放入queue2中
            }

            Queue<Integer> queueTemp;
            queueTemp = queue1;
            queue1 = queue2;
            queue2 = queueTemp; // 最后交换queue1和queue2, 将queue2的元素都放到queue1中
        }

        /**
         * 模拟出栈
         * Removes the element on top of the stack and returns that element.
         * @return
         */
        public int pop() {
            return queue1.poll();  // 因为queue1中得元素和栈中保持一致,所以这个和下面的操作只看queue1即可
        }

        /**
         * 模拟获取栈顶元素
         * Get the top element.
         * @return
         */
        public int top() {
            return queue1.peek();
        }

        /**
         * 模拟判断栈是否为空
         * Returns whether the stack is empty.
         * @return
         */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }

    /**
     * 使用一个Deque实现
     */
    class MyStack {
        // Deque 接口继承了 Queue 接口
        // 所以 Queue 中的 add、poll、peek等效于 Deque 中的 addLast、pollFirst、peekFirst
        Deque<Integer> que1;
        /** Initialize your data structure here. */
        public MyStack() {
            que1 = new ArrayDeque<>();
        }

        /**
         * 模拟入栈
         * Push element x onto stack.
         * @param x
         */
        public void push(int x) {
            que1.addLast(x);
        }

        /**
         * 模拟出栈
         * Removes the element on top of the stack and returns that element.
         * @return
         */
        public int pop() {
            int size = que1.size();
            size--;

            int res = que1.pollLast();
            return res;
        }

        /**
         * 模拟获取栈顶元素
         * Get the top element.
         * @return
         */
        public int top() {
            return que1.peekLast();
        }

        /**
         * 模拟判断栈是否为空
         * Returns whether the stack is empty.
         * @return
         */
        public boolean empty() {
            return que1.isEmpty();
        }
    }


}
