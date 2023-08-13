package DataStructure;

import java.util.Arrays;

/**
 * https://www.nowcoder.com/practice/a861533d45854474ac791d90e447bafd?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/13 22:40
 */
public class 二叉搜索树的后序遍历序列 {

    /**
     *
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0) return false;
        return jiaoyan(sequence);
    }

    public boolean jiaoyan(int[] sequence){
        // 递归终止条件: 数组为空时停止
        if (sequence.length == 0) return true;

        int index = 0;
        // 获取根节点  因为是后序遍历得到的数组, 所以最后一个数就是根节点
        int root = sequence[sequence.length-1];

        // 先找到左子树的分界点
        while (index < sequence.length-1){
            // 比根节点大的一定在根节点的右子树, 所以直接break循环
            if (sequence[index] > root) break;

            index++;
        }

        // 程序能执行到此处，说明index 左边的元素全是左子树， index 右边的元素全是右子树

        int temp = index;
        while(temp<sequence.length-1){  // 先校验右子树中是否全部元素大于根。
            if(sequence[temp]<root){  // 一旦有右子树有小于的就不用在往后判断了, 因为一定不是二叉搜索树了
                return false;
            }
            temp++;
        }

        // 递归校验左子树          复制出一个左闭右开区间的数组
        boolean left = jiaoyan(Arrays.copyOfRange(sequence,0,index));
        // 递归校验右子树
        boolean right = jiaoyan(Arrays.copyOfRange(sequence,index,sequence.length-1));


        return left && right;
    }
}
