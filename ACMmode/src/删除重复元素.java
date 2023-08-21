import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1019
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/21 22:22
 */
public class 删除重复元素 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String[] str = sc.nextLine().split(" ");
            if (Integer.parseInt(str[0]) == 0) System.out.print("list is empty");

            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            // 构造链表
            for (int i=1; i<str.length; i++){
                ListNode temp = new ListNode(Integer.parseInt(str[i]));
                cur.next = temp;
                cur = cur.next;
                if(i == str.length-1) cur.next = null;
            }

            // 输出处理前的链表
            ListNode pointer = dummy.next;
            while(pointer != null){
                System.out.print(pointer.val + " ");
                pointer = pointer.next;
            }
            System.out.println();

            ListNode fast = dummy.next;
            ListNode slow = dummy;
            while(fast != null){
                if (slow != null && fast.val == slow.val){
                    slow.next = fast.next;
                    fast = fast.next;
                } else {  // 注意这里加else, 思考作用
                    fast = fast.next;
                    slow = slow.next;
                }
            }

            // 输出新的链表
            ListNode pointer2 = dummy.next;
            while(pointer2 != null){
                System.out.print(pointer2.val + " ");
                pointer2 = pointer2.next;
            }
            System.out.println();

        }
    }
}
