时间: 2023.5.17 
<a name="sPFyY"></a>
# 今日任务
344.反转字符串, 541. 反转字符串II, 剑指Offer 05.替换空格, 151.翻转字符串里的单词, 剑指Offer58-II.左旋转字符串

<a name="CXkSh"></a>
# 收获

1. 位运算
2. `substring()`, `reverse()`函数的使用方法和注意点
3. StringBuilder 的用法, 和 StringBuilder类型转String
4. 151.翻转字符串里的单词  有一个方法中使用到了`Deque`类型
<a name="KOISD"></a>
# 明天计划
位运算再学学?
<a name="XBsr0"></a>
# 复习

- [ ] 重点复习 day07_15.三数之和 
- [ ] 重点复习 day07_8.四数之和 

<a name="KLPhv"></a>
# 344.反转字符串 
:::info
建议： 本题是字符串基础题目，就是考察 reverse 函数的实现，同时也明确一下 平时刷题什么时候用 库函数，什么时候 不用库函数 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0344.%E5%8F%8D%E8%BD%AC%E5%AD%97%E7%AC%A6%E4%B8%B2.html](https://programmercarl.com/0344.%E5%8F%8D%E8%BD%AC%E5%AD%97%E7%AC%A6%E4%B8%B2.html)
:::
<a name="Hnpdc"></a>
## 双指针
```java
/**
 * 双指针(头尾指针
 * @param s
 */
public void reverseString(char[] s) {
    int i=0, j=s.length-1;

    while (i < j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
        i++;
        j--;
    }
}
```
<a name="xDWJP"></a>
## 位运算
位异或运算符<br />位异或运算符为`^`，<br />其运算规则是：参与运算的数字，低位对齐，高位不足的补零，

- 如果对应的二进制位相同（同时为 0 或同时为 1）时，结果为 0；
- 如果对应的二进制位不相同，结果则为 1。

下面是一个使用位异或运算符的表达式。<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1684341545444-b0765950-c3ae-4474-bbbe-3b2ffdb870ea.png#averageHue=%2325292b&clientId=u0552a628-b78f-4&from=paste&height=313&id=ud3428900&originHeight=366&originWidth=1123&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=61068&status=done&style=shadow&taskId=u2d75cd62-2743-4759-a69f-e67cb39837a&title=&width=959.8290950353812)<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1684342069374-ebaf7ba1-0356-410a-a3ff-2cfe549d40bb.png#averageHue=%23151515&clientId=u0552a628-b78f-4&from=paste&height=337&id=ued62cb46&originHeight=394&originWidth=1068&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=39424&status=done&style=shadow&taskId=u90b5495b-a70c-4afe-92d6-ccc8bdbf31c&title=&width=912.820546302571)
```java
/**
 * 位运算
 * @param s
 */
public void reverseString2(char[] s) {
    int l = 0;
    int r = s.length - 1;
    while (l < r) {
        s[l] ^= s[r];  //构造 a ^ b 的结果，并放在 a 中
        s[r] ^= s[l];  //将 a ^ b 这一结果再 ^ b ，存入b中，此时 b = a, a = a ^ b
        s[l] ^= s[r];  //a ^ b 的结果再 ^ a ，存入 a 中，此时 b = a, a = b 完成交换
        l++;
        r--;
    }
}
```

<a name="D0R9l"></a>
# 541. 反转字符串II
:::info
建议：本题又进阶了，自己先去独立做一做，然后在看题解，对代码技巧会有很深的体会。 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0541.%E5%8F%8D%E8%BD%AC%E5%AD%97%E7%AC%A6%E4%B8%B2II.html](https://programmercarl.com/0541.%E5%8F%8D%E8%BD%AC%E5%AD%97%E7%AC%A6%E4%B8%B2II.html)
:::
可以对比[反转字符串](https://leetcode.cn/problems/reverse-string/)。<br />一些同学可能为了处理逻辑：每隔 `2k` 个字符的前 `k` 的字符，写了一堆逻辑代码或者再搞一个计数器，来统计 `2k`，再统计前 `k` 个字符。<br />其实在遍历字符串的过程中，只要让 `i += (2 * k)`，`i` 每次移动 `2 * k` 就可以了，**然后判断是否需要有反转的区间**。<br />因为要找的也就是每 `2 * k` 区间的起点，这样写，程序会高效很多<br />所以当需要固定规律一段一段去处理字符串的时候，要想想在在 for 循环的表达式上做做文章。

注意: 题目的意思其实概括为 每隔 2k 个反转前 k 个，尾数不够 k 个时候全部反转
<a name="d1tau"></a>
## 解法一
:::danger
`substring()`, `reverse()`函数的使用方法和注意点
:::
```java
/**
 * 解法一
 * 注意本方法中substring(),reverse()的使用
 * @param s
 * @param k
 * @return
 */
public String reverseStr1(String s, int k) {
    StringBuffer res = new StringBuffer();
    int len = s.length();
    int start = 0;

    while (start < len){
        //找到k处和2k处
        StringBuffer temp = new StringBuffer();
        //与length进行判断，如果大于len了，那就将其置为len
        int fitstK = (start + k > len) ? len :start+k;
        int secondK = (start + (2*k) >len) ? len :start+(2*k);

        // 无论start所处位置，至少会反转一次
        temp.append(s.substring(start, fitstK));  // substring() 返回start到firstK字符，左闭右开
        res.append(temp.reverse());

        // 如果firstK到secondK之间有元素，这些元素直接放入res即可。
        if (fitstK < secondK){
            res.append(s.substring(fitstK, secondK));  //前面append的是反转后的，这里再取出后面没反转的那部分加到结果字符串中
        }
        start += (2 * k);//注意这里每次循环加2k
    }
    return res.toString();
}
```
<a name="Qat1r"></a>
## 解法二
注意最后的返回字符串的方法, 直接`new String(字符数组)`
```java
/**
 * 解法二（应该是更好理解
 * 注意本方法中s.toCharArray(),异或运算的反转
 * @param s
 * @param k
 * @return
 */
public String reverseStr2(String s, int k) {
    char[] ch = s.toCharArray();//字符串转为数组
    for (int i=0; i<ch.length; i += 2*k){
        int start = i;
        //这里是判断尾数够不够k个来取决end指针的位置
        int end = Math.min(ch.length - 1, start + k-1);//这一步的方法很重要
        //用异或运算反转
        while (start < end){
            ch[start] ^= ch[end];
            ch[end] ^= ch[start];
            ch[start] ^= ch[end];
            start++;
            end--;
        }
    }
    return new String(ch);
}


/**
 * 解法二的变形
 * 用temp来交换数值
 */
public String reverseStr(String s, int k) {
    char[] chars = s.toCharArray(); // 将字符串转为数组
    System.out.println("看看字符串转为数组后是什么样的 : "+ Arrays.toString(chars));

    for (int i=0; i<chars.length; i+=2*k){  // 注意这里i的变化方式
        int start = i;
        // 判断尾数够不够k个来取决end指针的位置
        int end = Math.min(chars.length-1, start+k-1); // 取小值, 比如+k后的长度大于了原来的字符串长度, 则反转的最后位置就按照字符串的长度全部反转
        while (start < end){
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;

            start++;
            end--;
        }
    }
    return new String(chars);
}
```
来自：代码随想录

<a name="jkfpD"></a>
# 剑指Offer 05.替换空格 
:::info
建议：对于线性数据结构，填充或者删除，后序处理会高效的多。好好体会一下。<br />题目链接/文章讲解：[https://programmercarl.com/%E5%89%91%E6%8C%87Offer05.%E6%9B%BF%E6%8D%A2%E7%A9%BA%E6%A0%BC.html](https://programmercarl.com/%E5%89%91%E6%8C%87Offer05.%E6%9B%BF%E6%8D%A2%E7%A9%BA%E6%A0%BC.html)
:::
本题重点关注双指针法，注意StringBuilder的使用<br />如果想把这道题目做到极致，就不要只用额外的辅助空间了！<br />首先扩充数组到每个空格替换成 "%20" 之后的大小。, 然后从后向前替换空格，也就是双指针法，过程如下：<br />`i` 指向新长度的末尾，`j` 指向旧长度的末尾。<br />![替换空格.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1684343348879-4a2e02f4-3e41-41df-96a2-6bd9f780093d.gif#averageHue=%23fcfcfc&clientId=u551b088f-e03b-4&from=paste&height=296&id=u8fe07522&originHeight=346&originWidth=498&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=1198660&status=done&style=shadow&taskId=u6b9ee7cb-3e4e-4b07-b9aa-6c09bbdfc46&title=&width=425.64104125344596)<br />有同学问了，为什么要从后向前填充，从前向后填充不行么？<br />从前向后填充就是`O(n^2)`的算法了，因为每次添加元素都要将添加元素之后的所有元素向后移动。, 其实很多数组填充类的问题，都可以先预先给数组扩容带填充后的大小，然后在从后向前进行操作。<br />这么做有两个**好处**：

- 不用申请新数组。
- 从后向前填充元素，避免了从前向后填充元素时，每次添加元素都要将添加元素之后的所有元素向后移动的问题。
<a name="EhFGR"></a>
## 暴力
StringBuilder的用法, 和 StringBuilder类型转String
```java
/**
 * 方法一
 * 使用一个新的对象，复制 str，复制的过程对其判断，是空格则替换，否则直接复制，类似于数组复制
 *
 * 注意StringBuilder的使用
 * @param s
 * @return
 */
public String replaceSpace(String s) {
    if (s == null) return null;

    // 选用StringBuilder单线程使用，比较快，选不选都行
    StringBuilder sb = new StringBuilder();

    // 使用sb逐个复制s，碰到空格则替换，否则直接复制
    for (int i=0; i<s.length(); i++){
        // s.charAt(i) 为char类型，为了比较需要将其转为和 “ ” 相同的字符串类型
        // if("".equals(String.valueOf(s.charAt(i))){}
        if (s.charAt(i) == ' '){
            sb.append("%20");
        } else {
            sb.append(s.charAt(i));
        }
    }
    return sb.toString();
}
```
<a name="xHCbe"></a>
## 双指针
本题的双指针为 : 倒着的快慢指针
:::danger
注意: 

1. StringBuilder类型的用法
2. 最后`char[]`返回为字符串的方式
:::

```java
/**
 * 方法二
 * 双指针法，倒着的快慢指针
 * @param s
 * @return
 */
public String replaceSpace2(String s) {
    if(s == null || s.length() == 0){
        return s;
    }
    // 扩充空间，空格数量2倍
    StringBuilder str = new StringBuilder();
    for (int i=0; i<s.length(); i++){
        if (s.charAt(i) == ' '){
            str.append("  "); // 这里是两个空格,考虑为啥是加两个空格，因为原来的一个空格替换为%20多了两个字符，所以加上两个空格的位置备用
        }
    }
    // 若是没有空格则直接返回
    if (str.length() == 0){
        return s;
    }

    // 有空格的情况，定义两个指针
    int left = s.length() - 1; // 左指针：指向原始字符串最后一个位置
    s += str.toString(); // 这一步为对原始字符串的拓展,   很重要
    int right = s.length() -1; //右指针：指向扩展字符串的最后一个位置

    char[] chars = s.toCharArray(); // 注意这个方法的使用

    while (left >= 0){
        if (chars[left] == ' '){
            chars[right--] = '0';  // 先做了赋值再减减
            chars[right--] = '2';
            chars[right] = '%';  // 最后一个位置的填充不减减
        }else {
            chars[right] = chars[left];
        }
        left--;
        right--;
    }
    return new String(chars);
}
```
.
<a name="gxhIx"></a>
# 151.翻转字符串里的单词 
:::info
建议：这道题目基本把 刚刚做过的字符串操作 都覆盖了，不过就算知道解题思路，本题代码并不容易写，要多练一练。 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0151.%E7%BF%BB%E8%BD%AC%E5%AD%97%E7%AC%A6%E4%B8%B2%E9%87%8C%E7%9A%84%E5%8D%95%E8%AF%8D.html](https://programmercarl.com/0151.%E7%BF%BB%E8%BD%AC%E5%AD%97%E7%AC%A6%E4%B8%B2%E9%87%8C%E7%9A%84%E5%8D%95%E8%AF%8D.html)
:::
<a name="mYTPL"></a>
## 随想录题解
这道题目可以说是综合考察了字符串的多种操作。<br />一些同学会使用`split`库函数，分隔单词，然后定义一个新的 string 字符串，最后再把单词倒序相加，那么这道题题目就是一道水题了，失去了它的意义。<br />所以这里我还是提高一下本题的难度：不要使用辅助空间，空间复杂度要求为`O(1)`。<br />不能使用辅助空间之后，那么只能在原字符串上下功夫了。<br />想一下，我们将整个字符串都反转过来，那么单词的顺序指定是倒序了，只不过单词本身也倒序了，那么再把单词反转一下，单词不就正过来了。<br />所以解题思路如下：

- 移除多余空格
- 将整个字符串反转
- 将每个单词反转

举个例子，源字符串为："the sky is blue "

- 移除多余空格 : "the sky is blue"
- 字符串反转："eulb si yks eht"
- 单词反转："blue is sky the"

这样我们就完成了翻转字符串里的单词。
```java
/**
 * 解法一: 不使用Java内置的方法实现
 *
 * 步骤:
 * 1.去除首尾以及中间多余空格
 * 2.反转整个字符串
 * 3.反转各个单词
 * @param s
 * @return
 */
public String reverseWords(String s) {
    System.out.println("待反转的的字符串为: " + s);
    // 1.去除首位及中间多余空格
    StringBuilder sb = removeSpace(s); // 调用自定义的方法
    // 2.反转整个字符串
    reverseString(sb, 0, sb.length()-1); // 调用自定义的方法
    System.out.println("反转整个字符串后: " + sb);
    // 3.反转每个单独的单词
    reverseEachWord(sb);  // 调用自定义的方法
    System.out.println("处理完后的字符串为: "+ sb.toString());
    return sb.toString();

}
// 删除首尾的空格方法
private StringBuilder removeSpace(String s){
    int start = 0;
    int end = s.length()-1;

    while (s.charAt(start) == ' ') start++;  // 前面有空格就++跳过
    while (s.charAt(end) == ' ') end--;  // 后面有空格就--跳过

    // 清除完前后的空格，再来执行下面
    StringBuilder sb = new StringBuilder();
    while (start <= end){
        char c = s.charAt(start);
        // 这里是清除字符串中间多余的空格，思考这一步. 假设有两个单词直接为两个空格的话,start 索引到达第二个的空格,
        // 就意味着有一个空格已经被 append 到了sb中, 则此时 sb 的最后一位也是空格, 不满足进入 if 内部的条件, 直接下一步 start++ 跳过了多余的空格
        if (c != ' ' || sb.charAt(sb.length()-1)!=' '){
            sb.append(c);
        }
        start++;
    }
    System.out.println("清除完多余空格后: " + sb);
    return sb;
}
// 反转整个字符串方法  , 使用头尾指针的方法
private void reverseString(StringBuilder sb, int start, int end){ //反转字符串指定区间[start, end]的字符
    while (start < end){
        char temp = sb.charAt(start); // 暂存一下第一个
        sb.setCharAt(start, sb.charAt(end));  // 将最后一个放到前面
        sb.setCharAt(end, temp);  // 将之前暂存的第一个放到后面
        start++;
        end--;
    }
}
// 反转各个单词方法 , 快慢指针
private void reverseEachWord(StringBuilder sb){
    int start = 0;  // 慢指针
    int end = 1;  // 快指针
    int n = sb.length();
    while (start < n){
        while (end<n && sb.charAt(end)!=' '){  // 快指针向前走, 遇到空格则说明到达一个单词的边界
            end++;
        }
        reverseString(sb, start, end-1);  // 注意这一步很重要，调用自定义的反转字符方法
        start = end + 1; // 注意这两步的顺序
        end = start + 1;
    }
}
```
```java
/**
 * 解法二
 *
 * 创建新字符数组填充。时间复杂度O(n)
 * @param s
 * @return
 */
public String reverseWords2(String s) {
    //源字符数组
    char[] initialArr = s.toCharArray();
    //新字符数组
    char[] newArr = new char[initialArr.length+1];//下面循环添加"单词 "，最终末尾的空格不会返回
    int newArrPos = 0;//作为新字符数组的索引

    //i来进行整体对源字符数组从后往前遍历
    int i = initialArr.length-1;
    while (i >= 0){
        while (i>=0 && initialArr[i]==' ') i--;//跳过空格

        //此时i位置是边界或!=空格，先记录当前索引，之后的while用来确定单词的首字母的位置
        int right = i;
        while(i>=0 && initialArr[i] != ' ') i--;

        //指定区间单词取出(由于i为首字母的前一位，所以这里+1,)，取出的每组末尾都带有一个空格.由上面的逻辑来看i的位置已经是空格了，所以要+1才能取到字母
        for (int j=i+1; j<=right; j++){
            newArr[newArrPos++] = initialArr[j];
            if (j == right){
                newArr[newArrPos++] = ' ';//空格
            }
        }
    }

    //若是原始字符串没有单词，直接返回空字符串；若是有单词，返回0-末尾空格索引前范围的字符数组(转成String返回)
    if(newArrPos == 0){
        return "";
    }else{
        return new String(newArr,0,newArrPos-1);//注意这个方法的使用
    }
}
```
<a name="YcfXV"></a>
## 双端队列法
官方题解<br />由于**双端队列**支持从队列**头部插入**的方法，因此我们可以沿着字符串一个一个单词处理，然后将单词压入队列的头部，再将队列转成字符串即可。<br />![](https://cdn.nlark.com/yuque/0/2023/png/32832913/1676022611603-8e27890d-a4c6-4593-a2c7-e12e83e0b58c.png#averageHue=%23f8f3e5&clientId=u5eb9b32e-0310-4&from=paste&id=u7a4df73e&originHeight=880&originWidth=1688&originalType=url&ratio=1.1699999570846558&rotation=0&showTitle=false&status=done&style=none&taskId=ufca9903e-fdc0-43ed-9e2c-82d376f5db4&title=)
:::danger
注意:

1. 下面代码中队列的使用：Deque<String> d = new ArrayDeque<String>();
   1. offerFirst() 方法将指定元素插入此列表的前面![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1684515269836-b069cf9e-2bc7-4412-8546-3ab537fa6d2d.png#averageHue=%23303336&clientId=u6c5d8526-5173-4&from=paste&height=626&id=ua4e58e81&originHeight=733&originWidth=1203&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=18832&status=done&style=none&taskId=u8ed1a11e-2749-4ebe-a483-9d5f9cb0e13&title=&width=1028.205165919469)
2. String.join() 方法(Java8新方法), 连接给定的字符串并返回连接的字符串。为了同样的目的，Java 8 还引入了一个新的StringJoiner类。

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1684515377876-a816b4b2-2d6d-431b-b526-27f51e213afe.png#averageHue=%232d3133&clientId=u6c5d8526-5173-4&from=paste&height=455&id=u02e36890&originHeight=532&originWidth=1078&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=41948&status=done&style=none&taskId=u5c148ca7-34aa-4b63-9d16-2341d30dbb4&title=&width=921.3675551630819)
:::
```java
/**
 * 双端队列法
 * 使用了 Deque 类型
 * @param s
 * @return
 */
public String reverseWords2(String s) {
    int left=0, right=s.length()-1;

    // 去掉字符串开头的空格
    while (left<=right && s.charAt(left)==' '){
        left++;
    }
    // 去掉字符串末尾的空白字符
    while (left<=right && s.charAt(right)==' '){
        right--;
    }

    Deque<String> dq = new ArrayDeque<>();  // 注意学习队列的用法
    StringBuilder sb = new StringBuilder();

    while (left<=right){  // 注意这里要有等于号
        char temp = s.charAt(left);
        if ((sb.length()!=0) && (temp==' ')){  // sb长度不为零, temp遇到空格, 说明到了一个单词的边界分隔处
            // 将单词 push 到队列的头部
            dq.offerFirst(sb.toString());
            sb.setLength(0);  // 重新将sb长度设置为0, 进行下一个单词的操作
        } else if (temp != ' '){
            sb.append(temp);
        }
        left++;
    }
    dq.offerFirst(sb.toString());  // 这一步是将最后一个单词加入到队列, 因为到最后时没有空格进入不了第一个if判断来进行加入

    return String.join(" ", dq);  // 注意这个方法的使用
}

作者：LeetCode-Solution
链接：https://leetcode.cn/problems/reverse-words-in-a-string/solution/fan-zhuan-zi-fu-chuan-li-de-dan-ci-by-leetcode-sol/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```

<a name="T8CmK"></a>
# 剑指Offer58-II.左旋转字符串 
:::info
建议：题解中的解法如果没接触过的话，应该会想不到<br />题目链接/文章讲解：[https://programmercarl.com/%E5%89%91%E6%8C%87Offer58-II.%E5%B7%A6%E6%97%8B%E8%BD%AC%E5%AD%97%E7%AC%A6%E4%B8%B2.html](https://programmercarl.com/%E5%89%91%E6%8C%87Offer58-II.%E5%B7%A6%E6%97%8B%E8%BD%AC%E5%AD%97%E7%AC%A6%E4%B8%B2.html)
:::
```java
/**
 * 使用StringBuilder申请额外的空间
 * @param s
 * @param n
 * @return
 */
public String reverseLeftWords(String s, int n) {
    int slow=0, fast = n;
    StringBuilder sb = new StringBuilder();
    while (fast < s.length()){
        sb.append(s.charAt(fast));
        fast++;
    }

    while (slow < n){
        sb.append(s.charAt(slow));
        slow++;
    }
    return sb.toString();
}


```
```java
/**
 * 申请额外的空间
 * 1. 反转区间为前 n 的子串
 * 2. 反转区间为 n 到末尾的子串
 * 3. 反转整个字符串
 * 4. 最后就可以达到左旋 n 的目的，而不用定义新的字符串，完全在本串上操作。
 * @param s
 * @param n
 * @return
 */
public String reverseLeftWords2(String s, int n) {
    int len = s.length();
    StringBuilder sb = new StringBuilder(s);
    reverseString(sb, 0, n-1); // 反转前面的区间
    reverseString(sb,n,len-1);  // 反转后面的区间
    return sb.reverse().toString();  // 先反转, 再转成字符串
}
private void reverseString(StringBuilder sb, int start, int end){
    while (start < end){
        char temp = sb.charAt(start);
        sb.setCharAt(start, sb.charAt(end));
        sb.setCharAt(end, temp);
        start++;
        end--;
    }
}
```
```java
/**
 * 解法三  不使用额外空间
 * 空间复杂度：O(1)。用原始数组来进行反转操作
 *
 * // 思路为：先整个字符串反转，再反转前面的，最后反转后面 n 个
 * @param s
 * @param n
 * @return
 */
public String reverseLeftWords3(String s, int n) {
    char[] chars = s.toCharArray();
    reverseAA(chars, 0, chars.length - 1);
    reverseAA(chars, 0, chars.length - 1 - n);
    reverseAA(chars, chars.length - n, chars.length - 1);
    return new String(chars);
}
private void reverseAA(char[] chars, int left, int right) {
    while (left < right) {
        chars[left] ^= chars[right];
        chars[right] ^= chars[left];
        chars[left] ^= chars[right];
        left++;
        right--;
    }
}
```
