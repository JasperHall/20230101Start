package a2022;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/31 21:54
 */
//https://leetcode.cn/problems/contains-duplicate/
public class 存在重复元素 {


    //方法一：排序
    public boolean containsDuplicate1(int[] nums) {
        Arrays.sort(nums);//先排序，后期挨个比较，如果有相同的肯定会挨到一块。
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    //方法二：哈希表
    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> sites = new HashSet<>();
        for (int n : nums){  //n为遍历nums中的每个元素
            if (!sites.add(n)){  //add不进去则表明是sites中已经有了该元素
                return true;
            }
        }
        return false;
    }
}
