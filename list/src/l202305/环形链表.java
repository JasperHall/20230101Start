package l202305;

/**
 * https://leetcode.cn/problems/linked-list-cycle-ii/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/14 16:20
 */
public class 环形链表 {  // 142


    public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;//先定义出快慢指针

            while (fast != null && fast.next != null){//因为定义快指针每次走两步，所以要判断fast.next!=null
                slow = slow.next;
                fast = fast.next;
                if (slow == fast){//出现相遇则表示有环
                    ListNode index1 = fast;
                    ListNode index2 = head;
                    //两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                    while (index1 != index2){
                        index1 = index1.next;
                        index2 = index2.next;
                    }
                    return index1;//return index2也行，毕竟都相等了
                }
            }
            return null;
        }
    }
}
