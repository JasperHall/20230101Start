package l202305;

/**
 * https://leetcode.cn/problems/design-linked-list/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/13 20:19
 */
public class 设计链表 {  // 707

    class MyLinkedList {
        //都使用设置虚拟头节点来操作链表
        int size; // size存储链表元素的个数
        ListNode head; // 虚拟头结点

        /**
         * 初始化 MyLinkedList 对象。
         * 初始化链表
         */
        public MyLinkedList() {
            size = 0;
            head = new ListNode(0);
        }

        /**
         * 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
         * 获取第index个节点的数值，注意index是从0开始的，第0个节点就是头结点
         * @param index
         * @return
         */
        public int get(int index) {
            // 如果index非法，返回-1
            if (index < 0 || index >= size){
                return -1;
            }
            ListNode currentNode = head; //current当前的

            // 包含一个虚拟头节点，所以查找第index+1个节点
            for (int i=0; i<=index; i++){  // 注意这里是 <= , 因为index从0开始, 所用这里有等于号
                currentNode = currentNode.next;
            }
            return currentNode.val;
        }

        /**
         * 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
         *
         * 在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
         *      * @param index
         * @param val
         */
        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        /**
         * 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
         * @param val
         */
        public void addAtTail(int val) {
            //在链表的最后插入一个节点，等价于在(末尾+1)个元素前添加
            addAtIndex(size, val);
        }

        /**
         * 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。
         * 如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。
         * 如果 index 比长度更大，该节点将 不会插入 到链表中。
         *
         * 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
         * 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
         * 如果 index 大于链表的长度，则返回空
         *
         * @param val
         */
        public void addAtIndex(int index, int val) {
            if (index > size){
                return;
            }
            if (index < 0){
                index = 0; // 就是插入在头节点之前
            }

            size++;  // 为什么随想录的题解会写这一行???  size是定义的全局的,因为插入后会多一位, 所以要++

            // 找到要插入节点的前驱
            ListNode predecessor = head;
            for (int i=0; i<index; i++){
                predecessor = predecessor.next; // 注意: 有虚拟头节点的存在, 所以要向后多next一下
            }
            ListNode toAdd = new ListNode(val);
            toAdd.next = predecessor.next;
            predecessor.next = toAdd;
        }

        /**
         * 如果下标有效，则删除链表中下标为 index 的节点。
         *
         * 如果索引 index 有效，则删除链表中的第 index 个节点。
         * @param index
         */
        public void deleteAtIndex(int index) {
            if (index < 0 || index>= size){
                return;
            }

            size--;  // size是定义的全局的,因为插入后会少一位, 所以要--

            if (index == 0){
                head = head.next;
                return;
            }
            ListNode predecessor = head;
            for (int i=0; i<index; i++){
                predecessor = predecessor.next;
            }
            predecessor.next = predecessor.next.next;
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

    class TwoListNode{
        int val;
        TwoListNode next, prev;
        TwoListNode(){}
        TwoListNode(int val){
            this.val = val;
        }
    }

    class MyLinkedList2{
        // 记录链表中元素的数量
        int size;
        // 记录链表的虚拟头结点和尾结点
        TwoListNode head, tail;

        public MyLinkedList2(){
            // 初始化操作
            this.size = 0;
            this.head = new TwoListNode(0);
            this.tail = new TwoListNode(0);
            // 这一步非常关键，否则在加入头结点的操作中会出现null.next的错误！！！
            head.next = tail;
            tail.prev = head;
        }
        /**
         * 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
         * 获取第index个节点的数值，注意index是从0开始的，第0个节点就是头结点
         * @param index
         * @return
         */
        public int get(int index){
            // 判断index是否有效
            if(index<0 || index>size) return -1;

            TwoListNode cur = this.head;
            // 判断是哪一边, 遍历时间更短
            if (index >= size/2){  // 说明index在中点之后, 从后往前遍历更好
                // tail 开始
                cur = tail;
                for (int i=0; i<size-index; i++){
                    cur = cur.prev; // 前一个
                }
            } else {
                for (int i=0; i<=index; i++){ // 因为index从0开始, 所用这里有等于号
                    cur = cur.next;
                }
            }
            return cur.val;
        }


        /**
         * 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
         *
         * 在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
         */
        public void addAtHead(int val){
            // 等价于在第0个元素前添加
            addAtIndex(0, val);
        }

        /**
         * 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
         */
        public void addAtTail(int val) {
            // 等价于在最后一个元素(null)前添加
            addAtIndex(size,val);
        }

        /**
         * 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。
         * 如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。
         * 如果 index 比长度更大，该节点将 不会插入 到链表中。
         *
         * 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
         * 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
         * 如果 index 大于链表的长度，则返回空
         */
        public void addAtIndex(int index, int val) {
            // index大于链表长度
            if (index>size) return;
            // index小于0
            if (index<0) index=0;

            size++; //接下来要加入元素, 所以链表的size++
            // 找到前驱
            TwoListNode pre = this.head;
            for (int i=0; i<index; i++){ // 这里不是 <= , 所以是找到index所在坐标的前驱, 加了=就是找到index坐标所在的元素
                pre = pre.next;
            }

            // 新建节点
            TwoListNode newNode = new TwoListNode(val);
            newNode.next = pre.next;
            pre.next.prev = newNode;
            newNode.prev = pre;
            pre.next = newNode;
        }

        /**
         * 如果下标有效，则删除链表中下标为 index 的节点。
         *
         * 如果索引 index 有效，则删除链表中的第 index 个节点。
         */
        public void deleteAtIndex(int index) {
            // 判断索引是否有效
            if (index<0 || index>=size) return;

            size--;  // 删除操作会使得原链表size--

            TwoListNode pre = this.head;
            for (int i=0; i<index; i++){
                pre = pre.next;
            }
            pre.next.next.prev = pre;
            pre.next = pre.next.next;

        }
    }
}
