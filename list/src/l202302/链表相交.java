package l202302;

/**
 * https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/1 11:44
 */
public class 链表相交 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    public class Solution {
        public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
            ListNode curA = headA;
            ListNode curB = headB;
            int lenA=0, lenB=0;

            while (curA != null){//求链表A的长度
                lenA++;
                curA = curA.next;
            }
            while (curB != null){//求链表B的长度
                lenB++;
                curB = curB.next;
            }

            curA = headA;
            curB = headB;

            //这里是为了始终A是长的那个，这样可以为了比较长度差，避免减出负数
            if (lenB > lenA){// 让curA为最长链表的头，lenA为其长度
                //1. swap (lenA, lenB);交换长度
                int tmpLen = lenA;
                lenA = lenB;
                lenB = tmpLen;
                //2. swap (curA, curB);交换指针
                ListNode tmpNode = curA;
                curA = curB;
                curB = tmpNode;
            }

            int gap = lenA - lenB;//求长度差
            while (gap-- > 0){
                curA = curA.next;
            }
            //遍历curA和curB，遇到相同则直接返回
            while (curA != null){
                if (curA == curB){
                    return curA;
                }
                curA = curA.next;
                curB = curB.next;
            }
            return null;
        }
    }
}
