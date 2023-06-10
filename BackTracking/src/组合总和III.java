import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/combination-sum-iii/
 *
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/10 20:55
 */
public class 组合总和III {  // 216. 组合总和 III

    class Solution {
        LinkedList<Integer> path = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();

        public List<List<Integer>> combinationSum3(int k, int n) {
            backTracking(n, k, 1, 0);
            return result;
        }

        private void backTracking(int targetSum, int k, int startIndex, int sum){
            if (sum > targetSum) return;

            // 剪枝 i <= 9 - (k - path.size()) + 1; 如果还是不清楚
            // 也可以改为 if (path.size() > k) return; 执行效率上是一样的
            if (path.size() > k) return;

            if (sum==targetSum && path.size()==k){
                result.add(new ArrayList<>(path));
                return;
            }

            for(int i=startIndex; i<=9; i++){
                path.add(i);
                sum += i;
                backTracking(targetSum, k , i+1, sum);
                sum -= i;
                path.removeLast();
            }
        }
    }
}
