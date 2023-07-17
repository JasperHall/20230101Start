时间: 2023.6.20 周二
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part04, 01背包问题，你该了解这些!, 01背包问题，你该了解这些！ 滚动数组  , 416. 分割等和子集 
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [ ] 复习理解 343.整数拆分 和 96.不同的二叉搜索树


正式开始背包问题，背包问题还是挺难的，虽然大家可能看了很多背包问题模板代码，感觉挺简单，但基本理解的都不够深入。<br />如果是直接从来没听过背包问题，可以先看文字讲解慢慢了解 这是干什么的。<br />如果做过背包类问题，可以先看视频，很多内容，是自己平时没有考虑到位的。<br />背包问题，力扣上没有原题，大家先了解理论，今天就安排一道具体题目。<br />**详细布置**
<a name="PMIJX"></a>
# 01背包问题 二维
:::info
[https://programmercarl.com/背包理论基础01背包-1.html](https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-1.html)<br />视频讲解：[https://www.bilibili.com/video/BV1cg411g7Y6](https://www.bilibili.com/video/BV1cg411g7Y6)
:::
<a name="Ct0zt"></a>
## 二维dp数组01背包(例题)
依然动规五部曲分析一波。

1. **确定dp数组以及下标的含义**

对于背包问题，有一种写法， 是使用二维数组，即dp[i][j] 表示从下标为 [0-i] 的物品里任意取，放进容量为 j 的背包，价值总和最大是多少。<br />只看这个二维数组的定义，大家一定会有点懵，看下面这个图：<br />![20210110103003361.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689511230109-d5cfdc37-7358-482e-b5b4-21871d01b0f1.png#averageHue=%23f4f4f4&clientId=u86ff8cf5-b82f-4&from=paste&height=371&id=u7ff21351&originHeight=494&originWidth=912&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=38217&status=done&style=shadow&taskId=ubc72b14c-cf05-4a67-80fa-0382ead29ba&title=&width=684)<br />要时刻记着这个dp数组的含义，下面的一些步骤都围绕这dp数组的含义进行的，如果哪里看懵了，就来回顾一下 i 代表什么，j 又代表什么。

2. **确定递推公式**

再回顾一下dp[i][j]的含义：从下标为 [0-i] 的物品里任意取，放进容量为 j 的背包，价值总和最大是多少。<br />那么可以有两个方向推出来dp[i][j]，

- **不放物品i**：由 `dp[i - 1][j]` 推出，即背包容量为 j，里面不放物品 i 的最大价值，此时 dp[i][j] 就是 dp[i - 1][j]。(其实就是当物品i的重量大于背包j的重量时，物品i无法放进背包中，所以背包内的价值依然和前面相同。)
- **放物品i**：由`dp[i-1][j-weight[i]]`推出, `dp[i-1][j-weight[i]]` 为背包容量为`j - weight[i]`的时候不放物品 i 的最大价值，那么`dp[i-1][j-weight[i]]+value[i] `（物品i的价值），就是背包放物品i得到的最大价值

所以递归公式： `dp[i][j] = max(dp[i-1][j],  dp[i-1][j-weight[i]]+value[i]);`(不放i的价值 和 放i的价值 取较大的)

3. **dp数组如何初始化**

关于初始化，一定要和dp数组的定义吻合，否则到递推公式的时候就会越来越乱。<br />首先从dp[i][j]的定义出发，如果背包容量 j 为 0 的话，即 dp[i][0]，无论是选取哪些物品，背包价值总和一定为0。如图：<br />![2021011010304192.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689511600409-54c2fc65-c03b-47a9-a229-0812d2d6603a.png#averageHue=%23f3f3f3&clientId=u86ff8cf5-b82f-4&from=paste&height=374&id=u995ee785&originHeight=498&originWidth=852&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=38506&status=done&style=shadow&taskId=u28b3544c-ed80-457d-abba-b2f78743359&title=&width=639)<br />再看其他情况。<br />状态转移方程 `dp[i][j] = max(dp[i-1][j],  dp[i-1][j-weight[i]]+value[i]);`可以看出 i 是由 i-1 推导出来，**那么 i 为 0 的时候就一定要初始化。**<br />dp[0][j]，即：i 为 0，存放编号 0 的物品的时候，各个容量的背包所能存放的最大价值。<br />那么很明显当 j < weight[0]的时候，dp[0][j] 应该是 0，因为背包容量比编号0的物品重量还小。<br />当 j >= weight[0] 时，dp[0][j] 应该是value[0]，因为背包容量放足够放编号0物品。<br />代码初始化如下：
```java
for (int j = 0 ; j < weight[0]; j++) {  // 当然这一步，如果把dp数组预先初始化为0了，这一步就可以省略，但很多同学应该没有想清楚这一点。
    dp[0][j] = 0;
}
// 正序遍历
for (int j = weight[0]; j <= bagweight; j++) {
    dp[0][j] = value[0];
}
```
此时dp数组初始化情况如图所示：<br />![20210110103109140.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689511910573-a18979cd-1a8d-4441-bf21-03ad31672725.png#averageHue=%23f2f2f2&clientId=u86ff8cf5-b82f-4&from=paste&height=342&id=u1839d782&originHeight=456&originWidth=838&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=37490&status=done&style=shadow&taskId=u183f923f-52ed-4653-93fd-59e093a13dc&title=&width=629)<br />dp[0][j] 和 dp[i][0] 都已经初始化了，那么其他下标应该初始化多少呢？<br />其实从递归公式： `dp[i][j] = max(dp[i-1][j], dp[i-1][j-weight[i]]+value[i]); `可以看出 dp[i][j] 是由左上方数值推导出来了，那么 其他下标初始为什么数值都可以，因为都会被覆盖。<br />初始-1，初始-2，初始100，都可以！<br />但只不过一开始就统一把dp数组统一初始为0，更方便一些。如图：<br />![动态规划-背包问题10.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/32832913/1689512018510-a71f84f7-e3bf-41a1-a1c3-b509690d084c.jpeg#averageHue=%23f1f1f1&clientId=u86ff8cf5-b82f-4&from=paste&height=398&id=u41ee18bc&originHeight=466&originWidth=804&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=65951&status=done&style=shadow&taskId=u9902614f-d8ff-48cf-8c4e-0433a090e39&title=&width=687.1795123850815)<br />最后初始化代码如下：
```java
// 初始化 dp
int[][] dp = new int[goods][bagSize + 1];

// 初始化dp数组
// 创建数组后，其中默认的值就是0
for (int j = weight[0]; j <= bagSize; j++) {
    dp[0][j] = value[0];
}
```
费了这么大的功夫，才把如何初始化讲清楚，相信不少同学平时初始化dp数组是凭感觉来的，但有时候感觉是不靠谱的。

4. **确定遍历顺序**

在如下图中，可以看出，有两个遍历的维度：物品与背包重量<br />![2021011010314055.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689512123457-32cf75c9-8ec5-44b6-b66a-e604081187be.png#averageHue=%23f3f2f2&clientId=u86ff8cf5-b82f-4&from=paste&height=350&id=u4dcbd2fa&originHeight=466&originWidth=866&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=38505&status=done&style=shadow&taskId=ubc45d097-70c9-49cc-94b8-1edb8bc6b40&title=&width=650)<br />那么问题来了，先遍历 物品还是先遍历背包重量呢？其实都可以！！ 但是**先遍历物品更好理解**。<br />那么我先给出先遍历物品，然后遍历背包重量的代码。
```java
// weight数组的大小 就是物品个数
for(int i = 1; i < weight.size(); i++) { // 遍历物品
    for(int j = 0; j <= bag_weight; j++) { // 遍历背包容量
        // weight[i] 下标i物品的重量
        if (j < weight[i]) {  // 如果此时背包的容量比要放的物品重量小
            dp[i][j] = dp[i - 1][j];  // 不放入该物品, 直接将上面的次的 价值 值填入
        } else {
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i]);  // 选较大的价值
        }
    }
}
```
**先遍历背包，再遍历物品**，也是可以的！（注意我这里使用的二维dp数组）例如这样：
```java
// weight数组的大小 就是物品个数
for(int j = 0; j <= bag_weight; j++) { // 遍历背包容量
    for(int i = 1; i < weight.size(); i++) { // 遍历物品
        if (j < weight[i]) dp[i][j] = dp[i - 1][j];
        else dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
    }
}
```
为什么也是可以的呢？ 要理解**递归**的本质 和 **递推**的方向。<br />`dp[i][j] = max(dp[i - 1][], dp[i - 1][j - weight[i]] + value[i]);` 递归公式中可以看出 dp[i][j] 是靠 dp[i-1][j] 和 dp[i - 1][j - weight[i]] 推导出来的。<br />dp[i-1][j] 和 dp[i - 1][j - weight[i]] 都在 dp[i][j] 的左上角方向（包括正上方向），那么**先遍历物品，再遍历背包**的过程如图所示：<br />![202101101032124.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689514218079-b996ecc5-1b54-47ed-93aa-98bd170e3a31.png#averageHue=%23f4f2f2&clientId=u86ff8cf5-b82f-4&from=paste&height=246&id=u65373ba0&originHeight=492&originWidth=892&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=41871&status=done&style=shadow&taskId=ubb0e50c0-5e9b-4fd9-8aa2-a30e9e6d213&title=&width=446)<br />再来看看**先遍历背包，再遍历物品**呢，如图：<br />![20210110103244701.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689514242233-28d61562-619b-4861-95ed-d11c73815afd.png#averageHue=%23f3f1f1&clientId=u86ff8cf5-b82f-4&from=paste&height=242&id=u44e096c4&originHeight=484&originWidth=850&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=42569&status=done&style=shadow&taskId=u04912313-39f9-481b-af90-5f5b0cede98&title=&width=425)<br />大家可以看出，虽然两个for循环遍历的次序不同，但是dp[i][j]所需要的数据就是左上角，根本不影响dp[i][j]公式的推导！但**先遍历物品再遍历背包这个顺序更好理解**。<br />其实背包问题里，两个for循环的先后循序是非常有讲究的，理解遍历顺序其实比理解推导公式难多了。

5. **举例推导dp数组**

来看一下对应的dp数组的数值，如图：<br />![20210118163425129.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/32832913/1689514377966-d4c78359-cbe0-4595-b333-eed8d73066c7.jpeg#averageHue=%23f2f2f2&clientId=u86ff8cf5-b82f-4&from=paste&height=330&id=uce527584&originHeight=440&originWidth=850&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=68195&status=done&style=shadow&taskId=u419f5f01-9a18-479a-8108-5974144b6e1&title=&width=638)<br />最终结果就是dp[2][4]。<br />建议大家此时自己在纸上推导一遍，看看dp数组里每一个数值是不是这样的。<br />做动态规划的题目，最好的过程就是自己在纸上举一个例子把对应的dp数组的数值推导一下，然后在动手写代码！<br />很多同学做dp题目，遇到各种问题，然后凭感觉东改改西改改，怎么改都不对，或者稀里糊涂就改过了。<br />主要就是自己没有动手推导一下dp数组的演变过程，如果推导明白了，代码写出来就算有问题，只要把dp数组打印出来，对比一下和自己推导的有什么差异，很快就可以发现问题了。
```java
public class BagProblem {
    public static void main(String[] args) {
        int[] weight = {1,3,4};
        int[] value = {15,20,30};
        int bagSize = 4;
        testWeightBagProblem(weight,value,bagSize);
    }

    /**
     * 动态规划获得结果
     * @param weight  物品的重量
     * @param value   物品的价值
     * @param bagSize 背包的容量
     */
    public static void testWeightBagProblem(int[] weight, int[] value, int bagSize){

        // 创建dp数组
        int goods = weight.length;  // 获取物品的数量
        int[][] dp = new int[goods][bagSize + 1];

        // 初始化dp数组
        // 创建数组后，其中默认的值就是0
        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = value[0];
        }

        // 填充dp数组
        for (int i = 1; i < weight.length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i]) {
                    /**
                     * 当前背包的容量都没有当前物品i大的时候，是不放物品i的
                     * 那么前i-1个物品能放下的最大价值就是当前情况的最大价值
                     */
                    dp[i][j] = dp[i-1][j];
                } else {
                    /**
                     * 当前背包的容量可以放下物品i
                     * 那么此时分两种情况：
                     *    1、不放物品i
                     *    2、放物品i
                     * 比较这两种情况下，哪种背包中物品的最大价值最大
                     */
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i-1][j-weight[i]] + value[i]);
                }
            }
        }

        // 打印dp数组
        for (int i = 0; i < goods; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }
}
```
讲了这么多才刚刚把二维dp的01背包讲完，这里大家其实可以发现最简单的是推导公式了，推导公式估计看一遍就记下来了，但难就难在如何初始化和遍历顺序上。<br />可能有的同学并没有注意到 **初始化** 和 **遍历顺序 **的重要性，我们后面做力扣上背包面试题目的时候，大家就会感受出来了。
<a name="abyVy"></a>
# 01背包问题 一维
:::info
[https://programmercarl.com/背包理论基础01背包-2.html](https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-2.html)<br />视频讲解：[https://www.bilibili.com/video/BV1BU4y177kY](https://www.bilibili.com/video/BV1BU4y177kY)
:::
<a name="zfBhf"></a>
## 一维dp数组:滚动数组(例题)
:::info
题目<br />背包最大重量为4。<br />物品为：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689516637980-4df5607a-aacd-4345-911a-11ac9fc411cc.png#averageHue=%23e9ca9d&clientId=u86ff8cf5-b82f-4&from=paste&height=155&id=u08892155&originHeight=181&originWidth=224&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=5743&status=done&style=shadow&taskId=u17cde3b0-212f-4cfa-b2ca-6461e57cc89&title=&width=191.45299847544558)<br />问背包能背的物品最大价值是多少？
:::
对于背包问题其实状态都是可以压缩的。<br />在使用**二维数组**的时候，递推公式：`dp[i][j] = max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i]);`<br />其实可以发现如果把 dp[i-1] 那一层拷贝到dp[i]上，表达式完全可以是：`dp[i][j] = max(dp[i][j], dp[i][j-weight[i]] + value[i]);`<br />与其把 dp[i-1 ]这一层拷贝到 dp[i] 上，不如只用一个一维数组了，只用dp[j]（一维数组，也可以理解是一个滚动数组）。<br />这就是滚动数组的由来，需要满足的条件是上一层可以重复利用，直接拷贝到当前层。<br />读到这里估计大家都忘了 dp[i][j]里的 i 和 j 表达的是什么了，**i是物品，j是背包容量**。<br />**dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少**。一定要时刻记住这里i和j的含义，要不然很容易看懵了。<br />动规五部曲分析如下：

1. **确定dp数组的定义**

在一维dp数组中，dp[j]表示：容量为j的背包，所背的物品价值可以最大为dp[j]。

2. **一维dp数组的递推公式**

dp[j]为 容量为 j 的背包所背的最大价值，那么如何推导dp[j]呢？<br />dp[j]可以通过 dp[j-weight[i]] 推导出来，dp[j - weight[i]] 表示容量为 j - weight[i] 的背包所背的最大价值。<br />`dp[j - weight[i]] + value[i]` 表示 容量为 j 物品i 重量 的背包 加上 物品i 的价值。（也就是容量为j的背包，放入物品i了之后的价值即：dp[j]）<br />此时dp[j]有**两个选择**，**一个是取**自己dp[j] 相当于 二维dp数组中的dp[i-1][j]，即不放物品i，**一个是取**`dp[j-weight[i]] + value[i]`, 即放物品i，指定是取最大的，毕竟是求最大价值，<br />所以递归公式为：
```java
dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
```
可以看出相对于二维dp数组的写法，就是把dp[i][j]中i的维度去掉了。

3. **一维dp数组如何初始化**

关于初始化，一定要和dp数组的定义吻合，否则到递推公式的时候就会越来越乱。<br />dp[j]表示：容量为j的背包，所背的物品价值可以最大为dp[j]，那么dp[0]就应该是0，因为背包容量为0所背的物品的最大价值就是0。<br />那么dp数组除了下标0的位置，初始为0，其他下标应该初始化多少呢？<br />看一下递归公式：dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);<br />dp数组在推导的时候一定是取价值最大的数，如果题目给的价值都是正整数那么非0下标都初始化为0就可以了。这样才能让dp数组在递归公式的过程中取的最大的价值，而不是被初始值覆盖了。那么假设物品价值都是大于0的，所以dp数组初始化的时候，都初始为0就可以了。

4. **一维dp数组遍历顺序**

代码如下：
```java
for(int i = 0; i < weight.size(); i++) { // 遍历物品
    for(int j = bagWeight; j >= weight[i]; j--) { // 遍历背包容量  注意这里的遍历顺序
        dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
    }
}
```
这里大家发现和二维dp的写法中，遍历背包的顺序是不一样的！**二维dp遍历的时候，背包容量是从小到大，而一维dp遍历的时候，背包是从大到小**。<br />为什么呢？ **倒序遍历是为了保证物品i只被放入一次**！。但如果一旦正序遍历了，那么物品0就会被重复加入多次！<br />举一个例子：物品0的重量weight[0] = 1，价值value[0] = 15<br />如果正序遍历<br />dp[1] = dp[1 - weight[0]] + value[0] = 15<br />dp[2] = dp[2 - weight[0]] + value[0] = 30<br />此时dp[2]就已经是30了，意味着物品0，被放入了两次，所以不能正序遍历。<br />为什么倒序遍历，就可以保证物品只放入一次呢？倒序就是先算dp[2]<br />dp[2] = dp[2 - weight[0]] + value[0] = 15 （dp数组已经都初始化为0）<br />dp[1] = dp[1 - weight[0]] + value[0] = 15<br />所以从后往前循环，每次取得状态不会和之前取得状态重合，这样每种物品就只取一次了。<br />那么问题又来了，为什么二维dp数组历的时候不用倒序呢？因为对于二维dp，dp[i][j]都是通过上一层即dp[i - 1][j]计算而来，本层的dp[i][j]并不会被覆盖！（如何这里读不懂，大家就要动手试一试了，空想还是不靠谱的，实践出真知！）<br />再来看看两个嵌套for循环的顺序，代码中是**先遍历物品嵌套遍历背包容量**，那可不可以先遍历背包容量嵌套遍历物品呢？**不可以**！<br />因为一维dp的写法，**背包容量一定是要倒序遍历**（原因上面已经讲了），如果遍历背包容量放在上一层，那么每个dp[j]就只会放入一个物品，即：背包里只放入了一个物品。<br />**倒序遍历的原因是，本质上还是一个对二维数组的遍历，并且右下角的值依赖上一层左上角的值，因此需要保证左边的值仍然是上一层的，从右向左覆盖**。（这里如果读不懂，就再回想一下dp[j]的定义，或者就把两个for循环顺序颠倒一下试试！）<br />所以一维dp数组的背包在遍历顺序上和二维其实是有很大差异的！，这一点大家一定要注意。

5. **举例推导dp数组**

一维dp，分别用物品0，物品1，物品2 来遍历背包，最终得到结果如下：<br />![20210110103614769.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689517978951-5a6cd88d-14fb-4f81-8e71-16534268d493.png#averageHue=%23f2f1f1&clientId=u86ff8cf5-b82f-4&from=paste&height=362&id=ubaf59543&originHeight=482&originWidth=890&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=43891&status=done&style=shadow&taskId=u3163dbd7-9cab-421e-9bfe-36e2ae7a928&title=&width=668)
```java
public static void main(String[] args) {
    int[] weight = {1, 3, 4};
    int[] value = {15, 20, 30};
    int bagWight = 4;
    testWeightBagProblem(weight, value, bagWight);
}

public static void testWeightBagProblem(int[] weight, int[] value, int bagWeight){
    int wLen = weight.length;
    //定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值
    int[] dp = new int[bagWeight + 1];
    //遍历顺序：先遍历物品，再遍历背包容量
    for (int i = 0; i < wLen; i++){
        for (int j = bagWeight; j >= weight[i]; j--){
            dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
        }
    }
    //打印dp数组
    for (int j = 0; j <= bagWeight; j++){
        System.out.print(dp[j] + " ");
    }
}
```
以上的讲解可以开发一道面试题目（毕竟力扣上没原题）。<br />就是本文中的题目，要求先实现一个纯**二维**的01背包，如果写出来了，然后再问为什么两个for循环的嵌套顺序这么写？反过来写行不行？再讲一讲初始化的逻辑。<br />然后要求实现一个**一维**数组的01背包，最后再问，一维数组的01背包，两个for循环的顺序反过来写行不行？为什么？<br />注意以上问题都是在候选人把代码写出来的情况下才问的。<br />就是纯01背包的题目，都不用考01背包应用类的题目就可以看出候选人对算法的理解程度了。<br />相信大家读完这篇文章，应该对以上问题都有了答案！<br />此时01背包理论基础就讲完了，我用了两篇文章把01背包的dp数组定义、递推公式、初始化、遍历顺序从二维数组到一维数组统统深度剖析了一遍，没有放过任何难点。<br />大家可以发现其实信息量还是挺大的。<br />如果把 `二维dp数组01背包(例题)` 和 `本篇` 的内容都理解了，后面再做01背包的题目，就会发现非常简单了。<br />不用再凭感觉或者记忆去写背包，而是有自己的思考，了解其本质，代码的方方面面都在自己的掌控之中。即使代码没有通过，也会有自己的逻辑去debug，这样就思维清晰了。
<a name="wAAPA"></a>
# 416.分割等和子集
:::info
本题是 01背包的应用类题目<br />[https://programmercarl.com/0416.分割等和子集.html](https://programmercarl.com/0416.%E5%88%86%E5%89%B2%E7%AD%89%E5%92%8C%E5%AD%90%E9%9B%86.html)<br />视频讲解：[https://www.bilibili.com/video/BV1rt4y1N7jE](https://www.bilibili.com/video/BV1rt4y1N7jE)
:::
:::info
本题是 01背包的应用类题目  (数组, 动态规划)<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689573042650-a906834a-731b-4300-82ec-98df69e7bda6.png#averageHue=%232c2c2b&clientId=u4c53d5d6-3ad6-4&from=paste&height=125&id=ucfa0eb18&originHeight=146&originWidth=394&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=5429&status=done&style=shadow&taskId=u596ce49a-4644-4249-b135-aea019d55e2&title=&width=336.75214910413195)<br />这道题目初步看，和如下两题几乎是一样的，大家可以用回溯法，解决如下两题

- 698.划分为k个相等的子集
- 473.火柴拼正方形

这道题目是要找是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。<br />那么只要找到集合里能够出现 `sum / 2` 的子集总和，就算是可以分割成两个相同元素和子集了。<br />本题是**可以用回溯暴力搜索出所有答案的，但最后超时了，也不想再优化了，放弃回溯，直接上01背包吧**。
:::
背包问题，大家都知道，有N件物品和一个最多能背重量为W 的背包。第i件物品的重量是weight[i]，得到的价值是value[i] 。每件物品只能用一次，求解将哪些物品装入背包里物品价值总和最大。<br />背包问题有多种背包方式，常见的有：01背包、完全背包、多重背包、分组背包和混合背包等等。要注意题目描述中商品是不是可以重复放入。即一个商品如果可以重复多次放入是完全背包，而只能放入一次是01背包，写法还是不一样的。要明确**本题中我们要使用的是01背包**，因为元素我们只能用一次。<br />回归主题：首先，本题要求集合里能否出现总和为 sum / 2 的子集。<br />那么来对应一下本题，看看背包问题如何来解决。只有确定了如下四点，才能把01背包问题套到本题上来。

- 背包的体积为 sum / 2
- 背包要放入的商品（集合里的元素）重量为 元素的数值，价值也为元素的数值
- 背包如果正好装满，说明找到了总和为 sum / 2 的子集。
- 背包中每一个元素是不可重复放入。

以上分析完，我们就可以套用01背包，来解决这个问题了。

1. **确定dp数组以及下标的含义**

01背包中，dp[j] 表示： 容量为j的背包，所背的物品价值最大可以为dp[j]。<br />**本题中每一个元素的数值既是重量，也是价值**。套到本题，dp[j]表示 背包总容量（所能装的总重量）是j，放进物品后，背的最大重量为dp[j]。<br />那么如果背包容量为target， dp[target]就是装满 背包之后的重量，所以 当 `dp[target] == target` 的时候，背包就装满了。<br />有录友可能想，那还有装不满的时候？拿输入数组 [1, 5, 11, 5]，举例， dp[7] 只能等于 6，因为 只能放进 1 和 5。而dp[6] 就可以等于6了，放进1 和 5，那么dp[6] == 6，说明背包装满了。

2. **确定递推公式**

01背包的递推公式为：`dp[j] = max(dp[j], dp[j-weight[i]] + value[i]);`<br />本题，相当于背包里放入数值，那么 物品i 的重量是nums[i]，其价值也是nums[i]。<br />所以递推公式：`dp[j] = max(dp[j], dp[j-nums[i]] + nums[i]);`

3. **dp数组如何初始化**

在01背包，一维dp如何初始化，已经讲过，从dp[j]的定义来看，首先dp[0]一定是0。<br />如果题目给的价值都是正整数那么 非0下标 都 初始化为0 就可以了，如果题目给的价值有负数，那么非0下标就要初始化为负无穷。这样才能让dp数组在递推的过程中取得最大的价值，而不是被初始值覆盖了。<br />本题题目中 只包含正整数的非空数组，所以非0下标的元素初始化为0就可以了。<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689573042650-a906834a-731b-4300-82ec-98df69e7bda6.png#averageHue=%232c2c2b&clientId=u4c53d5d6-3ad6-4&from=paste&height=125&id=C1l15&originHeight=146&originWidth=394&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=5429&status=done&style=shadow&taskId=u596ce49a-4644-4249-b135-aea019d55e2&title=&width=336.75214910413195)<br />代码如下：
```java
// 题目中说：每个数组中的元素不会超过 100，数组的大小不会超过 200
// 总和不会大于20000，背包最大只需要其中一半，所以10001大小就可以了
vector<int> dp(10001, 0);
```
或
```java
int target = sum / 2;
int[] dp = new int[target + 1];
```

4. **确定遍历顺序**

如果使用一维dp数组，**物品遍历**的for循环放在**外层**，**遍历背包**的for循环放在**内层**，且内层for循环倒序遍历！代码如下：
```java
// 开始 01背包
for(int i = 0; i < nums.size(); i++) {
    for(int j = target; j >= nums[i]; j--) { // 每一个元素一定是不可重复放入，所以从大到小遍历
        dp[j] = max(dp[j], dp[j-nums[i]] + nums[i]);
    }
}
```

5. **举例推导dp数组**

dp[j]的数值一定是**小于等于** j 的。**如果 dp[j] == j 说明，集合中的子集总和正好可以凑成总和j，理解这一点很重要**。<br />用例1，输入[1,5,11,5] 为例，如图：<br />![20210110104240545.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689573206347-00613a32-d005-430c-901c-972f2fae68d7.png#averageHue=%23f5f4f4&clientId=u4c53d5d6-3ad6-4&from=paste&height=232&id=u60bcf71c&originHeight=464&originWidth=1332&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=52190&status=done&style=shadow&taskId=ue5c68ebe-a850-4d2f-beea-d25d7765b91&title=&width=666)<br />最后dp[11] == 11，说明可以将这个数组分割成两个子集，使得两个子集的元素和相等。
```java
class Solution {
    public boolean canPartition(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        int n = nums.length;
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        //总和为奇数，不能平分
        if(sum % 2 != 0) return false;
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for(int i = 0; i < n; i++) {
            for(int j = target; j >= nums[i]; j--) {
                //物品 i 的重量是 nums[i]，其价值也是 nums[i]
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
           
            //剪枝一下，每一次完成內層的for-loop，立即檢查是否dp[target] == target，優化時間複雜度（26ms -> 20ms）
            if(dp[target] == target)
                return true;
        }
        return dp[target] == target;
    }
}
```
```java
public class Solution {
    public static void main(String[] args) {
        int num[] = {1,5,11,5};
        canPartition(num);

    }
    public static boolean canPartition(int[] nums) {
        int len = nums.length;
        // 题目已经说非空数组，可以不做非空判断
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 特判：如果是奇数，就不符合要求
        if ((sum %2 ) != 0) {
            return false;
        }

        int target = sum / 2; //目标背包容量
        // 创建二维状态数组，行：物品索引，列：容量（包括 0）
        /*
        dp[i][j]表示从数组的 [0, i] 这个子区间内挑选一些正整数
          每个数只能用一次，使得这些数的和恰好等于 j。
        */
        boolean[][] dp = new boolean[len][target + 1];  // len为数组的长度

        // 这一段可有可无
        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满  （这里的dp[][]数组的含义就是“恰好”，所以就算容积比它大的也不要）
        /*if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }*/

        // 再填表格后面几行
        //外层遍历物品
        for (int i = 1; i < len; i++) {
            //内层遍历背包
            for (int j = 0; j <= target; j++) {  // 这个写法j代表背包的容量肯定会到11, 判断有没有情况
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i - 1][j];

                //如果某个物品单独的重量恰好就等于背包的重量，那么也是满足dp数组的定义的
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                // 如果某个物品的重量小于j，那就可以看该物品是否放入背包
                // dp[i - 1][j] 表示该物品不放入背包，如果在 [0, i-1] 这个子区间内已经有一部分元素，使得它们的和为 j ，那么 dp[i][j] = true；
                // dp[i - 1][j - nums[i]] 表示该物品放入背包。如果在 [0, i-1] 这个子区间内就得找到一部分元素，使得它们的和为 j-nums[i]。
                if (nums[i] < j) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];  // 有一个为true, 结果就为true
                    // 注意 dp[i-1][j-nums[i]] 中的 j-nums[i], 背包的容量减去当前物品的重量, 如果有值刚好与减去后的相等, 则背包刚好装满
                }
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return dp[len - 1][target];
    }
}
//dp数组的打印结果
false true false false false false false false false false false false 
false true false false false true true false false false false false 
false true false false false true true false false false false true 
false true false false false true true false false false true true 
```
```java
class Solution {
    public boolean canPartition(int[] nums) {
        //using 2-D DP array.
        int len = nums.length;
        //check edge cases;
        if(len == 0)
            return false;

        int sum = 0;
        for (int num : nums)
            sum += num;
        //we only deal with even numbers. If sum is odd, return false;
        if(sum % 2 == 1)
            return false;
        
        int target = sum / 2;
        int[][] dp = new int[nums.length][target + 1];

        // for(int j = 0; j <= target; j++){
        //     if(j < nums[0])
        //         dp[0][j] = 0;
        //     else
        //         dp[0][j] = nums[0];
        // }

        //initialize dp array
        for(int j = nums[0]; j <= target; j++){
            dp[0][j] = nums[0];
        }

        for(int i = 1; i < len; i++){
            for(int j = 0; j <= target; j++){
                if (j < nums[i]) 
                    dp[i][j] = dp[i - 1][j];
                else 
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
            }
        }

        //print out DP array
        // for(int x : dp){
        //     System.out.print(x + ",");
        // }
        // System.out.print("    "+i+" row"+"\n");
        return dp[len - 1][target] == target;
    }
}
//dp数组的打印结果 for test case 1.
0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
0, 1, 1, 1, 1, 5, 6, 6, 6, 6, 6, 6, 
0, 1, 1, 1, 1, 5, 6, 6, 6, 6, 6, 11, 
0, 1, 1, 1, 1, 5, 6, 6, 6, 6, 10, 11, 
```
-

