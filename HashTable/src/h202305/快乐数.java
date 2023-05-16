package h202305;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/happy-number/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/16 14:27
 */
public class 快乐数 {  // 202

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean res = solution.isHappy(2);
        System.out.println(res);
    }

    static class Solution{
        public boolean isHappy(int n) {
            Set<Integer> record = new HashSet<>();
            while (n !=1 && !record.contains(n)){  // 不包含
                record.add(n);
                n = getNextNumber(n);
            }
            return n == 1;  // 退出上边的while循环只有两种情况, 一种是n=1返回真, 另一种是出现了循环返回假
        }
        public int getNextNumber(int n){
            int result = 0;
            while (n > 0){
                int temp = n % 10;
                result = result + temp * temp;  // 注意这里有个加号
                n = n /10;
            }
            return result;
        }
    }

}
