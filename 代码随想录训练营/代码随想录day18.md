时间: 2023.5.27
<a name="sPFyY"></a>
# 今日任务
第六章 二叉树 part05<br />513.找树左下角的值, 112. 路径总和  , 113.路径总和ii, 106.从中序与后序遍历序列构造二叉树 ,105.从前序与中序遍历序列构造二叉树
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 112.路径总和 学习一下迭代的方法
- [ ] 113.路径总和ii 学习
- [ ] 105.从前序与中序遍历序列构造二叉树  做一下
- [ ] 106.从中序与后序遍历序列构造二叉树  复习,再看看讲解视频
<a name="XBsr0"></a>
# 复习

- [ ] 110.平衡二叉树  的迭代方式学一学
- [ ] 学习集合框架(List, ArrayLisrt),整理笔记梳理条理
- [ ] 257. 二叉树的所有路径 的迭代法学一下
- [ ] 404.左叶子之和 的迭代法学一下

<a name="WBg0t"></a>
# 513.找树左下角的值  
:::info
本题递归偏难，反而迭代简单属于模板题， 两种方法掌握一下 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0513.%E6%89%BE%E6%A0%91%E5%B7%A6%E4%B8%8B%E8%A7%92%E7%9A%84%E5%80%BC.html](https://programmercarl.com/0513.%E6%89%BE%E6%A0%91%E5%B7%A6%E4%B8%8B%E8%A7%92%E7%9A%84%E5%80%BC.html)
:::
本题要找出树的最后一行的最左边的值。此题用**层序遍历**是非常简单的，反而用**递归**的话会比较难一点。<br />注意对题目的理解, 是要找到最后一行的节点的最左侧的节点, 就算最后一行的节点只有最右侧子树有一个右节点, 则这个右节点也是最左侧节点
<a name="xWyNl"></a>
## 递归法
咋眼一看，这道题目用递归的话就就一直向左遍历，最后一个就是答案呗？但没有这么简单，一直向左遍历到最后一个，它未必是最后一行啊。<br />我们来分析一下题目：在树的最后一行找到最左边的值。**首先要是最后一行，然后是最左边的值。**<br />如果使用递归法，如何判断是最后一行呢，其实就是深度最大的叶子节点一定是最后一行。所以要找深度最大的叶子节点。<br />那么如何找最左边的呢？可以使用前序遍历（当然中序，后序都可以，因为**本题没有 中间节点的处理逻辑，只要左优先就行**），保证优先左边搜索，然后记录深度最大的叶子节点，此时就是树的最后一行最左边的值。

**递归三部曲：**

1. **确定递归函数的参数和返回值**

参数必须有要遍历的树的根节点，还有就是一个int型的变量用来记录最长深度。 这里就不需要返回值了，所以递归函数的返回类型为void。<br />本题还需要类里的两个全局变量，`maxLen`用来记录最大深度，`result`记录最大深度最左节点的数值。
```java
int maxDepth = INT_MIN;   // 全局变量 记录最大深度
int value;       // 全局变量 最大深度最左节点的数值
void traversal(TreeNode root, int depth)
```

2. **确定终止条件**. 当遇到叶子节点的时候，就需要统计一下最大的深度了，所以需要遇到叶子节点来更新最大深度。
```java
if (root.left == NULL && root.right == NULL) {  // 左右都为null, 则是叶子节点
    if (depth > maxDepth) {
        maxDepth = depth;           // 更新最大深度
        value = root.val;   // 最大深度最左面的数值
    }
    return;
}
```

3. **确定单层递归的逻辑**. 在找最大深度的时候，递归的过程中依然要使用回溯，代码如下：
```java
                    // 中  本题的逻辑不需要 中
if (root.left != null ) {   // 左
    depth++; // 深度加一
    traversal(root.left, depth);
    depth--; // 回溯，深度减一
}
if (root.right != null) { // 右
    depth++; // 深度加一
    traversal(root.right, depth);
    depth--; // 回溯，深度减一
}
return;
```
完整代码如下:
```java
/**
 * 递归 易懂版
 * @param root
 * @return
 */
private int maxDepth2 = -1;  // 全局变量 记录最大深度
private  int value2 = 0;  // 全局变量 最大深度最左节点的数值
public int findBottomLeftValue2(TreeNode root) {
    value2 = root.val;
    findLeftValue2(root, 0);
    return value2;
}
private void findLeftValue2(TreeNode root, int deep){
    if (root == null) return;

    if (root.left==null && root.right==null){  // 左右都为null, 则是叶子节点
        if (deep > maxDepth2){
            maxDepth2 = deep;  // 更新最大深度
            value2 = root.val;  // 最大深度最左面的数值
        }
        // return;  // 有没有return都行
    }

    if (root.left != null) {
        deep++;
        findLeftValue2(root.left, deep);
        deep--;  // 回溯
    }
    if (root.right != null) {
        deep++;
        findLeftValue2(root.right, deep);
        deep--;  // 回溯
    }
    // return;   // 有没有return都行
}
```
当然回溯的地方可以精简，精简代码如下：
```java
/**
 * 递归  精简版
 * @param root
 * @return
 */
private int maxDepth = -1;  // 全局变量 记录最大深度
private int value = 0;  // 全局变量 最大深度最左节点的数值
public int findBottomLeftValue(TreeNode root) {
    value = root.val;
    findLeftValue(root, 0);
    return value;
}
private void findLeftValue(TreeNode root, int deep){
    if (root == null) return;

    if (root.left==null && root.right==null){  // 左右都为null, 则是叶子节点
        if (deep > maxDepth){
            maxDepth = deep;  // 更新最大深度
            value = root.val;  // 最大深度最左面的数值
        }
    }
    if (root.left != null) findLeftValue(root.left, deep+1);  // 隐藏着回溯
    if (root.right != null) findLeftValue(root.right, deep+1);  // 隐藏着回溯
}
```
如果对回溯部分精简的代码 不理解的话，可以看这篇[257. 二叉树的所有路径](https://programmercarl.com/0257.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%89%80%E6%9C%89%E8%B7%AF%E5%BE%84.html#%E9%80%92%E5%BD%92)
<a name="XygiS"></a>
## 迭代法
本题使用层序遍历再合适不过了，比递归要好理解得多！只需要记录最后一行第一个节点的数值就可以了。<br />把层序遍历的模板，稍作修改就可以过了这道题。代码如下：
```java
//迭代法
class Solution {

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (i == 0) {
                    res = poll.val;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return res;
    }
}
```
<a name="g66V5"></a>
## 总结
![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685447773501-4bd26988-73dc-42e4-9049-99c9420e18ef.png#averageHue=%23fdfcfa&clientId=u816c4de0-b75a-4&from=paste&height=180&id=u92d33c8e&originHeight=211&originWidth=789&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=33136&status=done&style=shadow&taskId=u33934bee-3c8a-465c-9f73-bbf08f86650&title=&width=674.358999094315)
<a name="pgzdq"></a>
# 112.路径总和  
:::info
本题 又一次设计要回溯的过程，而且回溯的过程隐藏的还挺深，建议先看视频来理解 <br />112. 路径总和，和 113. 路径总和ii 一起做了。 优先掌握递归法。<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0112.%E8%B7%AF%E5%BE%84%E6%80%BB%E5%92%8C.html](https://programmercarl.com/0112.%E8%B7%AF%E5%BE%84%E6%80%BB%E5%92%8C.html)
:::
-<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685451668079-cb80818f-06e8-4514-9171-75b9d8a7211b.png#averageHue=%23fdfcfb&clientId=u816c4de0-b75a-4&from=paste&height=213&id=u945809d6&originHeight=249&originWidth=1079&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=66092&status=done&style=shadow&taskId=ud00a7bcc-e10b-4401-9d27-66b4b3fdd9d&title=&width=922.222256049133)<br />遍历从根节点到叶子节点的路径看看总和是不是目标和。
<a name="P1biH"></a>
## 递归法(回溯)
可以使用深度优先遍历的方式（本题前中后序都可以，无所谓，因为**中节点没有处理逻辑**）来遍历二叉树<br />**开始递归三部曲:**

1. 确定递归函数的**参数**和**返回类型**

**参数**：需要二叉树的根节点，还需要一个计数器，这个计数器用来计算二叉树的一条边之和是否正好是目标和，计数器为int型。<br />再来看**返回值**: 递归函数什么时候需要返回值？什么时候不需要返回值？这里总结如下三点：

   - 如果需要搜索整棵二叉树且不用处理递归返回值，递归函数就**不要返回值**。（这种情况在 113.路径总和II 中遇到）
   - 如果需要搜索整棵二叉树且需要处理递归返回值，递归函数就**需要返回值**。
   - 如果要搜索其中一条符合条件的路径，那么递归一定**需要返回值，因为遇到符合条件的路径了就要及时返回**。（**本题的情况**）

而本题我们要找一条符合条件的路径，所以递归函数需要返回值，及时返回，那么返回类型是什么呢？如图所示：<br />![2021020316051216.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685452096535-5cd4a11c-07f4-46a5-a312-f757522bf9f1.png#averageHue=%23f8f7f7&clientId=u816c4de0-b75a-4&from=paste&height=677&id=uc498b68a&originHeight=792&originWidth=892&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=107003&status=done&style=shadow&taskId=uc6c00d13-4106-4956-ae55-77fe42ffc9a&title=&width=762.393190357578)<br />图中可以看出，遍历的路线，并不要遍历整棵树，所以递归函数需要返回值，可以用`boolean`类型表示。所以代码如下：
```java
boolean traversal(TreeNode cur, int count);  // 注意函数的返回类型
```

2. **确定终止条件**

首先计数器如何统计这一条路径的和呢？不要去累加然后判断是否等于目标和，那么代码比较麻烦，可以用递减，让计数器count初始为目标和，然后每次减去遍历路径节点上的数值。

- 如果最后`count == 0`，同时到了叶子节点的话，说明找到了目标和。
- 如果遍历到了叶子节点，count不为0，就是没找到。递归终止条件代码如下：
```java
if (cur.left!=null  && cur.right!=null && count == 0) return true; // 遇到叶子节点，并且计数为0
if (cur.left!=null && cur.right!=null) return false; // 遇到叶子节点而没有找到合适的边，直接返回
```

3. **确定单层递归的逻辑**. 因为终止条件是判断叶子节点，所以递归的过程中就不要让空节点进入递归了。递归函数是有返回值的，如果递归函数返回true，说明找到了合适的路径，应该立刻返回。代码如下：
```java
if (cur.left!=null) { // 左 （空节点不遍历）
    // 遇到叶子节点返回true，则直接返回true
    if (traversal(cur.left, count - cur.left.val)) return true; // 注意这里有回溯的逻辑
}
if (cur.right!=null) { // 右 （空节点不遍历）
    // 遇到叶子节点返回true，则直接返回true
    if (traversal(cur.right, count - cur.right.val)) return true; // 注意这里有回溯的逻辑
}
return false;
```
以上代码中是包含着回溯的，没有回溯，如何后撤重新找另一条路径呢。<br />回溯隐藏在`traversal(cur.left, count - cur.left.val)`这里， 因为把`count - cur.left.val`直接作为参数传进去，函数结束，count的数值没有改变。<br />为了把回溯的过程体现出来，可以改为如下代码：
```java
if (cur.left!=null) { // 左
    count -= cur.left.val; // 递归，处理节点;
    if (traversal(cur.left, count)) return true;
    count += cur.left.val; // 回溯，撤销处理结果
}
if (cur.right!=null) { // 右
    count -= cur.right.val;
    if (traversal(cur.right, count)) return true;
    count += cur.right.val;
}
return false;
```
完整代码如下
```java
class solution {
   public boolean haspathsum(treenode root, int targetsum) {
        if (root == null) {
            return false;
        }
        targetsum -= root.val;
        // 叶子结点
        if (root.left == null && root.right == null) {
            return targetsum == 0;  // 进入这里说明是到达了叶子节点, 判断此时的 targetsum == 0 是否为true
        } 
        if (root.left != null) {  // 左
            boolean left = haspathsum(root.left, targetsum);
            if (left) {      // 已经找到
                return true;
            }
        }
        if (root.right != null) {  / 右
            boolean right = haspathsum(root.right, targetsum);
            if (right) {     // 已经找到
                return true;
            }
        }
        return false;
    }
}
```
下面是精简后的代码: 
```java
// lc112 简洁方法
class solution {
    public boolean haspathsum(treenode root, int targetsum) {

        if (root == null) return false; // 为空退出

        // 叶子节点判断是否符合
        if (root.left == null && root.right == null) return root.val == targetsum;

        // 求两侧分支的路径和
        return haspathsum(root.left, targetsum - root.val) || haspathsum(root.right, targetsum - root.val);
    }
}
```
精简之后的代码，已经完全看不出分析的过程了，所以我们要把题目分析清楚之后，再追求代码精简。
<a name="zR9wE"></a>
## 迭代法
如果使用**栈**模拟递归的话，那么如果做回溯呢？此时栈里一个元素不仅要记录该节点指针，还要记录从头结点到该节点的路径数值总和。<br />c++就我们用pair结构来存放这个栈里的元素。<br />定义为：pair<TreeNode*, int> pair<节点指针，路径数值><br />这个为栈里的一个元素。<br />如下代码是使用栈模拟的前序遍历，如下：（详细注释）
```java
class solution {
    public boolean haspathsum(treenode root, int targetsum) {
        if(root == null) return false;
        stack<treenode> stack1 = new stack<>();
        stack<integer> stack2 = new stack<>();
        stack1.push(root);
        stack2.push(root.val);
        while(!stack1.isempty()) {
            int size = stack1.size();

            for(int i = 0; i < size; i++) {
                treenode node = stack1.pop();
                int sum = stack2.pop();

                // 如果该节点是叶子节点了，同时该节点的路径数值等于sum，那么就返回true
                if(node.left == null && node.right == null && sum == targetsum) {
                    return true;
                }
                // 右节点，压进去一个节点的时候，将该节点的路径数值也记录下来
                if(node.right != null){
                    stack1.push(node.right);
                    stack2.push(sum + node.right.val);
                }
                // 左节点，压进去一个节点的时候，将该节点的路径数值也记录下来
                if(node.left != null) {
                    stack1.push(node.left);
                    stack2.push(sum + node.left.val);
                }
            }
        }
        return false;
    }
}
```
或
```java
public boolean hasPathSum(TreeNode root, int targetSum) {
    Stack<TreeNode> treeNodeStack = new Stack<>();
    Stack<Integer> sumStack = new Stack<>();

    if(root == null)
        return false;
    treeNodeStack.add(root);
    sumStack.add(root.val);

    while(!treeNodeStack.isEmpty()){
        TreeNode curr = treeNodeStack.peek();
        int tempsum = sumStack.pop();
        if(curr != null){
            treeNodeStack.pop();
            treeNodeStack.add(curr);
            treeNodeStack.add(null);
            sumStack.add(tempsum);
            if(curr.right != null){
                treeNodeStack.add(curr.right);
                sumStack.add(tempsum + curr.right.val);
            }
            if(curr.left != null){
                treeNodeStack.add(curr.left);
                sumStack.add(tempsum + curr.left.val);
            }
        }else{
            treeNodeStack.pop();
            TreeNode temp = treeNodeStack.pop();
            if(temp.left == null && temp.right == null && tempsum == targetSum)
                return true;
        }
    }
    return false;
}
```
<a name="RfGCy"></a>
# 106.从中序与后序遍历序列构造二叉树 
:::info
本题算是比较难的二叉树题目了，大家先看视频来理解。 <br />106.从中序与后序遍历序列构造二叉树，105.从前序与中序遍历序列构造二叉树 一起做，思路一样的<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0106.%E4%BB%8E%E4%B8%AD%E5%BA%8F%E4%B8%8E%E5%90%8E%E5%BA%8F%E9%81%8D%E5%8E%86%E5%BA%8F%E5%88%97%E6%9E%84%E9%80%A0%E4%BA%8C%E5%8F%89%E6%A0%91.html](https://programmercarl.com/0106.%E4%BB%8E%E4%B8%AD%E5%BA%8F%E4%B8%8E%E5%90%8E%E5%BA%8F%E9%81%8D%E5%8E%86%E5%BA%8F%E5%88%97%E6%9E%84%E9%80%A0%E4%BA%8C%E5%8F%89%E6%A0%91.html)
:::
本题算是比较难的二叉树题目了，大家先看视频来理解。 `106.从中序与后序遍历序列构造二叉树`，`105.从前序与中序遍历序列构造二叉树` 一起做，思路一样的<br />[LeetCode：106.从中序与后序遍历序列构造二叉树  哔哩哔哩讲解视频链接](https://www.bilibili.com/video/BV1vW4y1i7dn/?vd_source=39e7d39df0e7ad00c675c5477b658ace)<br />首先回忆一下如何根据两个顺序构造一个唯一的二叉树，相信理论知识大家应该都清楚，就是以 后序数组 的最后一个元素为切割点，先切 中序数组，根据 中序数组，反过来再切 后序数组。一层一层切下去，**每次后序数组最后一个元素就是节点元素**。<br />如果让我们肉眼看两个序列，画一棵二叉树的话，应该分分钟都可以画出来。流程如图：<br />![20210203154249860.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685519198319-ea6f1743-2119-4d16-91b5-54a79948cf53.png#averageHue=%23f6f5f5&clientId=u0c8b16c5-373b-4&from=paste&height=672&id=ud7634962&originHeight=786&originWidth=1292&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=111608&status=done&style=shadow&taskId=uaaf77642-f7e5-4402-8ff1-0dfd69fa04b&title=&width=1104.2735447780165)<br />那么代码应该怎么写呢？说到一层一层切割，就应该想到了**递归**。来看一下一共分几步：<br />第一步：如果数组大小为零的话，说明是空节点了。<br />第二步：如果不为空，那么取后序数组最后一个元素作为节点元素。<br />第三步：找到后序数组最后一个元素在中序数组的位置，作为切割点<br />第四步：切割中序数组，切成中序左数组和中序右数组 （顺序别搞反了，一定是先切中序数组）<br />第五步：切割后序数组，切成后序左数组和后序右数组<br />第六步：递归处理左区间和右区间<br />不难写出如下代码：（先把框架写出来）
```java
// inorder中序数组 , postorder后序数组
private TreeNode buildHelper(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postorderStart, int postorderEnd){

    // 第一步
    if (postorder.length == 0) return null;  //  if(postorderStart == postorderEnd) return null;

    // 第二步：后序遍历数组最后一个元素，就是当前的中间节点
    int rootVal = postorder[postorderEnd - 1];
    TreeNode root = new TreeNode(rootVal);

    // 第三步：找切割点, 
    int middleIndex;
    for (middleIndex = inorderStart; middleIndex < inorderEnd; middleIndex++){
        if(inorder[middleIndex] == rootVal) break;  // 找到中序数组中和后序中相等的位置
    }

    // 第四步：切割中序数组，得到 中序左数组 和 中序右数组
    // 第五步：切割后序数组，得到 后序左数组和后序右数组

    // 第六步:递归处理左区间和右区间
    root.left = buildHelper(inorder, leftInorderStart, leftInorderEnd,  postorder, leftPostorderStart, leftPostorderEnd); // 这里都填左区间的值
    root.right = buildHelper(inorder, rightInorderStart, rightInorderEnd, postorder, rightPostorderStart, rightPostorderEnd);// 这里都填右区间的值

    return root;
}
```
**难点**是如何切割，以及边界值找不好很容易乱套。此时应该**注意确定切割的标准**，是左闭右开，还有左开右闭，还是左闭右闭，这个就是不变量，要在递归中保持这个不变量。在切割的过程中会产生四个区间，把握不好不变量的话，一会左闭右开，一会左闭右闭，必然乱套！<br />**首先要切割中序数组**，为什么先切割中序数组呢？切割点在后序数组的最后一个元素，就是用这个元素来切割中序数组的，所以必要先切割中序数组。<br />中序数组相对比较好切，找到切割点（后序数组的最后一个元素）在中序数组的位置，然后切割，如下代码中我坚持**左闭右开**的原则：
```java
// 第三步：找切割点,找到中序遍历的切割点
int middleIndex;
for (middleIndex = inorderStart; middleIndex < inorderEnd; middleIndex++){
    if(inorder[middleIndex] == rootVal) break;  // 找到中序数组中和后序中相等的位置
}

// 切割数组, 坚持区间 左闭右开
// 第四步: 切割中序数组, 得到 中序左数组 和 中序右数组
int leftInorderStart = inorderStart;  // 中序左数组起点, 就是本次递归传入的中序左数组起点
int leftInorderEnd = middleIndex;  // 中序左数组终点, 中序左数组的终点就是到刚找到的中间节点的位置

int rightInorderStart = middleIndex + 1;  // 中序右数组起点, 中序右数组的起点就是从刚找到的中间节点的下一个位置开始
int rightInorderEnd = inorderEnd;  // 中序右数组终点, 就是整个中序数组的终点
```
接下来就要切割后序数组了。<br />首先后序数组的最后一个元素指定不能要了，这是切割点 也是 当前二叉树中间节点的元素，已经用了。<br />后序数组的切割点怎么找？后序数组没有明确的切割元素来进行左右切割，不像中序数组有明确的切割点，切割点左右分开就可以了。<br />**此时有一个很重要的点，就是中序数组大小一定是和后序数组的大小相同的（这是必然）**。<br />中序数组我们都切成了** 左中序数组** 和 **右中序数组** 了，那么后序数组就可以按照 **左中序数组** 的大小来切割，切成 **左后序数组** 和 **右后序数组**。代码如下：
```java
// 第五步: 切割后序数组，得到 后序左数组 和 后序右数组
int leftPostorderStart = postorderStart;  // 后序左数组起点
int leftPostorderEnd = postorderStart + (middleIndex - inorderStart);  // 后序左数组终点,

int rightPostorderStart = leftPostorderEnd;  // 后序右数组起点, 后序左数组终点就是(因为是左闭右开的区间)
int rightPostorderEnd = postorderEnd - 1;  // 后序右数组终点, 这里减一是去除最后一个元素, 舍弃末尾元素，因为这个元素就是中间节点，已经用过了
```
此时，中序数组切成了 **左中序数组** 和** 右中序数组**，后序数组切割成 **左后序数组** 和 **右后序数组**。<br />接下来可以递归了，代码如下：
```java
 // 第六步:递归处理左区间和右区间
root.left = buildHelper(inorder, leftInorderStart, leftInorderEnd,  postorder, leftPostorderStart, leftPostorderEnd); // 这里都填左区间的值
root.right = buildHelper(inorder, rightInorderStart, rightInorderEnd, postorder, rightPostorderStart, rightPostorderEnd);// 这里都填右区间的值
```
<a name="zOnrh"></a>
## 完整代码如下
:::danger
可以学着输出一下切割后的数组的样子帮助理解
:::
```java
class Solution {
    public TreeNode buildTree2(int[] inorder, int[] postorder) {  // inorder中序数组 , postorder后序数组
        if(postorder.length == 0 || inorder.length == 0) return null;
        return buildHelper(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }
    private TreeNode buildHelper(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postorderStart, int postorderEnd){
        // 第一步
        if(postorderStart == postorderEnd) return null;

        // 第二步：后序遍历数组最后一个元素，就是当前的中间节点
        int rootVal = postorder[postorderEnd - 1];
        TreeNode root = new TreeNode(rootVal);

        // 第三步：找切割点,找到中序遍历的切割点
        int middleIndex;
        for (middleIndex = inorderStart; middleIndex < inorderEnd; middleIndex++){
            if(inorder[middleIndex] == rootVal) break;  // 找到中序数组中和后序中相等的位置
        }

        // 切割数组, 坚持区间 左闭右开
        // 第四步: 切割中序数组, 得到 中序左数组 和 中序右数组
        int leftInorderStart = inorderStart;  // 中序左数组起点, 就是本次递归传入的中序左数组起点
        int leftInorderEnd = middleIndex;  // 中序左数组终点, 中序左数组的终点就是到刚找到的中间节点的位置

        int rightInorderStart = middleIndex + 1;  // 中序右数组起点, 中序右数组的起点就是从刚找到的中间节点的下一个位置开始
        int rightInorderEnd = inorderEnd;  // 中序右数组终点, 就是整个中序数组的终点


        // 第五步: 切割后序数组，得到 后序左数组 和 后序右数组
        int leftPostorderStart = postorderStart;  // 后序左数组起点
        int leftPostorderEnd = postorderStart + (middleIndex - inorderStart);  // 后序左数组终点,

        int rightPostorderStart = leftPostorderEnd;  // 后序右数组起点, 后序左数组终点就是(因为是左闭右开的区间)
        int rightPostorderEnd = postorderEnd - 1;  // 后序右数组终点, 这里减一是去除最后一个元素, 舍弃末尾元素，因为这个元素就是中间节点，已经用过了

        // 第六步:递归处理左区间和右区间
        root.left = buildHelper(inorder, leftInorderStart, leftInorderEnd,  postorder, leftPostorderStart, leftPostorderEnd); // 这里都填左区间的值
        root.right = buildHelper(inorder, rightInorderStart, rightInorderEnd, postorder, rightPostorderStart, rightPostorderEnd);// 这里都填右区间的值

        return root;
    }
}
```
```java
/**
 * 解法一
 * @param inorder
 * @param postorder
 * @return
 */
Map<Integer, Integer> map;  // 方便根据数值查找位置
public TreeNode buildTree(int[] inorder, int[] postorder) {  // inorder中序数组 , postorder后序数组
    map = new HashMap<>();
    for (int i=0; i<inorder.length; i++){
        map.put(inorder[i], i);
    }
    return findNode(inorder, 0, inorder.length, postorder, 0, postorder.length);
}
private TreeNode findNode(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd){
    // 参数里的范围都是前闭后开
    if (inBegin>=inEnd || postBegin>=postEnd){  // 不满足左闭右开，说明没有元素，返回空树
        return null;
    }

    int rootIndex = map.get(postorder[postEnd-1]); // 找到后序遍历的最后一个元素在中序遍历中的位置, 利用了map的操作
    TreeNode root = new TreeNode(inorder[rootIndex]);  // 构造节点
    int lenOfLeft = rootIndex - inBegin;  // 保存中序左子树个数，用来确定后序数列的个数

    // 下面分别递归 root 的左右方向
    root.left = findNode(inorder, inBegin, rootIndex, postorder, postBegin, postBegin+lenOfLeft);
    // 注意理解下面rootIndex+1的用法, postEnd-1是为了去除最后一个元素
    root.right = findNode(inorder, rootIndex+1, inEnd, postorder, postBegin+lenOfLeft, postEnd-1); 

    return root;
}
```
<a name="Lpf91"></a>
## 总结
之前我们讲的二叉树题目都是各种遍历二叉树，这次开始构造二叉树了，思路其实比较简单，但是真正代码实现出来并不容易。所以要避免眼高手低，踏实地把代码写出来。<br />大家遇到这种题目的时候，要学会打日志来调试（如何打日志有时候也是个技术活），不要脑动模拟，脑动模拟很容易越想越乱。<br />认真研究完本篇，相信大家对二叉树的构造会清晰很多
