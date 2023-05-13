package a2022;

import java.util.ArrayList;
import java.util.List;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/11 10:05
 */
public class 组合总和 {
    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        Solution solution = new Solution();
        System.out.println(solution.combinationSum(candidates, target));
    }



    /**
     * 方法一：回溯不剪枝
     * 作者：LeetCode-s2022.Solution
     * 链接：https://leetcode.cn/problems/combination-sum/solution/zu-he-zong-he-by-leetcode-solution/
     */
    static class Solution {

        public List<List<Integer>> combinationSum(int[] candidates, int target) {

            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            List<Integer> combine = new ArrayList<Integer>();
            dfs(candidates, target, ans, combine, 0);
            return ans;
        }

        /**
         * 递归函数
         *
         * @param candidates 传入的数组
         * @param target     剩余
         * @param ans
         * @param combine
         * @param idx        下标：初始值为0
         */
        public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
            if (idx == candidates.length) {
                return;
            }
            if (target == 0) {
                ans.add(new ArrayList<Integer>(combine));
                return;
            }
            // 直接跳过
            dfs(candidates, target, ans, combine, idx + 1);
            // 选择当前数
            if (target - candidates[idx] >= 0) {
                combine.add(candidates[idx]);
                dfs(candidates, target - candidates[idx], ans, combine, idx);
                combine.remove(combine.size() - 1);
            }
        }
    }
}

