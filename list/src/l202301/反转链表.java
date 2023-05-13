package l202301;

/**
 * https://leetcode.cn/problems/reverse-linked-list/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/31 21:59
 */
public class 反转链表 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        /**
         * 双指针法
         * @param head
         * @return
         */
        public ListNode reverseList1(ListNode head) {
            ListNode prev = null;
            ListNode cur = head;
            ListNode temp = null;//存值用

            while ( cur != null){
                temp = cur.next;//
                cur.next = prev;
                prev = cur;
                cur = temp;
            }
            return prev;//返回头节点
        }

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

        /**
         * 从后向前递归
         * 继续理解
         * @param head
         * @return
         */
        public ListNode reverseList3(ListNode head) {
            //边缘条件判断
            if (head == null) return null;
            if (head.next == null) return head;

            //递归调用，反转第二个节点开始往后的链表
            ListNode last = reverseList3(head.next);
            //反转头节点与第二个节点的指向
            head.next.next = head;
            //此时的head节点为尾结点，next需要指向null
            head.next = null;
            return last;
        }
    }
}
