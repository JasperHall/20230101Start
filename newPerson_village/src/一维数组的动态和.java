import java.util.Scanner;
//https://leetcode.cn/problems/running-sum-of-1d-array/
public class 一维数组的动态和 {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new  int[n];
        for (int i = 0; i<a.length; i++){
            a[i] = in.nextInt();
        }

        Solution solution = new Solution();
        int []b=solution.runningSum(a);
        for (int i = 0; i<b.length; i++){
            System.out.println(b[i]);
        }


    }
    //方法一
    static class Solution{
        public int [] runningSum(int[] nums){
            int length = nums.length;
            int [] sums = new int[length];
            for(int i =0; i < length; i++){
                for (int j =0;j<=i; j++){
                    sums[i] +=nums[j];
                }
            }
            return sums;
        }


    }
    //方法二
    class Solution2 {
        public int[] runningSum(int[] nums) {
            int length = nums.length;
            int[] sums = new int[length];
            sums[0] = nums[0];
            for (int i = 1; i < length; i++) {
                sums[i] = sums[i - 1] + nums[i];
            }
            return sums;
        }
    }



}
