时间: 2023.6.26 周一
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part09, 198.打家劫舍, 213.打家劫舍II. 337.打家劫舍III
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [ ] <br />

今天就是打家劫舍的一天，这个系列不算难，大家可以一口气拿下。
<a name="XGtAV"></a>
# 198.打家劫舍
:::info
视频讲解：[https://www.bilibili.com/video/BV1Te411N7SX](https://www.bilibili.com/video/BV1Te411N7SX)<br />[https://programmercarl.com/0198.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8D.html](https://programmercarl.com/0198.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8D.html)
:::
:::info
中等题   ([数组](https://leetcode.cn/tag/array/), [动态规划](https://leetcode.cn/tag/dynamic-programming/))
:::
大家如果刚接触这样的题目，会有点困惑，当前的状态我是偷还是不偷呢？仔细一想，当前房屋偷与不偷取决于 前一个房屋和前两个房屋是否被偷了。<br />所以这里就更感觉到，**当前状态和前面状态会有一种依赖关系**，**那么这种依赖关系都是动规的递推公式**。<br />以上是大概思路，打家劫舍是dp解决的经典问题，接下来我们来动规五部曲分析如下：

1. **确定dp数组（dp table）以及下标的含义**

dp[i]：考虑下标i（包括i）以内的房屋，最多可以偷窃的金额为dp[i]。

2. **确定递推公式**

决定dp[i]的因素就是第i房间偷还是不偷。<br />如果偷第i房间，那么`dp[i] = dp[i - 2] + nums[i]`，即：第i-1房一定是不考虑的，找出 下标i-2（包括i-2）以内的房屋，最多可以偷窃的金额为`dp[i-2]`加上第i房间偷到的钱`nums[i]`。<br />如果不偷第i房间，那么`dp[i] = dp[i - 1]`，即考 虑i-1房，（注意这里是考虑，并不是一定要偷i-1房，这是很多同学容易混淆的点）<br />然后dp[i]取最大值，即`dp[i] = max(dp[i-2]+nums[i], dp[i-1]);`

3. **dp数组如何初始化**

从递推公式`dp[i] = max(dp[i-2]+nums[i], dp[i-1]);`可以看出，递推公式的基础就是 dp[0] 和 dp[1]<br />从dp[i]的定义上来讲，dp[0] 一定是 nums[0]，dp[1]就是nums[0]和nums[1]的最大值即：`dp[1] = max(nums[0], nums[1]);`<br />代码如下：
```java
int[] dp = new int[nums.length];
dp[0] = nums[0];
dp[1] = Math.max(dp[0], nums[1]);
```

4. **确定遍历顺序**

dp[i] 是根据 dp[i - 2] 和 dp[i - 1] 推导出来的，那么一定是从前到后遍历！代码如下：
```java
for (int i = 2; i < nums.length; i++) {
    dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
}
```

5. **举例推导dp数组**

以示例二，输入[2,7,9,3,1]为例。<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1691547387661-fb4fee95-7df1-4b86-b4e3-e4f76c78c313.png#averageHue=%23f4f3f3&clientId=ub1147e76-62dd-4&from=paste&height=276&id=uc7c245f1&originHeight=323&originWidth=734&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=58062&status=done&style=shadow&taskId=u3afe98e6-1da6-4db5-8f80-0bef9c05590&title=&width=627.3504503615047)<br />红框`dp[nums.size() - 1]`为结果。
```java
// 动态规划
class Solution {
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];

		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		dp[1] = Math.max(dp[0], nums[1]);
		for (int i = 2; i < nums.length; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
		}

		return dp[nums.length - 1];
	}
}
```
```java
// 使用滚动数组思想，优化空间
// 分析本题可以发现，所求结果仅依赖于前两种状态，此时可以使用滚动数组思想将空间复杂度降低为3个空间
class Solution {
    public int rob(int[] nums) {
        
        int len = nums.length;

        if (len == 0) return 0;
        else if (len == 1) return nums[0];
        else if (len == 2) return Math.max(nums[0],nums[1]);


        int[] result = new int[3]; //存放选择的结果
        result[0] = nums[0];
        result[1] = Math.max(nums[0],nums[1]);
        

        for(int i=2;i<len;i++){

            result[2] = Math.max(result[0]+nums[i],result[1]);

            result[0] = result[1];
            result[1] = result[2];
        }
        
        return result[2];
    }
}
```
```java
// 进一步对滚动数组的空间优化 dp数组只存与计算相关的两次数据
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1)  {
            return nums[0];
        }
        // 初始化dp数组
        // 优化空间 dp数组只用2格空间 只记录与当前计算相关的前两个结果
        int[] dp = new int[2];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        int res = 0;
        // 遍历
        for (int i = 2; i < nums.length; i++) {
            res = Math.max((dp[0] + nums[i]) , dp[1] );
            dp[0] = dp[1];
            dp[1] = res;
        }
        // 输出结果
        return dp[1];
    }
}
```
-
<a name="eQisZ"></a>
# 213.打家劫舍II
:::info
视频讲解：[https://www.bilibili.com/video/BV1oM411B7xq](https://www.bilibili.com/video/BV1oM411B7xq)<br />[https://programmercarl.com/0213.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8DII.html](https://programmercarl.com/0213.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8DII.html)
:::
:::info
中等题   ([数组](https://leetcode.cn/tag/array/), [动态规划](https://leetcode.cn/tag/dynamic-programming/))<br />这道题目和 `198.打家劫舍` 差不多的，唯一区别就是成环了。
:::
对于一个数组，成环的话主要有如下三种情况：

- 情况一：考虑不包含首尾元素

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1691548948522-6fc964e2-35b3-4941-8c4a-a913df335252.png#averageHue=%23f0eeee&clientId=ue2f03668-da7a-4&from=paste&height=194&id=u245688df&originHeight=227&originWidth=683&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=53267&status=done&style=shadow&taskId=u1177245e-890e-4c4f-9ef5-7680002e467&title=&width=583.7607051728988)

- 情况二：考虑包含首元素，不包含尾元素

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1691548964992-924667e3-ed9e-4517-b228-650aa115a2a2.png#averageHue=%23eeeded&clientId=ue2f03668-da7a-4&from=paste&height=179&id=u17bb49af&originHeight=210&originWidth=696&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=49219&status=done&style=shadow&taskId=u0dffc4da-4319-4fdd-bfa8-9a994d6fa98&title=&width=594.871816691563)

- 情况三：考虑包含尾元素，不包含首元素

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1691548980003-236ebb70-a7fc-476b-8468-4d2c26ddf599.png#averageHue=%23edecec&clientId=ue2f03668-da7a-4&from=paste&height=172&id=udaf06a2e&originHeight=201&originWidth=699&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=51810&status=done&style=shadow&taskId=ud0a8f256-f22b-4bf6-9c13-40899c4500f&title=&width=597.4359193497164)<br />注意我这里用的是"考虑"，例如情况三，**虽然是考虑包含尾元素，但不一定要选尾部元素**！ 对于情况三，取nums[1] 和 nums[3]就是最大的。而 情况二 和 情况三 都包含了情况一了，所以只考虑情况二和情况三就可以了。<br />分析到这里，本题其实比较简单了。 剩下的和`198.打家劫舍`就是一样的了。
```java
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];

        int result1 = robAction(nums, 0, len - 1);
        int result2 = robAction(nums, 1, len);
        
        return Math.max(result1, result2);
    }
	// 198.打家劫舍的逻辑
    int robAction(int[] nums, int start, int end) {
        if(start == end) return nums[start];
        
        int x = 0, y = 0, z = 0;
        
        for (int i = start; i < end; i++) {
            y = z;
            z = Math.max(y, x + nums[i]);
            x = y;
        }
        return z;
    }
}
```
![上下两种写法的区别](https://cdn.nlark.com/yuque/0/2023/png/32832913/1691549871194-6b75971e-556f-4ce5-9645-5ba08855b762.png#averageHue=%23292e37&clientId=ue2f03668-da7a-4&from=paste&height=229&id=ud28ba780&originHeight=268&originWidth=1483&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=true&size=49640&status=done&style=shadow&taskId=uc937cf80-7920-4b61-aaca-828066eb417&title=%E4%B8%8A%E4%B8%8B%E4%B8%A4%E7%A7%8D%E5%86%99%E6%B3%95%E7%9A%84%E5%8C%BA%E5%88%AB&width=1267.521414013776 "上下两种写法的区别")
```java
/**
 * 使用数组
 * @param nums
 * @return
 */
public int rob2(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int len = nums.length;
    if (len == 1) return nums[0];

    int result1 = robAction2(nums, 0, len - 2);  // 情况二
    int result2 = robAction2(nums, 1, len-1);  // 情况三

    return Math.max(result1, result2);
}
// 198.打家劫舍的逻辑
int robAction2(int[] nums, int start, int end) {
    if(start == end) return nums[start];

    int[] dp = new int[nums.length];
    dp[start] = nums[start];
    dp[start+1] = Math.max(dp[start], nums[start+1]);
    for (int i=start+2; i<=end; i++){  // 注意这里范围的限制 i<=end
        dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
    }
    return dp[end];
}
时间复杂度: O(n)
空间复杂度: O(n)
```
成环之后还是难了一些的， 不少题解没有把“考虑房间”和“偷房间”说清楚。<br />这就导致大家会有这样的困惑：情况三怎么就包含了情况一了呢？ 本文图中最后一间房不能偷啊，偷了一定不是最优结果。<br />所以我在本文重点强调了情况一二三是“考虑”的范围，而具体房间偷与不偷交给递推公式去抉择。<br />这样大家就不难理解情况二和情况三包含了情况一了。
<a name="r3ARw"></a>
# 337.打家劫舍III
:::info
视频讲解：[https://www.bilibili.com/video/BV1H24y1Q7sY](https://www.bilibili.com/video/BV1H24y1Q7sY)<br />[https://programmercarl.com/0337.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8DIII.html](https://programmercarl.com/0337.%E6%89%93%E5%AE%B6%E5%8A%AB%E8%88%8DIII.html)
:::
:::info
中等题   ([树](https://leetcode.cn/tag/tree/), [深度优先搜索](https://leetcode.cn/tag/depth-first-search/), [动态规划](https://leetcode.cn/tag/dynamic-programming/), [二叉树](https://leetcode.cn/tag/binary-tree/))<br />这道题目和`198.打家劫舍`，`213.打家劫舍II`也是如出一辙，只不过这个换成了树。
:::
如果对树的遍历不够熟悉的话，那本题就有难度了。<br />对于树的话，首先就要想到遍历方式，前中后序（深度优先搜索）还是层序遍历（广度优先搜索）。<br />**本题一定是要后序遍历**，因为通过递归函数的返回值来做下一步计算。<br />与198.打家劫舍，213.打家劫舍II一样，关键是要讨论当前节点抢还是不抢。如果抢了当前节点，两个孩子就不能动，如果没抢当前节点，就可以考虑抢左右孩子（**注意这里说的是“考虑**”)
<a name="OUiRj"></a>
## 暴力递归
```java
// 递归去偷，超时
public int rob(TreeNode root) {
    if (root == null) return 0;
    int money = root.val;
    if (root.left != null) {
        money += rob(root.left.left) + rob(root.left.right);
    }
    if (root.right != null) {
        money += rob(root.right.left) + rob(root.right.right);
    }
    return Math.max(money, rob(root.left) + rob(root.right));
}

时间复杂度：O(n^2)，这个时间复杂度不太标准，也不容易准确化，例如越往下的节点重复计算次数就越多
空间复杂度：O(log n)，算上递推系统栈的空间
```
当然以上代码超时了，这个递归的过程中其实是有重复计算了。<br />计算了root的四个孙子（左右孩子的孩子）为头结点的子树的情况，又计算了root的左右孩子为头结点的子树的情况，计算左右孩子的时候其实又把孙子计算了一遍
<a name="gPfGB"></a>
## 记忆化递推
所以可以使用一个map把计算过的结果保存一下，这样如果计算过孙子了，那么计算孩子的时候可以复用孙子节点的结果。
```java
// 2.递归去偷，记录状态
// 执行用时：3 ms , 在所有 Java 提交中击败了 56.24% 的用户
public int rob(TreeNode root) {
    Map<TreeNode, Integer> memo = new HashMap<>();
    return robAction(root, memo);
}

int robAction(TreeNode root, Map<TreeNode, Integer> memo) {
    
    if (root == null) return 0;
    if (memo.containsKey(root)) return memo.get(root);  // 如果计算过了该点就直接返回
    
    int money = root.val;
    if (root.left != null) {
        money += robAction(root.left.left, memo) + robAction(root.left.right, memo);
    }
    if (root.right != null) {
        money += robAction(root.right.left, memo) + robAction(root.right.right, memo);
    }
    int res = Math.max(money, robAction(root.left, memo) + robAction(root.right, memo));
    memo.put(root, res);
    return res;
}

时间复杂度：O(n)
空间复杂度：O(log n)，算上递推系统栈的空间
```
<a name="rixok"></a>
## 动态规划
在上面两种方法，其实对一个节点 偷与不偷 得到的最大金钱都没有做记录，而是需要实时计算。<br />而动态规划其实就是**使用状态转移容器来记录状态的变化**，这里可以**使用一个长度为2的数组，记录当前节点偷与不偷所得到的的最大金钱**。<br />这道题目算是**树形dp的入门题目**，因为是在树上进行状态转移，我们在讲解二叉树的时候说过递归三部曲，那么下面我以递归三部曲为框架，其中融合动规五部曲的内容来进行讲解。

1. **确定递归函数的参数和返回值**

这里我们要求一个节点 偷与不偷 的两个状态所得到的金钱，那么返回值就是一个长度为2的数组。<br />参数为当前节点，代码如下：
```java
int[] res = robAction1(root);
```
其实这里的返回数组就是dp数组。<br />所以**dp数组（dp table）以及下标的含义：下标为0记录不偷该节点所得到的的最大金钱，下标为1记录偷该节点所得到的的最大金钱**。<br />所以本题dp数组就是一个长度为2的数组！<br />那么有同学可能疑惑，长度为2的数组怎么标记树中每个节点的状态呢？<br />别忘了**在递归的过程中，系统栈会保存每一层递归的参数**。如果还不理解的话，就接着往下看，看到代码就理解了哈。

2. **确定终止条件**

在遍历的过程中，如果遇到空节点的话，很明显，无论偷还是不偷都是0 (Java中数组默认值就是0)，所以就返回
```java
if (root == null ) return res;
```
这也相当于dp数组的初始化

3. **确定遍历顺序**

首先明确的是使用**后序遍历**。 因为要通过递归函数的返回值来做下一步计算。<br />通过递归左节点，得到左节点偷与不偷的金钱。<br />通过递归右节点，得到右节点偷与不偷的金钱。
```java
 // 下标0: 不偷, 下标1:偷
int[] left = robAction1(root.left);  // 左
int[] right = robAction1(root.right);  // 右
```

4. **确定单层递归的逻辑**

如果是偷当前节点，那么左右孩子就不能偷，`res[1] = root.val + left[0] + right[0];`（如果对下标含义不理解就再回顾一下dp数组的含义）<br />如果不偷当前节点，那么左右孩子就可以偷，至于到底偷不偷一定是选一个最大的，所以：`res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);`<br />最后当前节点的状态就是`{res[0], res[1]};` , 即：{**不偷**当前节点得到的最大金钱，**偷**当前节点得到的最大金钱}
```java
// 下标0: 不偷, 下标1:偷
int[] left = robAction1(root.left);  // 左
int[] right = robAction1(root.right);  // 右

res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
res[1] = root.val + left[0] + right[0];
return res;
```

5. **举例推导dp数组**

以示例1为例，dp数组状态如下：（注意用后序遍历的方式推导）<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1691552575226-c84bb3d6-8c5b-408d-b70d-72cffadfb2cd.png#averageHue=%23f7f7f7&clientId=ue2f03668-da7a-4&from=paste&height=511&id=u7eae99fa&originHeight=598&originWidth=696&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=114014&status=done&style=shadow&taskId=ub9a556d5-bf79-4076-947e-b9de042b8fc&title=&width=594.871816691563)<br />最后头结点就是 取下标0 和 下标1的最大值就是偷得的最大金钱。<br />递归三部曲与动规五部曲分析完毕, 完整代码如下: 
```java
// 3.状态标记递归
// 执行用时：0 ms , 在所有 Java 提交中击败了 100% 的用户
// 不偷：Max(左孩子不偷，左孩子偷) + Max(又孩子不偷，右孩子偷)
// root[0] = Math.max(rob(root.left)[0], rob(root.left)[1]) +
// Math.max(rob(root.right)[0], rob(root.right)[1])
// 偷：左孩子不偷+ 右孩子不偷 + 当前节点偷
// root[1] = rob(root.left)[0] + rob(root.right)[0] + root.val;
public int rob3(TreeNode root) {
    int[] res = robAction1(root);
    return Math.max(res[0], res[1]);
}

int[] robAction1(TreeNode root) {
    int res[] = new int[2];
    if (root == null) return res;

    // 下标0: 不偷, 下标1:偷
    int[] left = robAction1(root.left);  // 左
    int[] right = robAction1(root.right);  // 右

    res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    res[1] = root.val + left[0] + right[0];
    return res;
}

时间复杂度：O(n)，每个节点只遍历了一次
空间复杂度：O(log n)，算上递推系统栈的空间
```
这道题是树形DP的入门题目，通过这道题目大家应该也了解了，所谓树形DP就是在树上进行递归公式的推导。所以树形DP也没有那么神秘！<br />只不过平时我们习惯了在一维数组或者二维数组上推导公式，一下子换成了树，就需要对树的遍历方式足够了解！
