package l202305;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/14 14:32
 */
public class 链表相交 {  // 面试题 02.07. 链表相交


    public class Solution {
        /**
         * 哈希集合
         * @param headA
         * @param headB
         * @return
         */
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            Set<ListNode> visited = new HashSet<ListNode>();
            ListNode temp = headA;

            while (temp != null){
                visited.add(temp);  // 将链表headA的各个值加入到集合中
                temp = temp.next;
            }

            temp = headB;
            while (temp != null){
                if (visited.contains(temp)){
                    return temp;
                }
                temp = temp.next;
            }
            return null;
        }

        /**
         * 双指针
         * @param headA
         * @param headB
         * @return
         */
        public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }
            ListNode pA = headA, pB = headB;
            while (pA != pB) {
                pA = pA == null ? headB : pA.next;
                pB = pB == null ? headA : pB.next;
            }
            return pA;
        }
    }
}
