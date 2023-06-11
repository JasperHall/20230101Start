import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/11 21:44
 */
public class 全排列 {  // 46. 全排列

    class Solution {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used;
        public List<List<Integer>> permute(int[] nums) {
            if (nums.length == 0){
                return result;
            }
            used = new boolean[nums.length];
            permuteHelper(nums);
            return result;
        }
        private void permuteHelper(int[] nums){
            if (path.size() == nums.length){
                result.add(new ArrayList<>(path));
                return;
            }

            for (int i=0; i<nums.length; i++){
                if (used[i]){
                    continue;
                }

                used[i] = true;
                path.add(nums[i]);
                permuteHelper(nums);
                path.removeLast();
                used[i] = false;
            }
        }
    }
}
