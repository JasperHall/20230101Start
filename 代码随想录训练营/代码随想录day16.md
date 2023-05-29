时间: 2023.5.25
<a name="sPFyY"></a>
# 今日任务
第六章  二叉树part03<br />104.二叉树的最大深度  559.n叉树的最大深度, 111.二叉树的最小深度, 222.完全二叉树的节点个数
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 104.二叉树的最大深度 的 迭代法
- [ ] 559.n叉树的最大深度
- [ ] 111.二叉树的最小深度 的迭代法
- [ ] 222.完全二叉树的节点个数 的完全二叉树解法继续理解
<a name="XBsr0"></a>
# 复习
层序遍历有好多题可以做<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685275995754-8ac4ccde-ff67-4d49-a667-7b65f1b40af2.png#averageHue=%23fefdfc&clientId=ua91cdb4a-50ac-4&from=paste&height=292&id=u603d56dc&originHeight=342&originWidth=613&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=28603&status=done&style=shadow&taskId=uf3725483-7c26-49bf-a4c3-dcf870a8762&title=&width=523.9316431493221)

- [ ] 学习下LinkedLis, 整理教程
- [ ] 复习前中后序遍历的统一写法
- [ ] 复习226.翻转二叉树的递归法
- [ ] 对称二叉树想通过类型题目: 100.相同的树, 572.另一个树的子树

