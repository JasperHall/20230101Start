package a2022;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/3 14:51
 */
public class 子集 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = {1,2,3};

        System.out.println(solution.subsets(a));
    }
    static class Solution {
        /**
         * 回溯法
         * @param nums
         * @return
         */
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            backtrack(0, nums, res, new ArrayList<Integer>());
            return res;
        }
        private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp){
            res.add(new ArrayList<>(tmp));
            for (int j=i; j<nums.length; j++){
                tmp.add(nums[j]);
                backtrack(j+1,nums,res,tmp);
                tmp.remove(tmp.size()-1);
            }
        }
    }
}
