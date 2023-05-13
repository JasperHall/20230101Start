package s202301;

/**
 * https://leetcode.cn/problems/backspace-string-compare/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/25 16:46
 */
public class 比较含退格的字符串 {
    public static void main(String[] args) {
        String s = "abc#";
        String t = "abd#";
        Solution solution = new Solution();
        boolean ans = solution.backspaceCompare2(s,t);
        System.out.println(ans);
    }


    static class Solution {
        /**
         * 方法一：重构字符
         * @param s
         * @param t
         * @return
         */
        public boolean backspaceCompare1(String s, String t) {
            String ans1 = build(s);
            String ans2 = build(t);
            if (ans1.equals(ans2)){//注意这里不能用==
                return true;
            }
            return false;
        }

        public String build(String str){
            StringBuilder ret = new StringBuilder();
            int length = str.length();
            for (int i=0; i<length; i++){
                char ch = str.charAt(i);
                if (ch != '#'){//当不是#号时就往StringBuilder里加一个
                    ret.append(ch);
                }else {
                    if (ret.length() > 0){//这个是#，删除上一次加进来的那个字符
                        ret.deleteCharAt(ret.length()-1);
                    }
                }
            }
            return ret.toString();
        }


        /**
         * 方法二：双指针
         */
        public boolean backspaceCompare2(String S, String T) {
            int i = S.length()-1, j=T.length()-1;//i为指针一，j为指针二。两个指针初始分别在两个字符串的末尾
            int skipS = 0, skipT = 0;//用来存储当前的#号个数。每次使用后#个数减一

            while (i >=0 || j>=0){
                //找 和 处理 S中的#
                while (i >= 0){
                    if (S.charAt(i) == '#'){
                        skipS++;
                        i--;
                    }else if (skipS > 0){
                        skipS--;
                        i--;
                    }else {
                        break;//注意break的用法
                    }
                }
                //找 和 处理 T中的#
                while (j >= 0){
                    if (T.charAt(j) == '#'){
                        skipT++;
                        j--;
                    }else if (skipT > 0){
                        skipT--;
                        j--;
                    }else {
                        break;
                    }
                }

                if (i >= 0 && j>=0){
                    if (S.charAt(i) != T.charAt(j)){
                        return false;
                    }
                }else {
                    if (i >=0 || j>= 0){
                        return false;
                    }
                }
                i--;
                j--;
            }
            return true;
        }

    }
}
