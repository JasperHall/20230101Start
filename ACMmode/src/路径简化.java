import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/7 19:33
 */
public class 路径简化 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()){
            String s = sc.nextLine();
            String right = s.substring(4);
            String[] order = right.split("/");

            System.out.println(Arrays.toString(order));

            Deque<String> stack = new ArrayDeque<>();

            for (int i=0; i<order.length; i++){
                if ("..".equals(order[i])){
                    if (!stack.isEmpty()){
                        stack.pollFirst();
                    }
                } else if (".".equals(order[i]) || "".equals(order[i])){
                } else {
                    stack.push(order[i]);
                }
            }

            StringBuffer sb = new StringBuffer();

            while (!stack.isEmpty()){
                sb.append("/");
                sb.append(stack.pollLast());
            }
            System.out.println(sb.toString());
        }
    }
}
