package s202302;

/**
 * https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/10 17:51
 */
public class 左旋转字符串 {

    class Solution {
        /**
         * 解法一
         * @param s
         * @param n
         * @return
         */
        public String reverseLeftWords(String s, int n) {
            StringBuilder sb = new StringBuilder();
            int i = 0;//左指针，
            int j = n;//右指针
            while (j<=s.length()-1){
                sb.append(s.charAt(j));
                j++;
            }
            while (i < n){
                sb.append(s.charAt(i));
                i++;
            }
            return sb.toString();
        }


        /**
         * 解法二
         *
         * @param s
         * @param n
         * @return
         */
        public String reverseLeftWords2(String s, int n) {
            int len=s.length();
            StringBuilder sb=new StringBuilder(s);
            reverseString(sb,0,n-1);//旋转前面
            reverseString(sb,n,len-1);//旋转后面
            return sb.reverse().toString();//旋转全部
        }
        public void reverseString(StringBuilder sb, int start, int end) {
            while (start < end) {
                char temp = sb.charAt(start);
                sb.setCharAt(start, sb.charAt(end));
                sb.setCharAt(end, temp);
                start++;
                end--;
            }
        }


        /**
         * 解法三
         * 空间复杂度：O(1)。用原始数组来进行反转操作
         * //思路为：先整个字符串反转，再反转前面的，最后反转后面 n 个
         * @param s
         * @param n
         * @return
         */
        public String reverseLeftWords3(String s, int n) {
            char[] chars = s.toCharArray();
            reverseAA(chars, 0, chars.length - 1);
            reverseAA(chars, 0, chars.length - 1 - n);
            reverseAA(chars, chars.length - n, chars.length - 1);
            return new String(chars);
        }

        public void reverseAA(char[] chars, int left, int right) {
            while (left < right) {
                chars[left] ^= chars[right];
                chars[right] ^= chars[left];
                chars[left] ^= chars[right];
                left++;
                right--;
            }
        }


    }
}
