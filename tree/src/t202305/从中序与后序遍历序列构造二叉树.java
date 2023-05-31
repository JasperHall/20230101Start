package t202305;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/31 0:22
 */
public class 从中序与后序遍历序列构造二叉树 {  //106
    class Solution {
        /**
         * 解法一
         * @param inorder
         * @param postorder
         * @return
         */
        Map<Integer, Integer> map;  // 方便根据数值查找位置
        public TreeNode buildTree(int[] inorder, int[] postorder) {  // inorder中序数组 , postorder后序数组
            map = new HashMap<>();
            for (int i=0; i<inorder.length; i++){
                map.put(inorder[i], i);
            }
            return findNode(inorder, 0, inorder.length, postorder, 0, postorder.length);// 注意区间的左闭右开
        }
        private TreeNode findNode(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd){
            // 参数里的范围都是前闭后开
            if (inBegin>=inEnd || postBegin>=postEnd){  // 不满足左闭右开，说明没有元素，返回空树
                return null;
            }

            int rootIndex = map.get(postorder[postEnd-1]); // 找到后序遍历的最后一个元素在中序遍历中的位置, 利用了map的操作
            TreeNode root = new TreeNode(inorder[rootIndex]);  // 构造节点
            int lenOfLeft = rootIndex - inBegin;  // 保存中序左子树个数，用来确定后序数列的个数

            // 下面分别递归 root 的左右方向
            root.left = findNode(inorder, inBegin, rootIndex, postorder, postBegin, postBegin+lenOfLeft);
            // 注意理解下面rootIndex+1的用法, postEnd-1是为了去除最后一个元素
            root.right = findNode(inorder, rootIndex+1, inEnd, postorder, postBegin+lenOfLeft, postEnd-1);

            return root;
        }

        /**
         * 解法二
         * @param inorder
         * @param postorder
         * @return
         */
        public TreeNode buildTree2(int[] inorder, int[] postorder) {  // inorder中序数组 , postorder后序数组
            if(postorder.length == 0 || inorder.length == 0) return null;
            return buildHelper(inorder, 0, inorder.length, postorder, 0, postorder.length);
        }
        private TreeNode buildHelper(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postorderStart, int postorderEnd){
            // 第一步
            if(postorderStart == postorderEnd) return null;

            // 第二步：后序遍历数组最后一个元素，就是当前的中间节点
            int rootVal = postorder[postorderEnd - 1];
            TreeNode root = new TreeNode(rootVal);

            // 第三步：找切割点,找到中序遍历的切割点
            int middleIndex;
            for (middleIndex = inorderStart; middleIndex < inorderEnd; middleIndex++){
                if(inorder[middleIndex] == rootVal) break;  // 找到中序数组中和后序中相等的位置
            }

            // 切割数组, 坚持区间 左闭右开
            // 第四步: 切割中序数组, 得到 中序左数组 和 中序右数组
            int leftInorderStart = inorderStart;  // 中序左数组起点, 就是本次递归传入的中序左数组起点
            int leftInorderEnd = middleIndex;  // 中序左数组终点, 中序左数组的终点就是到刚找到的中间节点的位置

            int rightInorderStart = middleIndex + 1;  // 中序右数组起点, 中序右数组的起点就是从刚找到的中间节点的下一个位置开始
            int rightInorderEnd = inorderEnd;  // 中序右数组终点, 就是整个中序数组的终点


            // 第五步: 切割后序数组，得到 后序左数组 和 后序右数组
            int leftPostorderStart = postorderStart;  // 后序左数组起点
            int leftPostorderEnd = postorderStart + (middleIndex - inorderStart);  // 后序左数组终点,

            int rightPostorderStart = leftPostorderEnd;  // 后序右数组起点, 后序左数组终点就是(因为是左闭右开的区间)
            int rightPostorderEnd = postorderEnd - 1;  // 后序右数组终点, 这里减一是去除最后一个元素, 舍弃末尾元素，因为这个元素就是中间节点，已经用过了

            // 第六步:递归处理左区间和右区间
            root.left = buildHelper(inorder, leftInorderStart, leftInorderEnd,  postorder, leftPostorderStart, leftPostorderEnd); // 这里都填左区间的值
            root.right = buildHelper(inorder, rightInorderStart, rightInorderEnd, postorder, rightPostorderStart, rightPostorderEnd);// 这里都填右区间的值

            return root;
        }


    }
}
