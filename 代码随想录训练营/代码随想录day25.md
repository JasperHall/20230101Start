时间: 2023.6.3
<a name="sPFyY"></a>
# 今日任务
第七章 回溯算法part02, 216.组合总和III, 17.电话号码的字母组合
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [x] 77.组合继续理解, 学习剪枝操作


-
<a name="eoqYc"></a>
# 216.组合总和III 
:::info
如果把 组合问题理解了，本题就容易一些了。 <br />题目链接/文章讲解：[https://programmercarl.com/0216.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CIII.html](https://programmercarl.com/0216.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CIII.html)<br />视频讲解：[https://www.bilibili.com/video/BV1wg411873x](https://www.bilibili.com/video/BV1wg411873x)
:::
本题就是在[1,2,3,4,5,6,7,8,9]这个集合中找到 和为n 的 k个数 的组合。而整个集合已经是固定的了[1,...,9]。<br />本题 k 相当于树的**深度**，9(因为整个集合就是9个数) 就是树的**宽度**。例如 k = 2，n = 4的话，就是在集合[1,2,3,4,5,6,7,8,9]中求 k（个数） = 2, n（和） = 4的组合。<br />选取过程如图：<br />![20201123195717975.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686403819018-866c2833-00f3-403b-a8c2-2541d724177e.png#averageHue=%23f7f6f6&clientId=u01470f95-e50c-4&from=paste&height=894&id=u066d2492&originHeight=1046&originWidth=2026&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=223212&status=done&style=none&taskId=u765dc6fb-8771-4c9e-948d-b76b97aacf2&title=&width=1731.6239951395212)<br />图中，可以看出，只有最后取到集合（1，3）和为4 符合条件

<a name="fZwHD"></a>
## 开始回溯三部曲

1. **确定递归函数参数**

依然需要一维列表`path`来存放符合条件的结果，二维列表`result`来存放结果集。依然定义`path` 和 `result`为全局变量。<br />至于为什么取名为path？从上面树形结构中，可以看出，结果其实就是一条根节点到叶子节点的路径。
```java
List<List<Integer>> result = new ArrayList<>();  // 存放结果集
LinkedList<Integer> path = new LinkedList<>(); // 符合条件的结果
```
接下来还需要如下参数：

- targetSum（int）目标和，也就是题目中的n。
- k（int）就是题目中要求k个数的集合。
- sum（int）为已经收集的元素的总和，也就是path里元素的总和。
- startIndex（int）为下一层for循环搜索的起始位置。

所以代码如下：
```java
List<List<Integer>> result = new ArrayList<>();  // 存放结果集
LinkedList<Integer> path = new LinkedList<>(); // 符合条件的结果
void backTracking(int targetSum, int k, int startIndex, int sum) {
```
其实这里`sum`这个参数也可以省略，每次`targetSum`减去选取的元素数值，然后判断如果`targetSum`为0了，说明收集到符合条件的结果了，我这里为了直观便于理解，还是加一个`sum`参数。<br />还要强调一下，回溯法中递归函数参数很难一次性确定下来，一般先写逻辑，需要啥参数了，填什么参数。

2. **确定终止条件**

什么时候终止呢？  在上面已经说了，k其实就已经限制树的深度，因为就取k个元素，树再往下深了没有意义。所以如果`path.size()`和 k 相等了，就终止。如果此时path里收集到的元素和（sum） 和targetSum（就是题目描述的n）相同了，就用result收集当前的结果。所以 终止代码如下：
```java
if (path.size() == k) {
    if (sum == targetSum) result.add(new ArrayList<>(path));
    return;  // 如果path.size() == k 但sum != targetSum 直接返回
}
```

3. **单层搜索过程**

本题和`77. 组合`区别之一就是集合固定的就是9个数[1,...,9]，所以for循环固定 `i<=9`<br />![20201123195717975-20230310113546003.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686404386563-726f40a4-1c77-4a7c-8116-f72c74f1c6ff.png#averageHue=%23f7f6f6&clientId=u01470f95-e50c-4&from=paste&height=894&id=u989b79eb&originHeight=1046&originWidth=2026&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=223212&status=done&style=none&taskId=u254c9b54-801b-4cf3-a2f4-c7669ca042e&title=&width=1731.6239951395212)<br />处理过程就是 path 收集每次选取的元素，相当于树型结构里的边，sum来统计path里元素的总和。代码如下：
```java
for (int i = startIndex; i <= 9; i++) {
    path.add(i);
    sum += i;
    backTracking(targetSum, k, i + 1, sum);  // 注意i+1调整startIndex
    //回溯
    path.removeLast();
    //回溯
    sum -= i;
}
```
别忘了处理过程 和 回溯过程是一一对应的，处理有加，回溯就要有减！
<a name="W8IlE"></a>
## 剪枝思路
![2020112319580476.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686404634621-2f66a7a7-9e3a-4000-9352-3a7eecb35233.png#averageHue=%23f6f6f6&clientId=u01470f95-e50c-4&from=paste&height=863&id=u4071ba6a&originHeight=1010&originWidth=2018&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=242374&status=done&style=none&taskId=uece04b44-2b8f-4d01-abd6-745c369044d&title=&width=1724.7863880511125)<br />已选元素总和如果已经大于n（图中数值为4）了，那么往后遍历就没有意义了，直接剪掉。那么剪枝的地方可以放在递归函数开始的地方，剪枝代码如下：
```java
if (sum > targetSum) { // 剪枝操作
    return;
}
```
当然这个剪枝也可以放在 调用递归之前，即放在这里，只不过要记得 要回溯操作给做了。
```java
for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) { // 剪枝
    sum += i; // 处理
    path.add(i); // 处理
    if (sum > targetSum) { // 剪枝操作
        sum -= i; // 剪枝之前先把回溯做了
        path.removeLast(); // 剪枝之前先把回溯做了
        return;
    }
    backTracking(targetSum, k, i + 1, sum);  // 注意i+1调整startIndex
    sum -= i; // 回溯
    path.removeLast(); // 回溯
}
```
for循环的范围也可以剪枝，i <= 9 - (k - path.size()) + 1就可以了。


<a name="i5zi0"></a>
## 完整代码
```java
// 剪枝版
class Solution {
	List<List<Integer>> result = new ArrayList<>();  // 存放结果集
	LinkedList<Integer> path = new LinkedList<>(); // 符合条件的结果

	public List<List<Integer>> combinationSum3(int k, int n) {
		backTracking(n, k, 1, 0);
		return result;
	}

	private void backTracking(int targetSum, int k, int startIndex, int sum) {
		// 减枝
		if (sum > targetSum) {
			return;
		}

		if (path.size() == k) {
			if (sum == targetSum) result.add(new ArrayList<>(path));
			return;  // 如果path.size() == k 但sum != targetSum 直接返回
		}

		// 减枝 9 - (k - path.size()) + 1
		for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
			path.add(i);
			sum += i;
			backTracking(targetSum, k, i + 1, sum);  // 注意i+1调整startIndex
			//回溯
			path.removeLast();
			//回溯
			sum -= i;
		}
	}
}

// 上面剪枝 i <= 9 - (k - path.size()) + 1; 如果还是不清楚
// 也可以改为 if (path.size() > k) return; 执行效率上是一样的
class Solution {
    LinkedList<Integer> path = new LinkedList<>();
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backTracking(n, k, 1, 0);
        return result;
    }

    private void backTracking(int targetSum, int k, int startIndex, int sum){
        if (sum > targetSum) return;

        if (path.size() > k) return;

        if (sum==targetSum && path.size()==k){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i=startIndex; i<=9; i++){
            path.add(i);
            sum += i;
            backTracking(targetSum, k , i+1, sum);
            sum -= i;
            path.removeLast();
        }
    }
}
```
```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        res.clear();
        list.clear();
        backtracking(k, n, 9);
        return res;
    }

    private void backtracking(int k, int n, int maxNum) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        // 因为不能重复，并且单个数字最大值是maxNum，所以sum最大值为
        // （maxNum + (maxNum - 1) + ... + (maxNum - k + 1)） == k * maxNum - k*(k - 1) / 2
        if (maxNum == 0
                || n > k * maxNum - k * (k - 1) / 2
                || n < (1 + k) * k / 2) {
            return;
        }
        list.add(maxNum);
        backtracking(k - 1, n - maxNum, maxNum - 1);
        list.remove(list.size() - 1);
        backtracking(k, n, maxNum - 1);
    }

}
```
-
<a name="XVupt"></a>
# 17.电话号码的字母组合 
:::info
本题大家刚开始做会有点难度，先自己思考20min，没思路就直接看题解。 <br />题目链接/文章讲解：[https://programmercarl.com/0017.%E7%94%B5%E8%AF%9D%E5%8F%B7%E7%A0%81%E7%9A%84%E5%AD%97%E6%AF%8D%E7%BB%84%E5%90%88.html](https://programmercarl.com/0017.%E7%94%B5%E8%AF%9D%E5%8F%B7%E7%A0%81%E7%9A%84%E5%AD%97%E6%AF%8D%E7%BB%84%E5%90%88.html)<br />视频讲解：[https://www.bilibili.com/video/BV1yV4y1V7Ug](https://www.bilibili.com/video/BV1yV4y1V7Ug)
:::
从示例上来说，输入"23"，最直接的想法就是两层for循环遍历了吧，正好把组合的情况都输出了。如果输入"233"呢，那么就三层for循环，如果"2333"呢，就四层for循环.......<br />大家应该感觉出和`77.组合`遇到的一样的问题，就是这for循环的层数如何写出来，此时又是回溯法登场的时候了。<br />理解本题后，要解决如下三个问题：

1. 数字和字母如何映射
2. 两个字母就两个for循环，三个字符我就三个for循环，以此类推，然后发现代码根本写不出来
3. 输入1 * #按键等等异常情况
<a name="ulLNo"></a>
## 数字和字母如何映射
可以使用数组来做映射，我这里定义一个数组，代码如下：
```java
//初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
```
<a name="cVysj"></a>
## 回溯法来解决n个for循环的问题
例如：输入："23"，抽象为树形结构，如图所示：<br />![20201123200304469.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1686417939101-b2e71fe5-2fca-4e96-a48e-bca66f9ca838.png#averageHue=%23f4f4f4&clientId=u01470f95-e50c-4&from=paste&height=622&id=u368ecc24&originHeight=728&originWidth=1486&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=128255&status=done&style=none&taskId=uc2747613-b79f-41dc-a5f6-aa81f8e017b&title=&width=1270.085516671929)<br />图中可以看出遍历的深度，就是输入"23"的长度，而叶子节点就是我们要收集的结果，输出 ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]。

**回溯三部曲：**

1. **确定回溯函数参数**

首先需要一个字符串s来收集叶子节点的结果，然后用一个字符串数组result保存起来，这两个变量我依然定义为全局。<br />再来看参数，参数指定是有题目中给的`string digits`，然后还要有一个参数就是int型的`index`。<br />注意这个index可不是 `77.组合`和`216.组合总和III`中的`startIndex`了。这个`index`是记录遍历第几个数字了，就是用来遍历`digits`的（题目中给出数字字符串），同时index也表示树的深度。
```java
//设置全局列表存储最后的结果
List<String> list = new ArrayList<>();

void backTracking(String digits, String[] numString, int index) {
```

2. **确定终止条件**

例如输入用例"23"，两个数字，那么根节点往下递归两层就可以了，叶子节点就是要收集的结果集。<br />那么终止条件就是如果`index` 等于 输入的数字个数（`digits.size`）了（本来`index`就是用来遍历`digits`的）。然后收集结果，结束本层递归。代码如下：
```java
//遍历全部一次记录一次得到的字符串
if (num == digits.length()) {
    list.add(temp.toString());
    return;
}
```

3. **确定单层遍历逻辑**

首先要取index指向的数字，并找到对应的字符集（手机键盘的字符集）。然后for循环来处理这个字符集，代码如下：
```java
//str 表示当前num对应的字符串
String str = numString[digits.charAt(index) - '0'];
for (int i = 0; i < str.length(); i++) {
    temp.append(str.charAt(i));
    //c
    backTracking(digits, numString, index + 1);
    //剔除末尾的继续尝试
    temp.deleteCharAt(temp.length() - 1);
}
```
注意这里for循环，可不像是在回溯算法：`77题和216题` 中从`startIndex`开始遍历的。<br />因为本题每一个数字代表的是不同集合，也就是求不同集合之间的组合，而`77. 组合`和`216.组合总和III`都是求同一个集合中的组合！
:::danger
**注意：输入1 * #按键等等异常情况**代码中最好考虑这些异常情况，但题目的测试数据中应该没有异常情况的数据，所以我就没有加了。但是要知道会有这些异常，如果是现场面试中，一定要考虑到
:::
```java
class Solution {
    //设置全局列表存储最后的结果
    List<String> list = new ArrayList<>();

    public List<String> letterCombinations(String digits) {

        if (digits == null || digits.length() == 0) {
            return list;
        }
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        //迭代处理
        backTracking(digits, numString, 0);
        return list;

    }

    //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
    StringBuilder temp = new StringBuilder();

    //比如digits如果为"23",num 为0，则str表示2对应的 abc
    public void backTracking(String digits, String[] numString, int index) {
        //遍历全部一次记录一次得到的字符串
        if (index == digits.length()) {
            list.add(temp.toString()); // 注意这里要转换类型
            return;
        }
        //str 表示当前num对应的字符串
        String nowStr = numString[digits.charAt(index) - '0'];  // 取数字对应的字符集  注意这一步的字符转int操作
        for (int i = 0; i < nowStr.length(); i++) {
            temp.append(nowStr.charAt(i));
            // 回溯
            backTracking(digits, numString, index + 1);  // 递归，注意index+1，一下层要处理下一个数字了
            // 剔除末尾的继续尝试
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
```
-
