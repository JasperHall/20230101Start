package h202305;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/4sum-ii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/16 15:35
 */
public class 四数相加II {  // 454
    class Solution {
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            Map<Integer, Integer> map = new HashMap<>(); // key记录 nums1元素 加 nums2元素 遍历后的和，value存储相同和出现的次数
            int count = 0;

            int temp;
            for (int n1 : nums1) {
                for (int n2 : nums2) {
                    temp = n1 + n2;  // nums1 和 nums2 各个元素求和存入 temp
                    if (map.containsKey(temp)){
                        map.put(temp, map.get(temp)+1);  // 此key 的 value值 +1
                    }else {
                        map.put(temp, 1);   // 不存在此key, 计入出现一次
                    }
                }
            }

            // 统计剩余的两个数组的元素的和，在map中找是否存在相加为0的情况，同时记录次数。
            for (int n3 : nums3) {
                for (int n4 : nums4) {
                    temp = n3 + n4;
                    if (map.containsKey(0 - temp)){
                        count += map.get(0-temp);  // 返回的总数, 等于 相加等于0的次数
                    }
                }
            }
            return count;
        }
    }
}
