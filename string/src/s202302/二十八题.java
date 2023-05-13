package s202302;

/**
 * https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/11 21:46
 */
public class 二十八题 {// 找出字符串中第一个匹配项的下标

    class Solution {
        public int strStr(String haystack, String needle) {
            int i = 0;//待匹配字符串的指针
            int j = 0;//要求字符串的指针
            int res = -1;//要返回的结果

            while (i < haystack.length() && j <needle.length()){
                if (haystack.charAt(i) == needle.charAt(j)){
                    i++;
                    j++;
                    res = i;
                    while (true){

                        if (haystack.charAt(i) == needle.charAt(j)){
                            i++;
                            j++;
                        }

                    }
                }
            }
            return -1;
        }

        /**
         * 基于窗口滑动的算法
         * <p>
         * 时间复杂度：O(m*n)
         * 空间复杂度：O(1)
         * 注：n为haystack的长度，m为needle的长度
         */
        public int strStr2(String haystack, String needle) {
            int m = needle.length();//模式串长度
            // 当needle是空字符串时我们应当返回0
            if (m == 0){
                return 0;
            }
            int n = haystack.length();//文本串长度
            if (n < m){
                return -1;
            }
            int i = 0;//文本串指针
            int j = 0;//模式串指针

            while (i < n-m+1){//这里用while，i的起始点要保证后面的长度大于等于模式串的长度
                //找到首字母相等
                while (i < n && haystack.charAt(i)!=needle.charAt(j)){//这里用while
                    i++;
                }

                if (i == n){//没有首字母相等的，肯定就找不到匹配的了
                    return -1;
                }
                //遍历后续字符串，判断是否相等
                i++;
                j++;

                while (i<n && j<m && haystack.charAt(i)==needle.charAt(j)){//这里用while
                    i++;
                    j++;
                }

                if (j == m){ //找到，模式串指针移动到了最后
                    return i-j;
                }else {//没找到
                    i -= j-1;//相当于  i=i-(j-1),把i向后移动了一位
                    j=0;//模式串的指针归零,然后重新循环
                }
            }
            return -1;
        }


        /**
         * KMP  解法一，（减一）
         * @param haystack
         * @param needle
         * @return
         */
        public int strStr3(String haystack, String needle) {
            if (needle.length() == 0) return 0;

            int[] next = new int[needle.length()];

            getNext(next, needle);//调用自定义的方法

            int j = -1;

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
        //生成前缀表数组
        public void getNext(int[] next, String s){// s 传进来的是模式串
            int j = -1;
            next[0] = j;
            for (int i=1; i<s.length(); i++){
                //当时用 减一 求前缀表数组时, j>=0  //当时用 不减一 求前缀数组时 j>0;
                while (j >= 0 && s.charAt(i) != s.charAt(j+1)){ //注意这一步中的 j >= 0， 这里是while
                    j = next[j];
                }

                if (s.charAt(i) == s.charAt(j+1)){
                    j++;
                }
                next[i] = j;
            }
        }


        /**
         * KMP算法，解法二（不减一）
         * @param haystack
         * @param needle
         * @return
         */
        public int strStr4(String haystack, String needle) {
            if (needle.length() == 0) return 0;
            int[] next = new int[needle.length()];//前缀数组

            getNext2(next, needle);//调用自定义的方法,去生成前缀数组

            int j=0;
            for (int i=0; i<haystack.length(); i++){
                while (j>0 && needle.charAt(j)!=haystack.charAt(i)){//注意这里是 while 循环
                    j = next[j-1];//注意这里是  j-1  不是  i-1
                }

                if (needle.charAt(j) == haystack.charAt(i)){
                    j++;
                }
                if (j == needle.length()){//当 j 移动到和长度相等说明对比完毕, 然后返回结果就可以了
                    return i-needle.length()+1; //第一个匹配的下标就是 i当前所处的位置,减去模式串的长度再加一
                }
            }
            return  -1;
        }
        //生成前缀数组
        private void getNext2(int[] next, String s){//接受前缀数组和模式串
            int j=0;
            next[0]=0;
            for (int i=1; i<s.length(); i++){
                //当时用 不减一 求前缀数组时 j>0;
                while (j>0 && s.charAt(j)!=s.charAt(i)){
                    j=next[j-1];
                }

                if (s.charAt(j)==s.charAt(i)){
                    j++;
                }

                next[i] = j;
            }
        }



    }
}
