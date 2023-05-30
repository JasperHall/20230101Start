package t202305;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/binary-tree-paths/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/30 14:09
 */
public class 二叉树的所有路径 {  // 257
    class Solution {
        /**
         * 回溯
         * 前序遍历
         * @param root
         * @return
         */
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();  // 存最终结果
            if (root == null) return res;

            List<Integer> paths = new ArrayList<>();  // 作为结果中的路径

            traversal(root, paths, res);
            return res;

        }
        private void traversal(TreeNode root, List<Integer> paths, List<String> result){
            paths.add(root.val);  // 前序遍历, 中

            // 遇到叶子节点
            if (root.left==null && root.right==null){
                // 输出
                StringBuilder sb = new StringBuilder();  // StringBuilder用来拼接字符串，速度更快
                for (int i=0; i<paths.size()-1; i++){  // 这里 i<paths.size()-1 的原因是:最后一个路径后面不能用 -> , 所以要在循环后面单独加append
                    sb.append(paths.get(i)).append("->");
                }
                sb.append(paths.get(paths.size()-1));  // 记录最后一个节点(叶子节点)

                result.add(sb.toString());  // sb转为字符串, 每组路径是结果列表的一个元素
                return;
            }

            // 递归和回溯是同时进行，所以要放在同一个花括号里
            if (root.left != null){
                traversal(root.left, paths, result);
                paths.remove(paths.size()-1);  // 回溯是要从path列表中删除已经遍历的节点
            }
            if (root.right != null){
                traversal(root.right, paths, result);
                paths.remove(paths.size()-1);
            }
        }

        /**
         * 回溯 精简版
         * @param root
         * @return
         */
        public List<String> binaryTreePaths2(TreeNode root) {
            List<String> res = new ArrayList<>();  // 存最终结果
            if (root == null) return res;

            String paths=""; // 作为结果中的路径

            traversal2(root, paths, res);
            return res;
        }
        private void traversal2(TreeNode root, String paths, List<String> result){
            paths += String.valueOf(root.val);

            if (root.left==null && root.right==null){
                result.add(paths);
                return;
            }

            if (root.left != null) traversal2(root.left, paths+"->", result);
            if (root.right != null) traversal2(root.right, paths+"->", result);
        }
    }
}
