package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/25 23:17
 */
//https://leetcode.cn/problems/remove-element/
public class 移除元素 {

    //方法一：双指针
    public int removeElement1(int[] nums, int val){
        int n = nums.length;
        int left = 0;//left左指针（慢指针），right右指针（快指针）
        for (int right=0; right<n; right++){
            /*判断快指针取到的数组元素是否和要删除的值相等，不相等的话就把快指针的值给慢指针，同时左指针++右移
            * 若相等，不赋值，左指针不动，右指针右移
            * */
            if (nums[right] != val){
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    /*
    * 方法二：双指针优化(前后指针
    *
    * 如果左指针指向的元素等于val，此时将右指针指向的元素复制到左指针的位置然后右指针左移一位。
    * 如果赋值过来的元素恰好也等于val，可以继续把右指针指向的元素的值赋值过来(左指针指向的等于的元素的位置继续被覆盖)，
    * 直到左指针指向的元素的值不等于val 为止。
    * 当左指针和右指针重合的时候，左右指针遍历完数组中所有的元素。
    * 这样的方法两个指针在最坏的情况下合起来只遍历了数组一次。与方法一不同的是，方法二避免了需要保留的元素的重复赋值操作
    * */
    public int removeElement2(int[] nums, int val){
        int left = 0;
        int right = nums.length;
        while (left<right){
            if (nums[left] == val){
                nums[left] = nums[right-1];
                right--;
            } else {
              left++;
            }
        }
        return left;
    }
}
