时间: 2023.6.22 周四
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part06, 完全背包, 518. 零钱兑换 II, 377. 组合总和 Ⅳ  

<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [ ] <br />

详细布置 <br />力扣上没有纯粹的完全背包的题目，所以大家看本篇了解一下 完全背包的理论 <br />后面的两道题目，都是完全背包的应用，做做感受一下 
<a name="em9Fn"></a>
# 完全背包 
:::info
视频讲解：[带你学透完全背包问题！ 和 01背包有什么差别？遍历顺序上有什么讲究？_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1uK411o7c9)<br />[代码随想录](https://programmercarl.com/%E8%83%8C%E5%8C%85%E9%97%AE%E9%A2%98%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80%E5%AE%8C%E5%85%A8%E8%83%8C%E5%8C%85.html)
:::
<a name="ErTBV"></a>
## 完全背包理论基础
完全背包: 有 N件物品 和 一个最多能背重量为W的背包。第i件物品的重量是weight[i]，得到的价值是value[i] 。每件物品都有无限个（也就是**物品可以放入背包多次**），求解将哪些物品装入背包里物品价值总和最大。<br />完全背包 和 01背包 问题唯一**不同的地方就是，每种物品有无限件**。<br />同样leetcode上没有纯完全背包问题，都是需要完全背包的各种应用，需要转化成完全背包问题，所以我这里还是以纯完全背包问题进行讲解理论和原理。<br />在下面的讲解中，我依然举这个例子：
:::info
背包最大重量为4。<br />物品为：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1691232996831-5c8a4c42-2577-4c0a-ae43-430814833f30.png#averageHue=%23f6f7f8&clientId=u1ae6c898-2a6c-4&from=paste&height=147&id=ud1abd393&originHeight=172&originWidth=333&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=6362&status=done&style=shadow&taskId=u8770828a-bf42-4ffc-869f-ab896327c00&title=&width=284.6153950550151)<br />每件商品都有无限个！<br />问背包能背的物品最大价值是多少？
:::
01背包和完全背包唯一不同就是体现在遍历顺序上，所以本文就**不去做动规五部曲了**，我们直接针对遍历顺序经行分析！<br />首先再回顾一下01背包的核心代码
```java
for(int i = 0; i < weight.size(); i++) { // 遍历物品
    for(int j = bagWeight; j >= weight[i]; j--) { // 遍历背包容量
        dp[j] = max(dp[j], dp[j-weight[i]] + value[i]);
    }
}
```
我们知道01背包内嵌的循环是**从大到小遍历**，为了保证每个物品仅被添加一次。<br />而**完全背包的物品是可以添加多次的，所以要从小到大去遍历**，即：
```java
// 先遍历物品，再遍历背包
for(int i = 0; i < weight.size(); i++) { // 遍历物品
    for(int j = weight[i]; j <= bagWeight ; j++) { // 遍历背包容量
        dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
    }
}
```
dp状态图如下：<br />![20210126104510106.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/32832913/1691233639297-dc7fa25f-730e-4d5b-8ea8-409f9f941c28.jpeg#averageHue=%23f3f3f3&clientId=u1ae6c898-2a6c-4&from=paste&height=473&id=uf90da6ab&originHeight=630&originWidth=970&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=103219&status=done&style=shadow&taskId=u46a94cfe-49c0-41d5-8a84-2f9da2947ff&title=&width=728)<br />相信很多同学看网上的文章，关于完全背包介绍基本就到为止了。<br />其实还有一个很重要的问题，为什么遍历物品在外层循环，遍历背包容量在内层循环？这个问题很多题解关于这里都是轻描淡写就略过了，大家都默认 遍历物品在外层，遍历背包容量在内层，好像本应该如此一样，那么为什么呢？<br />难道就不能遍历背包容量在外层，遍历物品在内层？<br />看过这两篇的话：

- [动态规划：关于01背包问题，你该了解这些！(opens new window)](https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-1.html)
- [动态规划：关于01背包问题，你该了解这些！（滚动数组）](https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-2.html)

就知道了，01背包中 **二维dp数组**的两个for遍历的先后循序是**可以颠倒**了，**一维dp数组**的两个for循环先后循序**一定是先遍历物品，再遍历背包容量**。<br />在**完全背包**中，对于一维dp数组来说，其实两个for循环嵌套顺序是无所谓的！<br />因为dp[j] 是根据 下标j 之前所对应的dp[j]计算出来的。 只要保证下标j之前的dp[j]都是经过计算的就可以了。

遍历**物品**在**外层**循环，遍历**背包容量**在**内层**循环，状态如图：<br />![20210126104529605.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/32832913/1691236285883-e3549582-239a-42e0-bbc0-c2de052d1622.jpeg#averageHue=%23f4f4f4&clientId=u1ae6c898-2a6c-4&from=paste&height=479&id=u580e0548&originHeight=638&originWidth=944&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=100047&status=done&style=shadow&taskId=u782dffa3-6968-46bf-850a-0c6b2e63a14&title=&width=708)<br />遍历**背包容量**在**外层**循环，遍历**物品**在**内层**循环，状态如图：<br />![20210729234011.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1691236396644-3009ce6f-5e17-446d-9265-e2356ac2e87d.png#averageHue=%23f4f4f4&clientId=u1ae6c898-2a6c-4&from=paste&height=486&id=uf5d2a41c&originHeight=648&originWidth=1008&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=64612&status=done&style=shadow&taskId=u02acba96-0a8d-4175-a6e1-a3de6ece2ab&title=&width=756)<br />看了这两个图，大家就会理解，完全背包中，两个for循环的先后循序，都不影响计算dp[j]所需要的值（这个值就是下标j之前所对应的dp[j]）。
```java
// 先遍历背包，再遍历物品
for(int j = 0; j <= bagWeight; j++) { // 遍历背包容量
    for(int i = 0; i < weight.length(); i++) { // 遍历物品
        if (j - weight[i] >= 0) dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
    }
    cout << endl;
}
```
完整代码如下
```java
//先遍历物品，再遍历背包
private static void testCompletePack(){
    int[] weight = {1, 3, 4};
    int[] value = {15, 20, 30};
    int bagWeight = 4;
    int[] dp = new int[bagWeight + 1];
    
    for (int i = 0; i < weight.length; i++){ // 遍历物品
        for (int j = weight[i]; j <= bagWeight; j++){ // 遍历背包容量
            dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
        }
    }
    for (int maxValue : dp){
        System.out.println(maxValue + "   ");
    }
}


//先遍历背包，再遍历物品
private static void testCompletePackAnotherWay(){
    int[] weight = {1, 3, 4};
    int[] value = {15, 20, 30};
    int bagWeight = 4;
    int[] dp = new int[bagWeight + 1];
    
    for (int i = 1; i <= bagWeight; i++){ // 遍历背包容量
        for (int j = 0; j < weight.length; j++){ // 遍历物品
            if (i - weight[j] >= 0){
                dp[i] = Math.max(dp[i], dp[i - weight[j]] + value[j]);
            }
        }
    }
    for (int maxValue : dp){
        System.out.println(maxValue + "   ");
    }
}
```
细心的同学可能发现，全文我说的都是**对于纯完全背包问题，其for循环的先后循环是可以颠倒的**！<br />但如果题目稍稍有点变化，就会体现在遍历顺序上。<br />如果问装满背包有几种方式的话？ 那么两个for循环的先后顺序就有很大区别了，而leetcode上的题目都是这种稍有变化的类型。<br />最后，又可以出一道面试题了，就是纯完全背包，要求先用二维dp数组实现，然后再用一维dp数组实现，最后再问，两个for循环的先后是否可以颠倒？为什么？ 这个简单的完全背包问题，估计就可以难住不少候选人了
<a name="oJYj1"></a>
# 518. 零钱兑换 II  
:::info
视频讲解：[动态规划之完全背包，装满背包有多少种方法？组合与排列有讲究！| LeetCode：518.零钱兑换II_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1KM411k75j)<br />[代码随想录](https://programmercarl.com/0518.%E9%9B%B6%E9%92%B1%E5%85%91%E6%8D%A2II.html)
:::
这是一道典型的背包问题，一看到钱币数量不限，就知道这是一个**完全背包**。<br />但本题和纯完全背包不一样，纯完全背包是凑成背包最大价值是多少，而本题是要求凑成总金额的物品组合个数！<br />注意题目描述中是凑成总金额的硬币组合数，为什么强调是组合数呢？<br />例如示例一：<br />5 = 2 + 2 + 1<br />5 = 2 + 1 + 2<br />这是一种组合，都是 2 2 1。<br />如果问的是排列数，那么上面就是两种排列了。<br />组合不强调元素之间的顺序，排列强调元素之间的顺序。 其实这一点我们在讲解回溯算法专题的时候就讲过了。<br />那我为什么要介绍这些呢，因为这和下文讲解遍历顺序息息相关!<br />回归本题，**动规五步曲**来分析如下：

1. **确定dp数组以及下标的含义**

dp[j]：凑成总金额j的货币组合数为dp[j]

2. **确定递推公式**

dp[j] 就是所有的`dp[j - coins[i]]`（考虑coins[i]的情况）相加。<br />所以递推公式：`dp[j] += dp[j - coins[i]];`<br />这个递推公式大家应该不陌生了，在讲解 01背包题目的时候在这篇`494. 目标和`中就讲解了，求装满背包有几种方法，公式都是：`dp[j] += dp[j - nums[i]];`

3. **dp数组如何初始化**

**首先dp[0]一定要为1**，**dp[0] = 1**是 递归公式的基础。如果dp[0] = 0 的话，后面所有推导出来的值都是0了。<br />那么 dp[0] = 1 有没有含义，其实既可以说 凑成总金额0的货币组合数为1，也可以说 凑成总金额0的货币组合数为0，好像都没有毛病。<br />但题目描述中，也没明确说 amount = 0 (总金额=0)的情况，结果应该是多少。<br />这里我认为题目描述还是要说明一下，因为后台测试数据是默认，amount = 0 的情况，组合数为1的。<br />**下标非0的dp[j]初始化为0**，这样累计加`dp[j - coins[i]]`的时候才不会影响真正的 dp[j]<br />dp[0]=1还说明了一种情况：如果正好选了coins[i]后，也就是j-coins[i] == 0的情况表示这个硬币刚好能选，此时dp[0]为1表示只选coins[i]存在这样的一种选法。

4. **确定遍历顺序**

本题中我们是外层for循环遍历物品（钱币），内层for遍历背包（金钱总额），还是外层for遍历背包（金钱总额），内层for循环遍历物品（钱币）呢？<br />在动态规划中讲解了完全背包的两个for循环的先后顺序都是可以的。**但本题就不行了！**<br />因为纯完全背包求得装满背包的最大价值是多少，和凑成总和的元素有没有顺序没关系，即：有顺序也行，没有顺序也行！而本题要求凑成总和的组合数，元素之间明确要求没有顺序。所以纯完全背包是能凑成总和就行，不用管怎么凑的。本题是求凑出来的方案个数，且每个方案个数是为组合数。<br />那么本题，两个for循环的先后顺序可就有说法了。<br />我们先来看 **外层for循环遍历物品（钱币），内层for遍历背包（金钱总额）**的情况。
```java
for (int i = 0; i < coins.size(); i++) { // 遍历物品  i<硬币个数
    for (int j = coins[i]; j <= amount; j++) { // 遍历背包容量 j<=目标总额
        dp[j] += dp[j - coins[i]];
    }
}
```
假设：coins[0] = 1，coins[1] = 5。<br />那么就是先把1加入计算，然后再把5加入计算，得到的方法数量只有{1, 5}这种情况。而不会出现{5, 1}的情况。<br />**所以这种遍历顺序中dp[j]里计算的是组合数！**<br />如果把两个for交换顺序，代码如下：
```java
for (int j = 0; j <= amount; j++) { // 遍历背包容量
    for (int i = 0; i < coins.size(); i++) { // 遍历物品
        if (j - coins[i] >= 0) dp[j] += dp[j - coins[i]];
    }
}
```
背包容量的每一个值，都是经过 1 和 5 的计算，包含了{1, 5} 和 {5, 1}两种情况。此时dp[j]里算出来的就是**排列数！**<br />**可能这里很多同学还不是很理解，建议动手把这两种方案的dp数组数值变化打印出来，对比看一看**！（实践出真知）

5. **举例推导dp数组**

输入: amount = 5, coins = [1, 2, 5] ，dp状态图如下：<br />![20210120181331461.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/32832913/1691239534608-f16fcd78-2e32-487c-bbb9-0a9253ded02c.jpeg#averageHue=%23f3f3f3&clientId=u1ae6c898-2a6c-4&from=paste&height=488&id=ufa9e3bb0&originHeight=650&originWidth=1058&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=122225&status=done&style=shadow&taskId=ub9d988fa-5762-448f-9964-ce355926bdb&title=&width=794)<br />最后红色框dp[amount]为最终结果。<br />分析完毕, 完整代码如下
```java
class Solution {
    public int change(int amount, int[] coins) {
        //递推表达式
        int[] dp = new int[amount + 1];
        //初始化dp数组，表示金额为0时只有一种情况，也就是什么都不装
        dp[0] = 1;
        
        for (int i = 0; i < coins.length; i++) {  // 一维数组时必须先遍历硬币(物品)
            for (int j = coins[i]; j <= amount; j++) {  // 遍历背包容量, 容量从硬币大小为起点省去判断是否能往里放
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
```
```java
// 二维dp数组版本，方便理解
class Solution {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];  // 注意这里初始化的长度
        // 只有一种硬币的情况
        for (int i = 0; i <= amount; i += coins[0]) {  // 第0种硬币, 填满第0种硬币的整数倍的背包的方案只有一种
            dp[0][i] = 1;
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                // 第i种硬币使用0~k次，求和
                for (int k = 0; k * coins[i] <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k * coins[i]];  // 注意对这一步求和的理解
                }
            }
        }
        return dp[coins.length - 1][amount];
    }
}
```
-
<a name="hKECB"></a>
# 377. 组合总和 Ⅳ  
:::info
视频讲解：[动态规划之完全背包，装满背包有几种方法？求排列数？| LeetCode：377.组合总和IV_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1V14y1n7B6)<br />[代码随想录](https://programmercarl.com/0377.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8C%E2%85%A3.html)
:::
本题题目描述说是求组合，但又说是可以元素相同顺序不同的组合算两个组合，其实就是求排列！<br />弄清什么是组合，什么是排列很重要。

- 组合不强调顺序，(1,5)和(5,1)是同一个组合。
- 排列强调顺序，(1,5)和(5,1)是两个不同的排列。

大家在代码随想录里学习回溯算法专题的时候，一定做过这两道题目[回溯算法：39.组合总和](https://programmercarl.com/0039.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8C.html)和[回溯算法：40.组合总和II](https://programmercarl.com/0040.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CII.html)会感觉这两题和本题很像！<br />但其本质是本题求的是排列总和，而且仅仅是求排列总和的个数，并不是把所有的排列都列出来。**如果本题要把排列都列出来的话，只能使用回溯算法爆搜。**

**动规五部曲**分析如下：

1. **确定dp数组以及下标的含义**

dp[i]: 凑成目标正整数为i的排列个数为dp[i]

2. **确定递推公式**

dp[i]（考虑nums[j]）可以由 dp[i - nums[j]]（不考虑nums[j]） 推导出来。<br />因为只要得到nums[j]，排列个数`dp[i - nums[j]]`，就是dp[i]的一部分。<br />上面的 494.目标和 and 518.零钱兑换 题目的 递推公式一般都是`dp[i] += dp[i - nums[j]];` 本题也是

3. **dp数组如何初始化**

因为递推公式dp[i] += dp[i - nums[j]]的缘故，dp[0]要初始化为1，这样递归其他dp[i]的时候才会有数值基础。<br />至于dp[0] = 1 有没有意义呢？其实没有意义，所以我也不去强行解释它的意义了，因为题目中也说了：给定目标值是正整数！ 所以dp[0] = 1是没有意义的，仅仅是为了推导递推公式。<br />至于非0下标的dp[i]应该初始为多少呢？初始化为0，**这样才不会影响dp[i]累加所有的dp[i - nums[j]]**。

4. **确定遍历顺序**

个数可以不限使用，说明这是一个**完全背包**。得到的集合是排列，说明需要**考虑元素之间的顺序**。<br />本题要求的是排列，那么这个for循环嵌套的顺序可以有说法了。

   - 如果**求组合数**就是外层for循环遍历物品，内层for遍历背包。
   - 如果**求排列数**就是外层for遍历背包，内层for循环遍历物品。

如果把遍历nums（物品）放在外循环，遍历target的作为内循环的话，举一个例子：计算dp[4]的时候，结果集只有 {1,3} 这样的集合，不会有{3,1}这样的集合，因为nums遍历放在外层，3只能出现在1后面！<br />**所以本题遍历顺序最终遍历顺序：target（背包）放在外循环，将nums（物品）放在内循环，内循环从前到后遍历**。

5. **举例来推导dp数组**

我们再来用示例中的例子推导一下：<br />![20230310000625.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1691247329005-23b93349-493a-4d43-8c46-20f096a46ce9.png#averageHue=%23f4f4f4&clientId=u1ae6c898-2a6c-4&from=paste&height=404&id=uce2f7252&originHeight=538&originWidth=978&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=137103&status=done&style=shadow&taskId=ud2f642cf-5101-48c3-901f-cc111dfa832&title=&width=734)<br />如果代码运行处的结果不是想要的结果，就把dp[i]都打出来，看看和我们推导的一不一样。<br />完整代码如下
```java
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];  // 注意这里初始化的长度
        dp[0] = 1; // 初始化为1, 若为0的话后面的没法根据前面的递推

        for (int i=0; i<=target; i++){  // 遍历背包
            for (int j=0; j<nums.length; j++){ // 遍历物品
                if (i >= nums[j]) dp[i] += dp[i-nums[j]];
            }
        }
        return dp[target];
    }
}
```
-


