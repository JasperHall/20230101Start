时间: 2023.6.17周六
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part02, 62.不同路径 , 63. 不同路径 II 

<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] day39两道题都再看看

<a name="XBsr0"></a>
# 复习

- [ ] 复习 746. 使用最小花费爬楼梯



:::info
今天开始逐渐有 dp的感觉了，题目不多，就两个 不同路径，可以好好研究一下
:::
<a name="qrxnd"></a>
# 62.不同路径 
:::info
本题大家掌握动态规划的方法就可以。 数论方法 有点非主流，很难想到。 [代码随想录](https://programmercarl.com/0062.%E4%B8%8D%E5%90%8C%E8%B7%AF%E5%BE%84.html)<br />视频讲解：[动态规划中如何初始化很重要！| LeetCode：62.不同路径_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1ve4y1x7Eu)
:::
:::info
本题大家掌握动态规划的方法就可以。 数论方法 有点非主流，很难想到(数学, 动态规划, 组合数学)
:::
<a name="MiVih"></a>
## 深搜
这道题目，刚一看最直观的想法就是用图论里的深搜，来枚举出来有多少种路径。<br />注意题目中说机器人每次只能向下或者向右移动一步，那么其实机器人走过的路径可以抽象为一棵二叉树，而叶子节点就是终点！<br />如图举例：<br />![20201209113602700.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689086131581-00319399-2f8f-404c-a812-bc39d5e5433c.png#averageHue=%23f6f6f6&clientId=u87977038-58a8-4&from=paste&height=521&id=uc5a36ba0&originHeight=610&originWidth=1246&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=83043&status=done&style=none&taskId=u29972f2a-f3e9-4cc5-906d-d6103f8cef9&title=&width=1064.957304019666)<br />此时问题就可以转化为求二叉树叶子节点的个数，代码如下：
```cpp
class Solution {
private:
    int dfs(int i, int j, int m, int n) {
        if (i > m || j > n) return 0; // 越界了
        if (i == m && j == n) return 1; // 找到一种方法，相当于找到了叶子节点
        return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
    }
public:
    int uniquePaths(int m, int n) {
        return dfs(1, 1, m, n);
    }
};
```
**大家如果提交了代码就会发现超时了！**<br />来分析一下时间复杂度，这个深搜的算法，其实就是要遍历整个二叉树。<br />这棵树的深度其实就是`m+n-1`（深度按从1开始计算）。<br />那二叉树的节点个数就是 ![](https://cdn.nlark.com/yuque/__latex/aded4c899aa88c661f9aac5a02e9d224.svg#card=math&code=2%5E%7Bm%2Bn-2%7D-1&id=xKIom)。可以理解深搜的算法就是遍历了整个满二叉树（其实没有遍历整个满二叉树，只是近似而已）<br />所以上面深搜代码的时间复杂度为![](https://cdn.nlark.com/yuque/__latex/8edef0a4cd8afb4f266f830406d9b7e7.svg#card=math&code=O%282%5E%7Bm%2Bn-1%7D-1%29&id=mLZtp)，可以看出，这是指数级别的时间复杂度，是非常大的
<a name="EODLS"></a>
## 动态规划
机器人从 (0 , 0) 位置出发，到 (m - 1, n - 1) 终点。<br />按照**动规五部曲**来分析：

1. **确定dp数组（dp table）以及下标的含义**

dp[i][j] ：表示从（0 ，0）出发，到 (i, j)  有 dp[i][j] 条不同的路径。

2. **确定递推公式**

想要求dp[i][j]，只能有两个方向来推导出来，即`dp[i - 1][j]`和 `dp[i][j - 1]`。<br />此时在回顾一下 `dp[i - 1][j]` 表示啥，是从 (0, 0) 的位置到 (i - 1, j) 有几条路径，dp[i][j - 1] 同理。<br />那么很自然，`dp[i][j] = dp[i - 1][j] + dp[i][j - 1]`，因为 dp[i][j] 只有这两个方向过来。

3. **dp数组的初始化**

如何初始化呢，首先 dp[i][0] 一定都是1，因为从 (0, 0) 的位置到 (i, 0) 的路径只有一条，那么 dp[0][j] 也同理。所以初始化代码为：
```java
for (int i = 0; i < m; i++) dp[i][0] = 1;
for (int j = 0; j < n; j++) dp[0][j] = 1;
```

4. **确定遍历顺序**

这里要看一下递推公式`dp[i][j] = dp[i - 1][j] + dp[i][j - 1]`，dp[i][j] 都是从其 **上方 和 左方** 推导而来，那么从左到右一层一层遍历就可以了。<br />这样就可以保证推导 dp[i][j] 的时候，`dp[i - 1][j]` 和 `dp[i][j - 1`]一定是有数值的。

5. **举例推导dp数组**

如图所示：<br />![20201209113631392.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689086942780-2a7b84ef-9683-481e-aa28-a8efafcc2313.png#averageHue=%23f1f0f0&clientId=u87977038-58a8-4&from=paste&height=421&id=u9a5cbe89&originHeight=492&originWidth=846&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=39910&status=done&style=none&taskId=u0d7033f0-6276-4545-9369-e498645933d&title=&width=723.0769495992275)<br />以上动规五部曲分析完毕
```java
  /**
 * 1. 确定dp数组下标含义 dp[i][j] 到每一个坐标可能的路径种类
 * 2. 递推公式 dp[i][j] = dp[i-1][j] dp[i][j-1]
 * 3. 初始化 dp[i][0]=1 dp[0][i]=1 初始化横竖就可
 * 4. 遍历顺序 一行一行遍历
 * 5. 推导结果 。。。。。。。。
 *
 * @param m
 * @param n
 * @return
 */
public static int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    //初始化
    for (int i = 0; i < m; i++) {
        dp[i][0] = 1;
    }
    for (int i = 0; i < n; i++) {
        dp[0][i] = 1;
    }

    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = dp[i-1][j]+dp[i][j-1];
        }
    }
    return dp[m-1][n-1];  // 注意这里最终坐标要长度-1  
}
时间复杂度：O(m × n)
空间复杂度：O(m × n)
```
其实用一个一维数组（也可以理解是滚动数组）就可以了，但是不利于理解，可以优化点空间，建议先理解了二维，在理解一维, 代码如下：
```java
/**
 * 一维数组解法
 * @param m
 * @param n
 * @return
 */
public int uniquePaths2(int m, int n) {
    int[] dp = new int[n];  // 这里长度为n

    for (int i=0; i<n; i++) dp[i] = 1;

    for (int j=1; j<m; j++){  // 外层循环为 <m
        for (int i=1; i<n; i++){  // 内层循环为 <n
            dp[i] += dp[i-1];
        }
    }
    return dp[n-1];
} 
时间复杂度：O(m × n)
空间复杂度：O(n)   相比于二维数组, 优化了空间
```
![9c34cc7cac66150ced72d748a10e6a3.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689089512719-ea2e05da-cf24-4794-ab2b-28e48ab09ae6.png#averageHue=%23e9e8e4&clientId=u87977038-58a8-4&from=paste&height=1223&id=ubc7c6ae5&originHeight=1431&originWidth=2371&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=272219&status=done&style=none&taskId=ua7261255-826c-47e0-9090-536b55bd9b6&title=&width=2026.4958008271494)
<a name="tXM3L"></a>
## 数论方法
在这个图中，可以看出一共m，n的话，无论怎么走，走到终点都需要 m + n - 2 步。<br />![20201209113602700-20230310120944078.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689088791693-57d3721b-923d-47d5-a5a8-5d483fe2c20e.png#averageHue=%23f6f6f6&clientId=u87977038-58a8-4&from=paste&height=521&id=uc95986d6&originHeight=610&originWidth=1246&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=83043&status=done&style=none&taskId=u7e5d04eb-d05b-4b82-a166-6045d16cdd1&title=&width=1064.957304019666)<br />在这`m + n - 2`步中，一定有`m - 1`步是要向下走的，不用管什么时候向下走。<br />那么有几种走法呢？ 可以转化为，给你`m + n - 2`个不同的数，随便取`m - 1`个数，有几种取法。<br />那么这就是一个组合问题了。那么答案，如图所示：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689089644375-546e4068-9bae-44c0-a931-f0f95d438bbb.png#averageHue=%23e7e7e7&clientId=u87977038-58a8-4&from=paste&height=200&id=ue4d4d92f&originHeight=234&originWidth=394&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=14761&status=done&style=none&taskId=u91ec79d6-d0e6-431c-9a8e-2a4cd9d5a7a&title=&width=336.75214910413195)<br />**求组合的时候，要防止两个int相乘溢出**！ 所以不能把算式的分子都算出来，分母都算出来再做除法。<br />例如如下代码是不行的。
```java
class Solution {
public:
    int uniquePaths(int m, int n) {
        int numerator = 1, denominator = 1;
        int count = m - 1;
        int t = m + n - 2;
        while (count--) numerator *= (t--); // 计算分子，此时分子就会溢出
        for (int i = 1; i <= m - 1; i++) denominator *= i; // 计算分母
        return numerator / denominator;
    }
};
```
需要在计算分子的时候，不断除以分母，代码如下：
```java
class Solution {
public:
    int uniquePaths(int m, int n) {
        long long numerator = 1; // 分子
        int denominator = m - 1; // 分母
        int count = m - 1;
        int t = m + n - 2;
        while (count--) {
            numerator *= (t--);
            while (denominator != 0 && numerator % denominator == 0) {
                numerator /= denominator;
                denominator--;
            }
        }
        return numerator;
    }
};
时间复杂度：O(m)
空间复杂度：O(1)
```
计算组合问题的代码还是有难度的，**特别是处理溢出的情况**
<a name="Nb3Iu"></a>
## 总结
本文分别给出了深搜，动规，数论三种方法。<br />深搜当然是超时了，顺便分析了一下使用深搜的时间复杂度，就可以看出为什么超时了。<br />然后在给出动规的方法，依然是使用动规五部曲，这次我们就要考虑如何正确的初始化了，初始化和遍历顺序其实也很重要！
<a name="Al19o"></a>
# 63. 不同路径 II 
:::info
[代码随想录](https://programmercarl.com/0063.%E4%B8%8D%E5%90%8C%E8%B7%AF%E5%BE%84II.html)<br />视频讲解：[动态规划，这次遇到障碍了| LeetCode：63. 不同路径 II_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1Ld4y1k7c6)
:::
:::info
(数组, 动态规划, 矩阵)
:::
这道题相对于[62.不同路径](https://programmercarl.com/0062.%E4%B8%8D%E5%90%8C%E8%B7%AF%E5%BE%84.html)就是有了障碍。第一次接触这种题目的同学可能会有点懵，这有障碍了，应该怎么算呢？[62.不同路径](https://programmercarl.com/0062.%E4%B8%8D%E5%90%8C%E8%B7%AF%E5%BE%84.html)中我们已经详细分析了没有障碍的情况，有障碍的话，其实就是标记对应的dp table（dp数组）保持初始值(0)就可以了。<br />动规五部曲：

1. **确定dp数组（dp table）以及下标的含义**

dp[i][j] ：表示从（0 ，0）出发，到(i, j) 有dp[i][j]条不同的路径。

2. **确定递推公式**

递推公式和 62.不同路径一样，`dp[i][j] = dp[i - 1][j] + dp[i][j - 1]`。<br />但这里需要注意一点，因为有了障碍，(i, j)如果就是障碍的话应该就保持初始状态（初始状态为0）。所以代码为：
```java
if (obstacleGrid[i][j] == 0) { // 当(i, j)没有障碍的时候，再推导dp[i][j]
    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
}
```

3. **dp数组如何初始化**

在62.不同路径中我们给出如下的初始化：
```java
for (int i = 0; i < m; i++) dp[i][0] = 1;
for (int j = 0; j < n; j++) dp[0][j] = 1;
```
因为从(0, 0)的位置到(i, 0)的路径只有一条，所以dp[i][0]一定为1，dp[0][j]也同理。但如果 (i, 0) 这条边有了障碍之后，障碍之后（包括障碍）都是走不到的位置了，所以障碍之后的dp[i][0]应该还是初始值0。如图：<br />![20210104114513928.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689129043569-5b477a0d-02c5-4992-a27f-8346cd1c708a.png#averageHue=%23f3f3f3&clientId=u66575110-c521-4&from=paste&height=282&id=u93c0b86c&originHeight=330&originWidth=802&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=25356&status=done&style=none&taskId=u8923bb27-4f6e-4407-b707-54fcf3b2505&title=&width=685.4701106129793)<br />下标(0, j)的初始化情况同理。所以本题初始化代码为：
```java
for (int i = 0; i < m && obstacleGrid[i][0]==0 ; i++) dp[i][0] = 1;
for (int j = 0; j < n && obstacleGrid[0][j]==0; j++) dp[0][j] = 1;
```
注意代码里for循环的终止条件，一旦遇到`obstacleGrid[i][0] == 1`的情况就停止dp[i][0]的赋值1的操作，dp[0][j]同理

4. **确定遍历顺序**

从递归公式`dp[i][j] = dp[i - 1][j] + dp[i][j - 1]`中可以看出，一定是从左到右一层一层遍历，这样保证推导dp[i][j]的时候，`dp[i - 1][j]`和`dp[i][j - 1]`一定是有数值。代码如下：
```java
for (int i = 1; i < m; i++) {
    for (int j = 1; j < n; j++) {
        if (obstacleGrid[i][j] == 1) continue;
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    }
}
```

5. **举例推导dp数组**

拿示例1来举例如题：<br />![20210104114548983.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689129218040-670b5d6a-7504-492a-9dfc-7bf54cfb004f.png#averageHue=%23f3edeb&clientId=u66575110-c521-4&from=paste&height=421&id=ue88fe033&originHeight=492&originWidth=866&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=123687&status=done&style=none&taskId=u49f7ea64-3ab7-4b61-ae79-dfa21cbe692&title=&width=740.1709673202495)<br />对应的dp table 如图：<br />![20210104114610256.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689129222718-9829ca6a-5550-48c4-999b-d328bf806ab6.png#averageHue=%23f2eded&clientId=u66575110-c521-4&from=paste&height=332&id=udb14a646&originHeight=388&originWidth=428&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=16993&status=done&style=none&taskId=u277c877f-60d5-4783-af4a-c0facef2f28&title=&width=365.8119792298692)<br />如果这个图看不懂，建议再理解一下递归公式，然后照着文章中说的遍历顺序，自己推导一下！
```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];

        //如果在起点或终点出现了障碍，直接返回0
        /*if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) {
            return 0;
        }*/

        for (int i = 0; i < m && obstacleGrid[i][0]==0 ; i++) dp[i][0] = 1;
        for (int j = 0; j < n && obstacleGrid[0][j]==0; j++) dp[0][j] = 1;

        for (int i=1; i<m; i++){
            for (int j=1; j<n; j++){
                if (obstacleGrid[i][j] == 1) continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m-1][n-1];  // 注意这里最终坐标要长度-1
    }
}
时间复杂度：O(n × m)，n、m 分别为obstacleGrid 长度和宽度
空间复杂度：O(n × m)
```
下面进行空间优化
```java
// 空间优化版本
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];

        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j != 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }
}
时间复杂度：O(n × m)，n、m 分别为obstacleGrid 长度和宽度
空间复杂度：O(m)
```
-
