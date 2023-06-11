import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.cn/problems/non-decreasing-subsequences/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/11 20:28
 */
public class 递增子序列 {  // 491. 递增子序列

    class Solution {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> findSubsequences(int[] nums) {
            backTracking(nums, 0);
            return result;
        }
        private void backTracking(int[] nums, int startIndex){
            if (path.size() >= 2){   // 题目要求递增子序列大小至少为2
                result.add(new ArrayList<>(path));
                // 注意这里不要加return，因为要取树上的所有节点
            }

            HashSet<Integer> hashSet = new HashSet<>(); // 使用set来对本层元素进行去重
            for (int i=startIndex; i<nums.length; i++){
                // path不为空 同时目前path最后一个元素大于目前的nums[i],  或者  hashSet包含nums[i]  就跳过本次循环
                // 因为要目前的nums[i]大 然后加入path才是递增的, 如果hashSet包含nums[i]就说明同一层出现了重复元素
                if (!path.isEmpty() && path.get(path.size()-1)>nums[i] || hashSet.contains(nums[i])){
                    continue;
                }
                hashSet.add(nums[i]);  // 记录这个元素在本层用过了，本层后面不能再用了
                path.add(nums[i]);
                backTracking(nums, i+1);
                path.remove(path.size()-1);
            }
        }
    }
}
