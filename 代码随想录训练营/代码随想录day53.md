时间: 2023.7.1 周六
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part14, 1143.最长公共子序列, 1035.不相交的线, 53. 最大子序和  动态规划 
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [ ] <br />

<a name="xKBvy"></a>
# 1143.最长公共子序列
:::info
体会一下本题和 718. 最长重复子数组 的区别  <br />视频讲解：[https://www.bilibili.com/video/BV1ye4y1L7CQ](https://www.bilibili.com/video/BV1ye4y1L7CQ)<br />[https://programmercarl.com/1143.%E6%9C%80%E9%95%BF%E5%85%AC%E5%85%B1%E5%AD%90%E5%BA%8F%E5%88%97.html](https://programmercarl.com/1143.%E6%9C%80%E9%95%BF%E5%85%AC%E5%85%B1%E5%AD%90%E5%BA%8F%E5%88%97.html)
:::
:::info
中等  ([字符串](https://leetcode.cn/tag/string/), [动态规划](https://leetcode.cn/tag/dynamic-programming/))
:::
本题和动态规划：`718. 最长重复子数组`区别在于这里不要求是连续的了，但要有相对顺序，即："ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。<br />继续动规五部曲分析如下：

1. **确定dp数组（dp table）以及下标的含义**

dp[i][j]：长度为`[0, i - 1]`的字符串text1 与 长度为`[0, j - 1]`的字符串text2的最长公共子序列为dp[i][j]<br />有同学会问：为什么要定义长度为[0, i - 1]的字符串text1，定义为长度为[0, i]的字符串text1不香么？这样定义是为了后面代码实现方便，如果非要定义为长度为[0, i]的字符串text1也可以，在动态规划：`718. 最长重复子数组`中的「拓展」里 详细讲解了区别所在，其实就是简化了dp数组第一行和第一列的初始化逻辑。

2. **确定递推公式**

主要就是两大情况： `text1[i - 1]` 与 `text2[j - 1]`相同，`text1[i - 1]` 与 `text2[j - 1]`不相同

   - 如果`text1[i - 1]` 与 `text2[j - 1]`相同，那么找到了一个公共元素，所以`dp[i][j] = dp[i - 1][j - 1] + 1;`
   - 如果`text1[i - 1]`与`text2[j - 1]`不相同, 那就看看`**text1[0, i - 2]**`**与**`**text2[0, j - 1]**`**的最长公共子序列 和**`**text1[0, i-1]**`**与**`**text2[0, j - 2]**`**的最长公共子序列，取最大的。即：**`**dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);**`

代码如下：
```java
if (text1[i - 1] == text2[j - 1]) {
    dp[i][j] = dp[i - 1][j - 1] + 1;
} else {
    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
}
```

3. **dp数组如何初始化**

先看看dp[i][0]应该是多少呢？<br />test1[0, i-1]和空串的最长公共子序列自然是0，所以dp[i][0] = 0;<br />同理dp[0][j]也是0。<br />其他下标都是随着递推公式逐步覆盖，初始为多少都可以，那么就统一初始为0。<br />代码：
```java
vector<vector<int>> dp(text1.size() + 1, vector<int>(text2.size() + 1, 0));
```

4. **确定遍历顺序**

从递推公式，可以看出，有三个方向可以推出dp[i][j]，如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1693968209167-8b516a7c-7c0a-46b7-b6af-d009e97644b5.png#averageHue=%23ececec&clientId=u21a5e4a3-27b3-4&from=paste&height=284&id=vQEhY&originHeight=378&originWidth=474&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=47999&status=done&style=shadow&taskId=u8905822f-e63c-4fdd-81ca-71e064521dd&title=&width=356)<br />那么为了在递推的过程中，这三个方向都是经过计算的数值，所以要从前向后，从上到下来遍历这个矩阵。

5. 举例推导dp数组

以输入：text1 = "abcde", text2 = "ace" 为例，dp状态如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1693968237586-800d9661-0f79-4a25-b6c4-14aeac8e7f2b.png#averageHue=%23f1f1f1&clientId=u21a5e4a3-27b3-4&from=paste&height=487&id=hgHvd&originHeight=648&originWidth=626&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=83442&status=done&style=shadow&taskId=ucef181bb-c8fd-4ed2-9892-675ff9752a2&title=&width=470)<br />最后红框`dp[text1.size()][text2.size()]`为最终结果
```java
/*
	二维dp数组
*/
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // char[] char1 = text1.toCharArray();
        // char[] char2 = text2.toCharArray();
        // 可以在一开始的时候就先把text1, text2 转成char[]，之后就不需要有这么多为了处理字符串的调整就可以和卡哥的code更一致

        int[][] dp = new int[text1.length()+1][text2.length()+1]; // // 先对dp数组做初始化操作

        for (int i = 1; i < text1.length()+1; i++) {  // i=1开始
            char char1 = text1.charAt(i-1);
            for (int j=1; j<text2.length()+1; j++){
                char char2 = text2.charAt(j-1);

                // 开始列出状态转移方程
                if (char1 == char2){  // String与String值相等比较用equals()方法，char与char值相等比较用”==”进行比较。
                    dp[i][j] = dp[i-1][j-1]+1; 
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}

时间复杂度: O(n * m)，其中 n 和 m 分别为 text1 和 text2 的长度
空间复杂度: O(n * m)
```
```java
/**
	一维dp数组
*/
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();

        // 多从二维dp数组过程分析  
        // 关键在于  如果记录  dp[i - 1][j - 1]
        // 因为 dp[i - 1][j - 1]  <!=>  dp[j - 1]  <=>  dp[i][j - 1]
        int [] dp = new int[n2 + 1];

        for(int i = 1; i <= n1; i++){

            // 这里pre相当于 dp[i - 1][j - 1]
            int pre = dp[0];
            for(int j = 1; j <= n2; j++){

                //用于给pre赋值
                int cur = dp[j];
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    //这里pre相当于dp[i - 1][j - 1]   千万不能用dp[j - 1] !!
                    dp[j] = pre + 1;
                } else{
                    // dp[j]     相当于   dp[i - 1][j]
                    // dp[j - 1] 相当于   dp[i][j - 1]
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }

                //更新dp[i - 1][j - 1], 为下次使用做准备
                pre = cur;
            }
        }

        return dp[n2];
    }
}
```
-
<a name="lBnys"></a>
# 1035.不相交的线 
:::info
其实本题和 1143.最长公共子序列 是一模一样的，大家尝试自己做一做。<br />视频讲解：[https://www.bilibili.com/video/BV1h84y1x7MP](https://www.bilibili.com/video/BV1h84y1x7MP)<br />[https://programmercarl.com/1035.%E4%B8%8D%E7%9B%B8%E4%BA%A4%E7%9A%84%E7%BA%BF.html](https://programmercarl.com/1035.%E4%B8%8D%E7%9B%B8%E4%BA%A4%E7%9A%84%E7%BA%BF.html)
:::
:::info
中等  ([数组](https://leetcode.cn/tag/array/), [动态规划](https://leetcode.cn/tag/dynamic-programming/))
:::
相信不少录友看到这道题目都没啥思路，我们来逐步分析一下。<br />绘制一些连接两个数字 A[i] 和 B[j] 的直线，只要 A[i] == B[j]，且直线不能相交！<br />直线不能相交，这就是说明在字符串A中 找到一个与字符串B相同的子序列，且这个子序列不能改变相对顺序，只要相对顺序不改变，链接相同数字的直线就不会相交。<br />拿示例一A = [1,4,2], B = [1,2,4]为例，相交情况如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1693971138038-447ba428-40b8-4cf8-9d39-4355e377efbe.png#averageHue=%23f5f5f5&clientId=ufd6b8e67-99ca-4&from=paste&height=345&id=u60e9bd42&originHeight=404&originWidth=686&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=54276&status=done&style=shadow&taskId=ua3103940-4223-45be-88c7-035ba03c460&title=&width=586.3248078310521)<br />其实也就是说A和B的**最长公共子序列**是[1,4]，长度为2。 这个**公共子序列指的是相对顺序不变**（即数字4在**字符串A**中数字1的后面，那么数字4也应该在**字符串B**数字1的后面）<br />这么分析完之后，大家可以发现：**本题说是求绘制的最大连线数，其实就是求两个字符串的最长公共子序列的长度**！<br />那么本题就和我们刚刚讲过的这道题目动态规划：`1143.最长公共子序列`就是一样一样的了。<br />一样到什么程度呢？ 把字符串名字改一下，其他代码都不用改，直接copy过来就行了。<br />其实本题就是求最长公共子序列的长度，介于我们刚刚讲过动态规划：`1143.最长公共子序列`，所以本题我就不再做动规五部曲分析了。<br />如果大家有点遗忘了最长公共子序列，就再看一下这篇：动态规划：`1143.最长公共子序列`<br />本题代码如下：
```java
 class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[len1][len2];
    }
}
时间复杂度: O(n * m)，其中 n 和 m 分别为 text1 和 text2 的长度
空间复杂度: O(n * m)
```

<a name="Zo6p6"></a>
# 53. 最大子序和
:::info
这道题我们用贪心做过，这次 再用dp来做一遍 <br />视频讲解：[https://www.bilibili.com/video/BV19V4y1F7b5](https://www.bilibili.com/video/BV19V4y1F7b5)<br />[https://programmercarl.com/0053.%E6%9C%80%E5%A4%A7%E5%AD%90%E5%BA%8F%E5%92%8C%EF%BC%88%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%EF%BC%89.html](https://programmercarl.com/0053.%E6%9C%80%E5%A4%A7%E5%AD%90%E5%BA%8F%E5%92%8C%EF%BC%88%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%EF%BC%89.html)
:::
:::info
中等题  ([数组](https://leetcode.cn/tag/array/), [分治](https://leetcode.cn/tag/divide-and-conquer/), [动态规划](https://leetcode.cn/tag/dynamic-programming/))<br />进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 **分治法 **求解。
:::
这道题之前我们在讲解贪心专题的时候用**贪心算法解决过一次**，贪心算法：`最大子序和`。这次我们用动态规划的思路再来分析一次。<br />动规五部曲如下：

1. **确定dp数组（dp table）以及下标的含义**

dp[i]：包括下标i（以nums[i]为结尾）的最大连续子序列和为dp[i]。

2. **确定递推公式**

dp[i]只有两个方向可以推出来：

   - `dp[i - 1] + nums[i]`，即：nums[i]加入当前连续子序列和
   - `nums[i]`，即：从头开始计算当前连续子序列和

一定是取最大的，所以`dp[i] = max(dp[i - 1] + nums[i], nums[i]);`

3. **dp数组如何初始化**

从递推公式可以看出来dp[i]是依赖于dp[i - 1]的状态，dp[0]就是递推公式的基础。<br />dp[0]应该是多少呢? 根据dp[i]的定义，很明显dp[0]应为nums[0]即`dp[0] = nums[0]`

4. **确定遍历顺序**

递推公式中dp[i]依赖于dp[i - 1]的状态，需要从前向后遍历。<br />举例推导dp数组<br />以示例一为例，输入：nums = [-2,1,-3,4,-1,2,1,-5,4]，对应的dp状态如下：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1693972090032-40277cbe-6abf-4d8d-b0fa-9194710c209f.png#averageHue=%23f3f3f3&clientId=ufd6b8e67-99ca-4&from=paste&height=200&id=u4d51c763&originHeight=234&originWidth=727&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=26898&status=done&style=shadow&taskId=u535d8fc1-8bfe-4c81-bc6a-53661c72299&title=&width=621.3675441591471)<br />**注意**最后的结果可不是dp[nums.size() - 1]！ ，而是dp[6]。<br />在回顾一下dp[i]的定义：包括下标i之前的最大连续子序列和为dp[i]。<br />那么我们要找最大的连续子序列，就应该找每一个i为终点的连续最大子序列。<br />所以在递推公式的时候，可以直接选出最大的dp[i]。<br />以上动规五部曲分析完毕，完整代码如下：
```java
  /**
 * 1.dp[i]代表当前下标对应的最大值
 * 2.递推公式 dp[i] = max (dp[i-1]+nums[i],nums[i]) res = max(res,dp[i])
 * 3.初始化 都为 0
 * 4.遍历方向，从前往后
 * 5.举例推导结果。。。
 *
 * @param nums
 * @return
 */
public static int maxSubArray2(int[] nums) {
    if (nums.length == 0) {
        return 0;
    }

    int res = nums[0];
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    // i=1 开始
    for (int i = 1; i < nums.length; i++) {
        dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);  // 注意和本身是否最大也要做个比较

        res = Math.max(res, dp[i]);
    }
    return res;
}

时间复杂度：O(n)
空间复杂度：O(n)
```
```java
//因为dp[i]的递推公式只与前一个值有关，所以可以用一个变量代替dp数组，空间复杂度为O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int pre = nums[0];
        for(int i = 1; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            res = Math.max(res, pre);
        }
        return res;
    }
}
```
-
