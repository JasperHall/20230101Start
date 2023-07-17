import java.util.Arrays;

/**
 * 背包问题
 * https://leetcode.cn/problems/partition-equal-subset-sum/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/7/16 22:37
 */
public class 分割等和子集 {  // 416. 分割等和子集
    public static void main(String[] args) {
        int num[] = {2,2,2,2};
        boolean a = canPartition2(num);

    }

    /**
     * 一维数组版
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        if(nums==null || nums.length==0){
            return false;
        }

        int n = nums.length;
        int sum = 0;

        for (int num : nums){
            sum += num;
        }
        // 总和为奇数, 不能平分
        if (sum%2 == 1) return false;
        int target = sum / 2;
        int[] dp = new int[target+1];

        for (int i=0; i<n; i++){
            System.out.println('\n'+"i="+i);
            for (int j=target; j>=nums[i]; j--){
                // 物品 i 的重量是 nums[i]，其价值也是 nums[i]
                dp[j] = Math.max(dp[j], dp[j-nums[i]] +nums[i]);
                System.out.print("dp["+j+"]="+dp[j]+"  ");
            }
        }
        System.out.println('\n'+"----end---------");
        System.out.println(Arrays.toString(dp));

        return dp[target]==target;
    }

    /**
     * 二维数组版
     * @param nums
     * @return
     */
    public static boolean canPartition2(int[] nums) {
        int len = nums.length;
        // 题目已经说非空数组，可以不做非空判断
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 特判：如果是奇数，就不符合要求
        if ((sum %2 ) != 0) {
            return false;
        }

        int target = sum / 2; //目标背包容量
        // 创建二维状态数组，行：物品索引，列：容量（包括 0）
        /*
        dp[i][j]表示从数组的 [0, i] 这个子区间内挑选一些正整数
          每个数只能用一次，使得这些数的和恰好等于 j。
        */
        boolean[][] dp = new boolean[len][target + 1];  // len为数组的长度

        // 这一段可有可无
        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满  （这里的dp[][]数组的含义就是“恰好”，所以就算容积比它大的也不要）
        /*if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }*/

        // 再填表格后面几行
        //外层遍历物品
        for (int i = 1; i < len; i++) {
            //内层遍历背包
            for (int j = 0; j <= target; j++) {  // 这个写法j代表背包的容量肯定会到11, 判断有没有情况
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i - 1][j];

                //如果某个物品单独的重量恰好就等于背包的重量，那么也是满足dp数组的定义的
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                // 如果某个物品的重量小于j，那就可以看该物品是否放入背包
                // dp[i - 1][j] 表示该物品不放入背包，如果在 [0, i-1] 这个子区间内已经有一部分元素，使得它们的和为 j ，那么 dp[i][j] = true；
                // dp[i - 1][j - nums[i]] 表示该物品放入背包。如果在 [0, i-1] 这个子区间内就得找到一部分元素，使得它们的和为 j-nums[i]。
                if (nums[i] < j) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];  // 有一个为true, 结果就为true
                    // 注意 dp[i-1][j-nums[i]] 中的 j-nums[i], 背包的容量减去当前物品的重量, 如果有值刚好与减去后的相等, 则背包刚好装满
                }
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return dp[len - 1][target];
    }



}
