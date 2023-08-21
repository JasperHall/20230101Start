import javafx.scene.web.WebHistory;

import java.util.Scanner;
import java.util.Stack;

/**
 * https://kamacoder.com/problem.php?id=1016
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/21 18:53
 */
public class 出栈合法性 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        while (in.hasNext()){
            int n = in.nextInt();
            if (n==0) break;
            Stack<Integer> stack = new Stack<>();  // 辅助栈
            int[] input = new int[n];  // 顺序排列的入栈顺序
            int[] output = new int[n];

            for (int i=0; i<n; i++){
                input[i] = i+1;
            }

            for (int i=0; i<n; i++){
                output[i] = in.nextInt();
            }

            int j = 0; // 出栈数组下标
            for (int i=0; i<input.length; i++){
                stack.push(input[i]);

                // 若stack的顶部数据与popV出栈数字相同，则数据出栈
                while (!stack.isEmpty() && stack.peek() == output[j]) {  // 注意理解这一块的逻辑
                    stack.pop();
                    j++;
                }
            }
            if (stack.isEmpty()){
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}
