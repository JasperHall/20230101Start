import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/8 15:21
 */
public class 两个链表的第一个公共结点 {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode n1 = pHead1, n2 = pHead2;

        while (n1 != n2){
            n1 = (n1==null) ? pHead2 : n1.next;
            n2 = (n2==null) ? pHead1 : n2.next;
        }

        return n1;
    }

    /**
     * 栈
     * @param a
     * @param b
     * @return
     */
    public ListNode FindFirstCommonNode2(ListNode a, ListNode b) {

        Deque<ListNode> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();
        while (a != null) {
            d1.add(a);
            a = a.next;
        }
        while (b != null) {
            d2.add(b);
            b = b.next;
        }

        ListNode ans = null;  // 结果
        while (!d1.isEmpty() && !d2.isEmpty() && d1.peekLast() == d2.peekLast()) {  // peek返回但不删除
            ans = d1.pollLast();  // poll返回并删除
            d2.pollLast();
        }
        return ans;
    }
}
