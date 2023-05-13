package s2022;

import java.util.Scanner;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/20 23:24
 */
//https://leetcode.cn/leetbook/read/array-and-string/conm7/
public class 最长回文字串 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("请输入：");
        String a = in.next();
        Solution solution = new Solution();
        String b = solution.longestPalindrome(a);
        System.out.println(b);
    }

    //解法一：暴力解题
    static class Solution{
        public String longestPalindrome(String s){
            if (s == null || s.length()==1){
                return s;
            }
            int maxLen = 1;//初始最大长度
            int startIndex = 0;//设置初始的开始索引
            int j = s.length()-1;//将字符串转为数组后的末尾下标
            char[] chars_arr = s.toCharArray();//字符串转为数组
            for (int i=0; i<s.length()-1; i++){//for循环，下标从0到末尾
                char c = chars_arr[i];//定义字符c，把数组chars_arr的下标为i 的值赋值给c
                while (i<j && (j-i+1)>maxLen)
                {
                    if (chars_arr[j]==c && isPalindrome(chars_arr,i,j)){
                        //数组chars_arr在索引j和i的值相等 同时 由方法isPalindrome判断i到j的值相等
                        maxLen=j-i+1;
                        startIndex=i;
                        break;
                    }else {
                        j--;
                    }
                }//到这里是while循环

                j = chars_arr.length-1;
            }
            return s.substring(startIndex, startIndex+maxLen);
        }

        //判断下标i到j是否为回文
        public boolean isPalindrome(char[] chars_arr, int i, int j){
            while(i<j){
                if(chars_arr[i]!=chars_arr[j]){
                    return false;
                }
                i++;
                j--;

            }
            return true;
        }
    }

    //解法二：中心回文
    static class Solution2{
        public String longestPalindrome(String s) {
            //用数组分别记录长度，起点，终点
            int[] res = new int[3];
            //每一次循环都将当前的坐标，作为回文的中心点，分为偶数和奇数中心，记录长度最长的。
            for (int i = 0; i < s.length(); i++) {
                int begin = i;
                int end = i;
                //奇数回文串，直接开始中心回文。
                while (begin-1>=0 && end+1<s.length() && s.charAt(begin-1) == s.charAt(end+1)){
                    end++;
                    begin--;
                    //比已知最长的长度长，则记录数据
                    if (end - begin > res[0]){
                        res[0] = end - begin;
                        res[1] = begin;
                        res[2] = end;
                    }
                }
                //偶数回文串，第一次先判断和下一个是否相等，然后中心回文
                begin = i;
                end = i+1;
                while (begin>=0 && end<s.length() && s.charAt(begin) == s.charAt(end)){
                    if (end - begin > res[0]){
                        res[0] = end - begin;
                        res[1] = begin;
                        res[2] = end;
                    }
                    end++;
                    begin--;
                }
            }
            return s.substring(res[1],res[2]+1);

//            作者：大大大大光
//            链接：https://leetcode.cn/leetbook/read/array-and-string/conm7/?discussion=taofDl

        }
    }

}
