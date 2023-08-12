package DataStructure;

/**
 * https://www.nowcoder.com/practice/f9f78ca89ad643c99701a7142bd59f5d?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/9 23:41
 */
public class 删除链表的节点 {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param head ListNode类
     * @param val int整型
     * @return ListNode类
     */
    public ListNode deleteNode (ListNode head, int val) {
        // write code here
        ListNode dummy = new ListNode(-1);  // 创建一个虚拟头节点
        dummy.next = head;

        ListNode pre = dummy;// 前序节点
        ListNode cur = head; // 当前节点

        // 遍历链表
        while (cur != null){
            // 找到目标节点
            if (cur.val == val){
                // 断开连接
                pre.next = cur.next;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
}
