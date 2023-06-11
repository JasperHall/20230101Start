时间: 2023.6.7周三
<a name="sPFyY"></a>
# 今日任务
第七章 回溯算法part05, 491.递增子序列, 46.全排列, 47.全排列 II
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] day29__47.全排列 II 的去重操作继续理解 

<a name="XBsr0"></a>
# 复习

- [ ] day28__90.子集II  的去重再理解理解
- [ ] 40.数组总和II  的回溯去重再看看
- [ ] day28__90.子集II 的去重操作

<a name="WTCcr"></a>
# 491.递增子序列
:::info
本题和大家刚做过的 90.子集II 非常像，但又很不一样，很容易掉坑里。 <br />[https://programmercarl.com/0491.%E9%80%92%E5%A2%9E%E5%AD%90%E5%BA%8F%E5%88%97.html](https://programmercarl.com/0491.%E9%80%92%E5%A2%9E%E5%AD%90%E5%BA%8F%E5%88%97.html)<br />视频讲解：[回溯算法精讲，树层去重与树枝去重 | LeetCode：491.递增子序列_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1EG4y1h78v)
:::
本题和`90.子集II`非常像，但又很不一样，很容易掉坑里。 <br />这个 递增子序列 比较像是取有序的子集。而且本题也要求**不能有相同的**递增子序列。<br />在`90.子集II` 中是通过**排序**，再加一个**标记数组**来达到去重的目的。而本题求自增子序列，是不能对原数组进行排序的，排完序的数组都是自增子序列了。所以不能使用之前的去重逻辑！<br />本题给出的示例，还是一个有序数组 [4, 6, 7, 7]，这更容易误导大家按照排序的思路去做了。为了有鲜明的对比，我用**[4, 7, 6, 7]**这个数组来举例，抽象为树形结构如图：<br />![20201124200229824.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686487173001-a69d2eb3-9517-4435-9019-a74abee5dd0b.png#averageHue=%23f4f3f3&clientId=u43ac0098-d5e7-4&from=paste&height=805&id=u0c5d1c8b&originHeight=942&originWidth=1960&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=298153&status=done&style=none&taskId=u1b0007e4-ebe2-400c-a331-d68ce9b277e&title=&width=1675.213736660149)
<a name="IaqkP"></a>
## 回溯三部曲

1. **递归函数参数**

本题求子序列，很明显一个元素不能重复使用，所以需要`startIndex`，调整下一层递归的起始位置。代码如下：
```java
List<List<Integer>> result = new ArrayList<>();
List<Integer> path = new ArrayList<>();

private void backTracking(int[] nums, int startIndex){
```

2. **终止条件**

本题其实类似求子集问题，也是要遍历树形结构找每一个节点，所以和回溯算法：`78.子集` 一样，可以不加终止条件，`startIndex`每次都会加1，并不会无限递归。<br />但本题收集结果有所不同，题目**要求递增子序列大小至少为2**，所以代码如下：
```java
if(path.size() >= 2){
    result.add(new ArrayList<>(path));
    // 注意这里不要加return，因为要取树上的所有节点
}
```

3. **单层搜索逻辑**

![20201124200229824-20230310131640070.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686488689939-22fbb835-2e58-4a51-a0d6-04f5b355450d.png#averageHue=%23f4f3f3&clientId=u43ac0098-d5e7-4&from=paste&height=805&id=u6253d942&originHeight=942&originWidth=1960&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=298153&status=done&style=none&taskId=ucfac4c8a-738f-4ea0-8209-b6519352346&title=&width=1675.213736660149)<br />在图中可以看出，同一父节点下的同层上使用过的元素就不能再使用了, 那么单层搜索代码如下：
```java
HashSet<Integer> hashSet = new HashSet<>(); // 使用set来对本层元素进行去重
for (int i=startIndex; i<nums.length; i++){
    // path不为空 同时目前path最后一个元素大于目前的nums[i],  或者  hashSet包含nums[i]  就跳过本次循环
    // 因为要目前的nums[i]大 然后加入path才是递增的, 如果hashSet包含nums[i]就说明同一层出现了重复元素
    if (!path.isEmpty() && path.get(path.size()-1)>nums[i] || hashSet.contains(nums[i])){
        continue;
    }
    hashSet.add(nums[i]);  // 记录这个元素在本层用过了，本层后面不能再用了
    path.add(nums[i]);
    backTracking(nums, i+1);
    path.remove(path.size()-1);
}
```
对于已经习惯写回溯的同学，看到递归函数上面的`hashSet.add(nums[i]);`, 下面却没有对应的 弹出之类的操作, 应该很不习惯吧, 哈哈<br />这也是需要注意的点, `HashSet<Integer> hashSet = new HashSet<>();`是记录本层元素是否重复使用, 新的一层`hashSet`都会重新定义(清空), 所以要知道`hashSet`只负责本层！
```java
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backTracking(nums, 0);
        return result;
    }
    private void backTracking(int[] nums, int startIndex){
        if (path.size() >= 2){   // 题目要求递增子序列大小至少为2
            result.add(new ArrayList<>(path));
            // 注意这里不要加return，因为要取树上的所有节点
        }

        HashSet<Integer> hashSet = new HashSet<>(); // 使用set来对本层元素进行去重
        for (int i=startIndex; i<nums.length; i++){
            // path不为空 同时目前path最后一个元素大于目前的nums[i],  或者  hashSet包含nums[i]  就跳过本次循环
            // 因为要目前的nums[i]大 然后加入path才是递增的, 如果hashSet包含nums[i]就说明同一层出现了重复元素
            if (!path.isEmpty() && path.get(path.size()-1)>nums[i] || hashSet.contains(nums[i])){
                continue;
            }
            hashSet.add(nums[i]);  // 记录这个元素在本层用过了，本层后面不能再用了
            path.add(nums[i]);
            backTracking(nums, i+1);
            path.remove(path.size()-1);
        }
    }
}
```
<a name="o0Z2W"></a>
## 优化
![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686490575850-1196c9f6-6ae8-416c-9937-1bab2d69b56b.png#averageHue=%2330302f&clientId=u43ac0098-d5e7-4&from=paste&height=93&id=u7323217c&originHeight=109&originWidth=375&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=2690&status=done&style=none&taskId=u347e69f6-0296-493c-9509-accee2952f4&title=&width=320.51283226916115)<br />上面使用了`HashSet`来记录本层元素是否重复使用, 其实使用数组来做哈希效率会高很多<br />注意题目中说了，数值范围[-100,100]，所以完全可以用数组来做哈希。<br />程序运行的时候对`HashSet`频繁的add，`HashSet`需要做哈希映射（也就是把key通过hash function映射为唯一的哈希值）相对费时间，而且每次重新定义set，add的时候其底层的符号表也要做相应的扩充，也是费事的。那么优化后的代码如下：
```java
// 使用数组来做哈希
class Solution {
    private List<Integer> path = new ArrayList<>();
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums,0);
        return res;
    }

    private void backtracking (int[] nums, int start) {
        if (path.size() > 1) {
            res.add(new ArrayList<>(path));
        }

        int[] used = new int[201];  // 定义数组 
        for (int i = start; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1) ||(used[nums[i] + 100] == 1)) {
                continue;
            }
            used[nums[i] + 100] = 1;  // 因为说nums[i]的范围为[-100, 100], 所以如果nums最小为-100时, 此时+100下标是0, 是合法的
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
```
```java
//法二：使用map
class Solution {
    //结果集合
    List<List<Integer>> res = new ArrayList<>();
    //路径集合
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        getSubsequences(nums,0);
        return res;
    }
    private void getSubsequences( int[] nums, int start ) {
        if(path.size()>1 ){
            res.add( new ArrayList<>(path) );
            // 注意这里不要加return，要取树上的节点
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=start ;i < nums.length ;i++){
            if(!path.isEmpty() && nums[i]< path.getLast()){
                continue;
            }
            // 使用过了当前数字
            if ( map.getOrDefault( nums[i],0 ) >=1 ){
                continue;
            }
            map.put(nums[i],map.getOrDefault( nums[i],0 )+1);
            path.add( nums[i] );
            getSubsequences( nums,i+1 );
            path.removeLast();
        }
    }
}
```
-
<a name="urcLL"></a>
# 46.全排列
:::info
本题重点感受一下，排列问题 与 组合问题，组合总和，子集问题的区别。 为什么排列问题不用 startIndex <br />[https://programmercarl.com/0046.%E5%85%A8%E6%8E%92%E5%88%97.html](https://programmercarl.com/0046.%E5%85%A8%E6%8E%92%E5%88%97.html)<br />视频讲解：[https://www.bilibili.com/video/BV19v4y1S79W](https://www.bilibili.com/video/BV19v4y1S79W)
:::
本题重点感受一下，排列问题 与 组合问题，组合总和，子集问题的区别。 为什么排列问题不用 startIndex <br />以[1,2,3]为例，抽象成树形结构如下：<br />![20211027181706.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686492303610-a213147e-6107-4655-bb01-0ff66508976f.png#averageHue=%23f3f2f2&clientId=u43ac0098-d5e7-4&from=paste&height=817&id=ud0256b2a&originHeight=956&originWidth=1884&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=239695&status=done&style=none&taskId=ua1b80718-1c68-49ea-9bd1-65e0ac73684&title=&width=1610.2564693202655)
<a name="H3Nd8"></a>
## 回溯三部曲

1. **递归函数参数**

首先排列是有序的，也就是说 [1,2] 和 [2,1] 是两个集合, 这和之前分析的子集以及组合所不同的地方. 可以看出元素1在[1,2]中已经使用过了, 但是在[2,1]中还要在使用一次1，所以处理排列问题就不用使用startIndex了。<br />但排列问题需要一个`used`数组，标记已经选择的元素，如图橘黄色部分所示:<br />![20211027181706 (1).png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686492325296-d3d62e54-105d-4f21-8c9c-10275819d807.png#averageHue=%23f3f2f2&clientId=u43ac0098-d5e7-4&from=paste&height=817&id=uce7c7832&originHeight=956&originWidth=1884&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=239695&status=done&style=none&taskId=uf17b3b43-6016-4712-9c89-d97de0e0603&title=&width=1610.2564693202655)
```java
List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
boolean[] used;

void permuteHelper(int[] nums){
```

2. **递归终止条件**

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686492600609-3463f902-00af-4440-a076-6e5feadd47e8.png#averageHue=%23f3f2f2&clientId=u43ac0098-d5e7-4&from=paste&height=794&id=ub72af1f1&originHeight=929&originWidth=1755&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=330687&status=done&style=none&taskId=ud308c414-0220-48d9-bdda-ae61ad88eb8&title=&width=1500.0000550196742)<br />可以看出叶子节点，就是收割结果的地方。<br />那么什么时候，算是到达叶子节点呢？当收集元素的数组`path`的大小达到和`nums`数组一样大的时候，说明找到了一个全排列，也表示到达了叶子节点。<br />代码如下：
```java
if (path.size() == nums.length){  // 此时说明找到了一组
    result.add(new ArrayList<>(path));
    return;
}
```

3. **单层搜索的逻辑**

这里和`77.组合问题` 、`131.切割问题`和`78.子集问题`最大的不同就是for循环里不用startIndex了。<br />因为排列问题，每次都要从头开始搜索，例如元素1在[1,2]中已经使用过了，但是在[2,1]中还要再使用一次1。而`used`数组，其实就是记录此时`path`里都有哪些元素使用了，一个排列里一个元素只能使用一次。代码如下：
```java
for (int i = 0; i < nums.length; i++){
    if (used[i]){
        continue;
    }
    used[i] = true;
    path.add(nums[i]);
    permuteHelper(nums);
    path.removeLast();
    used[i] = false;
}
```

整体代码如下
```java
class Solution {

    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0){
            return result;
        }
        used = new boolean[nums.length];
        permuteHelper(nums);
        return result;
    }

    private void permuteHelper(int[] nums){
        if (path.size() == nums.length){  // 此时说明找到了一组
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (used[i]){
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            permuteHelper(nums);
            path.removeLast();
            used[i] = false;
        }
    }
}
```
--
```java
// 解法2：通过判断path中是否存在数字，排除已经选择的数字
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return result;
        backtrack(nums, path);
        return result;
    }
    public void backtrack(int[] nums, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
        }
        for (int i =0; i < nums.length; i++) {
            // 如果path中已有，则跳过
            if (path.contains(nums[i])) {
                continue;
            } 
            path.add(nums[i]);
            backtrack(nums, path);
            path.removeLast();
        }
    }
}
```
-
<a name="uUiar"></a>
# 47.全排列 II
:::info
本题 就是我们讲过的 40.组合总和II 去重逻辑 和 46.全排列 的结合，可以先自己做一下，然后重点看一下 文章中 我讲的拓展内容。 used[i - 1] == true 也行，used[i - 1] == false 也行 <br />[https://programmercarl.com/0047.%E5%85%A8%E6%8E%92%E5%88%97II.html](https://programmercarl.com/0047.%E5%85%A8%E6%8E%92%E5%88%97II.html)<br />视频讲解：[https://www.bilibili.com/video/BV1R84y1i7Tm](https://www.bilibili.com/video/BV1R84y1i7Tm)
:::
![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686497657027-28985ed5-5461-42af-bbf1-5a818a2d27d1.png#averageHue=%23393735&clientId=u43ac0098-d5e7-4&from=paste&height=38&id=uaf5aa3a0&originHeight=45&originWidth=637&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=7806&status=done&style=none&taskId=uc3c635bd-56d5-41e7-9e37-58e1528d10c&title=&width=544.4444644145484)<br />这道题目和`46.全排列`的区别在于给定一个**可包含重复数字的序列**，要返回**所有不重复的全排列**。所以这里又涉及到**去重**了。
> 在`40.组合总和II`、`90.子集II`我们分别详细讲解了组合问题和子集问题如何去重。

那么排列问题其实也是一样的套路。还要强调的是**去重一定要对元素进行排序**，这样我们才方便通过相邻的节点来判断是否重复使用了。<br />我以示例中的 [1,1,2]为例 （为了方便举例，已经排序）抽象为一棵树，去重过程如图：<br />![20201124201331223.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686500417432-8f29a6e2-bb99-4ebc-9400-9a48d5a7d25d.png#averageHue=%23f5f4f4&clientId=u43ac0098-d5e7-4&from=paste&height=938&id=udca5e5eb&originHeight=1098&originWidth=1894&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=356471&status=done&style=none&taskId=u150ebb28-9bcc-46c5-9ca9-af5820b51b7&title=&width=1618.8034781807764)<br />图中我们对同一树层，前一位（也就是`nums[i-1]`）如果使用过，那么就进行去重。<br />一般来说：组合问题 和 排列问题 是在树形结构的叶子节点上收集结果，而 子集问题就是取树上所有节点的结果。<br />在`46.全排列`中已经详细讲解了排列问题的写法，在`40.组合总和II`、`90.子集II`中详细讲解了去重的写法，所以这次就不做回溯三部曲分析了，直接给出代码，如下
<a name="MjIIZ"></a>
## 完整代码
```java
class Solution {
    //存放结果
    List<List<Integer>> result = new ArrayList<>();
    //暂存结果
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        Arrays.sort(nums);
        backTrack(nums, used);
        return result;
    }

    private void backTrack(int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }
            //如果同⼀树⽀nums[i]没使⽤过开始处理
            if (used[i] == false) {
                used[i] = true;//标记同⼀树⽀nums[i]使⽤过，防止同一树枝重复使用
                path.add(nums[i]);
                backTrack(nums, used);
                path.remove(path.size() - 1);//回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
                used[i] = false;//回溯
            }
        }
    }
}
```
<a name="QD4yZ"></a>
## 拓展
去重最为关键的代码为：
```java
if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
    continue;
}
```
如果改成 `used[i - 1] == true`， 也是正确的!，去重代码如下：
```java
if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == true) {
    continue;
}
```
这是为什么呢? 就是上面刚说的，如果要对**树层中前一位**去重，就用`used[i - 1] == false`，如果要**对树枝前一位**去重用`used[i - 1] == true`。<br />对于排列问题，树层上去重和树枝上去重，都是可以的，但是树层上去重效率更高！<br />这么说是不是有点抽象？来来来，我就用输入: [1,1,1] 来举一个例子。树层上去重(`used[i - 1] == false`)，的树形结构如下：<br />![20201124201406192.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686501193071-fd17d353-6ad1-4f6c-9e13-b5a0447fc3c6.png#averageHue=%23f5f4f4&clientId=u43ac0098-d5e7-4&from=paste&height=822&id=fuQb0&originHeight=962&originWidth=1534&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=237321&status=done&style=none&taskId=ub6d1bf91-0b92-4bda-948d-cd64010b3ba&title=&width=1311.1111592023817)<br />树枝上去重（`used[i - 1] == true`）的树型结构如下：<br />![20201124201431571.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686501190088-767f634d-8427-45eb-8105-c67206afdb7c.png#averageHue=%23f5f4f4&clientId=u43ac0098-d5e7-4&from=paste&height=988&id=HEWM7&originHeight=1156&originWidth=2274&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=430148&status=done&style=none&taskId=u198197d2-527c-4978-bc9a-9c8af7e4e63&title=&width=1943.5898148801932)<br />应该可以很清晰的看到，树层上对前一位去重非常彻底，效率很高，树枝上对前一位去重虽然最后可以得到答案，但是做了很多无用搜索
<a name="jTl43"></a>
## 总结
这道题其实还是用了我们之前讲过的去重思路，但有意思的是，去重的代码中，这么写：
```java
if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
    continue;
}
```
和这么写
```java
if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == true) {
    continue;
}
```
都是可以的，这也是很多同学做这道题目困惑的地方，知道`used[i - 1] == false`也行而`used[i - 1] == true`也行，但是就想不明白为啥。<br />所以我通过举 [1,1,1] 的例子，把这两个去重的逻辑分别抽象成树形结构，大家可以一目了然：为什么两种写法都可以以及哪一种效率更高！<br />这里可能大家又有疑惑，既然`used[i - 1] == false`也行而`used[i - 1] == true`也行，那为什么还要写这个条件呢？<br />直接这样写 不就完事了？
```java
if (i > 0 && nums[i] == nums[i - 1]) {
    continue;
}
```
其实并不行，一定要加上`used[i - 1] == false`或者`used[i - 1] == true`，因为`used[i - 1]`要一直是 true 或者一直是false 才可以，而不是 一会是true 一会又是false。 所以这个条件要写上。<br />是不是豁然开朗了！！
