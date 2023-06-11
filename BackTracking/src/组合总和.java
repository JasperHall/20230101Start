import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/combination-sum/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/11 9:19
 */
public class 组合总和 {  // 39. 组合总和

    class Solution {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            backtracking(candidates, target, 0 ,0);
            return result;
        }
        private void backtracking(int[] candidates, int target, int sum, int startIndex){
            if (sum > target) return;

            if (sum == target){
                result.add(new ArrayList<>(path));
                return;
            }

            for (int i=startIndex; i<candidates.length; i++){
                sum += candidates[i];
                path.add(candidates[i]);
                backtracking(candidates, target, sum, i); // 不用i+1了，表示可以重复读取当前的数
                sum -= candidates[i];
                path.removeLast();
            }
        }
    }
}
