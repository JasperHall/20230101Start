package l202301;

/**
 * https://leetcode.cn/problems/swap-nodes-in-pairs/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/31 23:16
 */
public class 两两交换链表中的节点 {

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
         * 直接遍历
         * @param head
         * @return
         */
        public ListNode swapPairs2(ListNode head) {
            ListNode dumyhead = new ListNode(0);//设置一个虚拟头节点
            dumyhead.next = head;// 将虚拟头结点指向head，这样方面后面做删除操作
            ListNode cur = dumyhead;//相当于指针
            ListNode temp;//临时节点，保存两个节点后面的节点
            ListNode firstnode;// 临时节点，保存两个节点之中的第一个节点
            ListNode secondnode; // 临时节点，保存两个节点之中的第二个节点

            //cur.next != null是size为偶数的情况，cur.next.next != null为size为奇数的情况
            while (cur.next != null && cur.next.next != null){
                temp = cur.next.next.next;
                firstnode = cur.next;
                secondnode = cur.next.next;

                cur.next = secondnode;//步骤一
                secondnode.next = firstnode;//步骤二
                firstnode.next = temp;//步骤三
                cur = firstnode;//cur移动，准备下一轮交换

            }
            return dumyhead.next;//返回虚拟头节点的下一个节点
        }

        /**
         * 递归
         * @param head
         * @return
         */
        public ListNode swapPairs1(ListNode head) {
            // base case 退出提交
            if (head == null || head.next == null) return head;
            // 获取当前节点的下一个节点
            ListNode next = head.next;
            //进行递归
            ListNode newNode = swapPairs1(next.next);
            //这里进行交换
            next.next = head;
            head.next = newNode;

            return next;
        }
    }
}
