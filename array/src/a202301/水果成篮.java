package a202301;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.cn/problems/fruit-into-baskets/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/25 21:01
 */
public class 水果成篮 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] a = {1,1,1,2,2,3};
        int res = solution.totalFruit3(a);
        System.out.println(res);
    }

    static class Solution {
        /**
         * 自写，滑动窗口,超时
         * @param fruits
         * @return
         */
        public int totalFruit(int[] fruits) {
            int res = 0;
            int left = 0, right = 0;
            int[] temp = new int[2];//存放种类(两个篮子)

//            if (fruits.length == 1) return 1;

            for (;left<fruits.length; left++){
                temp[0] = fruits[left];
                right=left;
                for (; right<fruits.length; right++){
                    if (fruits[left] != fruits[right]){
                        temp[1] = fruits[right];
                        break;
                    }
                }

                for (; right<fruits.length; right++){
                    if (fruits[right] != temp[0] && fruits[right] != temp[1]){
                        break;
                    }
                }
                res = Math.max(res, right-1-left+1);
            }


            return res;
        }

        /**
         * 滑动窗口,看不懂
         */
        public int totalFruit2(int[] fruits) {
            int n = fruits.length, ans = 0;//长度，结果
            int[] cnts = new int[n+10];
            for (int i=0, j=0, tot =0; i<n; i++){
                if (++cnts[fruits[i]] == 1){
                    tot++;
                }
                while (tot >2){
                    if (--cnts[fruits[j++]]==0){
                        tot--;
                    }
                }
                ans = Math.max(ans, i-j+1);

            }
            return ans;
        }

        /**
         * 滑动窗口
         * @param fruits
         * @return
         */
        public int totalFruit3(int[] fruits) {
            int len = 0;//要返回的结果
            int left = 0;
            Set<Integer> set = new HashSet<>();//Set是不可重复集合
            for (int right=0; right<fruits.length; right++){
                set.add(fruits[right]);//当添加重复元素时会加不进去
                if (set.size()<=2){
                //注意这里方法体啥也不写，相当于直接结束这次if
                }else {//当set.size() > 2 时说明set中出现第三个不同的值
                    left = right - 1;
                    //这里的作用是让左指针退回到第三个值出现的边界处，比如[1,1,1,2,2,3,3,3,3,3],当right=5时,left退回到left=2
                    while (fruits[left] == fruits[right-1]){
                        left--;
                    }
                    left++;//这里left再加1，是为了给后面的循环做起点，比如[1,1,1,2,2,3,3,3,3,3],当right=5时，再往后left就是从left=3开始算
                    set.clear();//清空
                    //还是因为set的特性，比如[1,1,1,2,2,3,3,3,3,3]当righ=5 left=3时，set里只加fruits[right-1]=2和fruits[right]=3就行
                    set.add(fruits[right-1]);
                    set.add(fruits[right]);
                }
                len = Math.max(len, right-left+1);//len每次循环都会保存一个最长的，所以不怕丢掉情况
            }
            return len;
        }

    }
}
