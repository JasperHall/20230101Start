import java.util.Scanner;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/16 16:16
 */
public class A加B问题I {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a+b);
        }
    }
}
