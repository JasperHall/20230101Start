package h202302;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/intersection-of-two-arrays/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/2 20:09
 */
public class 两个数组的交集 {


    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0){
                return new int[0];
            }

            Set<Integer> set1 = new HashSet<>();
            Set<Integer> resultSet = new HashSet<>();

            //遍历数组1
            for(int i : nums1){
                set1.add(i);
            }

            //遍历数组2的过程中判断哈希表中是否存在该元素
            for(int i : nums2){
                if (set1.contains(i)){
                    resultSet.add(i);
                }
            }
            //将结果集合转为数组
            return resultSet.stream().mapToInt(x -> x).toArray();
        }

        /**
         * 官方题解
         * 两个集合法
         * @param nums1
         * @param nums2
         * @return
         */
        public int[] intersection2(int[] nums1, int[] nums2) {
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            for(int i:nums1){
                set1.add(i);
            }
            for(int i:nums2){
                if(set1.contains(i)){
                    set2.add(i);
                }
            }
            int[] arr = new int[set2.size()];
            int j=0;
            for(int i:set2){
                arr[j++] = i;
            }
            return arr;
        }

        /**
         * 使用Hashset，hashset是一个不允许有重复元素的集合
         * @param nums1
         * @param nums2
         * @return
         */
        public int[] intersection3(int[] nums1, int[] nums2) {
            Set<Integer> nums1_Set = new HashSet<>();
            Set<Integer> resultSet = new HashSet<>();

            //将nums1转为HashSet存入nums1_Set
            for (int i=0; i<nums1.length; i++){
                nums1_Set.add(nums1[i]);
            }

            //遍历nums2，如果有与nums1相同的就加入到存储结果的resultSet中
            for (int i=0; i<nums2.length; i++){
                if (nums1_Set.contains(nums2[i])){
                    resultSet.add(nums2[i]);
                }
            }

            //将resultSet中的元素添加到结果数据中
            int[] result = new int[resultSet.size()];
            int index=0;
            for (Integer item : resultSet){
                result[index++]=item;
            }

            return result;
        }



        /**
         * 排序+双指针
         *
         * 初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，如果两个数字不相等，
         * 则将指向较小数字的指针右移一位，如果两个数字相等，且该数字不等于pre ，将该数字添加到答案并更新 pre 变量，
         * 同时将两个指针都右移一位。当至少有一个指针超出数组范围时，遍历结束
         *
         * 作者：LeetCode-s2022.Solution
         * 链接：https://leetcode.cn/problems/intersection-of-two-arrays/solution/liang-ge-shu-zu-de-jiao-ji-by-leetcode-solution/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         * @param nums1
         * @param nums2
         * @return
         */
        public int[] intersection4(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int length1 = nums1.length, length2 = nums2.length;

            int[] reslut = new int[length1+length2]; //存储结果的数组

            int index = 0, index1 = 0, index2 = 0; //两个指针index1，index2

            while (index1<length1 && index2<length2){
                int num1 = nums1[index1], num2 = nums2[index2];
                if (num1 == num2){
                    //第二个条件可以保证加入元素的唯一性
                    if (index == 0 || num1 != reslut[index-1]){ //因为是排好序的，挨着后进来的要么和前一个相等，要么不等
                        reslut[index++] = num1;
                    }
                    index1++;
                    index2++;
                }else if(num1 < num2){
                    index1++;  //较小的元素指针右移一位
                }else {
                    index2++;
                }
            }
            return Arrays.copyOfRange(reslut, 0 ,index);
        }

    }
}
