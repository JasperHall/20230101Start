<a name="fn7NT"></a>
# [209.长度最小的子数组](https://leetcode.cn/leetbook/read/array-and-string/c0w4r/)
个人认为此题目滑动窗口法是最优解
<a name="rMPVy"></a>
## 方法一：前缀和&双指针
基本思路:<br />前缀和记录前N个元素之和已备后续计算子数组和，再过滤掉特殊情况，快慢指针遍历前缀和即可，其中快慢指针移动规则如下:<br />1.慢指针和快指针**所夹区间**（前开后闭）等于目标值，则将区间大小于结果集对比并更替结果集；<br />2.慢指针和快指针**所夹区间**（前开后闭）小于目标值，则将区间大小于结果集对比并更替结果集并前移快指针；<br />3.慢指针和快指针**所夹区间**（前开后闭）大于目标值，则前移慢指针
```java
public int minSubArrayLen4(int target, int[] nums) {
    int len = nums.length;
    //前缀和
    int[] prefixSum = new int[len+1];
    for (int idx=0; idx<len; idx++){
        prefixSum[idx+1] = prefixSum[idx] + nums[idx];
    }
    //目标大于全数组数据之和
    if (prefixSum[len] < target) return 0;
    //目标恰好等于全数组数据之和
    if (prefixSum[len] == target) return len;

    int slow=0, fast=0, res=Integer.MAX_VALUE;
    while (fast <= len){
        if (prefixSum[fast] - prefixSum[slow] <target){
            fast++;
        }else if (prefixSum[fast] - prefixSum[slow] > target){
            res = Math.min(res, fast-slow);
            slow++;
        }else {
            res = Math.min(res, fast-slow);
            fast++;
            slow++;
        }
    }
    return res == Integer.MAX_VALUE ? 0 :res;
}
```
<a name="dsSWe"></a>
## 方法二：直接遍历（暴力）
（此方法计算超时）

