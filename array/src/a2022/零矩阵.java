package a2022;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/17 8:02
 */
/*
https://leetcode.cn/leetbook/read/array-and-string/ciekh/
 */
public class 零矩阵 {
    public static void main(String[] args) {

    }

    class Solution{
        public void setZeroes(int[][] matrix){
            int a = matrix.length;//行的长度row
            int b = matrix[0].length;//列的长度列column

            int[] zeroA = new int[a];
            int[] zeroB = new int[b];
            Arrays.fill(zeroA,0);
            Arrays.fill(zeroB,0);

            for(int i=0; i<a; i++){
                for (int j=0; j<b;j++){
                    if (matrix[i][j]==0){
                        zeroA[i] = 1;
                        zeroB[j] = 1;
                    }
                }
            }
            for (int i=0;i<a; i++){
                for (int j=0; j<b; j++){
                    if (zeroA[i]==1 || zeroB[j]==1){
                        matrix[i][j]=0;
                    }
                }
            }
        }
    }
}
