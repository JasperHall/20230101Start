import java.util.ArrayList;

/**
 * https://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/7 22:18
 */
public class 从尾到头打印链表 {

    /**
     * 非递归
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode temp = listNode;

        while (temp != null){
            list.add(0, temp.val);
            temp = temp.next;
        }
        return list;
    }

    /**
     * 递归实现
     * @param listNode
     * @return
     */
    ArrayList<Integer> list = new ArrayList();
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {

        if(listNode != null){
            printListFromTailToHead2(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }

}
