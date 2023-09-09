package kamacoder;

import javafx.geometry.Side;

import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1017
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/21 20:17
 */
public class 链表的基本操作 {
    /**
     * 链表节点
     */
    static class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
        }
    }
    /**
     * 链表
     */
    static class LinkedList{
        private Node head;
        private int size;

        public LinkedList(){
            this.head = null;
            this.size = 0;
        }

        /**
         * 头插法
         * @param val 要插入的值
         */
        public void addFirst(int val){
            Node node = new Node(val);
            node.next = head;
            head = node;
            size++;  // 记得给长度加一
        }

        /**
         * 获取链表元素
         * @param idx 索引
         * @return
         */
        public int get(int idx){  //
            int cnt = 0;  // 计数, 寻找索引
            Node curr = head;
            while (curr != null){
                if (cnt == idx){
                    return curr.val;  // 获取成功,返回值
                }
                cnt++;
                curr = curr.next;
            }
            return -1;  // 没获取到, 返回-1
        }

        /**
         * 删除链表节点
         * @param idx 索引
         * @return
         */
        public boolean delete(int idx){
            if (head == null || idx<0 || idx>=size) return false;

            if (idx == 0){
                head = head.next;
            } else {
                int cnt = 0;
                Node curr = head;
                while (cnt < idx -1){
                    curr = curr.next;
                    cnt++;
                }
                curr.next = curr.next.next;
            }
            size--;  // 记得给长度减一
            return true;
        }

        /**
         * 插入元素
         * @param idx 插入位置的索引
         * @param val 值
         * @return
         */
        public boolean insert(int idx, int val){
            if (idx < 0 || idx > size) return false;  // 插入位置索引不合法, 直接返回false

            if (idx == 0){ // 插入位置为0时
                addFirst(val);  // 调用头插法方法
                return true;
            }

            int cnt = 0;
            Node curr = head;
            while (curr!=null && cnt<idx-1){ // 此循环最后一步为cnt = idx, 到达正确索引处
                curr = curr.next;
                cnt++;
            }
            if (curr == null) return false;  // 如果该位置的节点为null,则返回false

            Node node = new Node(val);
            node.next = curr.next;
            curr.next = node;
            size++;  // 记得长度加一
            return true;
        }

        /**
         * 输出链表
         */
        public void show(){
            if (head == null){
                System.out.println("Link list is empty");
                return;
            }

            Node curr = head;
            while (curr != null){
                System.out.print(curr.val + " ");  // 注意这里为不换行输出
                curr = curr.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList linkedList = new LinkedList();

        int n = sc.nextInt();
        for (int i=0; i<n; i++){
            int num = sc.nextInt();
            linkedList.addFirst(num);
        }

        int m = sc.nextInt(); // m代表接下来会输入m行指令对链表进行操作
        for (int i=0; i<m; i++){
            String operation = sc.next();

            if ("get".equals(operation)){
                int a = sc.nextInt();
                int result = linkedList.get(a-1);  //题目中a从1开始计数, 实际索引从0开始, 所以要-1
                if (result != -1) {
                    System.out.println(result);
                } else {
                    System.out.println("get fail");
                }
            } else if ("delete".equals(operation)) {
                int a = sc.nextInt();
                boolean deleteResult = linkedList.delete(a-1); //题目中a从1开始计数, 实际索引从0开始, 所以要-1
                if (deleteResult){
                    System.out.println("delete OK");
                } else {
                    System.out.println("delete fail");
                }
            } else if ("insert".equals(operation)){
                int a = sc.nextInt();  // 在第a个位置前面插入e。
                int e = sc.nextInt();
                boolean insertResult = linkedList.insert(a-1, e);
                if (insertResult) {
                    System.out.println("insert OK");
                } else {
                    System.out.println("insert fail");
                }
            } else if ("show".equals(operation)){
                linkedList.show();
            }
        }
        sc.close();
    }

}
