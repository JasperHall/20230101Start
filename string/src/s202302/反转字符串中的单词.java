package s202302;

/**
 * https://leetcode.cn/problems/reverse-words-in-a-string/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/10 15:26
 */
public class 反转字符串中的单词 {

    class Solution {
        /**
         * 解法一
         *
         * 有双指针的使用
         *
         * 不使用Java内置方法实现
         * <p>
         * 1.去除首尾以及中间多余空格
         * 2.反转整个字符串
         * 3.反转各个单词
         */
        public String reverseWords(String s) {
            // System.out.println("ReverseWords.reverseWords2() called with: s = [" + s + "]");
            // 1.去除首尾以及中间多余空格
            StringBuilder sb = removeSpace(s);//调用自定义的方法
            // 2.反转整个字符串
            reverseString(sb, 0, sb.length()-1);//调用自定义方法
            // 3.反转各个单词
            reverseEachWord(sb);//调用自定义的方法
            return sb.toString();

        }
        private StringBuilder removeSpace(String s){//删除首尾的空格
            // System.out.println("ReverseWords.removeSpace() called with: s = [" + s + "]");
            int start = 0;
            int end = s.length()-1;

            while (s.charAt(start) == ' ') start++;//前面有空格就++跳过
            while (s.charAt(end) == ' ') end--;//后面有空格就--跳过

            //清除完前后的空格，再来执行下面
            StringBuilder sb = new StringBuilder();
            while (start <= end){
                char c = s.charAt(start);
                if (c != ' ' || sb.charAt(sb.length()-1) != ' '){//这里是清除字符串中间多余的空格，思考这一步
                    sb.append(c);
                }
                start++;
            }
            // System.out.println("ReverseWords.removeSpace returned: sb = [" + sb + "]");
            return sb;
        }
        //前后双指针
        private void reverseString(StringBuilder sb ,int start, int end){//反转字符串指定区间[start, end]的字符
            // System.out.println("ReverseWords.reverseString() called with: sb = [" + sb + "], start = [" + start + "], end = [" + end + "]");
            while (start < end) {
                char temp = sb.charAt(start);//暂存一下第一个
                sb.setCharAt(start, sb.charAt(end));//将最后一个放到前面
                sb.setCharAt(end, temp);//将暂存的第一个放到后面
                start++;
                end--;
            }
        }
        private void reverseEachWord(StringBuilder sb){//反转各个单词
            int start = 0;
            int end = 1;
            int n = sb.length();
            while (start < n){
                while (end < n && sb.charAt(end)!=' '){
                    end++;
                }
                reverseString(sb, start, end-1);//注意这一步很重要，调用自定义的反转字符方法
                start = end+1;
                end = start+1;
            }
        }



        /**
         * 解法二
         *
         * 创建新字符数组填充。时间复杂度O(n)
         * @param s
         * @return
         */
        public String reverseWords2(String s) {
            //源字符数组
            char[] initialArr = s.toCharArray();
            //新字符数组
            char[] newArr = new char[initialArr.length+1];//下面循环添加"单词 "，最终末尾的空格不会返回
            int newArrPos = 0;//作为新字符数组的索引

            //i来进行整体对源字符数组从后往前遍历
            int i = initialArr.length-1;
            while (i >= 0){
                while (i>=0 && initialArr[i]==' ') i--;//跳过空格

                //此时i位置是边界或!=空格，先记录当前索引，之后的while用来确定单词的首字母的位置
                int right = i;
                while(i>=0 && initialArr[i] != ' ') i--;

                //指定区间单词取出(由于i为首字母的前一位，所以这里+1,)，取出的每组末尾都带有一个空格.由上面的逻辑来看i的位置已经是空格了，所以要+1才能取到字母
                for (int j=i+1; j<=right; j++){
                    newArr[newArrPos++] = initialArr[j];
                    if (j == right){
                        newArr[newArrPos++] = ' ';//空格
                    }
                }
            }

            //若是原始字符串没有单词，直接返回空字符串；若是有单词，返回0-末尾空格索引前范围的字符数组(转成String返回)
            if(newArrPos == 0){
                return "";
            }else{
                return new String(newArr,0,newArrPos-1);//注意这个方法的使用
            }
        }

        /**
         * 方法三
         *
         * 参考卡哥 c++ 代码的三步骤：先移除多余空格，再将整个字符串反转，最后把单词逐个反转
         * 有别于解法一 ：没有用 StringBuilder  实现，而是对 String 的 char[] 数组操作来实现以上三个步骤
         * @param s
         * @return
         */
        public String reverseWords3(String s) {
            //用 char[] 来实现 String 的 removeExtraSpaces，reverse 操作
            char[] chars = s.toCharArray();
            //1.去除首尾以及中间多余空格
            chars = removeExtraSpaces(chars);//调用自定义方法
            //2.整个字符串反转
            reverse(chars, 0, chars.length - 1);//调用自定义方法
            //3.单词反转
            reverseEveryWord(chars);//调用自定义方法
            return new String(chars);
        }

        //1.用 快慢指针 去除首尾以及中间多余空格，可参考数组元素移除的题解
        public char[] removeExtraSpaces(char[] chars) {
            int slow = 0;
            for (int fast = 0; fast < chars.length; fast++) {
                //先用 fast 移除所有空格
                if (chars[fast] != ' ') {
                    //在用 slow 加空格。 除第一个单词外，单词末尾要加空格
                    if (slow != 0)
                        chars[slow++] = ' ';
                    //fast 遇到空格或遍历到字符串末尾，就证明遍历完一个单词了
                    while (fast < chars.length && chars[fast] != ' ')
                        chars[slow++] = chars[fast++];
                }
            }
            //相当于 c++ 里的 resize()
            char[] newChars = new char[slow];
            System.arraycopy(chars, 0, newChars, 0, slow);
            return newChars;
        }

        //双指针实现指定范围内字符串反转，可参考字符串反转题解
        public void reverse(char[] chars, int left, int right) {
            if (right >= chars.length) {
                System.out.println("set a wrong right");
                return;
            }
            while (left < right) {
                chars[left] ^= chars[right];
                chars[right] ^= chars[left];
                chars[left] ^= chars[right];
                left++;
                right--;
            }
        }

        //3.单词反转
        public void reverseEveryWord(char[] chars) {
            int start = 0;
            //end <= s.length() 这里的 = ，是为了让 end 永远指向单词末尾后一个位置，这样 reverse 的实参更好设置
            for (int end = 0; end <= chars.length; end++) {
                // end 每次到单词末尾后的空格或串尾,开始反转单词
                if (end == chars.length || chars[end] == ' ') {
                    reverse(chars, start, end - 1);
                    start = end + 1;
                }
            }
        }

    }
}
