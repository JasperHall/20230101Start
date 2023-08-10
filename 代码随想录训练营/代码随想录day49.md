时间: 2023.6.27 周二
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part10, 121. 买卖股票的最佳时机, 122.买卖股票的最佳时机II 
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [ ] <br />

股票问题是一个动态规划的系列问题，今日安排的题目不多，大家可以慢慢消化。
<a name="JAyX5"></a>
# 121. 买卖股票的最佳时机
:::info
视频讲解：[https://www.bilibili.com/video/BV1Xe4y1u77q](https://www.bilibili.com/video/BV1Xe4y1u77q)<br />[https://programmercarl.com/0121.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BA.html](https://programmercarl.com/0121.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BA.html)
:::
:::info
简单题  ([数组](https://leetcode.cn/tag/array/), [动态规划](https://leetcode.cn/tag/dynamic-programming/))
:::
<a name="bCtlH"></a>
## 暴力
这道题目最直观的想法，就是暴力，找最优间距了。
```java
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int result = 0;
        for (int i = 0; i < prices.size(); i++) {
            for (int j = i + 1; j < prices.size(); j++){
                result = max(result, prices[j] - prices[i]);
            }
        }
        return result;
    }
};

时间复杂度：O(n^2)
空间复杂度：O(1)
```
<a name="WNBbv"></a>
## 贪心
因为股票就买卖一次，那么贪心的想法很自然就是取最左最小值，取最右最大值，那么得到的差值就是最大利润。
```java
class Solution {
    public int maxProfit(int[] prices) {
        // 找到一个最小的购入点
        int low = Integer.MAX_VALUE;
        // res不断更新，直到数组循环完毕
        int res = 0;
        for(int i = 0; i < prices.length; i++){
            low = Math.min(prices[i], low);
            res = Math.max(prices[i] - low, res);
        }
        return res;
    }
}
时间复杂度：O(n)
空间复杂度：O(1)
```
<a name="bqcwc"></a>
## 动态规划
动规五部曲分析如下：

1. **确定dp数组（dp table）以及下标的含义**

dp[i][0] 表示**第i天持有股票所得最多现金** ，这里可能有同学疑惑，本题中只能买卖一次，持有股票之后哪还有现金呢？其实一开始现金是0，那么加入第i天买入股票现金就是 -prices[i]， 这是一个负数。<br />dp[i][1] 表示**第i天不持有股票所得最多现金**<br />注意这里说的是“持有”，“持有”不代表就是当天“买入”！也有可能是昨天就买入了，今天保持持有的状态<br />很多同学把“持有”和“买入”没区分清楚。在下面递推公式分析中，会进一步讲解。

2. **确定递推公式**

如果第i天**持有**股票即dp[i][0]， 那么可以由两个状态推出来

   - 第i-1天 就持有股票，那么就保持现状，所得现金就是昨天持有股票的所得现金 即：dp[i - 1][0]
   - 第i天 买入股票，所得现金就是买入今天的股票后所得现金即：-prices[i]  (这里是负数后面直接加就相当于减)

那么dp[i][0]应该选所得现金最大的，所以`**dp[i][0] = max(dp[i-1][0], -prices[i]);**`<br />如果第i天**不持有**股票即dp[i][1]， 也可以由两个状态推出来

   - 第i-1天 就不持有股票，那么就保持现状，所得现金就是昨天不持有股票的所得现金 即：dp[i - 1][1]
   - 第i天 卖出股票，所得现金就是按照今天股票价格卖出后所得现金即：`prices[i] + dp[i - 1][0]`

同样dp[i][1]取最大的，`**dp[i][1] = max(dp[i-1][1], prices[i] + dp[i-1][0]);**`<br />这样递推公式我们就分析完了

3. **dp数组如何初始化**

由递推公式 `dp[i][0] = max(dp[i - 1][0], -prices[i]);` 和 `dp[i][1] = max(dp[i - 1][1], prices[i] + dp[i - 1][0]);`可以看出, 其基础都是要从dp[0][0]和dp[0][1]推导出来。

   - 那么dp[0][0]表示第0天持有股票，此时的持有股票就一定是买入股票了，因为不可能有前一天推出来，所以**dp[0][0] -= prices[0];**
   - dp[0][1]表示第0天不持有股票，不持有股票那么现金就是0，所以**dp[0][1] = 0;**
4. **确定遍历顺序**

从递推公式可以看出dp[i]都是由dp[i - 1]推导出来的，那么一定是**从前向后遍历**。

5. **举例推导dp数组**

以示例1，输入：[7,1,5,3,6,4]为例，dp数组状态如下：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1691636055435-bc7660e6-feaa-4d8c-9531-700f661f0b0e.png#averageHue=%23f2f2f2&clientId=uc93f9bd2-7051-4&from=paste&height=473&id=u4766ee36&originHeight=631&originWidth=608&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=57079&status=done&style=shadow&taskId=u60458363-8751-4b03-a2b8-74cbdaa342c&title=&width=456)<br />dp[5][1]就是最终结果。为什么不是dp[5][0]呢？因为本题中不持有股票状态所得金钱一定比持有股票状态得到的多！
```java
// 解法1
public int maxProfit(int[] prices) {
    if(prices==null || prices.length==0) return 0;

    int length = prices.length;

    // dp[i][0]代表第i天持有股票的最大收益
    // dp[i][1]代表第i天不持有股票的最大收益
    int[][] dp = new int[length][2];
    int result = 0;
    dp[0][0] = -prices[0];  // 买了索引0处的股票
    dp[0][1] = 0;  // 没买股票

    for (int i = 1; i < length; i++) {
        dp[i][0] = Math.max(dp[i-1][0], -prices[i]);
        dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]+prices[i]); // 前一天也没股票, 和 , 前一天有股票今天卖出去
    }
    return dp[length-1][1];
}

时间复杂度：O(n)
空间复杂度：O(n)
```
```java
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int dp[][] = new int[2][2];
        
        dp[0][0] = - prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < len; i++){
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], - prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], prices[i] + dp[(i - 1) % 2][0]);
        }
        return dp[(len - 1) % 2][1];
    }
}
```

从递推公式可以看出，dp[i]只是依赖于dp[i - 1]的状态。
```java
dp[i][0] = max(dp[i - 1][0], -prices[i]);
dp[i][1] = max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
```
那么我们只需要记录 当 前天的dp状态 和 前一天的dp状态 就可以了，可以使用滚动数组来节省空间，代码如下：
```java
class Solution {
  public int maxProfit(int[] prices) {
    int[] dp = new int[2];
    // 记录一次交易，一次交易有买入卖出两种状态
    // 0代表持有，1代表卖出
    dp[0] = -prices[0];
    dp[1] = 0;
    // 可以参考斐波那契问题的优化方式
    // 我们从 i=1 开始遍历数组，一共有 prices.length 天，
    // 所以是 i<=prices.length
    for (int i = 1; i <= prices.length; i++) {
      // 前一天持有；或当天买入
      dp[0] = Math.max(dp[0], -prices[i - 1]);
      // 如果 dp[0] 被更新，那么 dp[1] 肯定会被更新为正数的 dp[1]
      // 而不是 dp[0]+prices[i-1]==0 的0，
      // 所以这里使用会改变的dp[0]也是可以的
      // 当然 dp[1] 初始值为 0 ，被更新成 0 也没影响
      // 前一天卖出；或当天卖出, 当天要卖出，得前一天持有才行
      dp[1] = Math.max(dp[1], dp[0] + prices[i - 1]);
    }
    return dp[1];
  }
}
时间复杂度：O(n)
空间复杂度：O(1)
```
-
<a name="LJZfX"></a>
# 122.买卖股票的最佳时机II  
:::info
视频讲解：[https://www.bilibili.com/video/BV1D24y1Q7Ls](https://www.bilibili.com/video/BV1D24y1Q7Ls)<br />[https://programmercarl.com/0122.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAII%EF%BC%88%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%EF%BC%89.html](https://programmercarl.com/0122.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAII%EF%BC%88%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%EF%BC%89.html)
:::
:::info
中等题   ([贪心](https://leetcode.cn/tag/greedy/), [数组](https://leetcode.cn/tag/array/), [动态规划](https://leetcode.cn/tag/dynamic-programming/))<br />本题我们在讲解贪心专题的时候就已经讲解过了 [贪心算法：买卖股票的最佳时机II](https://programmercarl.com/0122.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAII.html)，只不过没有深入讲解动态规划的解法，那么这次我们再好好分析一下动规的解法。
:::
本题和[121. 买卖股票的最佳时机](https://programmercarl.com/0121.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BA.html)的唯一区别是**本题股票可以买卖多次了**（注意只有一只股票，所以再次购买前要出售掉之前的股票）<br />在动规五部曲中，这个**区别主要是体现在递推公式**上，其他都和[121. 买卖股票的最佳时机](https://programmercarl.com/0121.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BA.html) 一样一样的。所以重点讲一讲递推公式。

这里重申一下dp数组的含义：

- dp[i][0] 表示第i天持有股票所得现金。
- dp[i][1] 表示第i天不持有股票所得最多现金

如果第i天持有股票即dp[i][0]， 那么可以由两个状态推出来

- 第i-1天 就持有股票，那么就保持现状，所得现金就是昨天持有股票的所得现金 即：`dp[i - 1][0]`
- 第i天 买入股票，所得现金就是昨天不持有股票的所得现金减去 今天的股票价格 即：`dp[i - 1][1] - prices[i]`

注意这里和[121. 买卖股票的最佳时机](https://programmercarl.com/0121.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BA.html)唯一不同的地方，就是推导dp[i][0]的时候，第i天买入股票的情况。在[121. 买卖股票的最佳时机](https://programmercarl.com/0121.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BA.html)中，因为股票全程只能买卖一次，所以如果买入股票，那么第i天持有股票即dp[i][0]一定就是 -prices[i]。而**本题，因为一只股票可以买卖多次，所以当第i天买入股票的时候，所持有的现金可能有之前买卖过的利润**。<br />那么第i天持有股票即dp[i][0]，如果是第i天买入股票，所得现金就是昨天不持有股票的所得现金 减去 今天的股票价格 即：`dp[i - 1][1] - prices[i]`。

再来看看如果第i天不持有股票即dp[i][1]的情况， 依然可以由两个状态推出来

- 第i-1天就不持有股票，那么就保持现状，所得现金就是昨天不持有股票的所得现金 即：dp[i - 1][1]
- 第i天卖出股票，所得现金就是按照今天股票价格卖出后所得现金即：prices[i] + dp[i - 1][0]

注意这里和[121. 买卖股票的最佳时机(opens new window)](https://programmercarl.com/0121.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BA.html)就是一样的逻辑，卖出股票收获利润（可能是负值）天经地义！<br />代码如下：（注意代码中的注释，标记了和121.买卖股票的最佳时机唯一不同的地方）
```java
// 动态规划
class Solution 
    // 实现1：二维数组存储
    // 可以将每天持有与否的情况分别用 dp[i][0] 和 dp[i][1] 来进行存储
    // 时间复杂度：O(n)，空间复杂度：O(n)
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];     // 创建二维数组存储状态
        // 初始状态 
        dp[0][0] = -prices[0];  // 第一天就持有           
        dp[0][1] = 0;  // 第一天不持有
        for (int i = 1; i < n; i++) {
            // 第 i 天，有股票, 第i-1天持有股票所得现金 和 i-1天没有股票i天买来股票所得现金是昨天不持有股票的所得现金 减去 今天的股票价格
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            // 第 i 天，没有股票, i-1天没有股票所持现金 和 i-1天有股票i天卖出后所得现金 取最大
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);    
        }
        return dp[n - 1][1];    // 卖出股票收益高于持有股票收益，因此取[1]
    }
}
```
```java
//DP using 2*2 Array (下方還有使用一維滾動數組的更優化版本)
class Solution {
    public int maxProfit(int[] prices) {
        int dp[][] = new int [2][2];
        //dp[i][0]: holding the stock
        //dp[i][1]: not holding the stock
        dp[0][0] = - prices[0];
        dp[0][1] = 0;

        for(int i = 1; i < prices.length; i++){
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] - prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 1) % 2][0] + prices[i]);
        }
        return dp[(prices.length - 1) % 2][1];
    }
}
```
```java
// 优化空间
class Solution {
    public int maxProfit(int[] prices) {
        int[] dp = new int[2];
        // 0表示持有，1表示卖出
        dp[0] = -prices[0];
        dp[1] = 0;
        for(int i = 1; i <= prices.length; i++){
            // 前一天持有; 既然不限制交易次数，那么再次买股票时，要加上之前的收益
            dp[0] = Math.max(dp[0], dp[1] - prices[i-1]);
            // 前一天卖出; 或当天卖出，当天卖出，得先持有
            dp[1] = Math.max(dp[1], dp[0] + prices[i-1]);
        }
        return dp[1];
    }
```
