import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/10 22:10
 */
public class 电话号码的字母组合 {  // 17. 电话号码的字母组合

    class Solution {
        //设置全局列表存储最后的结果
        List<String> list = new ArrayList<>();

        public List<String> letterCombinations(String digits) {

            if (digits == null || digits.length() == 0) {
                return list;
            }
            //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
            String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

            //迭代处理
            backTracking(digits, numString, 0);
            return list;

        }

        //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
        StringBuilder temp = new StringBuilder();

        //比如digits如果为"23",num 为0，则str表示2对应的 abc
        public void backTracking(String digits, String[] numString, int index) {
            //遍历全部一次记录一次得到的字符串
            if (index == digits.length()) {
                list.add(temp.toString()); // 注意这里要转换类型
                return;
            }
            //str 表示当前num对应的字符串
            String nowStr = numString[digits.charAt(index) - '0'];  // 取数字对应的字符集  注意这一步的字符转int操作
            for (int i = 0; i < nowStr.length(); i++) {
                temp.append(nowStr.charAt(i));
                // 回溯
                backTracking(digits, numString, index + 1);  // 递归，注意index+1，一下层要处理下一个数字了
                // 剔除末尾的继续尝试
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }
}
