package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/12/2 20:13
 */
//https://leetcode.cn/problems/matrix-diagonal-sum/
public class 矩阵对角线元素的和 {
    public static void main(String[] args) {
        int [][] a = {{1,2,3},{4,5,6},{7,8,9}};
        Solution s = new Solution();
        System.out.println(s.diagonalSum1(a));
    }

   static class Solution {
        //方法一：自己写的，内存消耗击败5%，用时击败100%
        public int diagonalSum1(int[][] mat) {
            int sum =0;
            for (int i=0; i<mat.length; i++){
                sum = sum+mat[i][i];
                sum = sum+mat[i][mat.length-1-i];
            }
            int len = mat.length;
            int m = (len-1)/2;
            if (len%2==1){
                sum = sum - mat[m][m];
            }
            return sum;
        }

        //方法二：内存击败32%，用时击败100
        public int diagonalSum2(int[][] mat) {
            int n = mat.length;
            int sum = 0, mid = n/2;
            for (int i=0; i<n; i++){
                sum +=mat[i][i] + mat[i][n-1-i];
            }
            return  sum - mat[mid][mid] * (n&1);//按位与运算
        }
    }
}
