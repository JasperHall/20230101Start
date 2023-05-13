package s202303;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/16 14:03
 *
 * https://leetcode.cn/problems/sliding-window-maximum/
 */
public class 滑动窗口最大值 {


    /**
     * 解法一
     * 自定义数组
     */
    class MyQueue {
        Deque<Integer> deque = new LinkedList<>();
        //弹出元素时, 比较当前要弹出的数值是否等于队列出口的数值, 如果相等则弹出
        //同时判断队列当前是否为空
        void poll(int val){
            if (! deque.isEmpty() && val==deque.peek()){//peek,返回队首元素 思考这里等号的作用,相等表示窗口到这个位置了,需要弹出一个让出位置
                deque.poll(); //poll出队
            }
        }
        //添加元素时, 如果要添加的元素大于入口处的元素, 就将入口元素弹出,保证队列元素单调递减
        //比如此时队列元素 3, 1,2 将要入队, 比1大, 所以1要弹出. 此时队列: 3,2
        void add(int val){
            while (!deque.isEmpty() && val>deque.getLast()){ //注意这里是while, 传入的参数挨个与队尾比大小
                deque.removeLast();
            }
            deque.add(val);
        }
        //队列队顶元素始终为最大值
        int peek(){
            return deque.peek(); //peek,返回队首元素
        }
    }
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums.length == 1){
                return nums;
            }
            int len = nums.length - k + 1;  // 注意这一步的计算逻辑
            //存放结果元素的数组
            int[] res = new int[len];
            int num = 0;
            //自定义队列
            MyQueue myQueue = new MyQueue();
            //先将前k的元素放入队列
            for (int i=0; i<k; i++){
                myQueue.add(nums[i]);  //调用自定义的方法
            }

            res[num++] = myQueue.peek();//调用自定义的方法, 此时设置 res[0]得值
            //开始滑动窗口
            for (int i=k; i<nums.length; i++){
                //滑动窗口移除最前面的元素，移除是判断该元素是否放入队列
                myQueue.poll(nums[i - k]);  //poll 出队  思考这里出队的用处
                //滑动窗口加入最后面的元素
                myQueue.add(nums[i]);  //add  入队
                //记录对应的最大值
                res[num++] = myQueue.peek();
            }
            return res;
        }
    }

    /**
     * 解法二
     * 利用双端队列手动实现单调队列
     *
     * 用一个单调队列来存储对应的下标，每当窗口滑动的时候，直接取队列的头部指针对应的值放入结果集即可
     * 单调队列类似 （tail -->） 3 --> 2 --> 1 --> 0 (--> head) (右边为头结点，元素存的是下标)
     */
    class Solution2 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            ArrayDeque<Integer> deque = new ArrayDeque<>();//队列存储的是 下标
            int n = nums.length;
            int[] res = new int[n - k + 1]; //结果数组的长度为 = 原数组长度-窗口长度+1
            int idx = 0;
            for(int i = 0; i < n; i++) {
                // 根据题意，i为nums下标，是要在[i - k + 1, i] 中选到最大值，只需要保证两点

                // 注意接下来是两个 while 循环
                // 1.队列头结点需要在[i - k + 1, i]范围内，不符合则要弹出
                while(!deque.isEmpty() && deque.peek() < i - k + 1){ //队首元素 < i - k + 1
                    deque.poll();//poll出队
                }
                // 2.既然是单调，就要保证每次放进去的数字要比末尾的都大，否则也弹出
                while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {//peekLast 看尾部最后一个元素是什么
                    deque.pollLast();//出去尾部最后一个元素
                }

                deque.offer(i);//入队

                // 因为单调，当i增长到符合第一个k范围的时候，每滑动一步都将队列头节点放入结果就行了
                if(i >= k - 1){
                    res[idx++] = nums[deque.peek()];  //获取队首元素
                }
            }
            return res;
        }
    }
}
