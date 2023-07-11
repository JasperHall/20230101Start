import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/merge-intervals/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/7/10 22:56
 */
public class 合并区间 {  // 56. 合并区间
    class Solution {
        public int[][] merge(int[][] intervals) {
            List<int[]> res = new LinkedList<>();
            // 按照左边界进行排序
            Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

            // initial start 是最小左边界
            int start = intervals[0][0];  // 初始区间的左边界
            int rightmostRightBound = intervals[0][1];  // 初始区间的右边界

            for (int i = 1; i < intervals.length; i++) {
                // 如果下一个区间的左边界 大于 上一个的右边界
                if (intervals[i][0] > rightmostRightBound){
                    // 直接加入区间, 并更新start
                    res.add(new int[]{start, rightmostRightBound});  // add进去一个长度为2的一维数组, 两个数分别表示区间的左右边界
                    start = intervals[i][0];
                    rightmostRightBound = intervals[i][1];
                } else {
                    // 右重叠的情况
                    // 更新最大右边界
                    rightmostRightBound = Math.max(rightmostRightBound, intervals[i][1]);
                }
            }
            res.add(new int[]{start, rightmostRightBound});
            return res.toArray(new int[res.size()][]);
        }
    }
}
