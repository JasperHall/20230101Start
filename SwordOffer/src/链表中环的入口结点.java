import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * https://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/8 16:35
 */
public class 链表中环的入口结点 {

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode slow = hasCycle(pHead);
        // 没有环
        if (slow == null) return null;

        // 有环
        // 再来一个新的指针到列表的头部
        ListNode newHead = pHead;
        while (newHead != slow){
            newHead = newHead.next;
            slow = slow.next;
        }
        return slow;
    }
    // 判断有没有环
    public ListNode hasCycle(ListNode head){
        // 先判断链表为空的情况
        if (head == null) return null;

        // 快慢指针
        ListNode fast = head;
        ListNode slow = head;

        // 如果没有环, 快指针会先到链表尾部
        while (fast!=null && fast.next!=null){
            // 快指针移动两步
            fast = fast.next.next;
            // 慢指针移动一步
            slow = slow.next;

            // 如果有环, 则返回相遇的位置
            if (fast == slow) return slow;
        }

        // 没有在while循环中返回这说明到末尾了, 没有环
        return null;
    }


}
