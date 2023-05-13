package m202301;

/**
 * https://leetcode.cn/problems/valid-perfect-square/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/15 21:28
 */
public class 有效的完全平方数 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean a = solution.isPerfectSquare1(16);
        System.out.println(a);
    }

    static class Solution {
        /**
         * 自写，不全正确
         * @param num
         * @return
         */
        public boolean isPerfectSquare1(int num) {
            if (num ==1 ) return true;//排除自身的情况
            int left=0, right = num;
            while (left < right){
                int mid = left + (right - left) / 2;
                if (mid * mid > num){
                    right = mid;
                }else if (mid * mid == num){
                    return true;
                }else {
                    left = mid+1;
                }
            }
            return false;
        }

        /**
         * 二分
         */
        public boolean isPerfectSquare2(int num){
            long left=0, right=num;//注意这里的long
            while (left < right){
                long mid = left+right+1 >> 1;//注意这里的long
                if (mid * mid >num){
                    right = mid-1;
                }else {
                    left = mid;
                }
            }
            return right * right == num;
        }
    }
}
