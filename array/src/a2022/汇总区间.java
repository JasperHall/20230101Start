package a2022;

import java.util.ArrayList;
import java.util.List;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/3 10:34
 */

//https://leetcode.cn/problems/summary-ranges/
public class 汇总区间 {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,5,6,7,9};

        System.out.println(summaryRanges1(a));
        System.out.println(summaryRanges2(a));

    }

    //方法一：官方题解：一次变量
    //分析：根据题目描述知道原数组是排好序的，所有当两个相邻元素的差值大于一时就生成一个新区间
    public static List<String> summaryRanges1(int[] nums) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int low = i;  //区间的左值
            i++;
            //判断前后两个相邻元素的差值是否大于一，前一个值加一不等于后一个值就不对（因为题目说无重复元素）
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;  //区间的右值
            StringBuffer temp = new StringBuffer(Integer.toString(nums[low]));
            if (low < high) {
                temp.append("->");
                temp.append(Integer.toString(nums[high]));
            }
            ret.add(temp.toString());
        }
        return ret;
    }

    //方法二：双指针
    public static List<String> summaryRanges2(int[] nums) {
        List<String> res = new ArrayList<>();
        // i 初始指向第 1 个区间的起始位置
        int i = 0;
        for (int j = i; j < nums.length; j++) {
            // j 向后遍历，直到不满足连续递增(即 nums[j] + 1 != nums[j + 1])
            // 或者 j 达到数组边界，则当前连续递增区间 [i, j] 遍历完毕，将其写入结果列表。
            if (j + 1 == nums.length || nums[j] + 1 != nums[j + 1]) {
                // 将当前区间 [i, j] 写入结果列表
                StringBuilder sb = new StringBuilder();
                sb.append(nums[i]);
                if (i != j) {
                    sb.append("->").append(nums[j]);
                }
                res.add(sb.toString());
                // 将 i 指向更新为 j + 1，作为下一个区间的起始位置
                i = j + 1;
            }
        }
        return res;
    }
}
