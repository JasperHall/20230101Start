import java.util.Arrays;

/**
 * https://leetcode.cn/problems/non-overlapping-intervals/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/7/10 19:25
 */
public class 无重叠区间 {  // 435. 无重叠区间
    class Solution {
        /**
         * 按照右边界进行排序
         * @param intervals
         * @return
         */
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

            int count = 1;  // 记录非交叉区间的个数

            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] < intervals[i-1][1]){  // 后面区间的左边界值 小于 前面区间的右边界值 说明存在重叠部分
                    // 把当前区间的右边界值 赋值为 当前区间和上一个区间的右边界中较小的一个
                    intervals[i][1] = Math.min(intervals[i-1][1], intervals[i][1]);
                    continue;
                } else {
                    count++;
                }
            }
            return intervals.length - count;  // 总长度减去没有交叉的区间个数, 就是要移除的重叠区间
        }
    }
}
