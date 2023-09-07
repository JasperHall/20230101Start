时间: 2023.7.3 周一
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part15, 392.判断子序列, 115.不同的子序列  
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [ ] <br />

<a name="SP3bV"></a>
# 392.判断子序列
:::info
这道题目算是 编辑距离问题 的入门题目（毕竟这里只是涉及到减法），慢慢的，后面就要来解决真正的 编辑距离问题了<br />[https://programmercarl.com/0392.%E5%88%A4%E6%96%AD%E5%AD%90%E5%BA%8F%E5%88%97.html](https://programmercarl.com/0392.%E5%88%A4%E6%96%AD%E5%AD%90%E5%BA%8F%E5%88%97.html)
:::
<a name="k7642"></a>
## 双指针
```java
public boolean isSubsequence2(String s, String t) {
    int m = s.length();
    int n = t.length();
    /*
    这样写不行, 这样就是只要有就行, 无法保证字符的顺序了,
    int res = 0;
    for (int i=0; i<m; i++){
        for (int j=0; j<n; j++){
            if (s.charAt(i) == t.charAt(j)) res++;
        }
    }
    return res == m;
    */
    int i=0, j=0;
    while (i<m && j<n){
        if (s.charAt(i) == t.charAt(j)) i++;
        j++;
    }

    return i==m;
}

时间复杂度: O(m+n)
空间复杂度: O(1)
```
<a name="JeYKv"></a>
## 动态规划
这道题应该算是编辑距离的入门题目，因为从题意中我们也可以发现，只需要计算删除的情况，不用考虑增加和替换的情况。所以**掌握本题的动态规划解法是对后面要讲解的编辑距离的题目打下基础**。

**动态规划五部曲分析如下：**

1. **确定dp数组（dp table）以及下标的含义**

dp[i][j] 表示以下标`i-1`为结尾的字符串s，和以下标`j-1`为结尾的字符串t，**相同子序列的长度为**`**dp[i][j]**`。**注意**这里是判断s是否为t的子序列。即t的长度一定是大于等于s的。<br />有同学问了，为啥要表示下标`i-1`为结尾的字符串呢，为啥不表示下标`i`为结尾的字符串呢？为什么这么定义在 `718. 最长重复子数组`中做了详细的讲解。其实用i来表示也可以！但我统一以下标i-1为结尾的字符串来计算，这样在下面的递归公式中会容易理解一些，如果还有疑惑，可以继续往下看。

2. **确定递推公式**

在确定递推公式的时候，首先要考虑如下两种操作，整理如下：

   - `if (s[i-1] == t[j-1])`: t中找到了一个字符在s中也出现了
   - `if (s[i-1] != t[j-1])`: 相当于t要删除元素，继续匹配

`if (s[i-1] == t[j-1])`(相等)，那么`dp[i][j] = dp[i-1][j-1] + 1;`，因为找到了一个相同的字符，相同子序列长度自然要在`dp[i-1][j-1]`的基础上加1（如果不理解，在回看一下dp[i][j]的定义）<br />`if (s[i-1] != t[j-1])`(不等)，此时相当于t要删除元素，t如果把当前元素`t[j-1]`删除，那么`dp[i][j]` 的数值就是 看`s[i - 1]`与`t[j **- 2]**`的比较结果了，即：`dp[i][j] = dp[i][j - 1];`
> 其实这里 大家可以发现和`1143.最长公共子序列`的递推公式基本那就是一样的，区别就是 本题 如果删元素一定是字符串t，而 1143.最长公共子序列 是两个字符串都可以删元素。

3. **dp数组如何初始化**

从递推公式可以看出`dp[i][j]`都是依赖于`dp[i-1][j-1]` 和 `dp[i][j-1]`，所以`dp[0][0]`和`dp[i][0]`是一定要初始化的。<br />这里大家已经可以发现，在定义`dp[i][j]`含义的时候为什么要表示以下标`i-1`为结尾的字符串s，和以下标`j-1`为结尾的字符串t，相同子序列的长度为`dp[i][j]`。<br />因为**这样的定义在dp二维矩阵中可以留出初始化的区间**，如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694068060766-c8d61814-a478-4edb-b704-180e4a1336a1.png#averageHue=%23f0f0f0&clientId=u77573d6d-74c8-4&from=paste&height=290&id=u5b69d383&originHeight=339&originWidth=670&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=29591&status=done&style=shadow&taskId=u8189dafd-d021-413b-8a7a-118cb10d4a5&title=&width=572.6495936542345)<br />如果要是定义的`dp[i][j]`是以下标`i`为结尾的字符串s 和 以下标`j`为结尾的字符串t，初始化就比较麻烦了。<br />`dp[i][0]` 表示以下标`i-1`为结尾的字符串，与空字符串的相同子序列长度，所以为0. `dp[0][j]`同理。
```java
vector<vector<int>> dp(s.size() + 1, vector<int>(t.size() + 1, 0));
```

4. **确定遍历顺序**

同理从递推公式可以看出`dp[i][j]`都是依赖于`dp[i - 1][j - 1]`和`dp[i][j - 1]`，那么遍历顺序也应该是从上到下，从左到右, 如图所示：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694068128791-6191908f-32c0-41a2-86d3-26acc0631a22.png#averageHue=%23eeeeee&clientId=u77573d6d-74c8-4&from=paste&height=338&id=uc25155bb&originHeight=396&originWidth=521&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=46883&status=done&style=shadow&taskId=u907ac415-89eb-4d80-a2ae-cab8dcba833&title=&width=445.2991616326212)

5. **举例推导dp数组**

以示例一为例，输入：s = "abc", t = "ahbgdc"，dp状态转移图如下：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694068148958-25cd9cce-20c2-4eca-a843-17dd929ce323.png#averageHue=%23efefef&clientId=u77573d6d-74c8-4&from=paste&height=280&id=u20a17d21&originHeight=328&originWidth=678&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=50772&status=done&style=shadow&taskId=uee3c3698-5f3f-4524-bbfb-dc2549e0161&title=&width=579.4872007426434)<br />`dp[i][j]`表示以下标`i-1`为结尾的字符串s和以下标j-1为结尾的字符串t 相同子序列的长度，所以如果`dp[s.size()][t.size()]`与 字符串s的长度相同说明：s与t的最长相同子序列就是s，那么s 就是 t 的子序列。<br />图中`dp[s.size()][t.size()] = 3`， 而`s.size()` 也为3。所以s是t 的子序列，返回true。
```java
public boolean isSubsequence(String s, String t) {
    int length1 = s.length();
    int length2 = t.length();

    int[][] dp = new int[length1+1][length2+1];

    for (int i=1; i<=length1; i++){  // 注意这里i从1开始, <=
        for (int j=1; j<=length2; j++){ // 注意这里j从1开始, <=
            if (s.charAt(i-1) == t.charAt(j-1)){
                dp[i][j] = dp[i-1][j-1]+1;
            } else {
                dp[i][j] = dp[i][j-1];
            }
        }
    }
    // length1是字符串s的长度
    if (dp[length1][length2] == length1){
        return true;
    } else {
        return false;
    }
}

时间复杂度：O(n × m)
空间复杂度：O(n × m)
```
这道题目算是编辑距离的入门题目（毕竟这里只是涉及到减法），也是动态规划解决的经典题型。<br />这一类题都是题目读上去感觉很复杂，模拟一下也发现很复杂，用动规分析完了也感觉很复杂，但是最终代码却很简短。<br />在之前的题目讲解中，我们讲了 `1143.最长公共子序列` 大家会发现 本题和 `1143.最长公共子序列` 的相似之处。<br />编辑距离的题目最能体现出动规精髓和巧妙之处，大家可以好好体会一下
<a name="YYWnL"></a>
# 115.不同的子序列
:::info
但相对于刚讲过 392.判断子序列，本题 就有难度了 ，感受一下本题和  392.判断子序列 的区别。 <br />[https://programmercarl.com/0115.%E4%B8%8D%E5%90%8C%E7%9A%84%E5%AD%90%E5%BA%8F%E5%88%97.html](https://programmercarl.com/0115.%E4%B8%8D%E5%90%8C%E7%9A%84%E5%AD%90%E5%BA%8F%E5%88%97.html)
:::
:::info
困难题   ([字符串](https://leetcode.cn/tag/string/), [动态规划](https://leetcode.cn/tag/dynamic-programming/))
:::
这道题目如果不是子序列，而是要求连续序列的，那就可以考虑用KMP。<br />这道题目相对于`72. 编辑距离`，简单了不少，因为本题相当于只有删除操作，不用考虑替换增加之类的。<br />但相对于刚讲过的动态规划：`392.判断子序列`就有难度了，这道题目双指针法可就做不了了，来看看动规五部曲分析如下：

1. **确定dp数组（dp table）以及下标的含义**

dp[i][j]：以`i-1`为结尾的s子序列中出现以`j-1`为结尾的t的**个数**为`dp[i][j]`。<br />为什么`i-1`，`j-1` 这么定义在`718. 最长重复子数组`中做了详细的讲解。

2. **确定递推公式**

这一类问题，基本是要分析两种情况

   - `s[i - 1]` 与 `t[j - 1]`相等
   - `s[i - 1]` 与 `t[j - 1]` 不相等

当`s[i-1]` 与 `t[j-1]`**相等时**，`dp[i][j]`可以有两部分组成。

   - 一部分是**用**`s[i-1]`来匹配，那么个数为`dp[i-1][j-1]`。即不需要考虑当前 s子串 和 t子串 的最后一位字母，所以只需要 `dp[i-1][j-1]`。
   - 一部分是**不用**`s[i-1]`来匹配，个数为`dp[i-1][j]`。

这里可能有录友不明白了，为什么还要考虑 不用`s[i-1]`来匹配，都相同了指定要匹配啊。<br />例如： `s：bagg`和 `t：bag` ，s[3] 和 t[2]是相同的，但是字符串s也可以不用`s[3]`来匹配，

   - 即用`s[0]s[1]s[2]`组成的bag。
   - 当然也可以用`s[3]`来匹配，即：s[0]s[1]s[3]组成的bag。

**所以当**`**s[i-1]**`**与 **`**t[j-1]**`**相等时，**`**dp[i][j] = dp[i-1][j-1] + dp[i-1][j];**`<br />当`s[i - 1]`与 `t[j - 1]`**不相等时**，`dp[i][j]`只有一部分组成，不用`s[i-1]`来匹配, **就是模拟在s中删除这个元素**), 即：`dp[i-1][j]`, 所以**递推公式为:**`**dp[i][j] = dp[i - 1][j];**`<br />这里可能有录友还疑惑，为什么只考虑 “不用s[i - 1]来匹配” 这种情况， 不考虑 “不用t[j - 1]来匹配” 的情况呢。这里大家要明确，我们求的是 s 中有多少个 t，而不是 求t中有多少个s，所以只考虑 s中删除元素的情况，即 不用s[i - 1]来匹配 的情况。

3. **dp数组如何初始化**

从**递推公式**`**dp[i][j] = dp[i-1][j-1] + dp[i-1][j];**`(元素相等时)** 和 **`**dp[i][j] = dp[i-1][j];**`(元素不相等时) 中可以看出`dp[i][j]`是从**上方和左上方推导而来**，如图：，那么 `dp[i][0]` 和`dp[0][j]`是一定要初始化的。<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694071873662-084d9f13-796d-4d8e-b5c5-d4a9a9749fa8.png#averageHue=%23f3f1f1&clientId=u77573d6d-74c8-4&from=paste&height=277&id=uc7823f38&originHeight=554&originWidth=613&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=46238&status=done&style=shadow&taskId=uc1fa982c-45ea-4e1d-9203-01ceba6c93e&title=&width=307)<br />每次当初始化的时候，都要回顾一下dp[i][j]的定义，不要凭感觉初始化。<br />`**dp[i][0]**`表示什么呢？**dp[i][0] 表示：以**`**i-1**`**为结尾的s可以随便删除元素，出现空字符串的个数**。那么`dp[i][0]`一定都是1，因为也就是把以`i-1`为结尾的s，删除所有元素，出现空字符串的个数就是1。<br />再来看`**dp[0][j]**`，`**dp[0][j]**`：空字符串s可以随便删除元素，出现以`j-1`为结尾的字符串t的个数。那么`dp[0][j]`一定都是0，s如论如何也变成不了t。<br />最后就要看一个特殊位置了，即：dp[0][0] 应该是多少。`dp[0][0]`应该是1，空字符串s，可以删除0个元素，变成空字符串t。<br />初始化分析完毕，代码如下：
```java
int[][] dp = new int[s.length()+1][t.length()+1];

for (int i=0; i<s.length()+1; i++){
    dp[i][0] = 1;  // 初始化
}
```

4. **确定遍历顺序**

从**递推公式**`dp[i][j] = dp[i-1][j-1] + dp[i-1][j];` 和 `dp[i][j] = dp[i-1][j];` 中可以看出`dp[i][j]`都是根据左上方和正上方推出来的。<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694072040309-bcc507e0-0fc1-4be9-8916-d783f989c6e0.png#averageHue=%23f0eeee&clientId=u77573d6d-74c8-4&from=paste&height=244&id=u7024a67b&originHeight=487&originWidth=487&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=31788&status=done&style=shadow&taskId=u8d41947c-7b60-4a3d-bca5-5a65e0f877a&title=&width=244)<br />所以遍历的时候一定是**从上到下，从左到右**，这样保证`dp[i][j]`可以根据之前计算出来的数值进行计算。代码如下：
```java
for (int i = 1; i <= s.size(); i++) {
    for (int j = 1; j <= t.size(); j++) {
        if (s[i - 1] == t[j - 1]) {
            dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
        } else {
            dp[i][j] = dp[i - 1][j];
        }
    }
}
```

5. **举例推导dp数组**

以s："baegg"，t："bag"为例，推导dp数组状态如下：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694072085952-cec6c071-8fff-4263-b5cf-fb2b544e282e.png#averageHue=%23f0efef&clientId=u77573d6d-74c8-4&from=paste&height=485&id=u1d4844e2&originHeight=567&originWidth=581&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=74027&status=done&style=shadow&taskId=ud0810090-07bb-4d5f-80e3-5c0f8a851b5&title=&width=496.581214795687)<br />如果写出来的代码怎么改都通过不了，不妨把dp数组打印出来，看一看，是不是这样的。
```java
public int numDistinct(String s, String t) {
    int[][] dp = new int[s.length()+1][t.length()+1];

    for (int i=0; i<s.length()+1; i++){
        dp[i][0] = 1;  // 初始化
    }

    // 注意 i 和 j 的起始值和取值范围
    for (int i=1; i<s.length()+1; i++){
        for (int j=1; j<t.length()+1; j++){
            if (s.charAt(i-1) == t.charAt(j-1)){
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }
    return dp[s.length()][t.length()];
}
时间复杂度: O(n * m)
空间复杂度: O(n * m)
```
-
