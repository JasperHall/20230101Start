package DataStructure;

import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/3194a4f4cf814f63919d0790578d51f3?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/16 12:07
 */
public class 翻转单词序列 {

    /**
     * 双指针
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        int n = str.length();
        char[] c = str.toCharArray();

        //第一次整体翻转
        reverse(c, 0, n-1);

        for (int i=0; i<n; i++){
            int j=i;
            // 以空格为界找到一个单词
            while (j<n && c[j] !=' '){
                j++;
            }
            // 将这个单词翻转
            reverse(c, i, j-1); // j的位置是空格, 要用j-1
            i = j;
        }
        return new String(c);
    }
    // 字符串反转方法
    private void reverse(char[] c, int left, int right){
        while (left < right){
            swap(c, left++, right--);
        }
    }
    // 字符串交换函数
    private void swap(char[] c, int left, int right){
        char temp = c[left];
        c[left] = c[right];
        c[right] = temp;
    }

    /**
     * 栈
     * @param str
     * @return
     */
    public String ReverseSentence2(String str) {
        Stack<String> st = new Stack<String>();
        String[] temp = str.split(" ");  // 使用split方法按空格分成数组

        // 单词加入栈中
        for (int i = 0; i < temp.length; i++) {
            st.push(temp[i]);
            st.push(" ");
        }

        StringBuilder res = new StringBuilder();
        // 去掉最后一个空格
        if (!st.isEmpty()) st.pop();//弹出

        // 栈遵循先进后厨，单词顺序是反的
        while (!st.isEmpty()){
            res.append(st.pop());
        }
        return res.toString();

    }
}
