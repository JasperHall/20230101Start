package kamacoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://kamacoder.com/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/20 18:57
 */
public class 摆平积木 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()){
            int n = sc.nextInt();

            if(n==0) break;

            int sum = 0;
            int[] ans = new int[n];

            for (int i=0; i<n; i++){
                ans[i] = sc.nextInt();
                sum += ans[i];
            }
            int avg = sum/n;

            int res = 0;
            for(int i=0; i<n; i++){
                if (ans[i] > avg){
                    res += ans[i] - avg;
                }
            }

            System.out.println(res);
            System.out.println();
        }
    }

    /**
     * 使用链表
     */
    public void sss(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            Integer size = scanner.nextInt();
            if (size == 0) {
                break;
            }
            ArrayList<Integer> list = new ArrayList<>();
            Integer sum = 0;
            for (int i = 0; i < size; i++) {
                int num = scanner.nextInt();
                sum += num;
                list.add(num);
            }
            Integer average = sum / size;
            Integer res = 0;
            //由于题目中说明了保证积木总数能被积木堆数整除，那么肯定能够把多出来的转到少的积木堆上面的
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) > average) {
                    res += list.get(i) - average;
                }
            }
            System.out.println(res);
            System.out.println();
        }
    }
}
