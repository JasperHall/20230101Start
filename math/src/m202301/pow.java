package m202301;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/15 19:35
 */
//https://leetcode.cn/problems/powx-n/
public class pow {
    public static void main(String[] args) {
        Solution solution = new Solution();
        double res = solution.myPow1(5.0,4);
        System.out.println(res);
    }

    static class Solution {
        public double myPow1(double x, int n) {
            long N = n;
            return N >= 0 ? quickMul1(x, N) : 1.0 / quickMul1(x, -N);
        }

        public double quickMul1(double x, long N) {
            if (N == 0) {
                return 1.0;
            }
            double y = quickMul1(x, N / 2);
            return N % 2 == 0 ? y * y : y * y * x;
        }
    }



}
