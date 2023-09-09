时间: 2023.7.4 周二
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part16, 583. 两个字符串的删除操作, 72. 编辑距离, 编辑距离总结篇   
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [ ] <br />

<a name="mUvUm"></a>
# 583. 两个字符串的删除操作
:::info
本题和动态规划：115.不同的子序列 相比，其实就是两个字符串都可以删除了，情况虽说复杂一些，但整体思路是不变的。<br />[https://programmercarl.com/0583.%E4%B8%A4%E4%B8%AA%E5%AD%97%E7%AC%A6%E4%B8%B2%E7%9A%84%E5%88%A0%E9%99%A4%E6%93%8D%E4%BD%9C.html](https://programmercarl.com/0583.%E4%B8%A4%E4%B8%AA%E5%AD%97%E7%AC%A6%E4%B8%B2%E7%9A%84%E5%88%A0%E9%99%A4%E6%93%8D%E4%BD%9C.html)
:::
:::info
中等题     ([字符串](https://leetcode.cn/tag/string/), [动态规划](https://leetcode.cn/tag/dynamic-programming/))         本题是编辑距离问题<br />`1 <= word1.length, word2.length <= 500`<br />word1 和 word2 只包含小写英文字母
:::
<a name="bGFFv"></a>
## DP1
本题和动态规划：`115.不同的子序列`相比，**其实就是两个字符串都可以删除了**，情况虽说复杂一些，但整体思路是不变的。这次是两个字符串可以相互删了，这种题目也知道用动态规划的思路来解，动规五部曲，分析如下：

1. **确定dp数组（dp table）以及下标的含义**

dp[i][j]：以`i-1`为结尾的字符串word1，和以`j-1`位结尾的字符串word2，想要达到相等，所需要删除元素的最少次数。   (这里dp数组的定义有点点绕，大家要撸清思路)

2. **确定递推公式**
   - 当`word1[i - 1]` 与 `word2[j - 1]`**相同**的时候, 递推公式: `dp[i][j] = dp[i - 1][j - 1];`
   - 当`word1[i - 1`] 与 `word2[j - 1]`**不相同**的时候, 有三种情况：
      - 情况一：删`word**1**[i - 1]`，最少操作次数为`dp[i - 1][j] + 1`
      - 情况二：删`word**2**[j - 1]`，最少操作次数为`dp[i][j - 1] + 1`
      - 情况三：同时删`word**1**[i - 1]`和`word**2**[j - 1]`，操作的最少次数为`dp[i - 1][j - 1] + 2`

那最后当然是**取最小值**，所以当`word**1**[i - 1]` 与 `word**2**[j - 1]`不相同的时候，递推公式：`dp[i][j] = min({dp[i-1][j-1]+2`, `dp[i-1][j]+1, dp[i][j-1]+1});`<br />因为`dp[i][j-1] + 1 = dp[i-1][j-1] + 2`，所以递推公式可简化为：`dp[i][j] = min(dp[i-1][j] + 1, dp[i][j-1] + 1);`<br />这里可能不少录友有点迷糊，从字面上理解就是当同时删`word1[i-1]`和`word2[j- 1]`, `dp[i][j-1]`本来就不考虑`word2[j-1]`了，那么我在删`word1[i-1]`，是不是就达到两个元素都删除的效果，即`dp[i][j-1] + 1`

3. **dp数组如何初始化**

从递推公式中，可以看出来，**dp[i][0] **和 **dp[0][j]**是一定要初始化的。<br />**dp[i][0]**：word2为空字符串，以`i-1`为结尾的字符串word1要删除多少个元素，才能和word2相同呢，很明显`dp[i][0] = i`。<br />**dp[0][j]**的话同理，所以代码如下：
```java
vector<vector<int>> dp(word1.size() + 1, vector<int>(word2.size() + 1));
for (int i = 0; i <= word1.size(); i++) dp[i][0] = i;
for (int j = 0; j <= word2.size(); j++) dp[0][j] = j;
```

4. **确定遍历顺序**

从递推公式 `dp[i][j] = min(dp[i-1][j-1] + 2, min(dp[i-1][j], dp[i][j-1]) + 1);` 和`dp[i][j] = dp[i-1][j-1]`可以看出dp[i][j]都是根据**左上方、正上方、正左方**推出来的。所以遍历的时候一定是从上到下，从左到右，这样保证dp[i][j]可以根据之前计算出来的数值进行计算。

5. **举例推导dp数组**

以word1:"sea"，word2:"eat"为例，推导dp数组状态图如下：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694248162528-25e7698d-eedd-4730-91ef-905ff36ff223.png#averageHue=%23efefef&clientId=u97afa8bd-b1a2-4&from=paste&height=403&id=ua51cf25c&originHeight=537&originWidth=586&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=47504&status=done&style=shadow&taskId=ua854ccef-9a4e-4d1c-8074-ad496d16cf9&title=&width=440)
```java
// dp数组中存储需要删除的字符个数
class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i < word1.length() + 1; i++) dp[i][0] = i;
        for (int j = 0; j < word2.length() + 1; j++) dp[0][j] = j;
        
        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 2,
                                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        
        return dp[word1.length()][word2.length()];
    }
}

时间复杂度: O(n * m)
空间复杂度: O(n * m)
```
<a name="dwTH8"></a>
## DP2
本题和动态规划：`1143.最长公共子序列`基本相同，只要求出两个字符串的最长公共子序列长度即可，那么除了最长公共子序列之外的字符都是必须删除的，最后用两个字符串的总长度减去两个最长公共子序列的长度就是删除的最少步数。
```java
// dp数组中存储word1和word2最长相同子序列的长度
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return len1 + len2 - dp[len1][len2] * 2;  // 记得 * 2
    }
}
```
```java
//DP - longest common subsequence (用最長公共子序列反推)
class Solution {
    public int minDistance(String word1, String word2) {
        char[] char1 = word1.toCharArray();
        char[] char2 = word2.toCharArray();

        int len1 = char1.length;
        int len2 = char2.length;

        int dp[][] = new int [len1 + 1][len2 + 1];

        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(char1[i - 1] == char2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return len1 + len2 - (2 * dp[len1][len2]);//和leetcode 1143只差在這一行。
    }
}

时间复杂度: O(n * m)
空间复杂度: O(n * m)
```
-
<a name="UXWlT"></a>
# 72. 编辑距离
:::info
最终我们迎来了编辑距离这道题目，之前安排题目都是为了 编辑距离做铺垫。 <br />[https://programmercarl.com/0072.%E7%BC%96%E8%BE%91%E8%B7%9D%E7%A6%BB.html](https://programmercarl.com/0072.%E7%BC%96%E8%BE%91%E8%B7%9D%E7%A6%BB.html)
:::
:::info
困难题   ([字符串](https://leetcode.cn/tag/string/), [动态规划](https://leetcode.cn/tag/dynamic-programming/))  <br />`0 <= word1.length, word2.length <= 500`<br />word1 和 word2 由小写英文字母组成
:::
编辑距离终于来了，这道题目如果大家没有了解动态规划的话，会感觉超级复杂。**编辑距离是用动规来解决的经典题目**，这道题目看上去好像很复杂，但用动规可以很巧妙的算出最少编辑距离。<br />接下来我依然使用动规五部曲，对本题做一个详细的分析：

1. **确定dp数组（dp table）以及下标的含义**

dp[i][j] 表示: 以下标`i-1`为结尾的字符串word1，和以下标`j-1`为结尾的字符串word2，最近编辑距离为`dp[i][j]`。<br />有同学问了，为啥要表示下标`i-1`为结尾的字符串呢，为啥不表示下标i为结尾的字符串呢？为什么这么定义在`718. 最长重复子数组`中做了详细的讲解。其实用i来表示也可以！ 用i-1就是为了方便后面dp数组初始化的。

2. **确定递推公式**

在确定递推公式的时候，首先要考虑清楚编辑的几种操作，整理如下：
```java
if (word1[i - 1] == word2[j - 1])
    不操作
if (word1[i - 1] != word2[j - 1])
    增
    删
    换
```
也就是如上**4种情况**。<br />`if (word1[i - 1] == word2[j - 1])`(**等于时**) 那么说明不用任何编辑，`dp[i][j]` 就应该是`dp[i - 1][j - 1]`，即`dp[i][j] = dp[i - 1][j - 1];`<br />此时可能有同学有点不明白，为啥要即`dp[i][j] = dp[i - 1][j - 1]`呢？那么就在回顾上面讲过的dp[i][j]的定义，`word1[i - 1]` 与 `word2[j - 1]`相等了，那么就不用编辑了，以下标`i-2`为结尾的字符串word1和以下标`j-2`为结尾的字符串word2的最近编辑距离`dp[i - 1][j - 1]`就是 dp[i][j]了。<br />在下面的讲解中，如果哪里看不懂，就回想一下dp[i][j]的定义，就明白了。在整个动规的过程中，最为关键就是正确理解dp[i][j]的定义！<br />`if (word1[i - 1] != word2[j - 1])`(**不等于时**)，此时就需要编辑了，如何编辑呢？

- **操作一**：word1**删除**一个元素，那么就是以下标`i - 2`为结尾的word1 与`j-1`为结尾的word2的最近编辑距离 再加上一个操作。即 `dp[i][j] = dp[i - 1][j] + 1;`
- **操作二**：word2**删除**一个元素，那么就是以下标`i - 1`为结尾的word1 与`j-2`为结尾的word2的最近编辑距离 再加上一个操作。即 `dp[i][j] = dp[i][j - 1] + 1;`

这里有同学发现了，怎么都是删除元素，添加元素去哪了?  **word2添加一个元素，相当于word1删除一个元素**，例如 `word1 = "ad"` ，`word2 = "a"`，word1删除元素`'d'` 和 word2添加一个元素`'d'`，变成`word1="a"`, `word2="ad"`， 最终的操作数是一样！ dp数组如下图所示意的：
```java
            a                         a     d
   +-----+-----+             +-----+-----+-----+
   |  0  |  1  |             |  0  |  1  |  2  |
   +-----+-----+   ===>      +-----+-----+-----+
 a |  1  |  0  |           a |  1  |  0  |  1  |
   +-----+-----+             +-----+-----+-----+
 d |  2  |  1  |
   +-----+-----+
```

- **操作三**：**替换**元素，word1替换`word1[i - 1]`，使其与`word2[j - 1]`相同，此时不用增删加元素。

可以回顾一下，`if (word1[i - 1] == word2[j - 1])`的时候我们的操作 是`dp[i][j] = dp[i - 1][j - 1]` 对吧。那么**只需要一次替换的操作**，就可以让`word1[i - 1] 和 word2[j - 1]`相同。所以 `dp[i][j] = dp[i - 1][j - 1] + 1;`<br />综上，当`if (word1[i - 1] != word2[j - 1])`时取最小的，即：`dp[i][j] = min({dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]}) + 1;`<br />递归公式代码如下：
```java
if (word1[i - 1] == word2[j - 1]) {
    dp[i][j] = dp[i - 1][j - 1];
}
else {
    dp[i][j] = min({dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]}) + 1;
}
```

3. **dp数组如何初始化**

再回顾一下dp[i][j]的定义：dp[i][j] 表示以下标`i-1`为结尾的字符串word1，和以下标`j-1`为结尾的字符串word2，最近编辑距离为dp[i][j]。<br />那么dp[i][0] 和 dp[0][j] 表示什么呢？`**dp[i][0]**` ：以下标`i-1`为结尾的字符串word1，和**空字符串**word2，最近编辑距离为`dp[i][0]`。那么`dp[i][0]`就应该是`i`，对word1里的元素全部做删除操作，即：`dp[i][0] = i;`同理`dp[0][j] = j;`
```java
for (int i = 0; i <= word1.size(); i++) dp[i][0] = i;
for (int j = 0; j <= word2.size(); j++) dp[0][j] = j;
```

4. **确定遍历顺序**

从如下四个递推公式：

- dp[i][j] = dp[i - 1][j - 1]
- dp[i][j] = dp[i - 1][j - 1] + 1
- dp[i][j] = dp[i][j - 1] + 1
- dp[i][j] = dp[i - 1][j] + 1

可以看出dp[i][j]是依赖左方，上方和左上方元素的，如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694262768094-843bcc9b-4fba-47d1-8c02-7eda813c0770.png#averageHue=%23edecec&clientId=u97afa8bd-b1a2-4&from=paste&height=290&id=u1d519cd9&originHeight=339&originWidth=436&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=42898&status=done&style=shadow&taskId=u07e361cb-01ec-4cad-840b-2045c5e89ea&title=&width=372.649586318278)<br />所以在dp矩阵中一定是从左到右从上到下去遍历。代码如下：
```java
for (int i = 1; i <= word1.size(); i++) {
    for (int j = 1; j <= word2.size(); j++) {
        if (word1[i - 1] == word2[j - 1]) {
            dp[i][j] = dp[i - 1][j - 1];
        }
        else {
            dp[i][j] = min({dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]}) + 1;
        }
    }
}
```

5. **举例推导dp数组**

以示例1为例，输入：word1 = "horse", word2 = "ros"为例，dp矩阵状态图如下：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694262807159-83c83762-894f-4b02-8463-65c4fb81a8b2.png#averageHue=%23ebebeb&clientId=u97afa8bd-b1a2-4&from=paste&height=414&id=u8080bbd1&originHeight=552&originWidth=449&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=52954&status=done&style=shadow&taskId=u3cd09f2b-8213-41fa-bf08-f7b04cf8f6a&title=&width=337)
```java
public int minDistance(String word1, String word2) {
    int len1 = word1.length();
    int len2 = word2.length();
    int[][] dp = new int[len1+1][len2+1];

    // 初始化   从1开始, 且小于等于长度
    for (int i=1; i<=len1; i++) dp[i][0] = i;
    for (int j=1; j<=len2; j++) dp[0][j] = j;

    // 注意是从1开始, 然后是小于等于长度
    for (int i=1; i<=len1; i++){
        for (int j=1; j<=len2; j++){
            // 因为dp数组有效位从1开始
            // 所以当前遍历到的字符串的位置为i-1 | j-1
            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
            }
        }
    }
    return dp[len1][len2];
}
时间复杂度: O(n * m)
空间复杂度: O(n * m)
```
-
<a name="bKjyJ"></a>
# 编辑距离总结篇
做一个总结吧<br />[https://programmercarl.com/%E4%B8%BA%E4%BA%86%E7%BB%9D%E6%9D%80%E7%BC%96%E8%BE%91%E8%B7%9D%E7%A6%BB%EF%BC%8C%E5%8D%A1%E5%B0%94%E5%81%9A%E4%BA%86%E4%B8%89%E6%AD%A5%E9%93%BA%E5%9E%AB.html](https://programmercarl.com/%E4%B8%BA%E4%BA%86%E7%BB%9D%E6%9D%80%E7%BC%96%E8%BE%91%E8%B7%9D%E7%A6%BB%EF%BC%8C%E5%8D%A1%E5%B0%94%E5%81%9A%E4%BA%86%E4%B8%89%E6%AD%A5%E9%93%BA%E5%9E%AB.html)
