import java.util.Scanner;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/7 19:20
 */
public class 汽水瓶换饮料 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()){
            int n = sc.nextInt();
            if (n == 0) break;

            int temp = n+1;
            int res = 0;
            while(temp >= 3){
                temp = temp-3+1;
                res++;
            }
            System.out.println(res);
        }
    }
}
