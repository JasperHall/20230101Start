时间: 2023.5.23
<a name="sPFyY"></a>
# 今日任务
理论基础, 递归遍历 , 迭代遍历, 统一迭代
<a name="CXkSh"></a>
# 收获
每日精华里搜索“第六章 二叉树part01”，有关于树的递归遍历的内容
<a name="KOISD"></a>
# 明天计划

- [ ] 继续理解统一迭代法

<a name="XBsr0"></a>
# 复习

- [ ] day13_239滑动窗口最大值的解法二
- [ ] day12_347.前k个高频元素的大顶堆法, 桶排序法 
- [ ] 学习Lambda对集合的排序
- [ ] 学习map.entry()方法

<a name="hHArv"></a>
# 理论基础 
:::info
需要了解 二叉树的种类，存储方式，遍历方式 以及二叉树的定义 <br />文章讲解：[https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html](https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)
:::

<a name="RKuY9"></a>
# 递归遍历 （必须掌握）
:::info
二叉树的三种递归遍历掌握其规律后，其实很简单 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E9%80%92%E5%BD%92%E9%81%8D%E5%8E%86.html](https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E9%80%92%E5%BD%92%E9%81%8D%E5%8E%86.html)
:::
这里来好好看看谈一谈递归, 很多人都是看到递归算法属于是 "一看就会, 一写就废". 主要是对递归不成体系，没有方法论，每次写递归算法 ，都是靠玄学来写代码，代码能不能编过都靠运气。<br />现在介绍 前后中序的递归写法，一些同学可能会感觉很简单，其实不然，我们要通过简单题目把方法论确定下来，有了方法论，后面才能应付复杂的递归。<br />这里帮助大家确定下来**递归算法的三个要素**。每次写递归，都按照这三要素来写，可以保证大家写出正确的递归算法！
:::danger

