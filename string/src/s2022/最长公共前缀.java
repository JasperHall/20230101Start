package s2022;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/19 9:40
 */
//https://leetcode.cn/leetbook/read/array-and-string/ceda1/
public class 最长公共前缀 {

    /*
    解法一：这个方法有漏洞，不能保证strs[0]是最短的字符串，如果strs不是最短的话
    在中间for循环会造成越界
     */
    class Solution1{
        public String longestCommomPrefix(String[] strs){
            String prefix = strs[0];
            for (String s : strs){
                //startsWith() 方法用于检测字符串是否以指定的前缀开始。
                if (prefix.startsWith(s)){
                    prefix = s;
                    continue;
                }
                for (int i=0; i<prefix.length(); i++){
                    if (s.charAt(i) != prefix.charAt(i)){
                        prefix = new String(Arrays.copyOf(prefix.toCharArray(), i));
                        break;
                    }
                }
            }
            return prefix;
        }
    }
    /*
    解法二
     */
    class Solution2{
        public String longestCommomPrefix(String[] strs){
            String str = "";
            String minStr = strs[0];
            int count = 0;
            //数组长度为0，或者为null返回""
            if (strs.length == 0 || strs == null){
                return "";
            }
            //数组中有“”，返回“”
            for (int i=0; i<strs.length; i++){
                if ("".equals(strs[i])){
                    return "";
                }
            }
            //找出数组中最短的字符串
            for (int i=0; i<strs.length; i++){
                if (strs[i].length() < minStr.length()){
                    minStr = strs[i];
                }
            }
            for (int i=0; i<minStr.length(); i++){
                str = minStr.substring(0, minStr.length()-i);//最长的情况
                int j = 0;
                for (;j< strs.length; j++){
                    if (strs[j].startsWith(str)){ //如果前缀匹配，就比较下一个字符串
                        continue;
                    }
                    if (!strs[j].startsWith(str)){//如果前缀不匹配，开始进行下一轮
                        break;
                    }
                }
                if(j== strs.length){//如果成立，证明内层for循环正常结束
                    return str;
                }
            }
            return "";
        }
    }

}
