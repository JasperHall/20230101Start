时间: 2023.5.19
<a name="sPFyY"></a>
# 今日任务
理论基础, 232.用栈实现队列, 225. 用队列实现栈
<a name="CXkSh"></a>
# 收获

1. 栈与队列印象加深
<a name="KOISD"></a>
# 明天计划
<a name="XBsr0"></a>
# 复习

- [ ] day09_继续理解KMP算法
- [ ] 做day09的 459.重复的子字符串
<a name="LqV3z"></a>
# 理论基础 
:::info
了解一下 栈与队列的内部实现机智，文中是以C++为例讲解的。 <br />文章讲解：[https://programmercarl.com/%E6%A0%88%E4%B8%8E%E9%98%9F%E5%88%97%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html](https://programmercarl.com/%E6%A0%88%E4%B8%8E%E9%98%9F%E5%88%97%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html)
:::

<a name="kdF0X"></a>
# 232.用栈实现队列 
:::info
大家可以先看视频，了解一下模拟的过程，然后写代码会轻松很多。<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0232.%E7%94%A8%E6%A0%88%E5%AE%9E%E7%8E%B0%E9%98%9F%E5%88%97.html](https://programmercarl.com/0232.%E7%94%A8%E6%A0%88%E5%AE%9E%E7%8E%B0%E9%98%9F%E5%88%97.html)
:::
使用栈来模式队列的行为，如果仅仅用一个栈，是一定不行的，所以需要两个栈**一个输入栈，一个输出栈**，**这里要注意输入栈和输出栈的关系。**<br />在 push 数据的时候，只要数据**放进输入栈**就好，**但在pop的时候，操作就复杂一些，输出栈如果为空，就把进栈数据全部导入进来（注意是全部导入）**，再从出栈弹出数据，**如果输出栈不为空**，则直接从出栈弹出数据就可以了。<br />最后如何判断队列为空呢？**如果进栈和出栈都为空的话，说明模拟的队列为空了。**<br />在代码实现的时候，会发现`pop()`和`peek()`两个函数功能类似，代码实现上也是类似的，可以思考一下如何把代码抽象一下。
```java
class MyQueue {

    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public MyQueue() {
        stackIn = new Stack<>(); // 负责模拟入队
        stackOut = new Stack<>();  // 负责模拟出队
    }

    /**
     * 模拟入队
     * Push element x to the back of queue.
     * @param x
     */
    public void push(int x) {
        stackIn.push(x);
    }

    /**
     * 模拟出队
     * Removes the element from in front of queue and returns that element.
     * @return
     */
    public int pop() {
        dumpstackIn();  // 调用自定义的方法
        return stackOut.pop();
    }

    /**
     * 模拟获取队尾元素
     * Get the front element.
     * @return
     */
    public int peek() {
        dumpstackIn(); // 调用自定义的方法
        return stackOut.peek();
    }

    /**
     * 判断是否为空
     * Returns whether the queue is empty.
     * @return
     */
    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    // 如果stackOut为空，那么将stackIn中的元素全部放到stackOut中
    private void dumpstackIn(){
        if (! stackOut.isEmpty()){
            return;  // 如果stackOut不为空, 这直接回到原方法进行操作
        }

        // 如果执行到这一步, 说明上面的if没有进入, 则说明stackOut是空的,也就是无法出栈,
        // 此时需要将stackIn中的挨个弹出, 放入stackOut中,然后回到原方法操作stackOut, 就实现了队列的先进先出
        while (! stackIn.isEmpty()){
            stackOut.push(stackIn.pop());
        }
    }
}
```
可以看出`peek()`的实现，直接复用了`pop()`， 要不然，对 stOut 判空的逻辑又要重写一遍。<br />再多说一些代码开发上的习惯问题，在工业级别代码开发中，最忌讳的就是 实现一个类似的函数，直接把代码粘过来改一改就完事了。<br />这样的项目代码会越来越乱，**一定要懂得复用，功能相近的函数要抽象出来，不要大量的复制粘贴，很容易出问题！（踩过坑的人自然懂）  比如此处用到的**`**dumpstackIn()**`**方法**
> 工作中如果发现某一个功能自己要经常用，同事们可能也会用到，自己就花点时间把这个功能抽象成一个好用的函数或者工具类，不仅自己方便，也方便了同事们。
> 同事们就会逐渐认可你的工作态度和工作能力，自己的口碑都是这么一点一点积累起来的! 在同事圈里口碑起来了之后，你就发现自己走上了一个正循环，以后的升职加薪才少不了你！哈哈哈

<a name="R35Dr"></a>
# 225. 用队列实现栈 
:::info
可以大家惯性思维，以为还要两个队列来模拟栈，其实只用一个队列就可以模拟栈了。 <br />建议大家掌握一个队列的方法，更简单一些，可以先看视频讲解<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0225.%E7%94%A8%E9%98%9F%E5%88%97%E5%AE%9E%E7%8E%B0%E6%A0%88.html](https://programmercarl.com/0225.%E7%94%A8%E9%98%9F%E5%88%97%E5%AE%9E%E7%8E%B0%E6%A0%88.html)
:::
<a name="pntpT"></a>
## 使用两个Queue实现
```java
/**
 * 使用两个Queue实现
 */
class MyStack1 {

    Queue<Integer> queue1; // 和栈中保持一样元素的队列
    Queue<Integer> queue2; // 辅助队列

    //Initialize your data structure here
    public MyStack1() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /**
     * 模拟入栈
     * Push element x onto stack.
     * @param x
     */
    public void push(int x) {
        queue2.offer(x); // 先放在辅助队列中  offer入队

        while(! queue1.isEmpty()){ // 当不为空的时候进入
            queue2.offer(queue1.poll()); // 将queue1的元素挨个出队, 放入queue2中
        }

        Queue<Integer> queueTemp;
        queueTemp = queue1;
        queue1 = queue2;
        queue2 = queueTemp; // 最后交换queue1和queue2, 将queue2的元素都放到queue1中
    }

    /**
     * 模拟出栈
     * Removes the element on top of the stack and returns that element.
     * @return
     */
    public int pop() {
        return queue1.poll();  // 因为queue1中得元素和栈中保持一致,所以这个和下面的操作只看queue1即可
    }

    /**
     * 模拟获取栈顶元素
     * Get the top element.
     * @return
     */
    public int top() {
        return queue1.peek();
    }

    /**
     * 模拟判断栈是否为空
     * Returns whether the stack is empty.
     * @return
     */
    public boolean empty() {
        return queue1.isEmpty();
    }
}
```
<a name="fBZnP"></a>
## 使用一个Deque实现
```java
/**
 * 使用一个Deque实现
 */
class MyStack {
    // Deque 接口继承了 Queue 接口
    // 所以 Queue 中的 add、poll、peek等效于 Deque 中的 addLast、pollFirst、peekFirst
    Deque<Integer> que1;
    /** Initialize your data structure here. */
    public MyStack() {
        que1 = new ArrayDeque<>();
    }

    /**
     * 模拟入栈
     * Push element x onto stack.
     * @param x
     */
    public void push(int x) {
        que1.addLast(x);
    }

    /**
     * 模拟出栈
     * Removes the element on top of the stack and returns that element.
     * @return
     */
    public int pop() {
        int size = que1.size();
        size--;

        int res = que1.pollLast();
        return res;
    }

    /**
     * 模拟获取栈顶元素
     * Get the top element.
     * @return
     */
    public int top() {
        return que1.peekLast();
    }

    /**
     * 模拟判断栈是否为空
     * Returns whether the stack is empty.
     * @return
     */
    public boolean empty() {
        return que1.isEmpty();
    }
}
```
