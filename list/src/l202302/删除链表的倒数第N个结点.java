package l202302;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/1 10:54
 */
public class 删除链表的倒数第N个结点 {
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
         * 双指针
         * @param head
         * @param n
         * @return
         */
        public ListNode removeNthFromEnd1(ListNode head, int n) {
            ListNode dumyNode = new ListNode(0);
            dumyNode.next = head;

            ListNode fastIndex = dumyNode;
            ListNode slowIndex = dumyNode;

            //只要快指慢指针相差n个节点即可
            for (int i=0; i<n; i++){
                fastIndex = fastIndex.next;
            }

            while (fastIndex.next != null){
                fastIndex = fastIndex.next;
                slowIndex = slowIndex.next;
            }

            //此时 slowIndex 的位置就是待删除元素的前一个位置。
            //具体情况可自己画一个链表长度为 3 的图来模拟代码来理解
            slowIndex.next = slowIndex.next.next;
            return dumyNode.next;//因为dumyNode是虚拟头节点，所以要返回dumyNode.next
        }
    }
}
