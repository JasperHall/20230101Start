package m2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/13 20:46
 */
//https://leetcode.cn/problems/number-of-1-bits/
public class 位1的个数 {
    public static void main(String[] args) {
        Solution solution = new Solution();

    }

    public static class Solution {
        //方法一：循环检查二进制位
        public int hammingWeight1(int n) {
            int ret = 0;
            for (int i = 0; i < 32; i++){
                if ((n & (1 << i)) != 0){// n 与运算 （1左移运算i位）
                    ret++;
                }
            }
            return ret;
        }

        //方法二：位运算优化
        public int hammingWeight2(int n) {
            int ret = 0;
            while (n != 0) {
                n &= n - 1;  //等价于 n=n&(n-1)
                ret++;
            }
            return ret;
        }
        //方法三：调用Integer.bitCount()API，计算二进制下有多少个
        public int hammingWeight3(int n) {
            return Integer.bitCount(n);
        }
    }
}
