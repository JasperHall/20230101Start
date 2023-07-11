/**
 * https://leetcode.cn/problems/fibonacci-number/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/7/11 18:15
 */
public class 斐波那契数 {  // 509. 斐波那契数
    class Solution {
        /**
         * 递归
         * @param n
         * @return
         */
        public int fib(int n) {
            if (n == 0){
                return 0;
            }
            if (n == 1){
                return 1;
            }

            return fib(n-1) + fib(n-2);
        }

        /**
         * 动态规划
         * 非压缩状态版本
         * @param n
         * @return
         */
        public int fib2(int n) {
            if (n <= 1) return n;

            int[] dp = new int[n+1];
            dp[0] = 0;
            dp[1] = 1;
            for (int index=2; index<=n; index++){
                dp[index] = dp[index-1]+dp[index-2];
            }
            return dp[n];
        }

        /**
         * 动态规划
         * 压缩
         * @param n
         * @return
         */
        public int fib3(int n) {
            if (n<=1) return n;
            int a=0, b=1, c=0;
            for (int i=1; i<n; i++){
                c = a+b;
                a = b;
                b = c;
            }
            return c;
        }

    }

}
