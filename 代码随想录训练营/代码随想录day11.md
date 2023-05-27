时间: 2023.5.20
<a name="sPFyY"></a>
# 今日任务
20. 有效的括号, 1047. 删除字符串中的所有相邻重复项, 150. 逆波兰表达式求值
<a name="CXkSh"></a>
# 收获

1. Deque对象的声明方式, 泛型的选择
2. Deque的 poll 和 pop 都是弹出
<a name="KOISD"></a>
# 明天计划
day11_Deque对象的声明继续复习, 各种操作的继续理解和记忆
<a name="XBsr0"></a>
# 复习

<a name="teGLP"></a>
# 20. 有效的括号 
:::info
讲完了栈实现队列，队列实现栈，接下来就是栈的经典应用了。 <br />大家先自己思考一下 有哪些不匹配的场景，在看视频 我讲的都有哪些场景，落实到代码其实就容易很多了。<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0020.%E6%9C%89%E6%95%88%E7%9A%84%E6%8B%AC%E5%8F%B7.html](https://programmercarl.com/0020.%E6%9C%89%E6%95%88%E7%9A%84%E6%8B%AC%E5%8F%B7.html)
:::
**括号匹配是使用栈解决的经典问题。**题意其实就像我们在写代码的过程中，要求括号的顺序是一样的，有左括号，相应的位置必须要有右括号。

> 如果还记得编译原理的话，编译器在 词法分析的过程中处理括号、花括号等这个符号的逻辑，也是使用了栈这种数据结构。
> 再举个例子，linux 系统中，`cd` 这个进入目录的命令我们应该再熟悉不过了。
> `cd a/b/c/../../`
> 这个命令最后进入a目录，系统是如何知道进入了a目录呢 ，这就是栈的应用（其实可以出一道相应的面试题了）, 所以栈在计算机领域中应用是非常广泛的。
> 有的同学经常会想学的这些数据结构有什么用，也开发不了什么软件，大多数同学说的软件应该都是可视化的软件例如APP、网站之类的，那都是非常上层的应用了，底层很多功能的实现都是基础的数据结构和算法。**所以数据结构与算法的应用往往隐藏在我们看不到的地方！**


这里我就不过多展开了，先来看题<br />由于栈结构的特殊性，非常适合做对称匹配类的题目。<br />首先要弄清楚，字符串里的括号不匹配有几种情况。**一些同学，在面试中看到这种题目上来就开始写代码，然后就越写越乱。**建议在写代码之前要分析好有哪几种不匹配的情况，如果不在动手之前分析好，写出的代码也会有很多问题。先来分析一下 这里有三种不匹配的情况，

1. 第一种情况，字符串里左方向的括号多余了 ，所以不匹配。

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1678930428227-c0198f79-8069-46e8-a17a-ae0e2d0f81da.png#averageHue=%23f8f6f6&clientId=udeeb46cf-87ef-4&from=paste&id=uf5a973b5&originHeight=136&originWidth=306&originalType=url&ratio=1.1699999570846558&rotation=0&showTitle=false&size=4911&status=done&style=none&taskId=u6d44a27a-d2b0-4b88-b22a-c9b68ee3a55&title=)

2. 第二种情况，括号没有多余，但是 括号的类型没有匹配上                     

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1678930446707-126355cc-0acb-4f08-9236-10641c32a78f.png#averageHue=%23f6f2f3&clientId=udeeb46cf-87ef-4&from=paste&id=u42b7eb2c&originHeight=122&originWidth=248&originalType=url&ratio=1.1699999570846558&rotation=0&showTitle=false&size=5059&status=done&style=none&taskId=uc17e90d3-cd15-47ac-99ff-e4e63763ccc&title=)

3. 第三种情况，字符串里右方向的括号多余了，所以不匹配。

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1678930487648-1e419b76-9c2c-4f81-a226-a86d1e05f2e8.png#averageHue=%23f5f3f3&clientId=udeeb46cf-87ef-4&from=paste&id=u460eca4e&originHeight=110&originWidth=330&originalType=url&ratio=1.1699999570846558&rotation=0&showTitle=false&size=5520&status=done&style=none&taskId=u8813f4d1-e341-4bc8-b1c8-f00f69691b7&title=)<br />我们的代码只要覆盖了这三种不匹配的情况，就不会出问题，可以看出 动手之前分析好题目的重要性。动画如下：<br />![](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1678930506263-c7598052-8063-48d0-8f10-6da3a25e384c.gif#averageHue=%23fdfdfd&clientId=udeeb46cf-87ef-4&from=paste&id=uc0ca5cc9&originHeight=670&originWidth=956&originalType=url&ratio=1.1699999570846558&rotation=0&showTitle=false&status=done&style=none&taskId=ub9bae50e-508a-476b-8153-cfd96df9f52&title=)<br />**第一种情况：**已经遍历完了字符串，但是栈不为空，说明有相应的左括号没有右括号来匹配，所以return false<br />**第二种情况**：遍历字符串匹配的过程中，发现栈里没有要匹配的字符。所以return false<br />**第三种情况**：遍历字符串匹配的过程中，栈已经为空了，没有匹配的字符了，说明右括号没有找到对应的左括号return false<br />那么什么时候说明左括号和右括号全都匹配了呢，就是字符串遍历完之后，栈是空的，就说明全都匹配了。<br />分析完之后，代码其实就比较好写了，<br />但还有一些技巧，**在匹配左括号的时候，右括号先入栈**，就只需要比较当前元素和栈顶相不相等就可以了，比左括号先入栈代码实现要简单的多了！
:::danger
注意: 

1. 只对左括号的情况进行入栈处理
2. Deque对象的声明, 泛型使用`Character`类型
:::
```java
public boolean isValid(String s) {
    Deque<Character> deque = new LinkedList<>();  // 注意Deque对象的声明, 泛型使用Character
    char ch;
    for (int i=0; i<s.length(); i++){
        ch = s.charAt(i);
        // 碰到 左括号, 就把相应的右括号入栈
        if (ch == '('){
            deque.push(')');
        }else if (ch == '{'){
            deque.push('}');
        }else if (ch == '['){
            deque.push(']');
        }else if (deque.isEmpty() || deque.peek() != ch){
            // 如果此时deque是空这说明进右括号的时候是空,则绝对返回false,
            // 如果deque.peek() != ch 则说明栈顶元素不相等, 绝对返回false
            return false;
        }else {
            //进到这里说明是右括号, 且有对应的左括号, 弹出就好了
            deque.pop();   // Deque的poll和pop都是弹出
        }
    }
    return deque.isEmpty(); // 最后判断栈中的元素是否匹配, 最后是空则就是都匹配完成, 返回true
}
```
来自代码随想录
<a name="Wt6Ph"></a>
# 1047. 删除字符串中的所有相邻重复项 
:::info
栈的经典应用。 <br />要知道栈为什么适合做这种类似于爱消除的操作，因为栈帮助我们记录了 遍历数组当前元素时候，前一个元素是什么。<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/1047.%E5%88%A0%E9%99%A4%E5%AD%97%E7%AC%A6%E4%B8%B2%E4%B8%AD%E7%9A%84%E6%89%80%E6%9C%89%E7%9B%B8%E9%82%BB%E9%87%8D%E5%A4%8D%E9%A1%B9.html](https://programmercarl.com/1047.%E5%88%A0%E9%99%A4%E5%AD%97%E7%AC%A6%E4%B8%B2%E4%B8%AD%E7%9A%84%E6%89%80%E6%9C%89%E7%9B%B8%E9%82%BB%E9%87%8D%E5%A4%8D%E9%A1%B9.html)
:::
本题也是用栈来解决的经典题目。<br />那么栈里应该放的是什么元素呢？<br />我们在删除相邻重复项的时候，其实就是要知道当前遍历的这个元素，我们在前一位是不是遍历过一样数值的元素，那么如何记录前面遍历过的元素呢？<br />所以就是**用栈来存放**，那么栈的目的，就是存放遍历过的元素，当遍历当前的这个元素的时候，去栈里看一下我们是不是遍历过相同数值的相邻元素。<br />然后再去做对应的消除操作。 如动画所示：<br />![](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1678936895460-9a5ce7a0-a6db-400e-b6a1-f6badd69b6a3.gif#averageHue=%23fdfdfd&clientId=uf82b815d-69f1-4&from=paste&id=u46a9e538&originHeight=354&originWidth=422&originalType=url&ratio=1.1699999570846558&rotation=0&showTitle=false&status=done&style=none&taskId=u1e0f9305-7fe1-40d2-8426-6a72df54f3b&title=)<br />从栈中弹出剩余元素，此时是字符串ac，因为从栈里弹出的元素是**倒序**的，所以再对字符串进行反转一下，就得到了最终的结果。
<a name="nCtog"></a>
## Deque
:::danger
关注怎么使用Deque最后返回字符串, 注意相加的顺序
:::
```java
public String removeDuplicates(String s) {
    Deque<Character> deque = new LinkedList<>();
    char ch;
    for (int i=0; i<s.length(); i++){
        ch = s.charAt(i);
        if (deque.isEmpty() || deque.peek()!=ch){
            deque.push(ch);
        }else if (deque.peek() == ch){
            deque.pop();
        }
    }
    String res = "";
    while (!deque.isEmpty()){  // 不是空就一直加长字符串
        res = deque.pop() + res; // 注意这里 相加 的顺序
    }
    return res;
}
```
<a name="CqNDV"></a>
## StringBuilder
当然可以拿字符串直接作为栈，这样省去了栈还要转为字符串的操作。
:::danger
注意: StringBuilder的应用
:::
```java
class Solution {
    public String removeDuplicates(String s) {
        // 将 res 当做栈
        // 也可以用 StringBuilder 来修改字符串，速度更快
        // StringBuilder res = new StringBuilder();
        StringBuffer res = new StringBuffer();
        // top为 res 的长度
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 当 top > 0,即栈中有字符时，当前字符如果和栈中字符相等，弹出栈顶字符，同时 top--
            if (top >= 0 && res.charAt(top) == c) {
                res.deleteCharAt(top);
                top--;
            // 否则，将该字符 入栈，同时top++
            } else {
                res.append(c);
                top++;
            }
        }
        return res.toString();
    }
}
```
时间复杂度: O(n), 其中 n 是字符串的长度。我们只需要遍历该字符串一次。<br />空间复杂度: O(n) 或 O(1)，取决于使用的语言提供的字符串类是否提供了类似「入栈」和「出栈」的接口。注意返回值不计入空间复杂度。
<a name="pcnZZ"></a>
## 双指针
:::danger
注意: 

1. `toCharArray()`方法的使用
2. [String(byte[] bytes, int offset, int length)的参数问题](https://blog.csdn.net/weixin_43691058/article/details/104110100)
:::
![4b8c8586f3a740faccbc378b78d8581.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1684688910217-7c904c47-82c5-4d10-bdd6-8ccf979ee8b6.png#averageHue=%23f4f3e6&clientId=u1f5d418e-a5d1-4&from=paste&height=2418&id=u03e55aab&originHeight=1964&originWidth=1364&originalType=binary&ratio=1.1699999570846558&rotation=90&showTitle=false&size=293645&status=done&style=none&taskId=ua8db4111-c166-4fd6-9a93-3890916d13b&title=&width=1679)
```java
class Solution {
    public String removeDuplicates(String s) {
        char[] ch = s.toCharArray();
        int fast = 0;
        int slow = 0;
        while(fast < s.length()){
            // 直接用fast指针覆盖slow指针的值
            ch[slow] = ch[fast];
            // 遇到前后相同值的，就跳过，即slow指针后退一步，下次循环就可以直接被覆盖掉了
            if(slow > 0 && ch[slow] == ch[slow - 1]){
                slow--;
            }else{
                slow++;
            }
            fast++;
        }
        return new String(ch,0,slow);
    }
}
```
<a name="MwjaO"></a>
# 150. 逆波兰表达式求值 
:::info
本题不难，但第一次做的话，会很难想到，所以先看视频，了解思路再去做题 <br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0150.%E9%80%86%E6%B3%A2%E5%85%B0%E8%A1%A8%E8%BE%BE%E5%BC%8F%E6%B1%82%E5%80%BC.html](https://programmercarl.com/0150.%E9%80%86%E6%B3%A2%E5%85%B0%E8%A1%A8%E8%BE%BE%E5%BC%8F%E6%B1%82%E5%80%BC.html)
:::
<a name="Clx5N"></a>
## 逆波兰表达式介绍
逆波兰表达式是一种**后缀表达式**，所谓后缀就是指算符写在后面。

- 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
- 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。

逆波兰表达式主要有以下两个优点：

- 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
- **适合用栈操作运算**：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中
<a name="KXAAG"></a>
## 题解
来看一下本题，**其实逆波兰表达式相当于是二叉树中的后序遍历**。 大家可以把运算符作为中间节点，按照后序遍历的规则画出一个二叉树。<br />但我们没有必要从二叉树的角度去解决这个问题，只要知道逆波兰表达式是用后序遍历的方式把二叉树序列化了，就可以了。<br />![150.逆波兰表达式求值.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1685165203298-fc97715c-6120-469d-8c07-6699b0bf2cf5.gif#averageHue=%23fcfcfc&clientId=u84e9280a-7839-4&from=paste&height=321&id=u0128430a&originHeight=376&originWidth=444&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=1444756&status=done&style=none&taskId=u499bc79b-d1d2-4dfc-a95a-060d9b71aa1&title=&width=379.4871934066868)
```java
public int evalRPN(String[] tokens) {
    Deque<Integer> stack = new LinkedList<>();

    for (String token : tokens) {
        if ("+".equals(token)){  // leetcode 内置jdk的问题，不能使用==判断字符串是否相等
            stack.push(stack.pop() + stack.pop());
        }else if ("-".equals(token)){
            stack.push(-stack.pop() + stack.pop());  // 注意 - 和/ 需要特殊处理
        }else if ("*".equals(token)){
            stack.push(stack.pop() * stack.pop());
        }else if ("/".equals(token)){
            int temp1 = stack.pop();  // 转换一下数据类型
            int temp2 = stack.pop();
            stack.push(temp2 / temp1);  // 注意这里除法的顺序
        }else {
            stack.push(Integer.valueOf(token));
        }
    }
    return stack.pop();
}
```

