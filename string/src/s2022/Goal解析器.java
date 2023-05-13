package s2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/12/4 20:06
 */
//https://leetcode.cn/problems/goal-parser-interpretation/
public class Goal解析器{
    public static void main(String[] args) {
        String command = "G()()()()(al)";
        Solution sol = new Solution();
        System.out.println(sol.interpret1(command));
    }

    static class Solution {
        //方法一：自写,错误
        public String interpret1(String command) {
            StringBuilder a = new StringBuilder();
            int len = command.length();
            int i = 0;
            while (i<=len-1){
                char s = command.charAt(i);
                char s2 = command.charAt(i + 1);

                if (i == len-2 && s2 == 'G'){
                    a.append('G');
                }
                if (s == 'G'){
                    a.append('G');
                }
                if (s == '(' && s2 == ')'){
                    a.append('o');
                }
                if (s == '(' && s2 == 'a'){
                    a.append('a');
                    a.append('l');
                }
                i++;

            }
            return a.toString();
        }

        //方法二：直接遍历
        public String interpret2(String command) {
            StringBuilder res = new StringBuilder();
            for (int i=0; i<command.length(); i++){
                if (command.charAt(i)=='G'){
                    res.append("G");
                }else if (command.charAt(i)=='('){
                    if (command.charAt(i+1) == ')'){
                        res.append("o");
                    }else {
                        res.append("al");
                    }
                }
            }
            return res.toString();
        }
    }
}

