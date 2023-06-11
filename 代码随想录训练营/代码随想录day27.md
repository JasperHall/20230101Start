时间: 2023.6.5周一
<a name="sPFyY"></a>
# 今日任务
第七章 回溯算法part03, 39. 组合总和, 40.组合总和II, 131.分割回文串
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 复习day27, 数组总和II
- [ ] 复习 131.分割回文串  
<a name="XBsr0"></a>
# 复习

- [ ] <br />

<a name="AssYZ"></a>
# 39. 组合总和 
:::info
本题是 集合里元素可以用无数次，那么和组合问题的差别 其实仅在于 startIndex上的控制<br />题目链接/文章讲解：[https://programmercarl.com/0039.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8C.html](https://programmercarl.com/0039.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8C.html)<br />视频讲解：[https://www.bilibili.com/video/BV1KT4y1M7HJ](https://www.bilibili.com/video/BV1KT4y1M7HJ)
:::
![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686447946105-d3487d38-cdf8-4676-80e9-b50798ff31f8.png#averageHue=%23303030&clientId=u43ac0098-d5e7-4&from=paste&height=143&id=u848aa678&originHeight=167&originWidth=509&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=6604&status=done&style=none&taskId=u8c1885fb-c1a1-4d45-9211-bb9a85b2195&title=&width=435.042751000008)<br />本题没有数量要求，可以无限重复，但是有总和的限制，所以间接的也是有个数的限制。本题搜索的过程抽象成树形结构如下：<br />![20201223170730367.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686447978078-15e4f262-3f3f-4c33-8ef1-2d21e6db2f08.png#averageHue=%23f6f5f5&clientId=u43ac0098-d5e7-4&from=paste&height=766&id=ubec3e030&originHeight=896&originWidth=1630&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=175066&status=done&style=none&taskId=uf9d73897-fddf-4700-9d0b-d20c74eb36a&title=&width=1393.1624442632872)<br />注意图中叶子节点的返回条件，因为本题没有组合数量要求，仅仅是总和的限制，所以**递归没有层数的限制**，只要选取的元素总和超过target，就返回！
<a name="IgjtY"></a>
## 回溯三部曲

1. **递归函数参数**

这里依然是定义两个全局变量，二维列表`result`存放结果集，列表`path`存放符合条件的结果。（这两个变量可以作为函数参数传入）<br />首先是题目中给出的参数，集合`candidates`, 和目标值`target`。<br />此外还定义了int型的`sum`变量来统计单一结果`path`里的总和，其实这个`sum`也可以不用，用`target`做相应的减法就可以了，最后如何`target==0`就说明找到符合的结果了，但为了代码逻辑清晰，我依然用了`sum`。<br />本题还需要`startIndex`来控制for循环的起始位置，对于组合问题，什么时候需要startIndex呢？  如果是一个集合来求组合的话，就需要`startIndex`，如果是多个集合取组合，各个集合之间相互不影响，那么就不用`startIndex`，<br />注意以上只是说求组合的情况，如果是排列问题，又是另一套分析的套路，后面我再讲解排列的时候就重点介绍。<br />代码如下：
```java
List<List<Integer>> res = new ArrayList<>();
LinkedList<Integer> path = new LinkedList<>();
void backtracking(int[] candidates, int target, int sum, int startIndex){
```

2. **递归终止条件**

在如下树形结构中：<br />![20201223170730367-20230310135337214.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686448368048-a6d95878-1e1d-45e5-a285-bc17cd2cfe5a.png#averageHue=%23f6f5f5&clientId=u43ac0098-d5e7-4&from=paste&height=766&id=uabb1b6bd&originHeight=896&originWidth=1630&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=175066&status=done&style=none&taskId=u6ce7ad41-7f97-4964-b0c1-5ddb9c391f9&title=&width=1393.1624442632872)<br />从叶子节点可以清晰看到，终止只有两种情况，`sum大于target` 和 `sum等于target`。`sum等于target`的时候，需要收集结果，代码如下：
```java
if (sum > target) {
    return;
}
if (sum == target) {
    result.add(new ArrayList<>(path));
    return;
}
```

3. **单层搜索的逻辑**

单层for循环依然是从`startIndex`开始，搜索`candidates`集合。<br />注意: 本题可以重复选取
```java
for (int i=startIndex; i<candidates.length; i++){
    sum += candidates[i];
    path.add(candidates[i]);
    backtracking(candidates, target, sum, i); // 不用i+1了，表示可以重复读取当前的数
    sum -= candidates[i];
    path.removeLast();
}
```

完整代码:
```java
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtracking(candidates, target, 0 ,0);
        return result;
    }
    private void backtracking(int[] candidates, int target, int sum, int startIndex){
        if (sum > target) return;

        if (sum == target){
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i=startIndex; i<candidates.length; i++){
            sum += candidates[i];
            path.add(candidates[i]);
            backtracking(candidates, target, sum, i); // 不用i+1了，表示可以重复读取当前的数
            sum -= candidates[i];
            path.removeLast();
        }
    }
}
```
<a name="IH0YM"></a>
## 剪枝
在下面这个树形结构中<br />![20201223170730367-20230310135342472.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686448994693-896c2d50-d71b-48d5-837f-5fd0a368c55f.png#averageHue=%23f6f5f5&clientId=u43ac0098-d5e7-4&from=paste&height=766&id=ub2db4648&originHeight=896&originWidth=1630&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=175066&status=done&style=none&taskId=u8c24e2d0-3f5d-439f-8949-a8046276f00&title=&width=1393.1624442632872)<br />以及上面的版本一的代码大家可以看到，对于`sum`已经大于` target`的情况，其实是依然进入了下一层递归，只是下一层递归结束判断的时候，会判断`sum > target`的话就返回。其实如果已经知道下一层的`sum`会大于`target`，就没有必要进入下一层递归了。<br />那么可以在for循环的搜索范围上做做文章了。**对总集合排序之后**，如果下一层的`sum`(就是本层的 sum + candidates[i]）已经大于`target`，就可以结束本轮for循环的遍历。<br />如图：<br />![20201223170809182.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686449119202-c9b674d6-aa14-43c4-9677-ad5e56d737c6.png#averageHue=%23f7f6f6&clientId=u43ac0098-d5e7-4&from=paste&height=744&id=ue6d819fb&originHeight=870&originWidth=1590&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=192281&status=done&style=none&taskId=ue9ebb673-0d8e-4653-a30d-b5adbd720dd&title=&width=1358.9744088212433)<br />for循环剪枝代码如下：
```java
for (int i = startIndex; i < candidates.size() && sum + candidates[i] <= target; i++)
```
剪枝后完整代码如下
```java
// 剪枝优化
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 先进行排序, 方便剪枝
        backtracking(res, new ArrayList<>(), candidates, target, 0, 0);
        return res;
    }

    public void backtracking(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int sum, int idx) {
        // 找到了数字和为 target 的组合
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            // 如果 sum + candidates[i] > target 就终止遍历
            if (sum + candidates[i] > target) break;
            path.add(candidates[i]);
            backtracking(res, path, candidates, target, sum + candidates[i], i);  // 因为重复选取, 所以这里i不用加一了
            path.remove(path.size() - 1); // 回溯，移除路径 path 最后一个元素
        }
    }
}
```
-
<a name="m8jYJ"></a>
# 40.组合总和II 
:::info
本题开始涉及到一个问题了：去重。<br />注意题目中给我们 集合是有重复元素的，那么求出来的 组合有可能重复，但题目要求不能有重复组合。 <br />题目链接/文章讲解：[https://programmercarl.com/0040.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CII.html](https://programmercarl.com/0040.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CII.html)<br />视频讲解：[https://www.bilibili.com/video/BV12V4y1V73A](https://www.bilibili.com/video/BV12V4y1V73A)
:::
本题的重点在于去重<br />注意:

1. 本题candidates 中的每个数字在每个组合中只能使用一次。
2. 本题数组candidates的元素是有重复的
3. 解集不能包含重复的组合。

本题的难点在于：**集合（数组candidates）有重复元素，但还不能有重复的组合**。<br />接下来可能想了：把所有组合求出来，再用 set或者map 去重，这么做很容易超时！所以要在搜索的过程中就去掉重复组合。<br />很多人在去重的问题上想不明白，其实很多题解也没有讲清楚，反正代码是能过的，感觉是那么回事，稀里糊涂的先把题目过了。这个去重为什么很难理解呢，所谓去重，其实就是使用过的元素不能重复选取。 这么一说好像很简单！<br />**都知道组合问题可以抽象为树形结构，那么“使用过”在这个树形结构上是有两个维度的，一个维度是同一树枝上使用过，一个维度是同一树层上使用过。没有理解这两个层面上的“使用过” 是造成大家没有彻底理解去重的根本原因**。<br />问题来了，我们是要同一树层上使用过，还是同一树枝上使用过呢？<br />回看一下题目，元素在同一个组合内是可以重复的，怎么重复都没事，但两个组合不能相同。所以**要去重的是同一树层上的“使用过”**，**同一树枝上的都是一个组合里的元素，不用去重**。<br />为了理解去重我们来举一个例子，candidates = [1, 1, 2], target = 3，（方便起见candidates已经排序了）强调一下，树层去重的话，需要对数组排序！<br />选择过程树形结构如图所示：<br />![20230310000918.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686451241395-e6b832b6-7a73-4c40-a1b2-faf7b68d4784.png#averageHue=%23f6f5f5&clientId=u43ac0098-d5e7-4&from=paste&height=872&id=ud3e545cc&originHeight=1020&originWidth=1734&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=219984&status=done&style=none&taskId=ub4542ac6-a892-4567-8144-a172da647e3&title=&width=1482.0513364126011)<br />图中的每个节点使用了一个used数组
<a name="q12JA"></a>
## 回溯三部曲

1. **递归函数的参数**

此题还需要加一个boolean型数组used，用来记录同一树枝上的元素是否使用过。这个**集合去重的重任就是used来完成的**。代码如下：
```java
LinkedList<Integer> path = new LinkedList<>();  // 符合条件的组合
List<List<Integer>> ans = new ArrayList<>(); // 存放组合集合
```

2. **递归终止条件**

终止条件为 `sum > target` 和 `sum == target`。
```java
if (sum > target) { // 这个条件其实可以省略
    return;
}
if (sum == target) {
    result.add(new ArrayList(path));
    return;
}
```
`sum > target`这个条件其实可以省略，因为在递归单层遍历的时候，会有剪枝的操作，下面会介绍到。

3. **单层搜索的逻辑**

前面我们提到：**要去重的是“同一树层上的使用过”**，如何判断同一树层上元素（相同的元素）是否使用过了呢。<br />如果`candidates[i] == candidates[i - 1]`并且`used[i - 1] == false`, 就说明: 前一个树枝, 使用了`candidates[i - 1]`, 也就是说同一树层使用过`candidates[i - 1]`。此时for循环里就应该做continue的操作。这块比较抽象，如图：<br />![20230310000954.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686451941037-e14cb70a-8c2f-4ffd-8f12-8c0ff3b5390c.png#averageHue=%23f6f6f5&clientId=u43ac0098-d5e7-4&from=paste&height=872&id=u2bc1dcac&originHeight=1020&originWidth=1768&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=266492&status=done&style=none&taskId=u46012e7b-fb88-4643-a8ed-de8fa811f3d&title=&width=1511.1111665383385)<br />我在图中将used的变化用橘黄色标注上，可以看出**在**`**candidates[i] == candidates[i - 1]**`**相同的情况下**：

- used[i - 1] == true，说明同一**树枝**candidates[i - 1]使用过
- used[i - 1] == false，说明同一**树层**candidates[i - 1]使用过

可能有人会想，为什么`used[i - 1] == false`就是同一树层呢, 因为同一树层, `used[i - 1] == false`才能表示, 当前取的`candidates[i]`是从`candidates[i - 1]`回溯而来的。而`used[i - 1] == true`，说明是进入下一层递归，去下一个数，所以是树枝上，如图所示：<br />![20221021163812.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686451965264-2b0aca71-22f1-477b-925c-199a242b8f63.png#averageHue=%23f7f6f5&clientId=u43ac0098-d5e7-4&from=paste&height=918&id=ua3410f08&originHeight=1074&originWidth=1816&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=308748&status=done&style=none&taskId=uf3b1fdff-9692-42bd-af63-0012698a12a&title=&width=1552.136809068791)<br />这块去重的逻辑很抽象，网上搜的题解基本没有能讲清楚的，如果大家之前思考过这个问题或者刷过这道题目，看到这里一定会感觉通透了很多！<br />那么单层搜索的逻辑代码如下：
```java
for (int i = startIndex; i < candidates.length; i++) {
  if (sum + candidates[i] > target) {
    break;
  }
  // 出现重复节点，同层的第一个节点已经被访问过，所以直接跳过
  if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1]==false) {
    continue;
  }
  used[i] = true;
  sum += candidates[i];
  path.add(candidates[i]);
  // 每个节点仅能选择一次，所以从下一位开始
  backTracking(candidates, target, i + 1);
  used[i] = false;
  sum -= candidates[i];
  path.removeLast();
}
```

完整代码如下
```java
class Solution {
  LinkedList<Integer> path = new LinkedList<>();  // 符合条件的组合
  List<List<Integer>> ans = new ArrayList<>(); // 存放组合集合
  boolean[] used;
  int sum = 0;

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    used = new boolean[candidates.length];
    // 加标志数组，用来辅助判断同层节点是否已经遍历
    Arrays.fill(used, false);
    // 为了将重复的数字都放到一起，所以先进行排序
    Arrays.sort(candidates);
    backTracking(candidates, target, 0);
    return ans;
  }

  private void backTracking(int[] candidates, int target, int startIndex) {
    if (sum == target) {
      ans.add(new ArrayList(path));
    }
      
    for (int i = startIndex; i < candidates.length; i++) {
      if (sum + candidates[i] > target) {
        break;
      }
      // used[i - 1] == true，说明同一树枝candidates[i - 1]使用过
   	  // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
   	  // 要对同一树层使用过的元素进行跳过
      // 出现重复节点，同层的第一个节点已经被访问过，所以直接跳过
      if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1]==false) {
        continue;
      }
      used[i] = true;
      sum += candidates[i];
      path.add(candidates[i]);
      // 每个节点仅能选择一次，所以从下一位开始
      backTracking(candidates, target, i + 1);
      used[i] = false;
      sum -= candidates[i];
      path.removeLast();
    }
  }
}
```
:::danger
注意: 

1. `if (sum + candidates[i] > target) {`为剪枝操作
2. Arrays.fill(), 数组填充方法
3. <br />
:::
<a name="jZSgU"></a>
## 补充:不使用标记数组
```java
class Solution {
  List<List<Integer>> res = new ArrayList<>();
  LinkedList<Integer> path = new LinkedList<>();
  int sum = 0;
  
  public List<List<Integer>> combinationSum2( int[] candidates, int target ) {
    //为了将重复的数字都放到一起，所以先进行排序
    Arrays.sort( candidates );
    backTracking( candidates, target, 0 );
    return res;
  }
  
  private void backTracking( int[] candidates, int target, int start ) {
    if ( sum == target ) {
      res.add( new ArrayList<>( path ) );
      return;
    }
    for ( int i = start; i < candidates.length && sum + candidates[i] <= target; i++ ) {
      //正确剔除重复解的办法
      //跳过同一树层使用过的元素
      if ( i > start && candidates[i] == candidates[i - 1] ) {
        continue;
      }

      sum += candidates[i];
      path.add( candidates[i] );
      // i+1 代表当前组内元素只选取一次
      backTracking( candidates, target, i + 1 );

      int temp = path.getLast();
      sum -= temp;
      path.removeLast();
    }
  }
}
```
-
<a name="i0FUj"></a>
# 131.分割回文串  
:::info
本题较难，大家先看视频来理解 分割问题，明天还会有一道分割问题，先打打基础。 <br />[https://programmercarl.com/0131.%E5%88%86%E5%89%B2%E5%9B%9E%E6%96%87%E4%B8%B2.html](https://programmercarl.com/0131.%E5%88%86%E5%89%B2%E5%9B%9E%E6%96%87%E4%B8%B2.html)<br />视频讲解：[https://www.bilibili.com/video/BV1c54y1e7k6](https://www.bilibili.com/video/BV1c54y1e7k6)
:::
本题这涉及到两个关键问题：

1. 切割问题，有不同的切割方式
2. 判断回文

相信这里不同的切割方式可以搞懵很多人了。这种题目，想用for循环暴力解法，可能都不那么容易写出来，所以要换一种暴力的方式，就是回溯。<br />很多人可能想不清楚 回溯究竟是如何切割字符串呢？我们来分析一下切割，其实切割问题类似组合问题。例如对于字符串abcdef：

- 组合问题：选取一个a之后，在bcdef中再去选取第二个，选取b之后在cdef中再选取第三个.....。
- 切割问题：切割一个a之后，在bcdef中再去切割第二段，切割b之后在cdef中再切割第三段.....。

感受出来了不？所以切割问题，也可以抽象为一棵树形结构，如图：<br />![131.分割回文串.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/32832913/1686458800607-457503e0-6442-43ad-87ac-1d29d3dd05a1.jpeg#averageHue=%23f6f5f5&clientId=u43ac0098-d5e7-4&from=paste&height=718&id=u33166798&originHeight=840&originWidth=1456&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=203371&status=done&style=none&taskId=uaf18d885-205f-4d12-8c0a-8253e9211c1&title=&width=1244.4444900903964)<br />递归用来纵向遍历，for循环用来横向遍历，切割线（就是图中的红线）切割到字符串的结尾位置，说明找到了一个切割方法。<br />此时可以发现，切割问题的回溯搜索的过程和组合问题的回溯搜索的过程是差不多的
<a name="ENrPK"></a>
## 回溯三部曲

1. **递归函数参数**

全局变量 队列dequePath 存放切割后回文的子串，二维列表result 存放结果集。 （这两个参数可以放到函数参数里）<br />本题递归函数参数还需要`startIndex`，因为切割过的地方，不能重复切割，和组合问题也是保持一致的。
```java
List<List<String>> result = new ArrayList<>();
Deque<String> dequePath = new LinkedList<>();

void backTracking(String s, int startIndex) {
```

2. **递归函数终止条件**

![131.分割回文串 (1).jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/32832913/1686459064894-803f42df-327c-4c34-8c84-b23e01561d4f.jpeg#averageHue=%23f6f5f5&clientId=u43ac0098-d5e7-4&from=paste&height=718&id=uf278773f&originHeight=840&originWidth=1456&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=203371&status=done&style=none&taskId=u475104aa-c2d3-4ea3-b5ec-59914f15c46&title=&width=1244.4444900903964)<br />从树形结构的图中可以看出：切割线切到了字符串最后面，说明找到了一种切割方法，此时就是本层递归的终止条件。<br />那么在代码里什么是切割线呢？  在处理组合问题的时候，**递归参数需要传入**`**startIndex**`**，表示下一轮递归遍历的起始位置，这个**`**startIndex**`**就是切割线**。所以终止条件代码如下：
```java
private void backTracking(String s, int startIndex) {
    //如果起始位置大于s的大小，说明找到了一组分割方案
    if (startIndex >= s.length()) {
        result.add(new ArrayList(dequePath));
        return;
    }
}
```

3. **单层搜索的逻辑**

来看看在递归循环中如何截取子串呢？<br />在`for (int i = startIndex; i < s.size(); i++)`循环中，我们 定义了起始位置`startIndex`，那么 `[startIndex, i]` 就是要截取的子串。首先判断这个子串是不是回文，**如果是回文**，就加入在`Deque<String> dequePath`中，`path`用来记录切割过的回文子串。代码如下：
```java
for (int i = startIndex; i < s.length(); i++) {
    //如果是回文子串，则记录
    if (isPalindrome(s, startIndex, i)) {
        String str = s.substring(startIndex, i + 1);
        dequePath.addLast(str);
    } else {  // 如果不是则直接跳过
        continue;
    }
    //起始位置后移，保证不重复
    backTracking(s, i + 1);  // 寻找i+1为起始位置的子串
    dequePath.removeLast();  // 回溯过程，弹出本次已经填在的子串
}
```
注意切割过的位置，不能重复切割，所以，`backtracking(s, i + 1);` 传入下一层的起始位置为i + 1。
<a name="sRetM"></a>
## 判断回文串
最后我们看一下回文子串要如何判断了，判断一个字符串是否是回文。<br />可以使用**双指针法**，一个指针从前向后，一个指针从后向前，如果前后指针所指向的元素是相等的，就是回文字符串了。
```java
//判断是否是回文串
private boolean isPalindrome(String s, int startIndex, int end) {
    for (int i = startIndex, j = end; i < j; i++, j--) {
        if (s.charAt(i) != s.charAt(j)) {
            return false;
        }
    }
    return true;
}
```
完整代码如下
```java
class Solution {
    List<List<String>> result = new ArrayList<>();
    Deque<String> dequePath = new LinkedList<>();

    public List<List<String>> partition(String s) {
        backTracking(s, 0);
        return result;
    }
    private void backTracking(String s, int startIndex){
        // 如果起始位置大于s的大小，说明找到了一组分割方案
        if (startIndex >= s.length()){
            result.add(new ArrayList<>(dequePath));
            return;
        }

        for (int i=startIndex; i<s.length(); i++){
            // 如果是回文字符串则记录
            if (isPalindrome(s, startIndex, i)){
                String str = s.substring(startIndex, i+1);
                dequePath.addLast(str);
            }else {  // 如果不是则直接跳过
                continue;
            }

            //起始位置后移，保证不重复
            backTracking(s, i + 1);  // 寻找i+1为起始位置的子串
            dequePath.removeLast();  // 回溯过程，弹出本次已经填在的子串
        }
    }

    // 判断是否是回文串的方法
    private boolean isPalindrome(String s, int startIndex, int end){
        for (int i=startIndex, j=end; i<j; i++,j--){
            if (s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return true;
    }
}
```
<a name="TG0F6"></a>
## 总结
这道题目在leetcode上是中等，但可以说是hard的题目了，但是代码其实就是按照模板的样子来的。那么难究竟难在什么地方呢？<br />列出如下几个难点：

- 切割问题可以抽象为组合问题
- 如何模拟那些切割线
- 切割问题中递归如何终止
- 在递归循环中如何截取子串
- 如何判断回文

平时在做难题的时候，总结出来难究竟难在哪里也是一种需要锻炼的能力。<br />一些同学可能遇到题目比较难，但是不知道题目难在哪里，反正就是很难。其实这样还是思维不够清晰，这种总结的能力需要多接触多锻炼。<br />本题我相信很多同学主要卡在了第一个难点上：就是不知道**如何切割**，甚至知道要用回溯法，也不知道如何用。也就是没有体会到按照求组合问题的套路就可以解决切割。如果意识到这一点，算是重大突破了。接下来就可以对着模板照葫芦画瓢。但接下来**如何模拟切割线**，如何终止，如何截取子串，其实都不好想，最后判断回文算是最简单的了。<br />关于模拟切割线，其实就是index是上一层已经确定了的分割线，i是这一层试图寻找的新分割线<br />除了这些难点，本题还有细节，例如：切割过的地方不能重复切割所以递归函数需要传入`i + 1`。<br />所以本题应该是一道hard题目了。可能刷过这道题目的录友都没感受到自己原来克服了这么多难点，就把这道题目AC了，这应该叫做无招胜有招，人码合一，哈哈哈
