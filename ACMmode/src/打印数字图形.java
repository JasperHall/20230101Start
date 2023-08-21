import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1011
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/21 14:43
 */
public class 打印数字图形 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();

            for (int i=1; i<=n; i++){
                print(n-i, i);
            }

            for (int i=n-1; i>=1; i--){  // 反过来打印少一行, 注意这里i>=1
                print(n-i, i);
            }
        }
    }

    public static void print(int blank, int n){
        //  前面需要补齐空格
        for(int i=1; i<=blank; i++){
            System.out.print(" ");  // 注意不能换行
        }
        for(int j=1; j<=n; j++){
            System.out.print(j);
        }
        for (int x = n - 1; x > 0; x--) {
            System.out.print(x);
        }
        System.out.println();  // 换行
    }
}
