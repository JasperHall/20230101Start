package DataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.nowcoder.com/practice/91b69814117f4e8097390d107d2efbe0?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/11 21:34
 */
public class 按之字形顺序打印二叉树 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param pRoot TreeNode类
     * @return int整型ArrayList<ArrayList<>>
     */
    public ArrayList<ArrayList<Integer>> Print (TreeNode pRoot) {
        // write code here

        TreeNode head = pRoot;
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (head == null) return res;  //如果是空，则直接返回空list

        Queue<TreeNode> temp = new LinkedList<>();  // 队列存储, 进行层序遍历
        temp.offer(head);

        TreeNode p;
        boolean flag = true;

        while (!temp.isEmpty()){
            ArrayList<Integer> row = new ArrayList<Integer>();  // 记录二叉树的某一行
            int len = temp.size();
            flag = !flag;//奇数行反转，偶数行不反转

            //因先进入的是根节点，故每层节点多少，队列大小就是多少
            for(int i = 0; i < len; i++){
                p = temp.poll();  // 弹出. 先弹出左,再弹右
                row.add(p.val);
                //若是左右孩子存在，则存入左右孩子作为下一个层次
                if(p.left != null) temp.offer(p.left);  // 先加左, 再加右
                if(p.right != null) temp.offer(p.right);
            }

            //奇数行反转，偶数行不反转, 示例: 第一次进来时false不翻转, 第二次进来是true翻转
            if(flag) Collections.reverse(row);
            res.add(row);
        }
        return res;

    }
}
