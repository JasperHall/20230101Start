package kamacoder;

import java.util.Scanner;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/10 17:33
 */
public class 打印二维数组 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int count = 1;
        int[][] array = new int[n][m];

        // 第一步, 第一行从左到右进行遍历
        for (int i=0; i<m-1; i++){  // 注意这里小于m-1
            int currentY = i;  // 控制横向
            int currentX = 0;  // 控制竖向

            // 保证填充的元素位置不越界
            while (currentX != n && currentY != -1){
                array[currentX][currentY] = count;
                count++;
                currentX++;
                currentY--;
            }
        }

        // 第二步, 最后一列从上到下进行遍历
        for (int i=0; i<n; i++){  // 这里是以n来划定范围, n表示的是行数, 也就是竖向的
            int currentX = i;  // 控制竖向
            int currentY = m-1;  // 控制横向

            // 保证填充的元素位置不越界
            while (currentY != -1 && currentX != n) {
                array[currentX][currentY] = count;
                count++;
                currentX++;
                currentY--;
            }
        }

        // 打印数组
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%d ", array[i][j]);
            }
            System.out.println();
        }

    }
}

