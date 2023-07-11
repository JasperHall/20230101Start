/**
 * https://leetcode.cn/problems/climbing-stairs/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/7/11 19:53
 */
public class 爬楼梯 {  // 70. 爬楼梯
    class Solution {
        /**
         * 自写
         * @param n
         * @return
         */
        public int climbStairs(int n) {
            if (n <=1 ) return n;
            int[] dp = new int[n+1];
            dp[1] = 1;
            dp[2] = 2;
            for (int index= 3; index<=n; index++){
                dp[index] = dp[index-1] + dp[index-2];
            }
            return dp[n];
        }

        /**
         * 常规方式
         * @param n
         * @return
         */
        public int climbStairs2(int n) {
            int[] dp = new int[n + 1];  // 因为0是在题目中是不算数的, 但是在数组中还是要有0位置, 所以要长度+1
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }

        /**
         * 用变量记录代替数组
         * @param n
         * @return
         */
        public int climbStairs3(int n) {
            if (n<=2) return n;
            int a=1, b=2, res=0;

            for (int i=3; i<=n; i++){
                res = a+b;
                a = b;
                b = res;
            }
            return res;
        }
    }
}
