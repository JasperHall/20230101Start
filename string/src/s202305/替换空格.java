package s202305;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/18 1:04
 */
public class 替换空格 {

    class Solution {
        /**
         * 暴力
         * @param s
         * @return
         */
        public String replaceSpace(String s) {
            if (s == null) return null;

            // 选用StringBuilder单线程使用，比较快，选不选都行
            StringBuilder sb = new StringBuilder();

            // 使用sb逐个复制s，碰到空格则替换，否则直接复制
            for (int i=0; i<s.length(); i++){
                // s.charAt(i) 为char类型，为了比较需要将其转为和 “ ” 相同的字符串类型
                // if("".equals(String.valueOf(s.charAt(i))){}
                if (s.charAt(i) == ' '){
                    sb.append("%20");
                } else {
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
            // 扩充空间，空格数量2倍
            StringBuilder str = new StringBuilder();
            for (int i=0; i<s.length(); i++){
                if (s.charAt(i) == ' '){
                    str.append("  "); // 这里是两个空格,考虑为啥是加两个空格，因为原来的一个空格替换为%20多了两个字符，所以加上两个空格的位置备用
                }
            }
            // 若是没有空格则直接返回
            if (str.length() == 0){
                return s;
            }

            // 有空格的情况，定义两个指针
            int left = s.length() - 1; // 左指针：指向原始字符串最后一个位置
            s += str.toString(); // 这一步为对原始字符串的拓展,   很重要
            int right = s.length() -1; //右指针：指向扩展字符串的最后一个位置

            char[] chars = s.toCharArray(); // 注意这个方法的使用

            while (left >= 0){
                if (chars[left] == ' '){
                    chars[right--] = '0';  // 先做了赋值再减减
                    chars[right--] = '2';
                    chars[right] = '%';  // 最后一个位置的填充不减减
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
