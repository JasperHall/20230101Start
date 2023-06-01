- 想法
   - 自己看到题目的第一想法
   - 看完代码随想录之后的想法 
- 实现过程中遇到哪些困难 
- 今日收获, 记录一下自己的学习时长

数组理论基础，704. 二分查找，27. 移除元素

<a name="Lfdgl"></a>
# 收获

- 复习二分查找的使用方法
- 理解数组的存储方式
<a name="jVHVS"></a>
# 计划

1. 二分查找的不同模板,在笔记里写了, 加深理解不同的模板在什么时候使用
2. 二分查找多做几道题, 下面有推荐
3. 找资料理解二分法的边界问题

<a name="vW2u4"></a>
# 数组理论基础  
文章链接：[https://programmercarl.com/%E6%95%B0%E7%BB%84%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html](https://programmercarl.com/%E6%95%B0%E7%BB%84%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)
<a name="TfPTU"></a>
#  704.二分查找
:::info
**题目建议**： 大家能把 704 掌握就可以，35.搜索插入位置 和 34. 在排序数组中查找元素的第一个和最后一个位置 ，如果有时间就去看一下，没时间可以先不看，二刷的时候在看。<br />先把 704写熟练，要**熟悉 根据 左闭右开，左闭右闭 两种区间规则 写出来的二分法**。<br />题目链接：[https://leetcode.cn/problems/binary-search/](https://leetcode.cn/problems/binary-search/)<br />文章讲解：[https://programmercarl.com/0704.%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE.html](https://programmercarl.com/0704.%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE.html)<br />视频讲解：[https://www.bilibili.com/video/BV1fA4y1o715](https://www.bilibili.com/video/BV1fA4y1o715)
:::
```java
public int search(int[] nums, int target) {
    int left = 0, right = nums.length-1;
    while (left <= right){
        int mid = (right-left)/2 + left;
        int num = nums[mid];
        if (num == target){
            return mid;
        } else if (num > target){
            right = mid -1;
        } else {
            left = mid + 1;
        }
    }
    return -1;
}
```
<a name="eReoE"></a>
# 27.移除元素
:::info
**题目建议**：  暴力的解法，可以锻炼一下我们的代码实现能力，建议先把暴力写法写一遍。 **双指针法 是本题的精髓，今日需要掌握**，至于拓展题目可以先不看。 <br />题目链接：[https://leetcode.cn/problems/remove-element/](https://leetcode.cn/problems/remove-element/)<br />文章讲解：[https://programmercarl.com/0027.%E7%A7%BB%E9%99%A4%E5%85%83%E7%B4%A0.html](https://programmercarl.com/0027.%E7%A7%BB%E9%99%A4%E5%85%83%E7%B4%A0.html)<br />视频讲解：[https://www.bilibili.com/video/BV12A4y1Z7LP](https://www.bilibili.com/video/BV12A4y1Z7LP)
:::
<a name="PfvTr"></a>
## 暴力
暴力解法就两层for循环, 一个for循环遍历数组元素 ，第二个for循环更新数组
```java
/**
 * 暴力
 * @param nums
 * @param val
 * @return
 */
public int removeElement(int[] nums, int val) {
    int len = nums.length;
    for (int i=0; i<len; i++){
        if (nums[i] == val){
            for (int j=i+1; j<len; j++){
                nums[j-1] = nums[j];
            }
            i--; // 因为下标i以后的数值都向前移动了一位，所以i也向前移动一位
            len--; // 此时数组的大小-1
        }
    }
    return len;
}
```
很明显暴力解法的时间复杂度是 O(n^2) ，这道题目暴力解法在leetcode上是可以过的。
<a name="KRuYP"></a>
## 双指针法
快慢指针法

- 快指针: 寻找新数组的元素, 新数组就是不含有目标元素的数组
- 慢指针: 指向更新 新数组下标的位置

![27.移除元素-双指针法.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1684029975266-93fb954f-0368-4868-a36b-3f2370ffc5de.gif#averageHue=%23fafafa&clientId=u3432a853-b6bd-4&from=paste&height=296&id=ud9a327a6&originHeight=346&originWidth=498&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=1798163&status=done&style=none&taskId=u4df4028e-8f48-462f-aea6-57b508d7d11&title=&width=425.64104125344596)
```java
/**
 * 快慢指针法
 * @param nums
 * @param val
 * @return
 */
public int removeElement2(int[] nums, int val) {
    int slowIndex = 0;
    for (int fastIndex = 0; fastIndex<nums.length; fastIndex++){
        if (nums[fastIndex] != val){  // 如果不等于就赋值过来, 如果等于就不赋值, 相当于跳过了那个相等的元素
            nums[slowIndex] = nums[fastIndex];  // 赋值操作
            slowIndex++;
        }
    }
    return slowIndex;
}

```
```java
/**
 * 相向指针
 * @param nums
 * @param val
 * @return
 */
public int removeElement3(int[] nums, int val) {
    
    int left = 0;
    int right = nums.length - 1;
    
    while(right >= 0 && nums[right] == val) right--; //将right移到从右数第一个值不为val的位置  直接去除右侧相等的边缘元素
    
    while(left <= right) {
        if(nums[left] == val) { //left位置的元素需要移除
            //将right位置的元素移到left（覆盖），right位置移除
            nums[left] = nums[right];
            right--;
        }
        left++;
        while(right >= 0 && nums[right] == val) right--;
    }
    return left;
}
```