- 时间复杂度：O(n^2)
- 空间复杂度：O(1)
```java
public int minSubArrayLen1(int target, int[] nums) {
    int length = nums.length;
    int min = Integer.MAX_VALUE;
    for (int i=0; i<length; i++){
        int sum=0;
        for (int j=i; j<length; j++){
            sum += nums[j];
            if (sum >= target){
                min = Math.min(min, j-i+1);
                break;
            }
        }
    }
    return min == Integer.MAX_VALUE ? 0 :min; //如果min == Integer.MAX_VALUE返回0，否则返回min
}
```
<a name="esc99"></a>
## 方法三：前缀和&二分查找
注意 Arrays.binarySearch()的用法，这个解法难理解<br />1.首先求出前缀和数组<br />2.
```java
public int minSubArrayLen2(int target, int[] nums) {
    int length = nums.length;

    if (length == 0){
        return 0;
    }
    //前缀和数组
    int[] sums = new int[length+1];
    sums[0] = 0;
    for (int i=1; i<=length; i++){
        sums[i] = sums[i-1]+nums[i-1];//求得前缀和数组
    }

    int min = Integer.MAX_VALUE;//取得Integer的最大值
    for (int i=1; i<=length; i++){
        int tmp = target + sums[i-1];
        int pos = Arrays.binarySearch(sums, tmp);
        if (pos < 0){
            pos =-pos - 1;
        }
        if (pos <= length){
            min = Math.min(min, pos - (i-1));
        }
    }
    return min == Integer.MAX_VALUE ? 0 : min;
}
```
<a name="xCLpx"></a>
## 方法四：滑动窗口
我觉得这是最优解<br />所谓滑动窗口，就是不断的调节子序列的起始位置和终止位置，从而得出我们要想的结果。<br />在暴力解法中，是一个for循环滑动窗口的起始位置，一个for循环为滑动窗口的终止位置，用两个for循环 完成了一个不断搜索区间的过程。<br />那么滑动窗口如何用一个for循环来完成这个操作呢。<br />首先要思考 **如果用一个for循环，那么应该表示 滑动窗口的起始位置，还是终止位置。**<br />如果只用一个for循环来表示 滑动窗口的起始位置，那么如何遍历剩下的终止位置？<br />此时难免再次陷入 暴力解法的怪圈。<br />所以 只用一个for循环，那么这个循环的索引，一定是表示 滑动窗口的终止位置。<br />那么问题来了， 滑动窗口的起始位置如何移动呢？<br />这里还是以题目中的示例来举例，s=7， 数组是 2，3，1，2，4，3，来看一下查找的过程：<br />![](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1674652197144-6bee5469-cbfc-4f2f-97c6-ca9e4134b8b1.gif#averageHue=%23fbfbfb&clientId=ucd1c2fc8-377e-4&from=paste&id=u597c35b8&originHeight=432&originWidth=558&originalType=url&ratio=1&rotation=0&showTitle=false&status=done&style=none&taskId=uf48ac7ff-ea7d-4fbf-9c89-d62d0ac6b9e&title=)<br />最后找到 4，3 是最短距离。<br />其实从动画中可以发现滑动窗口也可以理解为双指针法的一种！只不过这种解法更像是一个窗口的移动，所以叫做滑动窗口更适合一些。<br />在本题中实现滑动窗口，主要确定如下三点：

- 窗口内是什么？
- 如何移动窗口的起始位置？
- 如何移动窗口的结束位置？

窗口就是 满足其和 ≥ s 的长度最小的 连续 子数组。<br />窗口的起始位置如何移动：如果当前窗口的值大于s了，窗口就要向前移动了（也就是该缩小了）。<br />窗口的结束位置如何移动：窗口的结束位置就是遍历数组的指针，也就是for循环里的索引。<br />解题的关键在于 窗口的起始位置如何移动，如图所示：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1674652234676-e2bf3ac8-e177-40d3-b211-323c05fe927a.png#averageHue=%23f9f4f3&clientId=ucd1c2fc8-377e-4&from=paste&id=u0637889d&originHeight=540&originWidth=914&originalType=url&ratio=1&rotation=0&showTitle=false&size=99411&status=done&style=none&taskId=u8b66bb1a-13b7-48fe-a46d-387daf6a47d&title=)<br />可以发现滑动窗口的精妙之处在于根据当前子序列和大小的情况，不断调节子序列的起始位置。从而将O(n^2)暴力解法降为O(n)。

- 时间复杂度：O(n)
- 空间复杂度：O(1)

一些录友会疑惑为什么时间复杂度是O(n)。<br />不要以为for里放一个while就以为是O(n^2)啊， 主要是看每一个元素**被操作的次数**，每个元素在滑动窗后**进来操作一次，出去操作一次，每个元素都是被操作两次，所以时间复杂度是 2 × n 也就是O(n)。**<br />**代码实现：**

1. 首先定义一个左指针，一个右指针，一个sum存储计算的和
2. 右指针小于数组长度则进入循环，然后进行加法计算sum
3. 当sum大于等于目标值target时，计算当前左右指针的距离，标出较小的值，然后sum减去左边一个数，左指针右移一位，再比较是否大于等于target
4. 最后再右指针右移一位
```java
public int minSubArrayLen3(int target, int[] nums) {
    // 滑动窗口
    int left = 0, right = 0, sum = 0;
    int min = Integer.MAX_VALUE;
    
    while(right < nums.length){
        sum += nums[right];
        while(sum >= target){
            min = Math.min(min, right - left + 1);//当sum大于等于目标值target时，计算当前左右指针的距离，标出较小的值
            sum -= nums[left];//然后sum减去左边一个数
            left++;//左指针右移一位，再比较是否大于等于target
        }
        right++;//最后再右指针右移一位
    }
    return min == Integer.MAX_VALUE ? 0 : min;
}
```
```java
class Solution {

    // 滑动窗口
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
```
<a name="NGrOM"></a>
# [59. 螺旋矩阵 II](https://leetcode.cn/problems/spiral-matrix-ii/)
本题要坚持循环不变量原则，搞清楚循环中的各个状态<br />模拟顺时针画矩阵的过程:

- 填充上行从左到右
- 填充右列从上到下
- 填充下行从右到左
- 填充左列从下到上

由外向内一圈一圈这么画下去，这里一圈下来，我们要画每四条边，这四条边怎么画，每画一条边都要坚持一致的左闭右开，或者左开右闭的原则，这样这一圈才能按照统一的规则画下来。<br />那么我按照左闭右开的原则，来画一圈，大家看一下：<br />![](https://cdn.nlark.com/yuque/0/2023/png/32832913/1674707611326-cee8a5db-8bac-4f88-903a-f02ac803af3e.png#averageHue=%23faf1ef&clientId=u59212a24-fb91-4&from=paste&id=u6cde233c&originHeight=1196&originWidth=1462&originalType=url&ratio=1&rotation=0&showTitle=false&status=done&style=none&taskId=ue1de597e-1a52-4ae0-9edd-9abc8a0de5b&title=)<br />这里每一种颜色，代表一条边，我们遍历的长度，可以看出每一个拐角处的处理规则，拐角处让给新的一条边来继续画。<br />这也是坚持了每条边左闭右开的原则。<br />一些同学做这道题目之所以一直写不好，代码越写越乱。<br />就是因为在画每一条边的时候，一会左开右闭，一会左闭右闭，一会又来左闭右开，岂能不乱。<br />下图为关于while循环次数的理解<br />![BABE4A65E059C7F21DD7D731F165CB48.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1674708117558-d5cf1c27-8ea7-4064-9f2f-76dda4842f39.png#averageHue=%23f8ebc1&clientId=u59212a24-fb91-4&from=paste&height=615&id=ub9e3882f&originHeight=794&originWidth=1423&originalType=binary&ratio=1&rotation=0&showTitle=false&size=62425&status=done&style=none&taskId=u551087a8-8068-4226-bc5e-5cd77dc6bd1&title=&width=1102)
```java
/**
 * 代码随想录
 * @param n
 * @return
 */
public int[][] generateMatrix1(int n) {
    int loop = 0;//控制循环次数
    int[][] res = new int[n][n];//结果数组
    int start = 0;//每次循环的开始位置，（start, start）
    int count = 1;//定义填充数字
    int i, j;//i表示行，j表示列

    while (loop++ < n/2){// n/2计算转几圈,loop从1开始
        //模拟上侧的从左到右
        for (j=start; j<n-loop; j++){//j<n-loop,用来控制左闭右开，最后减去一个位置的元素
            res[start][j] = count++;
        }
        //模拟右侧的从上到下
        for (i=start; i<n-loop; i++){
            res[i][j] = count++;//j是全局变量，从j上一次的位置来固定是哪一列
        }
        //模拟下侧从右到左
        for (; j>=loop; j--){//注意这里的大于等于
            res[i][j] = count++;
        }
        //模拟右侧从下到上
        for (; i>=loop; i--){//注意这里的大于等于
            res[i][j] = count++;
        }
        start++;
        //loop++在while里写了
    }
    if (n % 2 ==1){
        res[start][start] = count;
    }
    return res;

}
```

