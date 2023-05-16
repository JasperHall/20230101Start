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

        /**
         * 递归法
         * @param head
         * @return
         */
        public ListNode reverseList2(ListNode head) {
            return reverse(null, head);
        }
        private ListNode reverse(ListNode prev, ListNode cur){
            if (cur == null) return prev;

            ListNode temp = null;
            temp = cur.next;  // temp为中间值, 这里保存一下
            cur.next = prev;  // 第一遍时, cur为head, 所以 head.next=null 了
            // 更新 prev, cur 的位置
            // prev = cur, cur=temp
            return  reverse(cur, temp);
        }
    }
}
