import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/11 10:08
 */
public class 组合总和II {

    class Solution {

        LinkedList<Integer> path = new LinkedList<>();  // 符合条件的组合
        List<List<Integer>> ans = new ArrayList<>();  // 存放组合集合
        boolean[] used;
        int sum = 0;

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            used = new boolean[candidates.length];

            // 加标志数组，用来辅助判断同层节点是否已经遍历
            Arrays.fill(used, false);
            // 为了将重复的数字都放到一起，所以先进行排序
            Arrays.sort(candidates);
            backTracking(candidates, target, 0);
            return ans;
        }
        private void backTracking(int[] candidates, int target, int startIndex) {
            if (sum == target){
                ans.add(new ArrayList(path));
            }

            for (int i=startIndex; i<candidates.length; i++){
                if (sum + candidates[i]>target){
                    break;
                }

                // used[i - 1] == true，说明同一树枝candidates[i - 1]使用过
                // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
                // 要对同一树层使用过的元素进行跳过
                // 出现重复节点，同层的第一个节点已经被访问过，所以直接跳过
                if (i>0 && candidates[i]==candidates[i-1] && used[i-1]==false){
                    continue;
                }

                used[i] = true;
                sum += candidates[i];
                path.add(candidates[i]);
                // 每个节点仅能选择一次，所以从下一位开始
                backTracking(candidates, target, i+1);
                used[i] = false;
                sum -= candidates[i];
                path.removeLast();
            }
        }


    }
}
