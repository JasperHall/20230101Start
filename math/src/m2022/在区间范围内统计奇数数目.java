package m2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/13 19:35
 */
public class 在区间范围内统计奇数数目 {

    static class Solution {
        public int countOdds(int low, int high) {
            int result;  //奇数数目
            if (low % 2== 0 && high % 2 ==0){  //两边两个数都是偶数
                result = (high-low)/2;
            }else {  //一奇一偶，或者两个奇
                result = (high-low) / 2 + 1;
            }
            return result;
        }
    }
}
