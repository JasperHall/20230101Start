import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1001
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/16 16:34
 *
 * 注意，测试数据不仅仅一组。也就是说，会持续输入N以及后面的a和b
 */
public class A加BII {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){
            int n = in.nextInt();
            while(n-- > 0){
                int a = in.nextInt();
                int b = in.nextInt();
                System.out.println(a+b);
            }
        }
    }
}
