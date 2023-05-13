package h202302;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/3sum/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/4 11:09
 */
public class 三数之和 {

    class Solution {

        /**
         * 双指针：头尾指针
         * @param nums
         * @return
         */
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);//排序

            //找出a+b+c=0
            // a=nums[i], b=nums[left], c=nums[right]
            for (int i=0; i<nums.length; i++){
                //排序后如果第一个元素已经大于0，则后面的都不会凑出三个数相加等于0的三元组了，遇到这种情况直接返回结果
                if (nums[i] > 0){
                    return result;
                }

                if (i>0 && nums[i]==nums[i-1]){//去掉 i 这个位置的重复情况
                    continue;
                }

                //双指针开始(头尾指针
                int left = i + 1;
                int right = nums.length - 1;
                while (right > left){//这里不能写等号,
                    int sum = nums[i] + nums[left] + nums[right];
                    //判断三数之和的情况，来做出情况选择操作
                    if (sum > 0){//和大于0,右边往左移
                        right--;
                    }else if (sum < 0){//和小于0,左边往右移
                        left++;
                    }else {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));//注意这一行方法的使用

                        //去重，去重的逻辑代码应该放在找到一个三元数组之后，对 b 和 c 进行去重
                        //上面是对 i 的取值处进行去重, right和left都是在i之后的值,这里对他们再进行去重, 可以这样做的原因是因为返回的是数组中的值,不是下标
                        while (right > left && nums[right] == nums[right-1]) right--;//因为是排序后的，所以相同的值都是挨着的
                        while (right > left && nums[left] == nums[left+1]) left++;

                        right--;
                        left++;
                    }
                }
            }
            return result;
        }
    }
}
