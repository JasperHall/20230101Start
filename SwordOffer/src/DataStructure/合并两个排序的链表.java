package DataStructure;

/**
 * https://www.nowcoder.com/practice/d8b6b4358f774294a89de2a6ac4d9337
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/8 14:51
 */
public class 合并两个排序的链表 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pHead1 ListNode类
     * @param pHead2 ListNode类
     * @return ListNode类
     */
    /**
     * 递归
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode Merge (ListNode pHead1, ListNode pHead2) {
        // write code here

        if (pHead1 == null) return pHead2;
        if (pHead2 == null) return pHead1;

        if (pHead2.val > pHead1.val){
            pHead1.next = Merge(pHead1.next, pHead2);
            return pHead1;
        } else {
            pHead2.next = Merge(pHead1, pHead2.next);
            return pHead2;
        }

    }

    /**
     * 空间O(1)的思路
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode Merge2 (ListNode pHead1, ListNode pHead2) {

        if(pHead1 == null) return pHead2;
        if(pHead2 == null) return pHead1;

        ListNode dummy = new ListNode(-1);  // 虚拟头结点
        ListNode res = dummy;  // 哨兵结点

        // 必须保证两个list都不为空时才进入循环
        while (pHead1!=null && pHead2!=null){
            if (pHead1.val > pHead2.val){
                dummy.next = pHead2;  // 小的赋值
                pHead2 = pHead2.next;  // 然后结点后移
                dummy = dummy.next;  // 虚拟结点也后移
            } else if (pHead1.val <= pHead2.val){
                dummy.next = pHead1;
                pHead1 = pHead1.next;
                dummy = dummy.next;
            }
        }
        // list1后面还有，就把剩下的全部拿走
        if(pHead1 != null) {
            dummy.next = pHead1;
        }
        if(pHead2 != null) {
            dummy.next = pHead2;
        }

        return res.next;
        // return dummy.next 错误的输出
    }
}
