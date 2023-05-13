package s202303;

import java.util.*;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/18 21:08
 *
 * https://leetcode.cn/problems/top-k-frequent-elements/
 */
public class 前K个高频元素 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{1,1,2,2,2,3,3,3,3};
        int k = 2;
        int[] res = s.topKFrequent2(nums, k);
        System.out.println(Arrays.toString(res));
    }

    /**
     * Coparator接口说明:
     * 返回负数, 形参中第一个参数排在前面; 返回正数, 形参中第二个参数排在前面
     * 对于队列, 排在前面意味着往对头靠
     * 对于堆(使用PriorityQueue实现): 从对头到队尾按从小到大排就是最小堆(小顶堆
     *              从队头到队尾按从大到小排就是最大堆（大顶堆）--->队头元素相当于堆的根节点
     */
    static class Solution {
        /**
         * 解法一
         * 基于大顶堆实现
         * @param nums
         * @param k
         * @return
         */
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();//key为传入的数组的元素值, val为该值出现的次数

            //给每个元素计算出现得次数
            for (int num : nums){
                map.put(num, map.getOrDefault(num,0)+1); //map.getOrDefault()方法是什么?
            }

            //在优先级队列中存储二元组(num, cnt), cnt表示元素值num在数组中的出现次数
            //出现次数按从 对头 到 队尾 的顺序是 从大到小 排, 出现次数最多的在对头(相当于大顶堆)
            PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair2[1]-pair1[1]);//重点
            for (Map.Entry<Integer, Integer> entry : map.entrySet()){//大顶堆需要对所有元素进行排序
                pq.add(new int[]{entry.getKey(), entry.getValue()});
            }

            int[] ans = new int[k];
            for (int i=0; i<k; i++){//依次从对头弹出k个, 就是出现频率前k高的元素
                ans[i] = pq.poll()[0];
            }
            return ans;
        }

        /**
         * 解法二
         * 使用小顶堆实现
         * @param nums
         * @param k
         * @return
         */
        public int[] topKFrequent2(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();//key为数组元素, val为对应出现次数

            //给每个元素计算出现得次数
            for (int num : nums){
                map.put(num, map.getOrDefault(num, 0)+1);
            }

            //在优先队列中存储二元组(num,cnt),cnt表示元素值num在数组中的出现次数
            //出现次数按从队 头 到 队尾 的顺序是 从小到大 排,出现次数最低的在队头(相当于小顶堆)
//            PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair1[1]-pair2[1]);

            // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
                public int compare(int[] m, int[] n) {
                    return m[1] - n[1];
                }
            });

            System.out.println("pq size = "+pq.size());
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {//小顶堆只需要维持k个元素有序

                if (pq.size() < k){ //小顶堆元素个数小于k个时直接加
                    pq.add(new int[]{entry.getKey(), entry.getValue()});
                }else {
                    if (entry.getValue() > pq.peek()[1]) {//当前元素出现次数 大于 小顶堆的根节点(这k个元素中出现次数最少的那个
                        pq.poll();//弹出 队头(小顶堆的根结点),即把堆里出现次数最少的那个删除,留下的就是出现次数多的了
                        pq.add(new int[]{entry.getKey(),entry.getValue()});
                    }
                }
            }
            int[] ans = new int[k];
            for (int i=k-1; i>=0; i--){//依次弹出小顶堆,先弹出的是堆的根,出现次数少,后面弹出的出现次数多
                ans[i] = pq.poll()[0];
            }
            return ans;
        }
    }
}
