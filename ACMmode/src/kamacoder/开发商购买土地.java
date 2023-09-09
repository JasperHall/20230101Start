package kamacoder;

import java.util.Scanner;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/8 12:00
 */
public class 开发商购买土地 {

    /**
     * 思路：计算出二维数组的二维前缀和，然后分别按行遍历和按列遍历，计算出最小值
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        long[][] landValues= new long[n][m];
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                landValues[i][j] = sc.nextInt();  // 输入每个位置上的数字
            }
        }

        // 当二维矩阵的大小为 1 时，只有一个开发商能拿到这块土地
        if (n==1 && m==1){
            System.out.println(landValues[0][0]);
        } else {
            System.out.println(findMinAverageDifference(landValues));
        }
        sc.close();
    }

    public static long findMinAverageDifference(long[][] landValues){
        int n = landValues.length;
        int m = landValues[0].length;
        long[][] prefixSumArray = new long[n][m];  // 二维前缀和数组
        long result = Integer.MAX_VALUE;  // 绝对值之差

        prefixSumArray[0][0] = landValues[0][0];  // 初始化, 将输入的二维数组0,0位置赋值给前缀和数组

        // 处理第一行  j从1开始
        for (int j=1; j<m; j++){
            prefixSumArray[0][j] = landValues[0][j] + prefixSumArray[0][j-1];
        }

        // 处理第一列  i从1开始
        for (int i=1; i<n; i++){
            prefixSumArray[i][0] = landValues[i][0] + prefixSumArray[i-1][0];
        }

        // 计算剩余的前缀和数组
        for (int i=1; i<n; i++){
            for (int j = 1; j < m; j++) {
                //                       1,1位置开始          0,1位置开始               1,0位置开始                0,0位置开始
                prefixSumArray[i][j] = landValues[i][j] + prefixSumArray[i-1][j] + prefixSumArray[i][j-1] - prefixSumArray[i-1][j-1];
            }
        }

        // 计算结果时，确保不会越界
        for (int i = 0; i < n; i++) {
            //
            result = Math.min(result, Math.abs(prefixSumArray[i][m-1] - (prefixSumArray[n-1][m-1] - prefixSumArray[i][m-1])));
        }
        for (int j = 0; j < m; j++) {
            result = Math.min(result, Math.abs(prefixSumArray[n-1][j] - (prefixSumArray[n-1][m-1] - prefixSumArray[n-1][j])));
        }

        return result;
    }

}
