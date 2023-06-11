import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets-ii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/11 17:16
 */
public class 子集II {

    class Solution {
        List<List<Integer>> result = new ArrayList<>();  // 存放符合条件结果的集合
        LinkedList<Integer> path = new LinkedList<>();  // 用来存放符合条件结果
        boolean[] used;

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            if (nums.length==0){
                result.add(path);
                return result;
            }
            Arrays.sort(nums);  // 排序一下
            used = new boolean[nums.length];  // 建立标记数组
            subsetsWithDupHelper(nums, 0);  // 调用自定义方法
            return result;
        }
        private void subsetsWithDupHelper(int[] nums, int startIndex){
            result.add(new ArrayList<>(path));  // 加一个空值

            if (startIndex >= nums.length) return;

            for (int i=startIndex; i<nums.length; i++){
                if (i>0 && nums[i]==nums[i-1] && !used[i-1]){  // !used[i-1] 和 used[i-1]==false 是一样的
                    continue;
                }

                path.add(nums[i]);
                used[i] = true;
                subsetsWithDupHelper(nums, i+1);
                path.removeLast();
                used[i] = false;
            }

        }
    }
}
