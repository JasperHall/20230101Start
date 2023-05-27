package s202305;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/top-k-frequent-elements/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/5/27 15:39
 */
public class 前k个高频元素 {  // 347
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>(); // key为数组元素, val为对应出现次数

            // 给map中的每个元素计算出现次数
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            //在优先队列中存储二元组(num,cnt),cnt表示元素值num在数组中的出现次数
            //出现次数按从队 头 到 队尾 的顺序是 从 小到大 排序,出现次数最低的在队头(相当于小顶堆)
//            PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair1[1]-pair2[1]);

            // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] m, int[] n) {
                    return m[1]-n[1];  // Comparator比较器, 升序排序
                }
            });

            System.out.println("pq size = " + pq.size());

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {  // 小顶堆只需要维持k个元素有序
                if (pq.size() < k){  // 小顶堆元素个数小于k个时直接加
                    pq.add(new int[]{entry.getKey(), entry.getValue()});
                } else {
                    if (entry.getValue() > pq.peek()[1]) {  // 当前元素出现次数 大于 小顶堆的根节点(这k个元素中出现次数最少的那个
                        pq.poll();  // 弹出队头(即小顶堆的根结点),即把堆里出现次数最少的那个删除,留下的就是出现次数多的了
                        pq.add(new int[]{entry.getKey(),entry.getValue()});
                    }
                }
            }

            int[] ans = new int[k];
            for (int i=k-1; i>=0; i--){  // 依次弹出小顶堆,先弹出的是堆的根,出现次数少,后面弹出的出现次数多
                ans[i] = pq.poll()[0];
            }
            return ans;

        }
    }
}
