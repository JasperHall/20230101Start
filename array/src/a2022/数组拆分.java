package a2022;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/11 8:52
 */
public class 数组拆分 {

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum=0;
        for (int i=0; i<nums.length; i=i+2){
            sum+=nums[i];
        }
        return sum;
    }
}
