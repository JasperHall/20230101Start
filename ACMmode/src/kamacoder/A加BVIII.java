package kamacoder;

import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1005
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/20 16:24
 */
public class A加BVIII {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){
            int N = in.nextInt();
            for(int i=0; i<N; i++){
                int M = in.nextInt();
                int sum = 0;
                while(M != 0){
                    int a = in.nextInt();
                    sum += a;
                    M--;
                }
                System.out.println(sum);
                if (i+1<N)  System.out.println();
            }
        }
    }
}
