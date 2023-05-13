package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/29 23:27
 */
//https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
public class 买卖股票的最佳时机 {

    public static void main(String[] args) {
        int[] a = {5,8,1,6,40,6};

        System.out.println(maxProfit2(a));
    }

    /**
     * 方法一：自己写的，java用暴力超时....
     * @param prices
     * @return
     */
    public static int maxProfit1(int[] prices) {
        int n=prices.length;
        int maxPrice=0;
        for (int i=0; i<=n-1;i++){
            for (int j=i+1;j<=n-1;j++){
                int p = prices[j]-prices[i];
                if (p>maxPrice){
                    maxPrice=p;
                }
            }
        }
        return maxPrice;
    }

    /**
     * 方法二：遍历一次就可以，价格最小值存入cost，利润最大值存入profit。
     * @param prices
     * @return
     */
    //若数组为[5,2,60,1,15],当遍历到值为1时，最小cost会改变为1，但是profit还是上次的60-2=58
    public static int maxProfit2(int[] prices) {
        int cost = Integer.MAX_VALUE;
        int profit = 0;//利润0
        for (int price : prices){
            cost = Math.min(cost, price);  //每次比比是不是最小值
            profit = Math.max(profit, price-cost);
        }
        return profit;
    }
}
