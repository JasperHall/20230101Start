package kamacoder;

import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1013
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/21 15:34
 */
public class 句子缩写 {
    /**
     * 解法一
     * @param args
     */
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){

            int n = in.nextInt();
            in.nextLine();
            for (int i=0; i<n; i++){  // 输入n行

                String s = in.nextLine();
                StringBuilder sb = new StringBuilder();
                boolean flag = true;

                for(int j=0; j<s.length(); j++){
                    char ch = s.charAt(j);
                    if (flag){
                        sb.append(ch);  // 只收集首字母
                        flag = false;
                    }
                    if(ch==' '){
                        while(j<s.length() && s.charAt(j)==' '){  // 这里一直有空格就一直循环
                            j++;
                        }
                        j--;//和for循环一起就多加了一次, 上面的循环会多加一次,这里减掉
                        flag = true;//表示空格读完，接下来可以收集首字母了
                    }
                }
                System.out.println(sb.toString().toUpperCase());
            }
        }
    }


    /**
     * 解法二
     * @param args
     */
    public  void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine().trim();
                char[] arr = line.toCharArray();
                StringBuilder sb = new StringBuilder();
                int j = 0;
                while (j < arr.length) {
                    if (arr[j] <= 'z' && arr[j] >= 'a') {
                        arr[j] = Character.toUpperCase(arr[j]);
                    }
                    sb.append(arr[j]);
                    while (j < arr.length && arr[j] != ' ') {
                        j++;
                    }
                    while (j < arr.length && arr[j] == ' ') {
                        j++;
                    }
                }
                System.out.println(sb.toString());
            }
        }
    }
}
