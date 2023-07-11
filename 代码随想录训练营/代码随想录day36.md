时间: 2023.6.14周三
<a name="sPFyY"></a>
# 今日任务
第八章 贪心算法 part05, 435. 无重叠区间, 763.划分字母区间 , 56. 合并区间 
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 复习 56. 合并区间  ,. 可以多看几种解法

<a name="XBsr0"></a>
# 复习

- [ ] 再看看 406.根据身高重建队列  和 452. 用最少数量的箭引爆气球 


:::info
今天的三道题目，都算是 重叠区间 问题，大家可以好好感受一下。 都属于那种看起来好复杂，但一看贪心解法，惊呼：这么巧妙！ <br />还是属于那种，做过了也就会了，没做过就很难想出来。<br />不过大家把如下三题做了之后， 重叠区间 基本上差不多了
:::
<a name="aRDa7"></a>
# 435. 无重叠区间 
[代码随想录](https://programmercarl.com/0435.%E6%97%A0%E9%87%8D%E5%8F%A0%E5%8C%BA%E9%97%B4.html)<br />相信很多同学看到这道题目都冥冥之中感觉要排序，但是究竟是按照右边界排序，还是按照左边界排序呢？其实都可以。主要就是为了让区间尽可能的重叠。<br />我**来按照右边界排序**，从左向右记录非交叉区间的个数。最后用区间总数减去非交叉区间的个数就是需要移除的区间个数了。<br />此时问题就是要求非交叉区间的最大个数。这里记录非交叉区间的个数还是有技巧的，如图：<br />![20230201164134.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1688991229264-8abb6958-061b-4dec-bd24-62329a4f01e4.png#averageHue=%23f8f5f3&clientId=uaac7712e-4126-4&from=paste&height=443&id=ua016b4c3&originHeight=518&originWidth=1126&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=92022&status=done&style=none&taskId=u39f23046-8534-43f3-ba6c-293f05bcf82&title=&width=962.3931976935345)<br />区间，1，2，3，4，5，6都按照右边界排好序。<br />当确定区间 1 和 区间2 重叠后，如何确定是否与 区间3 也重贴呢？<br />就是**取 区间1 和 区间2 右边界的最小值，因为这个最小值之前的部分一定是 区间1 和区间2 的重合部分，如果这个最小值也触达到区间3，那么说明 区间 1，2，3都是重合的**。<br />接下来就是找大于区间1结束位置的区间，是从区间4开始。那有同学问了为什么不从区间5开始？**别忘了已经是按照右边界排序的了**。<br />区间4结束之后，再找到区间6，所以一共记录非交叉区间的个数是三个。<br />总共区间个数为6，减去非交叉区间的个数3。移除区间的最小数量就是3。
```java
// 按照右边界排序
class Solution {
    /**
     * 按照右边界进行排序
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

        int count = 1;  // 记录非交叉区间的个数

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1]){  // 后面区间的左边界值 小于 前面区间的右边界值 说明存在重叠部分
                // 把当前区间的右边界值 赋值为 当前区间和上一个区间的右边界中较小的一个
                intervals[i][1] = Math.min(intervals[i-1][1], intervals[i][1]);
                continue;
            } else {
                count++;
            }
        }
        return intervals.length - count;  // 总长度减去没有交叉的区间个数, 就是要移除的重叠区间
    }
}
/**
 * 时间复杂度 : O(NlogN)  排序需要 O(NlogN) 的复杂度
 * 空间复杂度 : O(logN) java所使用的内置函数用的是快速排序需要 logN 的空间
 */
```
左边界排序可不可以呢？也是可以的，只不过 左边界排序我们就是直接求 重叠的区间，remove为记录重叠区间数。
```java
// 按左边排序，不管右边顺序。相交的时候取最小的右边。
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> {
            return Integer.compare(a[0],b[0]);
        });
        int remove = 0;
        int pre = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if(pre > intervals[i][0]) {
                remove++;
                pre = Math.min(pre, intervals[i][1]);
            }
            else pre = intervals[i][1];
        }
        return remove;
    }
}
```

本题其实和[代码随想录](https://programmercarl.com/0452.%E7%94%A8%E6%9C%80%E5%B0%91%E6%95%B0%E9%87%8F%E7%9A%84%E7%AE%AD%E5%BC%95%E7%88%86%E6%B0%94%E7%90%83.html)非常像，弓箭的数量就相当于是非交叉区间的数量，只要把弓箭那道题目代码里射爆气球的判断条件加个等号（认为[0，1][1，2]不是相邻区间），然后用总区间数减去弓箭数量 就是要移除的区间数量了。把[代码随想录](https://programmercarl.com/0452.%E7%94%A8%E6%9C%80%E5%B0%91%E6%95%B0%E9%87%8F%E7%9A%84%E7%AE%AD%E5%BC%95%E7%88%86%E6%B0%94%E7%90%83.html)代码稍做修改，就可以AC本题。
```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

            // 根据气球直径的开始坐标从小到大排序
            // 使用Integer内置比较方法，不会溢出
            Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

            int count = 1;  // points 不为空至少需要一支箭

            for (int i=1; i< intervals.length; i++){
                if (intervals[i][0] >= intervals[i-1][1]){  // 气球i 和 气球i-1 不挨着，注意这里不是 >=
                    count++;  // 需要加一只剑
                } else {  // 气球i和气球i-1挨着
                    intervals[i][1] = Math.min(intervals[i][1], intervals[i-1][1]);   // 更新重叠气球 最小 右边界
                }
            }
            return intervals.length-count;
    }
}
```
-
<a name="G9YMp"></a>
# 763.划分字母区间 
[代码随想录](https://programmercarl.com/0763.%E5%88%92%E5%88%86%E5%AD%97%E6%AF%8D%E5%8C%BA%E9%97%B4.html)<br />一想到分割字符串就想到了回溯，但本题其实不用回溯去暴力搜索。如果没有接触过这种题目的话，还挺有难度的。<br />题目要求同一字母最多出现在一个片段中，那么如何把同一个字母的都圈在同一个区间里呢？<br />在遍历的过程中相当于是要找每一个字母的边界，如果找到之前遍历过的所有字母的最远边界，说明这个边界就是分割点了。此时前面出现过所有字母，最远也就到这个边界了。<br />可以分为如下两步：

1. 统计每一个字符最后出现的位置
2. 从头遍历字符，并更新字符的最远出现下标，如果找到字符最远出现位置下标和当前下标相等了，则找到了分割点

![20201222191924417.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689000296712-c6d77bdd-5fbe-4675-8d7b-5d962b41fdb5.png#averageHue=%23f7f6f6&clientId=u7b70bfd7-145b-4&from=paste&height=482&id=u2cdb70f0&originHeight=564&originWidth=1430&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=164284&status=done&style=none&taskId=u6f26c446-3c24-4c72-8fb4-cac2b26db7c&title=&width=1222.2222670530678)
```java
class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> list = new LinkedList<>();
        int[] edge = new int[26];  // 用来记录字母的最后出现下标
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            edge[chars[i] - 'a'] = i;  // 更新字母的索引位置
        }

        int idx = 0;
        int last = -1;

        for (int i=0; i<chars.length; i++){
            idx = Math.max(idx, edge[chars[i]-'a']);  // 取当前位置的字母的索引最大值

            if (i == idx){  // 当当前位置的索引值和区间中字母索引出现最后位置最大值相等的时候
                list.add(i-last); // 结果列表加上一个目前该该位置的长度
                last = i;  // 更新下次用来计算长度的位置
            }
        }
        return list;
    }
}
时间复杂度：O(n)
空间复杂度：O(1)，使用的hash数组是固定大小
```
补充: <br />这里提供一种与[代码随想录](https://programmercarl.com/0452.%E7%94%A8%E6%9C%80%E5%B0%91%E6%95%B0%E9%87%8F%E7%9A%84%E7%AE%AD%E5%BC%95%E7%88%86%E6%B0%94%E7%90%83.html)、[代码随想录](https://programmercarl.com/0435.%E6%97%A0%E9%87%8D%E5%8F%A0%E5%8C%BA%E9%97%B4.html)相同的思路。<br />统计字符串中所有字符的起始和结束位置，记录这些区间(实际上也就是[代码随想录](https://programmercarl.com/0435.%E6%97%A0%E9%87%8D%E5%8F%A0%E5%8C%BA%E9%97%B4.html)题目里的输入)，**将区间按左边界从小到大排序，找到边界将区间划分成组，互不重叠。找到的边界就是答案。**
```java
class Solution{
    /*解法二： 上述c++补充思路的Java代码实现*/
    
    public  int[][] findPartitions(String s) {
        List<Integer> temp = new ArrayList<>();
        int[][] hash = new int[26][2];//26个字母2列 表示该字母对应的区间

        for (int i = 0; i < s.length(); i++) {
            //更新字符c对应的位置i
            char c = s.charAt(i);
            if (hash[c - 'a'][0] == 0) hash[c - 'a'][0] = i;

            hash[c - 'a'][1] = i;

            //第一个元素区别对待一下
            hash[s.charAt(0) - 'a'][0] = 0;
        }


        List<List<Integer>> h = new LinkedList<>();
        //组装区间
        for (int i = 0; i < 26; i++) {
            //if (hash[i][0] != hash[i][1]) {
            temp.clear();
            temp.add(hash[i][0]);
            temp.add(hash[i][1]);
            //System.out.println(temp);
            h.add(new ArrayList<>(temp));
            // }
        }
        // System.out.println(h);
        // System.out.println(h.size());
        int[][] res = new int[h.size()][2];
        for (int i = 0; i < h.size(); i++) {
            List<Integer> list = h.get(i);
            res[i][0] =  list.get(0);
            res[i][1] =  list.get(1);
        }

        return res;

    }

    public  List<Integer> partitionLabels(String s) {
        int[][] partitions = findPartitions(s);
        List<Integer> res = new ArrayList<>();
        Arrays.sort(partitions, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int right = partitions[0][1];
        int left = 0;
        for (int i = 0; i < partitions.length; i++) {
            if (partitions[i][0] > right) {
                //左边界大于右边界即可纪委一次分割
                res.add(right - left + 1);
                left = partitions[i][0];
            }
            right = Math.max(right, partitions[i][1]);

        }
        //最右端
        res.add(right - left + 1);
        return res;

    }
}
```
-
<a name="mWPNY"></a>
# 56. 合并区间  
本题相对来说就比较难了。<br />[代码随想录](https://programmercarl.com/0056.%E5%90%88%E5%B9%B6%E5%8C%BA%E9%97%B4.html)<br />本题相对来说就比较难了。本题的本质其实还是判断重叠区间问题。<br />大家如果认真做题的话，话发现和我们刚刚讲过的[452. 用最少数量的箭引爆气球(opens new window)](https://programmercarl.com/0452.%E7%94%A8%E6%9C%80%E5%B0%91%E6%95%B0%E9%87%8F%E7%9A%84%E7%AE%AD%E5%BC%95%E7%88%86%E6%B0%94%E7%90%83.html)和 [435. 无重叠区间(opens new window)](https://programmercarl.com/0435.%E6%97%A0%E9%87%8D%E5%8F%A0%E5%8C%BA%E9%97%B4.html)都是一个套路。<br />这几道题都是判断区间重叠，区别就是判断区间重叠后的逻辑，本题是判断区间重叠后要进行区间合并。所以一样的套路，先排序，让所有的相邻区间尽可能的重叠在一起，按左边界，或者右边界排序都可以，处理逻辑稍有不同。<br />按照左边界从小到大排序之后，如果`intervals[i][0] <= intervals[i - 1][1]` 即`intervals[i]`的左边界`<= intervals[i-1]`的右边界，则一定有重叠。(本题相邻区间也算重贴，所以是<=）<br />这么说有点抽象，看图：（**注意图中区间都是按照左边界排序之后了**）<br />![20201223200632791.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1689003758924-0fbfffef-bdd1-4990-bb65-327307906e6b.png#averageHue=%23f9f7f6&clientId=u7b70bfd7-145b-4&from=paste&height=455&id=ubdbf655f&originHeight=532&originWidth=718&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=42252&status=done&style=none&taskId=ubef77595-050c-48f5-83a7-305fd27a6c2&title=&width=613.6752361846872)<br />知道如何判断重复之后，剩下的就是合并了，如何去模拟合并区间呢？其实就是用合并区间后左边界和右边界，作为一个新的区间，加入到result数组里就可以了。如果没有合并就把原区间加入到result数组。
```java
/**
时间复杂度 ： O(NlogN) 排序需要O(NlogN)
空间复杂度 ： O(logN)  java 的内置排序是快速排序 需要 O(logN)空间

*/
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new LinkedList<>();
        // 按照左边界进行排序
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

        // initial start 是最小左边界
        int start = intervals[0][0];  // 初始区间的左边界
        int rightmostRightBound = intervals[0][1];  // 初始区间的右边界

        for (int i = 1; i < intervals.length; i++) {
            // 如果下一个区间的左边界 大于 上一个的右边界
            if (intervals[i][0] > rightmostRightBound){
                // 直接加入区间, 并更新start
                res.add(new int[]{start, rightmostRightBound});  // add进去一个长度为2的一维数组, 两个数分别表示区间的左右边界
                start = intervals[i][0];
                rightmostRightBound = intervals[i][1];
            } else {
                // 右重叠的情况
                // 更新最大右边界
                rightmostRightBound = Math.max(rightmostRightBound, intervals[i][1]);
            }
        }
        res.add(new int[]{start, rightmostRightBound});
        return res.toArray(new int[res.size()][]);
    }
}
```
```java
// 版本2
class Solution {
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= res.getLast()[1]) {
                int start = res.getLast()[0];
                int end = Math.max(intervals[i][1], res.getLast()[1]);
                res.removeLast();
                res.add(new int[]{start, end});
            }
            else {
                res.add(intervals[i]);
            }         
        }
        return res.toArray(new int[res.size()][]);
    }
}
```
-
