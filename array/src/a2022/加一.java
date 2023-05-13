package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/27 22:07
 */

//https://leetcode.cn/problems/plus-one/
public class 加一 {

    //方法一：逐个判断是否为9
    public int[] plusOne1(int[] digits) {
        int x = digits.length;  //x存储数组的长度
        for (int i=x-1; i>=0; i--){
            if (digits[i]!=9)//判断第i位是否为9,当是9的时候进不去if语句
            {
                digits[i]++;//若第i位不为9，就加一
                for (int j=i+1; j<x; j++){
                    digits[j]=0; //当进到这个内层for循环时，说明不是在数组的最后一位，也就意味着后面有的元素为9，所以把后一位设置为0
                }
                return digits;
            }
        }

        //当difits中的所有元素都为9时
        int[] ans = new int[x+1];
        ans[0]=1;
        return ans;
    }
    //同方法一：但是比较好懂
    public int[] plusOne2(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                return digits;
            }
        }
        int[] ans = new int[n + 1];
        ans[0] = 1;
        return ans;
    }

    public int[] plusOne3(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i] = (digits[i] + 1) % 10;  //数组的第i位加一，取余判断是否为0，为0则继续循环，不为零则返回此时的数组
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }

}
