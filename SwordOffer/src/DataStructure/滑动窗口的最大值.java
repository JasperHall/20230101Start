package DataStructure;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/16 15:17
 */
public class 滑动窗口的最大值 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param num int整型一维数组
     * @param size int整型
     * @return int整型ArrayList
     */
    public ArrayList<Integer> maxInWindows (int[] num, int size) {
        // write code here

        ArrayList<Integer> res = new ArrayList<Integer>();
        //窗口大于数组长度的时候，返回空
        if(size <= num.length && size != 0){
            //双向队列
            ArrayDeque<Integer> dq = new ArrayDeque<Integer>();

            //先遍历一个窗口
            for(int i = 0; i < size; i++){
                // 去掉比自己先进队列的小于自己的值
                while(!dq.isEmpty() && num[dq.peekLast()] < num[i]) // 队列不为空, 且新来的值大于队尾元素
                    dq.pollLast();
                dq.add(i);  // 加入下标
            }

            //遍历后续数组元素
            for(int i = size; i < num.length; i++){  // i是下标, i < num.length
                //取窗口内的最大值
                res.add(num[dq.peekFirst()]); // 当时存入的是下标

                // dq.peekFirst() < (i - size + 1) 判断 first是否还在最新窗口范围内
                while(!dq.isEmpty() && dq.peekFirst() < (i - size + 1))  dq.pollFirst();  // 弹出窗口移走后的值

                //加入新的值前，去掉比自己先进队列的小于自己的值
                while(!dq.isEmpty() && num[dq.peekLast()] < num[i]) dq.pollLast();

                dq.add(i);
            }

            res.add(num[dq.pollFirst()]);
        }
        return res;
    }
}
