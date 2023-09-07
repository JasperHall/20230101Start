package list;

/**
 * https://www.nowcoder.com/practice/b58434e200a648c589ca2063f1faf58c?tpId=295&tqId=654
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/7 15:58
 */
public class 链表内指定区间反转 {

    /**
     * 双指针(两次遍历)
     * @param head ListNode类
     * @param m int整型
     * @param n int整型
     * @return ListNode类
     *
     * 说明：方便理解，以下注释中将用left，right分别代替m,n节点
     */
    public ListNode reverseBetween (ListNode head, int m, int n) {
        // write code here

        ListNode dummy = new ListNode(-1);// 设置虚拟头节点
        dummy.next = head;
        ListNode pre = dummy;

        // 1.走left-1 (m-1)步到left (m)的 前一个节点, 注意是前一个
        for (int i=0; i<m-1; i++) pre = pre.next;  // 因为是前一个所以在这里i<m-1

        // 2.走right-left+1 (n-m+1) 步到right (n)节点
        ListNode rightNode = pre; // 从上一步的pre开始
        for (int i=0; i<n-m+1; i++) rightNode = rightNode.next;

        // 3.截取出一个子链表, 划定取出的范围
        ListNode leftNode = pre.next;  // leftNode就成为了切下来的链表的头部
        ListNode cur = rightNode.next;

        // 4.切断连接
        pre.next = null;  // 切断前面的连接
        rightNode.next = null;  // 切断后面的连接

        // 5.反转局部链表
        reverseList(leftNode);

        // 6.重新接回到原来的链表
        pre.next = rightNode;  // 反转后rightNode成了切下来的头部
        leftNode.next = cur;  //

        return dummy.next;
    }

    /**
     * 反转链表逻辑
     * @param head
     */
    public void reverseList(ListNode head){
        ListNode pre =null;
        ListNode cur = head;

        ListNode temp = null;//存值用

        while (cur != null){
            // Cur_next 指向cur节点的下一个节点
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
    }


    /**
     * 一次遍历(优化)
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween2 (ListNode head, int m, int n) {

        // 设置虚拟头节点
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;

        for (int i=0; i<m-1; i++){  // 把pre启动到待翻转区间的前一个位置
            pre = pre.next;
        }

        ListNode cur = pre.next;
        ListNode temp;  //   Cur_next (temp)：永远指向 curr 的下一个节点，循环过程中，curr 变化以后 Cur_next 会变化；
        for (int i=0; i<n-m; i++){
            temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;  // 注意这里不能.next
        }

        return dummyNode.next;

    }
}
