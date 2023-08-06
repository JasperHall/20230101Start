时间: 2023.6.23 周五
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part07, 70. 爬楼梯 （进阶）, 322. 零钱兑换, 279.完全平方数 
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [ ] <br />

详细布置 
<a name="U2GmM"></a>
# 70. 爬楼梯 （进阶） 
:::info
这道题目 爬楼梯之前我们做过，这次再用完全背包的思路来分析一遍 <br />[代码随想录](https://programmercarl.com/0070.%E7%88%AC%E6%A5%BC%E6%A2%AF%E5%AE%8C%E5%85%A8%E8%83%8C%E5%8C%85%E7%89%88%E6%9C%AC.html)
:::
之前讲这道题目的时候，因为还没有讲背包问题，所以就只是讲了一下爬楼梯最直接的**动规方法(斐波那契)**。这次终于讲到了背包问题，我选择带录友们再爬一次楼梯！<br />这道题目 我们在 [动态规划：爬楼梯](https://programmercarl.com/0070.%E7%88%AC%E6%A5%BC%E6%A2%AF.html) 中已经讲过一次了，原题其实是一道简单动规的题目。既然这么简单为什么还要讲呢，其实本题稍加改动就是一道面试好题。**改为：一步一个台阶，两个台阶，三个台阶，.......，直到 m个台阶。问有多少种不同的方法可以爬到楼顶呢？**<br />1阶，2阶，.... m阶就是物品，楼顶就是背包。每一阶可以重复使用，例如跳了1阶，还可以继续跳1阶。问跳到楼顶有几种方法其实就是问装满背包有几种方法。**此时大家应该发现这就是一个完全背包问题了！**和题目 [动态规划：377. 组合总和 Ⅳ](https://programmercarl.com/0377.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8C%E2%85%A3.html) 基本就是一道题了。

动规五部曲分析如下：

1. **确定dp数组以及下标的含义**

dp[i]：爬到有i个台阶的楼顶，有dp[i]种方法。

2. **确定递推公式**

在 [动态规划：494.目标和](https://programmercarl.com/0494.%E7%9B%AE%E6%A0%87%E5%92%8C.html)、 [动态规划：518.零钱兑换II](https://programmercarl.com/0518.%E9%9B%B6%E9%92%B1%E5%85%91%E6%8D%A2II.html)、[动态规划：377. 组合总和 Ⅳ](https://programmercarl.com/0377.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8C%E2%85%A3.html)中我们都讲过了，求装满背包有几种方法，<br />递推公式一般都是 `dp[i] += dp[i - nums[j]];`<br />本题呢，dp[i]有几种来源，dp[i - 1]，dp[i - 2]，dp[i - 3] 等等，即：dp[i - j], 那么递推公式为：`**dp[i] += dp[i - j]**`

3. **dp数组如何初始化**

既然递归公式是 `**dp[i] += dp[i - j]**`，那么dp[0] 一定为1，dp[0]是递归中一切数值的基础所在，如果dp[0]是0的话，其他数值都是0了。<br />下标非0的dp[i]初始化为0，因为dp[i]是靠dp[i-j]累计上来的，dp[i]本身为0这样才不会影响结果

4. **确定遍历顺序**

这是背包里求排列问题，即：**1、2 步 和 2、1 步都是上三个台阶，但是这两种方法不一样！**所以需将target放在外循环，将nums放在内循环。(背包外循环, 物品内循环)<br />每一步可以走多次，这是完全背包，内循环需要**从前向后遍历**。

5. **举例来推导dp数组**

介于本题和 [动态规划：377. 组合总和 Ⅳ](https://programmercarl.com/0377.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8C%E2%85%A3.html) 几乎是一样的，这里我就不再重复举例了。
```java
class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        int m = 2; //有兩個物品：itme1重量爲一，item2重量爲二
        dp[0] = 1;

        for (int i = 1; i <= n; i++) { // 遍历背包
            for (int j = 1; j <= m; j++) { //遍历物品
                if (i >= j)  //當前的背包容量 大於 物品重量的時候，我們才需要記錄當前的這個裝得方法（方法數+）
			dp[i] += dp[i - j];
            }
        }

        return dp[n];
    }
}
```
本题看起来是一道简单题目，稍稍进阶一下其实就是一个完全背包！<br />如果我来面试的话，我就会先给候选人出一个 本题原题，看其表现，如果顺利写出来，进而在要求每次可以爬[1 - m]个台阶应该怎么写。<br />顺便再考察一下两个for循环的嵌套顺序，为什么target放外面，nums放里面。这就能考察对背包问题本质的掌握程度，候选人是不是刷题背公式，一眼就看出来了。这么一连套下来，如果候选人都能答出来，相信任何一位面试官都是非常满意的。<br />本题代码不长，题目也很普通，但稍稍一进阶就可以考察完全背包，而且题目进阶的内容在leetcode上并没有原题，一定程度上就可以排除掉刷题党了，简直是面试题目的绝佳选择
<a name="j7nP9"></a>
# 322.零钱兑换  
:::info
如果求组合数就是外层for循环遍历物品，内层for遍历背包。<br />如果求排列数就是外层for遍历背包，内层for循环遍历物品。<br />这句话结合本题 大家要好好理解。<br />视频讲解：[动态规划之完全背包，装满背包最少的物品件数是多少？| LeetCode：322.零钱兑换_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV14K411R7yv)<br />[代码随想录](https://programmercarl.com/0322.%E9%9B%B6%E9%92%B1%E5%85%91%E6%8D%A2.html)
:::
题目中说每种硬币的数量是无限的，可以看出是典型的**完全背包问题**。<br />动规五部曲分析如下：

1. **确定dp数组以及下标的含义**

dp[j]：凑足 总额为j 所需钱币的**最少**个数为dp[j]

2. **确定递推公式**

凑足总额为`j - coins[i]`的最少个数为`dp[j - coins[i]]`，那么只需要加上一个钱币`coins[i]`即`dp[j-coins[i]] + 1`就是dp[j]（考虑coins[i]）, 所以dp[j] 要取所有 `dp[j - coins[i]] + 1` 中最小的。<br />递推公式：`dp[j] = min(dp[j-coins[i]]+1, dp[j]);` **(取最小的)**

3. **dp数组如何初始化**

首先凑足总金额为0所需钱币的个数一定是0，那么`dp[0] = 0;`<br />其他下标对应的数值呢？考虑到递推公式的特性，dp[j]必须初始化为一个最大的数，否则就会在`min(dp[j-coins[i]]+1, dp[j])`比较的过程中被初始值覆盖。所以下标非0的元素都是应该是最大值。<br />代码如下：
```java
int max = Integer.MAX_VALUE;
int[] dp = new int[amount + 1];
//初始化dp数组为最大值
for (int j = 0; j < dp.length; j++) {
    dp[j] = max;
}
//当金额为0时需要的硬币数目为0
dp[0] = 0;
```

4. **确定遍历顺序**

本题求钱币最小个数，那么**钱币有顺序和没有顺序都可以，都不影响钱币的最小个数**。所以本题并不强调集合是组合还是排列。

   - 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
   - 如果求排列数就是外层for遍历背包，内层for循环遍历物品。

所以本题的两个for循环的关系是：外层for循环遍历物品，内层for遍历背包或者外层for遍历背包，内层for循环遍历物品都是可以的！<br />那么**我采用**coins放在外循环，target在内循环的方式。(物品在外侧, 背包再内侧) 本题钱币数量可以**无限使用**，那么是**完全背包**。所以遍**历的内循环是正序**. <br />综上所述，遍历顺序为：coins（物品）放在外循环，target（背包）在内循环。且内循环正序。

5. 举例推导dp数组

以输入：coins = [1, 2, 5], amount = 5为例<br />![20210201111833906.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/32832913/1691295006202-4761ec12-ba7a-460c-be5a-a61795968687.jpeg#averageHue=%23f6f6f6&clientId=u07fe6c01-8255-4&from=paste&height=450&id=ubc4d46bb&originHeight=526&originWidth=826&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=75470&status=done&style=shadow&taskId=uda4f75e6-1d8f-4a2b-98ce-fe878427710&title=&width=705.9829318782056)<br />dp[amount]为最终结果。
```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[amount+1];

        // 初始化dp数组为最大值
        for (int j=0; j<dp.length; j++){
            dp[j] = max;
        }

        // 当金额为0时需要的硬币数目为0
        dp[0] = 0;

        for (int i=0; i<coins.length; i++){  // 遍历物品
            // 正序遍历：完全背包每个硬币可以选择多次
            for (int j=coins[i]; j<=amount; j++){
                // 只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                if (dp[j-coins[i]] != max){
                    // 选择硬币数目最小的情况
                    dp[j] = Math.min(dp[j], dp[j-coins[i]]+1);
                }
            }
        }

        return dp[amount] == max ? -1 : dp[amount];
    }
}

时间复杂度: O(n * amount)，其中 n 为 coins 的长度
空间复杂度: O(amount)
```
-
<a name="boLGb"></a>
# 279.完全平方数  
:::info
本题 和 322. 零钱兑换 基本是一样的，大家先自己尝试做一做 <br />视频讲解：[动态规划之完全背包，换汤不换药！| LeetCode：279.完全平方数_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV12P411T7Br)<br />[代码随想录](https://programmercarl.com/0279.%E5%AE%8C%E5%85%A8%E5%B9%B3%E6%96%B9%E6%95%B0.html)
:::
可能刚看这种题感觉没啥思路，又平方和的，又最小数的。<br />我来把题目翻译一下：完全平方数就是物品（可以无限件使用），凑个正整数n就是背包，问凑满这个背包最少有多少物品？<br />感受出来了没，这么浓厚的完全背包氛围，而且和题目 `322. 零钱兑换` 就是一样一样的！<br />动规五部曲分析如下：

1. **确定dp数组（dp table）以及下标的含义**

dp[j]：和为j的完全平方数的最少数量为dp[j]

2. **确定递推公式**

dp[j] 可以由`dp[j - i*i]`推出， `dp[j - i*i] + 1` 便可以凑成dp[j]。<br />此时我们要选择最小的dp[j]，所以递推公式：`dp[j] = **min**(dp[j - i*i] + 1, dp[j]);`

3. **dp数组如何初始化**

dp[0]表示 和为0的完全平方数的最小数量，那么dp[0]一定是0。<br />有同学问题，那0 * 0 也算是一种啊，为啥dp[0] 就是 0呢？看题目描述，找到若干个完全平方数（比如 1, 4, 9, 16, ...），题目描述中可没说要从0开始，dp[0]=0完全是为了递推公式。<br />非0下标的dp[j]应该是多少呢？从递归公式`dp[j] = min(dp[j - i * i] + 1, dp[j]);`中可以看出每次dp[j]都要选最小的，**所以非0下标的dp[j]一定要初始为最大值**，这样dp[j]在递推的时候才不会被初始值覆盖。

4. **确定遍历顺序**

我们知道这是完全背包，

   - 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
   - 如果求排列数就是外层for遍历背包，内层for循环遍历物品。

在`322. 零钱兑换`中我们就深入探讨了这个问题，本题也是一样的，是求最小数！<br />所以本题外层for遍历背包，内层for遍历物品，还是外层for遍历物品，内层for遍历背包，都是可以的！<br />我这里先给出外层遍历背包，内层遍历物品的代码：
```java
// 遍历背包
for (int j = 1; j <= n; j++) {
    // 遍历物品
    for (int i = 1; i * i <= j; i++) {
        dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
    }
}
```

5. **举例推导dp数组**

已输入n为5例，dp状态图如下：<br />![20210202112617341.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/32832913/1691307769265-4cf3dcfa-63a1-45d5-9dac-448e376d050e.jpeg#averageHue=%23f5f5f5&clientId=u07fe6c01-8255-4&from=paste&height=561&id=ubfc67506&originHeight=656&originWidth=1016&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=124144&status=done&style=shadow&taskId=u38f0a610-6ff5-4a69-b197-acd7df5d6a8&title=&width=868.3761002279139)<br />dp[0] = 0 dp[1] = min(dp[0] + 1) = 1 dp[2] = min(dp[1] + 1) = 2 dp[3] = min(dp[2] + 1) = 3 dp[4] = min(dp[3] + 1, dp[0] + 1) = 1 dp[5] = min(dp[4] + 1, dp[1] + 1) = 2<br />最后的dp[n]为最终结果。
```java
class Solution {
    // 版本二， 先遍历背包, 再遍历物品
    public int numSquares(int n) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        // 初始化
        for (int j = 0; j <= n; j++) {
            dp[j] = max;
        }
        // 当和为0时，组合的个数为0
        dp[0] = 0;
        // 遍历背包
        for (int j = 1; j <= n; j++) {
            // 遍历物品
            for (int i = 1; i * i <= j; i++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];
    }
}
时间复杂度: O(n * √n)
空间复杂度: O(n)
```
```java
class Solution {
    // 版本一，先遍历物品, 再遍历背包
    public int numSquares(int n) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        //初始化
        for (int j = 0; j <= n; j++) {
            dp[j] = max;
        }
    	//如果不想要寫for-loop填充數組的話，也可以用JAVA內建的Arrays.fill()函數。
    	//Arrays.fill(dp, Integer.MAX_VALUE);
	
        //当和为0时，组合的个数为0
        dp[0] = 0;
        // 遍历物品
        for (int i = 1; i * i <= n; i++) {
            // 遍历背包
            for (int j = i * i; j <= n; j++) {
                //if (dp[j - i * i] != max) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                //}
		//不需要這個if statement，因爲在完全平方數這一題不會有"湊不成"的狀況發生（ 一定可以用"1"來組成任何一個n），故comment掉這個if statement。
            }
        }
        return dp[n];
    }
}
时间复杂度: O(n * √n)
空间复杂度: O(n)
```
:::danger
Java填充数组的方法: `Arrays.fill(dp, Integer.MAX_VALUE);`
:::
-
