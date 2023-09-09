package kamacoder;

import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1014
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/21 16:04
 */
public class 神秘字符 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.nextLine();
        for (int i=1; i<=n; i++){
            String s1 = in.nextLine();
            String s2 = in.nextLine();

            int len = s1.length()/2;

            StringBuilder sb = new StringBuilder();
            for(int j=0; j<len; j++){
                sb.append(s1.charAt(j));
            }
            sb.append(s2);
            for(int k=len; k<s1.length(); k++){
                sb.append(s1.charAt(k));
            }

            System.out.println(sb.toString());
        }
    }
}
