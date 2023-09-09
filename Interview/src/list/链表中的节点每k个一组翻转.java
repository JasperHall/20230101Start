package list;

/**
 * https://www.nowcoder.com/practice/b49c3dc907814e9bbfa8437c251b028e?tpId=295&tqId=722
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/7 20:04
 */
public class 链表中的节点每k个一组翻转 {
    /**
     * 非递归
     * @param head ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup (ListNode head, int k) {
        // write code here
        //先创建一个虚拟节点
        ListNode dummy = new ListNode(0);
        //让哑节点的指针指向链表的头
        dummy.next = head;

        //开始反转的前一个节点，比如反转的节点范围是[link1，link2], 那么pre就是link1的 前 一个节点
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null) {
            // 每k个反转，end是每k个链表的最后一个
            for (int i = 0; i < k && end != null; i++) end = end.next;

            // 如果end是空，说明不够k个，就不需要反转了，直接退出循环。
            if (end == null) break;  // 注意这一步的写法

            // 反转开始的节点
            ListNode start = pre.next;
            // end是这k个的最后一个, 则next是下一次反转的头结点，先把他记录下来
            ListNode next = end.next;

            // 因为end是这k个链表的最后一个结点，把它和原来链表断开，这k个节点我们可以把他们看做一个小的链表，然后反转这个小链表
            end.next = null;

            // 因为pre是反转链表的前一个节点，我们把小链表[start,end]反转之后，让pre的指针指向这个反转的小链表
            pre.next = reverse(start);
            // 注意经过上一步反转之后，start反转到链表的尾部了，就是已经反转之后的尾结点了，
            // 让他之前下一次反转的头结点即可（上面分析过，next就是下一次反转的头结点）
            start.next = next;

            // 前面反转完了，要进入下一波了，pre和end都有 重新赋值 (注意重新赋值)
            pre = start;
            end = start;
        }

        return dummy.next;
    }

    /**
     * 链表的反转
     * @param head
     * @return
     */
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 递归
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup2 (ListNode head, int k) {
        // 边界条件判断 终止条件
        if (head == null || head.next == null) return head;

        ListNode tail = head;
        for (int i=0; i<k; i++){
            // 剩余数量小于k的话,则不需要反转
            if (tail == null) return head;

            tail = tail.next;
        }

        // 反转k个元素
        ListNode newHead = reverse2(head, tail);

        // 下一轮的开始的地方就是tail
        head.next = reverseKGroup2(tail, k); // 递归调用
        return newHead;
    }

    /**
     * 链表的反转，不是反转全部，只反转区间[head,tail)中间的节点，左闭右开区间
     * @param head
     * @param tail
     * @return
     */
    private ListNode reverse2(ListNode head, ListNode tail){
        ListNode pre = null;
        ListNode next = null;  // 起辅助作用
        while (head != tail){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

}
