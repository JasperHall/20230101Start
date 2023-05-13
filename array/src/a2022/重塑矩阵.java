package a2022;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/12/2 21:37
 */
//https://leetcode.cn/problems/reshape-the-matrix/
public class 重塑矩阵 {
    public static void main(String[] args) {
        int [][] mat = {{1,2},{3,4},{5,8}};
        int r = 1;
        int c = 4;
        Solution s = new Solution();
//        System.out.println(Arrays.toString(s.matrixReshape2(mat,r,c)));
        System.out.println(Arrays.toString(s.test(mat)));
    }

    static class Solution {
        //方法一：错误解法
        /*public int[][] matrixReshape1(int[][] mat, int r, int c) {
            int len1 = mat.length;
            int len2 = mat[0].length;
            int [][] res = new int[r][c];
            int m = 0, n = 0;
            if (len1*len2 == r*c) {
                while (m<r) {
                    while (n < c) {
                        for (int i = 0; i < len1; i++) {
                            for (int j = 0; j < len2; j++) {
                                res[m][n] = mat[i][j];
                            }
                        }
                        if (n!=c-1) n++;
                        if (n == c-1) n=0;
                    }
                    if (m != r-1) m++;
                    if (m == r-1) break;
                }
                return res;
            }else{
                return mat;
            }
        }*/

        //方法二：二维数组的一维表示
        public int[][] matrixReshape2(int[][] mat, int r, int c) {
            int m = mat.length;
            int n = mat[0].length;
            if (m*n != r*c){
                return mat;
            }
            int[][] ans = new int[r][c];
            for (int x=0; x<m*n; x++){
                ans[x/c][x%c] = mat[x/n][x%n];
            }
            return ans;
        }
        //方法三
        public int[][] matrixReshape3(int[][] mat, int r, int c) {
            int m = mat.length;int n = mat[0].length;//m为矩阵的行数，n为矩阵的列数
            if(m*n != r*c){//如果数目不相等，则返回原矩阵
                return mat;
            }
            int[][] ans = new int[r][c];
            int i = 0;int j = 0;
            for(int[] x : mat){
                for(int w : x){
                    ans[i][j++] = w;
                    if(j == c){
                        i++;
                        j = 0;
                    }
                }

            }
            return ans;
        }
        //二维数组转为一维
        public int[] test(int[][] mat){
            int i=0;
            int[] a = new int[mat.length*mat[0].length];
            for (int[] x : mat){
                if (i<mat.length){
                    a = x;
                }
                i++;
            }
            return a;
        }
    }
}
