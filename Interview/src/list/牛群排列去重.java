package list;

/**
 * https://www.nowcoder.com/practice/8cabda340ac6461984ef9a1ad66915e4?tpId=354
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/17 11:45
 */
public class 牛群排列去重 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode deleteDuplicates (ListNode head) {
        // write code here

        // 链表长度 <= 1
        if (head == null || head.next == null) {  // 这一步一定要写
            return head;
        }

        ListNode pre = head;
        ListNode cur = head.next;

        while (cur != null){
            if (cur.val == pre.val){
                // cur重复节点, 删除
                pre.next = cur.next;
            } else {
                // cur非重复节点, pre向后移动
                pre = cur;
            }

            // cur移动
            cur = cur.next;

        }

        return head;

    }
}
