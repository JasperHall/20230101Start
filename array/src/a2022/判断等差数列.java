package a2022;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/18 9:59
 */
public class 判断等差数列 {

    static class Solution{
        //方法一
        public boolean canMakeArithmeticProgression1(int[] arr) {
            Arrays.sort(arr);
            int diff = arr[1]-arr[0];//前两个数的差
            //开始遍历
            int i=0;
            while (i< arr.length-1){
                if (arr[i+1] == arr[0] + diff*(i+1)){  //每次第i+1个值，应该等于第一个值加公差乘以(i+1)
                    i++;
                }else
                    return false;
            }
            return true;
        }
        //方法二
        public boolean canMakeArithmeticProgression2(int[] arr) {
            Arrays.sort(arr);
            for (int i =0; i<arr.length-1;i++){
                if (arr[i]*2 != arr[i-1]+arr[i+1]){
                    return false;
                }
            }
            return true;
        }
    }
}
