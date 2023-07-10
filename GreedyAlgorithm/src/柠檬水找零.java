/**
 * https://leetcode.cn/problems/lemonade-change/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/29 8:38
 */
public class 柠檬水找零 {  // 860. 柠檬水找零
    class Solution {
        public boolean lemonadeChange(int[] bills) {
            int five = 0;
            int ten = 0;

            for (int i=0; i< bills.length; i++){
                if (bills[i] == 5){
                    five++;
                } else if (bills[i] == 10){
                    five--;
                    ten++;
                }else if (bills[i] == 20){
                    if (ten>0){
                        ten--;
                        five--;
                    } else {
                        five -= 3;
                    }
                }
                if (five<0 || ten<0) return false;
            }
            return true;
        }
    }
}
