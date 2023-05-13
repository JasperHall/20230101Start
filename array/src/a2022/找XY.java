package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/18 9:02
 */

//https://leetcode.cn/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/
public class 找XY {
    static class Solution {
        public int nearestValidPoint(int x, int y, int[][] points) {

            int min = Integer.MAX_VALUE;//先把min设置为integer的最大值
            int ans = -1;//根据题目要求，啥也不操作的话返回-1
            for (int i=0; i<points.length; i++){
                int[] point = points[i];//遍历points,把一对对的坐标复制给point
                int a =point[0], b=point[1];//取point的第一个数相当于x，第二个数相当于y

                if (a==x || b==y){
                    int dis = Math.abs(a-x)+Math.abs(b-y);
                    if (dis < min){
                        ans = i;
                        min = dis;

                    }
                }
            }

            return ans;
        }
    }
}
