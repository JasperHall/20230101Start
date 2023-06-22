/**
 * https://leetcode.cn/problems/jump-game/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/22 10:43
 */
public class 跳跃游戏 {  // 55. 跳跃游戏
    class Solution {
        /**
         * 贪心
         * @param nums
         * @return
         */
        public boolean canJump(int[] nums) {
            if (nums.length == 1) {
                return true;
            }
            //覆盖范围, 初始覆盖范围应该是0，因为下面的迭代是从下标0开始的
            int coverRange = 0;
            //在覆盖范围内更新最大的覆盖范围
            for (int i = 0; i <= coverRange; i++) {
                coverRange = Math.max(coverRange, i + nums[i]);
                if (coverRange >= nums.length - 1) {
                    return true;
                }
            }
            return false;
        }
    }
}
