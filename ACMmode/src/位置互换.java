import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * https://kamacoder.com/problem.php?id=1015
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/21 16:44
 */
public class 位置互换 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){
            int n = in.nextInt();;
            in.nextLine();
            for (int i=0; i<n; i++){
                String s = in.nextLine();
                StringBuilder sb = new StringBuilder();
                for(int j=0; j<s.length(); j++){
                    if (j%2==0){
                        char temp = s.charAt(j+1);
                        sb.append(temp);
                        temp = s.charAt(j);
                        sb.append(temp);
                    }
                }
                System.out.println(sb.toString());
            }
        }
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while((str = reader.readLine())!= null){
            StringTokenizer tokenizer = new StringTokenizer(str);
            int n = Integer.parseInt(tokenizer.nextToken());
            for(int i = 0; i < n; i++){
                String s = reader.readLine();
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < s.length(); j+= 2){
                    sb.append(s.charAt(j +1)).append(s.charAt(j));
                }
                System.out.println(sb.toString());
            }
        }
    }
}
