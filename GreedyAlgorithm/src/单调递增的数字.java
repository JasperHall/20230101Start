/**
 * https://leetcode.cn/problems/monotone-increasing-digits/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/7/11 10:21
 */
public class 单调递增的数字 {  // 738. 单调递增的数字
    class Solution {
        /**
         * 方法一
         * 耗时和空间占用都很高
         * @param n
         * @return
         */
        public int monotoneIncreasingDigits(int n) {
            String[] strings = (n+"").split("");  // 分割成数组放入strings字符数组中

            int flag = strings.length;  // 标记作用, flag后面的数都赋值为9

            for (int i=strings.length-1; i>0; i--){
                // 把字符数组中的值取出, 转为数字后再进行比较
                if (Integer.parseInt(strings[i]) < Integer.parseInt(strings[i-1])){  // 如果后面的数小于前面的
                    strings[i-1] = (Integer.parseInt(strings[i-1]) - 1) + "";  // 把前面的那个数字符取出转为数字类型, 然后减一, 然后赋值到原来的位置
                    flag = i;  // 更新标记的位置
                }
            }
            for (int i=flag; i<strings.length; i++){
                strings[i]  = "9";
            }
            return Integer.parseInt(String.join("", strings));
        }

        /**
         * 方法二
         * 在char数组上原地修改, 用时少, 效率高
         * @param n
         * @return
         */
        public int monotoneIncreasingDigits2(int n) {
            String s = String.valueOf(n);  // 先把int型的n转为String类型的s
            char[] chars = s.toCharArray();  // 然后把s转为字符数组
            int flag = s.length();

            for (int i=s.length()-1; i>0; i--){
                if (chars[i-1] > chars[i]){
                    chars[i-1]--;
                    flag = i;
                }
            }

            for (int i=flag; i<s.length(); i++){
                chars[i] = '9';
            }
            return Integer.parseInt(String.valueOf(chars));
        }
    }
}
