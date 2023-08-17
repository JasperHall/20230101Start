package list;

import java.util.ArrayList;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/17 14:30
 */
public class 调整牛群顺序 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 双指针
     * @param head ListNode类
     * @param n int整型
     * @return ListNode类
     */
    public ListNode moveNthToEnd (ListNode head, int n) {
        // write code here
        if (n == 1) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        for (int i=0; i<n; i++){
            fast = fast.next;
        }

        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        ListNode target = slow.next;
        slow.next = slow.next.next;
        target.next = null;
        fast.next = target;

        return dummy.next;
    }
}

