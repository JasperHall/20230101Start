package h202302;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/4sum-ii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/3 22:02
 */
public class 四数相加II {
    class Solution {
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            Map<Integer, Integer> map = new HashMap<>();//key记录nums1元素加nums2遍历后的和，value存储相同和出现的次数
            int count=0;///计数，结果

            int temp;
            for (int i=0; i<nums1.length; i++) {
                for (int j=0; j<nums2.length; j++) {
                    temp = nums1[i] + nums2[j];
                    if (map.containsKey(temp)){
                        map.put(temp, map.get(temp)+1);//加value值
                    }else {
                        map.put(temp, 1);
                    }
                }
            }

            //统计剩余的两个元素的和，在map中找是否存在相加为0的情况，同时记录次数。
            for (int i : nums3){
                for (int j : nums4){
                    temp = i+j;
                    if (map.containsKey(0 - temp)){
                        count += map.get(0-temp);
                    }
                }
            }
            return count;
        }
    }
}
