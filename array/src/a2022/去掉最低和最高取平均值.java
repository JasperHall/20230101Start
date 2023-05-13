package a2022;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/13 20:22
 */
//https://leetcode.cn/problems/average-salary-excluding-the-minimum-and-maximum-salary/
public class 去掉最低和最高取平均值 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = new int[]{48000,59000,99000,13000,78000,45000,31000,17000,39000,37000,93000,77000,33000,28000,4000,54000,67000,6000,1000,11000};
        System.out.println(solution.average(a));
    }

    static class Solution {
        public double average(int[] salary) {
            Arrays.sort(salary);
            int n = salary.length;
            double sum = 0;
            for (int i=0; i<n; i++){
                sum += salary[i];
            }
            double result = (sum-salary[0]-salary[n-1])/(n-2);

            return result;

        }
    }
}
