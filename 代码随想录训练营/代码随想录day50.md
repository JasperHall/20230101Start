时间: 2023.6.28 周三
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part11, 123.买卖股票的最佳时机III, 188.买卖股票的最佳时机IV 

<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [ ] <br />

<a name="ORijv"></a>
# 123.买卖股票的最佳时机III
:::info
这道题一下子就难度上来了，关键在于至多买卖两次，这意味着可以买卖一次，可以买卖两次，也可以不买卖。<br />视频讲解：[https://www.bilibili.com/video/BV1WG411K7AR](https://www.bilibili.com/video/BV1WG411K7AR)<br />[https://programmercarl.com/0123.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAIII.html](https://programmercarl.com/0123.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAIII.html)
:::
<a name="R2lIO"></a>
# [123. 买卖股票的最佳时机 III](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/)
:::info
困难题  ([数组](https://leetcode.cn/tag/array/), [动态规划](https://leetcode.cn/tag/dynamic-programming/))<br />这道题的关键在于**至多买卖两次**，这意味着可以买卖一次，可以买卖两次，也可以不买卖。
:::
接来下我用动态规划五部曲详细分析一下：

1. **确定dp数组以及下标的含义**

一天一共就有五个状态，<br /> 0没有操作 （其实我们也可以不设置这个状态）<br />1第一次持有股票<br />2第一次不持有股票<br />3第二次持有股票<br />4第二次不持有股票<br />**dp[i][j]中 i表示第i天，j为 [0 - 4] 五个状态，dp[i][j]表示第i天状态j所剩最大现金。**<br />需要注意：dp[i][1]，表示的是第i天，**买入股票的状态，并不是说一定要第i天买入股票**，这是很多同学容易陷入的误区。<br />例如 dp[i][1] ，并不是说 第i天一定买入股票，有可能 第 i-1天 就买入了，那么 dp[i][1] 延续买入股票的这个状态。

2. **确定递推公式**
- 达到dp[i][1]状态，有两个具体操作：
   - 操作一：第i天买入股票了，那么`dp[i][1] = dp[i-1][0] - prices[i]`
   - 操作二：第i天没有操作，而是沿用前一天买入的状态，即：`dp[i][1] = dp[i - 1][1]`

那么dp[i][1]究竟选 dp[i-1][0] - prices[i]，还是dp[i - 1][1]呢？一定是选最大的，所以 `dp[i][1] = max(dp[i-1][0] - prices[i], dp[i - 1][1]);`

- 同理dp[i][2]也有两个操作：
   - 操作一：第i天卖出股票了，那么`dp[i][2] = dp[i - 1][1] + prices[i]`
   - 操作二：第i天没有操作，沿用前一天卖出股票的状态，即：`dp[i][2] = dp[i - 1][2]`

所以`dp[i][2] = max(dp[i - 1][1] + prices[i], dp[i - 1][2])`

- 同理可推出剩下状态部分：
   - `dp[i][3] = max(dp[i - 1][3], dp[i - 1][2] - prices[i]);`
   - `dp[i][4] = max(dp[i - 1][4], dp[i - 1][3] + prices[i]);`
3. **dp数组如何初始化**
:::tips
第0天没有操作，这个最容易想到，就是0，即：dp[0][0] = 0;<br />第0天做第一次买入的操作，dp[0][1] = -prices[0];<br />第0天做第一次卖出的操作，这个初始值应该是多少呢？<br />此时还没有买入，怎么就卖出呢？ 其实大家可以理解当天买入，当天卖出，所以dp[0][2] = 0;<br />第0天第二次买入操作，初始值应该是多少呢？应该不少同学疑惑，第一次还没买入呢，怎么初始化第二次买入呢？<br />第二次买入依赖于第一次卖出的状态，其实相当于第0天第一次买入了，第一次卖出了，然后再买入一次（第二次买入），那么现在手头上没有现金，只要买入，现金就做相应的减少。<br />所以第二次买入操作，初始化为：dp[0][3] = -prices[0];<br />同理第二次卖出初始化dp[0][4] = 0;
:::

4. **确定遍历顺序**

从递归公式其实已经可以看出，一定是**从前向后遍历**，因为dp[i]，依靠dp[i - 1]的数值。

5. **举例推导dp数组**

以输入[1,2,3,4,5]为例<br />![20201228181724295-20230310134201291.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1692087918564-a5ed02c1-ea0d-476f-9317-77eb6f4d32c5.png#averageHue=%23f5f5f5&clientId=ub09f641a-1163-4&from=paste&height=353&id=u63c08424&originHeight=706&originWidth=864&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=71029&status=done&style=shadow&taskId=u86cdc3dd-8867-492c-9208-c2c5a21c2ed&title=&width=432)<br />大家可以看到红色框为最后两次卖出的状态。<br />现在**最大的时候一定是卖出的状态**，而**两次卖出的状态现金最大一定是最后一次卖出**。如果想不明白的录友也可以这么理解：如果第一次卖出已经是最大值了，那么我们可以在当天立刻买入再立刻卖出。所以dp[4][4]已经包含了dp[4][2]的情况。也就是说第二次卖出手里所剩的钱一定是最多的。<br />所以最终最大利润是dp[4][4]<br />以上五部都分析完了，不难写出如下代码：
```java
// 版本一
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;

        // 边界判断, 题目中 length >= 1, 所以可省去
        if (prices.length == 0) return 0;

        /**
         * 定义 5 种状态:
         * 0: 没有操作, 1: 第一次买入, 2: 第一次卖出, 3: 第二次买入, 4: 第二次卖出
         */
        int[][] dp = new int[len][5];
        dp[0][1] = -prices[0];
        //  初始化第二次买入的状态是确保 最后结果是最多两次买卖的最大利润
        dp[0][3] = -prices[0];

        for (int i=1; i<len; i++){
            // i天第一次买入      i-1天买入(持有)   第一次买入减金额   取最大
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            // i天第一次卖出       i-1天第一次卖出    i-1天持有,加上获利
            dp[i][2] = Math.max(dp[i - 1][2], dp[i-1][1]+prices[i]);
            // i天第二次买入(持有)   i-1天第二次买入(持有)   i-1天卖出键入今天的价价格
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);

            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }

        return dp[len - 1][4];
    }
}

时间复杂度：O(n)
空间复杂度：O(n × 5)
```
```java
// 版本二: 空间优化
class Solution {
    public int maxProfit(int[] prices) {
        int[] dp = new int[4]; 
        // 存储两次交易的状态就行了
        // dp[0]代表第一次交易的买入
        dp[0] = -prices[0];
        // dp[1]代表第一次交易的卖出
        dp[1] = 0;
        // dp[2]代表第二次交易的买入
        dp[2] = -prices[0];
        // dp[3]代表第二次交易的卖出
        dp[3] = 0;
        for(int i = 1; i <= prices.length; i++){
            // 要么保持不变，要么没有就买，有了就卖
            dp[0] = Math.max(dp[0], -prices[i-1]);
            dp[1] = Math.max(dp[1], dp[0]+prices[i-1]);
            // 这已经是第二次交易了，所以得加上前一次交易卖出去的收获
            dp[2] = Math.max(dp[2], dp[1]-prices[i-1]);
            dp[3] = Math.max(dp[3], dp[2]+ prices[i-1]);
        }
        return dp[3];
    }
}
时间复杂度：O(n)
空间复杂度：O(1)
```
大家会发现dp[2]利用的是当天的dp[1]。 但结果也是对的。<br />我来简单解释一下：<br />`dp[1] = max(dp[1], dp[0] - prices[i]);` 如果dp[1]取dp[1]，即保持买入股票的状态，那么 `dp[2] = max(dp[2], dp[1] + prices[i]);中dp[1] + prices[i]`就是今天卖出。<br />如果dp[1]取`dp[0] - prices[i]`，今天买入股票，那么`dp[2] = max(dp[2], dp[1] + prices[i]);`中的`dp[1] + prices[i]`相当于是今天再卖出股票，一买一卖收益为0，对所得现金没有影响。相当于今天买入股票又卖出股票，等于没有操作，保持昨天卖出股票的状态了。<br />这种写法看上去简单，其实思路很绕，不建议大家这么写，这么思考，很容易把自己绕进去！<br />对于本题，把版本一的写法研究明白，足以！
<a name="t7z7Q"></a>
# 188.买卖股票的最佳时机IV  
:::info
本题是123.买卖股票的最佳时机III 的进阶版  <br />视频讲解：[https://www.bilibili.com/video/BV16M411U7XJ](https://www.bilibili.com/video/BV16M411U7XJ)<br />[https://programmercarl.com/0188.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAIV.html](https://programmercarl.com/0188.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAIV.html)
:::
:::info
困难题  ([数组](https://leetcode.cn/tag/array/), [动态规划](https://leetcode.cn/tag/dynamic-programming/))<br />这道题目可以说是动态规划：123.买卖股票的最佳时机III 的进阶版，这里要求至多有k次交易。
:::
动规五部曲，分析如下：

1. **确定dp数组以及下标的含义**

在动态规划: `123.买卖股票的最佳时机III`中，我是定义了一个二维dp数组，本题其实依然可以用一个二维dp数组。<br />使用二维数组 dp[i][j] ：第i天的状态为j，所剩下的最大现金是dp[i][j]<br />j的状态表示为：

   - 0 表示不操作
   - 1 第一次买入
   - 2 第一次卖出
   - 3 第二次买入
   - 4 第二次卖出
   - .....

大家应该发现规律了吧 ，**除了0以外，偶数就是卖出，奇数就是买入**。<br />题目要求是至多有K笔交易，那么j的范围就定义为`2 * k + 1`就可以了。<br />所以二维dp数组的C++定义为：
```java
int[][] dp = new int[len][k*2 + 1];
```

2. **确定递推公式**

还要强调一下：dp[i][1]，表示的是第i天，买入股票的状态，并不是说一定要第i天买入股票，这是很多同学容易陷入的误区。

- 达到**dp[i][1]状态**，有两个具体操作：
   - 操作一：第i天买入股票了，那么`dp[i][1] = dp[i - 1][0] - prices[i]`
   - 操作二：第i天没有操作，而是沿用前一天买入的状态，即：`dp[i][1] = dp[i - 1][1]`

选最大的，所以`dp[i][1] = max(dp[i - 1][0] - prices[i], dp[i - 1][1]);`

- 同理**dp[i][2]状态**也有两个操作：
   - 操作一：第i天卖出股票了，那么`dp[i][2] = dp[i - 1][1] + prices[i]`
   - 操作二：第i天没有操作，沿用前一天卖出股票的状态，即：`dp[i][2] = dp[i - 1][2]`

所以`dp[i][2] = max(dp[i - 1][1] + prices[i], dp[i - 1][2])`<br />同理可以类比剩下的状态，代码如下：
```java
for (int j = 0; j < 2 * k - 1; j += 2) {
    dp[i][j + 1] = max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
    dp[i][j + 2] = max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
}
```
本题和动态规划：123.买卖股票的最佳时机III 最大的区别就是这里要类比j为奇数是买，偶数是卖的状态。

3. **dp数组如何初始化**

第0天没有操作，这个最容易想到，就是0，即：dp[0][0] = 0;<br />第0天做第一次买入的操作，dp[0][1] = -prices[0];<br />第0天做第一次卖出的操作，这个初始值应该是多少呢？<br />此时还没有买入，怎么就卖出呢？ 其实大家可以理解当天买入，当天卖出，所以dp[0][2] = 0;<br />第0天第二次买入操作，初始值应该是多少呢？应该不少同学疑惑，第一次还没买入呢，怎么初始化第二次买入呢？<br />第二次买入依赖于第一次卖出的状态，其实相当于第0天第一次买入了，第一次卖出了，然后在买入一次（第二次买入），那么现在手头上没有现金，只要买入，现金就做相应的减少。<br />所以第二次买入操作，初始化为：dp[0][3] = -prices[0];<br />第二次卖出初始化dp[0][4] = 0;<br />所以同理可以**推出dp[0][j]当j为奇数的时候都初始化为 -prices[0]**<br />代码如下：
```java
for (int j = 1; j < 2 * k; j += 2) {
    dp[0][j] = -prices[0];
}
```
在初始化的地方同样要类比j为偶数是卖、奇数是买的状态。

4. **确定遍历顺序**

从递归公式其实已经可以看出，一定是从前向后遍历，因为dp[i]，依靠dp[i - 1]的数值。

5. **举例推导dp数组**

以输入[1,2,3,4,5]，k=2为例。<br />![20201229100358221.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1692093751698-baf867e1-8c52-40e1-a575-9a8f1e486ad8.png#averageHue=%23f4f4f4&clientId=ub09f641a-1163-4&from=paste&height=340&id=uc699016f&originHeight=680&originWidth=838&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=67542&status=done&style=shadow&taskId=u90e6877e-d41a-4dfb-839e-02468a86293&title=&width=419)<br />最后一次卖出，一定是利润最大的，`dp[prices.size() - 1][2 * k]`即红色部分就是最后求解。
```java
// 版本一: 三维 dp数组
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;

        // [天数][交易次数][是否持有股票]
        int len = prices.length;
        int[][][] dp = new int[len][k + 1][2];
        
        // dp数组初始化
        // 初始化所有的交易次数是为确保 最后结果是最多 k 次买卖的最大利润
        for (int i = 0; i <= k; i++) {
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                // dp方程, 0表示不持有/卖出, 1表示持有/买入
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[len - 1][k][0];
    }
}
```
```java
// 版本二: 二维 dp数组
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;

        // [天数][股票状态]
        // 股票状态: 奇数表示第 k 次交易持有/买入, 偶数表示第 k 次交易不持有/卖出, 0 表示没有操作
        int len = prices.length;
        int[][] dp = new int[len][k*2 + 1];
        
        // dp数组的初始化, 与版本一同理
        for (int i = 1; i < k*2; i += 2) {  // 保证i都是奇数
            dp[0][i] = -prices[0];
        }

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < k*2 - 1; j += 2) {
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }
        return dp[len - 1][k*2];
    }
}
时间复杂度: O(n * k)，其中 n 为 prices 的长度
空间复杂度: O(n * k)
```
```java
//版本三：一维 dp数组 (下面有和卡哥邏輯一致的一維數組JAVA解法)
class Solution {
    public int maxProfit(int k, int[] prices) {
        if(prices.length == 0){
            return 0;
        }
        if(k == 0){
            return 0;
        }
        // 其实就是123题的扩展，123题只用记录2次交易的状态
        // 这里记录k次交易的状态就行了
        // 每次交易都有买入，卖出两个状态，所以要乘 2
        int[] dp = new int[2 * k];
        // 按123题解题格式那样，做一个初始化
        for(int i = 0; i < dp.length / 2; i++){
            dp[i * 2] = -prices[0];
        }
        for(int i = 1; i <= prices.length; i++){
            dp[0] = Math.max(dp[0], -prices[i - 1]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i - 1]);
            // 还是与123题一样，与123题对照来看
            // 就很容易啦
            for(int j = 2; j < dp.length; j += 2){
                dp[j] = Math.max(dp[j], dp[j - 1] - prices[i-1]);
                dp[j + 1] = Math.max(dp[j + 1], dp[j] + prices[i - 1]);
            }
        }
        // 返回最后一次交易卖出状态的结果就行了
        return dp[dp.length - 1];
    }
}
```
```java
class Solution {
    public int maxProfit(int k, int[] prices) {

        //edge cases
        if(prices.length == 0 || k == 0)
            return 0;

        
        int dp[] = new int [k * 2 + 1];

        //和卡哥邏輯一致，奇數天購入股票，故初始化只初始化奇數天。
        for(int i = 1; i < 2 * k + 1; i += 2){
            dp[i] = -prices[0];
        }

        for(int i = 1; i < prices.length; i++){ //i 從 1 開始，因爲第 i = 0 天已經透過初始化完成了。
            for(int j = 1; j < 2 * k + 1; j++){ //j 從 1 開始，因爲第 j = 0 天已經透過初始化完成了。
                //奇數天購買
                if(j % 2 == 1)
                    dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]);
                //偶數天賣出
                else
                    dp[j] = Math.max(dp[j], dp[j - 1] + prices[i]);
            }
	    //打印DP數組
            //for(int x : dp)
            //    System.out.print(x +", ");
            //System.out.println();
        }
        //return 第2 * k次賣出的獲利。
        return dp[2 * k];
    }
}
```
