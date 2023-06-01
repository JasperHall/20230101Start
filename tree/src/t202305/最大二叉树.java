package t202305;

/**
 * https://leetcode.cn/problems/maximum-binary-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/31 17:48
 */
public class 最大二叉树 {  // 654

    class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return constructMaximumBinaryTreeDemo(nums, 0, nums.length);
        }
        private TreeNode constructMaximumBinaryTreeDemo(int[] nums, int leftIndex, int rightIndex){
            if (rightIndex - leftIndex < 1){  // 没有元素了
                return null;
            }

            if (rightIndex - leftIndex == 1){  // 只有一个元素
                return new TreeNode(nums[leftIndex]);  // 直接返回该元素的树
            }

            int maxIndex = leftIndex; // 最大值所在的位置(索引)
            int maxValue = nums[maxIndex];  // 数组中的最大值

            for (int i=leftIndex+1; i<rightIndex; i++){  // 注意这里的右边界是 i<rightIndex
                if (nums[i] > maxValue){
                    maxIndex = i;
                    maxValue = nums[i];
                }
            }

            TreeNode root = new TreeNode(maxValue);

            // 根据maxValue分成左右子树   左闭右开的区间
            root.left = constructMaximumBinaryTreeDemo(nums, leftIndex, maxIndex);
            root.right = constructMaximumBinaryTreeDemo(nums, maxIndex + 1, rightIndex);

            return root;
        }

    }
}
