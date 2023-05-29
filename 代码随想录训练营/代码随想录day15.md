时间: 2023.5.24
<a name="sPFyY"></a>
# 今日任务
第六章  二叉树 part02<br />102.层序遍历 , 226.翻转二叉树 , 101.对称二叉树
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划
层序遍历有好多题可以做<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685275995754-8ac4ccde-ff67-4d49-a667-7b65f1b40af2.png#averageHue=%23fefdfc&clientId=ua91cdb4a-50ac-4&from=paste&height=292&id=u603d56dc&originHeight=342&originWidth=613&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=28603&status=done&style=shadow&taskId=uf3725483-7c26-49bf-a4c3-dcf870a8762&title=&width=523.9316431493221)

- [ ] 学习下LinkedLis, 整理教程
- [ ] 复习前中后序遍历的统一写法
- [ ] 复习226.翻转二叉树的递归法
- [ ] 对称二叉树想通过类型题目: 100.相同的树, 572.另一个树的子树
<a name="XBsr0"></a>
# 复习

- [ ] 继续理解统一迭代法
- [ ] 每日精华里搜索“第六章 二叉树part01”，有关于树的递归遍历的内容

<a name="niVKX"></a>
# 102.层序遍历 
:::info
看完本篇可以一口气刷十道题，试一试， 层序遍历并不难，大家可以很快刷了十道题。<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0102.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E5%B1%82%E5%BA%8F%E9%81%8D%E5%8E%86.html](https://programmercarl.com/0102.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E5%B1%82%E5%BA%8F%E9%81%8D%E5%8E%86.html)
:::
```java
// 102.二叉树的层序遍历
class Solution {
    public List<List<Integer>> resList = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        //checkFun01(root,0);
        checkFun02(root);

        return resList;
    }

    //DFS--递归方式
    public void checkFun01(TreeNode node, Integer deep) {
        if (node == null) return;
        deep++;

        if (resList.size() < deep) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> item = new ArrayList<Integer>();
            resList.add(item);
        }
        resList.get(deep - 1).add(node.val);

        checkFun01(node.left, deep);
        checkFun01(node.right, deep);
    }

   	// BFS--迭代方式--借助队列
    public void checkFun02(TreeNode node){
        if (node == null) return;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(node);

        while (!que.isEmpty()) {  // 队列不为空就进来while循环
            List<Integer> itemList = new ArrayList<>();
            int len = que.size();  // 注意len的控制循环次数

            while (len > 0){
                TreeNode tmpNode = que.poll(); // 出队
                itemList.add(tmpNode.val);

                if (tmpNode.left != null) que.offer(tmpNode.left);  // 入队
                if (tmpNode.right != null) que.offer(tmpNode.right);

                len--;
            }
            
            resList.add(itemList);
        }
    }
}
```
<a name="xWPJF"></a>
# [107. 二叉树的层序遍历 II](https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/)
相对于102.二叉树的层序遍历，就是最后把result数组反转一下就可以了。
```java
// 107. 二叉树的层序遍历 II
/**
 * 迭代, 借助队列
 * 层序遍历, 最后翻转数组
 * @param root
 * @return
 */
public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> list = new ArrayList<>();
    Deque<TreeNode> deque = new LinkedList<>();  // 双端队列

    if (root == null) return list;

    deque.offerLast(root);
    while (!deque.isEmpty()){
        List<Integer> levelList = new ArrayList<>();

        int levelSize = deque.size();

        while (levelSize > 0){
            TreeNode peek = deque.peekFirst();
            levelList.add(deque.pollFirst().val);

            if (peek.left != null) deque.offerLast(peek.left);
            if (peek.right != null) deque.offerLast(peek.right);

            levelSize--;  // 不要忘记减减
        }
        list.add(levelList);
    }

    List<List<Integer>> result = new ArrayList<>();
    for (int i=list.size()-1; i>=0; i--){
        result.add(list.get(i));
    }
    return result;
}
```
```java
/**
 * 思路和模板相同, 对收集答案的方式做了优化, 最后不需要反转
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 利用链表可以进行 O(1) 头部插入, 这样最后答案不需要再反转
        LinkedList<List<Integer>> ans = new LinkedList<>();

        Queue<TreeNode> q = new LinkedList<>();

        if (root != null) q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            List<Integer> temp = new ArrayList<>();

            for (int i = 0; i < size; i ++) {
                TreeNode node = q.poll();

                temp.add(node.val);

                if (node.left != null) q.offer(node.left);

                if (node.right != null) q.offer(node.right);
            }

            // 新遍历到的层插到头部, 这样就满足按照层次反序的要求
            ans.addFirst(temp);
        }

        return ans;
    }
}
```
:::danger
注意: LinkedList 的 addFirst()方法的使用
:::
<a name="ryLkS"></a>
# 226.翻转二叉树 （优先掌握递归） 
:::info
这道题目 一些做过的同学 理解的也不够深入，建议大家先看我的视频讲解，无论做过没做过，都会有很大收获。<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0226.%E7%BF%BB%E8%BD%AC%E4%BA%8C%E5%8F%89%E6%A0%91.html](https://programmercarl.com/0226.%E7%BF%BB%E8%BD%AC%E4%BA%8C%E5%8F%89%E6%A0%91.html)
:::
这道题目背后有一个让程序员心酸的故事，听说 Homebrew的作者Max Howell，就是因为没在白板上写出翻转二叉树，最后被Google拒绝了。<br />这道题目是非常经典的题目，也是比较简单的题目（至少一看就会）。但正是因为这道题太简单，一看就会，一些同学都没有抓住起本质，稀里糊涂的就把这道题目过了。如果做过这道题的同学也建议认真看完，相信一定有所收获！<br />如果要从整个树来看，翻转还真的挺复杂，整个树以中间分割线进行翻转，如图：<br />![20210203192724351.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685283324686-cea0c9da-8a55-44b9-85a2-86ac5a9ed93a.png#averageHue=%23f6f6f6&clientId=u0c10103c-f9b0-4&from=paste&height=629&id=u5fd31a63&originHeight=736&originWidth=1136&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=90796&status=done&style=shadow&taskId=u98d849f6-edda-49ac-b4c7-a3ac68baf13&title=&width=970.9402065540455)<br />可以发现想要翻转它，其实就把每一个节点的左右孩子交换一下就可以了。<br />关键在于遍历顺序，前中后序应该选哪一种遍历顺序？ （一些同学这道题都过了，但是不知道自己用的是什么顺序）<br />遍历的过程中去翻转每一个节点的左右孩子就可以达到整体翻转的效果。<br />注意: 只要把每一个节点的左右孩子翻转一下，就可以达到整体翻转的效果<br />这道题目使用前序遍历和后序遍历都可以，唯独中序遍历不方便，因为中序遍历会把某些节点的左右孩子翻转了两次！建议拿纸画一画，就理解了<br />那么层序遍历可以不可以呢？依然可以的！只要把每一个节点的左右孩子翻转一下的遍历方式都是可以的！
<a name="lossd"></a>
## 递归法
下文以前序遍历为例，通过动画来看一下翻转的过程:<br />![翻转二叉树.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1685283488030-bd042dc3-f544-467d-a420-8f9a81f3ad3c.gif#averageHue=%23fdf9f9&clientId=u0c10103c-f9b0-4&from=paste&height=301&id=u62dc2bce&originHeight=352&originWidth=508&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=1214980&status=done&style=shadow&taskId=u421fe27f-4a42-44df-8250-70f6c4591c3&title=&width=434.18805011395693)<br />我们来看一下**递归三部曲**：

1. **确定递归函数的参数和返回值**

参数就是要传入节点的指针，不需要其他参数了，通常此时定下来主要参数，如果在写递归的逻辑中发现还需要其他参数的时候，随时补充。<br />返回值的话其实也不需要，但是题目中给出的要返回root节点的指针，可以直接使用题目定义好的函数，所以就函数的返回类型为TreeNode。
```java
TreeNode invertTree(TreeNode root); 
```

2. 确定终止条件

当前节点为空的时候, 就返回
```java
if(root == NULL) return root;
```

3. 确定单层递归的逻辑

因为是先前序遍历, 所以进行交换左右孩子节点, 然后翻转左子树, 翻转右子树
```java
swap(root.left, root.right);
invertTree(root.left);
invertTree(root.right);
```
以下是递归完整代码
```java
//DFS递归
class Solution {
   /**
     * 前后序遍历都可以
     * 中序不行，因为先左孩子交换孩子，再根交换孩子（做完后，右孩子已经变成了原来的左孩子），
     * 再右孩子交换孩子（此时其实是对原来的左孩子做交换）
     */
    public TreeNode invertTree(TreeNode root) {
        // 终止条件
        if (root == null) return null;  // 这里写NULL或者写root都行

        invertTree2(root.left);
        invertTree2(root.right);
        swapChildren(root);
        return root;
    }

    private void swapChildren(TreeNode root) {
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }
}
```
<a name="Guquj"></a>
## BFS法
BFS层序遍历
```java
/**
 * BFS
 * @param root
 * @return
 */
public TreeNode invertTree(TreeNode root) {

    Deque<TreeNode> deque = new LinkedList<>();  // 双端队列

    if (root == null) return root;  // 这里写NULL或者写root都行

    deque.offerLast(root);
    while (!deque.isEmpty()){
        int len = deque.size();

        while (len-- > 0){
            TreeNode temp = deque.pollFirst();

            swap(temp);

            if (temp.left != null) deque.offerLast(temp.left);
            if (temp.right != null) deque.offerLast(temp.right);
        }
    }
    return root;
}
public void swap(TreeNode root){
    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;
}
```
<a name="EM1Vl"></a>
## 总结
针对二叉树的问题，解题之前一定要想清楚究竟是**前中后序遍历**，还是**层序遍历**。<br />二叉树解题的大忌就是自己稀里糊涂的过了（因为这道题相对简单），但是也不知道自己是怎么遍历的。<br />这也是造成了二叉树的题目“一看就会，一写就废”的原因。<br />针对翻转二叉树，我给出了一种递归，三种迭代（两种模拟深度优先遍历，一种层序遍历）的写法，都是之前我们讲过的写法，融汇贯通一下而已。<br />大家一定也有自己的解法，但一定要成方法论，这样才能通用，才能举一反三！
<a name="DlHPy"></a>
# 101. 对称二叉树 （优先掌握递归）  
:::info
先看视频讲解，会更容易一些。 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0101.%E5%AF%B9%E7%A7%B0%E4%BA%8C%E5%8F%89%E6%A0%91.html](https://programmercarl.com/0101.%E5%AF%B9%E7%A7%B0%E4%BA%8C%E5%8F%89%E6%A0%91.html)
:::
首先想清楚，判断对称二叉树要比较的是哪两个节点，要比较的可不是左右节点！<br />对于二叉树是否对称，要比较的是根节点的左子树与右子树是不是相互翻转的，理解这一点就知道了其实我们要比较的是两个树（这两个树是根节点的左右子树），所以在递归遍历的过程中，也是**要同时遍历两棵树**。<br />那么如何比较呢？比较的是两个子树的里侧和外侧的元素是否相等。如图所示：<br />![20210203144624414.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685344859016-e53049ac-86d9-442a-9382-3b3033c85073.png#averageHue=%23f7f7f7&clientId=u44d7a7ea-63e2-4&from=paste&height=662&id=u2d286b0e&originHeight=774&originWidth=1074&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=101569&status=done&style=shadow&taskId=u9f115f33-a26d-4715-8d85-da911898fff&title=&width=917.9487516188775)<br />那么遍历的顺序应该是什么样的呢？<br />本题遍历只能是“后序遍历”，因为我们要通过递归函数的返回值来判断两个子树的内侧节点和外侧节点是否相等。正是因为要遍历两棵树而且要比较内侧和外侧节点，所以准确的来说是**一个树的遍历顺序是左右中，一个树的遍历顺序是右左中。**但都可以理解算是后序遍历，尽管已经不是严格上在一个树上进行遍历的后序遍历了。<br />其实后序也可以理解为是一种回溯，当然这是题外话，讲回溯的时候会重点讲的。<br />先来看看递归法的代码应该怎么写。
<a name="VQHs5"></a>
## 递归法
递归三部曲

1. 确定递归函数的参数和返回值

因为我们要比较的是根节点的两个子树是否是相互翻转的，进而判断这个树是不是对称树，所以要比较的是两个树，**参数自然也是左子树节点和右子树节点。返回值自然是bool类型**。
```java
boolean compare(TreeNode left, TreeNode right);
```

2. 确定终止条件

要比较两个节点数值相不相同，首先要把两个节点为空的情况弄清楚！否则后面比较数值的时候就会操作空指针了。<br />**节点为空的情况有**：（注意我们比较的其实不是左孩子和右孩子，所以如下我称之为左节点右节点）

- 左节点为空，右节点不为空，不对称，return false
- 左不为空，右为空，不对称 return false
- 左右都为空，对称，返回true

此时已经排除掉了节点为空的情况，那么剩下的就是左右节点不为空：

- 左右都不为空，比较节点数值，不相同就return false

此时左右节点不为空，且数值也不相同的情况我们也处理了。代码如下：
```java
if(left==null && right!=null) return false;
else if(left!=null && right==null) return false;
else if(left==null && right==null) return true;
else if(left.val != right.val) return false;  // 注意这里没使用else要用else if
```
注意上面最后一种情况，没有使用else，而是else if， 因为我们把以上情况都排除之后，剩下的就是 左右节点都不为空，且数值相同的情况。

3. 确定单层递归的逻辑

此时才进入单层递归的逻辑，单层递归的逻辑就是处理 左右节点都不为空，且数值相同的情况。

- **比较二叉树外侧是否对称**：传入的是左节点的左孩子，右节点的右孩子。
- **比较内侧是否对称**，传入左节点的右孩子，右节点的左孩子。
- 如果左右都对称就返回true ，有一侧不对称就返回false 。

代码如下：
```java
boolean outside = compare(left.left, right.right);  // 左子树：左、 右子树：右
boolean inside = compare(left.right, right.left);    // 左子树：右、 右子树：左
boolean isSame = outside && inside;                    // 左子树：中、 右子树：中（逻辑处理）
return isSame;
```
如上代码中，我们可以看出使用的遍历方式，左子树左右中，右子树右左中，所以我把这个遍历顺序也称之为“后序遍历”（尽管不是严格的后序遍历）。
```java
/**
 * 递归法
 */
public boolean isSymmetric(TreeNode root) {
    return compare(root.left, root.right);
}
private boolean compare(TreeNode leftNode, TreeNode rightNode){
    if(leftNode==null && rightNode!=null) return false;
    else if(leftNode!=null && rightNode==null) return false;
    else if(leftNode==null && rightNode==null) return true;
    else if(leftNode.val != rightNode.val) return false;  // 注意这里没使用else要用else if
    // 比较外侧
    boolean compareOutside = compare(leftNode.left, rightNode.right);
    // 比较内侧
    boolean compareInside = compare(leftNode.right, rightNode.left);
    return compareOutside && compareInside; // 注意这里的返回方式
}
```
给出的代码并不简洁，但是把每一步判断的逻辑都清楚的描绘出来了。<br />如果上来就看网上各种简洁的代码，看起来真的很简单，但是很多逻辑都掩盖掉了，而题解可能也没有把掩盖掉的逻辑说清楚。盲目的照着抄，结果就是：发现这是一道“简单题”，稀里糊涂的就过了，但是真正的每一步判断逻辑未必想到清楚。<br />当然我可以把如上代码整理如下简洁版：
```java
public boolean isSymmetric(TreeNode root) {
    return compare(root.left, root.right);
}
private boolean compare(TreeNode leftNode, TreeNode rightNode){
    if(leftNode==null && rightNode!=null) return false;
    else if(leftNode!=null && rightNode==null) return false;
    else if(leftNode==null && rightNode==null) return true;
    else if(leftNode.val != rightNode.val) return false;  // 注意这里没使用else要用else if
    // &&前是比较外侧, &&后是比较内侧, 然后用与连接起来
    else return compare(leftNode.left, rightNode.right) && compare(leftNode.right, rightNode.left);
}
```
这个代码就很简洁了，但隐藏了很多逻辑，条理不清晰，而且递归三部曲，在这里完全体现不出来。<br />所以建议大家做题的时候，一定要想清楚逻辑，每一步做什么。把题目所有情况想到位，相应的代码写出来之后，再去追求简洁代码的效果。
<a name="dxQRT"></a>
## 迭代法
这道题目我们也可以使用迭代法，但要注意，这里的迭代法可**不是前中后序的迭代写法**，因为本题的本质是判断两个树是否是相互翻转的，其实已经不是所谓二叉树遍历的前中后序的关系了。<br />这里我们可以使用队列来比较两个树（根节点的左右子树）是否相互翻转，（**注意这不是层序遍历**）
<a name="ZXlxX"></a>
### 使用队列
通过队列来判断根节点的左子树和右子树的内侧和外侧是否相等，如动画所示：<br />![101.对称二叉树.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1685347409662-033be1f4-6204-4d2d-8470-e6e5e0056993.gif#averageHue=%23fefefe&clientId=u44d7a7ea-63e2-4&from=paste&height=361&id=u4c9c4548&originHeight=422&originWidth=634&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=1802013&status=done&style=shadow&taskId=ucd13dbd0-dfcb-4aeb-b103-cd25d8011db&title=&width=541.8803617563951)<br />如下的条件判断和递归的逻辑是一样的。
```java
/**
 * 迭代法
 * 使用双端队列，相当于两个栈
 */
public boolean isSymmetric2(TreeNode root) {
    Deque<TreeNode> deque = new LinkedList<>();
    deque.offerFirst(root.left);  // 这里插入对首
    deque.offerLast(root.right);  // 这里插入队尾

    while (!deque.isEmpty()) {
        TreeNode leftNode = deque.pollFirst();  // 这里是弹出队首
        TreeNode rightNode = deque.pollLast(); // 这里是弹出队尾

        if (leftNode==null && rightNode==null){
            continue;  // 跳过本次循环
        }

        /*if (leftNode == null && rightNode != null) {
            return false;
        }
        if (leftNode != null && rightNode == null) {
            return false;
        }
        if (leftNode.val != rightNode.val) {
            return false;
        }*/

        // 以上三个判断条件合并, 思考为什么可以这样合并: 上面的if已经判断的同时为null的情况, 所以接下来有一个为null将会是不等
        if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
            return false;
        }

        // 到达这里意味着每个左右节点的各自的左右孩子都不为空, 所以队首插入左节点的俩孩子, 队尾插入右节点的俩孩子,
        // 这样在接下来的循环中就可以分别从队首和队尾取出值来判断了
        deque.offerFirst(leftNode.left);
        deque.offerFirst(leftNode.right);
        deque.offerLast(rightNode.right);
        deque.offerLast(rightNode.left);

    }
    return true;  // 上边都没有return false 则最后就return true
}
```
```java
/**
 * 迭代法
 * 使用普通队列
 */
public boolean isSymmetric3(TreeNode root) {
    Queue<TreeNode> deque = new LinkedList<>();
    deque.offer(root.left);
    deque.offer(root.right);
    while (!deque.isEmpty()) {
        TreeNode leftNode = deque.poll();
        TreeNode rightNode = deque.poll();
        if (leftNode == null && rightNode == null) {
            continue;
        }
//            if (leftNode == null && rightNode != null) {
//                return false;
//            }
//            if (leftNode != null && rightNode == null) {
//                return false;
//            }
//            if (leftNode.val != rightNode.val) {
//                return false;
//            }
        // 以上三个判断条件合并
        if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
            return false;
        }
        // 这里顺序与使用Deque不同
        deque.offer(leftNode.left);
        deque.offer(rightNode.right);
        deque.offer(leftNode.right);
        deque.offer(rightNode.left);
    }
    return true;
}
```
<a name="ucli3"></a>
### 使用栈
细心的话，其实可以发现，这个迭代法，其实是把左右两个子树要比较的元素顺序放进一个容器，然后成对成对的取出来进行比较，那么其实使用栈也是可以的。<br />只要把队列原封不动的改成栈就可以了，下面给出代码。
```java
/**
 * 迭代法   使用栈
 * @param root
 * @return
 */
public boolean isSymmetric3(TreeNode root) {
    Stack<TreeNode> st = new Stack<>();
    st.push(root.left);
    st.push(root.right);
    while (!st.isEmpty()) {
        TreeNode leftNode = st.pop();
        TreeNode rightNode = st.pop();
        if (leftNode == null && rightNode == null) {
            continue;
        }

        if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
            return false;
        }

        st.push(leftNode.left);
        st.push(rightNode.right);
        st.push(leftNode.right);
        st.push(rightNode.left);
    }
    return true;
}
```
<a name="qHet8"></a>
## 总结
这次我们又深度剖析了一道二叉树的“简单题”，大家会发现，真正的把题目搞清楚其实并不简单，leetcode上accept了和真正掌握了还是有距离的。<br />我们介绍了递归法和迭代法，递归依然通过递归三部曲来解决了这道题目，如果只看精简的代码根本看不出来递归三部曲是如何解题的。<br />在迭代法中我们使用了队列，需要注意的是这不是层序遍历，而且仅仅通过一个容器来成对的存放我们要比较的元素，知道这一本质之后就发现，用队列，用栈，甚至用数组，都是可以的。<br />如果已经做过这道题目的同学，读完文章可以再去看看这道题目，思考一下，会有不一样的发现！
