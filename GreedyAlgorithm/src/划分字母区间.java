import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/partition-labels/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/7/10 21:20
 */
public class 划分字母区间 {  // 763. 划分字母区间
    class Solution {
        public List<Integer> partitionLabels(String s) {
            List<Integer> list = new LinkedList<>();
            int[] edge = new int[26];  // 用来记录字母的最后出现下标
            char[] chars = s.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                edge[chars[i] - 'a'] = i;  // 更新字母的索引位置
            }

            int idx = 0;
            int last = -1;

            for (int i=0; i<chars.length; i++){
                idx = Math.max(idx, edge[chars[i]-'a']);  // 取当前位置的字母的索引最大值

                if (i == idx){  // 当当前位置的索引值和区间中字母索引出现最后位置最大值相等的时候
                    list.add(i-last); // 结果列表加上一个目前该该位置的长度
                    last = i;  // 更新下次用来计算长度的位置
                }
            }
            return list;
        }
    }
}
