package s202303;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/19 11:22
 *
 * https://leetcode.cn/problems/simplify-path/
 */
public class 简化路径 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String test = "/home/";
        String res = solution.simplifyPath(test);
        System.out.println(res);
    }

    static class Solution {
        /**
         * 方法一
         * 一句话解释: 栈解决,把当前目录压入栈中,遇到..弹出栈顶,最后返回栈中元素.
         * @param path
         * @return
         */
        public String simplifyPath(String path) {
            Deque<String> stack = new LinkedList<>();

            for (String item : path.split("/")){
                //注意下面三个if判断的的位置
                if (item.equals("..")){
                    if (!stack.isEmpty()){
                        stack.pop();//弹出
                    }
                }else if (!item.isEmpty() && !item.equals(".")){
                    stack.push(item);
                }

            }
            String res = "";
            for (String d : stack){
                res = "/"+d+res;
            }
            return res.isEmpty() ? "/" : res;
        }


        /**
         * 方法二
         *
         * 1. 栈用来维护路径中的目录，将路径以"/"进行分割，我们会得到“.”、“..”、“路径”和“ ”四种
         * 2. 其中因为“..”表示上级路径，因此如果栈顶有元素的话，要将其弹出，表示切换到上级目录
         * 3. 至于“.”，因为表示的是当前目录，实际上和“ ”一样没有什么作用，直接无视掉就好了
         * 4. 最后栈中剩下的元素，再使用"/"拼接回去，就大功告成了。
         * @param path
         * @return
         */
        public String simplifyPath2(String path) {
            String[] path_s = path.split("/");
            Deque<String> stack = new LinkedList<>();

            for (String item : path_s){
                // .. 表示上一级
                if (item.equals("..")){
                    if (!stack.isEmpty()){
                        stack.pop();//弹出
                    }
                } else if (!item.equals("") && !item.equals(".")){// '.'表示当前目录，和''一样没有价值可以无视掉
                    stack.push(item);
                }
            }
            String res = "";
            if (stack.size() >0){  //注意这里的判断, 当最后返回的是根目录时,stack是空的
                for (String item : stack){
                    res = "/"+item+res;
                }
            }else {
                res = "/";
            }

            return res;
        }

        /**
         * 官方题解
         * 时间复杂度: O(n), 其中n是字符串path 的长度
         * 空间复杂度: O(n), 我们需要O(n)的空间存储 names中所有字符串
         * @param path
         * @return
         */
        public String simplifyPath3(String path) {
            String[] names = path.split("/");
            Deque<String> stack = new ArrayDeque<String>();
            for (String name : names) {
                if ("..".equals(name)) {
                    if (!stack.isEmpty()) {
                        stack.pollLast();
                    }
                } else if (name.length() > 0 && !".".equals(name)) {
                    stack.offerLast(name);
                }
            }
            StringBuffer ans = new StringBuffer();
            if (stack.isEmpty()) {
                ans.append('/');
            } else {
                while (!stack.isEmpty()) {
                    ans.append('/');
                    ans.append(stack.pollFirst());
                }
            }
            return ans.toString();
        }
    }
}
