package h202305;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/3sum/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/16 16:26
 */
public class 三数之和 {  // 15

    class Solution {
        /**
         * 双指针法 头尾指针
         * @param nums
         * @return
         */
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);  // 先来排个序, 排序后方便双指针的移动和去重

            // 找出 a+b+c = 0
            // 我们假设a=nums[i], b=nums[left], c=nums[right]
            for (int i=0; i<nums.length; i++){
                // 排序后如果第一个元素已经大于0，则后面的都不会凑出三个数相加等于0的三元组了，遇到这种情况直接返回结果
                if (nums[i] > 0){
                    return result;
                }

                if (i>0 && nums[i]==nums[i-1]){  // 去重, 去掉 i 这个位置的重复情况
                    continue;  // 进来这里说明是个重复的情况, 所以之间continue进入下一次循环
                }

                // 双指针开始(头尾指针
                int left = i+1;
                int right = nums.length-1;
                while (right > left){  // 注意:这里不能有等号
                    int sum = nums[i] + nums[left] + nums[right];

                    // 判断三数之和的情况，来做出对下一步的选择
                    if (sum > 0){  // 和大于0,右侧指针往左移(前面排过序了)
                        right--;
                    } else if (sum < 0){
                        left++;
                    }else {
                        result.add(Arrays.asList(nums[i], nums[left],nums[right])); // 注意这一行方法的使用

                        // 去重，去重的逻辑代码应该放在找到一个三元数组之后，对 b 和 c 进行去重
                        // 上面是对 i 的取值处进行去重, right 和 left 指针都是在 i 指针之后的值,这里对他们再进行去重,这样做的原因是因为返回的是数组中的值,不是下标
                        while (right>left && nums[right]==nums[right-1]) right--; //因为是排序后的，所以相同的值都是挨着的
                        while (right>left && nums[left]==nums[left+1]) left++;  // 都要判断 right>left

                        // 准备下一次循环
                        right--;
                        left++;
                    }
                }
            }
            return result;
        }
    }
}
