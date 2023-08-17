package list;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/17 11:11
 */
public class 删除链表峰值 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode deleteNodes (ListNode head) {
        // write code here

        ListNode pre = head;
        ListNode cur = head;

        cur = cur.next;

        while (cur != null){
            if (cur.next != null && cur.val > pre.val && cur.val > cur.next.val){
                pre.next = cur.next;
            }
            pre = cur;
            cur = cur.next;
        }
        return head;

    }
}
