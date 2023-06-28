/**
 * https://leetcode.cn/problems/gas-station/description/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/23 0:24
 */
public class 加油站 {  // 134. 加油站
    class Solution {
        /**
         * 方法一 不是真正意义上的贪心
         * @param gas
         * @param cost
         * @return
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int sum = 0;
            int min = 0;
            for (int i = 0; i < gas.length; i++) {
                sum += (gas[i] - cost[i]);  // gas[i] - cost[i] 一天剩下的油
                min = Math.min(sum, min);
            }

            // 仅仅总和大也是不行的, 因为万一第一天油很少不够, 但是后面的都很多富裕就会出现油很多但是从0点开始走走不到
            if (sum<0) return -1;  // 如果gas的总和小于cost总和，那么无论从哪里出发，一定是跑不了一圈的
            if (min >=0) return 0;  // 此时说明从0位置开始走可以回到起点

            for (int i = gas.length-1; i > 0; i--) {
                // 如果累加的最小值是负数，汽车就要从非0节点出发，从后向前，看哪个节点能把这个负数填平，能把这个负数填平的节点就是出发节点。
                min += (gas[i]-cost[i]);
                if (min>=0) return i;
            }

            return -1;
        }

        /**
         * 方法二 贪心
         * @param gas
         * @param cost
         * @return
         */
        public int canCompleteCircuit2(int[] gas, int[] cost) {
            int curSum = 0; // 当前总和
            int totalSum = 0; // 所有总和
            int index = 0;  // 指针

            for (int i = 0; i < gas.length; i++) {
                curSum += (gas[i] - cost[i]);
                totalSum += (gas[i] - cost[i]);
                if (curSum<0){
                    index = i+1;   // 也可以写: index = (i + 1) % gas.length ;
                    curSum = 0;
                }
            }
            if (totalSum<0) return -1;  // // 如果gas的总和小于cost总和，那么无论从哪里出发，一定是跑不了一圈的
            return index;
        }
    }
}
