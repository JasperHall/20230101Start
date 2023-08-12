package DataStructure;

/**
 * 创建节点类
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/7 22:19
 */
public class ListNode {
    // 节点的值
    int val;// 数值 data

    // 下一个节点
    ListNode next;// 节点 node

    // 节点的构造函数(无参)
    public ListNode(){}

    // 节点的构造函数(有一个参数)
    public ListNode(int val){
        this.val = val;
    }

    // 节点的构造函数(有两个参数)
    public ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }
}