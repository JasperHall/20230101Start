/**
 * https://leetcode.cn/problems/wiggle-subsequence/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/21 21:16
 */
public class 摆动序列 {  // 376. 摆动序列

    class Solution {
        public int wiggleMaxLength(int[] nums) {
            if (nums.length <= 1) return nums.length;

            int curDiff = 0; // 当前一对差值
            int preDiff = 0; // 前一对差值(上一个差值)
            int result = 1;  // 记录峰值个数，序列默认序列最右边有一个峰值

            for (int i=0; i<nums.length-1; i++){
                // 得到当前差值
                curDiff = nums[i+1] - nums[i];
                // 如果当前差值和上一个差值为一正一负
                // 等于0的情况表示初始时的preDiff
                if ((preDiff<=0 && curDiff>0) || (preDiff>=0 && curDiff<0)){
                    result++;
                    preDiff = curDiff;
                }
            }
            return result;
        }
    }

}
