时间: 2023.6.2
<a name="sPFyY"></a>
# 今日任务
第七章 回溯算法part01, 理论基础, 77. 组合  
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 77.组合继续理解, 学习剪枝操作

<a name="XBsr0"></a>
# 复习

- [ ] 669. 修剪二叉搜索树  的迭代法
- [ ] 108.将有序数组转换为二叉搜索树  的递归法

-
<a name="sHanX"></a>
# 理论基础 
:::info
其实在讲解二叉树的时候，就给大家介绍过回溯，这次正式开启回溯算法，大家可以先看视频，对回溯算法有一个整体的了解。<br />题目链接/文章讲解：[https://programmercarl.com/%E5%9B%9E%E6%BA%AF%E7%AE%97%E6%B3%95%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html](https://programmercarl.com/%E5%9B%9E%E6%BA%AF%E7%AE%97%E6%B3%95%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)<br />视频讲解：[https://www.bilibili.com/video/BV1cy4y167mM](https://www.bilibili.com/video/BV1cy4y167mM)
:::

<a name="MQTDG"></a>
# 77. 组合  
:::info
对着 在 回溯算法理论基础 给出的 代码模板，来做本题组合问题，大家就会发现 写回溯算法套路。<br />在回溯算法解决实际问题的过程中，大家会有各种疑问，先看视频介绍，基本可以解决大家的疑惑。<br />本题关于剪枝操作是大家要理解的重点，因为后面很多回溯算法解决的题目，都是这个剪枝套路。 <br />题目链接/文章讲解：[https://programmercarl.com/0077.%E7%BB%84%E5%90%88.html](https://programmercarl.com/0077.%E7%BB%84%E5%90%88.html)<br />视频讲解：[https://www.bilibili.com/video/BV1ti4y1L7cv](https://www.bilibili.com/video/BV1ti4y1L7cv)<br />剪枝操作：[https://www.bilibili.com/video/BV1wi4y157er](https://www.bilibili.com/video/BV1wi4y157er)
:::
本题是回溯法的经典题目。直接的解法当然是使用for循环，例如示例中k为2，很容易想到 用两个for循环，这样就可以输出 和示例中一样的结果。代码如下：
```java
int n = 4;
for (int i = 1; i <= n; i++) {
    for (int j = i + 1; j <= n; j++) {
        System.out.pritln(i + " " + j);
    }
}

```
输入：n = 100, k = 3 那么就三层for循环，代码如下：
```java
int n = 100;
for (int i = 1; i <= n; i++) {
    for (int j = i + 1; j <= n; j++) {
        for (int u = j + 1; u <= n; n++) {
            System.out.pritln(i + " " + j + " " + u);
        }
    }
}
```
如果n为100，k为50呢，那就50层for循环，是不是开始窒息。此时就会发现虽然想暴力搜索，但是用for循环嵌套连暴力都写不出来！<br />咋整？回溯搜索法来了，虽然回溯法也是暴力，但至少能写出来，不像for循环嵌套k层让人绝望。<br />那么回溯法怎么暴力搜呢？上面我们说了要解决 n为100，k为50的情况，暴力写法需要嵌套50层for循环，那么回溯法就用递归来解决嵌套层数的问题。<br />**递归来做层叠嵌套（可以理解是开k层for循环），每一次的递归中嵌套一个for循环，那么递归就可以用于解决多层嵌套循环的问题了**。<br />此时递归的层数大家应该知道了，例如：n为100，k为50的情况下，就是递归50层。<br />一些同学本来对递归就懵，回溯法中递归还要嵌套for循环，可能就直接晕倒了！如果脑洞模拟回溯搜索的过程，绝对可以让人窒息，所以需要抽象图形结构来进一步理解。<br />那么把组合问题抽象为如下树形结构：<br />![20201123195223940.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685812623592-7c209c9e-82fc-43c6-8576-afba0d07f729.png#averageHue=%23f6f6f6&clientId=ue871b9c9-a463-4&from=paste&height=562&id=u963ecd9f&originHeight=658&originWidth=1260&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=92195&status=done&style=none&taskId=uf92305b6-c79f-40c7-b048-587a2963d17&title=&width=1076.9231164243813)<br />可以看出这棵树，一开始集合是 1，2，3，4， 从左向右取数，取过的数，不再重复取。<br />第一次取1，集合变为2，3，4 ，因为k为2，我们只需要再取一个数就可以了，分别取2，3，4，得到集合[1,2] [1,3] [1,4]，以此类推。<br />每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围。图中可以发现**n相当于树的宽度，k相当于树的深度**。<br />那么如何在这个树上遍历，然后收集到我们要的结果集呢？图中每次搜索到了叶子节点，我们就找到了一个结果。相当于只需要把达到叶子节点的结果收集起来，就可以求得 n个数中k个数的组合集合。
<a name="XqkmH"></a>
## 回溯法三部曲

1. **递归函数的返回值以及参数**

在这里要定义两个全局变量，一个用来存放符合条件单一结果，一个用来存放符合条件结果的集合。代码如下：
```java
int[][] result; // 存放符合条件结果的集合
int[] path; // 用来存放符合条件结果
```
其实不定义这两个全局变量也是可以的，把这两个变量放进递归函数的参数里，但函数里参数太多影响可读性，所以就定义全局变量了。<br />函数里一定有两个参数，既然是集合n里面取k个数，那么n和k是两个int型的参数。<br />然后还需要一个参数，为int型变量`startIndex`，这个参数用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。<br />为什么要有这个startIndex呢？`startIndex` 就是防止出现重复的组合。<br />从下图中红线部分可以看出，在集合 [1,2,3,4] 取1之后，下一层递归，就要在 [2,3,4] 中取数了，那么下一层递归如何知道从 [2,3,4] 中取数呢，靠的就是`startIndex`。<br />![20201123195328976.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685812965391-57d2f14e-cea6-461a-8ffc-fbc2061bf5aa.png#averageHue=%23f7f6f6&clientId=ue871b9c9-a463-4&from=paste&height=581&id=u278bc29f&originHeight=680&originWidth=1442&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=112594&status=done&style=none&taskId=uae73281f-9e80-4c6c-aa27-6e661ec4644&title=&width=1232.4786776856809)<br />所以需要`startIndex`来记录下一层递归，搜索的起始位置。那么整体代码如下：
```java
int[][] result; // 存放符合条件结果的集合
int[][] path; // 用来存放符合条件单一结果
void backtracking(int n, int k, int startIndex)
```

2. 回溯函数终止条件

什么时候到达所谓的叶子节点了呢? path这个数组的大小如果达到k, 说明我们找到了一个子集大小为k的组合了, 在图中path存的就是根节点到叶子节点的路径. 如图红色部分：<br />![20201123195407907.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685813041625-46134364-2e58-4f96-912c-2c1363a3730d.png#averageHue=%23f6f5f5&clientId=ue871b9c9-a463-4&from=paste&height=525&id=u311e5a07&originHeight=614&originWidth=1444&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=111810&status=done&style=none&taskId=ue377f6e4-b820-4f45-b48a-4237de1ab8a&title=&width=1234.1880794577833)<br />此时用 result二维数组，把path保存起来，并终止本层递归。所以终止条件代码如下：
```java
if (path.length == k) {
    result.push_back(path);
    return;
}
```

3. 单层搜索的过程

回溯法的搜索过程就是一个树型结构的遍历过程，在如下图中，可以看出for循环用来横向遍历，递归的过程是纵向遍历。<br />![20201123195242899.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685813105205-dcc243b1-5762-45e5-a0fc-5b133c8d4bd8.png#averageHue=%23f6f4f4&clientId=ue871b9c9-a463-4&from=paste&height=670&id=u1c4f9b9e&originHeight=784&originWidth=1360&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=125426&status=done&style=none&taskId=ud96bf902-da8f-4a56-9fa4-30ede6cf5d4&title=&width=1162.3932050294911)<br />如此我们才遍历完图中的这棵树。for循环每次从startIndex开始遍历，然后用path保存取到的节点i。代码如下：
```java
for (int i = startIndex; i <= n; i++) { // 控制树的横向遍历
    path.push_back(i); // 处理节点
    backtracking(n, k, i + 1); // 递归：控制树的纵向遍历，注意下一层搜索要从i+1开始
    path.pop_back(); // 回溯，撤销处理的节点
}
```
可以看出`backtracking`（递归函数）通过不断调用自己一直往深处遍历，总会遇到叶子节点，遇到了叶子节点就要返回。`backtracking`的下面部分就是回溯的操作了，撤销本次处理的结果。<br />关键地方都讲完了，组合问题完整代码如下：
```java
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        combineHelper(n, k, 1);
        return result;
    }

    /**
     * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠startIndex
     * @param startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
     */
    private void combineHelper(int n, int k, int startIndex){
        //终止条件
        if (path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++){
            path.add(i);
            combineHelper(n, k, i + 1);
            path.removeLast();
        }
    }
}
```
<a name="q6h81"></a>
## 剪枝优化
