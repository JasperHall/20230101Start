时间:  2023.6.21 周三
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划 part05, 1049. 最后一块石头的重量 II, 494. 目标和, 474.一和零  
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [ ] 复习day42

详细布置 
<a name="tm4VA"></a>
# 1049. 最后一块石头的重量 II 
:::info
本题就和 昨天的 416. 分割等和子集 很像了，可以尝试先自己思考做一做。 <br />视频讲解：[动态规划之背包问题，这个背包最多能装多少？LeetCode：1049.最后一块石头的重量II_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV14M411C7oV)<br />[https://programmercarl.com/1049.%E6%9C%80%E5%90%8E%E4%B8%80%E5%9D%97%E7%9F%B3%E5%A4%B4%E7%9A%84%E9%87%8D%E9%87%8FII.html](https://programmercarl.com/1049.%E6%9C%80%E5%90%8E%E4%B8%80%E5%9D%97%E7%9F%B3%E5%A4%B4%E7%9A%84%E9%87%8D%E9%87%8FII.html)
:::
:::info
和  416. 分割等和子集 很像  (数组, 动态规划)
:::
注意本题不是直接完全平分成两个, 而是根据现有的各个石头的重量, 尽量分成近似相等的两堆, 然后碰撞<br />本题**物品的重量**为stones[i]，**物品的价值**也为stones[i]。对应着01背包里的物品重量weight[i]和 物品价值value[i]。<br />接下来进行动规五步曲：

1. **确定dp数组以及下标的含义**

dp[j]表示容量（这里说容量更形象，其实就是重量）为j的背包，最多可以背最大重量为dp[j]。<br />可以回忆一下01背包中，dp[j]的含义，**容量为j**的背包，最多可以装的价值为 dp[j]。<br />相对于 01背包，本题中，石头的重量是 stones[i]，石头的价值也是 stones[i] ，可以 “**最多可以装的价值为 dp[j]” == “最多可以背的重量为dp[j]”**

2. **确定递推公式**

01背包的递推公式为：`dp[j] = max(dp[j], dp[j-weight[i]] + value[i]);`<br />本题则是：`dp[j] = max(dp[j], dp[j-**stones[i]**] + **stones[i]**);`<br />一些同学可能看到这`dp[j-stones[i]] + stones[i]`中 又有`- stones[i]`又有`+stones[i]`，看着有点晕乎。大家可以再去看 dp[j]的含义。

3. **dp数组如何初始化**

既然 dp[j]中的j表示容量，那么最大容量（重量）是多少呢，就是所有石头的重量和。<br />因为提示中给出1 <= stones.length <= 30，1 <= stones[i] <= 1000，所以最大重量就是30 * 1000 。而我们要求的target其实只是最大重量的一半，所以dp数组开到15000大小就可以了。<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689589954223-858d0c5b-7a8e-4541-897d-bd692d6627ca.png#averageHue=%232a2a2a&clientId=u4c53d5d6-3ad6-4&from=paste&height=126&id=u80628e20&originHeight=147&originWidth=610&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=5805&status=done&style=shadow&taskId=u40412f18-b176-4fb4-b4bc-18b11beee12&title=&width=521.3675404911688)<br />当然**也可以把石头遍历一遍，计算出石头总重量 然后除2，得到dp数组的大小**。<br />接下来就是如何初始化dp[j]呢，因为重量都不会是负数，所以dp[j]都初始化为0就可以了，这样在递归公式`dp[j] = max(dp[j], dp[j - stones[i]] + stones[i]);`中dp[j]才不会初始值所覆盖。代码为：
```java
int target = sum >> 1;
//初始化dp数组
int[] dp = new int[target + 1];
```
或者
```java
vector<int> dp(15001, 0);
```

4. **确定遍历顺序**

如果使用一维dp数组，**物品遍历**的for循环放在**外层**，**遍历背包**的for循环放在**内层**，且内层for循环倒序遍历！代码如下：
```java
for (int i = 0; i < stones.length; i++) {  //遍历物品  使用一维数组时, 物品遍历在外侧, 背包遍历在内测
    //采用倒序
    for (int j = target; j >= stones[i]; j--) {  // 遍历背包
        //两种情况，要么放，要么不放
        dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
    }
}
```

5. **举例推导dp数组**

举例，输入：[2,4,1,1]，此时target = (2 + 4 + 1 + 1)/2 = 4 ，dp数组状态图如下：<br />![20210121115805904.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/32832913/1689590228803-477d4095-b2eb-4a0c-921d-0b2dfc151b1b.jpeg#averageHue=%23f2f2f2&clientId=u4c53d5d6-3ad6-4&from=paste&height=359&id=u14866468&originHeight=718&originWidth=920&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=121600&status=done&style=shadow&taskId=u48135f79-6053-4e55-adc7-9c000ec652e&title=&width=460)<br />最后dp[target]里是容量为target的背包所能背的最大重量。<br />那么分成两堆石头，**一堆**石头的总重量是dp[target]，**另一堆**就是sum - dp[target]。<br />**在计算target的时候，target = sum / 2 因为是向下取整，所以**`**sum - dp[target]**`** 一定是大于等于**`**dp[target]**`**的**。<br />那么相撞之后剩下的最小石头重量就是 (sum - dp[target]) - dp[target]。<br />完整代码
```java
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }
        int target = sum >> 1;
        //初始化dp数组
        int[] dp = new int[target + 1];
        // 注意这里i要从0开始
        for (int i = 0; i < stones.length; i++) {  // 使用一维数组时, 物品遍历在外侧, 背包遍历在内测
            //采用倒序
            for (int j = target; j >= stones[i]; j--) {  // 
                //两种情况，要么放，要么不放
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[target];
    }
}
```
```java
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int s : stones) {
            sum += s;
        }

        int target = sum / 2;
        //初始化，dp[i][j]为可以放0-i物品，背包容量为j的情况下背包中的最大价值
        int[][] dp = new int[stones.length][target + 1];
        
        //dp[i][0]默认初始化为0 ,  dp[0][j]取决于stones[0]
        // 初始化背包放入下标为0的物品时,背包的最大价值(第0行的所有列的初始化)
        for (int j = stones[0]; j <= target; j++) {  // 注意这里j的初始值
            dp[0][j] = stones[0];
        }

        for (int i = 1; i < stones.length; i++) {  
            for (int j = 1; j <= target; j++) {  //注意是 <=
                if (j >= stones[i]) {  // 注意这里是 >=
                    //                 不放:dp[i - 1][j]   放:dp[i - 1][j - stones[i]] + stones[i]
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[stones.length - 1][target]);
        return (sum - dp[stones.length - 1][target]) - dp[stones.length - 1][target];
    }
}
```
-
<a name="Obfdp"></a>
# 494. 目标和 
:::info
大家重点理解 递推公式：dp[j] += dp[j - nums[i]]，这个公式后面的提问 我们还会用到。  <br />视频讲解：[动态规划之背包问题，装满背包有多少种方法？| LeetCode：494.目标和_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1o8411j73x)<br />[https://programmercarl.com/0494.%E7%9B%AE%E6%A0%87%E5%92%8C.html](https://programmercarl.com/0494.%E7%9B%AE%E6%A0%87%E5%92%8C.html)
:::
:::info
大家重点理解 递推公式：dp[j] += dp[j - nums[i]]，这个公式后面的提问 我们还会用到。  ([数组](https://leetcode.cn/tag/array/), [动态规划](https://leetcode.cn/tag/dynamic-programming/), [回溯](https://leetcode.cn/tag/backtracking/))<br />二维数组比较好理解, 复习可以先看二维再看一维, 二维的也可以去看看LeetCode上别人的题解, 扩展思路
:::
> 这道题可以用回溯算法暴力搜索出来, 但是会超时

本题要如何使表达式结果为target，既然为target，(**将原数组分成两个数组, left:正数集合数组  right:负数集合数组**)那么就一定有 `left组合 - right组合 = target`。`left + right = sum`，而sum是固定的。`right = sum - left`, 公式来了， `left - (sum - left) = target` 推导出 `left = (target + sum)/2 `。target是固定的，sum是固定的，left就可以求出来。<br />此时问题就是在集合nums中找出和为left的组合。
<a name="P52Li"></a>
## 回溯
此时可以套 39.组合总和 的回溯法代码，几乎不用改动。<br />当然，也可以转变成序列区间选+ 或者 -，使用回溯法，那就是另一个解法。<br />我也把代码给出来吧，大家可以了解一下，回溯的解法，以下是本题转变为组合总和问题的回溯法代码：
```cpp
class Solution {
private:
    vector<vector<int>> result;
    vector<int> path;
    void backtracking(vector<int>& candidates, int target, int sum, int startIndex) {
        if (sum == target) {
            result.push_back(path);
        }
        // 如果 sum + candidates[i] > target 就终止遍历
        for (int i = startIndex; i < candidates.size() && sum + candidates[i] <= target; i++) {
            sum += candidates[i];
            path.push_back(candidates[i]);
            backtracking(candidates, target, sum, i + 1);
            sum -= candidates[i];
            path.pop_back();

        }
    }
public:
    int findTargetSumWays(vector<int>& nums, int S) {
        int sum = 0;
        for (int i = 0; i < nums.size(); i++) sum += nums[i];
        if (S > sum) return 0; // 此时没有方案
        if ((S + sum) % 2) return 0; // 此时没有方案，两个int相加的时候要各位小心数值溢出的问题
        int bagSize = (S + sum) / 2; // 转变为组合总和问题，bagsize就是要求的和

        // 以下为回溯法代码
        result.clear();
        path.clear();
        sort(nums.begin(), nums.end()); // 需要排序
        backtracking(nums, bagSize, 0, 0);
        return result.size();
    }
};
```
当然以上代码超时了。也可以使用记忆化回溯，但这里我就不在回溯上下功夫了，直接看动规吧
<a name="IypJl"></a>
## 动态规划
如何转化为01背包问题呢。<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689644530938-7c5c1214-ca7e-4101-8bd3-fd69d427fcb7.png#averageHue=%23ececec&clientId=u812841da-20da-4&from=paste&height=108&id=u5662b961&originHeight=126&originWidth=1376&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=38172&status=done&style=shadow&taskId=uc3e7f703-033d-4ad6-82fe-5f8da096a0b&title=&width=1176.0684192063086)<br />假设加法的总和为x，那么减法对应的总和就是sum - x。所以我们要求的是 **x - (sum - x) = target  ==>  x = (target + sum) / 2**<br />此时问题就转化为，装满容量为x的背包，有几种方法。这里的x，就是bagSize，也就是我们后面要求的背包容量。<br />大家看到** (target + sum) / 2 应该担心计算的过程中向下取整有没有影响**。这么担心就对了，例如sum 是5，target是2的话其实就是无解的，所以：
```java
if ((target + sum) % 2 == 1) return 0; // 此时没有方案
```
同时如果 target的绝对值已经大于sum，那么也是没有方案的。
```java
if (abs(target) > sum) return 0; // 此时没有方案
```
再回归到01背包问题，为什么是01背包呢？因为每个物品（题目中的1）只用一次！<br />这次和之前遇到的背包问题不一样了，之前都是求容量为j的背包，最多能装多少。本题则是**装满有几种方法**。其实这就**是一个组合问题**了。

1. **确定dp数组以及下标的含义**

dp[j] 表示：填满j（包括j）这么大容积的包，有dp[j]种方法<br />其实也可以使用二维dp数组来求解本题，dp[i][j]：使用 下标为[0, i]的nums[i]能够凑满j（包括j）这么大容量的包，有dp[i][j]种方法。<br />下面我都是统一使用一维数组进行讲解， 二维降为一维（滚动数组），其实就是上一层拷贝下来，

2. **确定递推公式**

有哪些来源可以推出dp[j]呢？只要搞到**nums[i]**，凑成 dp[j] 就有**dp[j - nums[i]]** 种方法。<br />例如：dp[j]，j 为5，

- 已经有一个1（nums[i]） 的话，有 dp[4]种方法 凑成 容量为5的背包。
- 已经有一个2（nums[i]） 的话，有 dp[3]种方法 凑成 容量为5的背包。
- 已经有一个3（nums[i]） 的话，有 dp[2]中方法 凑成 容量为5的背包
- 已经有一个4（nums[i]） 的话，有 dp[1]中方法 凑成 容量为5的背包
- 已经有一个5（nums[i]）的话，有 dp[0]中方法 凑成 容量为5的背包

那么凑整dp[5]有多少方法呢，也就是把 所有的 dp[j - nums[i]] 累加起来。所以求组合类问题的公式，都是类似这种：
```java
dp[j] = dp[j] + dp[j - nums[i]]
```
这个公式在后面在讲解背包解决排列组合问题的时候还会用到！

3. **dp数组如何初始化**

从递推公式可以看出，在**初始化的时候dp[0] 一定要初始化为1**，因为dp[0]是在公式中一切递推结果的起源，如果dp[0]是0的话，递推结果将都是0。<br />这里有录友可能认为从dp数组定义来说 dp[0] 应该是0，也有录友认为dp[0]应该是1。其实不要硬去解释它的含义，咱就把 dp[0]的情况带入本题看看应该等于多少。<br />如果给一个数组[0] ，target = 0，那么 `bagSize = (target + sum) / 2 = 0`。 dp[0]也应该是1， 也就是说给数组里的元素 0 前面无论放加法还是减法，都是 1 种方法。所以本题我们应该**初始化 dp[0] 为 1**。<br />可能有同学想了，那 如果是 数组[0,0,0,0,0] target = 0 呢。其实 此时最终的dp[0] = 32，也就是这五个零子集的所有组合情况，但此dp[0]非彼dp[0]，**dp[0]能算出32，其基础是因为dp[0] = 1 累加起来的**。<br />dp[j]其他下标对应的数值也应该初始化为0，从递推公式也可以看出，dp[j]要保证是0的初始值，才能正确的由dp[j - nums[i]]推导出来。

4. **确定遍历顺序**

对于01背包问题一维dp的遍历，**nums放在外循环，target在内循环，且内循环倒序**。

5. **举例推导dp数组**

输入：nums: [1, 1, 1, 1, 1], target: 3<br />bagSize = (target + sum) / 2 = (3 + 5) / 2 = 4      代入原式: 也就是正数集合left=4, 也就是找出几种集合凑出正数集合数组元素和为4<br />dp数组状态变化如下：<br />![c6a96bda67ded435aa42fd3d62c2514.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689649248453-0123ed79-2a70-4fdf-85a9-e75d88872f75.png#averageHue=%23efedeb&clientId=u812841da-20da-4&from=paste&height=1261&id=u71890fb9&originHeight=1475&originWidth=2329&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=830487&status=done&style=shadow&taskId=u90dd71c7-b763-48cb-91ee-240ee5b5684&title=&width=1990.5983636130034)
```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        
		//如果target过大 sum将无法满足
        if ( target < 0 && sum < -target) return 0;
        if ((target + sum) % 2 != 0) return 0;  // 注意理解这一步
        
        int size = (target + sum) / 2;
        if(size < 0) size = -size;  // 负数转正数后进行计算
        
        int[] dp = new int[size + 1];  // 注意这里要 +1
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[size];
    }
}
```
```java
public int findTargetSumWays2(int[] nums, int target) {
    int sum = 0;
    for (int num : nums) {
        sum += num;
    }

    // 如果target过大 sum将无法满足
    if (target<0 && sum<-target) return 0;
    if ((target+sum) % 2 != 0) return 0;  // 注意理解这一步

    int size = (target+sum) / 2;

    int[][] dp = new int[nums.length+1][size+1];  // 注意这里的数组两个方向长度的选择
    dp[0][0] = 1;

    // 注意初始下标的选取
    for (int i=1; i<nums.length+1; i++){  // 下标0, 已经初始化了, 所以从1开始
        for (int j=0; j<=size; j++){

            dp[i][j] = dp[i-1][j];  // 不选取当前元素
            if (j >= nums[i-1]){  // 背包容量 大于 当前与元素体积
                dp[i][j] += dp[i-1][j-nums[i-1]];  // 选取当前元素
            }
        }
    }

    // 打印数组
    /*for (int i=0; i<nums.length+1; i++){
        for (int j=0; j<=size; j++){
            System.out.print(dp[i][j] + "  ");
        }
        System.out.println();
    }
    System.out.println(dp[nums.length][size]);*/

    return dp[nums.length][size];
}
```
-
<a name="E10In"></a>
# 474.一和零  
:::info
通过这道题目，大家先粗略了解， 01背包，完全背包，多重背包的区别，不过不用细扣，因为后面 对于 完全背包，多重背包 还有单独讲解。<br />视频讲解：[动态规划之背包问题，装满这个背包最多用多少个物品？| LeetCode：474.一和零_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1rW4y1x7ZQ)<br />[https://programmercarl.com/0474.%E4%B8%80%E5%92%8C%E9%9B%B6.html](https://programmercarl.com/0474.%E4%B8%80%E5%92%8C%E9%9B%B6.html)
:::
:::info
难题  通过这道题目来了解 01背包，完全背包，多重背包的区别    ([数组](https://leetcode.cn/tag/array/),[字符串](https://leetcode.cn/tag/string/),[动态规划](https://leetcode.cn/tag/dynamic-programming/))<br />本题相当于把三维压缩成二维
:::
![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689652832190-4c15f20e-28b4-4f9a-a5a8-5c5fbdeea837.png#averageHue=%232b2b2a&clientId=u812841da-20da-4&from=paste&height=193&id=ub7c24ae5&originHeight=226&originWidth=686&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=11438&status=done&style=shadow&taskId=u47a04ec2-292e-4fa4-809c-ec4bf3d65a0&title=&width=586.3248078310521)<br />这道题目，还是比较难的，也有点像程序员自己给自己出个脑筋急转弯，程序员何苦为难程序员呢。<br />来说题，本题不少同学会认为是多重背包，一些题解也是这么写的。其实本题并不是多重背包，再来看一下这个图，捋清几种背包的关系<br />![20210117171307407-20230310132423205.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689664615943-e442b700-a4ec-45e5-b173-ec367ddbc7c8.png#averageHue=%23f6f4f4&clientId=u812841da-20da-4&from=paste&height=614&id=u676d0035&originHeight=718&originWidth=2074&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=139831&status=done&style=shadow&taskId=ubc37edb9-191d-4201-8f1e-59be8a3b56d&title=&width=1772.6496376699738)<br />多重背包是每个物品，数量不同的情况。<br />本题中strs 数组里的元素就是物品，每个物品都是一个！而 m 和 n 相当于是一个背包，两个维度的背包。<br />理解成多重背包的同学主要是把m和n混淆为物品了，感觉这是不同数量的物品，所以以为是多重背包。但本题其实是01背包问题！<br />只不过**这个背包有两个维度，一个是m 一个是n，而不同长度的字符串就是不同大小的待装物品**。<br />开始动规五部曲：

1. **确定dp数组（dp table）以及下标的含义**

dp[i][j]：最多有** i个0 **和** j个1** 的 strs的**最大子集的大小**为dp[i][j]。

2. **确定递推公式**

dp[i][j] 可以由前一个strs里的字符串推导出来，strs里的字符串有zeroNum个0，oneNum个1。<br />dp[i][j] 就可以是 `dp[i - zeroNum][j - oneNum] + 1`。<br />然后我们在遍历的过程中，取dp[i][j]的最大值。所以**递推公式：**`dp[i][j] = max(dp[i][j], dp[i-zeroNum][j-oneNum] + 1);`<br />此时大家可以回想一下01背包的递推公式：dp[j] = max(dp[j], dp[j- weight[i]]+value[i]);<br />对比一下就会发现，**字符串的 zeroNum和oneNum 相当于物品的重量（weight[i]），字符串本身的个数相当于物品的价值（value[i]）**。<br />这就是一个典型的01背包！ 只不过物品的重量有了两个维度而已。

3. **dp数组如何初始化**

在[动态规划：关于01背包问题，你该了解这些！（滚动数组）](https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-2.html)中已经讲解了, 01背包的dp数组初始化为0就可以。<br />因为**物品价值不会是负数**，初始为0，保证递推的时候dp[i][j]不会被初始值覆盖。

4. **确定遍历顺序**

本题相当于从三维降到了二维, 所以还是要用滚动数组<br />在[动态规划：关于01背包问题，你该了解这些！（滚动数组）](https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-2.html)中，我们讲到了01背包为什么一定是外层for循环遍历物品，内层for循环遍历背包容量且从后向前遍历！<br />那么本题也是，物品就是strs里的字符串，背包容量就是题目描述中的m和n。<br />代码如下：
```java
for (string str : strs) { // 遍历物品
    int oneNum = 0, zeroNum = 0;
    for (char c : str) {
        if (c == '0') zeroNum++;
        else oneNum++;
    }
    // 倒序
    for (int i = m; i >= zeroNum; i--) { // 遍历背包容量且从后向前遍历！
        for (int j = n; j >= oneNum; j--) {
            dp[i][j] = max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
        }
    }
}
```
有同学可能想，那个遍历背包容量的两层for循环先后循序有没有什么讲究？<br />没讲究，都是物品重量的一个维度，先遍历哪个都行！

5. **举例推导dp数组**

以输入：["10","0001","111001","1","0"]，m = 3，n = 3为例<br />最后dp数组的状态如下所示：<br />![20210120111201512.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/32832913/1689665795564-4e88f89c-6169-4bc8-baf2-1b467f89a327.jpeg#averageHue=%23f5f5f5&clientId=u812841da-20da-4&from=paste&height=627&id=u533c4333&originHeight=734&originWidth=912&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=98890&status=done&style=shadow&taskId=u8adaef9f-8486-40a5-a200-b65f04973e5&title=&width=779.4872080785999)
```java
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        //dp[i][j]表示i个0和j个1时的最大子集
        int[][] dp = new int[m + 1][n + 1];
        int oneNum, zeroNum;
        for (String str : strs) {
            oneNum = 0;
            zeroNum = 0;
            for (char ch : str.toCharArray()) {
                if (ch == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }
            //倒序遍历
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }
}

时间复杂度: O(kmn)，k 为strs的长度
空间复杂度: O(mn)
```
-




