package a2022;

import java.util.Arrays;

/**
 * https://leetcode.cn/leetbook/read/array-and-string/cnkjg/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/12/7 20:34
 */
public class 两数之和 {

    public static void main(String[] args) {
        int[] a = {3,2,4};
        int b =6;
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.twoSum(a,b)));
    }

    static class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int[] res = new int[2];
            for (int i=0; i<numbers.length-1; i++){
                res[0] = i;
                for (int j=i+1; j<numbers.length; j++){
                    res[1] = j;
                    if (numbers[i]+numbers[j]==target){
                        return res;
                    }
                }
            }
            return res;
        }
    }
}
