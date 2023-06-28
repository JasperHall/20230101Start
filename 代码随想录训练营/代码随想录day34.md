时间: 2023.6.12周一
<a name="sPFyY"></a>
# 今日任务
第八章 贪心算法 part03, 1005.K次取反后最大化的数组和, 134. 加油站, 135. 分发糖果  
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 复习理解 134. 加油站 

<a name="XBsr0"></a>
# 复习

- [ ] <br />

<a name="nfJq2"></a>
# 1005.K次取反后最大化的数组和  
:::info
本题简单一些，估计大家不用想着贪心 ，用自己直觉也会有思路。 [代码随想录](https://programmercarl.com/1005.K%E6%AC%A1%E5%8F%96%E5%8F%8D%E5%90%8E%E6%9C%80%E5%A4%A7%E5%8C%96%E7%9A%84%E6%95%B0%E7%BB%84%E5%92%8C.html)
:::
:::info
本题简单一些，估计大家不用想着贪心 ，用自己直觉也会有思路。 (贪心, 数组, 排序)
:::
贪心的思路，**局部最优**：让绝对值大的负数变为正数，当前数值达到最大，**整体最优**：整个数组和达到最大。局部最优可以推出全局最优。<br />那么如果将负数都转变为正数了，K依然大于0，此时的问题是一个有序正整数序列，如何转变K次正负，让 数组和 达到最大。<br />那么又是一个贪心：**局部最优**：只找数值最小的正整数进行反转，当前数值和可以达到最大（例如正整数数组{5, 3, 1}，反转1 得到-1 比 反转5得到的-5 大多了），**全局最优**：整个 数组和 达到最大。<br />虽然这道题目大家做的时候，可能都不会去想什么贪心算法，一鼓作气，就AC了。<br />我这里其实是为了给大家展现出来 经常被大家忽略的贪心思路，这么一道简单题，就用了两次贪心！

那么**本题的解题步骤为**：

- 第一步：将数组按照绝对值大小从大到小排序，注意要按照绝对值的大小
- 第二步：从前向后遍历，遇到负数将其变为正数，同时K--
- 第三步：如果K还大于0，那么反复转变数值最小的元素，将K用完
- 第四步：求和
```java
public int largestSumAfterKNegations(int[] nums, int k) {
    // 将数组按照绝对值大小 从大到小 排序，注意要按照绝对值的大小
    nums = IntStream.of(nums).boxed().sorted(((o1, o2) -> Math.abs(o2)-Math.abs(o1)))
            .mapToInt(Integer::intValue).toArray();

    int len = nums.length;

    for (int i=0; i<len; i++){
        // 从前向后遍历(从大到小), 遇到负数将其变为正数, 同时k--
        if (nums[i]<0 && k>0){  // 注意这里有个限制条件为k>0
            nums[i] = -nums[i];
            k--;
        }
    }

    // 如果K还大于0，那么反复转变数值最小的元素，将K用完
    if (k % 2 == 1) nums[len - 1] = -nums[len - 1];  // 注意这一步很重要
    return Arrays.stream(nums).sum();
}
```
:::danger

1. 学习按照绝对值排序的方法
2. 数组中元素求和方法
:::
```java
public int largestSumAfterKNegations2(int[] nums, int k) {
    // 排序, 把可能有的负数排到前面
    Arrays.sort(nums);
    int sum = 0;
    for (int i=0; i< nums.length; i++){
        // 贪心, 如果是负数, 而k还有盈余, 就把负数反过来
        if (nums[i]<0 && k>0){
            nums[i] = -1*nums[i];
            k--;
        }
        sum += nums[i];
    }
    Arrays.sort(nums);  // 注意这里再排序, 为了帮助下面的索引 0 处可以获得最小正数
    // 如果k没剩，那说明能转的负数都转正了，已经是最大和，返回sum
    // 如果k有剩，说明负数已经全部转正，所以如果k还剩偶数个就自己抵消掉，不用删减，如果k还剩奇数个就减掉2倍最小正数。
    return sum - (k%2==0 ? 0 : 2*nums[0]);
}
```
-
<a name="ff3E4"></a>
# 134. 加油站 
:::info
本题有点难度，不太好想，推荐大家熟悉一下方法二 [代码随想录](https://programmercarl.com/0134.%E5%8A%A0%E6%B2%B9%E7%AB%99.html)
:::
本题有难度
<a name="DsCsM"></a>
## 暴力解法
暴力的方法很明显就是![](https://cdn.nlark.com/yuque/__latex/f2d5f588234eb61a559ff90c41511b85.svg#card=math&code=O%28n%5E2%29&id=Z11Gz)的，遍历每一个加油站为起点的情况，模拟一圈。如果跑了一圈，中途没有断油，而且最后油量大于等于0，说明这个起点是ok的。暴力的方法思路比较简单，但代码写起来也不是很容易，关键是要模拟跑一圈的过程。<br />for 循环适合模拟从头到尾的遍历，而 while 循环适合模拟环形遍历，要善于使用 while！
```cpp
class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        
        for (int i = 0; i < cost.size(); i++) {
            
            int rest = gas[i] - cost[i]; // 记录剩余油量
            int index = (i+1) % cost.size();  // 思考这个index的作用???
            
            while (rest > 0 && index != i) { // 模拟以i为起点行驶一圈（如果有rest==0，那么答案就不唯一了）
                rest += gas[index] - cost[index];
                index = (index + 1) % cost.size();
            }
            
            // 如果以i为起点跑一圈，剩余油量>=0，返回该起始位置
            if (rest >= 0 && index == i) return i;
            
        }
        return -1;
    }
};

时间复杂度：O(n^2)
空间复杂度：O(1)
```
<a name="x2jVB"></a>
## 贪心算法
<a name="X6KrV"></a>
### 方法一
直接从全局进行贪心选择，情况如下：

- 情况一：如果gas的总和小于cost总和，那么无论从哪里出发，一定是跑不了一圈的
- 情况二：`rest[i] = gas[i]-cost[i]`为一天剩下的油，i从0开始计算累加到最后一站，如果累加没有出现负数，说明从0出发，油就没有断过，那么0就是起点。
- 情况三：如果累加的最小值是负数，汽车就要从非0节点出发，从后向前，看哪个节点能把这个负数填平，能把这个负数填平的节点就是出发节点。
```cpp
/**
 * 方法一 不是真正意义上的贪心
 * @param gas
 * @param cost
 * @return
 */
public int canCompleteCircuit(int[] gas, int[] cost) {
    int sum = 0;
    int min = 0;
    for (int i = 0; i < gas.length; i++) {
        sum += (gas[i] - cost[i]);  // gas[i] - cost[i] 一天剩下的油
        min = Math.min(sum, min);
    }

    // 仅仅总和大也是不行的, 因为万一第一天油很少不够, 但是后面的都很多富裕就会出现油很多但是从0点开始走走不到
    if (sum<0) return -1;  // 如果gas的总和小于cost总和，那么无论从哪里出发，一定是跑不了一圈的
    if (min >=0) return 0;  // 此时说明从0位置开始走可以回到起点

    for (int i = gas.length-1; i > 0; i--) {
        // 如果累加的最小值是负数，汽车就要从非0节点出发，从后向前，看哪个节点能把这个负数填平，能把这个负数填平的节点就是出发节点。
        min += (gas[i]-cost[i]);
        if (min>=0) return i;
    }

    return -1;
}

时间复杂度：O(n)
空间复杂度：O(1)
```
![过程描述](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687883517529-c6d86879-4c73-47ce-8e2b-4b046ddb3dc3.png#averageHue=%23eae9e5&clientId=u2ab39a1c-808a-4&from=paste&height=632&id=ue7910a5d&originHeight=740&originWidth=2315&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=true&size=131685&status=done&style=none&taskId=ucd3cb121-b0f2-448c-96e9-f3cc05ce617&title=%E8%BF%87%E7%A8%8B%E6%8F%8F%E8%BF%B0&width=1978.632551208288 "过程描述")<br />**其实Carl我不认为这种方式是贪心算法，因为没有找出局部最优，而是直接从全局最优的角度上思考问题**。但这种解法又说不出是什么方法，这就是一个从全局角度选取最优解的模拟操作。所以对于本解法是贪心，我持保留意见！但不管怎么说，解法毕竟还是巧妙的，不用过于执着于其名字称呼
<a name="lkL1X"></a>
### 贪心算法（方法二）
可以换一个思路，首先如果总油量减去总消耗大于等于零那么一定可以跑完一圈，说明 各个站点的加油站 剩油量rest[i]相加一定是大于等于零的。<br />每个加油站的剩余量`rest[i]=gas[i] - cost[i]`。<br />**i 从 0 开始累加 rest[i]，和记为 curSum，一旦 curSum 小于零，说明 [0, i] 区间都不能作为起始位置，因为这个区间选择任何一个位置作为起点，到 i 这里都会断油，那么起始位置从 i+ 1算起，再从 0 计算 curSum**。如图：<br />![20230117165628.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687884479151-3124bd2b-c17f-4786-abd1-426c35da2393.png#averageHue=%23f4f4f4&clientId=u2ab39a1c-808a-4&from=paste&height=662&id=uc8bbeb2a&originHeight=774&originWidth=798&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=59506&status=done&style=none&taskId=u0857b8d5-47dc-494b-a973-ca40b17d66e&title=&width=682.0513070687749)<br />那么为什么一旦 [0，i] 区间和为负数，起始位置就可以是 i+1 呢，i+1后面就不会出现更大的负数？如果出现更大的负数，就是更新i，那么起始位置又变成新的i+1了。<br />那有没有可能 [0，i] 区间 选某一个作为起点，累加到 i 这里 curSum是不会小于零呢？ 如图：<br />![20230117170703.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687884484350-c94f5154-dad8-4cf7-91b8-d2a19a3bf6c9.png#averageHue=%23f3f2f2&clientId=u2ab39a1c-808a-4&from=paste&height=417&id=u401d01e5&originHeight=488&originWidth=728&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=46970&status=done&style=none&taskId=u643e0a61-ea1b-4cb4-b547-c9aa089bfa6&title=&width=622.2222450451982)<br />如果 `curSum<0` 说明 `区间和1 + 区间和2 < 0`， 那么 假设从上图中的位置开始计数curSum不会小于0的话，就是 `区间和2>0`。<br />`区间和1 + 区间和2 < 0` 同时 `区间和2>0`，只能说明`区间和1 < 0`， 那么就会从假设的箭头初就开始从新选择其实位置了。<br />那么**局部最优**：当前累加 rest[i] 的和 curSum 一旦小于0，起始位置至少要是i+1，因为从i之前开始一定不行。**全局最优**：找到可以跑一圈的起始位置。<br />局部最优可以推出全局最优，找不出反例，试试贪心！
```cpp
/**
 * 方法二 贪心
 * @param gas
 * @param cost
 * @return
 */
public int canCompleteCircuit2(int[] gas, int[] cost) {
    int curSum = 0; // 当前总和
    int totalSum = 0; // 所有总和
    int index = 0;  // 指针

    for (int i = 0; i < gas.length; i++) {
        curSum += (gas[i] - cost[i]);
        totalSum += (gas[i] - cost[i]);
        if (curSum<0){
            index = i+1;   // 也可以写: index = (i + 1) % gas.length ; 
            curSum = 0;
        }
    }
    if (totalSum<0) return -1;  // // 如果gas的总和小于cost总和，那么无论从哪里出发，一定是跑不了一圈的
    return index;
}

时间复杂度：O(n)
空间复杂度：O(1)
```
-
<a name="BgYyn"></a>
# 135. 分发糖果 
:::info
本题涉及到一个思想，就是想处理好一边再处理另一边，不要两边想着一起兼顾，后面还会有题目用到这个思路 [代码随想录](https://programmercarl.com/0135.%E5%88%86%E5%8F%91%E7%B3%96%E6%9E%9C.html)
:::
这道题目一定是**要确定一边之后，再确定另一边**，例如比较每一个孩子的左边，然后再比较右边，如果两边一起考虑一定会顾此失彼。<br />先确定右边评分大于左边的情况（也就是从前向后遍历）<br />此时**局部最优**：只要右边评分比左边大，右边的孩子就多一个糖果，**全局最优**：相邻的孩子中，评分高的右孩子获得比左边孩子更多的糖果. 局部最优可以推出全局最优。<br />如果`ratings[i] > ratings[i - 1]` 那么 [i] 的糖 一定要比 [i - 1] 的糖多一个，所以贪心：`candyVec[i] = candyVec[i - 1] + 1`<br />代码如下：
```java
// 从前向后
for (int i = 1; i < ratings.size(); i++) {
    if (ratings[i] > ratings[i - 1]) candyVec[i] = candyVec[i - 1] + 1;
}
```
![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687972420538-7e47c033-cf1e-4264-8f2d-8057145a31b2.png#averageHue=%23f2f2f2&clientId=u66cb8813-575c-4&from=paste&height=396&id=udd8e9da5&originHeight=463&originWidth=1381&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=87911&status=done&style=none&taskId=u3d85c4de-b9a4-4b69-93c1-702b7141774&title=&width=1180.3419236365642)<br />再确定左孩子大于右孩子的情况（**从后向前遍历**）<br />遍历顺序这里有同学可能会有疑问，为什么不能从前向后遍历呢？因为 rating[5]与rating[4] 的比较 要利用上 rating[5]与rating[6] 的比较结果，所以 要从后向前遍历。如果从前向后遍历，rating[5]与rating[4] 的比较 就不能用上 rating[5]与rating[6] 的比较结果了 。如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687972475235-4220ab68-8945-43e0-81c6-58380e2a3c5b.png#averageHue=%23f8f7f7&clientId=u66cb8813-575c-4&from=paste&height=756&id=u7cac3281&originHeight=884&originWidth=1279&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=200219&status=done&style=none&taskId=u664af11f-cc45-40ac-aaef-fb9fbcb4ba0&title=&width=1093.1624332593522)<br />所以确定左孩子大于右孩子的情况一定要从后向前遍历！<br />如果 `ratings[i] > ratings[i + 1]`，此时`candyVec[i]`(第i个小孩的糖果数量）就有两个选择了，一个是`candyVec[i + 1] + 1`(从右边这个加1得到的糖果数量），一个是          `candyVec[i]`(之前比较右孩子大于左孩子得到的糖果数量）。<br />那么又要贪心了，**局部最优**：取`candyVec[i + 1] + 1` 和 `candyVec[i]` 最大的糖果数量，保证第 i 个小孩的糖果数量既大于左边的也大于右边的。全局最优：相邻的孩子中，评分高的孩子获得更多的糖果。          局部最优可以推出全局最优。<br />所以就取`candyVec[i + 1] + 1` 和 `candyVec[i]` 最大的糖果数量，`candyVec[i]`只有取最大的才能既保持对左边`candyVec[i - 1]`的糖果多，也比右边`candyVec[i + 1]`的糖果多。<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687972595902-a1ba5d88-8bf4-4a1b-a10c-c14df627afd0.png#averageHue=%23f4f4f4&clientId=u66cb8813-575c-4&from=paste&height=766&id=u9a407747&originHeight=896&originWidth=1161&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=159113&status=done&style=none&taskId=ua364e803-835e-4c2a-aa07-b2ce178dee3&title=&width=992.3077287053229)
```java
// 从后向前
for (int i = len - 2; i >= 0; i--) {
    if (ratings[i] > ratings[i + 1]) {
        candyVec[i] = Math.max(candyVec[i], candyVec[i + 1] + 1);
    }
}
```
```java
class Solution {
    /**
         分两个阶段
         1、起点下标1 从左往右，只要 右边 比 左边 大，右边的糖果=左边 + 1
         2、起点下标 ratings.length - 2 从右往左， 只要左边 比 右边 大，此时 左边的糖果应该 取本身的糖果数（符合比它左边大） 和 右边糖果数 + 1 二者的最大值，这样才符合 它比它左边的大，也比它右边大
    */
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] candyVec = new int[len];  // 糖果数组

        candyVec[0] = 1;  // 初始化为1, 最少的糖果为1

        for (int i = 1; i < len; i++) {
            candyVec[i] = (ratings[i] > ratings[i - 1]) ? candyVec[i - 1] + 1 : 1;
        }

        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                // 这里第二次反着遍历要取糖果数的最大值
                candyVec[i] = Math.max(candyVec[i], candyVec[i + 1] + 1);
            }
        }

        int ans = 0;
        for (int num : candyVec) {  // 最后要返回消耗的最少糖果数
            ans += num;
        }
        return ans;
    }
}

时间复杂度: O(n)
空间复杂度: O(n)
```
这在leetcode上是一道困难的题目，其难点就在于贪心的策略，如果在考虑局部的时候想两边兼顾，就会顾此失彼。那么本题采用了两次贪心的策略：

- 一次是从左到右遍历，只比较右边孩子评分比左边大的情况。
- 一次是从右到左遍历，只比较左边孩子评分比右边大的情况。

这样从局部最优推出了全局最优，即：相邻的孩子中，评分高的孩子获得更多的糖果
