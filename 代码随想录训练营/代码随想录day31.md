时间: 2023.6.9周五
<a name="sPFyY"></a>
# 今日任务
第八章 贪心算法 part01, 理论基础 , 455.分发饼干 , 376. 摆动序列 . 53. 最大子序和 
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 376. 摆动序列  的动态规划解法没看懂
- [ ] 455.分发饼干  两种不同的遍历判断方式
<a name="XBsr0"></a>
# 复习

- [ ] 学习Map, Set知识点, 然后继续理解 day30__332.重新安排行程


:::info
**贪心算法其实就是没有什么规律可言**，所以大家了解贪心算法 就了解它没有规律的本质就够了。 <br />不用花心思去研究其规律， 没有思路就立刻看题解。<br />基本贪心的题目 有两个极端，要不就是特简单，要不就是死活想不出来。  <br />学完贪心之后再去看动态规划，就会了解贪心和动规的区别。
:::
<a name="kebm3"></a>
# 理论基础 
:::info
[https://programmercarl.com/%E8%B4%AA%E5%BF%83%E7%AE%97%E6%B3%95%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html](https://programmercarl.com/%E8%B4%AA%E5%BF%83%E7%AE%97%E6%B3%95%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)
:::

<a name="O4rKA"></a>
# 455.分发饼干  
:::info
[https://programmercarl.com/0455.%E5%88%86%E5%8F%91%E9%A5%BC%E5%B9%B2.html](https://programmercarl.com/0455.%E5%88%86%E5%8F%91%E9%A5%BC%E5%B9%B2.html)
:::
为了满足更多的小孩，就不要造成饼干尺寸的浪费。大尺寸的饼干既可以满足胃口大的孩子也可以满足胃口小的孩子，那么就应该优先满足胃口大的。<br />这里的**局部最优**就是大饼干喂给胃口大的，充分利用饼干尺寸喂饱一个，**全局最优**就是喂饱尽可能多的小孩。可以尝试使用贪心策略，先将饼干数组和小孩数组排序。然后从后向前遍历小孩数组，用大饼干优先满足胃口大的，并统计满足小孩数量。<br />![20230405225628.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687352067662-128768b8-114f-45bd-9934-79201f0dd41a.png#averageHue=%23f5f4f3&clientId=uc89eaf72-24d6-4&from=paste&height=735&id=ua5ef1ec1&originHeight=860&originWidth=1610&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=142968&status=done&style=none&taskId=uffd4b292-55cc-4c28-a7f4-f17ec19ca98&title=&width=1376.0684265422651)<br />这个例子可以看出饼干 9 只有喂给胃口为 7 的小孩，这样才是整体最优解，并想不出反例，那么就可以撸代码了。
```java
class Solution {
    // 思路2：优先考虑胃口，先喂饱大胃口
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int start = s.length - 1;
        // 遍历胃口
        for (int index = g.length - 1; index >= 0; index--) {
            if(start >= 0 && g[index] <= s[start]) {
                start--;
                count++;
            }
        }
        return count;
    }
}
```
> 时间复杂度![](https://cdn.nlark.com/yuque/__latex/9a4b19897e03daeb7e8beeecf02e82cc.svg#card=math&code=O%28nlogn%29&id=meTRU)
> 空间复杂度![](https://cdn.nlark.com/yuque/__latex/a2006f1ac61cb1902beacb3e29fff089.svg#card=math&code=O%281%29&id=GzS9q)

思路二中, 使用start来控制饼干数组的遍历, 遍历饼干并没有再起一个for循环, 而是采用自减的方式, 这也是常用的技巧, 有的同学看到要遍历两个数组，就想到用两个 for 循环，那样逻辑其实就复杂了<br />注意在 思路二中优先考虑胃口, 先喂饱大胃口 , 这时**不可以先遍历饼干, 再遍历胃口**. 外面的 for 是里的下标 i 是固定移动的，而 if 里面的下标 start 是符合条件才移动的。如果 for 控制的是饼干， if 控制胃口，就是出现如下情况 ：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687353257334-0f5da106-810e-4516-b829-6b6b54bf5ab9.png#averageHue=%23f4f4f4&clientId=uec854012-4531-4&from=paste&height=573&id=udb774999&originHeight=670&originWidth=788&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=46124&status=done&style=none&taskId=u6bd7a851-ae63-4226-a59c-ec84c5a295f&title=&width=673.5042982082639)<br />if 里的 index 指向 胃口 10， for 里的 i 指向饼干 9，因为 饼干 9 满足不了 胃口 10，所以 i 持续向前移动，而 index 走不到s[index] >= g[i] 的逻辑，所以 index 不会移动，那么当 i 持续向前移动，最后所有的饼干都匹配不上。所以 一定要 for 控制 胃口，里面的 if 控制饼干<br />也可以换一个思路，小饼干先喂饱小胃口
```java
// 思路1：优先考虑饼干，小饼干先喂饱小胃口
public int findContentChildren(int[] g, int[] s) { // g是胃口, s是饼干
    Arrays.sort(g);
    Arrays.sort(s);

    int start = 0 , count = 0;  // count用来记录得到满足的小孩个数
	// 遍历饼干
    for (int i=0; i<s.length && start<g.length; i++){
        if (s[i] >= g[start]){
            start++;
            count++;
        }
    }
    return count;
}
```
-
<a name="GCRQH"></a>
# 376. 摆动序列  
:::info
[https://programmercarl.com/0376.%E6%91%86%E5%8A%A8%E5%BA%8F%E5%88%97.html](https://programmercarl.com/0376.%E6%91%86%E5%8A%A8%E5%BA%8F%E5%88%97.html)
:::
<a name="D6Ae0"></a>
## 思路1(贪心)
本题要求通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。<br />相信这么一说吓退不少同学，这要求最大摆动序列又可以修改数组，这得如何修改呢？来分析一下，要求删除元素使其达到最大摆动序列，应该删除什么元素呢？<br />用示例二来举例，如图所示：<br />![20201124174327597.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687354212836-7cec5832-1296-4b7e-9176-0bce564ed803.png#averageHue=%23f9f8f8&clientId=uec854012-4531-4&from=paste&height=429&id=u7bbbee3c&originHeight=502&originWidth=1300&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=75285&status=done&style=none&taskId=udbcc7d2c-b4cc-48e1-aeaf-2900714c15f&title=&width=1111.1111518664252)<br />**局部最优**：删除单调坡度上的节点（不包括单调坡度两端的节点），那么这个坡度就可以有两个局部峰值。<br />**整体最优**：整个序列有最多的局部峰值，从而达到最长摆动序列。<br />局部最优推出全局最优，并举不出反例，那么试试贪心！（为方便表述，以下说的峰值都是指局部峰值）<br />实际操作上，其实连删除的操作都不用做，因为题目要求的是最长摆动子序列的长度，所以只需要统计数组的峰值数量就可以了（相当于是删除单一坡度上的节点，然后统计长度）<br />这就是贪心所贪的地方，让峰值尽可能的保持峰值，然后删除单一坡度上的节点<br />在计算是否有峰值的时候，大家知道遍历的下标 i ，计算 `prediff（nums[i] - nums[i-1]）` 和 `curdiff（nums[i+1] - nums[i]）`，如果`prediff < 0 && curdiff > 0` 或者 `prediff > 0 && curdiff < 0` 此时就有波动就需要统计。<br />这是我们思考本题的一个大题思路，但本题要考虑三种情况：<br />情况一：上下坡中有平坡,  情况二：数组首尾两端,  情况三：单调坡中有平坡
<a name="urFXc"></a>
### 情况一: 上下坡中有平坡
例如 [1,2,2,2,1]这样的数组，如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687354468744-71e2b94e-d068-4888-b209-0e5a9a7ea677.png#averageHue=%23f7f7f7&clientId=uec854012-4531-4&from=paste&height=152&id=u034b9f7c&originHeight=178&originWidth=724&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=14687&status=done&style=none&taskId=u044ccb64-f2ae-4ca4-9c7a-87cb5dcd445&title=&width=618.8034415009938)<br />它的摇摆序列长度是多少呢？ 其实是长度是 3，也就是我们在删除的时候 要么删除左面的三个 2，要么就删除右边的三个 2。如下图，可以统一规则，删除左边的三个 2：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687354515999-855e925c-39ba-4080-81cb-56c41bc4a4b9.png#averageHue=%23f7f6f6&clientId=uec854012-4531-4&from=paste&height=396&id=ud903d7b7&originHeight=463&originWidth=764&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=58985&status=done&style=none&taskId=ucac529ab-9bc5-45ae-a669-5a6bd5d6669&title=&width=652.9914769430376)<br />在图中，当 i 指向第一个 2 的时候，`prediff > 0 && curdiff = 0`，当 i 指向最后一个 2 的时候 `prediff = 0 && curdiff < 0`。<br />如果我们采用，删左面三个 2 的规则，那么 当 `prediff = 0 && curdiff < 0` 也要记录一个峰值，因为他是把之前相同的元素都删掉留下的峰值。<br />所以我们记录峰值的条件应该是： `(preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0)`，为什么这里允许 `prediff == 0` ，就是为了 上面说的这种情况
<a name="nAvbW"></a>
### 情况二: 数组首位两端
本题统计峰值的时候，数组最左面和最右面如何统计呢？题目中说了，如果只有两个不同的元素，那摆动序列也是 2。<br />例如序列[2,5]，如果靠统计差值来计算峰值个数就需要考虑数组最左面和最右面的特殊情况。<br />因为我们在计算 `prediff（nums[i] - nums[i-1]）` 和 `curdiff（nums[i+1] - nums[i]）`的时候，至少需要三个数字才能计算，而数组只有两个数字。这里我们可以写死，就是 如果只有两个元素，且元素不同，那么结果为 2。<br />不写死的话，如何和我们的判断规则结合在一起呢？可以假设，数组最前面还有一个数字，那这个数字应该是什么呢？之前我们在 讨论 **情况一：相同数字连续** 的时候， prediff = 0 ，curdiff < 0 或者 >0 也记为波谷。那么为了规则统一，针对序列[2,5]，可以假设为[2,2,5]，这样它就有坡度了即 preDiff = 0，如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687355315329-2b85bc26-607e-452d-a1b5-4fc1cba602e1.png#averageHue=%23f8f8f8&clientId=uec854012-4531-4&from=paste&height=185&id=u43645592&originHeight=217&originWidth=519&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=18765&status=done&style=none&taskId=u5d317580-c587-451a-af43-ba5feff7df8&title=&width=443.589759860519)<br />针对以上情形，result 初始为 1（默认最右面有一个峰值），此时 `curDiff > 0 && preDiff <= 0`，那么 result++（计算了左面的峰值），最后得到的 result 就是 2（峰值个数为 2 即摆动序列长度为 2）经过以上分析后，我们可以写出如下代码：
```java
public int wiggleMaxLength(int[] nums) {
    if (nums.length <= 1) return nums.length;

    int curDiff = 0; // 当前一对差值
    int preDiff = 0; // 前一对差值
    int result = 1;  // 记录峰值个数，序列默认序列最右边有一个峰值

    for (int i=0; i<nums.length-1; i++){
        curDiff = nums[i+1] - nums[i];
        // 出现峰值
        if ((preDiff<=0 && curDiff>0) || (preDiff>=0 && curDiff<0)){
            result++;
        }
        preDiff = curDiff;
    }
    return result;
}
```
上面这块代码是不能通过本题的, 所以还要讨论情况三
<a name="U7KdA"></a>
### 情况三：单调坡度有平坡
在上面那块代码中，我们忽略了一种情况，即 如果在一个单调坡度上有平坡，例如[1,2,2,2,3,4]，如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687356322799-29f6cf68-f69b-4927-aab3-b2c4edeb03ba.png#averageHue=%23f6f5f5&clientId=uec854012-4531-4&from=paste&height=294&id=uc0aabec5&originHeight=344&originWidth=739&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=51867&status=done&style=none&taskId=u09fbf452-5504-4122-b477-0715e95153f&title=&width=631.6239547917602)<br />图中，我们可以看出，版本一的代码在三个地方记录峰值，但其实结果因为是 2，因为 单调中的平坡 不能算峰值（即摆动）。**之所以版本一会出问题，是因为我们实时更新了 prediff**。<br />那么我们应该什么时候更新 prediff 呢？我们**只需要在 这个坡度 摆动变化的时候，更新 prediff 就行**，**这样 prediff 在 单调区间有平坡的时候 就不会发生变化，造成我们的误判**。<br />所以本题的最终代码为：
```java
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        //当前差值
        int curDiff = 0;
        //上一个差值
        int preDiff = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            //得到当前差值
            curDiff = nums[i] - nums[i - 1];
            //如果当前差值和上一个差值为一正一负
            //等于0的情况表示初始时的preDiff
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                count++;
                preDiff = curDiff;
            }
        }
        return count;
    }
}
```
本题看起来好像简单，但需要考虑的情况还是很复杂的，而且很难一次性想到位。<br />本题异常情况的本质，就是要考虑平坡， 平坡分两种，一个是 上下中间有平坡，一个是单调有平坡，如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687356513424-cdcb8911-a15c-43d3-bd0b-cf57ca8bf69c.png#averageHue=%23f8f8f8&clientId=uec854012-4531-4&from=paste&height=393&id=ua2aadbf7&originHeight=460&originWidth=749&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=44984&status=done&style=none&taskId=uba542c75-26a3-4253-a9e8-f3f7468494b&title=&width=640.1709636522712)
<a name="nI0Jm"></a>
## 思路2(动态规划)
考虑用动态规划的思想来解决这个问题。<br />很容易可以发现，对于我们当前考虑的这个数，要么是作为山峰（即`nums[i] > nums[i-1]`），要么是作为山谷（即`nums[i] < nums[i - 1]`）。

- 设 dp 状态`dp[i][0]`，表示考虑前 i 个数，第 i 个数作为**山峰**的摆动子序列的最长长度
- 设 dp 状态`dp[i][1]`，表示考虑前 i 个数，第 i 个数作为**山谷**的摆动子序列的最长长度

则转移方程为：

- `dp[i][0] = max(dp[i][0], dp[j][1] + 1)`，其中`0 < j < i 且 nums[j] < nums[i]`，表示将 nums[i] 接到前面某个山谷后面，作为**山峰**。
- `dp[i][1] = max(dp[i][1], dp[j][0] + 1)`，其中`0 < j < i 且 nums[j] > nums[i]`，表示将 nums[i] 接到前面某个山峰后面，作为**山谷**。

初始状态：由于一个数可以接到前面的某个数后面，也可以以自身为子序列的起点，所以初始状态为：`dp[0][0] = dp[0][1] = 1`。
```java
// DP
class Solution {
    public int wiggleMaxLength(int[] nums) {
        // 0 i 作为波峰的最大长度
        // 1 i 作为波谷的最大长度
        int dp[][] = new int[nums.length][2];

        dp[0][0] = dp[0][1] = 1;
        for (int i = 1; i < nums.length; i++){
            //i 自己可以成为波峰或者波谷
            dp[i][0] = dp[i][1] = 1;

            for (int j = 0; j < i; j++){
                if (nums[j] > nums[i]){
                    // i 是波谷
                    dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
                }
                if (nums[j] < nums[i]){
                    // i 是波峰
                    dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
                }
            }
        }
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }
}
```
时间复杂度：O(n^2)<br />空间复杂度：O(n)
<a name="EEsQQ"></a>
# 53. 最大子序和  
:::info
[https://programmercarl.com/0053.%E6%9C%80%E5%A4%A7%E5%AD%90%E5%BA%8F%E5%92%8C.html](https://programmercarl.com/0053.%E6%9C%80%E5%A4%A7%E5%AD%90%E5%BA%8F%E5%92%8C.html)
:::
<a name="T0Na9"></a>
## 暴力解法
暴力解法的思路，第一层 for 就是设置起始位置，第二层 for 循环遍历数组寻找最大值
```cpp
// 暴力的解法 C++勉强可以过，其他语言就不确定了
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int result = INT32_MIN;
        int count = 0;
        for (int i = 0; i < nums.size(); i++) { // 设置起始位置
            count = 0;
            for (int j = i; j < nums.size(); j++) { // 每次从起始位置i开始遍历寻找最大值
                count += nums[j];
                result = count > result ? count : result;
            }
        }
        return result;
    }
};
时间复杂度：O(n^2)
空间复杂度：O(1)
```
<a name="GMMvH"></a>
## 贪心解法
输入 : [-2,1,-3,4,-1,2,1,-5,4]     输出: 6              解释:  连续子数组  [4,-1,2,1] 的和最大，为  6

贪心贪的是哪里呢？如果 -2 1 在一起，计算起点的时候，一定是从 1 开始计算，因为负数只会拉低总和，这就是贪心贪的地方！

- **局部最优**：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，因为负数加上下一个元素 “连续和”只会越来越小。
- **全局最优**：选取最大“连续和”

局部最优的情况下，并记录最大的“连续和”，可以推出全局最优。<br />从代码角度上来讲：遍历 nums，从头开始用 count 累积，如果 count 一旦加上 nums[i] 变为负数，那么就应该从 nums[i+1] 开始从 0 累积 count 了，因为已经变为负数的 count，只会拖累总和。这相当于是暴力解法中的不断调整最大子序和区间的起始位置。<br />那有同学问了，区间终止位置不用调整么？ 如何才能得到最大“连续和”呢？区间的终止位置，其实就是如果 count 取到最大值了，及时记录下来了。例如如下代码：
```java
if (count > result) result = count;
```
这样相当于是用 result 记录最大子序和区间和（变相的算是调整了终止位置）。如动画所示：<br />![53.最大子序和.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1687362118592-07aee6cd-61b2-425f-9cf3-2db296114dc1.gif#averageHue=%237c7c7c&clientId=uec854012-4531-4&from=paste&height=275&id=uf7ba1e01&originHeight=322&originWidth=416&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=1789593&status=done&style=none&taskId=u9a2833f6-16b0-4459-9d21-ac480d4b3e1&title=&width=355.55556859725607)<br />红色的起始位置就是贪心每次取 count 为正数的时候，开始一个区间的统计。
```java
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int sum = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            count += nums[i];
            sum = Math.max(sum, count); // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            if (count <= 0){
                count = 0; // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            }
        }
       return sum;
    }
}
时间复杂度：O(n)
空间复杂度：O(1)
```
当然题目没有说如果数组为空，应该返回什么，所以数组为空的话返回啥都可以了
<a name="CVsti"></a>
## 常见误区
**误区一**：<br />不少同学认为 如果输入用例都是-1，或者 都是负数，这个贪心算法跑出来的结果是 0， 这是又一次证明脑洞模拟不靠谱的经典案例，建议大家把代码运行一下试一试，就知道了，也会理解 为什么 result 要初始化为最小负数了。<br />![B426F27E08AC7032FBE6D46EA1F3A8EE.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/32832913/1687399589239-d0088e33-6b57-4125-8e35-bb5e5b9cc20b.jpeg#averageHue=%23f1f0ed&clientId=uc6de5e01-9acd-4&from=paste&height=1255&id=uc946207e&originHeight=1688&originWidth=912&originalType=binary&ratio=1.1699999570846558&rotation=90&showTitle=false&size=59958&status=done&style=none&taskId=u9fca69fb-8e38-4781-acbd-0be60bc9b9c&title=&width=678)<br />**误区二**：<br />大家在使用贪心算法求解本题，经常陷入的误区，就是分不清，是遇到 负数就选择起始位置，还是连续和为负选择起始位置。<br />在动画演示用，大家可以发现， 4，遇到 -1 的时候，我们依然累加了，为什么呢？因为和为 3，只要连续和还是正数就会 对后面的元素 起到增大总和的作用。 所以只要连续和为正数我们就保留。<br />这里也会有录友疑惑，那 4 + -1 之后 不就变小了吗？ 会不会错过 4 成为最大连续和的可能性？其实并不会，因为还有一个变量 result 一直在更新 最大的连续和，只要有更大的连续和出现，result 就更新了，那么 result 已经把 4 更新了，后面 连续和变成 3，也不会对最后结果有影响
<a name="E6oIB"></a>
## 动态规划
当然本题还可以用动态规划来做，在代码随想录动态规划章节我会详细介绍，如果大家想在想看，可以直接跳转
```java
// DP 方法
class Solution {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        ans = dp[0];

        for (int i = 1; i < nums.length; i++){
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            ans = Math.max(dp[i], ans);
        }

        return ans;
    }
}
```
-

