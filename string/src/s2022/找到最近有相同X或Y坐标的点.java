package s2022;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/12/2 9:40
 */
public class 找到最近有相同X或Y坐标的点 {

    public static void main(String[] args) {
        int [][] a = {{1,3},{2,5}};
        System.out.println(a[1][0]);
    }

    class Solution {
        public int nearestValidPoint(int x, int y, int[][] points) {
            int min = Integer.MAX_VALUE;
            int n = -1;
            for (int i=0; i<points.length; i++){
                int point[] = points[i];
                int a = point[0], b = point[1];
                //判断有一个轴的坐标相等
                if (a==x || b==y){
                    int l = Math.abs(x-a)+Math.abs(y-b);//计算距离（不用平方了，直接用绝对值代替）
                    if (l<min){
                        min=l;
                        n = i;
                    }
                }
            }

            return n;
        }
    }
}
