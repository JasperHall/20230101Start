时间: 2023.5.30
<a name="sPFyY"></a>
# 今日任务
第六章 二叉树part07, 530.二叉搜索树的最小绝对差, 501.二叉搜索树中的众数, 236. 二叉树的最近公共祖先  
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 236. 二叉树的最近公共祖先  重点复习一下
- [ ] 501.二叉搜索树中的众数  的二叉搜索树递归法再理解理解
<a name="XBsr0"></a>
# 复习

- [ ] 617.合并二叉树   的迭代法
- [ ] 98.验证二叉搜索树  学习本题的迭代法
<a name="RMGVJ"></a>
# 530.二叉搜索树的最小绝对差 
:::info
需要领悟一下二叉树遍历上双指针操作，优先掌握递归 <br />题目链接/文章讲解：[https://programmercarl.com/0530.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E7%9A%84%E6%9C%80%E5%B0%8F%E7%BB%9D%E5%AF%B9%E5%B7%AE.html](https://programmercarl.com/0530.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E7%9A%84%E6%9C%80%E5%B0%8F%E7%BB%9D%E5%AF%B9%E5%B7%AE.html)<br />视频讲解：[https://www.bilibili.com/video/BV1DD4y11779](https://www.bilibili.com/video/BV1DD4y11779)
:::
注意是二叉搜索树，二叉搜索树可是有序的。<br />遇到在二叉搜索树上求什么最值啊，差值之类的，就把它想成在一个有序数组上求最值，求差值，这样就简单多了。
<a name="Bl0HX"></a>
## 递归
用一个pre节点记录一下当前节点的前一个节点。<br />![20210204153247458.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685587090748-1f3b8ccf-eebd-4e93-a4ad-5bf938a86fdd.png#averageHue=%23f8f6f6&clientId=u08c9bfa8-e61d-4&from=paste&height=624&id=ud250d359&originHeight=730&originWidth=1120&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=124611&status=done&style=none&taskId=u63e1f003-a3c0-45d6-9f9a-21708b8335b&title=&width=957.2649923772279)<br />一些同学不知道在递归中如何记录前一个节点的指针，其实实现起来是很简单的，大家只要看过一次，写过一次，就掌握了。代码如下：
```java
class Solution {
    TreeNode pre;  // 记录上一个遍历的节点, 中序遍历的话二叉搜索树的值是单调递增的, 所以只记录上一个节点就可以
    int min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        findMin(root);
        return min;
    }

    private void findMin(TreeNode root){
        if (root == null) return;

        // 左
        findMin(root.left);

        // 中
        if (pre!=null){
            min = Math.min(min, root.val-pre.val);
        }
        pre = root;  // 赋值当前节点, 给下一次的递归使用
        findMin(root.right);

    }
}
```
<a name="eTryA"></a>
## 迭代
中序遍历的迭代法
```java
class Solution {
    // 統一迭代法-中序遍历
    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        int result = Integer.MAX_VALUE;

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
                if(pre != null)
                    result = Math.min(result, temp.val - pre.val);
                pre = temp;
            }
        }
        return result;
    }
}
```
```java
class Solution {
    // 迭代法-中序遍历
    TreeNode pre;
    Stack<TreeNode> stack;
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return 0;
        stack = new Stack<>();
        TreeNode cur = root;
        int result = Integer.MAX_VALUE;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur); // 将访问的节点放进栈
                cur = cur.left; // 左
            }else {
                cur = stack.pop(); 
                if (pre != null) { // 中
                    result = Math.min(result, cur.val - pre.val);
                }
                pre = cur;
                cur = cur.right; // 右
            }
        }
        return result;
    }
}
```
-
<a name="lt5UY"></a>
# 501.二叉搜索树中的众数 
:::info
和 530差不多双指针思路，不过 这里涉及到一个很巧妙的代码技巧。<br />可以先自己做做看，然后看我的视频讲解。<br />[https://programmercarl.com/0501.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E4%BC%97%E6%95%B0.html](https://programmercarl.com/0501.%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E4%BC%97%E6%95%B0.html)<br />视频讲解：[https://www.bilibili.com/video/BV1fD4y117gp](https://www.bilibili.com/video/BV1fD4y117gp)
:::
<a name="tOzV1"></a>
## 递归法
<a name="NMed5"></a>
### 如果不是二叉搜索树
如果不是二叉搜索树, 最直观的方法一定是把这个树都遍历了, 用map统计频率, 把频率排个序, 最后取前面高频的元素的集合<br />具体步骤如下：

1. 这个树都遍历了，用map统计频率. 至于用前中后序哪种遍历也不重要，因为就是要全遍历一遍，怎么个遍历法都行，层序遍历都没毛病！这里采用前序遍历，代码如下：
```java
// Map<Integer,Integer>  key:元素  value:出现频率
private void searchBST(TreeNode curr, Map<Integer,Integer> map){  // 前序遍历
    if (curr == null) return;

    map.put(curr.val, map.getOrDefault(curr.val,0) + 1);  // 统计元素频率存入map中

    searchBST(curr.left, map);
    searchBST(curr.right, map);
}
```

2. 把统计的出来的出现频率（即map中的value）排个序   // 按照频率从大到小排序
```java
List<Map.Entry<Integer, Integer>> mapList 
    = map.entrySet().stream().sorted((c1,c2) -> c2.getValue().compareTo(c1.getValue())).collect(Collectors.toList());
list.add(mapList.get(0).getKey());
```

3. 取前面高频的元素. 此时数组vector中已经是存放着按照频率排好序的pair，那么把前面高频的元素取出来就可以了。
```java
list.add(mapList.get(0).getKey());  // 这里先加一下最大的, 方便接下来的比较

// 把频率最高的加入List
for (int i=1; i<mapList.size(); i++){  // i的初始值为1, 使用上面你加入的索引0的位置的值做第一次比较
    if (mapList.get(i).getValue() == mapList.get(i-1).getValue()){
        list.add(mapList.get(i).getKey());
    }else {
        break;
    }
}
```
完整代码如下
```java
class Solution {
    /**
     * 暴力法
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        if(root == null) return list.stream().mapToInt(Integer::intValue).toArray();

        // 获得频率 Map
        searchBST(root, map);  // 调用自定义的递归函数

        List<Map.Entry<Integer, Integer>> mapList
                = map.entrySet().stream().sorted((c1,c2) -> c2.getValue().compareTo(c1.getValue())).collect(Collectors.toList());
        list.add(mapList.get(0).getKey());  // 这里先加一下最大的, 方便接下来的比较

        // 把频率最高的加入List
        for (int i=1; i<mapList.size(); i++){  // i的初始值为1, 使用上面你加入的索引0的位置的值做第一次比较
            if (mapList.get(i).getValue() == mapList.get(i-1).getValue()){
                list.add(mapList.get(i).getKey());
            }else {
                break;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    // Map<Integer,Integer>  key:元素  value:出现频率
    private void searchBST(TreeNode curr, Map<Integer,Integer> map){  // 前序遍历
        if (curr == null) return;

        map.put(curr.val, map.getOrDefault(curr.val,0) + 1);  // 统计元素频率存入map中

        searchBST(curr.left, map);
        searchBST(curr.right, map);
    }
}
```
:::danger

1. `list.stream()`的用法
2. Java 8中的`mapToInt`方法
3. `map.getOrDefault`方法
:::
所以如果本题没有说是二叉搜索树的话，那么就按照上面的思路写！
<a name="AdWNm"></a>
### 是二叉搜索树
既然是搜索树，它中序遍历就是有序的。<br />![20210204152758889.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685594302875-e09e073f-3ff5-47ee-8488-a9cdafe78e0c.png#averageHue=%23f9f7f7&clientId=ua6fe51d7-d50c-4&from=paste&height=670&id=u900348c2&originHeight=784&originWidth=974&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=115211&status=done&style=none&taskId=ubd348ca9-4a68-4cc3-87b1-ccd84e90465&title=&width=832.4786630137679)<br />中序遍历代码如下：
```java
void searchBST(TreeNode cur) {
    if (cur == NULL) return ;
    searchBST(cur.left); // 左

    （处理节点!!!）       // 中

    searchBST(cur.right); // 右
    return ;
}
```
遍历**有序数组**的元素出现频率，从头遍历，那么一定是**相邻两个元素作比较**，然后就把出现频率最高的元素输出就可以了。<br />关键是在有序数组上的话，好搞，在树上怎么搞呢？这就考察对树的操作了。弄一个指针指向前一个节点，这样每次cur（当前节点）才能和pre（前一个节点）作比较(双指针). 而且初始化的时候`**pre = NULL**`，**这样当pre=NULL时候，我们就知道这是比较的第一个元素。**
```java
if (pre == NULL) { // 第一个节点
    count = 1; // 频率为1
} else if (pre.val == cur.val) { // 与前一个节点数值相同
    count++;
} else { // 与前一个节点数值不同
    count = 1;
}
pre = cur; // 更新上一个节点
```
此时又有问题了，因为要求最大频率的元素集合（注意是集合，不是一个元素，可以有多个众数），可以先遍历一遍数组，找出最大频率（maxCount），然后再重新遍历一遍数组把出现频率为maxCount的元素放进集合。（因为众数有多个）这种方式遍历了两遍数组。这么遍历两遍二叉搜索树，把众数集合算出来也是可以的。<br />接下来的操作重点理解<br />但这里其实只需要遍历一次就可以找到所有的众数。如果 **频率count 等于 maxCount（最大频率），当然要把这个元素加入到结果集中（以下代码为result数组）**，代码如下：
```java
if (count == maxCount) { // 如果和最大值相同，放进result中
    result.push_back(cur->val);
}
```
是不是感觉这里有问题，result怎么能轻易就把元素放进去了呢，万一，这个maxCount此时还不是真正最大频率呢。<br />所以下面要做如下操作：频率 count 大于 maxCount的时候，不仅要更新maxCount，而且要清空结果集（以下代码为result数组），因为结果集之前的元素都失效了。
```java
if (count > maxCount) { // 如果计数大于最大值
    maxCount = count;   // 更新最大频率
    result.clear();     // 很关键的一步，不要忘记清空result，之前result里的元素都失效了
    result.push_back(cur->val);
}
```
关键代码都讲完了，完整代码如下：（只需要遍历一遍二叉搜索树，就求出了众数的集合）
```java
class Solution {
    ArrayList<Integer> resList;
    int maxCount;
    int count;
    TreeNode pre;

    public int[] findMode(TreeNode root) {
        resList = new ArrayList<>();
        maxCount = 0;
        count = 0;
        pre = null;
        findMode1(root);
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    public void findMode1(TreeNode root) {
        if (root == null) {
            return;
        }
        findMode1(root.left);

        int rootValue = root.val;
        // 计数
        if (pre == null || rootValue != pre.val) {
            count = 1;
        } else {
            count++;
        }
        // 更新结果以及maxCount
        if (count > maxCount) {
            resList.clear();
            resList.add(rootValue);
            maxCount = count;
        } else if (count == maxCount) {
            resList.add(rootValue);
        }
        pre = root;

        findMode1(root.right);
    }
}
```
:::danger
重点操作:<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685599300352-e9aea12b-b212-4d76-a34c-03bda2826d2f.png#averageHue=%23282d36&clientId=ua6fe51d7-d50c-4&from=paste&height=162&id=u4cfd9d63&originHeight=190&originWidth=423&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=11804&status=done&style=none&taskId=u6238aa59-c31c-448d-9dee-cc63bc85aad&title=&width=361.53847479961377)
:::
<a name="m3cww"></a>
## 迭代法
只要**把中序遍历转成迭代**，中间节点的处理逻辑完全一样。<br />下面我给出其中的一种中序遍历的迭代法，其中间处理逻辑一点都没有变（从递归法直接粘过来的代码，连注释都没改，哈哈）代码如下：
```java
class Solution {
    public int[] findMode(TreeNode root) {
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        int maxCount = 0;
        int count = 0;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur =cur.left;
            }else {
                cur = stack.pop();
                // 计数
                if (pre == null || cur.val != pre.val) {
                    count = 1;
                }else {
                    count++;
                }
                // 更新结果
                if (count > maxCount) {
                    maxCount = count;
                    result.clear();
                    result.add(cur.val);
                }else if (count == maxCount) {
                    result.add(cur.val);
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
```
-
<a name="jKrGE"></a>
# 236. 二叉树的最近公共祖先 
:::info
本题其实是比较难的，可以先看我的视频讲解 <br />[https://programmercarl.com/0236.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88.html](https://programmercarl.com/0236.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88.html)<br />视频讲解：[https://www.bilibili.com/video/BV1jd4y1B7E2](https://www.bilibili.com/video/BV1jd4y1B7E2)
:::
![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685602971826-31843dc3-4a05-41bd-bdb5-78d3a2c053ed.png#averageHue=%232f2f2e&clientId=ua6fe51d7-d50c-4&from=paste&height=152&id=u1bfed988&originHeight=178&originWidth=522&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=9070&status=done&style=none&taskId=u4b120b1a-d52c-4829-a343-fa0db2be96e&title=&width=446.1538625186723)<br />遇到这个题目首先想的是要是能自底向上查找就好了，这样就可以找到公共祖先了。<br />那么二叉树如何可以自底向上查找呢？回溯啊，**二叉树回溯的过程就是从底到上**。而**后序遍历（左右中）就是天然的回溯过程**，可以根据左右子树的返回值，来处理中节点的逻辑。<br />接下来就看如何判断一个节点是 节点q 和 节点p 的公共祖先呢。首先最容易想到的一个情况：如果找到一个节点，发现左子树出现结点p，右子树出现节点q，或者 左子树出现结点q，右子树出现节点p，那么该节点就是节点p和q的最近公共祖先。<br />即情况一：<br />![20220922173502.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685606306417-d860d4a2-8e99-4ee4-be57-ee2cab0eb617.png#averageHue=%23f7f6f6&clientId=ua6fe51d7-d50c-4&from=paste&height=581&id=u169da352&originHeight=680&originWidth=940&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=83163&status=done&style=none&taskId=u5c946399-181d-44f4-95ab-55ad75ba93e&title=&width=803.4188328880306)<br />判断逻辑是 如果递归遍历遇到q，就将q返回，遇到p 就将p返回，那么如果 左右子树的返回值都不为空，说明此时的中节点，一定是q 和p 的最近祖先。<br />那么有录友可能疑惑，会不会左子树 遇到q 返回，右子树也遇到q返回，这样并没有找到 q 和p的最近祖先。这么想的录友，要审题了，题目强调：**二叉树节点数值是不重复的，而且一定存在 q 和 p**。但是很多人容易忽略一个情况，就是**节点本身p(q)，它拥有一个子孙节点q(p)**。<br />情况二：<br />![20220922173530.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685606326084-742a4e38-479b-4b5d-b6ec-a8f671db5b5a.png#averageHue=%23f7f6f6&clientId=ua6fe51d7-d50c-4&from=paste&height=571&id=u3f6e43d8&originHeight=668&originWidth=964&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=83630&status=done&style=none&taskId=u3b6704dd-de28-428b-a0a6-e9060a66e03&title=&width=823.9316541532569)<br />其实情况一 和 情况二 代码实现过程都是一样的，也可以说，实现情况一的逻辑，顺便包含了情况二。<br />**因为遇到 q 或者 p 就返回，这样也包含了 q 或者 p 本身就是 公共祖先的情况**。这一点是很多录友容易忽略的，在下面的代码讲解中，可以再去体会。

递归三部曲：

1. 确定递归函数返回值以及参数

需要递归函数返回值，来告诉我们是否找到节点q或者p，那么返回值为boolean类型就可以了。但我们还要返回最近公共节点，可以利用上题目中返回值是TreeNode ，那么如果遇到p或者q，就把q或者p返回，返回值不为空，就说明找到了q或者p。
```java
TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
```

2. 确定终止条件

遇到空的话，因为树都是空了，所以返回空。那么我们来说一说，如果 `root == q`，或者 `root == p`，说明找到 q p ，则将其返回，这个返回值，后面在中节点的处理过程中会用到，那么中节点的处理逻辑，下面讲解。
```java
if (root == q || root == p || root == NULL) return root;
```

3. 确定单层递归逻辑. 值得注意的是 本题函数有返回值，是因为回溯的过程需要递归函数的返回值做判断，但**本题我们依然要遍历树的所有节点**。

如果递归函数有返回值，**如何区分要搜索一条边，还是搜索整个树呢**？搜索一条边的写法：
```java
if (递归函数(root.left)) return ;
if (递归函数(root.right)) return ;
```
搜索整个树写法：
```java
left = 递归函数(root.left);  // 左
right = 递归函数(root.right); // 右
left与right的逻辑处理;         // 中 
```
在递归函数有返回值的情况下：

- 如果要搜索一条边，递归函数返回值不为空的时候，立刻返回，
- 如果搜索整个树，直接用一个变量left、right接住返回值，这个left、right后序还有逻辑处理的需要，也就是后序遍历中处理中间节点的逻辑（也是回溯）。

那么为什么要遍历整棵树呢？直观上来看，找到最近公共祖先，直接一路返回就可以了。如图：<br />![2021020415105872.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685606793897-e38f140b-c370-4566-8ccf-4bd051be2356.png#averageHue=%23f7f6f6&clientId=ua6fe51d7-d50c-4&from=paste&height=675&id=u7684b945&originHeight=790&originWidth=962&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=113873&status=done&style=none&taskId=u9fda2710-60e3-49be-bae9-394d97dd4f1&title=&width=822.2222523811547)<br />就像图中一样直接返回7，美滋滋。但事实上还要遍历根节点右子树（即使此时已经找到了目标节点了），也就是图中的节点4、15、20。<br />因为在如下代码的后序遍历中，如果想利用 left和right 做逻辑处理， 不能立刻返回，而是要**等left与right逻辑处理完之后才能返回**。
```java
eft = 递归函数(root.left);  // 左
right = 递归函数(root.right); // 右
left与right的逻辑处理;         // 中 
```
所以此时大家要知道**我们要遍历整棵树**。知道这一点，对本题就有一定深度的理解了。那么先用left和right接住左子树和右子树的返回值，代码如下：
```java
TreeNode left = lowestCommonAncestor(root.left, p, q);
TreeNode right = lowestCommonAncestor(root.right, p, q);
```

- 如果 left 和 right 都不为空，说明此时root就是最近公共节点。这个比较好理解
- 如果 left为空，right不为空，就返回 right，说明目标节点是通过right返回的，反之依然。

这里有的同学就理解不了了，为什么left为空，right不为空，目标节点通过right返回呢？如图：<br />![20210204151125844.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685606951898-6637621e-cabe-4036-b5db-400f20550be2.png#averageHue=%23faf7f7&clientId=ua6fe51d7-d50c-4&from=paste&height=662&id=uad0f611c&originHeight=774&originWidth=1078&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=125690&status=done&style=none&taskId=uab087f39-d542-4535-bb94-9018e880917&title=&width=921.3675551630819)<br />图中节点10的左子树返回null，右子树返回目标值7，那么此时节点10的处理逻辑就是把右子树的返回值（最近公共祖先7）返回上去！<br />**这里也很重要，可能刷过这道题目的同学，都不清楚结果究竟是如何从底层一层一层传到头结点的**。<br />那么如果left和right都为空，则返回left或者right都是可以的，也就是返回空。
```java
if (left == NULL && right != NULL) return right;
else if (left != NULL && right == NULL) return left;
else  { //  (left == NULL && right == NULL)
    return NULL;
}
```
那么寻找最小公共祖先，完整流程图如下：<br />![202102041512582.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685607041268-3450e5bf-b261-46ec-86d6-d802b7ae6427.png#averageHue=%23f7f5f5&clientId=ua6fe51d7-d50c-4&from=paste&height=663&id=u16584628&originHeight=776&originWidth=1036&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=145057&status=done&style=none&taskId=u19892a9f-73ca-485b-93bf-b8c271d98a2&title=&width=885.4701179489358)<br />从图中，大家可以看到，我们是如何回溯遍历整棵二叉树，将结果返回给头结点的！
<a name="jsmWc"></a>
## 完整代码
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) { // 递归结束条件
            return root;
        }

        // 后序遍历
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);


        // 重点理解下面这些if语句
        if(left == null && right == null) { // 若未找到节点 p 或 q
            return null;
        }else if(left == null && right != null) { // 若找到一个节点
            return right;
        }else if(left != null && right == null) { // 若找到一个节点
            return left;
        }else { // 若找到两个节点
            return root;
        }
    }
}

```
<a name="FVOcz"></a>
## 总结
这道题目刷过的同学未必真正了解这里面回溯的过程，以及结果是如何一层一层传上去的。<br />那么我给大家归纳如下三点：

1. 求最小公共祖先，需要从底向上遍历，那么二叉树，只能通过后序遍历（即：回溯）实现从底向上的遍历方式。
2. 在回溯的过程中，必然要遍历整棵二叉树，即使已经找到结果了，依然要把其他节点遍历完，因为要使用递归函数的返回值（也就是代码中的left和right）做逻辑判断。
3. 要理解如果返回值left为空，right不为空为什么要返回right，为什么可以用返回right传给上一层结果。

可以说这里每一步，都是有难度的，都需要对二叉树，递归和回溯有一定的理解。<br />本题没有给出迭代法，因为迭代法不适合模拟回溯的过程。理解递归的解法就够了。
