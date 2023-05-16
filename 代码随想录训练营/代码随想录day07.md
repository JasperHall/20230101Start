时间: 2023.5.16
<a name="sPFyY"></a>
# 今日任务
454.四数相加II , 383. 赎金信 ,15. 三数之和 , 18. 四数之和, 总结  
<a name="CXkSh"></a>
# 收获

1. 454.四数相加II  合理运用问题拆分
<a name="KOISD"></a>
# 计划

1. 明天重点复习 15.三数之和 
2. 明天重点复习 18.四数之和 
<a name="XBsr0"></a>
# 复习


<a name="VhdFu"></a>
# 454.四数相加II 
:::info
建议：本题是 使用map 巧妙解决的问题，好好体会一下 哈希法 如何提高程序执行效率，降低时间复杂度，当然使用哈希法 会提高空间复杂度，但一般来说我们都是舍空间 换时间， 工业开发也是这样。<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0454.%E5%9B%9B%E6%95%B0%E7%9B%B8%E5%8A%A0II.html](https://programmercarl.com/0454.%E5%9B%9B%E6%95%B0%E7%9B%B8%E5%8A%A0II.html)
:::
进行巧妙的拆分计算

1. 首先定义 一个map，`key`放 nums1数组 和nums2数组 各个元素的和，`value` 放 nums1数组 和nums2数组 各个元素的和 出现的次数。
2. 遍历 nums1 和 nums2 数组，统计两个数组元素之和，**和出现的次数，放到 map 中**。
3. 定义 int 变量 `count`，用来统计 `四数之和 = 0` 出现的次数。
4. 在遍历 nums3 和 nums4 数组，找到如果 `0-(c+d) ` 在 map 中出现过的话，就用 `count` 把 map 中`key` 对应的 `value` 也就是出现次数统计出来。
5. 最后返回统计值 `count` 就可以了
```java
public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    Map<Integer, Integer> map = new HashMap<>();  // key记录nums1元素加nums2遍历后的和，value存储相同和出现的次数
    int count=0;  // 计数，结果

    int temp;
    for (int i=0; i<nums1.length; i++) {
        for (int j=0; j<nums2.length; j++) {
            temp = nums1[i] + nums2[j];  // nums1 和 nums2 各个元素求和存入 temp
            if (map.containsKey(temp)){
                map.put(temp, map.get(temp)+1); // 此key 的 value值 +1
            }else {
                map.put(temp, 1);
            }
        }
    }

    // 统计剩余的两个数组的元素的和，在map中找是否存在相加为0的情况，同时记录次数。
    for (int i : nums3){
        for (int j : nums4){
            temp = i+j;
            if (map.containsKey(0 - temp)){
                count += map.get(0-temp);
            }
        }
    }
    return count;
}
```

<a name="SKwiQ"></a>
# 383. 赎金信  
:::info
建议：本题 和 242.有效的字母异位词 是一个思路 ，算是拓展题 <br />题目链接/文章讲解：[https://programmercarl.com/0383.%E8%B5%8E%E9%87%91%E4%BF%A1.html](https://programmercarl.com/0383.%E8%B5%8E%E9%87%91%E4%BF%A1.html)
:::
可以用暴力法两层for循环挨个查找, 下面我们有哈希法<br />一些同学可能想，用数组干啥，都用map完事了，**其实在本题的情况下，使用map的空间消耗要比数组大一些的，因为map要维护红黑树或者哈希表，而且还要做哈希函数，是费时的！数据量大的话就能体现出来差别了。 所以数组更加简单直接有效！**
```java
/**
 * 写法一
 * @param ransomNote
 * @param magazine
 * @return
 */
public boolean canConstruct1(String ransomNote, String magazine) {
    int[] arr = new int[26];
    int temp;

    for (int i=0; i<magazine.length(); i++){
        temp = magazine.charAt(i) - 'a';
        arr[temp]++;
    }

    for (int i=0; i<ransomNote.length(); i++){
        temp = ransomNote.charAt(i) - 'a';

        if (arr[temp] > 0){
            arr[temp]--;
        }else {
            return false;
        }
    }
    return true;
}
```
<a name="X79Vz"></a>
# 15. 三数之和 
:::info
建议：本题虽然和 两数之和 很像，也能用哈希法，但用哈希法会很麻烦，双指针法才是正解，可以先看视频理解一下 双指针法的思路，文章中讲解的，没问题 哈希法很麻烦。 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0015.%E4%B8%89%E6%95%B0%E4%B9%8B%E5%92%8C.html](https://programmercarl.com/0015.%E4%B8%89%E6%95%B0%E4%B9%8B%E5%92%8C.html)
:::
本题虽然和 两数之和 很像，也能用哈希法，但用哈希法会很麻烦，双指针法才是正解，可以先看视频理解一下 双指针法的思路，文章中讲解的，没问题 哈希法很麻烦。 	<br />(难题), 虽然放在了哈希题解但是用双指针才是正解
<a name="N5ZY0"></a>
## 哈希解法：
略
<a name="BKhq6"></a>
## 双指针解法
其实这道题目使用哈希法并不十分合适，因为在去重的操作中有很多细节需要注意，在面试中很难直接写出没有bug的代码。<br />而且使用哈希法 在使用 两层for循环 的时候，能做的剪枝操作很有限，虽然时间复杂度是 `O(n^2)` ，也是可以在 leetcode 上通过，但是程序的执行时间依然比较长 。<br />接下来我来介绍另一个解法：双指针法，这道题目使用**双指针法 要比哈希法高效一些**，那么来讲解一下具体实现的思路。<br />动画效果如下：<br />![15.三数之和.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1684231844302-314ee283-a715-4dc0-ada9-43f266a84374.gif#averageHue=%23fcfcfc&clientId=u8bf4e181-75c6-4&from=paste&height=210&id=ue3f612be&originHeight=246&originWidth=376&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=500851&status=done&style=none&taskId=u7cd04502-9862-4c6f-820b-aaf3c94a86c&title=&width=321.36753315521224)<br />拿这个 nums 数组来举例，首先**将数组排序**，然后有一层 for循环，`i` 从下标`0`的地方开始，同时定一个下标 `left` 定义在`i+1`的位置上，定义下标 `right` 在数组结尾的位置上。依然还是在数组中找到 `abc` 使得 `a + b +c =0`，我们这里相当于 `a = nums[i]`，`b = nums[left]`，`c = nums[right]`。<br />接下来如何移动 `left` 和 `right` 呢，

-  如果** **`**nums[i] + nums[left] + nums[right]** > 0` 就说明 此时三数之和大了，因为数组是**排序**后了，所以 `right`下标就应该向左移动，这样才能让三数之和小一些。
- **如果** `nums[i] + nums[left] + nums[right] < 0` 说明 此时 三数之和小了，`left`就向右移动，才能让三数之和大一些，直到`left`与`right`相遇为止。

时间复杂度：O(n^2)。
:::danger
注意: 

1. result.add(Arrays.asList(nums[i], nums[left], nums[right]));  // 注意这一行方法的使用
:::
```java
/**
 * 双指针：头尾指针
 * @param nums
 * @return
 */
public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);//排序

    //找出a+b+c=0
    // a=nums[i], b=nums[left], c=nums[right]
    for (int i=0; i<nums.length; i++){
        //排序后如果第一个元素已经大于0，则后面的都不会凑出三个数相加等于0的三元组了，遇到这种情况直接返回结果
        if (nums[i] > 0){
            return result;
        }

        if (i>0 && nums[i]==nums[i-1]){//去掉 i 这个位置的重复情况
            continue;
        }
        
        //双指针开始(头尾指针
        int left = i + 1;
        int right = nums.length - 1;
        while (right > left){//这里不能写等号,
            int sum = nums[i] + nums[left] + nums[right];
            //判断三数之和的情况，来做出情况选择操作
            if (sum > 0){//和大于0,右边往左移
                right--;
            }else if (sum < 0){//和小于0,左边往右移
                left++;
            }else {
                result.add(Arrays.asList(nums[i], nums[left], nums[right]));//注意这一行方法的使用

                //去重，去重的逻辑代码应该放在找到一个三元数组之后，对 b 和 c 进行去重
                //上面是对 i 的取值处进行去重, right和left都是在i之后的值,这里对他们再进行去重, 可以这样做的原因是因为返回的是数组中的值,不是下标
                while (right > left && nums[right] == nums[right-1]) right--; //因为是排序后的，所以相同的值都是挨着的
                while (right > left && nums[left] == nums[left+1]) left++;

                right--;
                left++;
            }
        }
    }
    return result;
}
```
<a name="U0D2x"></a>
### 关于本题中的去重问题
**a的去重：**说道去重，其实主要考虑三个数的去重。` a, b ,c, `对应的就是 `nums[i]，nums[left]，nums[right]`, a 如果重复了怎么办，a 是 nums 里遍历的元素，那么应该直接跳过去。但这里有一个问题，**是判断 **`**nums[i] 与 nums[i + 1]**`** 是否相同，还是判断 **`**nums[i] 与 nums[i-1]**`** 是否相同**。有同学可能想，这不都一样吗。<br />其实不一样！都是和 `nums[i]` 进行比较，是比较它的前一个，还是比较他的后一个。<br />如果我们的写法是 这样：
```java
if (nums[i] == nums[i + 1]) { // 去重操作
    continue;
}
```
那就我们就把 三元组中出现重复元素的情况直接pass掉了。 例如{-1, -1 ,2} 这组数据，当遍历到第一个 -1 的时候，判断 下一个也是 -1，那这组数据就pass了。<br />我们要做的是 **不能有重复的三元组，但三元组内的元素是可以重复的！, **所以这里是有两个重复的维度。<br />那么应该这么写：
```java
if (i > 0 && nums[i] == nums[i - 1]) { continue; }
```
这么写就是当前使用 `nums[i]`，我们判断前一位是不是一样的元素，在看 {-1, -1 ,2} 这组数据，当遍历到 第一个 -1 的时候，只要前一位没有-1，那么 {-1, -1 ,2} 这组数据一样可以收录到 结果集里。这是一个非常细节的思考过程。<br />**b与c的去重：**很多同学写本题的时候，去重的逻辑多加了 对`right `和`left` 的去重：（代码中注释部分）
```java
while (right > left) {
    if (nums[i] + nums[left] + nums[right] > 0) {
        right--;
        // 去重 right
        while (left < right && nums[right] == nums[right + 1]) right--;
    } else if (nums[i] + nums[left] + nums[right] < 0) {
        left++;
        // 去重 left
        while (left < right && nums[left] == nums[left - 1]) left++;
    } else {
    }
}
```
但细想一下，这种去重其实对提升程序运行效率是没有帮助的。<br />拿`right`去重为例，即使不加这个去重逻辑，依然根据 `while (right > left) `和 `if (nums[i] + nums[left] + nums[right] > 0) `去完成`right--`的操作。<br />**多加了 **`**while (left < right && nums[right] == nums[right + 1]) right--;**`**这一行代码，其实就是把 需要执行的逻辑提前执行了，但并没有减少 判断的逻辑。**<br />最直白的思考过程，就是`right`还是一个数一个数的减下去的，所以在哪里减的都是一样的。所以这种去重 是可以不加的。 仅仅是 把去重的逻辑提前了而已。

<a name="pIIhA"></a>
# 18. 四数之和  
:::info
建议： 要比较一下，本题和 454.四数相加II 的区别，为什么 454.四数相加II 会简单很多，这个想明白了，对本题理解就深刻了。 本题 思路整体和 三数之和一样的，都是双指针，但写的时候 有很多小细节，需要注意，建议先看视频。 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0018.%E5%9B%9B%E6%95%B0%E4%B9%8B%E5%92%8C.html](https://programmercarl.com/0018.%E5%9B%9B%E6%95%B0%E4%B9%8B%E5%92%8C.html)
:::
可以联系 三数之和 ，主要再在意一下**剪枝和去重**。（ 可以看看与 454.四数相加II  题目的不同之初 ） <br />但是有一些细节需要注意，例如： 不要判断 `nums[k] > target` 就返回了，三数之和 可以通过 `nums[i] > 0` 就返回了，因为 0 已经是确定的数了，四数之和这道题目 `target`是任意值。比如：数组是 [-4, -3, -2, -1]，target是-10，不能因为-4 > -10而跳过。但是我们依旧可以去做**剪枝**，逻辑变成** **`**nums[i] > target && (nums[i] >=0 || target >= 0)**`就可以了。<br />四数之和的**双指针解法**是两层for循环 `nums[k] + nums[i]` 为确定值，依然是循环内有 `left和right` 下标作为双指针，找出 `**nums[k] + nums[i] + nums[left] + nums[right] == target**`** **的情况，<br />三数之和的时间复杂度是O(n^2)，四数之和的时间复杂度是O(n^3) 。<br />注意：题目中有四次去重操作
```java
/**
 * 双指针(头尾指针
 *
 * 关键在于排序和去重的思考
 * @param nums
 * @param target
 * @return
 */
public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);

    // 第一层 for 循环
    for (int i=0; i<nums.length; i++){
        // 如果最小值大于0则直接返回, 如果最小值大于目标值则直接返回. nums[i] > target 直接返回, 剪枝操作
        // 同时满足大于0,大于目标值是因为如果target是负数时, 和是最小的
        if (nums[i]>0 && nums[i]>target) return result;

        if (i>0 && nums[i-1]==nums[i]) continue;  // 对最左边的i进行去重, 第一次去重

        // 第二层 for 循环, 控制第二个数, 接下来的第三,四个数用双指针实现
        for (int j=i+1; j< nums.length; j++){
            if (j>i+1 && nums[j-1]==nums[j]) continue; // 对左边第二个数 nums[j] 进行去重,  第二次去重

            // 这里left起始为 j+1
            int left = j+1;
            int right = nums.length-1;
            while (right > left){
                // nums[k] + nums[i] + nums[left] + nums[right] > target 用int会溢出
                long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                    // 对nums[left]和nums[right]去重
                    while (right > left && nums[right] == nums[right - 1]) right--; // 第三次去重
                    while (right > left && nums[left] == nums[left + 1]) left++;  // 第四次去重

                    left++;
                    right--;
                }
            }
        }
    }
    return result;
}
```
[来自代码随想录](https://programmercarl.com/0018.%E5%9B%9B%E6%95%B0%E4%B9%8B%E5%92%8C.html)
<a name="R6F9N"></a>
## 补充
二级剪枝的部分：
```java
if (nums[k] + nums[i] > target && nums[k] + nums[i] >= 0) { break; }
```
可以优化为：
```java
if (nums[k] + nums[i] > target && nums[i] >= 0) { break; }
```
因为只要` nums[k] + nums[i] > target`，那么` nums[i]` 后面的数都是正数的话，就一定 不符合条件了。不过这种剪枝 其实有点 小绕，大家能够理解 文章给的完整代码的剪枝 就够了

