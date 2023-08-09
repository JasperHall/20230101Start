/**
 * https://www.nowcoder.com/practice/886370fe658f41b498d40fb34ae76ff9?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/9 21:21
 */
public class 链表中倒数最后k个结点 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 双指针
     * @param pHead ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        // write code here

        ListNode slow = pHead; // 慢指针
        ListNode fast = pHead; // 快指针, 先走 k 步

        for (int i = 0; i < k; i++) {
            if (fast != null){   // 注意这里加判断, 一开始没考虑到
                fast = fast.next;
            } else {
                return slow = null;
            }

        }

        while (fast != null){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
