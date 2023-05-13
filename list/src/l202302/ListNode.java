package l202302;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/2/1 11:39
 */
/**
 * 创建节点类
 */
public class ListNode {
    // 结点的值
    int val;//数值 data

    // 下一个结点
    ListNode next;// 结点 node

    // 节点的构造函数(无参)
    public ListNode() {
    }

    // 节点的构造函数(有一个参数)
    public ListNode(int val) {//可以定义一个有参构造方法，也可以定义一个无参构造方法
        this.val = val;
    }

    // 节点的构造函数(有两个参数)
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
