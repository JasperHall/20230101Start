package l202305;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/13 21:28
 */
public class 反转链表 {  // 206

    class Solution {
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
    }
}
