package s202302;

/**
 * https://leetcode.cn/problems/repeated-substring-pattern/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/13 21:12
 */
public class 重复的子字符串 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        boolean res = solution.repeatedSubstringPattern3("abacabac");
        System.out.println(res);
    }

    static class Solution {
        /**
         * KMP解法
         * @param s
         * @return
         */
        public boolean repeatedSubstringPattern(String s) {
            if (s.equals("")) return false;

            int len = s.length();
            //原串加个空格（哨兵），使得下标从1开始，这样j从0开始，也不用初始化了
            s = " " + s;
            char[] chars = s.toCharArray();
            int[] next = new int[len +1];

            //构造 next数组 过程，j从0开始（空格）， i从2开始
            for (int i=2, j=0; i<=len; i++){//注意这里 i从2开始
                //匹配不成功，j回到前一位置 next数组所对应的值
                while (j>0 && chars[i] != chars[j+1]){
                    j = next[j];
                }

                //匹配成功，j往后推移
                if (chars[i] == chars[j+1]){
                    j++;
                }
                //更新 next 数组的值
                next[i] = j;
            }

            //最后判断是否是重复子字符串，这里next[len]即代表next数组末尾得到值
            if (next[len] > 0 && len%(len-next[len]) == 0){//如果是重复子字符串所以next数组的最后一个值肯定大于0，
                return true;
            }
            return false;
        }

        /**
         * 枚举暴力
         * @param s
         * @return
         */
        public boolean repeatedSubstringPattern2(String s) {
            int n = s.length();//n为原字符串的长度
            for (int i=1; i*2<=n; i++){//只查看s长度的一半就可以（这里就是所谓的优化），注意 i 是从1开始计数的

                if (n % i ==0){ //取余为0证明原字符串的长度是字串的倍数，这样才能是重复的子字符串
                    boolean match = true;//这里先提前设置为true

                    for (int j = i; j<n; j++){
                        if (s.charAt(j) != s.charAt(j-i)){//注意这一步的操作是重点，  理解 j-i 的原因
                            match = false;
                            break;
                        }
                    }

                    if (match){//如果match是false进不来所以前几次有false的情况没事
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * 字符匹配
         */
        public boolean repeatedSubstringPattern3(String s) {
            System.out.println((s+s).indexOf(s, 1));
            return (s+s).indexOf(s, 1) != s.length();//不等于原字符串的长度也就说明是在合并的新字符串中间找到了合适的值，所以就是重复的子字符串
        }

    }
}
