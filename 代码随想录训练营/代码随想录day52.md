时间: 2023.6.30 周五
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part13, 300.最长递增子序列, 674. 最长连续递增序列, 718. 最长重复子数组  
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [ ] <br />

<a name="ALwd7"></a>
# 300.最长递增子序列
:::info
今天开始正式子序列系列，本题是比较简单的，感受感受一下子序列题目的思路。 <br />视频讲解：[https://www.bilibili.com/video/BV1ng411J7xP](https://www.bilibili.com/video/BV1ng411J7xP)<br />[https://programmercarl.com/0300.%E6%9C%80%E9%95%BF%E4%B8%8A%E5%8D%87%E5%AD%90%E5%BA%8F%E5%88%97.html](https://programmercarl.com/0300.%E6%9C%80%E9%95%BF%E4%B8%8A%E5%8D%87%E5%AD%90%E5%BA%8F%E5%88%97.html)
:::
:::info
中等  ([数组](https://leetcode.cn/tag/array/), [二分查找](https://leetcode.cn/tag/binary-search/), [动态规划](https://leetcode.cn/tag/dynamic-programming/))
:::
首先通过本题大家要明确什么是**子序列**，“子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序”。<br />本题也是代码随想录中子序列问题的第一题，如果没接触过这种题目的话，本题还是很难的，甚至想暴力去搜索也不知道怎么搜。 子序列问题是动态规划解决的经典问题，当前下标`i`的递增子序列长度，其实和`i`之前的下标`j`的子序列长度有关系，那又是什么样的关系呢。<br />接下来，我们依然用动规五部曲来详细分析一波：

1. **dp[i]的定义**

本题中，正确定义dp数组的含义十分重要。<br />dp[i]表示: `i`之前包括`i`的以`nums[i]`结尾的最长递增子序列的长度  (`nums`是输入的整数数组)<br />为什么一定表示"**以nums[i]结尾的最长递增子序**"? 因为我们在做递增比较的时候，如果比较 nums[j] 和 nums[i] 的大小，那么两个递增子序列一定分别以 nums[j]为结尾 和 nums[i]为结尾， 要不然这个比较就没有意义了，不是尾部元素的比较那么如何算递增呢。

2. **状态转移方程**

位置`i`的最长升序子序列等于`j`从`0`到`i-1`各个位置的最长升序子序列`+1`的最大值。<br />所以：`if (nums[i] > nums[j]) {dp[i] = max(dp[i], dp[j] + 1);}`<br />注意这里不是要`dp[i]`与`dp[j] + 1`进行比较，而是我们要取`dp[j] + 1`的最大值。

3. **dp[i]的初始化**

每一个`i`，对应的`dp[i]`（即最长递增子序列）**起始大小至少都是1.**

4. **确定遍历顺序**

`dp[i]` 是有`0`到`i-1`**各个位置的最长递增子序列 推导而来**，那么遍历`i`一定是**从前向后遍历**。<br />`j`其实就是遍历`0`到`i-1`，那么是从前到后，还是从后到前遍历都无所谓，只要吧`0`到`i-1`的元素都遍历了就行了。 所以默认习惯 从前向后遍历。<br />遍历i的循环在外层，遍历j则在内层，代码如下：
```java
for (int i = 1; i < nums.size(); i++) {
    for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
    }
    if (dp[i] > result) result = dp[i]; // 取长的子序列
}
```

5. **举例推导dp数组**

输入[0,1,0,3,2,3], dp数组的变化如下：

| 下标 | 0 | 1 | 2 | 3 | 4 | 5 |  | 最大长度 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| i=1 | 1 | 2 | 1 | 1 | 1 | 1 | dp[1]=2 | res=2 |
| i=2 | 1 | 2 | 1 | 1 | 1 | 1 | dp[2]=1 | res=2 |
| i=3 | 1 | 2 | 1 | 3 | 1 | 1 | dp[3]=3 | res=3 |
| i=4 | 1 | 2 | 1 | 3 | 3 | 1 | dp[4]=3 | res=3 |
| i=5 | 1 | 2 | 1 | 3 | 3 | 4 | dp[5]=4 | res=4 |

**如果代码写出来，但一直AC不了，那么就把dp数组打印出来，看看对不对！**
```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int res = 1;  // 这地方要写1, 让输入长度为1的数组时会直接返回长度1
        Arrays.fill(dp, 1);  // 最小递增子序列长度为1, 先都填充上1

        for (int i=1; i<dp.length; i++){
            for (int j=0; j<i; j++){
                if (nums[i] > nums[j]){
                    // 如果当前i所在的元素值比j所在的元素值大, 则进入判断内部, 比较dp[i]的值和前一个动态数组的j位置的值+1的大小
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}
时间复杂度: O(n^2)
空间复杂度: O(n)
```
本题最关键的是要想到dp[i]由哪些状态可以推出来，并取最大值，那么很自然就能想到递推公式：dp[i] = max(dp[i], dp[j] + 1);<br />子序列问题是动态规划的一个重要系列，本题算是入门题目，好戏刚刚开始
<a name="lQ18f"></a>
# 674. 最长连续递增序列
:::info
本题相对于昨天的动态规划：300.最长递增子序列 最大的区别在于“连续”。 先尝试自己做做，感受一下区别  <br />视频讲解：[https://www.bilibili.com/video/BV1bD4y1778v](https://www.bilibili.com/video/BV1bD4y1778v)<br />[https://programmercarl.com/0674.%E6%9C%80%E9%95%BF%E8%BF%9E%E7%BB%AD%E9%80%92%E5%A2%9E%E5%BA%8F%E5%88%97.html](https://programmercarl.com/0674.%E6%9C%80%E9%95%BF%E8%BF%9E%E7%BB%AD%E9%80%92%E5%A2%9E%E5%BA%8F%E5%88%97.html)
:::
:::info
简单   ([数组](https://leetcode.cn/tag/array/))
:::
本题注意是连续的递增序列
<a name="k49YY"></a>
## 动态规划
动规五部曲分析如下：

1. **确定dp数组（dp table）以及下标的含义**

dp[i]：以下标`i`**为结尾**的连续递增的子序列长度为`dp[i]`。<br />注意这里的定义，**一定是以下标i为结尾**，并不是说一定以下标0为起始位置。

2. **确定递推公式**

**如果**`**nums[i] > nums[i - 1]**`**，那么以**`**i**`**为结尾的连续递增的子序列长度 一定等于 以**`**i - 1**`**为结尾的连续递增的子序列长度**`**+1**`。即：`dp[i] = dp[i - 1] + 1;`<br />注意这里就体现出和动态规划：`300.最长递增子序列` 的区别！<br />因为本题要求连续递增子序列，所以就只要比较`nums[i]`与`nums[i - 1]`，而不用去比较nums[j]与nums[i] （j是在0到i之间遍历）。<br />既然不用j了，那么也不用两层for循环，本题一层for循环就行，比较`nums[i]`和`nums[i - 1]`。<br />这里大家要好好体会一下！

3. **dp数组如何初始化**

以下标`i`为结尾的连续递增的子序列长度最少也应该是`1`，即就是nums[i]这一个元素。所以**dp[i]应该初始化为1**;

4. **确定遍历顺序**

从递推公式上可以看出，`**dp[i + 1]**`**依赖dp[i]，所以一定是从前向后遍历**。<br />本文在确定递推公式的时候也说明了为什么本题只需要一层for循环，代码如下：
```java
for (int i = 1; i < nums.size(); i++) {
    if (nums[i] > nums[i - 1]) { // 连续记录
        dp[i] = dp[i - 1] + 1;
    }
}
```

5. **举例推导dp数组**

已输入nums = [1,3,5,4,7]为例，dp数组状态如下：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1693827503588-9bbc3994-3643-42f2-87a9-56d43ac31c97.png#averageHue=%23f5f5f5&clientId=u0d1254a0-3ac9-4&from=paste&height=192&id=ub9d8ec44&originHeight=256&originWidth=686&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=36235&status=done&style=none&taskId=u08105cc6-180e-4076-b31c-451f70f0f01&title=&width=515)<br />注意这里要取dp[i]里的最大值，所以dp[2]才是结果！
```java
 /**
 * 1.dp[i] 代表当前下标最大连续值
 * 2.递推公式 if（nums[i+1]>nums[i]） dp[i+1] = dp[i]+1
 * 3.初始化 都为1
 * 4.遍历方向，从其那往后
 * 5.结果推导 。。。。
 * @param nums
 * @return
 */
public static int findLengthOfLCIS(int[] nums) {
    int dp[] = new int[nums.length];

    for (int i=0; i<dp.length; i++){
        dp[i] = 1;
    }
    int res = 1; // 返回值初始化为1

    // 这里写i从0开始, 后面比较i+1和i的位置; 如果写i从1开始, 比较i-1和i的位置
    for (int i = 0; i < nums.length - 1; i++) {
        if (nums[i+1] > nums[i]){
            dp[i+1] = dp[i]+1;
        }
        res = Math.max(res, dp[i + 1]);
    }
    return res;
}

时间复杂度：O(n)
空间复杂度：O(n)
```
<a name="UxP88"></a>
## 贪心
这道题目也可以用贪心来做，也就是遇到`nums[i] > nums[i - 1]`的情况，count就++，否则count为1，记录count的最大值就可以了。<br />代码如下：
```java
public static int findLengthOfLCIS(int[] nums) {
    if (nums.length == 0) return 0;
    int res = 1; // 连续子序列最少也是1
    int count = 1;
    for (int i = 0; i < nums.length - 1; i++) {
        if (nums[i + 1] > nums[i]) { // 连续记录
            count++;
        } else { // 不连续，count从头开始
            count = 1;
        }
        if (count > res) res = count;
    }
    return res;
}

时间复杂度：O(n)
空间复杂度：O(1)
```
:::danger
**不连续**递增子序列的跟前0-i 个状态有关，**连续**递增的子序列只跟前一个状态有关
:::
<a name="hFCgw"></a>
# 718. 最长重复子数组
:::info
稍有难度，要使用二维dp数组了<br />视频讲解：[https://www.bilibili.com/video/BV178411H7hV](https://www.bilibili.com/video/BV178411H7hV)<br />[https://programmercarl.com/0718.%E6%9C%80%E9%95%BF%E9%87%8D%E5%A4%8D%E5%AD%90%E6%95%B0%E7%BB%84.html](https://programmercarl.com/0718.%E6%9C%80%E9%95%BF%E9%87%8D%E5%A4%8D%E5%AD%90%E6%95%B0%E7%BB%84.html)
:::
:::info
中等题   ([数组](https://leetcode.cn/tag/array/), [二分查找](https://leetcode.cn/tag/binary-search/), [动态规划](https://leetcode.cn/tag/dynamic-programming/), [滑动窗口](https://leetcode.cn/tag/sliding-window/), [哈希函数](https://leetcode.cn/tag/hash-function/), [滚动哈希](https://leetcode.cn/tag/rolling-hash/))
:::
注意题目中说的子数组，其实就是连续子序列。<br />要求两个数组中最长重复子数组，如果是暴力的解法 只需要先两层for循环确定两个数组起始位置，然后再来一个循环可以是for或者while，来从两个起始位置开始比较，取得重复子数组的长度。
<a name="mS1Cc"></a>
## 二维数组
本题其实是动规解决的经典题目，我们只要想到 用**二维数组**可以记录两个字符串的所有比较情况，这样就比较好推 递推公式了。 动规五部曲分析如下：

1. **确定dp数组（p table）以及下标的含义**

dp[i][j] ：以**下标**`**i-1**`**为结尾的A**，和以下标`j-1`为结尾的B，最长重复子数组长度为`dp[i][j]`.(特别注意: 以下标`i-1`为结尾的A. 标明**一定是以**`**A[i-1]**`**为结尾的字符串**)<br />此时细心的同学应该发现，那**dp[0][0]**是什么含义呢？总不能是以下标-1为结尾的A数组吧? 其实dp[i][j]的定义也就决定着，我们在遍历dp[i][j]的时候**i 和 j都要从1开始**。
> 那有同学问了，我就定义dp[i][j]为 以下标i为结尾的A，和以下标j 为结尾的B，最长重复子数组长度。不行么？
> 行倒是行！ 但实现起来就麻烦一点，需要单独处理初始化部分，在本题解下面的拓展内容里，我给出了 第二种 dp数组的定义方式所对应的代码和讲解，大家比较一下就了解了。

2. **确定递推公式**

根据dp[i][j]的定义，dp[i][j]的状态只能**由**`**dp[i-1][j-1]**`**推导出来**。<br />即当`A[i-1]`和`B[j-1]`相等的时候，`dp[i][j] = dp[i-1][j-1] + 1;`<br />根据递推公式可以看出，遍历i和 j要从1开始!

3. **dp数组如何初始化**

根据dp[i][j]的定义，**dp[i][0] 和dp[0][j]其实都是没有意义的**！但dp[i][0] 和dp[0][j]要初始值，因为 为了方便递归公式`dp[i][j] = dp[i-1][j-1] + 1;`所以**dp[i][0] 和dp[0][j]初始化为0**。<br />举个例子A[0]如果和B[0]相同的话，`dp[1][1] = dp[0][0] + 1`，只有dp[0][0]初始为0，正好符合递推公式逐步累加起来。

4. **确定遍历顺序**

外层for循环遍历A，内层for循环遍历B。<br />那又有同学问了，外层for循环遍历B，内层for循环遍历A。不行么？也行，一样的，我这里就用外层for循环遍历A，内层for循环遍历B了。<br />同时题目要求长度最长的子数组的长度。所以在遍历的时候顺便**把dp[i][j]的最大值记录下来**。<br />代码如下：
```java
for (int i = 1; i <= nums1.size(); i++) {
    for (int j = 1; j <= nums2.size(); j++) {
        if (nums1[i - 1] == nums2[j - 1]) {
            dp[i][j] = dp[i - 1][j - 1] + 1;
        }
        if (dp[i][j] > result) result = dp[i][j];
    }
}
```

5. **举例推导dp数组**

拿示例1中，A: [1,2,3,2,1]，B: [3,2,1,4,7]为例，画一个dp数组的状态变化，如下：<br />![T7F2%~M`%P}~U`C[NIPK97D.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1693832998259-ebdb00b0-d083-4bfa-b81b-35e9dfb72350.png#averageHue=%23eee2ba&clientId=u0d1254a0-3ac9-4&from=paste&height=419&id=u22e867c5&originHeight=1437&originWidth=2403&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=203194&status=done&style=shadow&taskId=u17ace2d3-91bd-419f-b889-7d29e5ab46c&title=&width=700)
```java
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int result = 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        
        for (int i = 1; i < nums1.length + 1; i++) {
            for (int j = 1; j < nums2.length + 1; j++) {
                // 不等于的话直接j++, 进入下次循环
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result;
    }
}

时间复杂度：O(n * m)，n 为A长度，m为B长度
空间复杂度：O(n * m)
```
<a name="VYxuX"></a>
## 一维滚动数组
在如下图中：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1693833657535-d92d419a-c256-474f-82f8-42d0a92ff414.png#averageHue=%23f4f3f3&clientId=u0d1254a0-3ac9-4&from=paste&height=455&id=u63be5511&originHeight=607&originWidth=715&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=138134&status=done&style=shadow&taskId=ud3f54ed7-71a3-413e-bf7e-763f20220fb&title=&width=536)<br />我们可以看出`dp[i][j]`都是由`dp[i-1][j-1]`推出。那么压缩为一维数组，也就是`dp[j]`都是由`dp[j-1]`推出。也就是相当于可以把上一层`dp[i-1][j]`拷贝到下一层   `dp[i][j]`来继续用。此时遍历B数组的时候，就要**从后向前遍历**，这样**避免重复覆盖**。
```java
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length + 1];  
        int result = 0;

        for (int i = 1; i <= nums1.length; i++) {
            // 注意这里是从后向前变量nums2数组, j>0
            for (int j = nums2.length; j > 0; j--) {  
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                } else {
                    dp[j] = 0;  // 思考这里的作用
                }
                result = Math.max(result, dp[j]);
            }
        }
        return result;
    }
}

时间复杂度：O(n × m)，n 为nums1长度，m为nums2长度
空间复杂度：O(m)  m为nums2长度
```
<a name="St8Hk"></a>
## 拓展
[代码随想录](https://programmercarl.com/0718.%E6%9C%80%E9%95%BF%E9%87%8D%E5%A4%8D%E5%AD%90%E6%95%B0%E7%BB%84.html#%E6%80%9D%E8%B7%AF)<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1693834299652-6c17ff00-2502-4e10-b3f1-cc59f2f9c34d.png#averageHue=%23fefcfb&clientId=u0d1254a0-3ac9-4&from=paste&height=307&id=u8e2ea87f&originHeight=359&originWidth=840&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=37538&status=done&style=shadow&taskId=ubc597c25-bea6-4331-9919-92fa73453a7&title=&width=717.948744282921)
