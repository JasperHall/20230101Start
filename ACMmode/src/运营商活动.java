import java.util.Scanner;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/20 20:44
 */
public class 运营商活动 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while(in.hasNextInt()){
            int m = in.nextInt();
            int k = in.nextInt();
            if (m == 0 && k == 0) break;

            int res = m + m/k;

            int remain = m/k + m%k;
            while ( remain / k != 0){  // 这里是除号, 注意理解这里为啥用除法
                res += remain / k;
                remain = remain/k + remain%k;
            }
            System.out.println(res);
        }
    }
}
