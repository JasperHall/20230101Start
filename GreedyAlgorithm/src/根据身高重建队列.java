import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/queue-reconstruction-by-height/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/29 23:46
 */
public class 根据身高重建队列 {

    class Solution {
        public int[][] reconstructQueue(int[][] people) {
            // 身高从大到小排（身高相同k小的站前面）
            Arrays.sort(people, (a, b) -> {
                if (a[0] == b[0]) return a[1]-b[1];  // a - b 是升序排列，故在a[0] == b[0]的狀況下，会根据k值升序排列
                return b[0] - a[0];  //b - a 是降序排列，在a[0] != b[0]，的状况会根据h值(身高)降序排列
            });

            LinkedList<int[]> que = new LinkedList<>();

            for (int[] p : people) {
                que.add(p[1],p);   //LinkedList.add(index, value)，將value插入到指定index。
            }

            return que.toArray(new int[people.length][]);  // 注意这里数组创建和转换的方法
        }
    }
}
