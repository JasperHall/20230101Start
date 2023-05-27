时间: 2023.5.22
<a name="sPFyY"></a>
# 今日任务
239. 滑动窗口最大值, 347.前 K 个高频元素, 总结
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] day13_239滑动窗口最大值的解法二
- [ ] day12_347.前k个高频元素的大顶堆法, 桶排序法 
- [ ] 学习Lambda对集合的排序
- [ ] 学习map.entry()方法
<a name="XBsr0"></a>
# 复习

<a name="VcrR3"></a>
# 239. 滑动窗口最大值 （一刷至少需要理解思路）
:::info
之前讲的都是栈的应用，这次该是队列的应用了。<br />本题算比较有难度的，需要自己去构造单调队列，建议先看视频来理解。 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0239.%E6%BB%91%E5%8A%A8%E7%AA%97%E5%8F%A3%E6%9C%80%E5%A4%A7%E5%80%BC.html](https://programmercarl.com/0239.%E6%BB%91%E5%8A%A8%E7%AA%97%E5%8F%A3%E6%9C%80%E5%A4%A7%E5%80%BC.html)
:::
这是使用**单调队列**的经典题目。<br />难点是如何求一个区间里的最大值呢？（这好像是废话) ，暴力一下不就得了。<br />暴力方法，遍历一遍的过程中每次从窗口中再找到最大的数值，这样很明显是 `O(n × k)` 的算法。<br />有的同学可能会想用一个**大顶堆（优先级队列）**来存放这个窗口里的 k 个数字，这样就可以知道最大的最大值是多少了， **但是问题是这个窗口是移动的，而大顶堆每次只能弹出最大值，我们无法移除其他数值，这样就造成大顶堆维护的不是滑动窗口里面的数值了**。所以不能用大顶堆。<br />此时我们需要一个队列，这个队列呢，放进去窗口里的元素，然后随着窗口的移动，队列也一进一出，每次移动之后，队列告诉我们里面的最大值是什么。这个队列应该长这个样子：
```java
class MyQueue {
public:
    void pop(int value) {
    }
    void push(int value) {
    }
    int front() {
        return que.front();
    }
};
```
每次窗口移动的时候，调用 `que.pop(滑动窗口中**移除**元素的数值)`，`que.push(滑动窗口**添加**元素的数值)`，然后`que.front()`就返回我们要的最大值。<br />这么个队列香不香，要是有现成的这种数据结构是不是更香了！可惜了，没有！ 我们需要自己实现这么个队列。<br />然后再分析一下，**队列里的元素一定是要排序的，而且要最大值放在出队口**，要不然怎么知道最大值呢。但如果把窗口里的元素都放进队列里，窗口移动的时候，队列需要弹出元素。<br />那么问题来了，已经排序之后的队列 怎么能把窗口要移除的元素（这个元素可不一定是最大值）弹出呢。大家此时应该陷入深思.....<br />**其实队列没有必要维护窗口里的所有元素，只需要维护有可能成为窗口里最大值的元素就可以了，同时保证队列里的元素数值是由大到小的。**<br />那么这个维护元素单调递减的队列就叫做单调队列，即 **单调递减 或 单调递增 的队列。C++中没有直接支持单调队列，需要我们自己来实现一个单调队列**<br />**注意:** 不要以为实现的单调队列就是 对窗口里面的数进行排序，如果排序的话，那和优先级队列又有什么区别了呢。<br />来看一下单调队列如何维护队列里的元素。  动画如下<br /> ![239.滑动窗口最大值.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1685169528227-17d8043a-a8ed-4cdd-93ed-15c46498da6a.gif#averageHue=%23efefef&clientId=u84e9280a-7839-4&from=paste&height=321&id=u63e951e4&originHeight=376&originWidth=444&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=609070&status=done&style=none&taskId=uadaeeb2f-505f-42de-a750-3910efd0032&title=&width=379.4871934066868)<br />对于窗口里的元素{2, 3, 5, 1 ,4}，单调队列里只维护{5, 4} 就够了，保持单调队列里单调递减，此时队列出口元素就是窗口里最大元素。<br />此时大家应该怀疑单调队列里维护着{5, 4} 怎么配合窗口进行滑动呢？<br />设计单调队列的时候，`pop`和 `push` 操作要保持如下规则：

1. **pop(value)**：如果窗口移除的元素value等于单调队列的出口元素，那么队列弹出元素，否则不用任何操作
2. **push(value)**：如果push的元素value大于入口元素的数值，那么就将队列入口的元素弹出，直到push元素的数值小于等于队列入口元素的数值为止

保持如上规则，每次窗口移动的时候，只要问 `que.front()` 就可以返回当前窗口的最大值。<br />为了更直观的感受到单调队列的工作过程，以题目示例为例，输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3，动画如下: <br />![239.滑动窗口最大值-2.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1685169568622-5b1412ba-0508-4450-b949-521024f25cc9.gif#averageHue=%23fafafa&clientId=u84e9280a-7839-4&from=paste&height=321&id=u061a82a6&originHeight=376&originWidth=574&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=1945828&status=done&style=none&taskId=uf7a8d6c5-a406-49c9-855f-71516a580ab&title=&width=490.5983085933293)<br />那么我们用什么数据结构来实现这个单调队列呢？当然使用**deque**最为合适
<a name="xjM7s"></a>
## 解法一
自定义数组法
```java
/**
 * 解法一
 * 自定义数组法
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1){
            return nums;  // 如果数组长度为1, 则直接返回
        }

        int len = nums.length - k + 1;  // 注意这一步的计算逻辑,求出结果数组的长度
        int[] res = new int[len];  // res是用来存放结果元素的数组
        int num = 0;

        MyQueue myQueue = new MyQueue(); // 自定义队列

        // 先将前k个元素放入队列
        for (int i=0; i<k; i++){
            myQueue.add(nums[i]);  // 调用自定义的方法
        }
        //调用自定义的方法, 此时设置 res[0]得值
        res[num++] = myQueue.peek();  // 注意num++的使用,先赋值,再+1

        // 开始滑动窗口
        for (int i=k; i<nums.length; i++){
            // 滑动窗口移除最前面的元素，移除时判断是否该移除目前队列的队首, 当目前队列的队首等于窗口的边界时就移除
            myQueue.poll(nums[i-k]);  // poll 出队  思考这里出队的思路, i指向当前要判断加入的元素位置, 减k代表窗口的左侧
            // 滑动窗口加入最后面的元素
            myQueue.add(nums[i]);  //add  入队
            // 记录对应的最大值
            res[num++] = myQueue.peek();
        }
        return res;
    }
}
/**
 * 自定义队列
 */
class MyQueue {
    Deque<Integer> deque = new LinkedList<>();

    // 自定义弹出元素的方法
    void poll(int val){
        // 弹出元素时, 比较当前要弹出的数值是否 等于 队列出口处的数值, 如果相等则弹出, 同时判断队列现在是否为空
        if (!deque.isEmpty() && val==deque.peek()){  // peek,返回队首元素 思考这里等号的作用,相等表示窗口到这个位置了,需要弹出一个让出位置
            deque.poll();  // 出队方法
        }
    }

    // 自定义加入元素的方法
    void add(int val){
        // 添加元素时,如果要添加的元素大于入口处的元素,就将入口元素弹出,保证队列元素单调递减
        //比如此时队列元素 3,1,2 将要入队, 2比1大, 所以1要弹出. 此时队列: 3,2
        while (!deque.isEmpty() && val>deque.getLast()){  // 注意这里是while循环, 传入的参数挨个与队尾比大小, 直到没有比新传入的元素小的元素
            deque.removeLast();  // 新传入的元素比原来队尾的元素大,则弹出队尾元素
        }
        deque.add(val);
    }

    // 队列的队首元素始终为最大值
    int peek(){
        return deque.peek();  // peek方法, 返回队首元素
    }
}
```
:::danger
注意:  if 和 while <br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685172912413-a4223b86-930b-48ae-b538-79824b070c20.png#averageHue=%23292e37&clientId=u5d810beb-3d67-4&from=paste&height=263&id=GGN6m&originHeight=308&originWidth=1228&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=27671&status=done&style=none&taskId=u8fc0ff56-d32d-4b93-b945-6fee034f8dd&title=&width=1049.5726880707464)
:::
再来看一下时间复杂度，使用单调队列的时间复杂度是 O(n)。<br />有的同学可能想了，在队列中 push元素的过程中，还有pop操作呢，感觉不是纯粹的O(n)。<br />其实，大家可以自己观察一下单调队列的实现，nums 中的每个元素最多也就被 push_back 和 pop_back 各一次，没有任何多余操作，所以整体的复杂度还是 O(n)。<br />空间复杂度因为我们定义一个辅助队列，所以是O(k)

<a name="p9HcU"></a>
# 347.前 K 个高频元素 （一刷至少需要理解思路）
:::info
大/小顶堆的应用， 在C++中就是优先级队列 <br />本题是 大数据中取前k值 的经典思路，了解想法之后，不算难。<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0347.%E5%89%8DK%E4%B8%AA%E9%AB%98%E9%A2%91%E5%85%83%E7%B4%A0.html](https://programmercarl.com/0347.%E5%89%8DK%E4%B8%AA%E9%AB%98%E9%A2%91%E5%85%83%E7%B4%A0.html)
:::
这道题目主要涉及到如下三块内容：

1. 要统计元素出现频率
2. 对频率排序
3. 找出前K个高频元素

**首先**统计元素出现的频率，这一类的问题可以使用 map 来进行统计。**然后**是对频率进行排序，这里我们可以使用一种 容器适配器就是**优先级队列**。

- 什么是优先级队列呢？

其实**就是一个披着队列外衣的堆**，因为优先级队列对外接口只是从队头取元素，从队尾添加元素，再无其他取元素的方式，看起来就是一个队列。而且优先级队列内部元素是自动依照元素的**权值**排列。

- 那么它是如何有序排列的呢？

缺省情况下 `priority_queue` 利用 `max-heap`（大顶堆）完成对元素的排序.

- 什么是堆呢？

**堆是一棵完全二叉树，树中每个结点的值都不小于(或不大于)其左右孩子的值**。 如果父亲结点是大于等于左右孩子就是**大顶堆**，小于等于左右孩子就是**小顶堆**。<br />所以大家经常说的大顶堆(堆头是最大元素)，小顶堆(堆头是最小元素)，如果懒得自己实现的话，就直接用`priority_queue`(优先级队列)就可以了，底层实现都是一样的，从小到大排就是小顶堆，从大到小排就是大顶堆。

本题我们就要使用优先级队列来对部分频率进行排序。
> **为什么不用快排呢? **使用快排要将map转换为vector的结构，然后对整个数组进行排序， 而这种场景下，我们其实只需要维护k个有序的序列就可以了，所以使用优先级队列是最优的。

此时要思考一下，是使用小顶堆呢，还是大顶堆？有的同学一想，题目要求前 K 个高频元素，那么果断用大顶堆啊。<br />那么问题来了，定义一个大小为 k 的大顶堆，在每次移动更新大顶堆的时候，每次弹出都把最大的元素弹出去了，那么怎么保留下来前K个高频元素呢。<br />而且使用大顶堆就要把所有元素都进行排序，那能不能只排序k个元素呢？<br />所以**我们要用小顶堆**，因为要统计最大前k个元素，只有小顶堆每次将最小的元素弹出，最后小顶堆里积累的才是前k个最大元素。<br />寻找前k个最大元素流程如图所示：（图中的频率只有三个，所以正好构成一个大小为3的小顶堆，如果频率更多一些，则用这个小顶堆进行扫描）<br />![347.前K个高频元素.jpg](https://cdn.nlark.com/yuque/0/2023/jpeg/32832913/1685175464084-567c798a-4da7-43f4-acbc-e78ddd23dba7.jpeg#averageHue=%23f6f6f6&clientId=u5d810beb-3d67-4&from=paste&height=923&id=u1db48f10&originHeight=1080&originWidth=1062&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=192726&status=done&style=shadow&taskId=u4b5471a2-bd01-4a9a-a368-053d17e02ef&title=&width=907.6923409862643)
:::info
思考: 

1. 堆? 栈? 队列?
:::
**注意打断点调试有助于理解!!!!!**
<a name="u4MFc"></a>
## 小顶堆解法
```java
/**
 * 使用小顶堆实现
 * @param nums
 * @param k
 * @return
 */
public int[] topKFrequent2(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();//key为数组元素, val为对应出现次数

    //给每个元素计算出现得次数
    for (int num : nums){
        map.put(num, map.getOrDefault(num, 0)+1);
    }

    //在优先队列中存储二元组(num,cnt),cnt表示元素值num在数组中的出现次数
    //出现次数按从队 头 到 队尾 的顺序是 从小到大 排,出现次数最低的在队头(相当于小顶堆)
//            PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair1[1]-pair2[1]);

    // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
    PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
        public int compare(int[] m, int[] n) {
            return m[1] - n[1];
        }
    });
    
    System.out.println("pq size = "+pq.size());
    
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) { // 小顶堆只需要维持k个元素有序

        if (pq.size() < k){ //小顶堆元素个数小于k个时直接加
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }else {
            if (entry.getValue() > pq.peek()[1]) {//当前元素出现次数 大于 小顶堆的根节点(这k个元素中出现次数最少的那个
                pq.poll();//弹出 队头(小顶堆的根结点),即把堆里出现次数最少的那个删除,留下的就是出现次数多的了
                pq.add(new int[]{entry.getKey(),entry.getValue()});
            }
        }
    }
    int[] ans = new int[k];
    for (int i=k-1; i>=0; i--){//依次弹出小顶堆,先弹出的是堆的根,出现次数少,后面弹出的出现次数多
        ans[i] = pq.poll()[0];
    }
    return ans;
}
```
:::danger

1. `PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair1[1]-pair2[1]);` 这意思理解不了啊我![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1679195618872-e984e2f1-c5a4-4f29-a75f-42047c57d0ae.png#averageHue=%232a3039&clientId=u18354cb0-9d58-4&from=paste&height=232&id=QOtdS&originHeight=271&originWidth=896&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=30083&status=done&style=shadow&taskId=u6294e20b-84d1-4c90-9d50-b65fba794f3&title=&width=765.8119939017823)![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685177636909-f5f8c893-dd96-48d7-8f53-64b7699870eb.png#averageHue=%23d1f8d0&clientId=u5d810beb-3d67-4&from=paste&height=519&id=u31cd7adf&originHeight=607&originWidth=884&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=105739&status=done&style=shadow&taskId=ua6ec7a0f-542c-4be0-9e13-ea96a5768b5&title=&width=755.5555832691692)![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1685177650591-67b24452-be6a-4860-90ac-eb3ce38d745f.png#averageHue=%23d1f8d0&clientId=u5d810beb-3d67-4&from=paste&height=346&id=u3a0c9f93&originHeight=405&originWidth=887&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=86541&status=done&style=shadow&taskId=u31810141-0660-4716-8fc7-60fc0415d17&title=&width=758.1196859273225)
2. `map.getOrDefault(num, 0)`方法

**Java HashMap getOrDefault() 方法**: 获取指定 key 对应对 value，如果找不到 key ，则返回设置的默认值。

   1. 语法: hashmap.getOrDefault(Object key, V defaultValue)   ---hashmap是HashMap的对象
      1. 参数 key: 键
      2. 参数 defaultValue : 当指定的key并不存在映射关系中，则返回的该默认值
   2. 返回 key 相映射的的 value，如果给定的 key 在映射关系中找不到，则返回指定的默认值。
3. 学习Lambda表达式对集合的排序方法
4. `map.entrySet()`方法学习
:::
<a name="lZ3av"></a>
## 大顶堆解法
<a name="fax9q"></a>
# 总结 
:::info
栈与队列做一个总结吧，加油<br />[https://programmercarl.com/%E6%A0%88%E4%B8%8E%E9%98%9F%E5%88%97%E6%80%BB%E7%BB%93.html](https://programmercarl.com/%E6%A0%88%E4%B8%8E%E9%98%9F%E5%88%97%E6%80%BB%E7%BB%93.html)
:::

