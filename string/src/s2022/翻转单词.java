package s2022;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/22 11:56
 */
//https://leetcode.cn/leetbook/read/array-and-string/crmp5/
public class 翻转单词 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        Solution solution = new Solution();
        System.out.println(solution.reverseWords_2(s));

    }
}

class Solution {
    //方法一
    //时间复杂度O（n）
    //空间复杂度O()
    public String reverseWords_1(String s) {
        String[] wordArray = s.split(" ");  //将字符按空格为分节符，转为数组
        StringBuffer stringBuffer = new StringBuffer();  //定义一个字符缓冲区
        int len = wordArray.length;
        while (len-- > 0){//len从数组最右侧开始取值，填充进stringBuffer
            if (!wordArray[len].isEmpty()){
                if (stringBuffer.length() > 0){
                    stringBuffer.append(" ");
                }
                stringBuffer.append(wordArray[len]);
            }
        }
        return stringBuffer.toString();
    }
    //方法二：双指针，原地解法
    //时间复杂度O（n）n=s.length  空间复杂度：O（1）
    public String reverseWords_2(String s){
        s = s.trim();  //删除字符串首尾部分的空格字符
        int len = s.length();  //字符串的长度
        int begin = len, end = len;  //单词起止坐标
        while (len-- > 0){
            //遇到非单词的分隔的空格符的情况
            //去掉空格符
            if (s.charAt(len)==' ' && begin==end){
                //新的字符串
                s = s.substring(0,len) + s.substring(len+1, s.length());
                begin--;
                end--;
                //遇到单词分隔的空格符的情况
            }else if (s.charAt(len)==' ' && begin != end){
                String word = s.substring(begin, end);
                s = s.substring(0, len) + (end < s.length() ? s.substring(end, s.length()) : "") + word +" ";
                begin--;
                end = begin;
                //非空格符的情况，寻找单词起始坐标
            }else {
                begin--;
            }
        }
        //处理最后一个单词
        String word = s.substring(0, end);
        s = s.substring(end, s.length()) + word;
        return s;
    }

    //方法三：栈
    public String reverseWords_3(String s){
        //设置一个栈来存放单词
        Stack<String> stack = new Stack<>();
        s.trim();
        String[] wordArray = s.split("");
        for (String word : wordArray){
            if (!word.isEmpty()){
                stack.add(word);
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (!stack.isEmpty()){
            stringBuffer.append(stack.pop());
            if (!stack.isEmpty()){
                stringBuffer.append(" ");
            }
        }
        return stringBuffer.toString();
    }

}
