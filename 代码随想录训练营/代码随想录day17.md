时间: 2023.5.26
<a name="sPFyY"></a>
# 今日任务
第六章   二叉树part04<br />110.平衡二叉树 , 257. 二叉树的所有路径 , 404.左叶子之和 
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 110.平衡二叉树  的迭代方式学一学
- [ ] 学习集合框架(List, ArrayLisrt),整理笔记梳理条理
- [ ] 257. 二叉树的所有路径 的迭代法学一下
- [ ] 404.左叶子之和 的迭代法学一下
<a name="XBsr0"></a>
# 复习

- [ ] 104.二叉树的最大深度 的 迭代法
- [ ] 559.n叉树的最大深度
- [ ] 111.二叉树的最小深度 的迭代法
- [ ] 222.完全二叉树的节点个数 的完全二叉树解法继续理解

迭代法，大家可以直接过，二刷有精力的时候 再去掌握迭代法。
<a name="FopJz"></a>
# 110.平衡二叉树 （优先掌握递归）
:::info
再一次涉及到，什么是高度，什么是深度，可以巩固一下。<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0110.%E5%B9%B3%E8%A1%A1%E4%BA%8C%E5%8F%89%E6%A0%91.html](https://programmercarl.com/0110.%E5%B9%B3%E8%A1%A1%E4%BA%8C%E5%8F%89%E6%A0%91.html)
:::
<a name="IoUZk"></a>
## 题外拓展
咋眼一看这道题目和 `104.二叉树的最大深度(leetcode)`很像，其实有很大区别。<br />这里强调一下概念：

- 二叉树节点的深度：指从根节点到该节点的最长简单路径边的条数。
- 二叉树节点的高度：指从该节点到叶子节点的最长简单路径边的条数。

但leetcode中强调的深度和高度很明显是按照节点来计算的，如图：<br />![20210203155515650.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685379281067-db08665d-1f09-4311-b72d-31a025b0eea5.png#averageHue=%23f9f7f7&clientId=u87bbb258-bc3f-4&from=paste&height=810&id=u0770c806&originHeight=948&originWidth=1370&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=167260&status=done&style=shadow&taskId=uc300bf04-b853-4f83-b655-750b65d4f1b&title=&width=1170.940213890002)
> 关于根节点的深度究竟是 1 还是 0，不同的地方有不一样的标准，leetcode的题目中都是以节点为一度，即根节点深度是 1。但维基百科上定义用边为一度，即根节点的深度是0，我们暂时以leetcode为准（毕竟要在这上面刷题）。

因为

- 求深度可以**从上到下**去查 所以需要**前序遍历**（中左右），
- 而高度只能**从下到上**去查，所以只能**后序遍历**（左右中）

有的同学一定疑惑，为什么`104.二叉树的最大深度(leetcode)` 中求的是二叉树的最大深度，也用的是后序遍历。<br />那是因为**代码的逻辑其实是求的根节点的高度，而根节点的高度就是这棵树的最大深度**，所以才可以使用后序遍历。<br />在`104.二叉树的最大深度(leetcode)`(opens new window)中，如果真正求取二叉树的最大深度，代码应该写成如下：（前序遍历）
```cpp
class Solution {
public:
    int result;
    void getDepth(TreeNode* node, int depth) {
        result = depth > result ? depth : result; // 中

        if (node->left == NULL && node->right == NULL) return ;

        if (node->left) { // 左
            depth++;    // 深度+1
            getDepth(node->left, depth);
            depth--;    // 回溯，深度-1
        }
        if (node->right) { // 右
            depth++;    // 深度+1
            getDepth(node->right, depth);
            depth--;    // 回溯，深度-1
        }
        return ;
    }
    int maxDepth(TreeNode* root) {
        result = 0;
        if (root == NULL) return result;
        getDepth(root, 1);
        return result;
    }
};
```
可以看出使用了前序（中左右）的遍历顺序，这才是真正求深度的逻辑！<br />注意以上代码是为了把细节体现出来，简化一下代码如下
```cpp
class Solution {
public:
    int result;
    void getDepth(TreeNode* node, int depth) {
        result = depth > result ? depth : result; // 中
        if (node->left == NULL && node->right == NULL) return ;
        if (node->left) { // 左
            getDepth(node->left, depth + 1);
        }
        if (node->right) { // 右
            getDepth(node->right, depth + 1);
        }
        return ;
    }
    int maxDepth(TreeNode* root) {
        result = 0;
        if (root == 0) return result;
        getDepth(root, 1);
        return result;
    }
};
```
<a name="IJd5m"></a>
## 递归解题
此时大家应该明白了既然要求比较**高度**，必然是要后序遍历。<br />递归三步曲分析：

1. **明确递归函数的参数和返回值**

参数：当前传入节点。 返回值：以当前传入节点为根节点的树的高度。<br />**那么如何标记左右子树是否差值大于1呢？** 如果当前传入节点为根节点的二叉树已经不是二叉平衡树了，还返回高度的话就没有意义了。所以如果已经不是二叉平衡树了，可以返回 -1 来标记已经不符合平衡树的规则了。<br />代码如下：
```java
// -1 表示已经不是平衡二叉树了，否则返回值是以该节点为根节点树的高度
int getHeight(TreeNode node)
```

2. **明确终止条件**. 递归的过程中依然是遇到空节点了为终止, 返回0, 表示当前节点为根节点的树高度为0. 代码如下：
```java
if (node == NULL) return 0;
```

3. **明确单层递归的逻辑**

如何判断以当前传入节点为根节点的二叉树是否是平衡二叉树呢？当然是其左子树高度和其右子树高度的差值。<br />分别求出其左右子树的高度，然后

- 如果差值小于等于1，则返回当前二叉树的高度，
- 否则返回-1，表示已经不是二叉平衡树了。

代码如下：
```java
int leftHeight = getHeight(node.left); // 左
if (leftHeight == -1) return -1;
int rightHeight = getHeight(node.right); // 右
if (rightHeight == -1) return -1;

int result;

if (abs(leftHeight - rightHeight) > 1) {  // 中
    result = -1;
} else {
    result = 1 + max(leftHeight, rightHeight); // 以当前节点为根节点的树的最大高度
}

return result;
```
精简一下
```java
int leftHeight = getHeight(node->left);
if (leftHeight == -1) return -1;
int rightHeight = getHeight(node->right);
if (rightHeight == -1) return -1;
return abs(leftHeight - rightHeight) > 1 ? -1 : 1 + max(leftHeight, rightHeight);
```
此时递归的函数就已经写出来了，这个递归的函数传入节点指针，返回以该节点为根节点的二叉树的高度，如果不是二叉平衡树，则返回-1。<br />getHeight整体代码如下:
```java
private int getHeight(TreeNode root) {
    if (root == null) {
        return 0;
    }
    int leftHeight = getHeight(root.left);
    if (leftHeight == -1) {
        return -1;
    }
    int rightHeight = getHeight(root.right);
    if (rightHeight == -1) {
        return -1;
    }
    // 左右子树高度差大于1，return -1表示已经不是平衡树了
    if (Math.abs(leftHeight - rightHeight) > 1) {
        return -1;
    }
    return Math.max(leftHeight, rightHeight) + 1;
}
```
解题完整代码如下
```java
class Solution {
   /**
     * 递归法
     */
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;  // 不等于-1,则证明是平衡二叉树
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        // 左右子树高度差大于1，return -1表示已经不是平衡树了
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
```
:::danger
注意:

1. Math.abs(); 方法的使用
:::
![093cade53c47e3551e58bf8023c3106.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685381443779-33846d9a-33c0-464c-9bea-0293ed1667ef.png#averageHue=%23f6f5f1&clientId=u87bbb258-bc3f-4&from=paste&height=1179&id=u1cdde317&originHeight=1380&originWidth=2460&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=314677&status=done&style=shadow&taskId=ude1d5a0f-3a32-4e0b-83ae-1eb810e93a8&title=&width=2102.564179685697)
<a name="fPKus"></a>
## 迭代题解
在 `104.二叉树的最大深度`中我们可以使用层序遍历来求深度，但是就不能直接用层序遍历来求高度了，这就体现出求高度和求深度的不同。<br />本题的迭代方式可以先定义一个函数，专门用来求高度。<br />这个函数通过 **栈 **模拟的**后序遍历**找每一个节点的高度（其实是通过求传入节点为根节点的最大深度来求的高度）代码如下：
```java
/**
 * 层序遍历，求结点的高度
 */
public int getHeight(TreeNode root) {
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
```
然后再用栈来模拟后序遍历，遍历每一个节点的时候，再去判断左右孩子的高度是否符合，代码如下：
```java
 public boolean isBalanced(TreeNode root) {
    if (root == null) {
        return true;
    }
    Stack<TreeNode> stack = new Stack<>();
    TreeNode pre = null;
    while (root!= null || !stack.isEmpty()) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        TreeNode inNode = stack.peek();
        // 右结点为null或已经遍历过
        if (inNode.right == null || inNode.right == pre) {
            // 比较左右子树的高度差，输出
            if (Math.abs(getHeight(inNode.left) - getHeight(inNode.right)) > 1) {
                return false;
            }
            stack.pop();
            pre = inNode;
            root = null;// 当前结点下，没有要遍历的结点了
        } else {
            root = inNode.right;// 右结点还没遍历，遍历右结点
        }
    }
    return true;
}
```
迭代整体代码如下
```java
class Solution {
   /**
     * 迭代法，效率较低，计算高度时会重复遍历
     * 时间复杂度：O(n^2)
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root!= null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode inNode = stack.peek();
            // 右结点为null或已经遍历过
            if (inNode.right == null || inNode.right == pre) {
                // 比较左右子树的高度差，输出
                if (Math.abs(getHeight(inNode.left) - getHeight(inNode.right)) > 1) {
                    return false;
                }
                stack.pop();
                pre = inNode;
                root = null;// 当前结点下，没有要遍历的结点了
            } else {
                root = inNode.right;// 右结点还没遍历，遍历右结点
            }
        }
        return true;
    }

    /**
     * 层序遍历，求结点的高度
     */
    public int getHeight(TreeNode root) {
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
当然此题用迭代法，其实效率很低，因为没有很好的模拟回溯的过程，所以迭代法有很多重复的计算。<br />虽然理论上所有的递归都可以用迭代来实现，但是有的场景难度可能比较大。
> 例如：都知道回溯法其实就是递归，但是很少人用迭代的方式去实现回溯算法！

因为对于回溯算法已经是非常复杂的递归了，如果再用迭代的话，就是自己给自己找麻烦，效率也并不一定高。
```java
class Solution {
   /**
     * 优化迭代法，针对暴力迭代法的getHeight方法做优化，利用TreeNode.val来保存当前结点的高度，这样就不会有重复遍历
     * 获取高度算法时间复杂度可以降到O(1)，总的时间复杂度降为O(n)。
     * 时间复杂度：O(n)
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode inNode = stack.peek();
            // 右结点为null或已经遍历过
            if (inNode.right == null || inNode.right == pre) {
                // 输出
                if (Math.abs(getHeight(inNode.left) - getHeight(inNode.right)) > 1) {
                    return false;
                }
                stack.pop();
                pre = inNode;
                root = null;// 当前结点下，没有要遍历的结点了
            } else {
                root = inNode.right;// 右结点还没遍历，遍历右结点
            }
        }
        return true;
    }

    /**
     * 求结点的高度
     */
    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = root.left != null ? root.left.val : 0;
        int rightHeight = root.right != null ? root.right.val : 0;
        int height = Math.max(leftHeight, rightHeight) + 1;
        root.val = height;// 用TreeNode.val来保存当前结点的高度
        return height;
    }
}
```
<a name="vZwab"></a>
## 总结
通过本题可以了解求二叉树深度 和 二叉树高度的差异，求深度适合用前序遍历，而求高度适合用后序遍历。<br />本题迭代法其实有点复杂，大家可以有一个思路，也不一定说非要写出来。<br />但是**递归方式是一定要掌握的**！
<a name="UhJbl"></a>
# 257. 二叉树的所有路径 （优先掌握递归）  
:::info
这是大家第一次接触到回溯的过程， 我在视频里重点讲解了 本题为什么要有回溯，已经回溯的过程。 <br />如果对**回溯 **似懂非懂，没关系， 可以先有个印象。 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0257.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%89%80%E6%9C%89%E8%B7%AF%E5%BE%84.html](https://programmercarl.com/0257.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%89%80%E6%9C%89%E8%B7%AF%E5%BE%84.html)
:::
这道题目要求从 根节点 到 叶子 的路径，所以需要**前序遍历**，这样才方便让父节点指向孩子节点，找到对应的路径。<br />在这道题目中将第一次涉及到**回溯**，因为我们要把路径记录下来，需要回溯来回退一个路径再进入另一个路径。<br />前序遍历以及回溯的过程如图：<br />![20210204151702443.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685431095306-a9a330c8-103e-4706-9cef-643a9d5d0493.png#averageHue=%23f8f5f5&clientId=ue90c6826-8c51-4&from=paste&height=701&id=uf68c65a9&originHeight=820&originWidth=940&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=149321&status=done&style=shadow&taskId=uf11af55a-5d1f-470c-90a7-b4aa753b6a6&title=&width=803.4188328880306)<br />我们先使用递归的方式，来做**前序遍历**。要知道 **递归和回溯** 就是一家的，本题也需要回溯。
<a name="c26zN"></a>
## 递归解法

1. 递**归函数参数以及返回值**

要传入根节点，记录每一条路径的path，和存放结果集的result，这里递归不需要返回值，代码如下：
```java
void traversal(TreeNode cur, List<Integer> paths, List<String> result)
```

2. **确定递归终止条件**, 在写递归的时候都习惯了这么写：`if (cur == NULL) { 终止处理逻辑 };`

但是本题的终止条件这样写会很麻烦, 因为本题要找到叶子节点, 就开始结束的处理逻辑了(把路径放进result里）。<br />**那么什么时候算是找到了叶子节点？ 是当 cur不为空，其左右孩子都为空的时候，就找到叶子节点。**<br />所以本题的终止条件是：
```java
if (cur.left == NULL && cur.right == NULL) {
    终止处理逻辑
}
```
为什么没有判断`cur`是否为空呢，因为下面的逻辑可以控制空节点不入循环。<br />再来看一下终止处理的逻辑。<br />这里使用 `List<Integer>` 结构 path 来记录路径，所以要把`List<Integer>`结构的 path 转为 string 格式，再把这个 string 放进 result 里。<br />那么为什么使用了`List<Integer>`结构来记录路径呢？ 因为在下面处理单层递归逻辑的时候，要做回溯，使用`List<Integer>`方便来做回溯。<br />可能有的同学问了，我看有些人的代码也没有回溯啊。其实是有回溯的，只不过隐藏在函数调用时的参数赋值里，下文我还会提到。<br />这里我们先使用`List<Integer>`结构的 path 容器来记录路径，那么终止处理逻辑如下：
```java
if (cur.left == NULL && cur.right == NULL) { // 遇到叶子节点
    // 输出
    StringBuilder sb = new StringBuilder();// StringBuilder用来拼接字符串，速度更快
    for (int i = 0; i < paths.size() - 1; i++) {
        sb.append(paths.get(i)).append("->");
    }
    sb.append(paths.get(paths.size() - 1));// 记录最后一个节点(叶子节点)
    res.add(sb.toString());// 收集一个路径
    return;
}
```

3. **确定单层递归逻辑. **因为是前序遍历，需要**先处理中间节点**，中间节点就是我们要记录路径上的节点，先放进path中。`paths.push(cur.val);`, 然后是 **递归和回溯** 的过程，上面说过没有判断cur是否为空，那么在这里递归的时候，如果为空就不进行下一层递归了。所以递归前要加上判断语句，下面要递归的节点是否为空，如下
```java
if (cur.left) {
    traversal(cur.left, path, result);
}
if (cur.right) {
    traversal(cur.right, path, result);
}
```
**此时还没完**，递归完，要做回溯啊，因为path 不能一直加入节点，它还要删节点，然后才能加入新的节点。那么回溯要怎么回溯呢，一些同学会这么写，如下：
```java
if (cur.left) {
    traversal(cur.left, path, result);
}
if (cur.right) {
    traversal(cur.right, path, result);
}
paths.remoce(paths.size() - 1);
```
这个回溯就有很大的问题，我们知道, **回溯和递归是一一对应的**, 有一个递归, 就要有一个回溯, 这么写的话相当于把递归和回溯拆开了, 一个在花括号里，一个在花括号外。<br />所以**回溯要和递归永远在一起**，世界上最遥远的距离是你在花括号里，而我在花括号外！那么代码应该这么写：
```java
// 递归和回溯是同时进行，所以要放在同一个花括号里
if (root.left != null) { // 左
    traversal(root.left, paths, res);
    paths.remove(paths.size() - 1);// 回溯
}
if (root.right != null) { // 右
    traversal(root.right, paths, res);
    paths.remove(paths.size() - 1);// 回溯
}
```
整体的代码如下
```java
//解法一
class Solution {
    /**
     * 递归法回溯  前序遍历(中左右)
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();  // 存最终结果
        if (root == null) return res;

        List<Integer> paths = new ArrayList<>();  // 作为结果中的路径

        traversal(root, paths, res);
        return res;

    }
    private void traversal(TreeNode root, List<Integer> paths, List<String> result){
        paths.add(root.val);  // 前序遍历, 中

        // 遇到叶子节点
        if (root.left==null && root.right==null){
            // 输出
            StringBuilder sb = new StringBuilder();  // StringBuilder用来拼接字符串，速度更快
            for (int i=0; i<paths.size()-1; i++){  // 这里 i<paths.size()-1 的原因是:最后一个路径后面不能用 -> , 所以要在循环后面单独加append
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size()-1));  // 记录最后一个节点(叶子节点)

            result.add(sb.toString());  // sb转为字符串, 每组路径是结果列表的一个元素
            return;
        }

        // 递归和回溯是同时进行，所以要放在同一个花括号里
        if (root.left != null){
            traversal(root.left, paths, result);
            paths.remove(paths.size()-1);  // 回溯是要从path列表中删除已经遍历的节点
        }
        if (root.right != null){
            traversal(root.right, paths, result);
            paths.remove(paths.size()-1);
        }
    }
}
```
:::danger
注意:

1. StringBuilder用来拼接字符串
:::
把以上的代码进行精简
```java
/**
 * 回溯 精简版
 * @param root
 * @return
 */
public List<String> binaryTreePaths2(TreeNode root) {
    List<String> res = new ArrayList<>();  // 存最终结果
    if (root == null) return res;

    String paths=""; // 作为结果中的路径

    traversal2(root, paths, res);
    return res;
}
private void traversal2(TreeNode root, String paths, List<String> result){
    paths += String.valueOf(root.val);  // 中

    if (root.left==null && root.right==null){
        result.add(paths);
        return;
    }

    if (root.left != null) traversal2(root.left, paths+"->", result);  // 左
    if (root.right != null) traversal2(root.right, paths+"->", result);  // 右
}
```
如上代码精简了不少，也隐藏了不少东西。<br />注意在函数定义的时候`void traversal(TreeNode root, String path, List<String> result)` ，定义的是`String path`，**每次都是复制赋值，不用使用引用**，否则就无法做到回溯的效果。（这里涉及到C++语法知识）<br />那么在如上代码中，貌似没有看到回溯的逻辑，其实不然，回溯就隐藏在`traversal(root.left, path + "->", result);`中的` path + "->"`。 **每次函数调用完，path依然是没有加上"->" 的，这就是回溯了。**<br />为了把这份精简代码的回溯过程展现出来，大家可以试一试把：
```java
if (root.left) traversal(root.left, path + "->", result); // 左  回溯就隐藏在这里
```
改成如下
```java
path += "->";
traversal(root.left, path, result); // 左
```
即：
```java
if (root.left != null) {
    path += "->";
    traversal(root.left, path, result); // 左
}
if (root.right != null) {
    path += "->";
    traversal(root.right, path, result); // 右
}
```
此时就没有回溯了，这个代码就是通过不了的了。<br />如果想把回溯加上，就要 在上面代码的基础上，加上回溯，就可以AC了。
```cpp
if (cur->left) {
    path += "->";
    traversal(cur->left, path, result); // 左
    path.pop_back(); // 回溯 '>'  cpp的string类型有pop_back方法
    path.pop_back(); // 回溯 '-'
}
if (cur->right) {
    path += "->";
    traversal(cur->right, path, result); // 右
    path.pop_back(); // 回溯 '>' 
    path.pop_back(); //  回溯 '-' 
}
```
大家应该可以感受出来，如果把 path + "->"作为函数参数就是可以的，因为并没有改变path的数值，执行完递归函数之后，path依然是之前的数值（相当于回溯了）<br />综合以上，第二种递归的代码虽然精简但把很多重要的点隐藏在了代码细节里，第一种递归写法虽然代码多一些，但是把每一个逻辑处理都完整的展现出来了。
<a name="yM3OL"></a>
## 迭代法
至于**非递归**的方式，我们可以依然可以使用前序遍历的迭代方式来模拟遍历路径的过程<br />这里除了模拟递归需要一个栈，同时还需要一个栈来存放对应的遍历路径。<br />使用java，可以直接定义一个成员变量为`object`的栈`Stack<Object> stack = new Stack<>();`，这样就不用定义两个栈了，都放到一个栈里就可以了。
```java
// 解法2
class Solution {
    /**
     * 迭代法
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null)
            return result;
        Stack<Object> stack = new Stack<>();
        // 节点和路径同时入栈
        stack.push(root);
        stack.push(root.val + "");
        while (!stack.isEmpty()) {
            // 节点和路径同时出栈
            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();
            // 若找到叶子节点
            if (node.left == null && node.right == null) {
                result.add(path);
            }
            //右子节点不为空
            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }
            //左子节点不为空
            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
        }
        return result;
    }
}
```
-
<a name="mAMDC"></a>
# 404.左叶子之和 （优先掌握递归）
:::info
其实本题有点文字游戏，搞清楚什么是左叶子，剩下的就是二叉树的基本操作。 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0404.%E5%B7%A6%E5%8F%B6%E5%AD%90%E4%B9%8B%E5%92%8C.html](https://programmercarl.com/0404.%E5%B7%A6%E5%8F%B6%E5%AD%90%E4%B9%8B%E5%92%8C.html)
:::
首先要注意是判断左叶子，不是二叉树左侧节点，所以不要上来想着层序遍历。<br />因为题目中其实没有说清楚左叶子究竟是什么节点，那么我来给出**左叶子的明确定义：节点A的左孩子不为空，且左孩子的左右孩子都为空（说明是叶子节点），那么A节点的左孩子为左叶子节点**<br />![20210204151949672.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685436773317-bd72eefe-7270-4250-960a-04e822e45a5e.png#averageHue=%23f9f9f9&clientId=u816c4de0-b75a-4&from=paste&height=410&id=u08665850&originHeight=480&originWidth=536&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=28392&status=done&style=shadow&taskId=u34cf018d-bf62-41c3-aa96-93265802d49&title=&width=458.11967492338766)<br />上图的左叶子节点之和为0, 因为这棵树没有左叶子<br />![20220902165805.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685436809382-6c1bc5ff-d57b-4b8b-af02-1decf3913a3e.png#averageHue=%23f6f5f4&clientId=u816c4de0-b75a-4&from=paste&height=639&id=u7e1d37e4&originHeight=748&originWidth=916&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=94846&status=done&style=shadow&taskId=u24194afe-dea3-42e3-91a0-f4afafce596&title=&width=782.9060116228043)<br />**判断当前节点是不是左叶子是无法判断的，必须要通过节点的父节点来判断其左孩子是不是左叶子**。<br />如果该节点的左节点不为空，该节点的左节点的左节点为空，该节点的左节点的右节点为空，则找到了一个左叶子，判断代码如下：
```java
if (node.left != NULL && node.left->left == NULL && node.left.right == NULL) {
    左叶子节点处理逻辑
}
```
<a name="jsme0"></a>
## 递归法
递归的遍历顺序为**后序遍历(左右中)**，是因为要通过递归函数的返回值来累加求取左叶子数值之和。

**递归三部曲：**

1. 确定递归函数的参数和返回值

判断一个树的左叶子节点之和，那么一定要传入树的根节点，递归函数的返回值为数值之和，所以为`int`使用题目中给出的函数就可以了。

2. 确定终止条件. 如果遍历到空节点，那么左叶子值一定是0
```java
if(root == null) return 0;
```
**注意: **只有当前遍历的节点是父节点，才能判断其子节点是不是左叶子。 所以如果当前遍历的节点是叶子节点，那其左叶子也必定是0，那么终止条件为：
```java
if (root == null) return 0;
if (root.left == null && root.right== null) return 0; // 其实这个也可以不写，如果不写不影响结果，但就会让递归多进行了一层。
```

3. 确定单层递归的逻辑. 

当遇到左叶子节点的时候，记录数值，然后通过递归求取左子树左叶子之和，和 右子树左叶子之和，相加便是整个树的左叶子之和。
```java
int leftValue = sumOfLeftLeaves(root.left);    // 左
if (root.left != null && root.left.left == null && root.left.right == null) {
    leftValue = root.left.val;
}
int rightValue = sumOfLeftLeaves(root.right);  // 右

int sum = leftValue + rightValue;               // 中
return sum;
```
整体递归代码如下：
```java
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        
        if (root == null) return 0;
        int leftValue = sumOfLeftLeaves(root.left);    // 左
        int rightValue = sumOfLeftLeaves(root.right);  // 右
                                                       
        int midValue = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) { 
            midValue = root.left.val;
        }
        int sum = midValue + leftValue + rightValue;  // 中
        return sum;
    }
}
```
<a name="m1Cwc"></a>
## 迭代法
