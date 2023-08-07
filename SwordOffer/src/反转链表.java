/**
 * https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/7 23:06
 */
public class 反转链表 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode ReverseList (ListNode head) {
        // write code here
        ListNode pre = null;
        ListNode next = null;

        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }
}
