package l202305;

import l202301.移除链表元素;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/14 11:35
 */
public class 删除链表的倒数第N个结点 {  // 19


    public static void main(String[] args) {
        Solution solution = new Solution();
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.creatList();

        ListNode res = solution.removeNthFromEnd(myLinkedList.head, 2);

        ListNode cur = res;
        while (cur != null){
            System.out.print(cur.val+", ");
            cur = cur.next;
        }
        System.out.println("\n"+"链表打印完成!");
    }

    /**
     * 创建链表
     */
    public static class MyLinkedList {

        public ListNode head;//链表的头

        public void creatList(){
            ListNode listNode1 = new ListNode(11);
            ListNode listNode2 = new ListNode(22);
            ListNode listNode3 = new ListNode(33);
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

            //只要快指慢指针相差n个节点即可
            for (int i=0; i<n; i++){
                fastIndex = fastIndex.next;
            }

            while (fastIndex.next != null){
                fastIndex = fastIndex.next;
                slowIndex = slowIndex.next;
            }

            //此时 slowIndex 的位置就是 待删除元素 的 前一个位置。
            //具体情况可自己画一个链表长度为 3 的图来模拟代码来理解
            slowIndex.next = slowIndex.next.next;
            return dummyNode.next;//因为dumyNode是虚拟头节点，所以要返回dumyNode.next
        }
    }
}
