package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/18 23:12
 */
//https://leetcode.cn/leetbook/read/array-and-string/cuxq3/
public class 对角线遍历 {

    class Solution{
        public int[] findDiagonalOrder(int[][] mat){
            if (mat.length == 0){
                return new int[0];
            }
            int m = mat.length;
            int n = mat[0].length;
            //存放数组
            int[] ans = new int[m * n];
            //对角线方向次数
            int count = n+m-1;
            //定义初始化  行标记，列标记，存放数组索引
            int row = 0, col = 0, index = 0;
            //开始对角线循环
            for (int i = 0; i<count; i++){
                //判断对角线方向（因题目初始从右上（i=0）开始）：偶数右上，奇数左下
                if (i%2 == 0){
                    //右上操作
                    while(row >= 0 && col < n) {
                        //将矩阵数存入存放数组
                        ans[index] = mat[row][col];
                        //索引后移
                        index++;
                        //右上规律：行减一，列加一
                        row--;
                        col++;
                    }
                    //判断是否为越界情况：不越界正常行加一，越界行加二，列减一
                    //（此处不理解可以拿草稿纸将循环中的row和col值遍历写，对照矩阵图就会明白了
                    if (col < n){
                        row++;
                    }else {
                        row += 2;
                        col--;
                    }
                }
                //左下操作：规律与右上相反
                else {
                    while (row < m && col >=0){
                        ans[index] = mat[row][col];
                        index++;
                        row++;
                        col--;
                    }
                    if (row < m){
                        col++;
                    }else{
                     row--;
                     col += 2;
                    }
                }
            }
            //返回存放数组
            return ans;
        }
    }
}
