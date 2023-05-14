package a202305;

/**
 * https://leetcode.cn/problems/remove-element/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/14 10:04
 */
public class 移除元素 {  // 27

    class Solution {
        /**
         * 暴力
         * @param nums
         * @param val
         * @return
         */
        public int removeElement(int[] nums, int val) {
            int len = nums.length;
            for (int i=0; i<len; i++){
                if (nums[i] == val){
                    for (int j=i+1; j<len; j++){
                        nums[j-1] = nums[j];
                    }
                    i--; // 因为下标i以后的数值都向前移动了一位，所以i也向前移动一位
                    len--; // 此时数组的大小-1
                }
            }
            return len;
        }

        /**
         * 快慢指针法
         * @param nums
         * @param val
         * @return
         */
        public int removeElement2(int[] nums, int val) {
            int slowIndex = 0;
            for (int fastIndex = 0; fastIndex<nums.length; fastIndex++){
                if (nums[fastIndex] != val){  // 如果不等于就赋值过来, 如果等于就不赋值, 相当于跳过了那个相等的元素
                    nums[slowIndex] = nums[fastIndex];  // 赋值操作
                    slowIndex++;
                }
            }
            return slowIndex;
        }

        /**
         * 相向指针
         * @param nums
         * @param val
         * @return
         */
        public int removeElement3(int[] nums, int val) {

            int left = 0;
            int right = nums.length - 1;

            while(right >= 0 && nums[right] == val) right--; //将right移到从右数第一个值不为val的位置  直接去除右侧相等的边缘元素

            while(left <= right) {
                if(nums[left] == val) { //left位置的元素需要移除
                    //将right位置的元素移到left（覆盖），right位置移除
                    nums[left] = nums[right];
                    right--;
                }
                left++;
                while(right >= 0 && nums[right] == val) right--;
            }
            return left;
        }
    }
}
