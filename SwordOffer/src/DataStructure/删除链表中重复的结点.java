package DataStructure;

import javax.sound.midi.MidiFileFormat;

/**
 * https://www.nowcoder.com/practice/fc533c45b73a41b0b44ccba763f866ef?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/9 21:42
 */
public class 删除链表中重复的结点 {

    public ListNode deleteDuplication(ListNode pHead) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (pHead != null){
            // 进入循环时，确保了 pHead 不会与上一节点相同
            if (pHead.next==null || pHead.next.val!=pHead.val){
                tail.next = pHead;
                tail = pHead;
            }

            // 如果 pHead 与下一节点相同，跳过相同节点（到达「连续相同一段」的最后一位）
            while (pHead.next != null && pHead.val == pHead.next.val) {  // 注意这里是while循环
                pHead = pHead.next;
            }
            pHead = pHead.next;
        }
        tail.next = null;
        return dummy.next;
    }
}
