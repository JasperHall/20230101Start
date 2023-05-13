package h202302;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/happy-number/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/3 17:24
 */
public class 快乐数 {

    class Solution {
        public boolean isHappy(int n) {
            Set<Integer> records = new HashSet<>();
            while (n != 1 && !records.contains(n)){//如果现在的n不等于1，且set中更没有重复则继续往下算
                records.add(n);//将这个符合条件的n加进去
                n = getNextNumber(n);//调用自定义的方法，进行下一轮计算，
            }

            return n==1;
        }

        private int getNextNumber(int n){
            int res = 0;
            while (n > 0){
                int temp = n % 10;//每个数字从右往左挨个计算
                res = res + temp*temp;
                n = n /10;//计算一次后去掉一位
            }
            return res;
        }
    }
}
