时间: 2023.5.31
<a name="sPFyY"></a>
# 今日任务
第六章 二叉树part08, 235. 二叉搜索树的最近公共祖先, 701.二叉搜索树中的插入操作 , 450.删除二叉搜索树中的节点 
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 235. 二叉搜索树的最近公共祖先  的迭代法
- [ ] 701.二叉搜索树中的插入操作  的迭代法
- [ ] 450.删除二叉搜索树中的节点  继续理解递归
<a name="XBsr0"></a>
# 复习

- [ ] 236. 二叉树的最近公共祖先  重点复习一下
- [ ] 501.二叉搜索树中的众数  的二叉搜索树递归法再理解理解
<a name="TWpqa"></a>
# 235. 二叉搜索树的最近公共祖先 
:::info
相对于 二叉树的最近公共祖先 本题就简单一些了，因为 可以利用二叉搜索树的特性。 <br />题目链接/文章讲解：[https://programmercarl.com/0235.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E7%9A%84%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88.html](https://programmercarl.com/0235.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E7%9A%84%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88.html)<br />视频讲解：[https://www.bilibili.com/video/BV1Zt4y1F7ww](https://www.bilibili.com/video/BV1Zt4y1F7ww)
:::
![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685611614115-0be4d8c6-ff42-423d-ac79-048424f04b03.png#averageHue=%232f2e2d&clientId=ua6fe51d7-d50c-4&from=paste&height=101&id=ua8e52ae0&originHeight=118&originWidth=448&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=7049&status=done&style=none&taskId=ucf7dd3d9-1896-41d3-a8f2-6b4fb2c9540&title=&width=382.90599695089116)<br />做了`236. 二叉树的最近公共祖先`这一题后我们知道了要利用回溯从底向上搜索, 遇到一个节点的左子树里有p, 右子树里有q, 那么当前节点就是最近公共祖先<br />本题是二叉搜索树，二叉搜索树是有序的，得好好利用一下这个特点。<br />在有序树里，如何判断一个节点的左子树里有p，右子树里有q呢？**因为是有序树，所有 如果 中间节点是 q 和 p 的公共祖先，那么 中节点的数组 一定是在 [p, q] 区间的。即 **`**中节点 > p && 中节点 < q**`** 或者 **`**中节点 > q && 中节点 < p**`。<br />那么只要从上到下去遍历，遇到 cur节点是数值在[p, q]区间中则一定可以说明该节点cur就是q 和 p的公共祖先。 那问题来了，一定是最近公共祖先吗？<br />如图，我们从根节点搜索，第一次遇到 cur节点是数值在[p, q]区间中，即 节点5，此时可以说明 p 和 q 一定分别存在于 节点 5的左子树，和右子树中。<br />![20220926164214.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685611891444-29cdaf98-3b3f-476d-809d-3d1f493e79e0.png#averageHue=%23f8f7f7&clientId=ua6fe51d7-d50c-4&from=paste&height=701&id=u2585e435&originHeight=820&originWidth=1008&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=93554&status=done&style=none&taskId=u975eeab4-1ac2-4335-8877-60f12b6141d&title=&width=861.5384931395051)<br />此时节点5是不是最近公共祖先？ 如果 从节点5继续向左遍历，那么将错过成为q的祖先， 如果从节点5继续向右遍历则错过成为p的祖先。<br />所以当我们从上向下去递归遍历，**第一次遇到 cur节点是数值在[p, q]区间中，那么cur就是 p和q的最近公共祖先**。<br />理解这一点，本题就很好解了。而递归遍历顺序，本题就不涉及到 前中后序了（这里没有中节点的处理逻辑，遍历顺序无所谓了）。<br />如图所示：p为节点6，q为节点9<br />![20220926165141.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685612028594-927f4137-8d9c-4e6d-8cf5-2ae4a2f111da.png#averageHue=%23f7f5f5&clientId=ua6fe51d7-d50c-4&from=paste&height=697&id=ue8480074&originHeight=816&originWidth=880&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=101396&status=done&style=none&taskId=u29373334-fd83-4afd-a325-fb0f78d276f&title=&width=752.1367797249648)<br />可以看出直接按照指定的方向，就可以找到节点8，为最近公共祖先，而且**不需要遍历整棵树，找到结果直接返回**！
<a name="N9I3k"></a>
## 递归法
开始递归三部曲<br />递归三部曲如下：

1. **确定递归函数返回值以及参数**

**参数**就是当前节点，以及两个结点 p、q。**返回值**是要返回最近公共祖先，所以是TreeNode 。代码如下：
```java
TreeNode traversal(TreeNode cur, TreeNode p, TreeNode q)
```

2. **确定终止条件**, 遇到空返回就可以了，代码如下：
```java
if (cur == NULL) return cur;
```
**其实都不需要这个终止条件**，因为题目中说了p、q 为不同节点且均存在于给定的二叉搜索树中。也就是说一定会找到公共祖先的，**所以并不存在遇到空的情况**。

3. 确定单层递归的逻辑

在遍历二叉搜索树的时候就是寻找区间`[p.val, q.val]`（**注意这里是左闭右闭**）<br />那么如果 `cur.val 大于 p.val，同时 cur.val 大于q.val`，那么就应该向左遍历（说明目标区间在左子树上）。需要注意的是此时不知道p和q谁大，所以两个都要判断, 代码如下：
```java
if (cur.val>p.val && cur.val>q.val) {
    TreeNode left = traversal(cur.left, p, q);
    if (left != NULL) {
        return left;
    }
}
```
细心的同学会发现，在这里调用递归函数的地方，把递归函数的返回值left，直接return。如果递归函数有返回值，如何区分要搜索一条边，还是搜索整个树。

- 搜索一条边的写法：
```java
if (递归函数(root.left)) return ;
if (递归函数(root.right)) return ;
```

- 搜索整个树写法：
```java
left = 递归函数(root.left);
right = 递归函数(root.right);
left与right的逻辑处理;
```
本题就是标准的搜索一条边的写法，遇到递归函数的返回值，如果不为空，立刻返回。<br />如果 `cur->val 小于 p->val`，同时 `cur->val 小于 q->val`，那么就应该向右遍历（目标区间在右子树）。
```java
if (cur.val<p.val && cur.val<q.val) {
    TreeNode right = traversal(cur.right, p, q);
    if (right != NULL) {
        return right;
    }
}
```
剩下的情况，就是cur节点在区间（`p.val<=cur.val && cur.val<=q.val`）或者 （`q.val<=cur.val && cur.val<=p.val`）中，那么cur就是最近公共祖先了，直接返回cur。代码如下：
```java
return cur;
```
整体的递归代码如下
```java
class Solution {
    // 递归法
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return traversal(root, p, q);
    }
    private TreeNode traversal(TreeNode cur, TreeNode p, TreeNode q){
        if (cur == null) return null;

        if (cur.val>p.val && cur.val>q.val){  // 左
            TreeNode left = traversal(cur.left, p, q);
            if (left!=null) {
                return left;
            }
        }

        if (cur.val< p.val && cur.val<q.val){  //右
            TreeNode right = traversal(cur.right, p, q);
            if (right!=null){
                return right;
            }
        }
        return cur;
    }
}
```
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }
}
```
<a name="kKxO7"></a>
## 迭代法
利用其有序性，迭代的方式还是比较简单的，解题思路在递归中已经分析了。迭代代码如下：
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                break;
            }
        }
        return root;
    }
}
```
<a name="CA1Cf"></a>
# 701.二叉搜索树中的插入操作  
:::info
本题比想象中的简单，大家可以先自己想一想应该怎么做，然后看视频讲解，就发现 本题为什么比较简单了。<br />题目链接/文章讲解：[https://programmercarl.com/0701.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E6%8F%92%E5%85%A5%E6%93%8D%E4%BD%9C.html](https://programmercarl.com/0701.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E6%8F%92%E5%85%A5%E6%93%8D%E4%BD%9C.html)<br />视频讲解：[https://www.bilibili.com/video/BV1Et4y1c78Y](https://www.bilibili.com/video/BV1Et4y1c78Y)
:::
![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685620164137-e914370a-29d7-47f5-854d-baabfaa7b60d.png#averageHue=%233c3936&clientId=ua6fe51d7-d50c-4&from=paste&height=102&id=u7c931126&originHeight=119&originWidth=816&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=26180&status=done&style=none&taskId=uaf6b354e-ba20-4ccf-8523-668803960ab&title=&width=697.4359230176947)<br />这道题目其实是一道简单题目，但是题目中的提示：有多种有效的插入方式，还可以重构二叉搜索树，一下子吓退了不少人，瞬间感觉题目复杂了很多。其实可以不考虑题目中提示所说的改变树的结构的插入方式。<br />如下演示视频中可以看出：只要按照二叉搜索树的规则去遍历，遇到空节点就插入节点就可以了。<br />![701.二叉搜索树中的插入操作.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1685621250736-a0c3ca6a-b5d6-4574-83c2-f5db33a3ef6e.gif#averageHue=%23959595&clientId=ua6fe51d7-d50c-4&from=paste&height=274&id=u9c56d33d&originHeight=320&originWidth=528&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=350568&status=done&style=none&taskId=udf888154-37f1-4d26-af84-e841eb742a0&title=&width=451.2820678349789)<br />例如插入元素10 ，需要找到末尾节点插入便可，一样的道理来插入元素15，插入元素0，插入元素6，需要调整二叉树的结构么？ 并不需要。。<br />只要遍历二叉搜索树，找到空节点 插入元素就可以了，那么这道题其实就简单了。接下来就是遍历二叉搜索树的过程了。
<a name="nXRAz"></a>
## 递归
递归三部曲

1. **确定递归函数参数以及返回值**

参数就是根节点指针，以及要插入元素，这里递归函数要不要有返回值呢？可以有，也可以没有，但递归函数如果没有返回值的话，实现是比较麻烦的，下面也会给出其具体实现代码。有返回值的话，可以利用返回值完成新加入的节点与其父节点的赋值操作。（下面会进一步解释）递归函数的返回类型为节点类型TreeNode。代码如下：
```java
TreeNode insertIntoBST(TreeNode root, int val)
```

2. **确定终止条件**

终止条件就是找到遍历的节点为null的时候，就是要插入节点的位置了，并把插入的节点返回。代码如下：
```java
if (root == NULL) {
    TreeNode node = new TreeNode(val);
    return node;
}
```
这里把添加的节点返回给上一层，就完成了父子节点的赋值操作了，详细再往下看。

3. **确定单层递归的逻辑**. 此时要明确，需要遍历整棵树么？别忘了这是搜索树，遍历整棵搜索树简直是对搜索树的侮辱，哈哈。搜索树是有方向了，可以根据插入元素的数值，决定递归方向。代码如下：
```java
if (root.val > val) root.left = insertIntoBST(root.left, val);
if (root.val < val) root.right = insertIntoBST(root.right, val);
return root;
```
到这里，大家应该能感受到，如何通过递归函数返回值完成了新加入节点的父子关系赋值操作了，下一层将加入节点返回，本层用`root.left`或者`root.right`将其接住。<br />完整代码如下
```java
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root==null){    // 如果当前节点为空，也就意味着val找到了合适的位置，此时创建节点直接返回。
            TreeNode node = new TreeNode(val);
            return node;
        }

        if (val < root.val){
            root.left = insertIntoBST(root.left, val);  // 递归创建左子树
        }
        if (val > root.val){
            root.right = insertIntoBST(root.right, val);// 递归创建右子树
        }
        return root;
    }
}
```

<a name="crWrW"></a>
## 迭代法
在迭代法遍历的过程中，需要记录一下当前遍历的节点的父节点，这样才能做插入节点的操作。用了记录pre和cur两个指针的技巧。
```java
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode newRoot = root;
        TreeNode pre = root;
        while (root != null) {
            pre = root;
            if (root.val > val) {
                root = root.left;
            } else if (root.val < val) {
                root = root.right;
            } 
        }
        if (pre.val > val) {
            pre.left = new TreeNode(val);
        } else {
            pre.right = new TreeNode(val);
        }

        return newRoot;
    }
}
```
-
<a name="gxAdY"></a>
# 450.删除二叉搜索树中的节点  
:::info
相对于 插入操作，本题就有难度了，涉及到改树的结构 <br />题目链接/文章讲解：[https://programmercarl.com/0450.%E5%88%A0%E9%99%A4%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E8%8A%82%E7%82%B9.html](https://programmercarl.com/0450.%E5%88%A0%E9%99%A4%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E8%8A%82%E7%82%B9.html)<br />视频讲解：[https://www.bilibili.com/video/BV1tP41177us](https://www.bilibili.com/video/BV1tP41177us)
:::
[代码岁想录教程](https://programmercarl.com/0450.%E5%88%A0%E9%99%A4%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E8%8A%82%E7%82%B9.html#%E9%80%92%E5%BD%92)<br />相对于 插入操作，本题就有难度了，涉及到改树的结构 <br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685622198538-d62f9fc4-70a5-42f7-8e57-0cadc2828344.png#averageHue=%23302f2f&clientId=uc097cca4-5394-4&from=paste&height=147&id=u9432f953&originHeight=172&originWidth=439&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=7812&status=done&style=none&taskId=uc5adf488-dd92-458c-b02a-7f18c0cbf4b&title=&width=375.2136889764313)<br />搜索树的节点删除要比节点增加复杂的多，有很多情况需要考虑，做好心理准备。
<a name="NPlfV"></a>
## 递归
递归三部曲：

1. **确定递归函数参数以及返回值. **说到递归函数的返回值，这里可以通过递归返回值删除节点。代码如下：
```java
TreeNode deleteNode(TreeNode root, int key)
```

2. **确定终止条件**. 遇到空返回，其实这也说明没找到删除的节点，遍历到空节点直接返回了
```java
if (root == null) return root;
```

3. 确定单层递归的逻辑

这里把二叉搜索树中删除节点遇到的情况都搞清楚。有以下五种情况：

- 第一种情况：没找到删除的节点，遍历到空节点直接返回了
- 找到删除的节点
   - 第二种情况：左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点
   - 第三种情况：删除节点的左孩子为空，右孩子不为空，删除节点，右孩子补位，返回右孩子为根节点
   - 第四种情况：删除节点的右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
   - 第五种情况：左右孩子节点都不为空，则将删除节点的左子树头结点（左孩子）放到删除节点的右子树的最左面节点的左孩子上，返回删除节点右孩子为新的根节点。

第五种情况有点难以理解，看下面动画：<br />![450.删除二叉搜索树中的节点.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1685624692187-f2d7f6f4-b6b8-40a5-9b68-2809d5eb0864.gif#averageHue=%23fdfdfd&clientId=uc097cca4-5394-4&from=paste&height=323&id=u0207f85b&originHeight=378&originWidth=494&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=503260&status=done&style=none&taskId=ufe278f3d-ad0c-4b63-8e77-cb6ee93f31a&title=&width=422.2222377092416)<br />动画中的二叉搜索树中，删除元素7， 那么删除节点（元素7）的左孩子就是5，删除节点（元素7）的右子树的最左面节点是元素8。将删除节点（元素7）的左孩子放到删除节点（元素7）的右子树的最左面节点（元素8）的左孩子上，就是把5为根节点的子树移到了8的左孩子的位置。要删除的节点（元素7）的右孩子（元素9）为新的根节点。.这样就完成删除元素7的逻辑，最好动手画一个图，尝试删除一个节点试试。代码如下：
```java
if (root.val == key) {
    // 第二种情况：左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点
    // 第三种情况：其左孩子为空，右孩子不为空，删除节点，右孩子补位 ，返回右孩子为根节点
    if (root.left == null) return root.right;
    // 第四种情况：其右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
    else if (root.right == null) return root.left;
    // 第五种情况：左右孩子节点都不为空，则将删除节点的左子树放到删除节点的右子树的最左面节点的左孩子的位置
    // 并返回删除节点右孩子为新的根节点。
    else {
        TreeNode cur = root.right; // 找右子树最左面的节点
        while(cur.left != null) {
            cur = cur.left;
        }
        cur.left = root.left; // 把要删除的节点（root）左子树放在cur的左孩子的位置
        TreeNode tmp = root;   // 把root节点保存一下，下面来删除
        root = root.right;     // 返回旧root的右孩子作为新root
        delete tmp;             // 释放节点内存（这里不写也可以，但C++最好手动释放一下吧）
        return root;
    }
}
```
这里相当于把新的节点返回给上一层，上一层就要用 `root.left` 或者 `root.right`接住，代码如下：
```java
if (root.val > key) root.left = deleteNode(root.left, key);
if (root.val < key) root.right = deleteNode(root.right, key);
return root;
```
整体代码如下：（注释中：情况1，2，3，4，5和上面分析严格对应）
```cpp
class Solution {
public:
    TreeNode* deleteNode(TreeNode* root, int key) {
        if (root == nullptr) return root; // 第一种情况：没找到删除的节点，遍历到空节点直接返回了
        if (root->val == key) {
            // 第二种情况：左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点
            if (root->left == nullptr && root->right == nullptr) {
                ///! 内存释放
                delete root;
                return nullptr;
            }
            // 第三种情况：其左孩子为空，右孩子不为空，删除节点，右孩子补位 ，返回右孩子为根节点
            else if (root->left == nullptr) {
                auto retNode = root->right;
                ///! 内存释放
                delete root;
                return retNode;
            }
            // 第四种情况：其右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
            else if (root->right == nullptr) {
                auto retNode = root->left;
                ///! 内存释放
                delete root;
                return retNode;
            }
            // 第五种情况：左右孩子节点都不为空，则将删除节点的左子树放到删除节点的右子树的最左面节点的左孩子的位置
            // 并返回删除节点右孩子为新的根节点。
            else {
                TreeNode* cur = root->right; // 找右子树最左面的节点
                while(cur->left != nullptr) {
                    cur = cur->left;
                }
                cur->left = root->left; // 把要删除的节点（root）左子树放在cur的左孩子的位置
                TreeNode* tmp = root;   // 把root节点保存一下，下面来删除
                root = root->right;     // 返回旧root的右孩子作为新root
                delete tmp;             // 释放节点内存（这里不写也可以，但C++最好手动释放一下吧）
                return root;
            }
        }
        if (root->val > key) root->left = deleteNode(root->left, key);
        if (root->val < key) root->right = deleteNode(root->right, key);
        return root;
    }
};
```
```java

class Solution {
  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) return root;  // 第一种情况：没找到删除的节点，遍历到空节点直接返回了
    if (root.val == key) {
        // 第二中藏在第三种和第四种之间
      if (root.left == null) {  // 第三种情况：其左孩子为空，右孩子不为空，删除节点，右孩子补位 ，返回右孩子为根节点
        return root.right;
      } else if (root.right == null) {  // 第四种情况：其右孩子为空，左孩子不为空，删除节点，左孩子补位，返回左孩子为根节点
        return root.left;
      } else {
        // 第五种情况：左右孩子节点都不为空，则将删除节点的左子树放到删除节点的右子树的最左面节点的左孩子的位置
    	// 并返回删除节点右孩子为新的根节点。
        TreeNode cur = root.right;
        // 找右子树最左面的节点
        while (cur.left != null) {
          cur = cur.left;
        }
        cur.left = root.left;  // 把要删除的节点（root）左子树放在cur的左孩子的位置
        root = root.right;  // 返回旧root的右孩子作为新root
        return root; // 返回
      }
    }
    if (root.val > key) root.left = deleteNode(root.left, key);
    if (root.val < key) root.right = deleteNode(root.right, key);
    return root;
  }
}
```
```java
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        root = delete(root,key);
        return root;
    }

    private TreeNode delete(TreeNode root, int key) {
        if (root == null) return null;  // 第一种情况：没找到删除的节点，遍历到空节点直接返回了

        if (root.val > key) {
            root.left = delete(root.left,key);
        } else if (root.val < key) {
            root.right = delete(root.right,key);
            
        } else {   // root.val==key
            // 第二种情况：左右孩子都为空（叶子节点），直接删除节点， 返回NULL为根节点  第二种情况再三四里面
            if (root.left == null) return root.right;  //  第三种情况：当前节点的左子树为空，返回当前的右子树
            if (root.right == null) return root.left;  // 第四种情况：当前节点的右子树为空，返回当前的左子树

            // 第五种情况：左右孩子节点都不为空，则将删除节点的左子树放到删除节点的右子树的最左面节点的左孩子的位置
            // 左右子树都不为空，找到右孩子的最左节点 记为tmp
            TreeNode tmp = root.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            root.val = tmp.val;
            root.right = delete(root.right,tmp.val);
        }
        return root;
    }
}
```

