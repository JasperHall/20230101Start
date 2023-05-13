package a202301;

/**
 * https://leetcode.cn/problems/spiral-matrix-ii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/26 11:21
 */
public class 陀螺矩阵 {
    class Solution {
        /**
         * 代码随想录
         * @param n
         * @return
         */
        public int[][] generateMatrix1(int n) {
            int loop = 0;//控制循环次数
            int[][] res = new int[n][n];//结果数组
            int start = 0;//每次循环的开始位置，（start, start）
            int count = 1;//定义填充数字
            int i, j;//i表示行，j表示列

            while (loop++ < n/2){// n/2计算转几圈,loop从1开始
                //模拟上侧的从左到右
                for (j=start; j<n-loop; j++){//j<n-loop,用来控制左闭右开，最后减去一个位置的元素
                    res[start][j] = count++;
                }
                //模拟右侧的从上到下
                for (i=start; i<n-loop; i++){
                    res[i][j] = count++;//j是全局变量，从j上一次的位置来固定是哪一列
                }
                //模拟下侧从右到左
                for (; j>=loop; j--){//注意这里的大于等于
                    res[i][j] = count++;
                }
                //模拟右侧从下到上
                for (; i>=loop; i--){//注意这里的大于等于
                    res[i][j] = count++;
                }
                start++;
                //loop++在while里写了
            }
            if (n % 2 ==1){
                res[start][start] = count;
            }
            return res;

        }
    }
}

