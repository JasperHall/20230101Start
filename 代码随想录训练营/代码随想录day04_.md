- 想法
   - 自己看到题目的第一想法
   - 看完代码随想录之后的想法 
- 实现过程中遇到哪些困难 
- 今日收获, 记录一下自己的学习时长
<a name="aFBtT"></a>
## **今日任务 **

- 24. 两两交换链表中的节点, 19.删除链表的倒数第N个节点 , 面试题 02.07. 链表相交, 142.环形链表II , 总结
<a name="lRq4E"></a>
# 收获

1. 复习链表中的交换步骤
2. 思考数学证明再算法中的用处
3. java中链表地址相等的比较方法
<a name="vNHzx"></a>
# 计划

1. 继续学习递归, 补充 24.两两交换链表中的节点 的递归做法
2. 复习 142.环形链表II
<a name="JKOEE"></a>
# 24. 两两交换链表中的节点 
:::info
用虚拟头结点，这样会方便很多。 <br />本题链表操作就比较复杂了，建议大家先看视频，视频里我讲解了注意事项，为什么需要temp保存临时节点。<br />题目链接/文章讲解/视频讲解： [https://programmercarl.com/0024.%E4%B8%A4%E4%B8%A4%E4%BA%A4%E6%8D%A2%E9%93%BE%E8%A1%A8%E4%B8%AD%E7%9A%84%E8%8A%82%E7%82%B9.html](https://programmercarl.com/0024.%E4%B8%A4%E4%B8%A4%E4%BA%A4%E6%8D%A2%E9%93%BE%E8%A1%A8%E4%B8%AD%E7%9A%84%E8%8A%82%E7%82%B9.html)
:::
建议使用**虚拟头结点**，这样会方便很多，要不然每次针对头结点（没有前一个指针指向头结点），还要单独处理。<br />接下来就是交换相邻两个元素了，此时一定**要画图**，不画图，操作多个指针很容易乱，而且要操作的先后顺序<br />初始时，`cur`指向虚拟头结点，然后进行如下三步：<br />![](https://cdn.nlark.com/yuque/0/2023/png/32832913/1675218998861-edeae2c3-4b24-4cca-8132-fcbdb87f0f1d.png#averageHue=%23f8f7f7&clientId=u2d481827-e457-4&from=paste&id=u7320ff1f&originHeight=456&originWidth=1456&originalType=url&ratio=1&rotation=0&showTitle=false&status=done&style=shadow&taskId=u59bb9891-0378-440a-8a1a-5f632a3badf&title=)<br />操作之后链表如下<br />![](https://cdn.nlark.com/yuque/0/2023/png/32832913/1675219011011-fd61220b-b53a-4976-aa74-a8ed47adb755.png#averageHue=%23f9f8f8&clientId=u2d481827-e457-4&from=paste&id=u94aaec63&originHeight=462&originWidth=1442&originalType=url&ratio=1&rotation=0&showTitle=false&status=done&style=shadow&taskId=ud6237e91-b0c2-431a-b505-0558fc9735b&title=)<br />也就是<br />![](https://cdn.nlark.com/yuque/0/2023/png/32832913/1675219031873-a0290dc2-0a41-4c47-8240-d428dd3ff53c.png#averageHue=%23f9f8f8&clientId=u2d481827-e457-4&from=paste&id=u19be6990&originHeight=346&originWidth=1588&originalType=url&ratio=1&rotation=0&showTitle=false&status=done&style=shadow&taskId=u85e7e574-f58c-4a08-a762-f87552b28e4&title=)
<a name="QesBE"></a>
## 直接遍历
```java
/**
 * 直接遍历
 * @param head
 * @return
 */
public ListNode swapPairs(ListNode head) {
    ListNode dummyHead = new ListNode(0); // 设置一个虚拟头节点
    dummyHead.next = head; // 将虚拟头结点指向head，这样方面后面做删除操作
    ListNode cur = dummyHead;
    ListNode temp;  // 临时节点，保存两个节点后面的节点
    ListNode firstnode; // 临时节点，保存两个节点之中的第一个节点
    ListNode secondnode; // 临时节点，保存两个节点之中的第二个节点

    //cur.next != null是size为偶数的情况，cur.next.next != null为size为奇数的情况
    // 链表 cur head a b c....
    while (cur.next != null && cur.next.next != null){
        temp = cur.next.next.next; // 第一次相当于 temp = b
        firstnode = cur.next;  // 第一次循环相当于 firstnode = head
        secondnode = cur.next.next; // 第一次循环相当于 secondnode = a

        // 注意接下来的每一步是不是要有 .next
        cur.next = secondnode; // 步骤一
        secondnode.next = firstnode; // 步骤二
        firstnode.next = temp; // 步骤三
        cur = firstnode;  // cur移动，准备下一轮交换
    }

    return dummyHead.next;//返回虚拟头节点的下一个节点
}
```
<a name="jILLk"></a>
## 递归


<a name="TxIUi"></a>
# 19.删除链表的倒数第N个节点  
:::info
双指针的操作，要注意，删除第N个节点，那么我们当前遍历的指针一定要指向 第N个节点的前一个节点，建议先看视频。<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0019.%E5%88%A0%E9%99%A4%E9%93%BE%E8%A1%A8%E7%9A%84%E5%80%92%E6%95%B0%E7%AC%ACN%E4%B8%AA%E8%8A%82%E7%82%B9.html](https://programmercarl.com/0019.%E5%88%A0%E9%99%A4%E9%93%BE%E8%A1%A8%E7%9A%84%E5%80%92%E6%95%B0%E7%AC%ACN%E4%B8%AA%E8%8A%82%E7%82%B9.html)
:::
双指针的经典应用，如果要删除倒数第n个节点，让 fast 移动 n 步，然后让 fast 和 slow 同时移动，直到 fast 指向链表末尾。删掉 slow 所指向的节点就可以了。<br />思路是这样的，但要注意一些细节。<br />分为如下几步：

1. 首先这里我推荐大家使用虚拟头结点，这样方便处理删除实际头结点的逻辑
2. 定义 fast 指针和 slow 指针，初始值为虚拟头结点，如图：

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1684044302528-ed2b92e6-639f-4dd6-890d-691ce3c53c23.png#averageHue=%23f6f6f6&clientId=ubccbf037-2cc8-4&from=paste&height=179&id=uef3baf93&originHeight=210&originWidth=692&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=29710&status=done&style=none&taskId=u65d8ae66-6aeb-4d6c-9f84-089c5b89352&title=&width=591.4530131473587)

3. fast首先走 n + 1步 ，为什么是 n+1 呢，因为只有这样同时移动的时候 slow 才能指向删除节点的上一个节点（方便做删除操作），如图：

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1684044338266-d5b3d8a8-0eb8-4232-8825-d5393e8bfcfe.png#averageHue=%23f7f7f7&clientId=ubccbf037-2cc8-4&from=paste&height=157&id=u066371cc&originHeight=184&originWidth=676&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=24171&status=done&style=none&taskId=u45a56ad4-cecd-490b-a8e7-c385f1a6a9e&title=&width=577.7777989705412)

4. fast 和 slow 同时移动，直到 fast 指向末尾，如题：

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1684044356311-35305a8b-3d2e-4b1c-84c5-e6ef7115b3ed.png#averageHue=%23f7f7f7&clientId=ubccbf037-2cc8-4&from=paste&height=179&id=ufc209677&originHeight=210&originWidth=675&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=23452&status=done&style=none&taskId=udcd85a3f-6e6c-4e8f-bd50-fa7fbec43d7&title=&width=576.92309808449)

5. 删除 slow 指向的下一个节点，如图：

![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1684044371033-c3f51d89-9694-4cb0-a8eb-6170bf0a5fe3.png#averageHue=%23f6f6f6&clientId=ubccbf037-2cc8-4&from=paste&height=169&id=u9637c282&originHeight=198&originWidth=685&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=32238&status=done&style=none&taskId=u6b35ddcd-fefa-41a1-bd55-3e8d11c3a9f&title=&width=585.470106945001)<br />本题注意快指针的移动次数
```java
/**
 * 双指针: 快慢指针
 * @param head
 * @param n
 * @return
 */
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummyNode = new ListNode(0);  // 虚拟头节点
    dummyNode.next = head;
    ListNode fastIndex = dummyNode;
    ListNode slowIndex = dummyNode;

    // 只要快指慢指针相差n个节点即可
    for (int i=0; i<n; i++){  // 因为下面慢指针要到需要删除的节点的前一个位置, 所以这列移动n次
        fastIndex = fastIndex.next;
    }

    while (fastIndex.next != null){
        fastIndex = fastIndex.next;
        slowIndex = slowIndex.next;
    }

    // 此时 slowIndex 的位置就是 待删除元素 的 前一个位置。
    // 具体情况可自己画一个链表长度为 3 的图来模拟代码来理解
    slowIndex.next = slowIndex.next.next;
    return dummyNode.next; // 因为dumyNode是虚拟头节点，所以要返回dumyNode.next
}
```

<a name="R7x4L"></a>
# 面试题 02.07. 链表相交  
:::info
本题没有视频讲解，大家注意 数值相同，不代表指针相同。<br />题目链接/文章讲解：[https://programmercarl.com/%E9%9D%A2%E8%AF%95%E9%A2%9802.07.%E9%93%BE%E8%A1%A8%E7%9B%B8%E4%BA%A4.html](https://programmercarl.com/%E9%9D%A2%E8%AF%95%E9%A2%9802.07.%E9%93%BE%E8%A1%A8%E7%9B%B8%E4%BA%A4.html)
:::
注意指针和数值的问题<br />认真阅读题目及示例。<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1675232248401-22cd7eb5-9936-472b-bf9b-1b64dbfc8046.png#averageHue=%23666665&clientId=u2d481827-e457-4&from=paste&height=543&id=u5817fecb&originHeight=635&originWidth=647&originalType=binary&ratio=1&rotation=0&showTitle=false&size=54784&status=done&style=none&taskId=u001e257f-4ea4-40b4-a2db-37bd0b86b83&title=&width=552.9914732750593)<br />简单来说，就是求两个链表交点节点的**指针**。 这里同学们要注意，交点不是数值相等，而是指针相等。<br />为了方便举例，假设节点元素数值相等，则节点指针相等。<br />看如下两个链表，目前`curA`指向链表 A 的头结点，`curB`指向链表 B 的头结点：<br />![](https://cdn.nlark.com/yuque/0/2023/png/32832913/1675232299454-55b46a9b-b2e9-44c9-b77f-884ea118a837.png#averageHue=%23f6f6f6&clientId=u2d481827-e457-4&from=paste&id=u4d9c5967&originHeight=582&originWidth=882&originalType=url&ratio=1&rotation=0&showTitle=false&status=done&style=none&taskId=uedb752d1-ddc4-401f-af4e-9bcd83f856c&title=)<br />我们求出两个链表的长度，并求出两个链表长度的差值，然后让`curA`移动到，和`curB`末尾对齐的位置，如图：<br />![](https://cdn.nlark.com/yuque/0/2023/png/32832913/1675232318451-86b7eb2a-1d09-49e4-a26e-bb5af951f34b.png#averageHue=%23f6f6f6&clientId=u2d481827-e457-4&from=paste&id=u347a9829&originHeight=588&originWidth=880&originalType=url&ratio=1&rotation=0&showTitle=false&status=done&style=none&taskId=u51ad7372-0d1f-4d7e-9e56-305b9534632&title=)<br />此时我们就可以比较`curA`和`curB`是否相同，如果不相同，同时向后移动`curA`和`curB`，如果遇到`curA == curB`，则找到交点。<br />否则循环退出返回空指针。
:::danger
链表节点直接使用 = 比较就是比较的指针地址相等
:::
```java
public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
    ListNode curA = headA;
    ListNode curB = headB;
    int lenA=0, lenB=0;

    while (curA != null){//求链表A的长度
        lenA++;
        curA = curA.next;
    }
    while (curB != null){//求链表B的长度
        lenB++;
        curB = curB.next;
    }

    curA = headA;
    curB = headB;

    //这里是为了始终A是长的那个，这样可以为了比较长度差，避免减出负数
    if (lenB > lenA){// 让curA为最长链表的头，lenA为其长度
        //1. swap (lenA, lenB);交换长度
        int tmpLen = lenA;
        lenA = lenB;
        lenB = tmpLen;
        //2. swap (curA, curB);交换指针
        ListNode tmpNode = curA;
        curA = curB;
        curB = tmpNode;
    }

    int gap = lenA - lenB;//求长度差
    while (gap-- > 0){
        curA = curA.next;
    }
    //遍历curA和curB，遇到相同则直接返回
    while (curA != null){
        if (curA == curB){
            return curA;
        }
        curA = curA.next;
        curB = curB.next;
    }
    return null;
}
```
来自：[代码随想录](https://www.programmercarl.com/%E9%9D%A2%E8%AF%95%E9%A2%9802.07.%E9%93%BE%E8%A1%A8%E7%9B%B8%E4%BA%A4.html#%E6%80%9D%E8%B7%AF)
<a name="afalT"></a>
## 简洁双指针
使用双指针的方法，可以将空间复杂度降至 O(1)。<br />只有当链表 `headA`和`headB` 都不为空时，两个链表才可能相交。因此首先判断链表 `headA` 和 `headB`是否为空，如果其中至少有一个链表为空，则两个链表一定不相交，返回`null`。<br />当链表 `headA` 和`headB` 都不为空时，创建两个指针 , `pA` 和 `pB`，初始时分别指向两个链表的头节点 `headA` 和 `headB`，然后将两个指针依次遍历两个链表的每个节点。具体做法如下：

- 每步操作需要同时更新指针 pA 和 pB。
- 如果指针 pA 不为空，则将指针 pA 移到下一个节点；如果指针 pB 不为空，则将指针 pB 移到下一个节点。
- 如果指针 pA 为空，则将指针 pA 移到链表 headB 的头节点；如果指针 pB 为空，则将指针 pB 移到链表headA 的头节点。
- 当指针 pA 和 pB 指向同一个节点或者都为空时，返回它们指向的节点或者 null![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1684052085715-57bab506-52fa-4e9a-9c5f-2a6fd5b8773c.png#averageHue=%2332302e&clientId=u0abbd295-cb1c-4&from=paste&height=593&id=u172f2e21&originHeight=694&originWidth=1072&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=122421&status=done&style=none&taskId=ucc5e15b1-007e-4ea4-8cf7-08edf132a2f&title=&width=916.2393498467753)
:::danger
经过证明中的数学推导, A和B在经历了几次循环比较后, 是会出现同时为null 的, 都为null, 无相等, 则返回null
:::
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}

作者：LeetCode-Solution
链接：https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/solution/lian-biao-xiang-jiao-by-leetcode-solutio-2kne/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```

<a name="PHSOr"></a>
# 142.环形链表II  
:::info
算是链表比较有难度的题目，需要多花点时间理解 确定环和找环入口，建议先看视频。<br />题目链接/文章讲解/视频讲解：[https://programmercarl.com/0142.%E7%8E%AF%E5%BD%A2%E9%93%BE%E8%A1%A8II.html](https://programmercarl.com/0142.%E7%8E%AF%E5%BD%A2%E9%93%BE%E8%A1%A8II.html)
:::
**思考：**

1. **怎样判断有环？**

答：之前我想到的判断最后一个节点的下一个节点是否为null来判断是否有环在这里是不行的，因为我做出这样的想法是因为之前遇到过定义好size长度的链表，而这里没有说好长度。所以只能用快慢指针相遇来判断是否有环。

2. **为什么快指针要每次移动两次？**

答：两个指针都在环中时，快指针每次移动两次，慢指针每次移动一次，就表示快指针每次向慢指针靠近一格，这样不会错过。如果是快指针每次移动三格，则是每次靠近两格，有可能会错过相遇。<br />**思路：**<br />这道题目，不仅考察对链表的操作，而且还需要一些数学运算。<br />主要考察两知识点：

- 判断链表是否环
- 如果有环，如何找到这个环的入口

**判断链表是否有环：**<br />可以使用快慢指针法，分别定义`fast`和`slow`指针，从头结点出发，`**fast**`**指针每次移动两个节点**，`**slow**`**指针每次移动一个节点**，如果`fast`和`slow`指针在途中相遇 ，说明这个链表有环。<br />为什么`fast`走两个节点，`slow`走一个节点，有环的话，一定会在环内相遇呢，而不是永远的错开呢

- 首先第一点：`fast`指针一定先进入环中，如果`fast`指针和`slow`指针相遇的话，一定是在环中相遇，这是毋庸置疑的。

那么来看一下，为什么`fast`指针和`slow`指针一定会相遇呢？<br />可以画一个环，然后让`fast`指针在任意一个节点开始追赶`slow`指针。会发现最终都是这种情况， 如下图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1684052686812-15b3853b-c7e3-4eb1-92dd-50cc2ffba310.png#averageHue=%23fdfdfd&clientId=u0abbd295-cb1c-4&from=paste&height=209&id=ua223852b&originHeight=244&originWidth=830&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=14362&status=done&style=none&taskId=u951f898f-79ad-4bfa-be21-0185c377075&title=&width=709.40173542241)<br />`fast`和`slow`各自再走一步， `fast`和`slow`就相遇了<br />这是因为`fast`是走两步，`slow`是走一步，其实相对于`slow`来说，`fast`是一个节点一个节点的靠近`slow`的，所以`fast`一定可以和`slow`重合。<br />动画如下：<br />![141.环形链表.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1684052710133-30a7074b-713c-474e-8021-c48e8205d752.gif#averageHue=%23e6e6e6&clientId=u0abbd295-cb1c-4&from=paste&height=344&id=uaa715f9c&originHeight=402&originWidth=568&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=1909133&status=done&style=none&taskId=u53342135-85f9-4d54-8ea2-42411e7750b&title=&width=485.47010327702276)<br />**如果有环，如何找到这个环的入口**<br />**此时已经可以判断链表是否有环了，那么接下来要找这个环的入口了。**<br />假设从头结点到环形入口节点的节点数为 **x**。 环形入口节点到 `fast`指针与`slow`指针相遇节点 节点数为 **y**。 从相遇节点 再到环形入口节点节点数为** z**。 如图所示：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1684052756346-4b471eee-ba3b-4480-8688-aca947d4940e.png#averageHue=%23faf9f9&clientId=u0abbd295-cb1c-4&from=paste&height=268&id=ue98e2749&originHeight=314&originWidth=877&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=43474&status=done&style=none&taskId=u132fa346-eef4-4308-b4a6-518694c50a2&title=&width=749.5726770668115)<br />那么相遇时： slow指针走过的节点数为: x + y， fast指针走过的节点数：x + y + n (y + z)，**n为fast指针在环内走了n圈才遇到slow指针**， （y+z）为 一圈内节点的个数A。<br />因为`fast`指针是一步走两个节点，`slow`指针一步走一个节点， 所以`fast`指针走过的节点数 **=** `slow`指针走过的节点数 ***** `2`：**(x + y) * 2 = x + y + n (y + z)**<br />两边消掉一个（x+y）: **x + y = n (y + z)**<br />因为要找环形的入口，那么要求的是x，因为x表示 头结点到 环形入口节点的的距离。<br />所以要求x ，将x单独放在左面：**x = n (y + z) - y **，<br />再从n(y+z)中提出一个 （y+z）来，整理公式之后为如下公式**：x = (n - 1) (y + z) + z ，**注意：这里n一定是大于等于1的，因为 fast指针至少要多走一圈才能相遇slow指针。<br />**这个公式说明什么呢？**<br />先拿n为1的情况来举例，意味着fast指针在环形里转了一圈之后，就遇到了 slow指针了。<br />当 n为1的时候，公式就化解为 x = z，<br />**这就意味着，从头结点出发一个指针，从相遇节点 也出发一个指针，这两个指针每次只走一个节点， 那么当这两个指针相遇的时候就是 环形入口的节点。**<br />也就是在相遇节点处，定义一个指针index1，在头结点处定一个指针index2。<br />让 index1 和 index2 同时移动，每次移动一个节点， 那么他们相遇的地方就是 环形入口的节点。<br />动画如下：<br />![142.环形链表II（求入口）.gif](https://cdn.nlark.com/yuque/0/2023/gif/32832913/1684052780051-12ef2674-19ed-41a3-b504-2a738d097eea.gif#averageHue=%23d7d7d7&clientId=u0abbd295-cb1c-4&from=paste&height=354&id=uf4323080&originHeight=414&originWidth=572&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=3282517&status=done&style=none&taskId=u9e7e541e-3218-4c6f-9322-e4ac8fc11b6&title=&width=488.8889068212271)<br />那么 n如果大于1是什么情况呢，就是fast指针在环形转n圈之后才遇到 slow指针。<br />其实这种情况和n为1的时候 效果是一样的，一样可以通过这个方法找到 环形的入口节点，只不过，index1 指针在环里 多转了(n-1)圈，然后再遇到index2，相遇点依然是环形的入口节点。<br />**补充：**<br />在推理过程中，大家可能有一个疑问就是：为什么第一次在环中相遇，slow的 步数 是 x+y 而不是 x + 若干环的长度 + y 呢？<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1675238572153-55009f1c-76e3-4539-94a5-a28b36002399.png#averageHue=%23faf7f7&clientId=u2d481827-e457-4&from=paste&id=u6fabd662&originHeight=682&originWidth=1364&originalType=url&ratio=1&rotation=0&showTitle=false&size=157489&status=done&style=none&taskId=u8834b0d5-1bcd-4d53-98e5-75ae555d2dc&title=)<br />首先 slow 进环的时候，fast 一定是先进环来了。<br />如果 slow 进环入口，fast 也在环入口，那么把这个环展开成直线，就是如下图的样子：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1675238600069-0ecd83fa-bfe4-47d9-bc8b-709ac2cd229a.png#averageHue=%23f7f5f5&clientId=u2d481827-e457-4&from=paste&id=u12b758a5&originHeight=550&originWidth=1250&originalType=url&ratio=1&rotation=0&showTitle=false&size=99237&status=done&style=none&taskId=u762476b1-dfaf-4b34-823a-074d36c075e&title=)<br />可以看出如果 slow 和 fast 同时在环入口开始走，一定会在环入口3相遇，slow 走了一圈，fast 走了两圈。<br />重点来了，slow 进环的时候，fast 一定是在环的任意一个位置，如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1675238636915-871e9942-8154-4205-80d2-0b16409857f3.png#averageHue=%23f7f5f5&clientId=u2d481827-e457-4&from=paste&id=u3caf7403&originHeight=472&originWidth=1226&originalType=url&ratio=1&rotation=0&showTitle=false&size=79085&status=done&style=none&taskId=u1d50465f-f46b-4364-8713-c9b1cd3e63a&title=)<br />那么 fast 指针走到环入口3的时候，已经走了k + n 个节点，slow相应的应该走了(k + n) / 2 个节点。<br />因为k是小于n的（图中可以看出），所以(k + n) / 2 一定小于n。<br />也就是说slow一定没有走到环入口3，而fast已经到环入口3了。<br />**这说明什么呢？**<br />在slow开始走的那一环已经和fast相遇了。<br />那有同学又说了，为什么fast不能跳过去呢？ 在刚刚已经说过一次了，fast相对于slow是一次移动一个节点，所以不可能跳过去。
```java
/**
 * 双指针
 * 快慢指针
 * @param head
 * @return
 */
public ListNode detectCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;//先定义出快慢指针

    while (fast != null && fast.next != null){//因为定义快指针每次走两步，所以要判断fast.next!=null
        slow = slow.next;
        fast = fast.next;
        if (slow == fast){//出现相遇则表示有环
            ListNode index1 = fast;
            ListNode index2 = head;
            //两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
            while (index1 != index2){
                index1 = index1.next;
                index2 = index2.next;
            }
            return index1;//return index2也行，毕竟都相等了
        }
    }
    return null;
}
```
[LeetCode优质题解](https://leetcode.cn/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/)



