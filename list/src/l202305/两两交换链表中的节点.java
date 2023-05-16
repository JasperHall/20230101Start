package l202305;

import java.util.List;

/**
 * https://leetcode.cn/problems/swap-nodes-in-pairs/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/14 11:00
 */
public class 两两交换链表中的节点 {  // 24

    class Solution {
        /**
         * 直接遍历
         * @param head
         * @return
         */
        public ListNode swapPairs(ListNode head) {
            ListNode dummyHead = new ListNode(0); // 设置一个虚拟头节点
            dummyHead.next = head; // 将虚拟头结点指向head，这样方面后面做删除操作
            ListNode cur = dummyHead;
            ListNode temp;  // 临时节点，保存两个节点后面的节点
            ListNode firstnode; // 临时节点，保存两个节点之中的第一个节点
            ListNode secondnode; // 临时节点，保存两个节点之中的第二个节点

            //cur.next != null是size为偶数的情况，cur.next.next != null为size为奇数的情况
            // 链表 cur head a b c....
            while (cur.next != null && cur.next.next != null){
                temp = cur.next.next.next; // 第一次相当于 temp = b
                firstnode = cur.next;  // 第一次循环相当于 firstnode = head
                secondnode = cur.next.next; // 第一次循环相当于 secondnode = a

                // 注意接下来的每一步是不是要有 .next
                cur.next = secondnode; // 步骤一
                secondnode.next = firstnode; // 步骤二
                firstnode.next = temp; // 步骤三
                cur = firstnode;  // cur移动，准备下一轮交换
            }

            return dummyHead.next;//返回虚拟头节点的下一个节点
        }

        /**
         * 递归法
         * @param head
         * @return
         */
        public ListNode swapPairs2(ListNode head) {
            // base case 退出提交
            if (head==null || head.next==null) return head;
            // 获得当前节点的下一个节点
            ListNode nextRes = head.next;
            // 进行递归
            ListNode newNode = swapPairs2(nextRes.next);
            // 这里进行交换
            nextRes.next = head;
            head.next = newNode;

            return nextRes;
        }
    }
}
