- 想法
   - 自己看到题目的第一想法
   - 看完代码随想录之后的想法 
- 实现过程中遇到哪些困难 
- 今日收获, 记录一下自己的学习时长

# 收获

1. 对dummy节点(虚拟节点)进行理解
    1. 区分虚拟头节点和不是虚拟头节点的创建方式
2. 熟练了双指针法的写法

<a name="I26Gl"></a>
# 计划

1. 继续理解 203.移除链表元素 虚拟节点
2. 学一下 707.设计链表 的双链表
3. 深度理解一下 206.DataStructure.反转链表 的递归法

<a name="P3DdU"></a>
# [203.移除链表元素](https://leetcode.cn/problems/remove-linked-list-elements/)
比较简单, 注意三种方法的灵活使用, 一种使用虚拟节点, 另外的不使用虚拟节点
```java
/**
 * 添加虚拟节点方式
 * 时间复杂度O(n)
 * 空间复杂度O(1)
 * @param head
 * @param val
 * @return
 */
public ListNode removeElements1(ListNode head, int val) {
    if (head == null){
        return head;
    }
    //因为删除可能涉及到头节点，所以设置dummy节点，统一操作
    ListNode dummy = new ListNode(-1, head);
    ListNode pre = dummy;//虚拟节点
    ListNode cur = head;
    while (cur != null){
        if (cur.val == val){
            pre.next = cur.next;
        }else {
            pre = cur;
        }
        cur = cur.next;
    }
    return dummy.next;
}

/**
 * 不添加虚拟节点的方式
 * 时间复杂度O(n)
 * 空间复杂度O(1)
 * @param head
 * @param val
 * @return
 */
public ListNode removeElements2(ListNode head, int val) {
    // 使用while一直判断到头节点不等于目标值val
    while (head != null && head.val == val){
       head = head.next;
    }
    //已经为null，提前退出
    if (head == null){
        return head;
    }
    //已确定当前 head.val != val
    ListNode pre = head;
    ListNode cur = head.next;

    while (cur != null){  // 头节点已经判断完毕, 所以这里第一次来判断的是head.next
        if (cur.val == val){
            pre.next = cur.next;
        }else {
            pre = cur;
        }
        cur = cur.next;
    }
    return head;
}

/**
 * 不添加虚拟节点and pre Node方式
 * 时间复杂度 O(n)
 * 空间复杂度 O(1)
 * @param head
 * @param val
 * @return
 */
public ListNode removeElements3(ListNode head, int val) {
    while (head != null && head.val == val){
        head = head.next;
    }

    ListNode curr = head;  // 只用这一个节点

    while (curr != null){
        while (curr.next != null && curr.next.val==val){  // 因为头节点一开始就判断完了, 所以判断头节点的下一个节点的值等不等于
            curr.next = curr.next.next;
        }
        curr = curr.next;
    }
    return head;
}
```
--
<a name="h8aLH"></a>
# [707.设计链表](https://leetcode.cn/problems/design-linked-list/)
注意到边界之类的等号问题
```java
class MyLinkedList {
    //都使用设置虚拟头节点来操作链表
    int size; // size存储链表元素的个数
    ListNode head; // 虚拟头结点

    /**
     * 初始化 MyLinkedList 对象。
     * 初始化链表
     */
    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }

    /**
     * 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
     * 获取第index个节点的数值，注意index是从0开始的，第0个节点就是头结点
     * @param index
     * @return
     */
    public int get(int index) {
        // 如果index非法，返回-1
        if (index < 0 || index >= size){
            return -1;
        }
        ListNode currentNode = head; //current当前的

        // 包含一个虚拟头节点，所以查找第index+1个节点
        for (int i=0; i<=index; i++){  // 注意这里是 <=
            currentNode = currentNode.next;
        }
        return currentNode.val;
    }

    /**
     * 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
     *
     * 在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
     *      * @param index
     * @param val
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
     * @param val
     */
    public void addAtTail(int val) {
        //在链表的最后插入一个节点，等价于在(末尾+1)个元素前添加
        addAtIndex(size, val);
    }

    /**
     * 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。
     * 如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。
     * 如果 index 比长度更大，该节点将 不会插入 到链表中。
     *
     * 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
     * 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
     * 如果 index 大于链表的长度，则返回空
     *
     * @param val
     */
    public void addAtIndex(int index, int val) {
        if (index > size){
            return;
        }
        if (index < 0){
            index = 0; // 就是插入在头节点之前
        }

        size++;  // 为什么随想录的题解会写这一行???  size是定义的全局的,因为插入后会多一位, 所以要++

        // 找到要插入节点的前驱
        ListNode predecessor = head;
        for (int i=0; i<index; i++){
            predecessor = predecessor.next; // 注意: 有虚拟头节点的存在, 所以要向后多next一下
        }
        ListNode toAdd = new ListNode(val);
        toAdd.next = predecessor.next;
        predecessor.next = toAdd;
    }

    /**
     * 如果下标有效，则删除链表中下标为 index 的节点。
     *
     * 如果索引 index 有效，则删除链表中的第 index 个节点。
     * @param index
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index>= size){
            return;
        }

        size--;  // size是定义的全局的,因为插入后会少一位, 所以要--

        if (index == 0){
            head = head.next;
            return;
        }
        ListNode predecessor = head;
        for (int i=0; i<index; i++){
            predecessor = predecessor.next;
        }
        predecessor.next = predecessor.next.next;
    }
}

```
时间不多了, 先光写一个单链表的设计, 有空复习下双链表
<a name="Cf2VZ"></a>
# [206.DataStructure.反转链表](https://leetcode.cn/problems/reverse-linked-list/)
看到题目的第一反映还是双指针, 对递归的掌握还是有问题的
<a name="K9NHR"></a>
## 双指针法
```java
/**
 * 双指针法
 * @param head
 * @return
 */
public ListNode reverseList(ListNode head) {
    ListNode pre = null;
    ListNode cur = head;
    ListNode temp = null; // 存值用

    while (cur != null){
        temp = cur.next;
        cur.next = pre;
        pre = cur;
        cur = temp;
    }
    return pre; // 返回头结点
}
```
<a name="AGzLc"></a>
## 递归法
[这个题解将递归解法二讲的比较清晰---链接](https://leetcode.cn/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/)
```java
/**
 * 递归
 * 其实还是应用的双指针的思想
 * @param head
 * @return
 */
public ListNode reverseList2(ListNode head) {
    return reverse(null, head);
}
private ListNode reverse(ListNode prev, ListNode cur){
    if ( cur == null) return prev;
    ListNode temp = null;
    temp = cur.next;//保存一下
    cur.next = prev;//反转操作
    //更新prev，cur的位置
    //prev = cur
    //cur = temp
    return reverse(cur, temp);
}
```
递归的两个条件：

1. 终止条件是当前节点或者下一个节点==null
2. 在函数内部，改变节点的指向，也就是 head 的下一个节点指向 head 递归函数那句
```java
head.next.next = head
```
很不好理解，其实就是 head 的下一个节点指向head。（例如1->2->3->4，当前head指向2，所以head.next指向3，head.next.next=3.next，因为本来head指向2没有改动，所以就可以成为head.next.next=3.next=2，就是1->2<-3->4，改变了2和3之间的指向）<br />递归函数中每次返回的 cur 其实**只最后一个节点**，在递归函数内部，**改变的是当前节点的指向**。<br />![](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1675218308303-14541bb7-0263-4f36-87c9-1ecefede6072.gif#averageHue=%23f9f8f5&clientId=u2d481827-e457-4&from=paste&id=u4822e690&originHeight=360&originWidth=640&originalType=url&ratio=1&rotation=0&showTitle=false&status=done&style=none&taskId=ud0686101-5fa5-463f-9672-85bf9506f89&title=)
<a name="prIiX"></a>

