package kamacoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1027
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/6 11:58
 */
public class 最长增长子序列 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int i=0; i<n; i++){
            String input = sc.next();
            int[] nums = parseStringToIntArray(input);
            System.out.println(lengthOfLIS(nums));
        }
    }

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums){
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i=0; i<dp.length; i++){
            for (int j=0; j<i; j++){
                if (nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
                res = Math.max(res, dp[i]);
            }
        }
        /*
        // 这一块这里写在for循环里边, 省一次for循环
        int res = 0;

        for (int i=0; i<dp.length; i++){
            res = Math.max(res, dp[i]);
        }
        */
        return res;
    }


    /**
     * 将输入的字符串转为数组
     * @param input
     * @return
     */
    public static int[] parseStringToIntArray(String input){
        String[] stringArray = input.replaceAll("[\\[\\]\\s]", "").split(",");  // 注意这里是用的replaceAll()不是replace()

        int[] intArray = new int[stringArray.length];

        for (int i=0; i< stringArray.length; i++){
            intArray[i] = Integer.parseInt(stringArray[i]);
        }

        return intArray;
    }
}
