package list;

/**
 * https://www.nowcoder.com/practice/267c0deb9a6a41e4bdeb1b2addc64c93?tpId=354
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/17 15:19
 */
public class 牛群的重新分组 {

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.creatList();

        Solution solution = new Solution();
        ListNode result = solution.reverseKGroup(myLinkedList.head, 4);

        ListNode cur = result;
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
            ListNode listNode1 = new ListNode(1);
            ListNode listNode2 = new ListNode(2);
            ListNode listNode3 = new ListNode(3);
            ListNode listNode4 = new ListNode(4);
            ListNode listNode5 = new ListNode(5);
            ListNode listNode6 = new ListNode(6);

            this.head = listNode1;

            listNode1.next = listNode2;
            listNode2.next = listNode3;
            listNode3.next = listNode4;
            listNode4.next = listNode5;
            listNode5.next = listNode6;

        }
    }

    static class Solution {
        /**
         * 首先写个函数，函数输入值为操作区间的前一个节点，和操作区间的后一个节点。
         * 主函数里构造虚拟头结点为初始prev，初始curr指向head。用count++计数，不能整除k就curr后移一位，能整除就带入函数操作，再更新prev和curr指针。
         *
         * @param head ListNode类
         * @param k    int整型
         * @return ListNode类
         */
        public ListNode reverseKGroup(ListNode head, int k) {
            // write code here

            if (head.next == null) return head;

            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            ListNode pre = dummy;
            ListNode cur = dummy.next;

            int count = 0;
            while (cur != null) {
                count++;
                if (count % k == 0) {
                    pre = reverse(pre, cur.next);
                    cur = pre.next;
                } else {
                    cur = cur.next;
                }
            }
            return dummy.next;
        }

        // 第一次进入是 pre 是虚拟头节点
        public ListNode reverse(ListNode pre, ListNode end) {
            ListNode cur = pre.next;
            ListNode tail = cur;

            while (cur != end) {
                ListNode temp = cur.next;
                cur.next = pre.next;
                pre.next = cur;
                cur = temp;
            }
            tail.next = end;
            return tail;
        }

        /**
         * 1.创建一个虚拟头节点dummy，将其指向原链表的头节点head。
         * 2.计算链表的长度length，以便确定需要进行翻转的组数。
         * 3.初始化两个指针prev和curr，分别指向当前组的前一个节点和当前组的第一个节点。
         * 4.在每一组内，使用反转操作将节点的指针方向翻转。
         * 5.连接相邻的组。
         * 6.返回虚拟头节点的next指针。
         * <p>
         * 计算链表的长度，所以时间复杂度为O(n)，其中n是链表的长度。接下来，每次翻转k个节点需要进行k-1次节点交换，因此每个节点只会被访问一次，总的时间复杂度为O(n)。由于只使用了常数级别的额外空间，所以空间复杂度为O(1)。
         *
         * @param head
         * @param k
         * @return
         */
        public ListNode reverseKGroup2(ListNode head, int k) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode prev = dummy;
            ListNode curr = head;

            int length = 0;
            while (head != null) {
                length++;
                head = head.next;
            }

            for (int i = 0; i < length / k; i++) {
                for (int j = 1; j < k; j++) {
                    ListNode temp = curr.next;
                    curr.next = temp.next;
                    temp.next = prev.next;
                    prev.next = temp;
                }
                prev = curr;
                curr = curr.next;
            }

            return dummy.next;
        }
    }
}
