package t202305;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode.cn/problems/find-mode-in-binary-search-tree/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/1 10:41
 */
public class 二叉搜索树中的众数 {  // 501. 二叉搜索树中的众数

    class Solution {
        /**
         * 暴力法
         * @param root
         * @return
         */
        public int[] findMode(TreeNode root) {
            Map<Integer, Integer> map = new HashMap<>();
            List<Integer> list = new ArrayList<>();

            if(root == null) return list.stream().mapToInt(Integer::intValue).toArray();

            // 获得频率 Map
            searchBST(root, map);  // 调用自定义的递归函数

            List<Map.Entry<Integer, Integer>> mapList
                    = map.entrySet().stream().sorted((c1,c2) -> c2.getValue().compareTo(c1.getValue())).collect(Collectors.toList());
            list.add(mapList.get(0).getKey());  // 这里先加一下最大的, 方便接下来的比较

            // 把频率最高的加入List
            for (int i=1; i<mapList.size(); i++){  // i的初始值为1, 使用上面你加入的索引0的位置的值做第一次比较
                if (mapList.get(i).getValue() == mapList.get(i-1).getValue()){
                    list.add(mapList.get(i).getKey());
                }else {
                    break;
                }
            }
            return list.stream().mapToInt(Integer::intValue).toArray();
        }
        // Map<Integer,Integer>  key:元素  value:出现频率
        private void searchBST(TreeNode curr, Map<Integer,Integer> map){  // 前序遍历
            if (curr == null) return;

            map.put(curr.val, map.getOrDefault(curr.val,0) + 1);  // 统计元素频率存入map中

            searchBST(curr.left, map);
            searchBST(curr.right, map);
        }


        /**
         * 利用二叉搜索树的性质
         * @param root
         * @return
         */
        ArrayList<Integer> resList;
        int maxCount;
        int count;
        TreeNode pre;
        public int[] findMode2(TreeNode root) {
            resList = new ArrayList<>();
            maxCount = 0;
            count = 0;
            pre = null;

            findResult(root);

            int[] res = new int[resList.size()];
            for (int i=0; i<resList.size(); i++){
                res[i] = resList.get(i);
            }
            return res;
        }
        private void findResult(TreeNode root){
            if (root == null) {
                return;
            }
            findResult(root.left);  // 左
                    // 中
            int rootValue = root.val;
            // 计数
            if (pre == null || rootValue != pre.val) { // 第一个节点 或 与上一个节点的值不同
                count = 1;  // 重新开始计数
            } else {
                count++;
            }

            // 更新结果以及maxCount   注意理解这里if的判断逻辑
            if (count > maxCount) {
                resList.clear();  // 对ArrayList进行操作
                resList.add(rootValue);
                maxCount = count;
            } else if (count == maxCount) {
                resList.add(rootValue);
            }

            pre = root;

            findResult(root.right);  // 右
        }
    }
}
