import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/combinations/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/4 0:41
 */
public class 组合 {  // 77. 组合
    class Solution {

        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();

        public List<List<Integer>> combine(int n, int k) {
            backtracking(n, k, 1);
            return result;
        }
        private void backtracking(int n, int k, int startIndex){
            if (path.size() == k) {
                result.add(new ArrayList<>(path));
                return;
            }
            for (int i = startIndex; i <= n; i++) {
                path.add(i); // 处理节点
                backtracking(n, k, i + 1); // 递归
                path.removeLast(); // 回溯，撤销处理的节点
            }
        }
    }
}