迭代法，大家可以直接过，二刷有精力的时候 再去掌握迭代法。
<a name="vRctM"></a>
# 104.二叉树的最大深度 （优先掌握递归）
:::info
什么是深度，什么是高度，如何求深度，如何求高度，这里有关系到二叉树的遍历方式。<br />大家 要先看视频讲解，就知道以上我说的内容了，很多录友刷过这道题，但理解的还不够。<br />题目链接/文章讲解/视频讲解： [https://programmercarl.com/0104.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E5%A4%A7%E6%B7%B1%E5%BA%A6.html](https://programmercarl.com/0104.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E5%A4%A7%E6%B7%B1%E5%BA%A6.html)
:::
<a name="taRpA"></a>
## 递归法
本题可以使用前序（中左右），也可以使用后序遍历（左右中），使用前序求的就是深度，使用后序求的是高度。

- 二叉树节点的深度：指从根节点到该节点的最长简单路径边的条数或者节点数（取决于深度从0开始还是从1开始）
- 二叉树节点的高度：指从该节点到叶子节点的最长简单路径边的条数或者节点数（取决于高度从0开始还是从1开始）

而**根节点的高度就是二叉树的最大深度**，所以本题中我们通过后序求的根节点高度来求的二叉树最大深度。这一点其实是很多同学没有想清楚的，很多题解同样没有讲清楚。<br />我先用**后序遍历(左右中)**来计算树的高度。

1. 确定**递归函数**的参数和返回值：参数就是传入树的根节点，返回就返回这棵树的深度，所以返回值为int类型。代码如下：
```java
int getDepth(TreeNode node);
```

2. 确定终止条件：如果为空节点的话，就返回0，表示高度为0。代码如下：
```java
if (node == NULL) return 0;
```

3. 确定单层递归的逻辑：先求它的左子树的深度，再求右子树的深度，最后取左右深度最大的数值 再**+1** （加1是因为算上当前中间节点）就是目前节点为根节点的树的深度。代码如下：
```java
int leftdepth = getdepth(node.left);       // 左
int rightdepth = getdepth(node.right);     // 右
int depth = 1 + max(leftdepth, rightdepth); // 中
return depth;
```
递归一直往下走, 所以最后是从下往上挨个+1上来的<br />完整代码如下: 
```java
class solution {
    /**
     * 递归法 求高度法
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
```
![对上边代码的解释](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685354360516-17a33ba2-70ca-42b3-b643-3eb187e43e5b.png#averageHue=%23f5f3ef&clientId=u44d7a7ea-63e2-4&from=paste&height=696&id=u504074b1&originHeight=814&originWidth=2393&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=true&size=237338&status=done&style=shadow&taskId=udd03541a-fd9e-4305-968d-33ecf8cef6d&title=%E5%AF%B9%E4%B8%8A%E8%BE%B9%E4%BB%A3%E7%A0%81%E7%9A%84%E8%A7%A3%E9%87%8A&width=2045.2992203202737 "对上边代码的解释")
```java
class Solution {
  /**
   * 递归法(求深度法)
   */
    //定义最大深度
    int maxnum = 0;

    public int maxDepth(TreeNode root) {
        ans(root,0);
        return maxnum;
    }
    
    //递归求解最大深度
    void ans(TreeNode tr,int tmp){
        if(tr==null) return;
        tmp++;
        maxnum = maxnum<tmp?tmp: maxnum;
        ans(tr.left,tmp);
        ans(tr.right,tmp);
        tmp--;
    }
}
```
![对求深度的代码过程记录](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685355852631-5f9813e1-69ba-4067-9efc-2aeb0c3adf9c.png#averageHue=%23f5f4f0&clientId=u44d7a7ea-63e2-4&from=paste&height=1033&id=u8257519b&originHeight=1209&originWidth=2369&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=true&size=266607&status=done&style=shadow&taskId=u4ed5447f-769a-42fe-b41a-9bd144b997a&title=%E5%AF%B9%E6%B1%82%E6%B7%B1%E5%BA%A6%E7%9A%84%E4%BB%A3%E7%A0%81%E8%BF%87%E7%A8%8B%E8%AE%B0%E5%BD%95&width=2024.7863990550472 "对求深度的代码过程记录")
<a name="cNXhM"></a>
## 迭代法
使用迭代法的话，使用**层序遍历**是最为合适的，因为最大的深度就是二叉树的层数，和层序遍历的方式极其吻合。<br />在二叉树中，一层一层的来遍历二叉树，记录一下遍历的层数就是二叉树的深度，如图所示：<br />![20200810193056585.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685355916531-49b04422-1505-480b-8bc9-21fdb29c1faa.png#averageHue=%23fcfcfc&clientId=u44d7a7ea-63e2-4&from=paste&height=467&id=u29ccf495&originHeight=546&originWidth=736&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=32956&status=done&style=shadow&taskId=u81f7a754-56a1-4a4c-bf6d-a58ee7c298c&title=&width=629.0598521336069)<br />所以这道题的迭代法就是一道模板题，可以使用二叉树层序遍历的模板来解决的。
```java
class solution {
    /**
     * 迭代法，使用层序遍历
     */
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int depth = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.poll();
                if (node.left != null) {
                    deque.offer(node.left);
                }
                if (node.right != null) {
                    deque.offer(node.right);
                }
            }
        }
        return depth;
    }
}
```
<a name="cj1C2"></a>
# 111.二叉树的最小深度 （优先掌握递归）
:::info
先看视频讲解，和最大深度 看似差不多，其实 差距还挺大，**有坑**。<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0111.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E5%B0%8F%E6%B7%B1%E5%BA%A6.html](https://programmercarl.com/0111.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E5%B0%8F%E6%B7%B1%E5%BA%A6.html)
:::
直觉上好像和求最大深度差不多，其实还是差不少的。<br />本题依然是**前序遍历和后序遍历都可以，前序求的是深度，后序求的是高度。**

- 二叉树节点的深度：指从根节点到该节点的最长简单路径边的条数或者节点数（取决于深度从0开始还是从1开始）
- 二叉树节点的高度：指从该节点到叶子节点的最长简单路径边的条数后者节点数（取决于高度从0开始还是从1开始）

那么使用后序遍历，其实求的是根节点到叶子节点的最小距离，就是求高度的过程，不过这个最小距离 也同样是最小深度。<br />以下讲解中遍历顺序上依然**采用后序遍历**<br />本题还有一个误区，在处理节点的过程中，最大深度很容易理解，最小深度就不那么好理解，如图：<br />![111.二叉树的最小深度.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685361531050-e7c322b4-f0ce-41a4-9ae8-d80ef3161132.png#averageHue=%23f9f8f8&clientId=u44d7a7ea-63e2-4&from=paste&height=639&id=ucb23e10e&originHeight=748&originWidth=958&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=84664&status=done&style=shadow&taskId=u72233157-0e3e-4020-aa95-b31a9c74058&title=&width=818.8034488369503)
:::danger
这就重新审题了，题目中说的是：最小深度是从根节点到最近叶子节点的最短路径上的节点数量。**注意是叶子节点。**<br />什么是叶子节点，左右孩子都为空的节点才是叶子节点！
:::
<a name="OAjKX"></a>
## 递归法
开始递归三部曲

1. 确定递归函数的返回值

参数为要传入的二叉树根节点，返回的是int类型的深度。
```java
int getDepth(TreeNode node)
```

2. 确定终止条件

终止条件也是遇到空节点返回0，表示当前节点的高度为0。
```java
if (node == null) return 0;
```

3. 确定单层递归的逻辑

这块和求最大深度可就不一样了，一些同学可能会写如下代码：
```java
int leftDepth = getDepth(node.left);
int rightDepth = getDepth(node.right);
int result = 1 + min(leftDepth, rightDepth);
return result;
```
这个代码就犯了此图中的误区：<br />![111.二叉树的最小深度 (1).png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685361725393-4153625c-1bbc-4486-be6a-b4012651e479.png#averageHue=%23f9f8f8&clientId=u44d7a7ea-63e2-4&from=paste&height=639&id=u32dd9bfd&originHeight=748&originWidth=958&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=84664&status=done&style=shadow&taskId=uc2b7948f-f09a-435e-b40c-84f37b5388f&title=&width=818.8034488369503)<br />如果这么求的话，没有左孩子的分支会算为最短深度。<br />所以，

- 如果左子树为空，右子树不为空，说明最小深度是 **1 + 右子树的深度。**
- 反之，右子树为空，左子树不为空，最小深度是** 1 + 左子树的深度。** 
- 最后如果左右子树都不为空，返回**左右子树深度最小值 + 1 。**

代码如下：
```java
int leftDepth = getDepth(node.left);           // 左
int rightDepth = getDepth(node.right);         // 右
                                                // 中
// 当一个左子树为空，右不为空，这时并不是最低点
if (node.left == NULL && node.right != NULL) { 
    return 1 + rightDepth;
}   
// 当一个右子树为空，左不为空，这时并不是最低点
if (node.left != NULL && node.right == NULL) { 
    return 1 + leftDepth;
}
int result = 1 + min(leftDepth, rightDepth);
return result;
```
遍历的顺序为后序（左右中），可以看出：求二叉树的最小深度和求二叉树的最大深度的差别主要在于处理左右孩子不为空的逻辑。
```java
class Solution {
    /**
     * 递归法, 比求最大深度要复杂一点
     * 因为最小深度是从根节点到最近**叶子节点**的最短路径上的节点数量
     * 后序遍历
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);

        // 这里得判断空值情况, 因为最小深度是从根节点到最近**叶子节点**的最短路径上的节点数量
        // 有叶子结点在算话
        if (root.left == null){
            return rightDepth+1;
        }else if (root.right == null){
            return leftDepth+1;
        }

        // 左右节点都不为空时
        return Math.min(leftDepth, rightDepth) + 1;
    }
}
```
下面是精简后的递归解法
```java
/**
 * 精简版
 * @param root
 * @return
 */
public int minDepth2(TreeNode root) {
    if (root == null) return 0;

    if (root.left==null && root.right!=null) return 1+minDepth2(root.right);

    if (root.left!=null && root.right==null) return 1+minDepth2(root.left);

    return 1 + Math.min(minDepth2(root.left), minDepth2(root.right));
}
```
精简之后的代码根本看不出是哪种遍历方式，所以依然还要强调一波：如果对二叉树的操作还不熟练，尽量不要直接照着精简代码来学。

<a name="KAojx"></a>
## 迭代法
相对于 `104.二叉树的最大深度 (leetcode)`，本题还可以使用**层序遍历**的方式来解决，思路是一样的。<br />需要注意的是，**只有当左右孩子都为空的时候，才说明遍历到最低点了。如果其中一个孩子不为空则不是最低点**, 代码如下:
```java
class Solution {
   /**
     * 迭代法，层序遍历
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int depth = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                if (poll.left == null && poll.right == null) {
                    // 是叶子结点，直接返回depth，因为从上往下遍历，所以该值就是最小值
                    return depth;
                }
                if (poll.left != null) {
                    deque.offer(poll.left);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
            }
        }
        return depth;
    }
}
```
<a name="Bm02n"></a>
# 222.完全二叉树的节点个数（优先掌握递归）
:::info
需要了解，普通二叉树 怎么求，完全二叉树又怎么求<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0222.%E5%AE%8C%E5%85%A8%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E8%8A%82%E7%82%B9%E4%B8%AA%E6%95%B0.html](https://programmercarl.com/0222.%E5%AE%8C%E5%85%A8%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E8%8A%82%E7%82%B9%E4%B8%AA%E6%95%B0.html)
:::
接下来会分为按照 普通二叉树的求法 和 利用完全二叉树的性质的求发来解题
<a name="vQ3pG"></a>
## 普通二叉树
首先按照普通二叉树的逻辑来求。<br />这道题目的递归法和求二叉树的深度写法类似， 而迭代法的遍历模板稍稍修改一下，记录遍历的节点数量就可以了。<br />递归遍历的顺序依然是后序遍历（左右中）。
<a name="hRTNU"></a>
### 递归法
开始递归三部曲

1. 确定递归函数的**参数**和**返回值**：参数就是传入树的根节点，返回就返回以该节点为根节点二叉树的节点数量，所以返回值为int类型。
```java
int getNodesNum(TreeNode cur);
```

2. 确定终止条件: 如果为空节点的话, 就返回0, 表示节点数为0,
```java
if (cur == null) return 0;
```

3. 确定单层递归的逻辑：**先求它的左子树**的节点数量，**再求右子树**的节点数量，最后取总和再加一 （加1是因为算上当前中间节点）就是目前节点为根节点的节点数量。
```java
int leftNum = getNodesNum(cur.left);      // 左
int rightNum = getNodesNum(cur.right);    // 右
int treeNum = leftNum + rightNum + 1;     // 中
return treeNum;
```
整体代码如下
```java
class Solution {
    /**
     * 普通二叉树的方式, 递归法
     * 后序遍历
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftNum = countNodes(root.left);  // 左
        int rightNum = countNodes(root.right);  // 右
        int treeNum = leftNum + rightNum + 1; // 中

        return treeNum;
    }
}
```
精简版代码如下
```java
class Solution {
    // 通用递归解法
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
```
网上基本都是这个精简的代码版本，其实不建议大家照着这个来写，代码确实精简，但隐藏了一些内容，连遍历的顺序都看不出来，所以初学者建议学习版本一的代码，稳稳的打基础。

- 时间复杂度：O(n)
- 空间复杂度：O(log n)，算上了递归系统栈占用的空间
<a name="L1jFo"></a>
### 迭代法
使用层序遍历, 把模板稍微变动, 加一个变量 result, 统计节点的数量就可以了
```java
class Solution {
    // 迭代法
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                TreeNode cur = queue.poll();
                result++;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return result;
    }
}
```
时间复杂度：O(n)<br />空间复杂度：O(n)
<a name="yhHA3"></a>
## 完全二叉树
:::danger
注意题目的要求输入的就已经是完全二叉树了
:::
在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。**若最底层为第 h 层，则该层包含 1~ 2^(h-1)  个节点**。<br />大家要自己看完全二叉树的定义，很多同学对完全二叉树其实不是真正的懂了。我来举一个典型的例子如题：<br />![20200920221638903-20230310123444151.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685369360718-0efe1d5a-57a0-4f98-9043-0043dddf109a.png#averageHue=%23fbf6f6&clientId=u87bbb258-bc3f-4&from=paste&height=573&id=u98e4fb5f&originHeight=670&originWidth=1426&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=119309&status=done&style=shadow&taskId=ub7fb8862-25c2-453f-b74f-66a3b0aef42&title=&width=1218.8034635088634)<br />完全二叉树只有两种情况，

- 情况一：就是满二叉树，
- 情况二：最后一层叶子节点没有满。

对于情况一，可以直接用 **2^树深度 - 1 **来计算，注意这里根节点深度为1。<br />对于情况二，分别递归左孩子，和右孩子，递归到某一深度一定会有左孩子或者右孩子为满二叉树，然后依然可以按照情况1来计算。<br />完全二叉树（一）如图：<br />![20201124092543662.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685369439625-a7aed5b2-d5a3-47d6-8743-3e7a303378d4.png#averageHue=%23f5f5f5&clientId=u87bbb258-bc3f-4&from=paste&height=726&id=u82ae7afa&originHeight=850&originWidth=1008&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=141098&status=done&style=shadow&taskId=u946262bd-f34a-4fa6-b257-738bbe4bf80&title=&width=861.5384931395051)<br />完全二叉树（二）如图：<br />![20201124092634138.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685369461627-49ee005a-4594-42d7-8e5e-692fcd219cd8.png#averageHue=%23f7f7f7&clientId=u87bbb258-bc3f-4&from=paste&height=709&id=ubd168d19&originHeight=830&originWidth=1014&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=131945&status=done&style=shadow&taskId=u89f40901-1535-46d1-a8c8-c7b468dff6e&title=&width=866.6666984558117)<br />可以看出如果整个树不是满二叉树，就递归其左右孩子，直到遇到满二叉树为止，用公式计算这个子树（满二叉树）的节点数量。<br />这里关键在于如何去判断一个左子树或者右子树是不是满二叉树呢？<br />**在完全二叉树中，如果递归向左遍历的深度等于递归向右遍历的深度，那说明就是满二叉树**。如图：<br />![20220829163554.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685369571800-e81ebde5-a697-49a0-9a57-03764c10580b.png#averageHue=%23f5f4f4&clientId=u87bbb258-bc3f-4&from=paste&height=554&id=uf24a05ef&originHeight=648&originWidth=1070&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=119086&status=done&style=shadow&taskId=u0d221edb-4f20-4669-8be0-61c7c55e5cc&title=&width=914.5299480746731)<br />在完全二叉树中，如果递归向左遍历的深度**不等于**递归向右遍历的深度，则说明**不是满二叉树**，如图：<br />![20220829163709.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685369807701-bd160639-2e4b-40b0-8333-4e0e05084444.png#averageHue=%23f6f5f5&clientId=u87bbb258-bc3f-4&from=paste&height=581&id=u81e68e9b&originHeight=680&originWidth=1116&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=116846&status=done&style=shadow&taskId=u2c892d13-58c9-440a-837b-b32b9386602&title=&width=953.8461888330236)<br />那有录友说了，这种情况，递归向左遍历的深度等于递归向右遍历的深度，但也不是满二叉树，如题：<br />![20220829163811.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685369875460-d6ac93c5-5c88-4bc5-b0d4-4e569ed53a07.png#averageHue=%23f6f4f4&clientId=u87bbb258-bc3f-4&from=paste&height=544&id=u3e91ba42&originHeight=636&originWidth=1110&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=114623&status=done&style=shadow&taskId=u1db55cb7-efb1-4db1-9c29-7dfc05dacce&title=&width=948.7179835167169)<br />如果这么想，大家就是对 完全二叉树理解有误区了，以上这棵二叉树，它根本就不是一个完全二叉树！

判断其子树是不是满二叉树, 如果是则利用公式计算这个子树(满二叉树)的节点数量, 如果不是则继续递归, 那么 在递归三部曲中，**第二部**：终止条件的写法应该是这样的：
```java
if (root == null) return 0; 
// 开始根据左深度和右深度是否相同来判断该子树是不是满二叉树
TreeNode left = root.left;
TreeNode right = root.right;
int leftDepth = 0, rightDepth = 0; // 这里初始为0是有目的的，为了下面求指数方便
while (left) {  // 求左子树深度
    left = left.left;
    leftDepth++;
}
while (right) { // 求右子树深度
    right = right.right;
    rightDepth++;
}
if (leftDepth == rightDepth) {
    return (2 << leftDepth) - 1; // 注意(2<<1) 相当于2^2，返回满足满二叉树的子树节点数量
}
```
递归三部曲，**第三部**，单层递归的逻辑：（可以看出使用后序遍历）
```java
int leftTreeNum = countNodes(root.left);       // 左
int rightTreeNum = countNodes(root.right);     // 右
int result = leftTreeNum + rightTreeNum + 1;    // 中
return result;
```
该部分精简之后的代码为:
```java
return countNodes(root.left) + countNodes(root.right) + 1; 
```
整体代码如下
```java
class Solution {
    /**
     * 针对完全二叉树的解法
     *
     * 满二叉树的结点数为：2^depth - 1
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        // 开始根据左深度和右深度是否相同来判断该子树是不是满二叉树
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftDepth = 0, rightDepth = 0; // 这里初始为0是有目的的，为了下面求指数方便
        while (left != null) {  // 求左子树深度
            left = left.left;
            leftDepth++;
        }
        while (right != null) { // 求右子树深度
            right = right.right;
            rightDepth++;
        }
        if (leftDepth == rightDepth) {
            return (2 << leftDepth) - 1; // 注意(2<<1) 相当于2^2，所以leftDepth初始为0
        }

        int leftTreeNum = countNodes(root.left);       // 左
        int rightTreeNum = countNodes(root.right);     // 右
        int result = leftTreeNum + rightTreeNum + 1;    // 中., 注意这里 +1
        return result;
        // 或
        // return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
```

