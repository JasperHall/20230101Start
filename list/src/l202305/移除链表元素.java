package l202305;

/**
 * https://leetcode.cn/problems/remove-linked-list-elements/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/13 19:50
 */
public class 移除链表元素 {  // 203


    class Solution {
        /**
         * 添加虚拟节点的方法
         * 时间复杂度 O(n)
         * 空间复杂度 O(1)
         * @param head
         * @param val
         * @return
         */
        public ListNode removeElements(ListNode head, int val) {
            if (head == null){
                return head;
            }
            // 因为删除可能涉及到头节点,所以设置dummy节点,统一操作
            ListNode dummy = new ListNode(-1, head);
            ListNode pre = dummy;
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

    }
}
