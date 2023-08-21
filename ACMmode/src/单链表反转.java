import javax.annotation.processing.SupportedSourceVersion;
import java.util.List;
import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1018
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/21 21:25
 */
public class 单链表反转 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){  // 这里是不是也能写hasNext
            String[] str = sc.nextLine().split(" ");
            if (Integer.parseInt(str[0]) == 0){
                System.out.println("list is empty");
            }

            ListNode dummyhead = new ListNode(-1);
            ListNode cur = dummyhead;

            // 构造链表
            for (int i=1; i<str.length; i++){
                ListNode temp = new ListNode(Integer.parseInt(str[i]));
                cur.next = temp;
                cur = cur.next;  // 指针向后移动一位
                if (i == str.length -1) cur.next = null; // 最后一个节点
            }

            // 输出原函数
            ListNode pointer = dummyhead.next;
            while (pointer != null){
                System.out.print(pointer.val + " ");  // 不换行输出
                pointer = pointer.next;
            }
            System.out.println();  // 换行


            // 翻转链表
            ListNode pre = null;
            ListNode cur2 = dummyhead.next;
            while (cur2 != null){
                ListNode nextnode = cur2.next;
                cur2.next = pre;
                pre = cur2;  // pre后移一位
                cur2 = nextnode; // cur2后移一位
            }
            ListNode pointer2 = pre;
            while (pointer2 != null){
                System.out.print(pointer2.val + " ");  // 不换行输出
                pointer2 = pointer2.next;
            }
            System.out.println();
        }
    }
}
