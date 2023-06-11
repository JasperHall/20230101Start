import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/11 16:31
 */
public class 子集 {  // 78. 子集

    class Solution {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();

        public List<List<Integer>> subsets(int[] nums) {
            backTracking(nums, 0);
            return result;
        }
        private void backTracking(int[] nums, int startIndex){
            result.add(new ArrayList<>(path));  // 遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合

            if (startIndex >= nums.length){  //终止条件可不加  其实可以不需要加终止条件，因为startIndex >= nums.size()时，本层for循环本来也结束了
                return;
            }

            for (int i=startIndex; i<nums.length; i++){
                path.add(nums[i]);
                backTracking(nums, i+1);
                path.removeLast();
            }
        }

    }
}
