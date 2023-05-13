//https://leetcode.cn/problems/middle-of-the-linked-list/
public class 链表的中间结点
{
    public static void main(String[] args) {

    }

    public static class ListNode {
        int val;
        ListNode next = null;
        public ListNode(int val) {
            this.val = val;
        }
    }
    //（快慢指针法）快指针q每次走2步，慢指针p每次走1步，当q走到末尾时p正好走到中间。
    class Solution {
        public ListNode middleNode(ListNode head) {
            ListNode slow = head, fast = head;
            while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }
    //将链表进行遍历
    class Solution2 {
        public ListNode middleNode(ListNode head) {
            ListNode[] A = new ListNode[100];
            int t = 0;
            while (head != null) {
                A[t++] = head;
                head = head.next;
            }
            return A[t / 2];
        }
    }
}
