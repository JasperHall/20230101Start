package s202305;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * https://leetcode.cn/problems/sliding-window-maximum/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/27 14:17
 */
public class 滑动窗口最大值 {  // 239

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{-7,-8,7,5,7,1,6,0};  // [-7,-8,7,5,7,1,6,0]
        int k = 4;
        int[] res = solution.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(res));
    }

    static class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums.length == 1){
                return nums;  // 如果数组长度为1, 则直接返回
            }

            int len = nums.length - k + 1;  // 注意这一步的计算逻辑,求出结果数组的长度
            int[] res = new int[len];  // res是用来存放结果元素的数组
            int num = 0;

            MyQueue myQueue = new MyQueue(); // 自定义队列

            // 先将前k个元素放入队列
            for (int i=0; i<k; i++){
                myQueue.add(nums[i]);  // 调用自定义的方法
            }
            //调用自定义的方法, 此时设置 res[0]得值
            res[num++] = myQueue.peek();  // 注意num++的使用,先赋值,再+1

            // 开始滑动窗口
            for (int i=k; i<nums.length; i++){
                // 滑动窗口移除最前面的元素，移除时判断是否该移除目前队列的队首, 当目前队列的队首等于窗口的边界时就移除
                myQueue.poll(nums[i-k]);  // poll 出队  思考这里出队的思路, i指向当前要判断加入的元素位置, 减k代表窗口的左侧
                // 滑动窗口加入最后面的元素
                myQueue.add(nums[i]);  //add  入队
                // 记录对应的最大值
                res[num++] = myQueue.peek();
            }
            return res;
        }
    }
    /**
     * 自定义队列
     */
    static class MyQueue {
        Deque<Integer> deque = new LinkedList<>();

        // 自定义弹出元素的方法
        void poll(int val){
            // 弹出元素时, 比较当前要弹出的数值是否 等于 队列出口处的数值, 如果相等则弹出, 同时判断队列现在是否为空
            if (!deque.isEmpty() && val==deque.peek()){  // peek,返回队首元素 思考这里等号的作用,相等表示窗口到这个位置了,需要弹出一个让出位置
                deque.poll();  // 出队方法
            }
        }

        // 自定义加入元素的方法
        void add(int val){
            // 添加元素时,如果要添加的元素大于入口处的元素,就将入口元素弹出,保证队列元素单调递减
            //比如此时队列元素 3,1,2 将要入队, 2比1大, 所以1要弹出. 此时队列: 3,2
            while (!deque.isEmpty() && val>deque.getLast()){  // 注意这里是while循环, 传入的参数挨个与队尾比大小, 直到没有比新传入的元素小的元素
                deque.removeLast();  // 新传入的元素比原来队尾的元素大,则弹出队尾元素
            }
            deque.add(val);
        }

        // 队列的队首元素始终为最大值
        int peek(){
            return deque.peek();  // peek方法, 返回队首元素
        }
    }
}
