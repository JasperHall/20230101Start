package s202302;

/**
 * https://leetcode.cn/problems/reverse-string-ii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/7 10:35
 */
public class 反转字符串II {

    class Solution {
        //题目的意思其实概括为 每隔2k个反转前k个，尾数不够k个时候全部反转
        /**
         * 解法一
         * 注意本方法中substring(),reverse()的使用
         * @param s
         * @param k
         * @return
         */
        public String reverseStr1(String s, int k) {
            StringBuffer res = new StringBuffer();
            int len = s.length();
            int start = 0;

            while (start < len){
                //找到k处和2k处
                StringBuffer temp = new StringBuffer();
                //与length进行判断，如果大于len了，那就将其置为len
                int fitstK = (start + k > len) ? len :start+k;
                int secondK = (start + (2*k) >len) ? len :start+(2*k);

                //无论start所处位置，至少会反转一次
                temp.append(s.substring(start, fitstK));//substring() 返回start到firstK字符，左闭右开
                res.append(temp.reverse());

                //如果firstK到secondK之间有元素，这些元素直接放入res即可。
                if (fitstK < secondK){
                    res.append(s.substring(fitstK, secondK));//前面append的是反转后的，这里再取出后面没反转的那部分加到结果字符串中
                }
                start += (2 * k);//注意这里每次循环加2k
            }
            return res.toString();
        }


        /**
         * 解法二（应该是更好理解
         * 注意本方法中s.toCharArray(),异或运算的反转
         * @param s
         * @param k
         * @return
         */
        public String reverseStr2(String s, int k) {
            char[] ch = s.toCharArray();//字符串转为数组
            for (int i=0; i<ch.length; i += 2*k){
                int start = i;
                //这里是判断尾数够不够k个来取决end指针的位置
                int end = Math.min(ch.length - 1, start + k-1);//这一步的方法很重要
                //用异或运算反转
                while (start < end){
                    ch[start] ^= ch[end];
                    ch[end] ^= ch[start];
                    ch[start] ^= ch[end];
                    start++;
                    end--;
                }
            }
            return new String(ch);
        }


        /**
         * 解法二的变形
         * 用temp来交换数值
         */
        public String reverseStr3(String s, int k) {
            char[] ch = s.toCharArray();//字符串转为数组
            for(int i = 0;i < ch.length;i += 2 * k){
                int start = i;
                // 判断尾数够不够k个来取决end指针的位置
                int end = Math.min(ch.length - 1,start + k - 1);//这一步的方法很重要
                while(start < end){

                    char temp = ch[start];
                    ch[start] = ch[end];
                    ch[end] = temp;

                    start++;
                    end--;
                }
            }
            return new String(ch);
        }




    }
}
