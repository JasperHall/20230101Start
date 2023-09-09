package kamacoder;

import java.sql.SQLOutput;
import java.util.*;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/7 19:33
 */
public class 路径简化 {

    /**
     * 思路：使用栈进行模拟
     *
     * 具体做法：
     * 以 "/" 为分隔符，将 Unix 命令字符串划分为一个字符串数组
     *
     * 遍历字符串
     *   如果是 "." 则忽略
     *   如果是 "..", 且如果栈不为空：将栈顶的文件夹弹出
     *   如果是文件夹名，则将文件夹压入栈中
     *
     * 最终如果栈为空，则输出根目录 "/"
     * 将栈强转为数组，然后按照顺序打印数组中的文件夹名
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String unixCommand = scanner.nextLine();  // 输入一串cd命令

            // "/+" 是正则表达式，可匹配多个连续的 "/"，replaceAll("/+", "/") 可将多个连续的 "/" 替换为单个"/"
            String[] dirs = unixCommand.substring(3, unixCommand.length())
                    .replaceAll("/+", "/").split("/");

            pwd(dirs);  // 调用自定义的方法
        }
        scanner.close();
    }

    public static void pwd(String[] dirs) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < dirs.length; i++) {
            if (dirs[i].equals(".") || dirs[i].equals("")) { // 如果是 "." 则忽略
                continue;
            } else if (dirs[i].equals("..")) {  // 如果是 "..". 且如果栈不为空：将栈顶的文件夹弹出
                if (!stack.empty()) {
                    stack.pop();
                }
            } else {  // 剩下的一种情况就是文件夹的名字, 入栈
                stack.push(dirs[i]);
            }
        }
        Object[] array = stack.toArray();  // 这里把栈转为数组
        if (array.length == 0) {
            System.out.printf("/");
        }
        for (int i = 0; i < array.length; i++) {
            System.out.printf("/%s", array[i]);
        }
        System.out.println();
    }
}
