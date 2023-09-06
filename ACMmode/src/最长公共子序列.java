import java.util.Scanner;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/6 10:53
 */
public class 最长公共子序列 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()){
            String a = sc.next();
            String b = sc.next();

            int[][] dp = new int[a.length()+1][b.length()+1];
            for (int i=1; i< a.length()+1; i++){
                char char1 = a.charAt(i-1);
                for (int j=1; j<b.length()+1; j++){
                    char char2 = b.charAt(j-1);

                    if (char1 == char2){
                        dp[i][j] = dp[i-1][j-1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
            System.out.println(dp[a.length()][b.length()]);
        }
    }
}
