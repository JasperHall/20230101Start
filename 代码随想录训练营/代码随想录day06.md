时间: 2023.5.15
<a name="HWz9u"></a>
# 今日任务
哈希表理论基础 , 242.有效的字母异位词 , 349. 两个数组的交集 , 202. 快乐数, 1. 两数之和   
<a name="RlGDB"></a>
# 收获

1. 复习哈希表概念
2. 理解HashSet什么时候使用
3. 取余操作的使用
4. 字符操作时 ASCII 的使用
<a name="EeIj3"></a>
# 明天计划
<a name="KjDsa"></a>
# 昨天复习

- [ ] day04  142.环形链表
- [ ] day04 继续学习递归, 补充 24.两两交换链表中的节点 的递归做法
- [ ] day02 全天
- [ ] day03 递归方法继续理解
<a name="hgLyD"></a>
# 哈希表理论基础 
:::info
建议：大家要了解哈希表的内部实现原理，哈希函数，哈希碰撞，以及常见哈希表的区别，数组，set 和map。  <br />什么时候想到用哈希法，**当我们遇到了要快速判断一个元素是否出现集合里的时候，就要考虑哈希法**。  这句话很重要，大家在做哈希表题目都要思考这句话。 <br />文章讲解[https://programmercarl.com/%E5%93%88%E5%B8%8C%E8%A1%A8%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html](https://programmercarl.com/%E5%93%88%E5%B8%8C%E8%A1%A8%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)
:::

<a name="vapcB"></a>
# 242.有效的字母异位词
:::info
建议： 这道题目，大家可以感受到 数组 用来做哈希表 给我们带来的遍历之处。 <br />题目链接/文章讲解/视频讲解： [https://programmercarl.com/0242.%E6%9C%89%E6%95%88%E7%9A%84%E5%AD%97%E6%AF%8D%E5%BC%82%E4%BD%8D%E8%AF%8D.html](https://programmercarl.com/0242.%E6%9C%89%E6%95%88%E7%9A%84%E5%AD%97%E6%AF%8D%E5%BC%82%E4%BD%8D%E8%AF%8D.html)
:::
本题注意ASCII的灵活使用<br />本题可以定义一个大小为26的数组，初始化为0（数组的默认值就是0），因为 字符a 到 字符z 的 ASCII 也是26个连续的数值。<br />定义一个数组叫做`record`用来上记录字符串 `s` 里字符出现的次数。<br />需要把字符映射到数组也就是**哈希表**的索引下标上，因为 **字符a 到 字符z 的ASCII是26个连续的数值**，所以 字符a 映射为下标0，相应的字符z映射为下标25。<br />再遍历 字符串`s`的时候，只需要将` s[i] - ‘a’` 所在的元素做`+1` 操作即可，并不需要记住 字符a 的ASCII，只要求**出一个相对数值就可以了**。 这样就将字符串s中字符**出现的次数**，统计出来了。<br />那看一下如何检查 字符串t 中是否出现了这些字符，同样在遍历 字符串t 的时候，对 t 中出现的字符映射哈希表索引上的数值再做`-1`的操作。<br />那么最后检查一下，`record`数组如果有的元素不为 0 ，说明 字符串s和t 一定是谁多了字符或者谁少了字符，`return false`。<br />最后如果`record`数组所有元素都为 0，说明字符串s和t是字母异位词，`return true`。<br />**时间复杂度为O(n)，空间上因为定义是的一个常量大小的辅助数组，所以空间复杂度为O(1)**。
```java
/**
 * 字典解法
 * 时间复杂度O(m+n) 空间复杂度O(1)
 * @param s
 * @param t
 * @return
 */
public boolean isAnagram(String s, String t) {
    int[] record = new int[26];

    for (int i =0; i<s.length(); i++){
        record[s.charAt(i) - 'a']++; //并不需要记住字符a的ASCII，只要求出一个相对数值就可以了
    }
    for (int i=0; i<t.length(); i++){
        record[t.charAt(i) - 'a']--;
    }

    for (int count : record){
        if (count != 0){
            return false; // record数组如果有的元素不为零0，说明字符串s和t 一定是谁多了字符或者谁少了字符。
        }
    }
    return true; // record数组所有元素都为零0，说明字符串s和t是字母异位词
}
```
来自：代码随想录
<a name="QU6eX"></a>
# 349. 两个数组的交集
:::info
建议：本题就开始考虑 什么时候用 set 什么时候用数组，本题其实是使用set的好题，但是后来力扣改了题目描述和 测试用例，添加了 0 <= nums1[i], nums2[i] <= 1000 条件，所以使用数组也可以了，不过建议大家忽略这个条件。 尝试去使用set。 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0349.%E4%B8%A4%E4%B8%AA%E6%95%B0%E7%BB%84%E7%9A%84%E4%BA%A4%E9%9B%86.html](https://programmercarl.com/0349.%E4%B8%A4%E4%B8%AA%E6%95%B0%E7%BB%84%E7%9A%84%E4%BA%A4%E9%9B%86.html)
:::
理解了HashSet用法后这题是比较简单的<br />意题目特意说明：输出结果中的每个元素一定是唯一的，也就是说输出的结果的去重的， 同时可以不考虑输出结果的顺序<br />但是要注意，使用数组来做哈希的题目，是因为题目都限制了数值的大小。<br />**如果这道题目没有限制数值的大小，就无法使用数组来做哈希表了。**<br />而且如果哈希值比较少、特别分散、跨度非常大，使用数组就造成空间的极大浪费。

那有同学可能问了，遇到哈希问题我直接都用set不就得了，用什么数组啊。<br />直接使用set 不仅占用空间比数组大，而且速度要比数组慢，set把数值映射到key上都要做hash计算的。<br />不要小瞧 这个耗时，在数据量大的情况，差距是很明显的。
```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0){
            return new int[0];
        }

        // 用于存放nums1数组的不重复的所有数字
        HashSet<Integer> set = new HashSet<>();
        // 遍历添加到set
        for (int i=0; i<nums1.length; i++){
            set.add(nums1[i]);
        }

        //用于存放交集的数字
        HashSet<Integer> resultSet = new HashSet<>();
        for (int i=0; i<nums2.length; i++){
            // 如果包含就添加到set中去
            if (set.contains(nums2[i])){
                resultSet.add(nums2[i]);
            }
        }

        //方法1：将结果集合转为数组  用JDK8Stream流新特性，不过耗时会更长
//            return resultSet.stream().mapToInt(x -> x).toArray();

        //方法2：另外申请一个数组存放setRes中的元素,最后返回数组
        int[] result = new int[resultSet.size()];
        int j = 0;
        for(int i : resultSet){
            result[j++] = i;
        }

        return result;
    }
}
```
<a name="imPqN"></a>
# 202. 快乐数
:::info
建议：这道题目也是set的应用，其实和上一题差不多，就是 套在快乐数一个壳子 <br />题目链接/文章讲解：[https://programmercarl.com/0202.%E5%BF%AB%E4%B9%90%E6%95%B0.html](https://programmercarl.com/0202.%E5%BF%AB%E4%B9%90%E6%95%B0.html)
:::
题目中说了会 无限循环，那么也就是说求和的过程中，sum会重复出现，这对解题很重要！当我们遇到了要快速判断一个元素是否出现集合里的时候，就要考虑哈希法了。所以这道题目使用哈希法，来判断这个sum是否重复出现，如果重复了就是return false， 否则一直找到sum为1为止。

我们可以先举几个例子。我们从 77 开始。则下一个数字是 49（因为 72=49），然后下一个数字是 97（因为 42+92=97)。我们可以不断重复该的过程，直到我们得到 1。因为我们得到了 1，我们知道 77 是一个快乐数，函数应该返回 true。<br />再举一个例子，从116开始，通过反复平方和计算下一个数字，我么最终得到58，再继续计算之后，又回到58。明显我们回到了一个已经计算过的数字，可以知道有一个循环，因此不可能达到1,。索引对于116，函数应该返回false<br />![image.png](https://cdn.nlark.com/yuque/0/2022/png/32832913/1669963314896-9748830f-2613-4894-8bfd-e46e01039d04.png#averageHue=%23fcfcfa&clientId=u6759ac28-9698-4&from=paste&height=541&id=u9840fc6c&originHeight=633&originWidth=1218&originalType=binary&ratio=1&rotation=0&showTitle=false&size=129925&status=done&style=none&taskId=ucd27c49b-98b2-4672-9b7b-8f97dba848b&title=&width=1041.0256792102355)<br />所以，给出一个数计算后，可能会有三种可能

1. 最终得到1
2. 最终进入循环
3. 值越来越大，最后无穷大

对于第三种情况，我们怎么知道他会继续变大，而不是最终得到1呢？所以想一下，每个固定位的数字中最大的值计算后的下一位是多少

| Digits | Largest | Next（前一个数字计算后） |
| --- | --- | --- |
| 1 | 9 | 81 |
| 2 | 99 | 162 |
| 3 | 999 | 243 |
| 4 | 9999 | 324 |
| 13 | 9999999999999 | 1053 |

所以3位数的下一位不可能大于243。这就意味着他要么被困在243以下的循环内，要么跌到1。4位或4位以上的数组在每一步之后都会走丢一位，直到降到3位为止。所以我们知道，最坏的情况下，算法会在243以下的所有数字上循环，然后回到它已经到过的一个循环或者回到1。但他不会无限期地进行下去，所以就可以排除（值越来越大，最后无穷大）的可能
<a name="Bnv4X"></a>
## 方法一：哈希集合
算法第一部分按照题目要求做数位分离，求平方和，<br />算法第二部分，使用哈希集合，每次生成链中的下一个数字时，我们都检查他是否存在哈希集合中

- 如果在哈希集合中，就意味着我们处于一个循环中，因此应该返回false
- 如果不再哈希集合中，就意味着我应该添加他。
```java
//方法一：用哈希集合检测循环
public int getNextNumber(int n){
    int result = 0;
    while (n > 0){
        int temp = n % 10;
        result = result + temp * temp;  // 注意这里有个加号
        n = n /10;
    }
    return result;
}
public boolean isHappy(int n) {
    Set<Integer> record = new HashSet<>();
    while (n !=1 && !record.contains(n)){  // 不包含
        record.add(n);
        n = getNextNumber(n);
    }
    return n == 1;  // 退出上边的while循环只有两种情况, 一种是n=1返回真, 另一种是出现了循环返回假
}
```
:::danger
注意点: 取余的单数操作
:::
<a name="S9P4m"></a>
## 方法二:快慢指针
<a name="MyhDs"></a>
# 1.两数之和
:::info
建议：本题虽然是 力扣第一题，但是还是挺难的，也是 代码随想录中 数组，set之后，使用map解决哈希问题的第一题。 <br />建议大家先看视频讲解，然后尝试自己写代码，在看文章讲解，加深印象。 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0001.%E4%B8%A4%E6%95%B0%E4%B9%8B%E5%92%8C.html](https://programmercarl.com/0001.%E4%B8%A4%E6%95%B0%E4%B9%8B%E5%92%8C.html)
:::

- 自己做题第一次用的为暴力遍历，但是时间复杂度为O(n^2)
- 由于哈希查找的时间复杂度为 O(1)，所以可以利用**哈希容器 map **降低时间复杂度
- 遍历数组 nums，i 为当前下标，每个值都判断 map中是否存在 `target-nums[i]` 的 key 值
- 如果存在则找到了两个值，**如果不存在**则将当前的 (nums[i],i) **存入** map 中，继续遍历直到找到为止
<a name="V5MaD"></a>
## 哈希法
首先我在强调一下 什么时候使用哈希法，当我们需要查询一个元素是否出现过，或者一个元素是否在集合里的时候，就要第一时间想到哈希法。<br />本题呢，我就需要一个集合来存放我们遍历过的元素，然后在遍历数组的时候去询问这个集合，某元素是否遍历过，也就是 是否出现在这个集合。那么我们就应该想到使用哈希法了。<br />因为本题，我们不仅要知道**元素有没有遍历过**，还有知道这个**元素对应的下标**，需要使用 `key value` 结构来存放，`**key**`**来存元素，**`**value**`**来存下标**，那么使用 map 正合适。
> 再来看一下使用 数组和set 来做哈希法的局限。
> - 数组的大小是受限制的，而且如果元素很少，而哈希值太大会造成内存空间的浪费。
> - set是一个集合，里面放的元素只能是一个key，而两数之和这道题目，不仅要判断y是否存在而且还要记录y的下标位置，因为要返回x 和 y的下标。所以set 也不能用。

此时就要选择另一种数据结构：map ，map是一种`key value`的存储结构，可以用`key`保存数值，用`value`在保存数值所在的下标。<br />**要明确两点**

- **map用来做什么**
- **map中key和value分别表示什么**

map 目的用来存放我们访问过的元素，因为遍历数组的时候，需要记录我们之前遍历过哪些元素和对应的下表，这样才能找到与当前元素相匹配的（也就是相加等于target）<br />接下来是map中key和value分别表示什么。<br />这道题 我们需要 给出一个元素，判断这个元素是否出现过，如果出现过，返回这个元素的下标。<br />那么判断元素是否出现，这个元素就要作为`key`，所以数组中的元素作为`key`，有`key`对应的就是`value`，`value`用来存下标。<br />所以** map中的存储结构为 {key：数据元素，value：数组元素对应的下标}**。<br />在遍历数组的时候，只需要向map去查询是否有和目前遍历元素比配的数值，**如果有**，就找到的匹配对，**如果没有**，就把目前遍历的元素放进map中，因为map存放的就是我们访问过的元素。
```java
public int[] twoSum(int[] nums, int target) {
    int[] result = new int[2]; // 结果数组

    if (nums == null || nums.length == 0){
        return result;
    }

    Map<Integer, Integer> map = new HashMap<>();  // key存放数组的元素值，value存放数组的下标

    for (int i=0; i<nums.length; i++){
        int temp = target - nums[i];  // 遍历当前元素
        // 在 map 中寻找是否有匹配的key
        if (map.containsKey(temp)){ // 如果在map中找到对应相加等于target的key，则给结果数组赋值，准备返回结果
            result[1] = i;
            result[0] = map.get(temp); //获取对应的value
            break;
        }
        map.put(nums[i], i); //如果没有找到匹配对，就把访问过的元素和下标加入到map中
    }
    return result;
}
```
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[] {map.get(target-nums[i]),i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}

作者：guanpengchn
链接：https://leetcode.cn/problems/two-sum/solution/jie-suan-fa-1-liang-shu-zhi-he-by-guanpengchn/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```
<a name="wgczw"></a>
## 暴力法
两层for循环
```java
public int[] twoSum(int[] numbers, int target) {
    int[] res = new int[2];
    for (int i=0; i<numbers.length-1; i++){
        res[0] = i;
        for (int j=i+1; j<numbers.length; j++){
            res[1] = j;
            if (numbers[i]+numbers[j]==target){
                return res;
            }
        }
    }
    return res;
}
```

