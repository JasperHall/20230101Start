package DataStructure;

import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/d77d11405cc7470d82554cb392585106?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/15 15:35
 */
public class 栈的压入弹出序列 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pushV int整型一维数组
     * @param popV int整型一维数组
     * @return bool布尔型
     */
    public boolean IsPopOrder (int[] pushV, int[] popV) {
        // write code here
        // 使用辅助栈
        Stack<Integer> stack = new Stack<>();
        // 出栈数组下标
        int j = 0;
        for (int i=0;i<pushV.length;i++) {
            stack.push(pushV[i]);
            // 若stack的顶部数据与popV出栈数字相同，则数据出栈
            while (!stack.isEmpty() && stack.peek() == popV[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();

    }

    public boolean IsPopOrder2 (int [] pushV,int[] popV) {
        // size表示目前存入栈的数字数量,j 出栈数组下标
        int size = 0, j = 0;
        for (int e : pushV) {
            // 每次更新栈顶数据
            pushV[size] = e;
            // 若stack的顶部数据与popV出栈数字相同，则数据出栈
            while (size >= 0 && pushV[size] == popV[j]) {
                j++;
                size--;
            }
            size++;
        }
        return size == 0;
    }
}
