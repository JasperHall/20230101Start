/**
 * https://leetcode.cn/problems/candy/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/29 0:59
 */
public class 分发糖果 {  // 135. 分发糖果
    class Solution {
        public int candy(int[] ratings) {
            int len = ratings.length;
            int[] candyVec = new int[len];  // 糖果数组

            candyVec[0] = 1;  // 初始化为1, 最少的糖果为1

            for (int i = 1; i < len; i++) {
                candyVec[i] = (ratings[i] > ratings[i - 1]) ? candyVec[i - 1] + 1 : 1;
            }

            for (int i = len - 2; i >= 0; i--) {
                if (ratings[i] > ratings[i + 1]) {
                    // 这里第二次反着遍历要取糖果数的最大值
                    candyVec[i] = Math.max(candyVec[i], candyVec[i + 1] + 1);
                }
            }

            int ans = 0;
            for (int num : candyVec) {  // 最后要返回消耗的最少糖果数
                ans += num;
            }
            return ans;
        }
    }
}
