package m202301;

/**
 * https://leetcode.cn/problems/sqrtx/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/8 21:04
 */
public class x的平方根 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("int的最大值:"+Integer.MAX_VALUE);
        System.out.println(solution.mySqrt2(2147395599));
    }


    static class Solution{
        /**
         * 二分查找
         * @param x
         * @return
         */
        public int mySqrt1(int x) {
            int left=0, right=x, ans = -1;
            while (left <= right){
                int mid = right + (left-right)/2;
                if ((long)mid*mid <= x){//注意此处的类型转换，int范围有局限性
                    ans = mid;
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
            return ans;
        }

        /**
         * 外网高分解答,这种方法就避免了int转long
         * @param x
         * @return
         */
        public int mySqrt2(int x) {
            int i = 1;
            int j = x;
            int ans = 0;
            while (i <= j){
                int mid = i + (j-i)/2;
                // upper bound的形式，因为我们要找的ans要是最接近于x的最大的数，利用upper bound
                if (mid <= x/mid){
                    i = mid +1;
                    // 只要mid <= x/mid，left左边界就会一直向右移动，ans就会一直更新（变大），直到不在满足mid <= x/mid的条件为止，
                    // ans就会停止更新，永远停在满足mid<=x/mid条件下面的最后一次更新，即满足ans * ans <= mid的最大值。
                    ans = mid;
                } else{
                    j = mid-1;
                }
            }

            return ans;
        }
    }


}
