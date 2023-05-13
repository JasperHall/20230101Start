package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/24 15:29
 */
//https://leetcode.cn/problems/next-greater-element-i/
public class 下一个更大元素 {

    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            int[] res = new int[m];//返回的数组和nums1的长度相等
            for (int i = 0; i < m; ++i) {
                int j = 0;
                while (j < n && nums2[j] != nums1[i]) {  //一直循环直到找到nums2[j]=nums1[i]
                    ++j;
                }
                int k = j + 1;//从nums2[j]的下一个开始往后比较，找到更小的
                while (k < n && nums2[k] < nums2[j]) {
                    ++k;
                }
                res[i] = k < n ? nums2[k] : -1;//没循环完，也就是k<n时，将此时的nums2[k]赋值
            }
            return res;
        }
    }

}
