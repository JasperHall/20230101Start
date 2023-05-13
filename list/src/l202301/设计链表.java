package l202301;

/**
 * https://leetcode.cn/problems/design-linked-list/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/26 19:43
 */
public class 设计链表 {

    /**
     * 单链表
     */
    class OneListNode {
        int val;
        OneListNode next;
        OneListNode(){}
        OneListNode(int val) {
            this.val=val;
        }
    }
    class MyLinkedList {
        //都使用设置虚拟头节点来操作链表

        int size;//size存储链表元素的个数
        OneListNode head;//虚拟头结点


        //初始化链表
        public MyLinkedList() {
            size = 0;
            head = new OneListNode(0);
        }

        /**
         * 获取链表中第 index 个节点的值。如果索引无效，则返回-1。
         *
         * 注意index是从0开始的，第0个节点就是头结点
         * @param index
         * @return
         */
        public int get(int index) {
            //如果index非法，返回-1
            if (index<0 || index>=size){
                return -1;
            }
            OneListNode currentNode = head;//current当前的
            //包含一个虚拟头节点，所以查找第index+1个节点
            for (int i=0; i<=index; i++){
                currentNode = currentNode.next;
            }
            return currentNode.val;
        }

        /**
         * 在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
         * @param val
         */
        public void addAtHead(int val) {
            //在链表最前面插入一个节点，等价于在第0个元素前添加
            addAtIndex(0, val);
        }

        /**
         * 将值为 val 的节点追加到链表的最后一个元素。
         * @param val
         */
        public void addAtTail(int val) {
            //在链表的最后插入一个节点，等价于在(末尾+1)个元素前添加
            addAtIndex(size, val);

        }

        /**
         * 在链表中的第index个节点之前添加值为val的节点。
         * 如果index等于链表的长度，则该节点将附加到链表的末尾。
         * 如果 index 大于链表长度，则不会插入节点。
         * 如果index小于等于0，则在头部插入节点。
         * @param index
         * @param val
         */
        public void addAtIndex(int index, int val) {
            if (index > size){
                return;
            }
            if (index < 0){
                index = 0;
            }
            size++;//???

            //找到要插入节点的前驱
            OneListNode pred = head;
            for (int i=0; i<index; i++){//注意有虚拟头节点的存在，所以要多向后数一位
                pred = pred.next;
            }
            OneListNode toAdd = new OneListNode(val);//将val值赋值给要插入的新节点
            toAdd.next = pred.next;
            pred.next = toAdd;
        }

        /**
         * 如果索引 index 有效，则删除链表中的第 index 个节点。
         * @param index
         */
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            size--;
            if (index == 0) {
                head = head.next;
                return;
            }
            OneListNode pred = head;
            for (int i = 0; i < index ; i++) {
                pred = pred.next;
            }
            pred.next = pred.next.next;

        }
    }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
    //双链表
    class TwoListNode{
        int val;
        TwoListNode next,prev;
        TwoListNode() {};
        TwoListNode(int val){
            this.val = val;
        }
    }
    class MyLinkedList2 {
        //记录链表中元素的数量
        int size;
        //记录链表的虚拟头结点和尾结点
        TwoListNode head,tail;

        public  MyLinkedList2() {
            //初始化操作
            this.size = 0;
            this.head = new TwoListNode(0);
            this.tail = new TwoListNode(0);
            //这一步非常关键，否则在加入头结点的操作中会出现null.next的错误！！！
            head.next=tail;
            tail.prev=head;
        }

        public int get(int index) {
            //判断index是否有效
            if(index<0 || index>=size){
                return -1;
            }
            TwoListNode cur = this.head;
            //判断是哪一边遍历时间更短
            if(index >= size / 2){
                //tail开始
                cur = tail;
                for(int i=0; i< size-index; i++){
                    cur = cur.prev;
                }
            }else{
                for(int i=0; i<= index; i++){
                    cur = cur.next;
                }
            }
            return cur.val;
        }

        public void addAtHead(int val) {
            //等价于在第0个元素前添加
            addAtIndex(0,val);
        }

        public void addAtTail(int val) {
            //等价于在最后一个元素(null)前添加
            addAtIndex(size,val);
        }

        public void addAtIndex(int index, int val) {
            //index大于链表长度
            if(index>size){
                return;
            }
            //index小于0
            if(index<0){
                index = 0;
            }
            size++;
            //找到前驱
            TwoListNode pre = this.head;
            for(int i=0; i<index; i++){
                pre = pre.next;
            }
            //新建结点
            TwoListNode newNode = new TwoListNode(val);
            newNode.next = pre.next;
            pre.next.prev = newNode;
            newNode.prev = pre;
            pre.next = newNode;

        }

        public void deleteAtIndex(int index) {
            //判断索引是否有效
            if(index<0 || index>=size){
                return;
            }
            //删除操作
            size--;
            TwoListNode pre = this.head;
            for(int i=0; i<index; i++){
                pre = pre.next;
            }
            pre.next.next.prev = pre;
            pre.next = pre.next.next;
        }
    }
}
