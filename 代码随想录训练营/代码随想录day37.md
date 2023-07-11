时间: 2023.6.15周四
<a name="sPFyY"></a>
# 今日任务
第八章 贪心算法 part06, 738.单调递增的数字, 968.监控二叉树 , 总结 
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 学习 968.监控二叉树

<a name="XBsr0"></a>
# 复习

- [ ] 复习 56. 合并区间  ,. 可以多看几种解法


详细布置 
<a name="h4Nyy"></a>
# 738.单调递增的数字 
[代码随想录](https://programmercarl.com/0738.%E5%8D%95%E8%B0%83%E9%80%92%E5%A2%9E%E7%9A%84%E6%95%B0%E5%AD%97.html)
<a name="w6LH5"></a>
## 暴力解法
题意很简单，那么首先想的就是暴力解法了，来我替大家暴力一波，结果自然是超时！代码如下：
```cpp
class Solution {
private:
    // 判断一个数字的各位上是否是递增
    bool checkNum(int num) {
        int max = 10;
        while (num) {
            int t = num % 10;
            if (max >= t) 
            {
                max = t;
            } else { 
                return false;
            }
            num = num / 10;
        }
        return true;
    }
public:
    int monotoneIncreasingDigits(int N) {
        for (int i = N; i > 0; i--) { // 从大到小遍历
            if (checkNum(i)) return i;
        }
        return 0;
    }
};
时间复杂度：O(n × m) m为n的数字长度
空间复杂度：O(1)
```
<a name="FfUvX"></a>
## 贪心算法
题目要求小于等于N的最大单调递增的整数，那么拿一个两位的数字来举例。<br />例如：98，一旦出现`strNum[i - 1] > strNum[i]`的情况(非单调递增)，首先想让`strNum[i - 1]--`，然后 strNum[i] 赋值为9，这样这个整数就是89，即小于98的最大的单调递增整数。<br />这一点如果想清楚了，这道题就好办了。<br />此时是从前向后遍历还是从后向前遍历呢？从前向后遍历的话，遇到`strNum[i - 1] > strNum[i]`的情况，让`strNum[i - 1]减一`，但此时如果`strNum[i - 1]减一`了，可能又小于`strNum[i - 2]`。<br />这么说有点抽象，举个例子，数字：332，从前向后遍历的话，那么就把变成了329，此时2又小于了第一位的3了，真正的结果应该是299。<br />那么从后向前遍历，就可以重复利用上次比较得出的结果了，从后向前遍历332的数值变化为：332 -> 329 -> 299<br />确定了遍历顺序之后，那么此时局部最优就可以推出全局，找不出反例，试试贪心。
```java
class Solution {
    /**
     * 方法一
     * 耗时和空间占用都很高
     * @param n
     * @return
     */
    public int monotoneIncreasingDigits(int n) {
        String[] strings = (n+"").split("");  // 分割成数组放入strings字符数组中

        int flag = strings.length;  // 标记作用, flag后面的数都赋值为9

        for (int i=strings.length-1; i>0; i--){
            // 把字符数组中的值取出, 转为数字后再进行比较
            if (Integer.parseInt(strings[i]) < Integer.parseInt(strings[i-1])){  // 如果后面的数小于前面的
                strings[i-1] = (Integer.parseInt(strings[i-1]) - 1) + "";  // 把前面的那个数字符取出转为数字类型, 然后减一, 然后赋值到原来的位置
                flag = i;  // 更新标记的位置
            }
        }
        for (int i=flag; i<strings.length; i++){
            strings[i]  = "9";
        }
        return Integer.parseInt(String.join("", strings));
    }
}
```
java版本1 中创建了String数组，多次使用了`Integer.parseInt`方法，这导致不管是耗时还是空间占用都非常高，用时12ms，下面提供一个版本在 char数组 上原地修改，用时1ms的版本
```java
class Solution {
    /**
     * 方法二
     * 在char数组上原地修改, 用时少, 效率高
     * @param n
     * @return
     */
    public int monotoneIncreasingDigits2(int n) {
        String s = String.valueOf(n);  // 先把int型的n转为String类型的s
        char[] chars = s.toCharArray();  // 然后把s转为字符数组
        int flag = s.length();

        for (int i=s.length()-1; i>0; i--){
            if (chars[i-1] > chars[i]){
                chars[i-1]--;
                flag = i;
            }
        }

        for (int i=flag; i<s.length(); i++){
            chars[i] = '9';
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}
```
本题只要想清楚个例，例如98，一旦出现`strNum[i - 1] > strNum[i]`的情况（非单调递增），首先想让`strNum[i - 1]减一`，strNum[i]赋值9，这样这个整数就是89。就可以很自然想到对应的贪心解法了。<br />想到了贪心，还要考虑遍历顺序，只有从后向前遍历才能重复利用上次比较的结果。<br />最后代码实现的时候，也需要一些技巧，例如用一个flag来标记从哪里开始赋值9。
<a name="zO9Ik"></a>
# 968.监控二叉树 （可以跳过）
本题是贪心和二叉树的一个结合，比较难，一刷大家就跳过吧。 <br />[代码随想录](https://programmercarl.com/0968.%E7%9B%91%E6%8E%A7%E4%BA%8C%E5%8F%89%E6%A0%91.html)
:::info
**困难题**(树, 深度优先搜索, 动态规划, 二叉树)
:::
这道题目首先要想，如何放置，才能让摄像头最小的呢？<br />从题目中示例，其实可以得到启发，我们发现题目示例中的摄像头都没有放在叶子节点上！这是很重要的一个线索，摄像头可以覆盖上中下三层，如果把摄像头放在叶子节点上，就浪费了一层的覆盖。所以把摄像头放在叶子节点的父节点位置，才能充分利用摄像头的覆盖面积。<br />那么有同学可能问了，为什么不从头结点开始看起呢，为啥要从叶子节点看呢？因为头结点放不放摄像头也就省下一个摄像头， 叶子节点放不放摄像头省下了的摄像头数量是指数阶别的。<br />所以我们要**从下往上**看，**局部最优**：让叶子节点的父节点安摄像头，所用摄像头最少，**整体最优**：全部摄像头数量所用最少！局部最优推出全局最优，找不出反例，那么就按照贪心来！<br />此时，大体思路就是**从低到上**，先给叶子节点父节点放个摄像头，然后隔两个节点放一个摄像头，直至到二叉树头结点。<br />此时这道题目还有两个难点：

1. 二叉树的遍历
2. 如何隔两个节点放一个摄像头
<a name="r1UoK"></a>
## 确定遍历顺序
在二叉树中如何从低向上推导呢？可以使用**后序遍历**也就是**左右中**的顺序，这样就可以在回溯的过程中从下到上进行推导了。<br />后序遍历代码如下：
```java
int traversal(TreeNode* cur) {

    // 空节点，该节点有覆盖
    if (终止条件) return ;

    int left = traversal(cur->left);    // 左
    int right = traversal(cur->right);  // 右

    逻辑处理                            // 中
    return ;
}
```
注意在以上代码中我们取了左孩子的返回值，右孩子的返回值，即left 和 right， 以后推导中间节点的状态
<a name="ZKDZx"></a>
## 如何隔两个节点放一个摄像头
此时需要状态转移的公式，大家不要和动态的状态转移公式混到一起，本题状态转移没有择优的过程，就是单纯的状态转移！<br />来看看这个状态应该如何转移，先来看看每个节点可能有几种状态：<br />有如下三种：

- 该节点无覆盖
- 本节点有摄像头
- 本节点有覆盖

我们分别有三个数字来表示：

- 0：该节点无覆盖
- 1：本节点有摄像头
- 2：本节点有覆盖

大家应该找不出第四个节点的状态了。一些同学可能会想有没有第四种状态：本节点无摄像头，其实无摄像头就是 无覆盖 或者 有覆盖的状态，所以一共还是三个状态。<br />因为在遍历树的过程中，就会遇到空节点，那么问题来了，空节点究竟是哪一种状态呢？ 空节点表示无覆盖？ 表示有摄像头？还是有覆盖呢？<br />回归本质，为了让摄像头数量最少，我们要尽量让叶子节点的父节点安装摄像头，这样才能摄像头的数量最少。那么空节点不能是无覆盖的状态，这样叶子节点就要放摄像头了，空节点也不能是有摄像头的状态，这样叶子节点的父节点就没有必要放摄像头了，而是可以把摄像头放在叶子节点的爷爷节点上。所以**空节点的状态只能是有覆盖**，这样就可以在叶子节点的父节点放摄像头了<br />接下来就是递推关系。<br />那么递归的终止条件应该是遇到了空节点，此时应该返回2（有覆盖），原因上面已经解释过了。代码如下：
```java
// 空节点，该节点有覆盖
if (cur == NULL) return 2;
```
递归的函数，以及终止条件已经确定了，再来看单层逻辑处理。<br />主要有如下**四类情况**：

1. **情况1：左右节点都有覆盖**

左孩子有覆盖，右孩子有覆盖，那么此时中间节点应该就是无覆盖的状态了。如图：<br />![20201229203710729.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689062003555-b3bd9605-1c2e-48cb-8e8a-12eb5b817ff8.png#averageHue=%23f8f7f7&clientId=u5f1fc337-0de8-4&from=paste&height=821&id=uc1485dfc&originHeight=960&originWidth=1158&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=118972&status=done&style=none&taskId=u8158f4f5-d913-4884-81f9-15f11a05fae&title=&width=989.7436260471696)
```java
// 左右节点都有覆盖
if (left == 2 && right == 2) return 0;
```

2. **情况2：左右节点至少有一个无覆盖的情况**

如果是以下情况，则中间节点（父节点）应该放摄像头：

- `left == 0 && right == 0`左右节点无覆盖
- `left == 1 && right == 0`左节点有摄像头，右节点无覆盖
- `left == 0 && right == 1` 左节点有无覆盖，右节点摄像头
- `left == 0 && right == 2` 左节点无覆盖，右节点覆盖
- `left == 2 && right == 0` 左节点覆盖，右节点无覆盖

这个不难理解，毕竟有一个孩子没有覆盖，父节点就应该放摄像头。此时摄像头的数量要加一，并且return 1，代表中间节点放摄像头。代码如下：
```java
if (left == 0 || right == 0) {
    result++;
    return 1;
}
```

3. **情况3：左右节点至少有一个有摄像头**

如果是以下情况，其实就是 左右孩子节点有一个有摄像头了，那么其父节点就应该是2（覆盖的状态）

- `left == 1 && right == 2` 左节点有摄像头，右节点有覆盖
- `left == 2 && right == 1` 左节点有覆盖，右节点有摄像头
- `left == 1 && right == 1` 左右节点都有摄像头

代码如下：
```java
if (left == 1 || right == 1) return 2;
```
从这个代码中，可以看出，如果`left == 1, right == 0` 怎么办？其实这种条件在情况2中已经判断过了，如图：<br />![2020122920362355.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689062722279-55c7ef56-5a32-4be6-b1f5-c04c94bfe0b6.png#averageHue=%23f7f5f5&clientId=u5f1fc337-0de8-4&from=paste&height=533&id=u097439a7&originHeight=624&originWidth=778&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=67009&status=done&style=none&taskId=u4a6fc101-0777-4189-901c-3fb029ef5a2&title=&width=664.957289347753)<br />这种情况也是大多数同学容易迷惑的情况。

4. **情况4：头结点没有覆盖**

以上都处理完了，递归结束之后，可能头结点 还有一个无覆盖的情况，如图：<br />![20201229203742446.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689062746913-a0865436-eff4-41d2-8997-77a8e438b8a8.png#averageHue=%23f8f7f7&clientId=u5f1fc337-0de8-4&from=paste&height=679&id=u06c8ac1a&originHeight=794&originWidth=1034&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=90020&status=done&style=none&taskId=uad3957a0-291d-41ba-9015-18d46861f8a&title=&width=883.7607161768336)<br />所以递归结束之后，还要判断根节点，如果没有覆盖，result++，代码如下：
```java
int minCameraCover(TreeNode root) {
    result = 0;
    if (traversal(root) == 0) { // root 无覆盖
        result++;
    }
    return result;
}
```
以上四种情况我们分析完了，代码也差不多了，整体代码如下：（以下我的代码注释很详细，为了把情况说清楚，特别把每种情况列出来。）
```java
class Solution {
    int  res=0;
    public int minCameraCover(TreeNode root) {
        // 对根节点的状态做检验,防止根节点是无覆盖状态 .
        if(minCame(root)==0){
            res++;
        }
        return res;
    }
    /**
     节点的状态值：
       0 表示无覆盖
       1 表示 有摄像头
       2 表示有覆盖
    后序遍历，根据左右节点的情况,来判读 自己的状态
     */
    public int minCame(TreeNode root){
        if(root==null){
            // 空节点默认为 有覆盖状态，避免在叶子节点上放摄像头
            return 2;
        }
        int left=minCame(root.left);
        int  right=minCame(root.right);

        // 如果左右节点都覆盖了的话, 那么本节点的状态就应该是无覆盖,没有摄像头
        if(left==2&&right==2){
            //(2,2)
            return 0;
        }else if(left==0||right==0){
            // 左右节点都是无覆盖状态,那 根节点此时应该放一个摄像头
            // (0,0) (0,1) (0,2) (1,0) (2,0)
            // 状态值为 1 摄像头数 ++;
            res++;
            return 1;
        }else{
            // 左右节点的 状态为 (1,1) (1,2) (2,1) 也就是左右节点至少存在 1个摄像头，
            // 那么本节点就是处于被覆盖状态
            return 2;
        }
    }
}
```
-
<a name="OTtQP"></a>
# 总结 
可以看看贪心算法的总结，贪心本来就没啥规律，能写出个总结篇真的不容易了。 <br />[代码随想录](https://programmercarl.com/%E8%B4%AA%E5%BF%83%E7%AE%97%E6%B3%95%E6%80%BB%E7%BB%93%E7%AF%87.html)
