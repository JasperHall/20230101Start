-时间: 2023.6.19 周一
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part03, 343. 整数拆分 , 96.不同的二叉搜索树 

<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 复习理解 343.整数拆分 和 96.不同的二叉搜索树

<a name="XBsr0"></a>
# 复习

- [ ] <br />

今天两题都挺有难度，建议大家思考一下没思路，直接看题解，第一次做，硬想很难想出来。
<a name="VEyNl"></a>
# 343. 整数拆分 
:::info
[代码随想录](https://programmercarl.com/0343.%E6%95%B4%E6%95%B0%E6%8B%86%E5%88%86.html)<br />[动态规划，本题关键在于理解递推公式！| LeetCode：343. 整数拆分_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1Mg411q7YJ)
:::
:::info
题目比较难(数学, 动态规划)
:::
看到这道题目，都会想拆成两个呢，还是三个呢，还是四个....我们来看一下如何使用动规来解决
<a name="N6854"></a>
## 动态规划
动规五部曲，分析如下：

1. **确定dp数组（dp table）以及下标的含义**

dp[i]：分拆数字i，可以得到的最大乘积为dp[i]。<br />dp[i] 的定义将贯彻整个解题过程，下面哪一步想不懂了，就想想dp[i]究竟表示的是啥！

2. **确定递推公式**

可以想 dp[i]最大乘积是怎么得到的呢？其实可以从 1 遍历 j，然后有两种渠道得到 dp[i].<br />一个是`j * (i - j)`直接相乘。<br />一个是`j * dp[i - j]`，相当于是拆分(i - j)，对这个拆分不理解的话，可以回想dp数组的定义。<br />那有同学问了，j怎么就不拆分呢？<br />j是从1开始遍历，拆分j的情况，在遍历j的过程中其实都计算过了。那么从1遍历j，比较`(i - j) * j`和`dp[i - j] * j `取最大的。递推公式：`dp[i] = max(dp[i], max((i - j) * j, dp[i - j] * j));`也可以这么理解，`j * (i - j)` 是单纯的把整数拆分为两个数相乘，而`j * dp[i - j]`是拆分成两个以及两个以上的个数相乘。<br />如果定义`dp[i - j] * dp[j]` 也是默认将一个数强制拆成4份以及4份以上了。所以递推公式：`dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j]));`<br />那么在取最大值的时候，为什么还要比较dp[i]呢？因为在递推公式推导的过程中，**每次计算dp[i]，取最大的**而已。

3. **dp的初始化**

不少同学应该疑惑，dp[0] dp[1]应该初始化多少呢？<br />有的题解里会给出 dp[0] = 1，dp[1] = 1 的初始化，但解释比较牵强，主要还是因为这么初始化可以把题目过了。<br />严格从dp[i]的定义来说，dp[0] dp[1] 就不应该初始化，也就是没有意义的数值。<br />拆分0和拆分1的最大乘积是多少？这是无解的。<br />**这里我只初始化dp[2] = 1，从dp[i]的定义来说，拆分数字2，得到的最大乘积是1，这个没有任何异议**！

4. **确定遍历顺序**

确定遍历顺序，先来看看递归公式：`dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j]));`<br />dp[i] 是依靠 dp[i - j] 的状态，所以遍历i 一定是从前向后遍历，先有 dp[i - j] 再有 dp[i]。<br />所以遍历顺序为：
```java
for (int i = 3; i <= n ; i++) {
    for (int j = 1; j < i - 1; j++) {
        dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j]));
    }
}
```
注意 枚举 j 的时候，是从1开始的。从0开始的话，那么让拆分一个数拆个0，求最大乘积就没有意义了。<br />j 的结束条件是` j < i - 1` ，其实` j < i `也是可以的，不过可以节省一步，例如让`j = i - 1`，的话，其实在` j = 1`的时候，这一步就已经拆出来了，重复计算，所以 `j < i - 1`<br />至于 i 是从3开始，这样`dp[i - j]`就是 dp[2] 正好可以通过我们初始化的数值求出来。更优化一步，可以这样：
```java
for (int i = 3; i <= n ; i++) {
    for (int j = 1; j <= i/2; j++) {
        dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j]));
    }
}
```
因为拆分一个数n 使之乘积最大，那么**一定是拆分成m个近似相同的子数相乘才是最大的**。<br />例如 6 拆成 3 * 3， 10 拆成 3 * 3 * 4。 100的话 也是拆成m个近似数组的子数 相乘才是最大的。<br />只不过我们不知道m究竟是多少而已，但可以明确的是m一定大于等于2，既然m大于等于2，也就是 最差也应该是拆成两个相同的 可能是最大值。<br />那么 j 遍历，只需要遍历到 n/2 就可以，后面就没有必要遍历了，一定不是最大值。<br />至于 “拆分一个数n 使之乘积最大，那么一定是拆分成m个近似相同的子数相乘才是最大的” 这个我就不去做数学证明了，感兴趣的同学，可以自己证明。

5. **举例推导dp数组**

举例当n为10 的时候，dp数组里的数值，如下：<br />![20210104173021581.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689152969312-2cb08310-f831-4cf6-b25d-eda54dd4580a.png#averageHue=%23f4f3f3&clientId=u66575110-c521-4&from=paste&height=297&id=u4974f111&originHeight=348&originWidth=908&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=29355&status=done&style=none&taskId=uc683696a-e8ea-4b96-9c47-1175c78e84a&title=&width=776.0684045343955)
```java
class Solution {
    public int integerBreak(int n) {
        // dp[i] 为正整数 i 拆分后的结果的最大乘积
        int[] dp = new int[n+1];
        dp[2] = 1;

        for (int i=3; i<=n; i++){
            // 这里的 j 其实最大值为 i-j,再大只不过是重复而已，
            for (int j=1; j<=i-j; j++){  // 还可以写成 for (int j=1; j<=i/2; j++)
                // 在本题中，我们分析 dp[0], dp[1]都是无意义的，
                // j 最大到 i-j,就不会用到 dp[0]与dp[1]
                dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j]));
                // j * (i - j) 是单纯的把整数 i 拆分为两个数 也就是 i,i-j ，再相乘
                // 而j * dp[i - j]是将 i 拆分成两个以及两个以上的个数,再相乘。
            }
        }

        return dp[n];
    }
}
时间复杂度：O(n^2)
空间复杂度：O(n)
```
<a name="Rqv8k"></a>
## 贪心
本题也可以用贪心，每次拆成n个3，如果剩下是4，则保留4，然后相乘，但是这个结论需要数学证明其合理性！<br />我没有证明，而是直接用了结论。感兴趣的同学可以自己再去研究研究数学证明哈。[力扣  贪心方法的数学推导](https://leetcode.cn/problems/integer-break/solutions/29098/343-zheng-shu-chai-fen-tan-xin-by-jyd/)
```java
/**
 * 贪心
 * @param n
 * @return
 */
public int integerBreak2(int n) {
    if (n==2) return 1;
    if (n==3) return 2;
    if (n==4) return 4;

    int res = 1;
    while (n>4){
        res *= 3;
        n -= 3;
    }

    res *= n;
    return res;
}
时间复杂度：O(n)
空间复杂度：O(1)
```
-
<a name="jniat"></a>
# 96.不同的二叉搜索树 
:::info
[代码随想录](https://programmercarl.com/0096.%E4%B8%8D%E5%90%8C%E7%9A%84%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.html)<br />[动态规划找到子状态之间的关系很重要！| LeetCode：96.不同的二叉搜索树_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1eK411o7QA)
:::
:::info
这题比较难     (树, 二叉搜索树, 数学, 动态规划, 二叉树)<br />什么是二叉搜索树? <br />二叉搜索树是有数值的了，**二叉搜索树是一个有序树。**

- 若它的左子树不空，则**左子树**上所有结点的值均**小于**它的根结点的值；
- 若它的右子树不空，则**右子树**上所有结点的值均**大于**它的根结点的值；
- 它的左、右子树也分别为**二叉排序树**

下面这两棵树都是搜索树<br />![20200806190304693.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1679824361856-5de6f36a-19f0-4bd5-92ee-b4ee79f7b6ba.png#averageHue=%23fcf5f5&clientId=uba910ae6-685a-4&from=paste&height=236&id=u841f761e&originHeight=276&originWidth=786&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=43860&status=done&style=shadow&taskId=ufc898827-af9c-4029-9bbb-a94ff1a2623&title=&width=671.7948964361617)
:::
这道题目描述很简短，但估计大部分同学看完都是懵懵的状态，这得怎么统计呢？<br />关于什么是二叉搜索树，我们之前在讲解二叉树专题的时候已经详细讲解过了，也可以看看这篇[二叉树：二叉搜索树登场！](https://programmercarl.com/0700.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E6%90%9C%E7%B4%A2.html)再回顾一波。<br />了解了二叉搜索树之后，我们应该先举几个例子，画画图，看看有没有什么规律，如图：<br />![20210107093106367.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689157605009-9bdf3a0f-5ec2-403e-b7a7-da67f80f6769.png#averageHue=%23f6f6f6&clientId=u66575110-c521-4&from=paste&height=381&id=u12fe2a79&originHeight=446&originWidth=876&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=54916&status=done&style=none&taskId=u8e4e3e32-6ccc-4f2b-a578-f2897a74efe&title=&width=748.7179761807604)<br />n为1的时候有一棵树，n为2有两棵树，这个是很直观的。<br />![20210107093129889.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689157887141-b8dcd5bc-8ac7-43e9-8852-272a5d59768e.png#averageHue=%23f7f7f7&clientId=u66575110-c521-4&from=paste&height=496&id=u6713d9d9&originHeight=580&originWidth=1740&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=115532&status=done&style=none&taskId=u74fb37a9-e9f1-4184-8b69-fed860303cc&title=&width=1487.1795417289077)<br />来看看n为3的时候，有哪几种情况。

- 当1为头结点的时候，其右子树有两个节点，看这两个节点的布局，是不是和 n 为2的时候两棵树的布局是一样的啊！（可能有同学问了，这布局不一样啊，节点数值都不一样。别忘了我们就是求不同树的数量，并不用把搜索树都列出来，所以不用关心其具体数值的差异）
- 当3为头结点的时候，其左子树有两个节点，看这两个节点的布局，是不是和n为2的时候两棵树的布局也是一样的啊！
- 当2为头结点的时候，其左右子树都只有一个节点，布局是不是和n为1的时候只有一棵树的布局也是一样的啊！

发现到这里，其实我们就找到了**重叠子问题**了，其实也就是发现可以通过 dp[1] 和 dp[2] 来推导出来 dp[3] 的某种方式。思考到这里，这道题目就有眉目了。

- dp[3]，就是 元素1为头结点搜索树的数量 + 元素2为头结点搜索树的数量 + 元素3为头结点搜索树的数量
   - 元素1为头结点搜索树的数量 = 右子树有2个元素的搜索树数量 * 左子树有0个元素的搜索树数量
   - 元素2为头结点搜索树的数量 = 右子树有1个元素的搜索树数量 * 左子树有1个元素的搜索树数量
   - 元素3为头结点搜索树的数量 = 右子树有0个元素的搜索树数量 * 左子树有2个元素的搜索树数量
      - 有2个元素的搜索树数量就是dp[2]。
      - 有1个元素的搜索树数量就是dp[1]。
      - 有0个元素的搜索树数量就是dp[0]。

所以 dp[3] = dp[2] * dp[0] + dp[1] * dp[1] + dp[0] * dp[2]<br />如图所示：<br />![20210107093226241.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689158187865-bff4454c-fdb9-48bc-b1a7-89e0ef7f8997.png#averageHue=%23f8f8f8&clientId=u66575110-c521-4&from=paste&height=949&id=u7a521da7&originHeight=1110&originWidth=2016&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=225795&status=done&style=none&taskId=ud2b6db98-6f15-44a5-b2d2-4fc51fe4ed4&title=&width=1723.0769862790103)<br />此时我们已经找到递推关系了，那么可以用动规五部曲再系统分析一遍。

1. **确定dp数组（dp table）以及下标的含义**

dp[i] ： 1 到 i 为节点组成的二叉搜索树的个数为dp[i]。<br />也可以理解是 i 个不同元素节点组成的二叉搜索树的个数为 dp[i] ，都是一样的。以下分析如果想不清楚，就来回想一下dp[i]的定义

2. **确定递推公式**

在上面的分析中，其实已经看出其递推关系，`dp[i] += dp[以j为头结点**左**子树节点数量] * dp[以j为头结点**右**子树节点数量]`<br />j 相当于是头结点的元素，从1遍历到 i 为止。<br />所以递推公式：`**dp[i] += dp[j-1] * dp[i-j];**`** ，j-1 为 j 为头结点左子树节点数量(减去一个头结点)，i-j 为以j为头结点右子树节点数量**

3. **dp数组如何初始化**

初始化，只需要初始化dp[0]就可以了，推导的基础，都是dp[0]。<br />那么dp[0]应该是多少呢？从定义上来讲，空节点也是一棵二叉树，也是一棵二叉搜索树，这是可以说得通的。<br />从递归公式上来讲，`dp[以j为头结点左子树节点数量] * dp[以j为头结点右子树节点数量]` 中以j为头结点左子树节点数量为0，也需要`dp[以j为头结点左子树节点数量] = 1`， 否则乘法的结果就都变成0了。所以**初始化dp[0] = 1**

4. **确定遍历顺序**

首先一定是遍历节点数，从递归公式：`dp[i] += dp[j - 1] * dp[i - j]`可以看出，节点数为 i 的状态是依靠 i之前节点数的状态。<br />那么遍历 i 里面每一个数作为头结点的状态，用 j 来遍历。代码如下：
```java
for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= i; j++) {
        dp[i] += dp[j - 1] * dp[i - j];
    }
}
```

5. **举例推导dp数组**

n为5时候的dp数组状态如图：<br />![20210107093253987.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689158282233-fbe65c58-8333-4ee9-9022-2fcc65447f7e.png#averageHue=%23f6f6f6&clientId=u66575110-c521-4&from=paste&height=368&id=ud0508aec&originHeight=430&originWidth=772&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=25877&status=done&style=none&taskId=u5d08d7d4-d1f7-4373-b791-e3807091ca2&title=&width=659.8290840314464)<br />当然如果自己画图举例的话，基本举例到n为3就可以了，n为4的时候，画图已经比较麻烦了。<br />我这里列到了n为5的情况，是为了方便大家 debug代码的时候，把dp数组打出来，看看哪里有问题。
```java
class Solution {
    public int numTrees(int n) {
        //初始化 dp 数组
        int[] dp = new int[n + 1];
        //初始化0个节点和1个节点的情况
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                //对于第i个节点，需要考虑1作为根节点直到i作为根节点的情况，所以需要累加
                //一共i个节点，对于根节点j时,左子树的节点个数为j-1，右子树的节点个数为i-j
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
```
这道题目虽然在力扣上标记是中等难度，但可以算是困难了！<br />首先这道题想到用动规的方法来解决，就不太好想，**需要举例，画图，分析，才能找到递推的关系**。<br />然后难点就是**确定递推公式**了，如果把递推公式想清楚了，遍历顺序和初始化，就是自然而然的事情了。<br />可以看出我依然还是用动规五部曲来进行分析，会把题目的方方面面都覆盖到！而且具体这五部分析是我自己平时总结的经验，找不出来第二个的，可能过一阵子 其他题解也会有动规五部曲了，哈哈。<br />当时我在用动规五部曲讲解斐波那契的时候，一些录友和我反应，感觉讲复杂了。<br />其实当时我一直强调简单题是用来练习方法论的，并不能因为简单我就代码一甩，简单解释一下就完事了。<br />可能当时一些同学不理解，现在大家应该感受方法论的重要性了，加油