1. **确定递归函数的参数和返回值**： 确定哪些参数是递归的过程中需要处理的，那么就在递归函数里加上这个参数， 并且还要明确每次递归的**返回值**是什么进而确定递归函数的返回类型。
2. **确定终止条件**： 写完了递归算法, 运行的时候，经常会遇到**栈溢出的错误**，就是没写终止条件或者终止条件写的不对，操作系统也是用一个**栈**的结构来保存每一层递归的信息，如果递归没有终止，操作系统的内存栈必然就会溢出。
3. **确定单层递归的逻辑**： 确定每一层递归需要处理的信息。在这里也就会重复调用自己来实现递归的过程。
:::
以下以**前序遍历**为例
```cpp
1.确定递归函数的参数和返回值：
因为要打印出前序遍历节点的数值，所以参数里需要传入vector来放节点的数值，
除了这一点就不需要再处理什么数据了也不需要有返回值，所以递归函数返回类型就是void，代码如下：
void traversal(TreeNode* cur, vector<int>& vec)

2. 确定终止条件：
在递归的过程中，如何算是递归结束了呢，当然是当前遍历的节点是空了，那么本层递归就要结束了，
所以如果当前遍历的这个节点是空，就直接return，代码如下：
if (cur == NULL) return;

3.确定单层递归的逻辑：
前序遍历是中左右的循序，所以在单层递归的逻辑，是要先取中节点的数值，代码如下：
vec.push_back(cur->val);    // 中
traversal(cur->left, vec);  // 左
traversal(cur->right, vec); // 右
```
单层递归的逻辑就是按照中左右的顺序来处理的，这样二叉树的前序遍历，基本就写完了，再看一下完整代码：<br />结合例题: [144. 二叉树的前序遍历](https://leetcode.cn/problems/binary-tree-preorder-traversal/),  [94. 二叉树的中序遍历](https://leetcode.cn/problems/binary-tree-inorder-traversal/),  [145. 二叉树的后序遍历](https://leetcode.cn/problems/binary-tree-postorder-traversal/)
<a name="NBKwn"></a>
## [144. 二叉树的前序遍历](https://leetcode.cn/problems/binary-tree-preorder-traversal/)
```java
// 前序遍历·递归·LC144_二叉树的前序遍历
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        preorder(root, result);
        return result;
    }

    public void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        // 先中, 再左, 再右
        result.add(root.val);  //注意这里的 result.add的作用
        preorder(root.left, result);
        preorder(root.right, result);
    }
}


```
<a name="oUi5q"></a>
## [94. 二叉树的中序遍历](https://leetcode.cn/problems/binary-tree-inorder-traversal/)
```java
// 中序遍历·递归·LC94_二叉树的中序遍历
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        // 先左, 再中, 再右
        inorder(root.left, list);
        list.add(root.val);             // 注意这一句add
        inorder(root.right, list); 
    }
}
```
<a name="ZoYOY"></a>
## [145. 二叉树的后序遍历](https://leetcode.cn/problems/binary-tree-postorder-traversal/)
```java
// 后序遍历·递归·LC145_二叉树的后序遍历
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);             // 注意这一句
    }
}
```
-
<a name="euumB"></a>
# 迭代遍历 （基础不好的录友，迭代法可以放过）
:::info
题目链接/文章讲解/视频讲解：[https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E8%BF%AD%E4%BB%A3%E9%81%8D%E5%8E%86.html](https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E8%BF%AD%E4%BB%A3%E9%81%8D%E5%8E%86.html)
:::
[本题目地址_代码随想录](https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E8%BF%AD%E4%BB%A3%E9%81%8D%E5%8E%86.html#%E4%B8%AD%E5%BA%8F%E9%81%8D%E5%8E%86-%E8%BF%AD%E4%BB%A3%E6%B3%95)
<a name="YSBJ1"></a>
## 前序遍历(迭代法
前序遍历是**中左右**，每次先处理的是中间节点，那么先将**根节点**放入栈中，然后将**右孩子**加入栈，再加入**左孩子**。<br />为什么要先加入 右孩子，再加入左孩子呢？ 因为这样出栈的时候才是中左右的顺序。动画如下：<br />![二叉树前序遍历（迭代法）.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1685194699338-1db16b33-2e9d-40fc-86d6-b3d2dec54e7d.gif#averageHue=%23000000&clientId=u19d18bf1-2909-4&from=paste&height=403&id=u3aaefb00&originHeight=472&originWidth=530&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=2389974&status=done&style=shadow&taskId=u4f9706b8-3c6b-4dd7-81e1-53bb5020cd3&title=&width=452.99146960708106)前序遍历: 5, 4, 1, 2, 6
```java
// 前序遍历顺序：中-左-右，入栈顺序：中-右-左
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
        return result;
    }
}
```
此时会发现貌似使用迭代法写出前序遍历并不难，确实不难。<br />此时是不是想改一点前序遍历代码顺序就把中序遍历搞出来了？ 其实还真不行！<br />但接下来，再用迭代法写中序遍历的时候，会发现套路又不一样了，目前的前序遍历的逻辑无法直接应用到中序遍历上。
<a name="mbH1X"></a>
## 中序遍历(迭代法
为了解释清楚，我说明一下 刚刚在迭代的过程中，其实我们有两个操作：

1. **处理：将元素放进result数组中**
2. **访问：遍历节点**

分析一下为什么刚刚写的前序遍历的代码，不能和中序遍历通用呢，因为前序遍历的顺序是**中左右**，先访问的元素是中间节点，要处理的元素也是中间节点，所以刚刚才能写出相对简洁的代码，因为要访问的元素和要处理的元素顺序是一致的，都是中间节点。<br />那么再看看中序遍历，中序遍历是**左中右**，先访问的是二叉树顶部的节点，然后一层一层向下访问，直到到达树左面的最底部，再开始处理节点（也就是在把节点的数值放进result数组中），这就造成了**处理顺序和访问顺序是不一致的**。<br />那么在使用迭代法写中序遍历，就需要借用指针的遍历来帮助访问节点，栈则用来处理节点上的元素。动画如下：<br />![二叉树中序遍历（迭代法）.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1685194909013-651fab8f-b471-4109-8882-4776ad3d7a1b.gif#averageHue=%238d8c8c&clientId=u19d18bf1-2909-4&from=paste&height=403&id=u0ed3b77b&originHeight=472&originWidth=530&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=2751512&status=done&style=shadow&taskId=ub07db5df-477f-42f4-8f3f-4704be9195b&title=&width=452.99146960708106)中序遍历: 1, 4, 2, 5, 6
```java
// 中序遍历顺序: 左-中-右 入栈顺序： 左-右
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()){  // 不为null 或 不为空
           if (cur != null){
               stack.push(cur);
               cur = cur.left;
           }else{
               cur = stack.pop();
               result.add(cur.val);
               cur = cur.right;
           }
        }
        return result;
    }
}
```
<a name="A93wS"></a>
## 后序遍历(迭代法
再来看后序遍历，**前序遍历**是**中左右**，**后续遍历**是**左右中**，那么我们只需要调整一下前序遍历的代码顺序，就变成**中右左**的遍历顺序，然后在**反转**result**数组**，输出的结果顺序就是左右中了，如下图：<br />![20200808200338924.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685194927767-b440df81-f195-48c0-ad63-2ea5211ce64d.png#averageHue=%23f6f5f5&clientId=u19d18bf1-2909-4&from=paste&height=224&id=ue8fc86d0&originHeight=262&originWidth=1128&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=27143&status=done&style=shadow&taskId=u214e0e85-efa7-4588-8426-62479c29716&title=&width=964.1025994656367)
:::danger
注意这里反转的用法`Collections.reverse(result);`
:::
```java
// 后序遍历顺序 左-右-中 入栈顺序：中-左-右 出栈顺序：中-右-左， 最后翻转结果
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.left != null){
                stack.push(node.left);
            }
            if (node.right != null){
                stack.push(node.right);
            }
        }
        Collections.reverse(result);
        return result;
    }
}
```
<a name="pnlJe"></a>
## 迭代遍历小结
此时我们用迭代法写出了二叉树的前后中序遍历，大家可以看出**前序和中序是完全两种代码风格**，并不像递归写法那样代码稍做调整，就可以实现前后中序。<br />这是因为前序遍历中访问节点（遍历节点）和处理节点（将元素放进result数组中）可以同步处理，但是中序就无法做到同步！<br />上面这句话，可能一些同学不太理解，建议自己亲手用迭代法，先写出来前序，再试试能不能写出中序，就能理解了。<br />那么问题又来了，难道 二叉树前后中序遍历的迭代法实现，就不能风格统一么（即前序遍历 改变代码顺序就可以实现中序 和 后序）？<br />当然可以，这种写法，还不是很好理解.
<a name="JNfWh"></a>
# 统一迭代   （基础不好的录友，迭代法可以放过）
:::info
这是统一迭代法的写法， 如果学有余力，可以掌握一下<br />题目链接/文章讲解：[https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E7%BB%9F%E4%B8%80%E8%BF%AD%E4%BB%A3%E6%B3%95.html](https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E7%BB%9F%E4%B8%80%E8%BF%AD%E4%BB%A3%E6%B3%95.html)
:::
在上边我们使用了递归法和迭代法实现了二叉树的前中后序遍历, 但是发现使用迭代法实现的前中后序遍历风格不统一, 除了先序和后序，有关联，中序完全就是另一个风格了，一会用栈遍历，一会又用指针来遍历。<br />实践后会发现使用迭代法实现先中后序遍历，很难写出统一的代码，不像是递归法，实现了其中的一种遍历方式，其他两种只要稍稍改一下节点顺序就可以了。<br />接下来这里使用迭代法写出风格统一的代码<br />前面使用栈无法同时解决 访问节点(遍历节点) 和 处理节点(将元素放进结果集)不一致的情况。**那我们就将访问的节点放入栈中，把要处理的节点也放入栈中但是要做标记。**<br />**就是要处理的节点放入栈之后，紧接着放入一个空指针作为标记**。 这种方法也可以叫做**标记法**。
<a name="oRMes"></a>
## 迭代法的中序遍历
```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                if (node.right!=null) st.push(node.right);  // 添加右节点（空节点不入栈）
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
    
                if (node.left!=null) st.push(node.left);    // 添加左节点（空节点不入栈）
            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.peek();    // 重新取出栈中元素
                st.pop();
                result.add(node.val); // 加入到结果集
            }
        }
        return result;
    }
}
```
看代码有点抽象我们来看一下动画(中序遍历)：<br />![中序遍历迭代（统一写法）.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1685202988063-2356ffb7-f12d-4eac-bcf7-62689a7ebd77.gif#averageHue=%23fbfbfb&clientId=u5779fcfe-f90d-4&from=paste&height=303&id=u6dd86c46&originHeight=354&originWidth=526&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=2487577&status=done&style=shadow&taskId=ued407e74-614e-4e12-9cbd-8737794d4de&title=&width=449.5726660628767)结果: 1,4,2,5,6<br />![5d0c0d7015efc05574c2b30a13a096e.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685207891052-c4ce2a20-196d-47a1-b149-536fff8a6ecd.png#averageHue=%23e8e7e3&clientId=uc9f04516-e6e8-4&from=paste&height=1041&id=u7e69b5aa&originHeight=1218&originWidth=2513&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=276896&status=done&style=shadow&taskId=u5db33aaf-c980-458f-8750-0a26f4b1701&title=&width=2147.8633266464053)<br />动画中，result数组就是最终结果集。<br />可以看出我们将访问的节点直接加入到栈中，但如果是处理的节点则后面放入一个空节点， 这样只有空节点弹出的时候，才将下一个节点放进结果集。<br />此时我们再来看前序遍历代码。
<a name="y5gwo"></a>
## 迭代法的前序遍历
迭代法前序遍历代码如下： (注意此时我们和中序遍历相比仅仅改变了两行代码的顺序)
```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                if (node.right!=null) st.push(node.right);  // 添加右节点（空节点不入栈）
                if (node.left!=null) st.push(node.left);    // 添加左节点（空节点不入栈）
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
                
            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.peek();    // 重新取出栈中元素
                st.pop();
                result.add(node.val); // 加入到结果集
            }
        }
        return result;
    }
}
```
<a name="Yqk3v"></a>
## 迭代法的后序遍历
后续遍历代码如下： (注意此时我们和中序遍历相比仅仅改变了两行代码的顺序)
```java
class Solution {
   public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root != null) st.push(root);
        while (!st.empty()) {
            TreeNode node = st.peek();
            if (node != null) {
                st.pop(); // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                st.push(node);                          // 添加中节点
                st.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
                if (node.right!=null) st.push(node.right);  // 添加右节点（空节点不入栈）
                if (node.left!=null) st.push(node.left);    // 添加左节点（空节点不入栈）         
                               
            } else { // 只有遇到空节点的时候，才将下一个节点放进结果集
                st.pop();           // 将空节点弹出
                node = st.peek();    // 重新取出栈中元素
                st.pop();
                result.add(node.val); // 加入到结果集
            }
        }
        return result;
   }
}
```
<a name="Gh9Bd"></a>
## 总结
此时我们写出了统一风格的迭代法，不用在纠结于前序写出来了，中序写不出来的情况了。<br />但是统一风格的迭代法并不好理解，而且想在面试直接写出来还有难度的。<br />所以大家根据自己的个人喜好，对于二叉树的前中后序遍历，选择一种自己容易理解的递归和迭代法。
