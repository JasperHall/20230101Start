package s202301;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/3 9:53
 */
public class 翻转字符串中的单词 {
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
       Solution solution = new Solution();

        System.out.println(solution.reverseWords2(s));
    }

    static class Solution{
        public String reverseWords(String s) {
            String[] split = s.split(" ");//使用split将每个单词取出
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0; i<split.length; i++){
                char[] chars = split[i].toCharArray();//将每个单词转为字符数组
                for (int j=chars.length-1; j>=0; j--){ //将每个字符数组反转
                    stringBuilder.append(chars[j]);
                }
                if (i!=split.length-1){//最后一个字符不加空格
                    stringBuilder.append(" ");//每个单词用空格隔开
                }
            }
            return stringBuilder.toString();
        }

        /**
         * 局部反转
         * @param s
         * @return
         */
        public String reverseWords2(String s) {
            char[] origin = s.toCharArray(); //每个字符都拆开存入origin
            int i=0, j=0, len=origin.length;
            for (; i<len; i++){
                char ch = origin[i];//遍历所有origin的字符
                if (ch == ' ' || i == len-1){
                    int m=j, n=ch==' ' ? i-1 : i;
                    while (m <= n){
                        char temp = origin[m];
                        origin[m++] = origin[n];
                        origin[n--] = temp;
                    }
                    j = i+1;
                }
            }
            return new String(origin);
        }
    }

}
