package kamacoder;

import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1000
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/16 16:16
 */
public class A加BI {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a+b);
        }
    }
}
