package a2022;

import java.util.Arrays;
import java.util.Scanner;

public class 输入输出数组
{
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new  int[n];
        for (int i = 0; i<a.length; i++){
            a[i] = in.nextInt();
        }
        for (int i =0; i<a.length; i++){
            System.out.println(a[i]);
        }
        System.out.println(Arrays.toString(a));

    }
}
