package kamacoder;

import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1008
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/20 20:02
 */
public class 奇怪的信 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()){
            int n = sc.nextInt();
            int res = 0;
            while(n>0){  // 只要n>0就继续循环
                int tmp = n % 10;  // 对10取余, 依次取到每一位数字
                if (tmp % 2 == 0){
                    res += tmp;
                }

                n = n/10;
            }
            System.out.println(res);
            System.out.println();
        }
    }
}
