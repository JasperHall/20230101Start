package m2022;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/happy-number/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/18 10:39
 */
public class 快乐数 {

    public static void main(String[] args) {
        int n =19;
        Solution s = new Solution();
        System.out.println(s.isHappy2(n));
    }

    static class Solution {
        /**
         * 方法一：用哈希集合检测循环
         */
        public boolean isHappy1(int n) {
            Set<Integer> seen = new HashSet<>();
            while (n!=1 && !seen.contains(n)){
                seen.add(n);
                n = getNext1(n);//调用方法
            }
//            if (seen.contains(n)){
//                return false;
//            }
            return n==1;
        }
        private int getNext1(int n){
            int totalSum = 0;
            while (n > 0){
                int d = n%10; //取到最后一位的数值
                n = n/10;//每次除以10，减少一位
                totalSum += d * d;
            }
            return  totalSum;
        }


        /**
         * 方法二：快慢指针,（运行超时
         */
        public boolean isHappy2(int n){
            int slow = n;
            int fast = getNext2(n);//调用自定义的方法
            while(fast !=1 && slow != fast){
                slow = getNext2(slow);//调用自定义的方法
                fast = getNext2(getNext2(fast));
            }
            return fast == 1;
        }
        public int getNext2(int n){
            int totalSum = 0;
            while (n>0){
                int d = n%10;
                n = n /10;
                totalSum += d*d;
            }
            return totalSum;
        }

    }
}
