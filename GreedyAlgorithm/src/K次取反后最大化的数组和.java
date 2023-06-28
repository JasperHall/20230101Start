import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/22 15:11
 */
public class K次取反后最大化的数组和 {  // 1005. K 次取反后最大化的数组和
    class Solution {
        /**
         * 贪心
         * @param nums
         * @param k
         * @return
         */
        public int largestSumAfterKNegations(int[] nums, int k) {
            // 将数组按照绝对值大小 从大到小 排序，注意要按照绝对值的大小
            nums = IntStream.of(nums).boxed().sorted(((o1, o2) -> Math.abs(o2)-Math.abs(o1)))
                    .mapToInt(Integer::intValue).toArray();

            int len = nums.length;

            for (int i=0; i<len; i++){
                // 从前向后遍历(从大到小), 遇到负数将其变为正数, 同时k--
                if (nums[i]<0 && k>0){  // 注意这里有个限制条件为k>0
                    nums[i] = -nums[i];
                    k--;
                }
            }

            // 如果K还大于0，那么反复转变数值最小的元素，将K用完
            if (k % 2 == 1) nums[len - 1] = -nums[len - 1];  // 注意这一步很重要
            return Arrays.stream(nums).sum();
        }

        /**
         * 方法二
         * @param nums
         * @param k
         * @return
         */
        public int largestSumAfterKNegations2(int[] nums, int k) {
            // 排序, 把可能有的负数排到前面
            Arrays.sort(nums);
            int sum = 0;
            for (int i=0; i< nums.length; i++){
                // 贪心, 如果是负数, 而k还有盈余, 就把负数反过来
                if (nums[i]<0 && k>0){
                    nums[i] = -1*nums[i];
                    k--;
                }
                sum += nums[i];
            }
            Arrays.sort(nums);  // 注意这里再排序, 为了帮助下面的索引 0 处可以获得最小正数
            // 如果k没剩，那说明能转的负数都转正了，已经是最大和，返回sum
            // 如果k有剩，说明负数已经全部转正，所以如果k还剩偶数个就自己抵消掉，不用删减，如果k还剩奇数个就减掉2倍最小正数。
            return sum - (k%2==0 ? 0 : 2*nums[0]);
        }
    }
}
