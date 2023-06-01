时间: 2023.5.29
<a name="sPFyY"></a>
# 今日任务
第六章 二叉树 part06, 654.最大二叉树, 617.合并二叉树, 700.二叉搜索树中的搜索 , 98.验证二叉搜索树 
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 617.合并二叉树   的迭代法
- [ ] 98.验证二叉搜索树  学习本题的迭代法
<a name="XBsr0"></a>
# 复习

- [ ] <br />

<a name="jtH0H"></a>
# 654.最大二叉树 
:::info
又是构造二叉树，昨天大家刚刚做完 中序后序确定二叉树，今天做这个 应该会容易一些， 先看视频，好好体会一下 为什么构造二叉树都是 前序遍历 <br />题目链接/文章讲解：[https://programmercarl.com/0654.%E6%9C%80%E5%A4%A7%E4%BA%8C%E5%8F%89%E6%A0%91.html](https://programmercarl.com/0654.%E6%9C%80%E5%A4%A7%E4%BA%8C%E5%8F%89%E6%A0%91.html)<br />视频讲解：[https://www.bilibili.com/video/BV1MG411G7ox](https://www.bilibili.com/video/BV1MG411G7ox)
:::
[<br />](https://leetcode.cn/problems/maximum-binary-tree/)
:::info
![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685533361815-7f0b299c-9550-4fd3-b931-6cf282c285e9.png#averageHue=%2330302f&clientId=u0c8b16c5-373b-4&from=paste&height=122&id=IBiER&originHeight=143&originWidth=422&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=5815&status=done&style=shadow&taskId=ua07532e6-3c16-42f6-a383-a6a584931ef&title=&width=360.6837739135627)<br />注意:

1. 不用判断是否为空的情况, 而且数组的元素值都大于等于0
2. 选择区间时左闭右开
:::
最大二叉树的构建过程如下：<br />![654.最大二叉树.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1685534130733-0e0dd78a-6ef7-4d1b-add7-4e3d69eb2de4.gif#averageHue=%23fdfdfd&clientId=u0c8b16c5-373b-4&from=paste&height=279&id=u69bc6ecf&originHeight=326&originWidth=500&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=741413&status=done&style=shadow&taskId=u9f74cc0c-7bd8-42cf-a4c7-65c9308ecfc&title=&width=427.3504430255482)<br />**构造树一般采用的是前序遍历，因为先构造中间节点，然后递归构造左子树和右子树**。<br />递归三部曲: 

1. **确定递归函数的参数和返回值**

参数传入的是存放元素的数组，返回该数组构造的二叉树的头结点，返回类型是指向节点的指针。代码如下：
```java
TreeNode constructMaximumBinaryTree(int[] nums);
```

2. **确定终止条件**. 题目中说了输入的数组大小一定是大于等于1的，所以我们不用考虑小于1的情况，

那么当递归遍历的时候，如果传入的数组大小为1，说明遍历到了叶子节点了。那么应该定义一个新的节点，并把这个数组的数值赋给新的节点，然后返回这个节点。 这表示一个数组大小是1的时候，构造了一个新的节点，并返回。代码如下：
```java
if (nums.length == 1) {
    return new TreeNode(nums[0]);
}
```

3. **定单层递归的逻辑**. 这里有三步工作
   1. 先要找到数组中 最大的值 和 对应的下标， 最大的值构造根节点，下标用来下一步分割数组。代码如下：
```java
int maxValue = 0;
int maxValueIndex = 0;
for (int i = 0; i < nums.length; i++) {
    if (nums[i] > maxValue) {
        maxValue = nums[i];
        maxValueIndex = i;
    }
}
TreeNode node = new TreeNode(0);
node.val = maxValue;
```

   2. 最大值所在的下标左区间 构造左子树,   这里要判断`maxValueIndex > 0`，因为要保证左区间至少有一个数值。代码如下：
```java
if (maxValueIndex > 0) {
    vector<int> newVec(nums.begin(), nums.begin() + maxValueIndex);
    node->left = constructMaximumBinaryTree(newVec);
}
```

   3. 最大值所在的下标右区间 构造右子树, 判断`maxValueIndex < (nums.length - 1)`，确保右区间至少有一个数值。代码如下：
```java
if (maxValueIndex < (nums.size() - 1)) {
    vector<int> newVec(nums.begin() + maxValueIndex + 1, nums.end());
    node->right = constructMaximumBinaryTree(newVec);
}
```
完整代码如下
```java
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTreeDemo(nums, 0, nums.length);
    }
    private TreeNode constructMaximumBinaryTreeDemo(int[] nums, int leftIndex, int rightIndex){
        if (rightIndex - leftIndex < 1){  // 没有元素了
            return null;
        }

        if (rightIndex - leftIndex == 1){  // 只有一个元素
            return new TreeNode(nums[leftIndex]);  // 直接返回该元素的树
        }

        int maxIndex = leftIndex; // 最大值所在的位置(索引)
        int maxValue = nums[maxIndex];  // 数组中的最大值

        for (int i=leftIndex+1; i<rightIndex; i++){  // 注意这里的右边界是 i<rightIndex
            if (nums[i] > maxValue){
                maxIndex = i;
                maxValue = nums[i];
            }
        }

        TreeNode root = new TreeNode(maxValue);

        // 根据maxValue分成左右子树   左闭右开的区间
        root.left = constructMaximumBinaryTreeDemo(nums, leftIndex, maxIndex);
        root.right = constructMaximumBinaryTreeDemo(nums, maxIndex + 1, rightIndex);

        return root;
    }
}
```
:::danger
注意对这两处的理解<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685536768175-5dc1bbbc-3d33-4c29-90b2-8087974c175e.png#averageHue=%23292e37&clientId=u0c8b16c5-373b-4&from=paste&height=74&id=u12b747c8&originHeight=86&originWidth=715&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=9412&status=done&style=shadow&taskId=ua6f0a234-c970-4365-a964-0f75fb68690&title=&width=611.1111335265339)<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685536776398-45ef7db8-2f5e-460e-80af-ea6508d1211f.png#averageHue=%23282d36&clientId=u0c8b16c5-373b-4&from=paste&height=65&id=ufc5f9917&originHeight=76&originWidth=470&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=4092&status=done&style=shadow&taskId=u899e482b-866f-482e-baf9-90601e8a411&title=&width=401.7094164440153)
:::
注意类似用数组构造二叉树的题目，**每次分隔尽量不要定义新的数组，而是通过下标索引直接在原数组上操作，这样可以节约时间和空间上的开销**。<br />一些同学也会疑惑，什么时候递归函数前面加if，什么时候不加if，这个问题我在最后也给出了解释。<br />其实就是不同代码风格的实现，**一般情况来说：如果让空节点（空指针）进入递归，就不加if，如果不让空节点进入递归，就加if限制一下， 终止条件也会相应的调整**
<a name="Rdwlp"></a>
# 617.合并二叉树 
:::info
这次是一起操作两个二叉树了， 估计大家也没一起操作过两个二叉树，也不知道该如何一起操作，可以看视频先理解一下。 优先掌握递归。<br />题目链接/文章讲解：[https://programmercarl.com/0617.%E5%90%88%E5%B9%B6%E4%BA%8C%E5%8F%89%E6%A0%91.html](https://programmercarl.com/0617.%E5%90%88%E5%B9%B6%E4%BA%8C%E5%8F%89%E6%A0%91.html)<br />视频讲解：[https://www.bilibili.com/video/BV1m14y1Y7JK](https://www.bilibili.com/video/BV1m14y1Y7JK)
:::
:::danger
![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685537213977-3b345084-a532-441a-ac5f-2e8e181c2dca.png#averageHue=%23363431&clientId=u0c8b16c5-373b-4&from=paste&height=42&id=IRYHy&originHeight=49&originWidth=336&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=4696&status=done&style=shadow&taskId=u45ac3f0b-8507-4168-b9f9-d0324e956c9&title=&width=287.17949771316836)
:::
<a name="kp8Dz"></a>
## 递归
本题使用哪种遍历都是可以的, 我们下面以**前序遍历(比较直观)**为例。动画如下：<br />![617.合并二叉树.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1685539107671-00a3779d-2348-4c3d-af52-fb3ae8dbde09.gif#averageHue=%23999897&clientId=u0c8b16c5-373b-4&from=paste&height=397&id=u362d94a8&originHeight=464&originWidth=506&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=1717578&status=done&style=shadow&taskId=u4cd818d9-70e4-4477-b8f4-2596c79679c&title=&width=432.47864834185475)<br />那么我们来按照递归三部曲来解决：

1. **确定递归函数的参数和返回值**： 首先要合入两个二叉树，那么参数至少是要传入两个二叉树的根节点，返回值就是合并之后二叉树的根节点。
```java
TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
```

2. **确定终止条件**：因为是传入了两个树，那么就有两个树遍历的节点 t1 和 t2，
- 如果t1 == NULL 了，两个树合并就应该是 t2 了（如果t2也为NULL也无所谓，合并之后就是NULL）。
- 反过来如果t2 == NULL，那么两个数合并就是t1（如果t1也为NULL也无所谓，合并之后就是NULL）。

代码如下：
```java
if (t1 == NULL) return t2; // 如果t1为空，合并之后就应该是t2
if (t2 == NULL) return t1; // 如果t2为空，合并之后就应该是t1
```

3. 确定单层递归的逻辑：单层递归的逻辑就比较好写了，这里我们重复利用一下t1这个树，t1就是合并之后树的根节点（就是修改了原来树的结构）。那么单层递归中，就要把两棵树的元素加到一起。
```java
t1.val += t2.val;
```
接下来 t1 的左子树是：合并 t1左子树 t2左子树之后的左子树。<br />t1 的右子树：是 合并 t1右子树 t2右子树之后的右子树。<br />最终t1就是合并之后的根节点。
```java
t1.left = mergeTrees(t1.left, t2.left);
t1.right = mergeTrees(t1.right, t2.right);
return t1;
```
最终完整版的代码为
```java
/**
 * 递归  前序遍历
 * @param root1
 * @param root2
 * @return
 */
public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
    if (root1 == null) return root2;  // 如果roo1为空，合并之后就应该是root2
    if (root2 == null) return root1;  // 如果root2为空，合并之后就应该是root1

    // 修改root1的数值和结构
    root1.val += root2.val;  // 中

    root1.left = mergeTrees(root1.left, root2.left);  // 左
    root1.right = mergeTrees(root1.right, root2.right);  // 右

    return root1;
}

/**
 * 递归  中序遍历
 * @param root1
 * @param root2
 * @return
 */
public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
    if (root1 == null) return root2;  // 如果roo1为空，合并之后就应该是root2
    if (root2 == null) return root1;  // 如果root2为空，合并之后就应该是root1

    // 修改root1的数值和结构
    root1.left = mergeTrees(root1.left, root2.left);  // 左
    root1.val += root2.val;  // 中
    root1.right = mergeTrees(root1.right, root2.right);  // 右

    return root1;
}


/**
 * 递归  后序遍历
 * @param root1
 * @param root2
 * @return
 */
public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
    if (root1 == null) return root2;  // 如果roo1为空，合并之后就应该是root2
    if (root2 == null) return root1;  // 如果root2为空，合并之后就应该是root1

    // 修改root1的数值和结构
    root1.left = mergeTrees(root1.left, root2.left);  // 左
    root1.right = mergeTrees(root1.right, root2.right);  // 右
    root1.val += root2.val;  // 中

    return root1;
}
```
前序遍历是最好理解的，建议用前序遍历来做<br />如上的方法修改了root1的结构，当然也可以不修改root1和root2的结构，重新定义一个树。不修改输入树的结构，前序遍历，代码如下：
```java
/**
 * 重新定义一个树的方法
 * @param root1
 * @param root2
 * @return
 */
public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
    if (root1 == null) return root2;  // 如果roo1为空，合并之后就应该是root2
    if (root2 == null) return root1;  // 如果root2为空，合并之后就应该是root1

    // 重新定义新的节点，不修改原有两个树的结构
    TreeNode tree = new TreeNode(0);

    tree.val = root1.val + root2.val;  // 中
    tree.left = mergeTrees(root1.left, root2.left);  // 左
    tree.right = mergeTrees(root1.right, root2.right);  // 右

    return tree;
}
```
<a name="cRiWO"></a>
## 迭代法
使用迭代法，如何同时处理两棵树呢？<br />思路我们在二叉树：`101. 对称二叉树(LeetCode)` 中的迭代法已经讲过一次了，求二叉树对称的时候就是把两个树的节点同时加入队列进行比较。<br />本题我们也使用队列，模拟的层序遍历，代码如下：
```java
class Solution {
    // 使用队列迭代
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 ==null) return root1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root1);
        queue.offer(root2);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            // 此时两个节点一定不为空，val相加
            node1.val = node1.val + node2.val;
            // 如果两棵树左节点都不为空，加入队列
            if (node1.left != null && node2.left != null) {
                queue.offer(node1.left);
                queue.offer(node2.left);
            }
            // 如果两棵树右节点都不为空，加入队列
            if (node1.right != null && node2.right != null) {
                queue.offer(node1.right);
                queue.offer(node2.right);
            }
            // 若node1的左节点为空，直接赋值
            if (node1.left == null && node2.left != null) {
                node1.left = node2.left;
            }
            // 若node1的右节点为空，直接赋值
            if (node1.right == null && node2.right != null) {
                node1.right = node2.right;
            }
        }
        return root1;
    }
}
```
```java
class Solution {
    // 使用栈迭代
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root2);
        stack.push(root1);
        while (!stack.isEmpty()) {
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();
            node1.val += node2.val;
            if (node2.right != null && node1.right != null) {
                stack.push(node2.right);
                stack.push(node1.right);
            } else {
                if (node1.right == null) {
                    node1.right = node2.right;
                }
            }
            if (node2.left != null && node1.left != null) {
                stack.push(node2.left);
                stack.push(node1.left);
            } else {
                if (node1.left == null) {
                    node1.left = node2.left;
                }
            }
        }
        return root1;
    }
}
```
<a name="tR17M"></a>
## 拓展
当然也可以秀一波指针的操作，这是我写的野路子，大家就随便看看就行了，以防带跑偏了。<br />如下代码中，想要更改二叉树的值，应该传入指向指针的指针。代码如下：（前序遍历）
```cpp
class Solution {
public:
    void process(TreeNode** t1, TreeNode** t2) {
        if ((*t1) == NULL && (*t2) == NULL) return;
        if ((*t1) != NULL && (*t2) != NULL) {
            (*t1)->val += (*t2)->val;
        }
        if ((*t1) == NULL && (*t2) != NULL) {
            *t1 = *t2;
            return;
        }
        if ((*t1) != NULL && (*t2) == NULL) {
            return;
        }
        process(&((*t1)->left), &((*t2)->left));
        process(&((*t1)->right), &((*t2)->right));
    }
    TreeNode* mergeTrees(TreeNode* t1, TreeNode* t2) {
        process(&t1, &t2);
        return t1;
    }
};
```
<a name="xY5VI"></a>
## 总结
合并二叉树，也是二叉树操作的经典题目，如果没有接触过的话，其实并不简单，因为我们习惯了操作一个二叉树，一起操作两个二叉树，还会有点懵懵的。<br />这不是我们第一次操作两棵二叉树了，在二叉树：`101. 对称二叉树(LeetCode)` 中也一起操作了两棵二叉树。<br />迭代法中，一般一起操作两个树都是使用队列模拟类似层序遍历，同时处理两个树的节点，这种方式最好理解，如果用模拟递归的思路的话，要复杂一些。<br />最后拓展中，我给了一个操作指针的野路子，大家随便看看就行了，如果学习C++的话，可以再去研究研究。<br />-

<a name="vXdWi"></a>
# 700.二叉搜索树中的搜索 
:::info
递归和迭代 都可以掌握以下，因为本题比较简单， 了解一下 二叉搜索树的特性<br />题目链接/文章讲解: [https://programmercarl.com/0700.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E6%90%9C%E7%B4%A2.html](https://programmercarl.com/0700.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E6%90%9C%E7%B4%A2.html)<br />视频讲解：[https://www.bilibili.com/video/BV1wG411g7sF](https://www.bilibili.com/video/BV1wG411g7sF)
:::
二叉搜索树是一个有序树：

- 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
- 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
- 它的左、右子树也分别为二叉搜索树

这就决定了，二叉搜索树，递归遍历和迭代遍历和普通二叉树都不一样。<br />本题，其实就是在二叉搜索树中搜索一个节点。那么我们来看看应该如何遍历
<a name="Ztgb4"></a>
## 递归

1. 确定递归函数的参数和返回值.  递归函数的参数传入的就是根节点和要搜索的数值，返回的就是以这个搜索数值所在的节点。
```java
TreeNode searchBST(TreeNode root, int val)
```

2. 确定终止条件.  如果root为空，或者找到这个数值了，就返回root节点。
```java
if (root == NULL || root->val == val) return root;
```

3. 确定单层递归的逻辑.  看看二叉搜索树的单层递归逻辑有何不同。因为**二叉搜索树的节点是有序的**，所以**可以有方向的去搜索**。
   1.  如果`root.val > val`，搜索左子树，
   2. 如果`root.val < val`，就搜索右子树，最后如果都没有搜索到，就返回 NULL。代码如下：
```java
TreeNode result = NULL;
if (root.val > val) result = searchBST(root.left, val);
if (root.val < val) result = searchBST(root.right, val);
return result;
```
很多录友写递归函数的时候 习惯直接写 `searchBST(root.left, val)`，却忘了 递归函数还有返回值。<br />递归函数的返回值是什么? 是 左子树如果搜索到了val，要将该节点返回。 如果不用一个变量将其接住，那么返回值不就没了。<br />所以要 `result = searchBST(root.left, val)`。
```java
class Solution {
    // 递归，利用二叉搜索树特点，优化
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }
}
```
```java
// 递归，普通二叉树
public TreeNode searchBST(TreeNode root, int val) {
    if (root == null || root.val ==val ) {
        return root;
    }

    TreeNode left = searchBST(root.left, val);

    if (left != null) return left;

    return searchBST(root.right, val);
}
```
<a name="ueEyj"></a>
## 迭代法
一提到二叉树遍历的迭代法，可能立刻想起使用栈来模拟深度遍历，使用队列来模拟广度遍历。<br />对于二叉搜索树可就不一样了，因为二叉搜索树的特殊性，也就是节点的有序性，可以不使用辅助栈或者队列就可以写出迭代法。

- 对于一般二叉树，递归过程中还有回溯的过程，例如走一个左方向的分支走到头了，那么要调头，在走右分支。
- 而对于二叉搜索树，不需要回溯的过程，因为节点的有序性就帮我们确定了搜索的方向。

例如要搜索元素为3的节点，我们不需要搜索其他节点，也不需要做回溯，查找的路径已经规划好了。中间节点如果大于3就向左走，如果小于3就向右走，如图：<br />![20200812190213280.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685546735615-1b959c25-b520-4f9c-995d-700fbce14d0d.png#averageHue=%23fcf4f4&clientId=u0c8b16c5-373b-4&from=paste&height=321&id=uf1bf82c8&originHeight=376&originWidth=520&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=24446&status=done&style=shadow&taskId=u28efe97d-c2e8-44d7-9218-7505723a30b&title=&width=444.4444607465701)<br />迭代法完整代码如下
```java
class Solution {
    // 迭代，普通二叉树
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop.val == val) {
                return pop;
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return null;
    }
}

```
```java
class Solution {
    // 迭代，利用二叉搜索树特点，优化，可以不需要栈
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null){
            if (val < root.val) root = root.left;
            else if (val > root.val) root = root.right;
            else return root;
        }
        return null;
    }
}
```
<a name="wMtea"></a>
## 总结
本篇介绍了二叉搜索树的遍历方式，因为**二叉搜索树的有序性**，遍历的时候要比普通二叉树简单很多。<br />但是一些同学很容易忽略二叉搜索树的特性，所以写出遍历的代码就未必真的简单了。
<a name="kaUav"></a>
# 98.验证二叉搜索树 
:::info
遇到 搜索树，一定想着中序遍历，这样才能利用上特性。 <br />但本题是有陷阱的，可以自己先做一做，然后在看题解，看看自己是不是掉陷阱里了。这样理解的更深刻。<br />题目链接/文章讲解：[https://programmercarl.com/0098.%E9%AA%8C%E8%AF%81%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.html](https://programmercarl.com/0098.%E9%AA%8C%E8%AF%81%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.html)<br />视频讲解：[https://www.bilibili.com/video/BV18P411n7Q4](https://www.bilibili.com/video/BV18P411n7Q4)
:::
遇到 搜索树，一定想着中序遍历，这样才能利用上特性。 <br />中序遍历下，输出的二叉搜索树节点的数值是有序序列。有了这个特性，验证二叉搜索树，就相当于变成了判断一个序列是不是递增的了
<a name="dsmdZ"></a>
## 递归法
<a name="tFU18"></a>
### C++数组法
可以递归中序遍历将二叉搜索树转变成一个数组，代码如下：
```java
vector<int> vec;
void traversal(TreeNode* root) {
    if (root == NULL) return;
    traversal(root->left);
    vec.push_back(root->val); // 将二叉搜索树转换为有序数组
    traversal(root->right);
}
```
然后只要比较一下，这个数组是否是有序的，注意二叉搜索树中不能有重复元素。
```java
traversal(root);
for (int i = 1; i < vec.size(); i++) {
    // 注意要小于等于，搜索树里不能有相同元素
    if (vec[i] <= vec[i - 1]) return false;
}
return true;
```
整体代码如下：
```java
class Solution {
private:
    vector<int> vec;
    void traversal(TreeNode* root) {
        if (root == NULL) return;
        traversal(root->left);
        vec.push_back(root->val); // 将二叉搜索树转换为有序数组
        traversal(root->right);
    }
public:
    bool isValidBST(TreeNode* root) {
        vec.clear(); // 不加这句在leetcode上也可以过，但最好加上
        traversal(root);
        for (int i = 1; i < vec.size(); i++) {
            // 注意要小于等于，搜索树里不能有相同元素
            if (vec[i] <= vec[i - 1]) return false;
        }
        return true;
    }
};
```
以上代码中，我们把二叉树转变为数组来判断，是最直观的，但其实不用转变成数组，可以在递归遍历的过程中直接判断是否有序。

这道题目比较容易陷入两个陷阱：
<a name="vwjSr"></a>
### 陷阱1
不能单纯的比较左节点小于中间节点，右节点大于中间节点就完事了。写出了类似这样的代码：
```java
if (root.val > root.left.val && root.val < root.right.val) {
    return true;
} else {
    return false;
}
```
我们要比较的是 **左子树所有节点小于中间节点，右子树所有节点大于中间节点**。所以以上代码的判断逻辑是错误的。<br />例如： [10,5,15,null,null,6,20] 这个case：节点10大于左节点5，小于右节点15，但右子树里出现了一个6 这就不符合了！<br />![20230310000824.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685553692318-00863b8d-94a7-4e47-9636-d7e92784ce68.png#averageHue=%23fcfbfb&clientId=u0c8b16c5-373b-4&from=paste&height=316&id=udcc48264&originHeight=370&originWidth=354&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=12276&status=done&style=shadow&taskId=u541a3bae-8e8c-4bff-8237-3122723d22a&title=&width=302.5641136620881)
<a name="h7tp7"></a>
### 陷阱2
样例中最小节点 可能是int的最小值，如果这样使用最小的int来比较也是不行的。此时可以初始化比较元素为longlong的最小值。<br />问题可以进一步演进：如果样例中根节点的val 可能是longlong的最小值 又要怎么办呢？文中会解答。<br />了解这些陷阱之后我们来看一下代码应该怎么写：
<a name="HbAAt"></a>
### 开始递归三部曲

1. **确定递归函数，返回值以及参数**

要定义一个longlong的全局变量，用来比较遍历的节点是否有序，因为后台测试数据中有int最小值，所以定义为longlong的类型，初始化为longlong最小值。<br />注意递归函数要有bool类型的返回值, 只有寻找某一条边（或者一个节点）的时候，递归函数会有bool类型的返回值。所以我们在寻找一个不符合条件的节点，如果没有找到这个节点就遍历了整个树，如果找到不符合的节点了，立刻返回。
```java
long long maxVal = LONG_MIN; // 因为后台测试数据中有int最小值
bool isValidBST(TreeNode* root)
```

2. **确定终止条件**.  如果是空节点 是不是二叉搜索树呢？ 是的，二叉搜索树也可以为空！代码如下：
```java
if (root == NULL) return true;
```

3. **确定单层递归的逻辑**,   中序遍历，一直更新maxVal，一旦发现maxVal >= root->val，就返回false，注意元素相同时候也要返回false。代码如下：
```java
bool left = isValidBST(root->left);         // 左

// 中序遍历，验证遍历的元素是不是从小到大
if (maxVal < root->val) maxVal = root->val; // 中
else return false;

bool right = isValidBST(root->right);       // 右
return left && right;
```
整体代码如下：
```java
class Solution {
public:
    long long maxVal = LONG_MIN; // 因为后台测试数据中有int最小值
    bool isValidBST(TreeNode* root) {
        if (root == NULL) return true;

        bool left = isValidBST(root->left);
        // 中序遍历，验证遍历的元素是不是从小到大
        if (maxVal < root->val) maxVal = root->val;
        else return false;
        bool right = isValidBST(root->right);

        return left && right;
    }
};
```
以上代码是因为后台数据有int最小值测试用例，所以都把maxVal改成了longlong最小值。<br />如果测试数据中有 longlong的最小值，怎么办？不可能在初始化一个更小的值了吧。 建议避免 初始化最小值，如下方法取到最左面节点的数值来比较。<br />代码如下：
```java
class Solution {
public:
    TreeNode* pre = NULL; // 用来记录前一个节点
    bool isValidBST(TreeNode* root) {
        if (root == NULL) return true;
        bool left = isValidBST(root->left);

        if (pre != NULL && pre->val >= root->val) return false;
        pre = root; // 记录前一个节点

        bool right = isValidBST(root->right);
        return left && right;
    }
};
```
最后这份代码看上去整洁一些，思路也清晰
```java
/**
 * 中序遍历
 */
TreeNode max;
public boolean isValidBST(TreeNode root) {
    if (root == null) return true;

    // 左
    boolean left = isValidBST(root.left);
    if (!left){
        return false;
    }

    // 中
    if(max!=null && root.val<=max.val){
        return false;
    }
    max =root;
    // 右
    boolean right = isValidBST(root.right);
    return right;
}
```
```java
// 简洁实现·递归解法
class Solution {
    public boolean isValidBST(TreeNode root) {
        return validBST(Long.MIN_VALUE, Long.MAX_VALUE, root);
    }
    boolean validBST(long lower, long upper, TreeNode root) {
        if (root == null) return true;
        if (root.val <= lower || root.val >= upper) return false;
        return validBST(lower, root.val, root.left) && validBST(root.val, upper, root.right);
    }
}
```
```java
// 简洁实现·中序遍历
class Solution {
    private long prev = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val <= prev) { // 不满足二叉搜索树条件
            return false;
        }
        prev = root.val;
        return isValidBST(root.right);
    }
}
```
<a name="w5mRE"></a>
## 迭代法
可以用迭代法模拟二叉树中序遍历, 迭代法中序遍历稍加改动就可以了，代码如下：
```java
//使用統一迭代法
class Solution {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        if(root != null)
            stack.add(root);        
        while(!stack.isEmpty()){
            TreeNode curr = stack.peek();
            if(curr != null){
                stack.pop();
                if(curr.right != null)
                    stack.add(curr.right);
                stack.add(curr);
                stack.add(null);
                if(curr.left != null)
                    stack.add(curr.left);
            }else{
                stack.pop();
                TreeNode temp = stack.pop();
                if(pre != null && pre.val >= temp.val)
                    return false;
                pre = temp;
            }
        }
        return true;
    }
}
```
```java
class Solution {
    // 迭代
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;// 左
            }
            // 中，处理
            TreeNode pop = stack.pop();
            if (pre != null && pop.val <= pre.val) {
                return false;
            }
            pre = pop;

            root = pop.right;// 右
        }
        return true;
    }
}
```
