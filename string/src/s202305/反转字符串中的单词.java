package s202305;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

/**
 * https://leetcode.cn/problems/reverse-words-in-a-string/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/19 23:23
 */
public class 反转字符串中的单词 {  // 151

    public static void main(String[] args) {
        Solution solution = new Solution();
        String res = solution.reverseWords(" hello world  ");
        System.out.println(res);
    }

    static class Solution {
        /**
         * 解法一: 不使用Java内置的方法实现
         *
         * 步骤:
         * 1.去除首尾以及中间多余空格
         * 2.反转整个字符串
         * 3.反转各个单词
         * @param s
         * @return
         */
        public String reverseWords(String s) {
            System.out.println("待反转的的字符串为: " + s);
            // 1.去除首位及中间多余空格
            StringBuilder sb = removeSpace(s); // 调用自定义的方法
            // 2.反转整个字符串
            reverseString(sb, 0, sb.length()-1); // 调用自定义的方法
            System.out.println("反转整个字符串后: " + sb);
            // 3.反转每个单独的单词
            reverseEachWord(sb);  // 调用自定义的方法
            System.out.println("处理完后的字符串为: "+ sb.toString());
            return sb.toString();

        }
        // 删除首尾的空格方法
        private StringBuilder removeSpace(String s){
            int start = 0;
            int end = s.length()-1;

            while (s.charAt(start) == ' ') start++;  // 前面有空格就++跳过
            while (s.charAt(end) == ' ') end--;  // 后面有空格就--跳过

            // 清除完前后的空格，再来执行下面
            StringBuilder sb = new StringBuilder();
            while (start <= end){
                char c = s.charAt(start);
                // 这里是清除字符串中间多余的空格，思考这一步. 假设有两个单词直接为两个空格的话,start 索引到达第二个的空格,
                // 就意味着有一个空格已经被 append 到了sb中, 则此时 sb 的最后一位也是空格, 不满足进入 if 内部的条件, 直接下一步 start++ 跳过了多余的空格
                if (c != ' ' || sb.charAt(sb.length()-1)!=' '){
                    sb.append(c);
                }
                start++;
            }
            System.out.println("清除完多余空格后: " + sb);
            return sb;
        }
        // 反转整个字符串方法  , 使用头尾指针的方法
        private void reverseString(StringBuilder sb, int start, int end){ //反转字符串指定区间[start, end]的字符
            while (start < end){
                char temp = sb.charAt(start); // 暂存一下第一个
                sb.setCharAt(start, sb.charAt(end));  // 将最后一个放到前面
                sb.setCharAt(end, temp);  // 将之前暂存的第一个放到后面
                start++;
                end--;
            }
        }
        // 反转各个单词方法 , 快慢指针
        private void reverseEachWord(StringBuilder sb){
            int start = 0;  // 慢指针
            int end = 1;  // 快指针
            int n = sb.length();
            while (start < n){
                while (end<n && sb.charAt(end)!=' '){  // 快指针向前走, 遇到空格则说明到达一个单词的边界
                    end++;
                }
                reverseString(sb, start, end-1);  // 注意这一步很重要，调用自定义的反转字符方法
                start = end + 1; // 注意这两步的顺序
                end = start + 1;
            }
        }

        /**
         * 双端队列法
         * 使用了 Deque 类型
         * @param s
         * @return
         */
        public String reverseWords2(String s) {
            int left=0, right=s.length()-1;

            // 去掉字符串开头的空格
            while (left<=right && s.charAt(left)==' '){
                left++;
            }
            // 去掉字符串末尾的空白字符
            while (left<=right && s.charAt(right)==' '){
                right--;
            }

            Deque<String> dq = new ArrayDeque<>();  // 注意学习队列的用法
            StringBuilder sb = new StringBuilder();

            while (left<=right){  // 注意这里要有等于号
                char temp = s.charAt(left);
                if ((sb.length()!=0) && (temp==' ')){  // sb长度不为零, temp遇到空格, 说明到了一个单词的边界分隔处
                    // 将单词 push 到队列的头部
                    dq.offerFirst(sb.toString());
                    sb.setLength(0);  // 重新将sb长度设置为0, 进行下一个单词的操作
                } else if (temp != ' '){
                    sb.append(temp);
                }
                left++;
            }
            dq.offerFirst(sb.toString());  // 这一步是将最后一个单词加入到队列, 因为到最后时没有空格进入不了第一个if判断来进行加入

            return String.join(" ", dq);  // 注意这个方法的使用
        }
    }
}
