package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/3 10:51
 */
//https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array
public class 寻找旋转数组中的最小值 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[] = {3,4,5,1,2};
        System.out.println(solution.findMin(nums));
    }

    static class Solution {
        /**
         * 方法一：s202301.二分查找
         * @param nums
         * @return
         */
        public int findMin(int[] nums) {
            int len = nums.length;
            int low = 0;
            int high = len-1;

//        s202301.二分查找
            while(low < high){
//            取中间值
                int mid = (high+low)/2;
//            如果中间值小于最大值，则最大值减小
//            疑问：为什么 high = mid;而不是 high = mid-1;
//            解答：{4,5,1,2,3}，如果high=mid-1，则丢失了最小值1
                if (nums[mid] < nums[high]) {
                    high = mid;
                } else {
//                如果中间值大于最大值，则最小值变大
//                疑问：为什么 low = mid+1;而不是 low = mid;
//                解答：{4,5,6,1,2,3}，nums[mid]=6，low=mid+1,刚好nums[low]=1
//                继续疑问：上边的解释太牵强了，难道没有可能low=mid+1,正好错过了最小值
//                继续解答：不会错过!!! 如果nums[mid]是最小值的话，则其一定小于nums[high],走if，就不会走else了
                    low = mid+1;
                }
            }
//        疑问：为什么while的条件是low<high,而不是low<=high呢
//        解答：low<high，假如最后循环到{*,10,1,*}的这种情况时，nums[low]=10,nums[high]=1,nums[mid]=10,low=mid+1,
//             直接可以跳出循环了,所以low<high,此时low指向的就是最小值的下标;
//             如果low<=high的话，low=high，还会再不必要的循环一次，此时最后一次循环的时候会发生low==high==mid，
//             则nums[mid]==nums[high]，则会走一次else语句，则low=mid+1,此时low指向的是最小值的下一个下标，
//             则需要return[low-1]
            return nums[low];
        }


        /**
         * 方法二：仔细读题可以发现该数组的本质还是升序的，所以可以按照这个方法
         * @param nums
         * @return
         */
        public int findMin2(int[] nums)
        {
            int temp = nums[0];
            for (int i = 1; i < nums.length; i++)
                if (nums[i] < temp)
                    return nums[i];
            return temp;
        }
    }
}
