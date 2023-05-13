package a2022;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/7 23:31
 */
//https://leetcode.cn/problems/container-with-most-water/
public class 盛水最多的容器 {

    //挨个遍历，超时
    public int maxArea1(int[] height) {
        int l=0, max=0;
        int q=0, p=0;
        for (int i=0; i<height.length; i++){
            for (int j=i+1; j<height.length; j++){
                if (height[i]<=height[j]){
                    l = height[i] * (j-i);
                }else if (height[i]>height[j]){
                    l = height[j] * (j-i);
                }
                if (l>max){
                    max = l;
                }
            }
        }
        return max;
    }

    //双指针
    public int maxArea2(int[] height) {
        int max=0;
        int q=0, p=height.length-1;//一个指针在开头，一个指针在最后
        while (q < p){
            int area = Math.min(height[q], height[p]) * (p-q);
            max = Math.max(area, max);

            if (height[q] <= height[p]){
                q++;
            }else {
                p--;
            }

        }

        return max;
        //来源力扣官方题解
    }

    //自己做的没做出来
    public int maxArea3(int[] height) {
        int l=0, max=0;
        //先找出两个最大的数，然后最大的容积要么是这两个最长的，要么在最长的外侧
        //先排序
        int[] nums = height;
        Arrays.sort(nums);
        int numMax1=nums[nums.length], numMax2=nums[nums.length-1];//两个最大的数
        int index1=0, index2=0;//两个最大值的索引
        for (int i=0; i<height.length; i++){
            if (numMax1==height[i]){
                index1=i;
                break;
            }
        }
        for (int i=0; i<height.length; i++){
            if (numMax1==height[i]){
                index2=i;
                break;
            }
        }


//        int q=0, p=0;//两个最大的数的索引
//        for (int i=0; i<height.length; i++){
//
//
//        }


        return max;
    }
}
