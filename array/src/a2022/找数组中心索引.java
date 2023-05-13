package a2022;

import java.util.Scanner;
//https://leetcode.cn/problems/find-the-middle-index-in-array/
public class 找数组中心索引 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [] a= new int[n];

        for (int i = 0; i < n; i++){
            a[i] = in.nextInt();
        }
        Solution solution = new Solution();
        System.out.println(solution.pivotIndex(a));
    }

    static class Solution{
        public int pivotIndex(int [] nums){
            int sum = 0;
            for(int i = 0; i < nums.length; i++){
                sum += nums[i];
            }
            int left_sum = 0;
            for (int i = 0; i < nums.length; i++){
                sum -= nums[i];
                if (left_sum == sum){
                    return i;
                }
                left_sum += nums[i];
            }
            return -1;
        }
    }
}
