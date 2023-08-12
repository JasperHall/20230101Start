package DataStructure;

import sun.reflect.generics.tree.Tree;

/**
 * https://www.nowcoder.com/practice/6e196c44c7004d15b1610b9afca8bd88?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/12 21:53
 */
public class 树的子结构 {

    /**
     * 递归判断
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        // 题目: 我们约定空树不是任意一个树的子结构
        //空树
        if(root2 == null)
            return false;
        //一个为空，另一个不为空
        if(root1 == null && root2 != null)
            return false;
        if(root1 == null || root2 == null)
            return true;

        boolean flag1 = isSame(root1, root2);  // 传入另一个方法进行判断
        boolean flag2 = HasSubtree(root1.left, root2);  //
        boolean flag3 = HasSubtree(root1.right, root2);

        return flag1 || flag2 || flag3;  // 有一个为真便是真
    }
    // 判断结构相同必须需要的函数
    public boolean isSame(TreeNode root1, TreeNode root2){
        if (root2 == null) return true; // 注意理解这一步 一旦root到了null 的位置, 只需要判断该点的val相等就可以了, 所以返回true才去并集不影响结果
        if (root1 == null) return false;

        boolean flag1 = (root1.val == root2.val);  // 判断结点值相同
        boolean flag2 = isSame(root1.left, root2.left);  // 判断左子树相同
        boolean flag3 = isSame(root1.right, root2.right);  // 判断右子树相同

        //      判断结点值相同                判断左子树相同                       判断右子树相同
        // return root1.val== root2.val && isSame(root1.left, root2.left) && isSame(root1.right, root2.right);
        return flag1 && flag2 && flag3;
    }


}
