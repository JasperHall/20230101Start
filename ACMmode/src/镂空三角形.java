import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1012
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/21 14:49
 */
public class 镂空三角形 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){

            String line = in.nextLine();  // 输入一行. nextLine()不识别空格
            if (line.equals("@")) break;

            String[] inputs = line.split(" "); //以空格分隔成数组
            char ch = inputs[0].charAt(0);  // 取出字符
            int n = Integer.parseInt(inputs[1]);

            printPattern(ch, n);  // 调用自己写的方法
        }

        in.close();
    }

    private static void printPattern(char ch, int n) {
        // 输出第一行
        printXXX(' ', n - 1); // 调用自己写的方法输出n-1空格
        System.out.println(ch);  // 输出一个字符

        // 输出第二行到倒数第二行
        for (int i = 2; i < n; i++) {
            printXXX(' ', n - i); // 先空格
            System.out.print(ch);  // 打印字符
            printXXX(' ', 2 * i - 3); // 再来点空格(减去两遍的要输出图形的位置,再加一个双倍的本身,一共-3)
            System.out.println(ch);//再打每行最后一个字符
        }

        // 输出最后一行
        if (n > 1) {
            printXXX(ch, 2 * n - 1);
            System.out.println();
        }

        System.out.println();
    }

    private static void printXXX(char ch, int count) {
        while (count-- > 0) {
            System.out.print(ch);
        }
    }

}
