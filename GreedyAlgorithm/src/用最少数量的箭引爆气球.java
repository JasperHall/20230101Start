import java.util.Arrays;

/**
 * https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/7/10 15:57
 */
public class 用最少数量的箭引爆气球 {  // 452. 用最少数量的箭引爆气球
    class Solution {
        public int findMinArrowShots(int[][] points) {

            if (points.length == 0) return 0;

            // 根据气球直径的开始坐标从小到大排序
            // 使用Integer内置比较方法，不会溢出
            Arrays.sort(points, (a,b) -> Integer.compare(a[0], b[0]));

            int count = 1;  // points 不为空至少需要一支箭

            for (int i=1; i< points.length; i++){
                if (points[i][0] > points[i-1][1]){  // 气球i 和 气球i-1 不挨着，注意这里不是 >=
                    count++;  // 需要加一只剑
                } else {  // 气球i和气球i-1挨着
                    points[i][1] = Math.min(points[i][1], points[i-1][1]);   // 更新重叠气球 最小 右边界
                }
            }
            return count;
        }
    }
}
