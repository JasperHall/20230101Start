package s202302;

/**
 * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/8 23:14
 */
public class 替换空格 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "We are ss";
        String res = solution.replaceSpace2(str);
        System.out.println(res);
    }

    static class Solution {
        /**
         * 方法一
         * 使用一个新的对象，复制 str，复制的过程对其判断，是空格则替换，否则直接复制，类似于数组复制
         *
         * 注意StringBuilder的使用
         * @param s
         * @return
         */
        public String replaceSpace1(String s) {
            if (s == null){
                return null;
            }
            //选用StringBuilder单线程使用，比较快，选不选都行
            StringBuilder sb = new StringBuilder();
            //使用sb逐个复制s，碰到空格则替换，否则直接复制
            for (int i=0; i<s.length(); i++){
                //s.charAt(i) 为char类型，巍峨比较需要将其转为和 “ ” 相同的字符串类型
                //if("".equals(String.valueOf(s.charAt(i))){}
                if (s.charAt(i) == ' '){
                    sb.append("%20");
                }else {
                    sb.append(s.charAt(i));
                }
            }
            return sb.toString();
        }

        /**
         * 方法二
         * 双指针法，倒着的快慢指针
         * @param s
         * @return
         */
        public String replaceSpace2(String s) {
            if(s == null || s.length() == 0){
                return s;
            }
            //扩充空间，空格数量2倍
            StringBuilder str = new StringBuilder();
            for (int i=0; i<s.length(); i++){
                if (s.charAt(i) == ' '){
                    str.append("  ");//这里是两个空格,考虑为啥是加两个空格，因为原来的一个空格替换为%20多了两个字符，所以加上两个空格的位置备用
                }
            }
            //若是没有空格则直接返回
            if (str.length() == 0){
                return s;
            }

            //有空格的情况，定义两个指针
            int left = s.length() - 1;//左指针：指向原始字符串最后一个位置
            s += str.toString();//这一步为拓展,很重要
            int right = s.length() -1;//右指针：指向扩展字符串的最后一个位置

            char[] chars = s.toCharArray();//注意这个方法的使用

            while (left >= 0){
                if (chars[left] == ' '){
                    chars[right--] = '0';
                    chars[right--] = '2';
                    chars[right] = '%';
                }else {
                    chars[right] = chars[left];
                }
                left--;
                right--;
            }
            return new String(chars);

        }
    }
}
