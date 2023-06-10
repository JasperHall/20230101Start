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

        List<List<Integer>> result = new ArrayList<>();  // 存放符合条件结果的集合
        LinkedList<Integer> path = new LinkedList<>();  // 用来存放符合条件单一结果

        public List<List<Integer>> combine(int n, int k) {
            combineHelper(n, k, 1);
            return result;
        }

        /**
         * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠startIndex
         * @param startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
         */
        private void combineHelper(int n, int k, int startIndex){
            //终止条件
            if (path.size() == k){
                result.add(new ArrayList<>(path));
                return;
            }
            for (int i = startIndex; i <= n - (k - path.size()) + 1; i++){  // 控制树的横向遍历
                path.add(i);  // 处理节点
                combineHelper(n, k, i + 1);  // 递归：控制树的纵向遍历，注意下一层搜索要从i+1开始
                path.removeLast();  // 回溯，撤销处理的节点
            }
        }


    }
}
