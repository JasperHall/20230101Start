时间: 2023.7.5 周三
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part17, 647. 回文子串, 516.最长回文子序列, 动态规划总结篇
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [ ] <br />

今天 我们就要结束动态规划章节了，大家激不激动！！！ 
<a name="eNXmD"></a>
# 647. 回文子串
:::info
动态规划解决的经典题目，如果没接触过的话，别硬想 直接看题解。<br />[https://programmercarl.com/0647.%E5%9B%9E%E6%96%87%E5%AD%90%E4%B8%B2.html](https://programmercarl.com/0647.%E5%9B%9E%E6%96%87%E5%AD%90%E4%B8%B2.html)
:::
:::info
中等题   
:::
<a name="uRLRU"></a>
## 暴力
两层for循环，遍历区间起始位置和终止位置，然后还需要一层遍历判断这个区间是不是回文。所以时间复杂度：O(n^3)
<a name="SoIXF"></a>
## 动态规划
动规五部曲：

1. **确定dp数组（dp table）以及下标的含义**

如果大家做了很多这种子序列相关的题目，在定义dp数组的时候 很自然就会想题目求什么，我们就如何定义dp数组。绝大多数题目确实是这样，不过本题如果我们定义，dp[i] 为 下标i结尾的字符串有 dp[i]个回文串的话，我们会发现很难找到递归关系。dp[i] 和 dp[i-1] ，dp[i + 1] 看上去都没啥关系。<br />所以我们要看回文串的性质。 如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694483990685-9515c8e6-9846-461c-ad58-8ac1c1c670ab.png#averageHue=%23f3f2f2&clientId=u09c901fa-a97e-4&from=paste&height=370&id=u9dffc54a&originHeight=493&originWidth=687&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=50922&status=done&style=shadow&taskId=u5a4c8a5a-f3a3-4fbf-b591-a3bb6c6a59c&title=&width=515)<br />我们在判断字符串S是否是回文，那么如果我们知道 s[1]，s[2]，s[3] 这个子串是回文的，那么只需要比较 s[0]和s[4]这两个元素是否相同，如果相同的话，这个字符串s 就是回文串。<br />那么此时我们是不是能找到一种递归关系，也就是判断一个子字符串（字符串的下表范围[i,j]）是否回文，依赖于，子字符串（下表范围[i + 1, j - 1]）） 是否是回文。所以为了明确这种递归关系，我们的dp数组是要定义成一位二维dp数组。<br />**布尔类型的dp[i][j]：表示区间范围**`**[i,j]**`** （注意是左闭右闭）的子串是否是回文子串，如果是dp[i][j]为true，否则为false**。

3. **确定递推公式**

在确定递推公式时，就要分析如下几种情况。整体上是两种，就是s[i]与s[j]**相等**，s[i]与s[j]**不相等**这两种。<br />当s[i]与s[j]**不相等**，那没啥好说的了，dp[i][j]一定是false。<br />当s[i]与s[j]**相等**时，这就复杂一些了，有如下三种情况

   - 情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
   - 情况二：下标i 与 j相差为1，例如aa，也是回文子串
   - 情况三：下标：i 与 j相差大于1的时候，例如cabac，此时s[i]与s[j]已经相同了，我们看`i`到`j`区间是不是回文子串就看aba是不是回文就可以了，那么aba的区间就是 `i+1` 与 `j-1`区间，这个区间是不是回文就看`dp[i + 1][j - 1]`是否为true。

以上三种情况分析完了，那么递归公式如下：
```cpp
if (s[i] == s[j]) {
    if (j - i <= 1) { // 情况一 和 情况二
        result++;
        dp[i][j] = true;
    } else if (dp[i + 1][j - 1]) { // 情况三
        result++;
        dp[i][j] = true;
    }
}
```
**result就是统计回文子串的数量**。<br />注意这里我没有列出当s[i]与s[j]不相等的时候，因为在下面dp[i][j]初始化的时候，就初始为false。

3. **dp数组如何初始化**

dp[i][j]可以初始化为true么？ 当然不行，怎能刚开始就全都匹配上了。所以**dp[i][j]初始化为false**。

4. **确定遍历顺序**

遍历顺序可有有点讲究了。<br />首先从递推公式中可以看出，情况三是根据`dp[i + 1][j - 1]`是否为true，在对dp[i][j]进行赋值true的。`dp[i + 1][j - 1]` 在 dp[i][j]的左下角，如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694487025541-e53c067e-ef88-4e20-a345-5747e5ee1f98.png#averageHue=%23efefef&clientId=u09c901fa-a97e-4&from=paste&height=190&id=u409ce33c&originHeight=347&originWidth=366&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=15651&status=done&style=shadow&taskId=u7c77ea43-512e-4156-aeb1-ff25247218a&title=&width=200)<br />如果这矩阵是从上到下，从左到右遍历，那么会用到没有计算过的`dp[i + 1][j - 1]`，也就是根据不确定是不是回文的区间`[i+1,j-1]`，来判断了`[i,j]`是不是回文，那结果一定是不对的。所以**一定要从下到上，从左到右遍历，这样保证dp[i + 1][j - 1]都是经过计算的**。<br />有的代码实现是优先遍历列，然后遍历行，其实也是一个道理，都是为了保证dp[i + 1][j - 1]都是经过计算的。<br />代码如下：
```cpp
for (int i = s.size() - 1; i >= 0; i--) {  // 注意遍历顺序
    for (int j = i; j < s.size(); j++) {
        if (s[i] == s[j]) {
            if (j - i <= 1) { // 情况一 和 情况二
                result++;
                dp[i][j] = true;
            } else if (dp[i + 1][j - 1]) { // 情况三
                result++;
                dp[i][j] = true;
            }
        }
    }
}
```

5. **举例推导dp数组**

举例，输入："aaa"，dp[i][j]状态如下：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694487206387-2c438a6f-a8c8-4abf-97d2-0ae25d629342.png#averageHue=%23f2f1f0&clientId=u09c901fa-a97e-4&from=paste&height=265&id=u068fa6c9&originHeight=353&originWidth=467&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=34041&status=done&style=shadow&taskId=u2097854a-b6db-45dc-9c7e-3b75607b24d&title=&width=351)<br />图中有6个true，所以就是有6个回文子串。<br />注意因为dp[i][j]的定义，所以`**j**`**一定是大于等于**`**i**`**的**，那么在填充dp[i][j]的时候一定是只填充**右上半部分**。<br />以上分析完毕：
```cpp
class Solution {
    public int countSubstrings(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        boolean[][] dp = new boolean[len][len];
        int result = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (chars[i] == chars[j]) {
                    if (j - i <= 1) { // 情况一 和 情况二
                        result++;
                        dp[i][j] = true;
                    } else if (dp[i + 1][j - 1]) { //情况三
                        result++;
                        dp[i][j] = true;
                    }
                }
            }
        }
        return result;
    }
}

时间复杂度：O(n^2)
空间复杂度：O(n^2)
```
```cpp
class Solution {
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                // i和j位置的字符相等, 和后面的两个条件满足一个, 就进入if语句内
                // 相当于情况一二三合并来写
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    res++;
                    dp[i][j] = true;
                }
            }
        }
        return res;
    }
}

时间复杂度：O(n^2)
空间复杂度：O(n^2)
```
<a name="tHYPU"></a>
## 双指针法
动态规划的空间复杂度是偏高的，我们再看一下双指针法。<br />首先确定回文串，就是**找中心然后向两边扩散**看是不是对称的就可以了。<br />在遍历中心点的时候，**要注意中心点有两种情况**。一个元素可以作为中心点，两个元素也可以作为中心点。<br />那么有人同学问了，三个元素还可以做中心点呢。其实三个元素就可以由一个元素左右添加元素得到，四个元素则可以由两个元素左右添加元素得到。<br />所以我们在计算的时候，**要注意一个元素为中心点和两个元素为中心点的情况**。<br />这两种情况可以放在一起计算，但分别计算思路更清晰，我倾向于分别计算，代码如下：
```cpp
class Solution {
    public int countSubstrings(String s) {
        int len, ans = 0;
        if (s == null || (len = s.length()) < 1) return 0;
        //总共有2 * len - 1个中心点
        for (int i = 0; i < 2 * len - 1; i++) {
            //通过遍历每个回文中心，向两边扩散，并判断是否回文字串
            //有两种情况，left == right，right = left + 1，这两种回文中心是不一样的
            int left = i / 2, right = left + i % 2;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                //如果当前是一个回文串，则记录数量
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }
}
```

<a name="Dx5Zq"></a>
# 516.最长回文子序列
:::info
647. 回文子串，求的是回文子串，而本题要求的是回文子序列， 大家要搞清楚两者之间的区别。 <br />[https://programmercarl.com/0516.%E6%9C%80%E9%95%BF%E5%9B%9E%E6%96%87%E5%AD%90%E5%BA%8F%E5%88%97.html](https://programmercarl.com/0516.%E6%9C%80%E9%95%BF%E5%9B%9E%E6%96%87%E5%AD%90%E5%BA%8F%E5%88%97.html)
:::
:::info
中等  
:::
我们刚刚做过了 `动态规划：回文子串`，求的是回文子串，而本题要求的是回文子序列， 要搞清楚这两者之间的区别。<br />回文子串是要**连续的**，回文子序列可**不是连续的**！ 回文子串，回文子序列都是动态规划经典题目。<br />回文子串，可以做这两题：

- 647.回文子串
- 5.最长回文子串

思路其实是差不多的，但本题要比求回文子串简单一点，因为情况少了一点。

1. **动规五部曲分析如下：**

确定dp数组（dp table）以及下标的含义<br />dp[i][j]：字符串s在`[i, j]`范围内最长的回文子序列的长度为dp[i][j]。

2. **确定递推公式**

在判断回文子串的题目中，关键逻辑就是看`s[i]`与`s[j]`是否相同。<br />如果`s[i]`与`s[j]`相同，那么`**dp[i][j] = dp[i + 1][j - 1] + 2;**`<br />如图：（如果这里看不懂，回忆一下dp[i][j]的定义）<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694491927200-8781ad7e-3c9b-4927-800c-c880114312f0.png#averageHue=%23f4f3f3&clientId=u09c901fa-a97e-4&from=paste&height=260&id=u05952e17&originHeight=347&originWidth=804&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=82509&status=done&style=shadow&taskId=ud69755a2-1388-4de4-b23b-79ae0a04f28&title=&width=603)<br />如果`s[i]`与`s[j]`不相同，说明`s[i]`和`s[j]`的同时加入 并不能增加`[i,j]`区间回文子序列的长度，那么**分别加入**s[i]、s[j]看看哪一个可以组成最长的回文子序列。

   - 加入`s[j]`的回文子序列长度为`dp[i + 1][j]`。
   - 加入`s[i]`的回文子序列长度为`dp[i][j - 1]`。

那么`dp[i][j]`一定是**取最大**的，即：`dp[i][j] = **max**(dp[i + 1][j], dp[i][j - 1]);`<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694491961000-7ba0bbfe-c0b4-4f0d-a187-ae20051f7d8d.png#averageHue=%23f4f2f2&clientId=u09c901fa-a97e-4&from=paste&height=313&id=ue3bb756b&originHeight=417&originWidth=805&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=101916&status=done&style=shadow&taskId=u76451f8a-40fb-4625-bcc6-5b8c17634e6&title=&width=604)<br />代码如下：
```cpp
if (s[i] == s[j]) {
    dp[i][j] = dp[i + 1][j - 1] + 2;
} else {
    dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
}
```

3. **dp数组如何初始化**

首先要考虑当`i`和`j`相同的情况，从递推公式：`dp[i][j] = dp[i + 1][j - 1] + 2;` 可以看出 递推公式是计算不到`i`和`j`相同时候的情况。所以需要手动初始化一下，**当**`**i**`**与**`**j**`**相同，那么**`**dp[i][j]**`**一定是等于1的，即：一个字符的回文子序列长度就是1**。<br />**其他情况**`**dp[i][j]**`**初始为0就行**，这样递推公式：`dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);` 中`dp[i][j]`才不会被初始值覆盖。

4. **确定遍历顺序**

从递归公式中，可以看出，`dp[i][j]` 依赖于`dp[i + 1][j - 1]`，`dp[i + 1][j]` 和 `dp[i][j - 1]`，如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694492674775-138cc92d-a4bb-48f2-9dc5-81d1faae986e.png#averageHue=%23f0edec&clientId=u09c901fa-a97e-4&from=paste&height=411&id=u58471470&originHeight=481&originWidth=487&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=31911&status=done&style=shadow&taskId=ub83f7e99-8e6a-428f-8248-f79533d68b2&title=&width=416.23933150688396)<br />所以遍历`i`的时候一定要从下到上遍历，这样才能保证下一行的数据是经过计算的。`j`的话，可以正常从左向右遍历。<br />代码如下：

5. **举例推导dp数组**

输入s:"cbbd" 为例，dp数组状态如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1694492711741-e88cd701-faf8-4680-82a5-167d4c8b0f0c.png#averageHue=%23f2f1f1&clientId=u09c901fa-a97e-4&from=paste&height=391&id=ud5aeeac1&originHeight=458&originWidth=573&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=63161&status=done&style=shadow&taskId=u39a2fbe1-5608-482c-ba39-c5efe7374a0&title=&width=489.7436077072782)<br />红色框即：`dp[0][s.size() - 1];`为最终结果。
```cpp
public int longestPalindromeSubseq(String s) {
    int len  = s.length();
    int[][] dp = new int[len+1][len+1];

    for (int i=len-1; i>=0; i--){   // 从后往前遍历 保证情况不漏
        dp[i][i] = 1; // 初始化  注意这里的坐标是 i,i
        for (int j = i+1; j < len; j++) {
            if (s.charAt(i) == s.charAt(j)){
                dp[i][j] = dp[i+1][j-1] + 2;
            } else {
                dp[i][j] = Math.max(dp[i+1][j], Math.max(dp[i][j], dp[i][j-1]));
            }
        }
    }
    return dp[0][len-1];
}

时间复杂度: O(n^2)
空间复杂度: O(n^2)
```
-
<a name="FW4lB"></a>
# 动态规划总结篇
[https://programmercarl.com/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E6%80%BB%E7%BB%93%E7%AF%87.html](https://programmercarl.com/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E6%80%BB%E7%BB%93%E7%AF%87.html)
