时间: 2023.6.1
<a name="sPFyY"></a>
# 今日任务
第六章 二叉树part09, 669. 修剪二叉搜索树, 108.将有序数组转换为二叉搜索树, 538.把二叉搜索树转换为累加树, 总结篇 
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 669. 修剪二叉搜索树  的迭代法
- [ ] 108.将有序数组转换为二叉搜索树  的递归法
<a name="XBsr0"></a>
# 复习

- [ ] 235. 二叉搜索树的最近公共祖先  的迭代法
- [ ] 701.二叉搜索树中的插入操作  的迭代法
- [ ] 450.删除二叉搜索树中的节点  继续理解递归
- [ ] 450.删除二叉搜索树中的节点   看看迭代

-
<a name="SYbre"></a>
# 669. 修剪二叉搜索树 
:::info
这道题目比较难，比 添加增加和删除节点难的多，建议先看视频理解。<br />题目链接/文章讲解： [https://programmercarl.com/0669.%E4%BF%AE%E5%89%AA%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.html](https://programmercarl.com/0669.%E4%BF%AE%E5%89%AA%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.html)<br />视频讲解： [https://www.bilibili.com/video/BV17P41177ud](https://www.bilibili.com/video/BV17P41177ud)
:::
<a name="UwNpW"></a>
## 递归法
直接想法就是：递归处理，然后遇到 `root.val < low || root.val > high` 的时候直接`return NULL`，一波修改，赶紧利落。不难写出如下代码：
```java
class Solution {
        public TreeNode trimBST(TreeNode root, int low, int high) {
            if (root == nullptr || root.val < low || root.val > high) return null;
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
}
```
然而上面的这段代码犯了很严重的错误, 如下图. [1, 3]区间 在二叉搜索树的中可不是单纯的 节点3 和 左孩子节点0 就决定的，还要考虑节点0的右子树。我们在重新关注一下第二个示例，如图：<br />![20210204155302751.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685712823668-f4c026f5-46e5-472c-b5a7-860c904f36b5.png#averageHue=%23faf9f9&clientId=ucce7ccd2-dc62-4&from=paste&height=788&id=u10df1c0b&originHeight=922&originWidth=1370&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=116564&status=done&style=none&taskId=u6b3b08af-abfe-4f02-93b4-087fe96c713&title=&width=1170.940213890002)<br />所以以上的代码是不可行的！从图中可以看出需要重构二叉树，想想是不是本题就有点复杂了。其实不用重构那么复杂。<br />在上图中我们发现节点0并不符合区间要求，那么将节点0的右孩子 节点2 直接赋给 节点3 的左孩子就可以了（就是把节点0从二叉树中移除），如图：<br />![20210204155327203.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685712937172-ffc0f2cc-f904-4d68-887b-a0bcc406ef87.png#averageHue=%23f9f8f8&clientId=ucce7ccd2-dc62-4&from=paste&height=612&id=ucaa7a93a&originHeight=716&originWidth=830&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=81402&status=done&style=none&taskId=uc287eb00-dc34-46f2-83fb-83c55a0f7ef&title=&width=709.40173542241)<br />理解了最关键部分了我们再递归三部曲：

1. **确定递归函数的参数以及返回值**

这里我们为什么需要返回值呢？因为是要遍历整棵树，做修改，其实不需要返回值也可以，我们也可以完成修剪（其实就是从二叉树中移除节点）的操作。**但是有返回值，更方便，可以通过递归函数的返回值来移除节点。**
```java
TreeNode trimBST(TreeNode root, int low, int high)
```

2. **确定终止条件**. 修剪的操作并不是在终止条件上进行的，所以就是遇到空节点返回就可以了。
```java
if (root == null ) return null;
```

3. **确定单层递归的逻辑**. 如果root（当前节点）的元素小于low的数值，那么应该递归右子树，并返回右子树符合条件的头结点。代码如下：
```java
if (root.val < low) {
    TreeNode right = trimBST(root.right, low, high); // 寻找符合区间[low, high]的节点
    return right;
}
```
如果root(当前节点)的元素大于high的，那么应该递归左子树，并返回左子树符合条件的头结点。
```java
if (root.val > high) {
    TreeNode left = trimBST(root.left, low, high); // 寻找符合区间[low, high]的节点
    return left;
}
```
接下来要将下一层处理完左子树的结果赋给`root.left`，处理完右子树的结果赋给`root.right`。最后返回root节点，代码如下：
```java
root.left = trimBST(root.left, low, high); // root->left接入符合条件的左孩子
root.right = trimBST(root.right, low, high); // root->right接入符合条件的右孩子
return root;
```
此时大家是不是还没发现这多余的节点究竟是如何从二叉树中移除的呢？在回顾一下上面的代码，针对下图中二叉树的情况：<br />![20210204155327203-20230310120126738.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685713133854-630837d6-f92a-4660-a9c9-e6aa14dcdec5.png#averageHue=%23f9f8f8&clientId=ucce7ccd2-dc62-4&from=paste&height=612&id=ud9bd9191&originHeight=716&originWidth=830&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=81402&status=done&style=none&taskId=u68629871-7419-498c-91cb-8e23717b954&title=&width=709.40173542241)<br />如下代码相当于把节点0的右孩子（节点2）返回给上一层，
```java
if (root.val < low) {
    TreeNode right = trimBST(root.right, low, high); // 寻找符合区间[low, high]的节点
    return right;
}
```
然后如下代码相当于用节点3的左孩子 把下一层返回的 节点0的右孩子（节点2） 接住。
```java
root.left = trimBST(root.left, low, high);
```
此时节点3的左孩子就变成了节点2，将节点0从二叉树中移除了。最后整体代码如下：
```java
class Solution {
        public TreeNode trimBST(TreeNode root, int low, int high) {
            if(root == null) return null;

            // 在递归中移除节点
            if (root.val < low){
                TreeNode right = trimBST(root.right, low, high);
                return right;
            }
            if (root.val > high){
                TreeNode left = trimBST(root.left, low, high);
                return left;
            }

            // root在[low,high]范围内
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);

            return root;
        }
    }
```


<a name="nU0lp"></a>
# 108.将有序数组转换为二叉搜索树  
:::info
本题就简单一些，可以尝试先自己做做。<br />[https://programmercarl.com/0108.%E5%B0%86%E6%9C%89%E5%BA%8F%E6%95%B0%E7%BB%84%E8%BD%AC%E6%8D%A2%E4%B8%BA%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.html](https://programmercarl.com/0108.%E5%B0%86%E6%9C%89%E5%BA%8F%E6%95%B0%E7%BB%84%E8%BD%AC%E6%8D%A2%E4%B8%BA%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91.html)<br />视频讲解：[https://www.bilibili.com/video/BV1uR4y1X7qL](https://www.bilibili.com/video/BV1uR4y1X7qL)
:::
要转换为一棵**高度平衡二叉搜索树**。为什么强调要平衡呢？因为只要给我们一个有序数组，如果强调平衡，都可以以线性结构来构造二叉搜索树。<br />例如 有序数组[-10，-3，0，5，9] 就可以构造成这样的二叉搜索树，如图。<br />![20220930173553.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685726722616-ced99ea9-a6d5-4fd4-b319-141c0460e6d3.png#averageHue=%23f7f7f7&clientId=ucce7ccd2-dc62-4&from=paste&height=571&id=ud029f449&originHeight=668&originWidth=530&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=48345&status=done&style=none&taskId=uceaebca2-990a-4384-9745-8a97652752f&title=&width=452.99146960708106)<br />上图中，是符合二叉搜索树的特性吧，如果要这么做的话，是不是本题意义就不大了，所以才强调是平衡二叉搜索树。<br />其实数组构造二叉树，构成平衡树是自然而然的事情，因为大家默认都是**从数组中间位置取值作为节点元素**，一般不会随机取。所以想构成不平衡的二叉树是自找麻烦。<br />**本质就是寻找分割点，分割点作为当前节点，然后递归左区间和右区间**。分割点就是数组中间位置的节点。<br />那么为问题来了，如果数组长度为偶数，中间节点有两个，取哪一个？取哪一个都可以，只不过构成了不同的平衡二叉搜索树。例如：输入：[-10,-3,0,5,9]如下两棵树，都是这个数组的平衡二叉搜索树：<br />![108.将有序数组转换为二叉搜索树.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685726918983-5587c039-839e-4415-92cc-fcbf0e7daf31.png#averageHue=%23f9f9f9&clientId=ucce7ccd2-dc62-4&from=paste&height=692&id=ud9be7747&originHeight=810&originWidth=1414&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=106256&status=done&style=none&taskId=u523560eb-7031-4cf8-aab5-da0aae644ff&title=&width=1208.5470528762503)<br />如果要分割的数组长度为偶数的时候，中间元素为两个，是取左边元素 就是树1，取右边元素就是树2。这也是题目中强调答案不是唯一的原因。 理解这一点，这道题目算是理解到位了
<a name="oEMHd"></a>
## 递归法
递归三部曲：

1. **确定递归函数返回值及其参数**. 

**删除**二叉树节点，**增加**二叉树节点，都是用递归函数的返回值来完成，这样是比较方便的。那么本题要**构造**二叉树，依然用递归函数的返回值来构造中节点的左右孩子。**在构造二叉树的时候尽量不要重新定义左右区间数组，而是用下标来操作原数组**。所以代码如下：
```java
// 左闭右闭区间[left, right]
TreeNode traversal(int[] nums, int left, int right)
```
这里注意，我这里定义的是左闭右闭区间，在不断分割的过程中，也会坚持左闭右闭的区间，这又涉及到我们讲过的循环不变量。

2. **确定递归终止条件**.  这里定义的是左闭右闭的区间，所以当区间 `left > right`的时候，就是空节点了。代码如下：
```java
if (left > right) return null;
```

3. **确定单层递归的逻辑**.  首先取数组中间元素的位置，不难写出`int mid = (left + right) / 2;`，**这么写其实有一个问题，就是数值越界，例如 left和right 都是最大int，这么操作就越界了，在二分法中尤其需要注意！**

**所以可以这么写：**`**int mid = left + ((right - left) / 2);**`**,** 但本题leetcode的测试数据并不会越界，所以怎么写都可以。但需要有这个意识！<br />取了中间位置，就开始以中间位置的元素构造节点，代码：`TreeNode root = new TreeNode(nums[mid]);`。接着划分区间，root的左孩子接住下一层左区间的构造节点，右孩子接住下一层右区间构造的节点。最后返回root节点，单层递归整体代码如下：
```java
int mid = left + ((right - left) / 2);
TreeNode root = new TreeNode(nums[mid]);
root.left = traversal(nums, left, mid - 1);
root.right = traversal(nums, mid + 1, right);
return root;
```
这里`int mid = left + ((right - left) / 2);`的写法相当于是如果数组长度为偶数，中间位置有两个元素，取靠左边的。<br />完整代码如下
```java
class Solution {
    // 递归: 左闭右开 [left,right)
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length);
    }
    
    public TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left >= right) {  // 左闭右开的区间这里用 >=
            return null;
        }
        if (right - left == 1) {
            return new TreeNode(nums[left]);
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, left, mid);
        root.right = sortedArrayToBST(nums, mid + 1, right);
        return root;
    }
}

```
:::danger
注意: 去中间值时的数组越界问题
:::
```java
class Solution {
    // 递归: 左闭右闭 [left,right]
	public TreeNode sortedArrayToBST(int[] nums) {
		TreeNode root = traversal(nums, 0, nums.length - 1);
		return root;
	}

	// 左闭右闭区间[left, right]
	private TreeNode traversal(int[] nums, int left, int right) {
		if (left > right) return null;   // 左闭右闭的区间这里用 > 号

		int mid = left + ((right - left) >> 1);
		TreeNode root = new TreeNode(nums[mid]);
		root.left = traversal(nums, left, mid - 1);
		root.right = traversal(nums, mid + 1, right);
		return root;
	}
}
```
注意：在调用traversal的时候传入的 left和right 为什么是0和`nums.length - 1`，因为定义的区间为左闭右闭
<a name="xslST"></a>
## 迭代法
迭代法可以通过三个队列来模拟，一个队列放遍历的节点，一个队列放左区间下标，一个队列放右区间下标。<br />模拟的就是不断分割的过程，C++代码如下：（我已经详细注释）
```java
class Solution {
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums.length == 0) return null;

		//根节点初始化
		TreeNode root = new TreeNode(-1);
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		Queue<Integer> leftQueue = new LinkedList<>();
		Queue<Integer> rightQueue = new LinkedList<>();

		// 根节点入队列
		nodeQueue.offer(root);
		// 0为左区间下标初始位置
		leftQueue.offer(0);
		// nums.size() - 1为右区间下标初始位置
		rightQueue.offer(nums.length - 1);

		while (!nodeQueue.isEmpty()) {
			TreeNode currNode = nodeQueue.poll();
			int left = leftQueue.poll();
			int right = rightQueue.poll();
			int mid = left + ((right - left) >> 1);

			// 将mid对应的元素给中间节点
			currNode.val = nums[mid];

			// 处理左区间
			if (left <= mid - 1) {
				currNode.left = new TreeNode(-1);
				nodeQueue.offer(currNode.left);
				leftQueue.offer(left);
				rightQueue.offer(mid - 1);
			}

			// 处理右区间
			if (right >= mid + 1) {
				currNode.right = new TreeNode(-1);
				nodeQueue.offer(currNode.right);
				leftQueue.offer(mid + 1);
				rightQueue.offer(right);
			}
		}
		return root;
	}
}
```
-
<a name="GCzNR"></a>
# 538.把二叉搜索树转换为累加树  
:::info
本题也不难，在 求二叉搜索树的最小绝对差 和 众数 那两道题目 都讲过了 双指针法，思路是一样的。<br />[https://programmercarl.com/0538.%E6%8A%8A%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E8%BD%AC%E6%8D%A2%E4%B8%BA%E7%B4%AF%E5%8A%A0%E6%A0%91.html](https://programmercarl.com/0538.%E6%8A%8A%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E8%BD%AC%E6%8D%A2%E4%B8%BA%E7%B4%AF%E5%8A%A0%E6%A0%91.html)<br />视频讲解：[https://www.bilibili.com/video/BV1d44y1f7wP](https://www.bilibili.com/video/BV1d44y1f7wP)
:::
一看到累加树，相信很多小伙伴都会疑惑：如何累加？遇到一个节点，然后再遍历其他节点累加？怎么一想这么麻烦呢。然后再发现这是一棵二叉搜索树，二叉搜索树啊，这是有序的啊。<br />那么有序的元素如何求累加呢？其实这就是一棵树，大家可能看起来有点别扭，**换一个角度来看，这就是一个有序数组[2, 5, 13]**，**求从后到前的累加数组，也就是[20, 18, 13]，**是不是感觉这就简单了。<br />为什么变成数组就是感觉简单了呢？因为数组大家都知道怎么遍历啊，从后向前，挨个累加就完事了，这换成了二叉搜索树，看起来就别扭了一些是不是。<br />那么知道如何遍历这个二叉树，也就迎刃而解了，**从树中可以看出累加的顺序是右中左，所以我们需要反中序遍历这个二叉树，然后顺序累加就可以了**
<a name="zQqyD"></a>
## 递归法
遍历顺序如图所示：<br />![20210204153440666.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685728188766-4174c190-798e-4895-bfd2-505e89b2eaea.png#averageHue=%23f9f8f8&clientId=ucce7ccd2-dc62-4&from=paste&height=465&id=ue2fbfd92&originHeight=544&originWidth=854&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=60893&status=done&style=none&taskId=uad6b3aed-790b-419a-a7e7-15653ad0704&title=&width=729.9145566876363)<br />本题依然需要一个`**pre**`**指针**记录当前遍历节点`**cur**`的前一个节点，这样才方便做累加。

1. **递归函数参数以及返回值**.  这里很明确了，不需要递归函数的返回值做什么操作了，要遍历整棵树。同时需要定义一个全局变量`pre`，用来保存`cur`节点的前一个节点的数值，定义为int型就可以了。代码如下：
```java
int pre = 0; // 记录前一个节点的数值
void traversal(TreeNode cur)
```

2. **确定终止条件**.  遇空就终止。
```java
if (cur == null) return;
```

3. 确定单层递归的逻辑.  注意要右中左来遍历二叉树， 中节点的处理逻辑就是让cur的数值加上前一个节点的数值。代码如下：
```java
traversal(cur.right);  // 右
cur.val += pre;        // 中
pre = cur.val;
traversal(cur.left);   // 左
```

完整代码如下
```java
class Solution {
    int sum;
    public TreeNode convertBST(TreeNode root) {
        sum = 0;
        convertBST1(root);
        return root;
    }

    // 按右中左顺序遍历，累加即可
    public void convertBST1(TreeNode root) {
        if (root == null) {
            return;
        }
        convertBST1(root.right);
        sum += root.val;
        root.val = sum;
        convertBST1(root.left);
    }
}
```
<a name="OsYnv"></a>
## 迭代法
迭代法其实就是中序模板题了
```cpp
class Solution {
private:
    int pre; // 记录前一个节点的数值
    void traversal(TreeNode* root) {
        stack<TreeNode*> st;
        TreeNode* cur = root;
        while (cur != NULL || !st.empty()) {
            if (cur != NULL) {
                st.push(cur);
                cur = cur->right;   // 右
            } else {
                cur = st.top();     // 中
                st.pop();
                cur->val += pre;
                pre = cur->val;
                cur = cur->left;    // 左
            }
        }
    }
public:
    TreeNode* convertBST(TreeNode* root) {
        pre = 0;
        traversal(root);
        return root;
    }
};
```
-
<a name="ODON1"></a>
# 总结篇  
:::info
好了，二叉树大家就这样刷完了，做一个总结吧<br />[https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E6%80%BB%E7%BB%93%E7%AF%87.html](https://programmercarl.com/%E4%BA%8C%E5%8F%89%E6%A0%91%E6%80%BB%E7%BB%93%E7%AF%87.html)
:::

