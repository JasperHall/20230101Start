package s202305;

/**
 * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/20 12:22
 */
public class 实现strStr {  // 28
    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.strStr2("aabaabaafa", "aabaaf");
        System.out.println("第一次匹配的索引为: "+res);
    }

    static class Solution {
        /**
         * 暴力匹配
         * @param haystack
         * @param needle
         * @return
         */
        public int strStr(String haystack, String needle) {
            int n = haystack.length(), m = needle.length();

            for (int i=0; i+m<= n; i++){  // i+m<=n就是当前i的坐标加上待匹配字符串的长度要小于等于母字符串的长度
                boolean flag = true;
                for (int j=0; j<m; j++){  // j<m, 寻找匹配字符
                    if (haystack.charAt(i+j) != needle.charAt(j)){ // 每次循环j++, 往后取一位, 挨个对比, 如有不同就设置为false
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    return i;
                }
            }
            return -1; // 没有匹配项, 返回-1
        }

        /**
         * KMP  前缀数组减一的方案
         * @param haystack
         * @param needle
         * @return
         */
        public int strStr2(String haystack, String needle) {
            if (needle.length() == 0) return 0;

            int[] next = new int[needle.length()]; // 初始化前缀表数组
            getNext(next, needle);  // 调用自定义方法,生成前缀表数组

            int j = -1;  // j控制的是needle字符串

            for (int i=0; i<haystack.length(); i++){
                while (j>=0 && haystack.charAt(i) != needle.charAt(j+1) ){  // 注意 j+1
                    j = next[j];
                }
                if (haystack.charAt(i) == needle.charAt(j+1)){// 注意 j+1
                    j++;
                }
                if (j == needle.length()-1){
                    return (i-needle.length()+1);
                }
            }
            return -1;
        }
        // 生成前缀表数组方法
        private void getNext(int[] next, String s){   // s是传入的模式串, 就是要在另一个字符串中找到s
            int j = -1;
            next[0] = j;

            for (int i=1; i<s.length(); i++){
                // 当使用 减一 求前缀表数组时, j>=0; 当使用 不减一 求前缀数组时, j>0;
                // 这里使用减一的方法
                while (j>=0 && s.charAt(i)!=s.charAt(j+1)){ // 注意这一步中的j>=0, 这里是while, 注意这里是不等于
                    j = next[j];
                }
                if (s.charAt(i) == s.charAt(j+1)){
                    j++;
                }
                next[i] = j;
            }
        }
    }
}
