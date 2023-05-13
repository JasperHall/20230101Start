package m2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/13 23:07
 */
//https://leetcode.cn/problems/subtract-the-product-and-sum-of-digits-of-an-integer
public class 整数的各位积和之差 {
    public static void main(String[] args) {
        Solution sol = new Solution();
//        System.out.println(2/10);
        System.out.println(sol.subtractProductAndSum(234));
    }

    static class Solution {
        public int subtractProductAndSum(int n) {
            int sum = 0;
            int mul = 1;
            while (n > 0){
                sum = sum + (n % 10);
                mul = mul * (n % 10);
                n = n /10;
            }
            return mul-sum;
        }

        public int subtractProductAndSum2(int n) {
            String s=Integer.toString(n);

            int muti=1,sum=0;
            for(int i=0;i<s.length();i++){
                muti*=(s.charAt(i)-48);
                sum+=(s.charAt(i)-48);
            }
            return muti-sum;
        }
    }
}
