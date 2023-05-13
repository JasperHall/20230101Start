package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/24 8:44
 */
//https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
public class 删除有序数组中的重复项 {
    public static void main(String[] args) {
        int[] nums = {1,2,2,3,5,5};
//        int[] nums = {1,1,1,2};
        System.out.println(removeDuplicates2(nums));
    }
    public static int removeDuplicates(int[] nums){
        int a = nums[0];//将数组的第一个值赋值给a
        int i, len=nums.length, index=1;  //index统计最后返回的数量
        for (i=1; i<len; ++i){
            if (nums[i]!=a){//判断前后两个不相等
                a = nums[i];  //把上一轮中前一个数，赋值给上一轮中的后一个数，进入下一轮比较
                nums[index++]=nums[i];
//                index++;
            }
        }
        return index;
    }

    //双指针法，快慢指针
    public static int removeDuplicates2(int[] nums){
        if (nums == null||nums.length==0) return 0;

        int p=0;//第一个指针
        int q=1;//第二个指针
        while (q<nums.length){
            if (nums[p] != nums[q]){
                nums[p+1] = nums[q];
                p++;
            }
            q++;
        }
        return p+1;
    }
}
