import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1003
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/20 15:57
 */
public class A加BIV {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int sum = 0;  // 注意在这里定义sum, 在第一个while外面定义的话会累加
            if (n==0) break;
            while(n!=0){
                int a = in.nextInt();
                sum += a;
                n--;
            }
            System.out.println(sum);
        }
    }
}
