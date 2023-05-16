package h202305;

import java.util.HashMap;
import java.util.HashSet;

/**
 * https://leetcode.cn/problems/intersection-of-two-arrays/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/16 13:15
 */
public class 两个数组的交集 {  // 349

    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0){
                return new int[0];
            }

            // 用于存放nums1数组的不重复的所有数字
            HashSet<Integer> set = new HashSet<>();
            // 遍历添加到set
            for (int i=0; i<nums1.length; i++){
                set.add(nums1[i]);
            }

            //用于存放交集的数字
            HashSet<Integer> resultSet = new HashSet<>();
            for (int i=0; i<nums2.length; i++){
                // 如果包含就添加到set中去
                if (set.contains(nums2[i])){
                    resultSet.add(nums2[i]);
                }
            }

            //方法1：将结果集合转为数组  用JDK8Stream流新特性，不过耗时会更长
//            return resultSet.stream().mapToInt(x -> x).toArray();

            //方法2：另外申请一个数组存放setRes中的元素,最后返回数组
            int[] result = new int[resultSet.size()];
            int j = 0;
            for(int i : resultSet){
                result[j++] = i;
            }

            return result;
        }
    }
}
