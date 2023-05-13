package t202303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/26 21:42
 */
public class 链表实现二叉树 {
    public static void main(String[] args) throws IOException {
        int ArraySize = 10;
        int tempdata;
        int [] content = new int[ArraySize];
        BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请连续输入 " + ArraySize + " 个数据");

        for (int i=0; i<ArraySize; i++){
            System.out.println("请输入第 " + (i+1) +"个数据: ");
            tempdata = Integer.parseInt(keyin.readLine());
            content[i] = tempdata;
        }
        new BinaryTree(content);  //通过构造方法将数值加入到二叉树
        System.out.println("使用链表方式建立二叉树, 成功!!!");
    }
}
