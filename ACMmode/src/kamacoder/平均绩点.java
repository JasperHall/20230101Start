package kamacoder;

import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1006
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/20 16:55
 */
public class 平均绩点 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()){
            String line = sc.nextLine();   // 输入一串大写字母

            String[] degrees = line.split(" ");  // 用空格分隔,转为数组, 可以方便利用数组长度算平均数

            double avg = 0;//平均数
            boolean flag = false;

            for(String degree : degrees){
                int num = convert(degree);  // 遇到不在规定范围内的字母返回 -1

                if (num < 0){
                    flag = true;
                    break;
                }
                avg += num*1.0 / degrees.length;  // 在这里计算
            }

            if(flag){  // 如果是真, 就说明遇到了不在规定范围内的字母
                System.out.println("Unknown");
            } else {
                System.out.printf("%.2f\n", avg);
            }
        }
    }
    private static int convert(String degree) {
        switch (degree) {
            case "A":
                return 4;
            case "B":
                return 3;
            case "C":
                return 2;
            case "D":
                return 1;
            case "F":
                return 0;
            default:
                return -1;
        }
    }
}
