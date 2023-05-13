package h202302;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/two-sum/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/3 21:19
 */
public class 两数之和 {

    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int[] res = new int[2];

            if (nums == null || nums.length == 0){
                return res;
            }

            Map<Integer, Integer> map = new HashMap<Integer, Integer>();//key存放数组的元素值，value存放数组的下标

            for (int i=0; i<nums.length; i++){
                int temp = target - nums[i];//遍历当前元素
                //在 map 中寻找是否有匹配的key
                if (map.containsKey(temp)){//如果在map中找到对应相加等于target的key，则给结果数组赋值，准备返回结果
                    res[1] = i;
                    res[0] = map.get(temp);//获取对应的value
                    break;
                }
                map.put(nums[i], i);//如果没有找到匹配对，就把访问过的元素和下标加入到map中
            }
            return  res;

        }
    }
}
