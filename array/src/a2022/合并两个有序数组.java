package a2022;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/27 22:41
 */
//https://leetcode.cn/problems/merge-sorted-array/
public class 合并两个有序数组 {

    public static void main(String[] args) {
        int[] nums1 = {1,5,8,0,0,0,0,0};
        int[] nums2 = {1,3,5,8,9};
        int m=3;
        int n=5;
        Solution solution = new Solution();
//        solution.merge2(nums1,m,nums2,n);
        System.out.println(Arrays.toString(solution.merge2(nums1,m,nums2,n)));
    }

    static class Solution{
        //方法一：直接替换后排序
        public void merge1(int[] nums1, int m, int[] nums2, int n) {
            for (int i=0; i<n; i++){
                nums1[m+i] = nums2[i];
            }
            Arrays.sort(nums1);
        }

        //方法二：双指针
    /*因为题目中说原数组就是非降序排列的，所以可以每次选出此时状态两个数组中第一个值的小值，加入新的数组，
    两个指针分别指向两个数组num1和num2的头部
    * */
        public int[] merge2(int[] nums1, int m, int[] nums2, int n) {
            int p=0, q=0;//p指向nums1的头部，q指向nums2的头部
            int[] nums3 = new int[m+n];//定义一个新数组，存放数据
            int cur;
            while (p<m || q<n){
                if(p==m){
                    cur = nums2[q++];
                }else if(q==n){
                    cur = nums1[p++];
                }else if (nums1[p]<nums2[q]){
                    cur = nums1[p++];
                }else {
                    cur = nums2[q++];
                }
                nums3[p + q - 1] = cur;
            }

            for (int i = 0; i < m + n; i++) {
                nums1[i] = nums3[i];
            }
            return nums1;
        }
    }

}
