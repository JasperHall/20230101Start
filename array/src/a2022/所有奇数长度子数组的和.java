package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/25 0:21
 */
//https://leetcode.cn/problems/sum-of-all-odd-length-subarrays/
public class 所有奇数长度子数组的和 {

    class Solution {
        //方法一：暴力
        public int sumOddLengthSubarrays(int[] arr) {
            int sum = 0;
            int l = arr.length;//数组的长度
            for (int start=0; start<l; start++){//最外层循环，设置从原数组的第几个数开始
                for (int length=1; start+length<=l; length+=2){//设置选取数组的长度
                    int end = start+length-1;//数组取的最后一个位置
                    for (int i=start; i<=end; i++){
                        sum += arr[i];
                    }
                }
            }
            return sum;
        }

        //方法二：前缀和
        public int sumOddLengthSubarrays2(int[] arr) {
            int n = arr.length;
            int[] prefixSums = new int[n + 1];
            for (int i = 0; i < n; i++) {
                prefixSums[i + 1] = prefixSums[i] + arr[i];
            }
            int sum = 0;
            for (int start = 0; start < n; start++) {
                for (int length = 1; start + length <= n; length += 2) {
                    int end = start + length - 1;
                    sum += prefixSums[end + 1] - prefixSums[start];
                }
            }
            return sum;
        }
    }
}
