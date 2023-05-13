package l202301;

/**
 * https://leetcode.cn/problems/remove-linked-list-elements/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/26 16:36
 */
public class 移除链表元素 {
    public static void main(String[] args) {
        Solution solution = new Solution();


    }

    /**
     * 创建链表
     */
    public class MyLinkedList {

        public ListNode head;//链表的头

        public void creatList(){
            ListNode listNode1 = new ListNode(11);
            ListNode listNode2 = new ListNode(22);
            ListNode listNode3 = new ListNode(22);
            ListNode listNode4 = new ListNode(44);
            ListNode listNode5 = new ListNode(55);

            this.head = listNode1;

            listNode1.next = listNode2;
            listNode2.next = listNode3;
            listNode3.next = listNode4;
            listNode4.next = listNode5;

        }
    }


    static class Solution {
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
            //因为删除可能涉及到头结点，所以设置dummy节点，统一操作
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
            //思考这里为什么用while，和注意与下面if的判断顺序
            while (head != null && head.val == val){ //这里是 &&
                head = head.next;
            }
            //已经为null，提前退出
            if (head == null){
                return head;
            }
            //已确定当前 head.val != val
            ListNode pre = head;
            ListNode cur = head.next;

            while (cur != null){
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
            while (head != null && head.val==val){//
                head = head.next;
            }
            ListNode curr = head;
            while (curr != null){
                while (curr.next != null && curr.next.next.val == val){
                    curr.next = curr.next.next;
                }
                curr = curr.next;
            }

            return head;
        }
    }
}
