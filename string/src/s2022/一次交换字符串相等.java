package s2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/18 10:45
 */
//https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal/
public class 一次交换字符串相等 {
    public static void main(String[] args) {
        Solution s = new Solution();
        String s1 = "bankcc";
        String s2 = "kanbcc";
        System.out.println(s.areAlmostEqual(s1,s2));
    }

    static class Solution {
        public boolean areAlmostEqual(String s1, String s2) {
            String[] a1 = new String[s1.length()]; //先定义两个长度为字符串长度的数组，存储不相等的字符
            String[] a2 = new String[s2.length()];

            if (s1.equals(s2)){
                return true;
            }

            int j=0;
            int s=0;//记录第几次不相等
            if (s1.length() == s2.length()){

                for (int i=0; i< s1.length(); i++){
                    /*if (j==2){
                        return false;
                    }*/
                    if (s1.charAt(i) != s2.charAt(i)){//当出现相同位置的字符不相等时进入循环
                        a1[j]=String.valueOf(s1.charAt(i));
                        a2[j]=String.valueOf(s2.charAt(i));
                        j++;
                        s++;
                        if (s == 3 || s1.length()==1){
                            return false;
                        }

                    }
                }

                if (a1[0].equals(a2[1]) && a1[1].equals(a2[0])){
                    return true;
                }
            }
            return false;
        }
    }
}
