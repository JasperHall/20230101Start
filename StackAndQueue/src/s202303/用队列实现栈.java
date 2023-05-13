package s202303;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/12 23:54
 *
 * https://leetcode.cn/problems/implement-stack-using-queues/
 */
public class 用队列实现栈 {

    /**
     * 使用两个Queue实现
     */
    class MyStack1 {

        Queue<Integer> queue1;//和栈中保持一样元素的队列
        Queue<Integer> queue2;//辅助队列

//        Initialize your data structure here
        public MyStack1() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }
        /** Push element x onto stack. */
        public void push(int x) {
            queue2.offer(x);//先放在辅助队列中  offer入队
            while(! queue1.isEmpty()){//当不为空的时候进入
                queue2.offer(queue1.poll());//poll出队
            }
            Queue<Integer> queueTemp;
            queueTemp = queue1;
            queue1 = queue2;
            queue2 = queueTemp;//最后交换queue1和queue2, 将元素都放到queue1中
        }
        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue1.poll();//因为queue1中得元素和栈中保持一致,所以这个和下面的操作只看queue1即可
        }
        /** Get the top element. */
        public int top() {
            return queue1.peek();
        }
        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty();
        }
    }

    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */

    /**
     * 使用一个Queue实现
     */
    class MyStack2 {

        Queue<Integer> queue;

        public MyStack2() {
            queue = new LinkedList<>();
        }

        //每 offer 一个数（A）进来，都重新排列，把这个数（A）放到队列的队首
        public void push(int x) {
            queue.offer(x);
            int size = queue.size();
            //移动除了 A 的其它数
            while (size-- > 1)
                queue.offer(queue.poll());
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

}
