package t202306;

/**
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/2 21:45
 */
public class 将有序数组转换为二叉搜索树 {  // 108. 将有序数组转换为二叉搜索树
    class Solution {
        /**
         * 递归
         * 数组区间:左闭右开
         * @param nums
         * @return
         */
        public TreeNode sortedArrayToBST(int[] nums) {
            return sortedArrayToBST(nums, 0, nums.length);
        }
        private TreeNode sortedArrayToBST(int[] nums, int left, int right){
            if (left >= right){  // 终止条件, 这里定义的是左闭右开的区间，所以当区间 left >= right的时候，就是空节点了
                return null;
            }

            if (right-left == 1){
                return new TreeNode(nums[left]);
            }

            int mid = left + (right - left) / 2; // 注意这里取中间值的处理方法, 这样可以避免int值越界
            TreeNode root = new TreeNode(nums[mid]);
            root.left = sortedArrayToBST(nums, left, mid);  // 注意这里的右边界是 mid, 左闭右开
            root.right = sortedArrayToBST(nums, mid+1, right);  // 注意这里的左边界是 mid+1, 左闭右开

            return root;
        }
    }
}
