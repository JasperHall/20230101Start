package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/17 8:48
 */
//https://leetcode.cn/problems/rotate-image/
public class 旋转矩阵 {

    class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            // 深拷贝 matrix -> tmp
            int[][] tmp = new int[n][];
            for (int i = 0; i < n; i++)
                tmp[i] = matrix[i].clone();
            // 根据元素旋转公式，遍历修改原矩阵 matrix 的各元素
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[j][n - 1 - i] = tmp[i][j];
                }
            }
        }
    }
}
