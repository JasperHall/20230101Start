时间: 2023.6.16周五
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part01, 理论基础 , 509. 斐波那契数, 70. 爬楼梯, 746. 使用最小花费爬楼梯 
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 复习 746. 使用最小花费爬楼梯

<a name="XBsr0"></a>
# 复习

- [ ] 学习 968.监控二叉树


今天正式开始动态规划！
<a name="u3n5H"></a>
# 理论基础 
无论大家之前对动态规划学到什么程度，一定要先看 我讲的 动态规划理论基础。 <br />如果没做过动态规划的题目，看我讲的理论基础，会有感觉 是不是简单题想复杂了？ <br />其实并没有，我讲的理论基础内容，在动规章节所有题目都有运用，所以很重要！  <br />如果做过动态规划题目的录友，看我的理论基础 就会感同身受了。<br />[代码随想录](https://programmercarl.com/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)<br />视频：[从此再也不怕动态规划了，动态规划解题方法论大曝光 ！| 理论基础 |力扣刷题总结| 动态规划入门_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV13Q4y197Wg)
<a name="bhWJM"></a>
# 509. 斐波那契数 
:::info
很简单的动规入门题，但简单题使用来掌握方法论的，还是要有动规五部曲来分析。[代码随想录](https://programmercarl.com/0509.%E6%96%90%E6%B3%A2%E9%82%A3%E5%A5%91%E6%95%B0.html)<br />视频：[手把手带你入门动态规划 | 对应力扣（leetcode）题号：509.斐波那契数_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1f5411K7mo)
:::
:::info
(递归, 记忆化搜索, 数学, 动态规划)
:::
<a name="klvaX"></a>
## 动态规划
**动规五部曲**：<br />这里我们要用一个一维dp数组来保存递归的结果

1. 确定dp数组以及下标的含义
   1. dp[i]的定义为：第i个数的斐波那契数值是dp[i]
2. 确定递推公式
   1. 为什么这是一道非常简单的入门题目呢？因为题目已经把递推公式直接给我们了：状态转移方程 `dp[i] = dp[i - 1] + dp[i - 2];`
3. dp数组如何初始化
   1. 题目中把如何初始化也直接给我们了，如下：
```java
dp[0] = 0;
dp[1] = 1;
```

4. 确定遍历顺序
   1. 从递归公式`dp[i] = dp[i - 1] + dp[i - 2];`中可以看出，dp[i] 是依赖 dp[i - 1] 和 dp[i - 2]，那么遍历的顺序一定是从前到后遍历的
5. 举例推导dp数组
   1. 按照这个递推公式`dp[i] = dp[i - 1] + dp[i - 2]`，我们来推导一下，当N为10的时候，dp数组应该是如下的数列：0 1 1 2 3 5 8 13 21 34 55

如果代码写出来，发现结果不对，就把dp数组打印出来看看和我们推导的数列是不是一致的。
```java
//非压缩状态的版本
class Solution {
    public int fib(int n) {
        if (n <= 1) return n;             
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int index = 2; index <= n; index++){
            dp[index] = dp[index - 1] + dp[index - 2];
        }
        return dp[n];
    }
}
时间复杂度：O(n)
空间复杂度：O(n)

```
当然可以发现，我们只需要维护两个数值就可以了，不需要记录整个序列。如下
```java
class Solution {
    public int fib(int n) {
        if (n < 2) return n;
        int a = 0, b = 1, c = 0;
        for (int i = 1; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
时间复杂度：O(n)
空间复杂度：O(1)
```
<a name="VZZaK"></a>
## 补充
**波那契数列**（Fibonacci sequence），又称黄金分割数列，因意大利数学家莱昂纳多·斐波那契（Leonardo Fibonacci）1202年以兔子繁殖为例子而引入，故又称为“兔子数列”，指的是这样一个数列：0,1,1,2,3,5,8,13,21,34,55...., 这个数列从第3项开始，每一项都等于前两项之和。<br />在数学上，**斐波那契数列**以如下被以递推的方法定义：![](https://cdn.nlark.com/yuque/__latex/16515eb928710d72281c0d8c28f61c41.svg#card=math&code=F%280%29%3D0%2C%20F%281%29%3D1%2C%20F%28n%29%3DF%28n-1%29%2BF%28n-2%29&id=E7MyD)<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687278244630-d8474036-79a3-4923-8050-0beaef49e14c.png#averageHue=%2326292b&clientId=u79728c9a-a8c2-4&from=paste&height=300&id=u3b5aaaae&originHeight=351&originWidth=723&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=22321&status=done&style=shadow&taskId=ub47b002c-dbdc-4c71-bc81-140eea8d2e0&title=&width=617.9487406149427)
<a name="GcSY9"></a>
### 递归实现
```java
public class Solution {
    public int Fibonacci(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        return Fibonacci(n-1) + Fibonacci(n-2);
    }
}
```
**备忘录方法**与常见的递归形式是自顶向下的方式, 其控制结构与直接递归方法相同, 区别在于备忘录方法为每个子问题**建立一个记录项**。初始化时, 该记录项存入一个特殊的值，表示该子问题尚未解决。在递归求解过程中,每遇到一个子问题,首先查看其相应的记录项。若仍是初始值, 则表示该子问题第一次遇到, 于是进行求解, 并保存求解结果, 以备以后查看，若记录项中已不是初始值, 则表示该子问题已被计算, 只要从记录项中取出该子问题的解即可。算法如下。<br />备忘录也是从上往下, 只是去掉递归中重复计算的部分, 因为它使用一个容器来装已经计算出的值, 这里就多一个判断，

- 如果计算过该式子，就直接取结果，
- 如果没计算过才计算出来。
```java
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
```
<a name="KQCWp"></a>
### 迭代实现
迭代法的效率比递归法好很多, 迭代是自底向上的
```java
public class Solution {
    public int Fibonacci(int n) {
        int[] res = {0, 1};
        if(n < 2) {
            return res[n];
        }
        int first = 0;
        int second = 1;
        int fibn = 0;
        for(int i = 2; i <= n; i++) {
            fibn = first + second;
            first = second;
            second = fibn;
        }
        return fibn;
    }
}

```
 因为递归算法存在着大量的重复计算，在N趋近于较大值时，可能会造成内存溢出或超时的情况，又因为使用迭代算法的情况下同样可以实现计算斐波那契数列第N项的功能，所以在N值很大时我们优先使用迭代算法。<br />[斐波那契数列（Fibonacci sequence）及相关结论](https://zhuanlan.zhihu.com/p/588439015)
<a name="mjGmq"></a>
# 70. 爬楼梯   
:::info
本题大家先自己想一想， 之后会发现，和 斐波那契数 有点关系。[代码随想录](https://programmercarl.com/0070.%E7%88%AC%E6%A5%BC%E6%A2%AF.html)<br />视频：[带你学透动态规划-爬楼梯（对应力扣70.爬楼梯）| 动态规划经典入门题目_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV17h411h7UH)
:::
:::info
(记忆化搜索, 数学, 动态规划)
:::
本题大家如果没有接触过的话，会感觉比较难，多举几个例子，就可以发现其规律。<br />爬到第一层楼梯有一种方法，爬到二层楼梯有两种方法。那么第一层楼梯再跨两步就到第三层 ，第二层楼梯再跨一步就到第三层。所以到第三层楼梯的状态可以由第二层楼梯 和 到第一层楼梯状态推导出来，那么就可以想到**动态规划**了。<br />我们来分析一下，**动规五部曲**：<br />定义一个一维数组来记录不同楼层的状态

1. **确定dp数组以及下标的含义**

dp[i]： 爬到第 i 层楼梯，有`dp[i]`种方法

2. **确定递推公式**

如何可以推出dp[i]呢？<br />从dp[i]的定义可以看出，dp[i] 可以有两个方向推出来。<br />首先是`dp[i - 1]`，上`i-1`层楼梯，有`dp[i - 1]`种方法，那么再一步跳一个台阶不就是`dp[i]`了么。还有就是`dp[i - 2]`，上`i-2`层楼梯，有`dp[i - 2]`种方法，那么再一步跳两个台阶不就是`dp[i]`了么。那么`dp[i]`就是`dp[i - 1]`与`dp[i - 2]`之和！所以`dp[i] = dp[i - 1] + dp[i - 2]`。<br />在推导 dp[i] 的时候，一定要时刻想着 dp[i] 的定义，否则容易跑偏。这体现出确定dp数组以及下标的含义的重要性！

3. **dp数组如何初始化**

再回顾一下** dp[i] 的定义：爬到第i层楼梯，有dp[i]种方法**。<br />那么 i为0，dp[i]应该是多少呢，这个可以有很多解释，但基本都是直接奔着答案去解释的。例如强行安慰自己爬到第0层，也有一种方法，什么都不做也就是一种方法即：dp[0] = 1，相当于直接站在楼顶。但总有点牵强的成分。<br />那还这么理解呢：我就认为跑到第0层，方法就是0啊，一步只能走 一个台阶或者两个台阶，然而楼层是0，直接站楼顶上了，就是不用方法，dp[0]就应该是0.<br />其实这么争论下去没有意义，大部分解释说 dp[0]应该为1 的理由其实是因为 dp[0]=1 的话在递推的过程中 i从2 开始遍历本题就能过，然后就往结果上靠去解释dp[0] = 1。<br />从dp数组定义的角度上来说，dp[0] = 0 也能说得通。<br />需要注意的是：题目中说了n是一个正整数，题目根本就没说n有为0的情况。所以本题其实就不应该讨论dp[0]的初始化！<br />我相信 dp[1] = 1，dp[2] = 2，这个初始化大家应该都没有争议的。<br />所以我的原则是：不考虑dp[0]如何初始化，只初始化dp[1] = 1，dp[2] = 2，然后从i = 3开始递推，这样才符合dp[i]的定义。

4. 确定遍历顺序

从递推公式`dp[i] = dp[i - 1] + dp[i - 2];`中可以看出，遍历顺序一定是从前向后遍历的

5. 举例推导dp数组

举例当n为5的时候，dp table（dp数组）应该是这样的<br />![20210105202546299.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689077726861-6cd83cad-d8fc-4a8b-a5bd-f1e32c76dd42.png#averageHue=%23f5f5f5&clientId=u87977038-58a8-4&from=paste&height=289&id=ub9c520a2&originHeight=338&originWidth=654&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=22050&status=done&style=none&taskId=ud69b2c8b-26a3-4eef-8626-37d4cf00a4c&title=&width=558.9743794774171)<br />如果代码出问题了，就把dp table 打印出来，看看究竟是不是和自己推导的一样。<br />此时大家应该发现了，这不就是斐波那契数列么！唯一的区别是，没有讨论 dp[0] 应该是什么，因为dp[0]在本题没有意义！
```java
// 常规方式
public int climbStairs(int n) {
    int[] dp = new int[n + 1];  // 因为0是在题目中是不算数的, 但是在数组中还是要有0位置, 所以要长度+1
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
}
```
```java
// 用变量记录代替数组
class Solution {
    public int climbStairs(int n) {
        if(n <= 2) return n;
        int a = 1, b = 2, sum = 0;

        for(int i = 3; i <= n; i++){
            sum = a + b;  // f(i - 1) + f(i - 2)
            a = b;        // 记录f(i - 1)，即下一轮的f(i - 2)
            b = sum;      // 记录f(i)，即下一轮的f(i - 1)
        }
        return b;
    }
}
```
很多动规的题目其实都是当前状态依赖前两个，或者前三个状态，都可以做空间上的优化，但我个人认为面试中能写出版本一就够了哈，清晰明了，如果面试官要求进一步优化空间的话，我们再去优化。<br />因为版本一才能体现出动规的思想精髓，递推的状态变化
<a name="yhj1t"></a>
## 拓展
这道题目还可以继续深化，就是一步一个台阶，两个台阶，三个台阶，直到 m个台阶，有多少种方法爬到n阶楼顶。<br />这又有难度了，这其实是一个完全背包问题，但力扣上没有这种题目，所以后续我在讲解背包问题的时候，今天这道题还会从背包问题的角度上来再讲一遍。 如果想提前看一下，可以看这篇:[70.爬楼梯完全背包版本(opens new window)](https://programmercarl.com/0070.%E7%88%AC%E6%A5%BC%E6%A2%AF%E5%AE%8C%E5%85%A8%E8%83%8C%E5%8C%85%E7%89%88%E6%9C%AC.html)<br />这里我先给出我的实现代码：
```cpp
class Solution {
public:
    int climbStairs(int n) {
        vector<int> dp(n + 1, 0);
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) { // 把m换成2，就可以AC爬楼梯这道题
                if (i - j >= 0) dp[i] += dp[i - j];
            }
        }
        return dp[n];
    }
};
```
代码中m表示最多可以爬m个台阶。<br />**以上代码不能运行哈，我主要是为了体现只要把m换成2，粘过去，就可以AC爬楼梯这道题，不信你就粘一下试试。**<br />此时我就发现一个绝佳的大厂面试题，第一道题就是单纯的爬楼梯，然后看候选人的代码实现，如果把dp[0]的定义成1了，就可以发难了，为什么dp[0]一定要初始化为1，此时可能候选人就要强行给dp[0]应该是1找各种理由。那这就是一个考察点了，对dp[i]的定义理解的不深入。<br />然后可以继续发难，如果一步一个台阶，两个台阶，三个台阶，直到 m个台阶，有多少种方法爬到n阶楼顶。这道题目leetcode上并没有原题，绝对是考察候选人算法能力的绝佳好题。<br />这一连套问下来，候选人算法能力如何，面试官心里就有数了。<br />其实大厂面试最喜欢的问题就是这种简单题，然后慢慢变化，在小细节上考察候选人。
<a name="yXqCA"></a>
# 746. 使用最小花费爬楼梯 
:::info
这道题目力扣改了题目描述了，现在的题目描述清晰很多，相当于明确说 第一步是不用花费的。 <br />更改题目描述之后，相当于是 文章中 「拓展」的解法 [代码随想录](https://programmercarl.com/0746.%E4%BD%BF%E7%94%A8%E6%9C%80%E5%B0%8F%E8%8A%B1%E8%B4%B9%E7%88%AC%E6%A5%BC%E6%A2%AF.html)<br />视频讲解：[动态规划开更了！| LeetCode：746. 使用最小花费爬楼梯_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV16G411c7yZ)
:::
:::info
(数组, 动态规划)
:::
题目中说 “你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯” 也就是相当于 跳到 下标 0 或者 下标 1 是不花费体力的， 从 下标 0 下标1 开始跳就要花费体力了。

1. **确定dp数组以及下标的含义**

使用动态规划，就要有一个数组来记录状态，本题只需要一个一维数组 dp[i] 就可以了。<br />**dp[i]的定义：到达第i台阶所花费的最少体力为dp[i]**。对于dp数组的定义，大家一定要清晰！

2. **确定递推公式**

可以有两个途径得到dp[i]，一个是 dp[i-1] 一个是 dp[i-2]。<br />dp[i - 1] 跳到 dp[i] 需要花费 `dp[i - 1] + cost[i - 1`]。<br />dp[i - 2] 跳到 dp[i] 需要花费 `dp[i - 2] + cost[i - 2]`。<br />那么究竟是选从 dp[i - 1] 跳还是从 dp[i - 2] 跳呢？一定是选最小的，所以`dp[i] = min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2])`;

3. **dp数组如何初始化**

看一下递归公式，dp[i]由`dp[i - 1]，dp[i - 2]`推出，既然初始化所有的 dp[i] 是不可能的，那么只**初始化 dp[0] 和dp[1] 就够了**，其他的最终都是`dp[0]dp[1]`推出。<br />那么 dp[0] 应该是多少呢？ 根据dp数组的定义，到达第0台阶所花费的最小体力为 dp[0]，那么有同学可能想，那dp[0] 应该是 cost[0]，例如 cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1] 的话，dp[0] 就是 cost[0] 应该是1。这里就要说明本题力扣为什么改题意，而且修改题意之后 就清晰很多的原因了。新题目描述中明确说了 “你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。” **也就是说 到达 第 0 个台阶是不花费的，但从 第 0 个台阶 往上跳的话，需要花费 cost[0]**。<br />所以初始化 dp[0] = 0，dp[1] = 0;

4. **确定遍历顺序**

最后一步，递归公式有了，初始化有了，如何遍历呢？本题的遍历顺序其实比较简单，简单到很多同学都忽略了思考这一步直接就把代码写出来了。<br />因为是模拟台阶，而且dp[i]由dp[i-1]dp[i-2]推出，所以是**从前到后遍历cost数组就可以了。**<br />但是稍稍有点难度的动态规划，其遍历顺序并不容易确定下来。 例如：01背包，都知道两个for循环，一个for遍历物品 嵌套 一个for遍历背包容量，那么为什么不是一个for遍历背包容量嵌套一个for遍历物品呢？ 以及在使用一维dp数组的时候遍历背包容量为什么要倒序呢？<br />这些都与遍历顺序息息相关。当然背包问题后续「代码随想录」都会重点讲解的！

5. **举例推导dp数组**

拿示例2：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1] ，来模拟一下dp数组的状态变化，如下：<br />![20221026175104.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689082476187-bbc3880c-bbec-4c18-a703-786a4450adc0.png#averageHue=%23f1f0f0&clientId=u87977038-58a8-4&from=paste&height=369&id=u86f5b7e4&originHeight=432&originWidth=1292&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=59847&status=done&style=none&taskId=uf54f958e-51dd-4826-8d3f-861f090dd4c&title=&width=1104.2735447780165)<br />如果大家代码写出来有问题，就把dp数组打印出来，看看和如上推导的是不是一样的。
```java
// 方式一：第一步不支付费用
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 1];

        // 从下标为 0 或下标为 1 的台阶开始，因此支付费用为0
        dp[0] = 0;  // 默认第一步都是不花费体力的
        dp[1] = 0;

        // 计算到达每一层台阶的最小费用
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[len];
    }
}
时间复杂度：O(n)
空间复杂度：O(n)

----------------------------优化空间复杂度--------------------------
/**
* 优化空间复杂度
* @param cost
* @return
*/
public int minCostClimbingStairs2(int[] cost) {
    int dp0 = 0;
    int dp1 = 0;  // 注意 初始两个值都是 0 
    for (int i=2; i<=cost.length; i++){
        int dpi = Math.min(dp1+cost[i-1], dp0+cost[i-2]);
        dp0 = dp1;
        dp1 = dpi;
    }
    return dp1;
}
时间复杂度：O(n)
空间复杂度：O(1)
```
```java
// 方式二：第一步支付费用
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        //最后一步，如果是由倒数第二步爬，则最后一步的体力花费可以不用算
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }
}
```

