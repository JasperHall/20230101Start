import java.util.Arrays;

/**
 * 斐波那契数列Fibonacci
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/26 0:23
 */
public class Fibonacci {
    /**
     * 递归的备忘录方法
     */
    int TopDownFibonacci(int n, int[] F){
        Arrays.fill(F, -1); // 给数组F填充成-1, 值为-1表示该斐波那契数还没有计算
        F[0] = 0; F[1] = 1;
        return getValueFibonacci(n, F);

    }

    int getValueFibonacci(int n, int[] F){
        if (F[n] != -1) return F[n];  // 不等于-1, 表示该斐波那契数已经计算过了, 可以直接取出并返回

        int u = getValueFibonacci(n-1, F) + getValueFibonacci(n-1, F);
        F[n] = u;
        return F[n];
    }
}
