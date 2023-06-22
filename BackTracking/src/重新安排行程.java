import java.rmi.MarshalledObject;
import java.util.*;

/**
 * https://leetcode.cn/problems/reconstruct-itinerary/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/12 10:37
 */
public class 重新安排行程 {  // 332. 重新安排行程

    class Solution {

        private Deque<String> res; // 记录返回结果
        private Map<String, Map<String, Integer>> map;  // Map<出发机场, Map<到达机场, 航班次数>> map

        public List<String> findItinerary(List<List<String>> tickets) {
            map = new HashMap<String, Map<String, Integer>>(); // 初始化Map
            res = new LinkedList<>(); // 初始化res

            for (List<String> t : tickets) {
                Map<String, Integer> temp;
                if (map.containsKey(t.get(0))){  // t.get(0); 取得出发机场
                    temp = map.get(t.get(0)); // map.get(key)是获取对应key 的 value
                    temp.put(t.get(1), temp.getOrDefault(t.get(1), 0) + 1);  // 方法获取指定 key 对应对 value，如果找不到 key ，则返回设置的默认值。
                } else {
                    temp = new TreeMap<>(); // 升序Map
                    temp.put(t.get(1), 1);  // t.get(1); 取得到达机场, 后面的1为航班次数为1
                }
                map.put(t.get(0), temp);  //
            }

            res.add("JFK"); // 规定起始点是 JFK
            backTracking(tickets.size());
            return new ArrayList<>(res);
        }
        private boolean backTracking(int ticketNum){
            // 终止条件, 我们回溯遍历的过程中，遇到的机场个数，如果达到了（航班数量+1），那么我们就找到了一个行程，把所有航班串在一起了。
            if(res.size() == ticketNum + 1){
                return true;
            }

            String last = res.getLast();
            if(map.containsKey(last)){//防止出现null
                for(Map.Entry<String, Integer> target : map.get(last).entrySet()){
                    int count = target.getValue();
                    if(count > 0){  // 记录到达机场是否飞过了
                        res.add(target.getKey());
                        target.setValue(count - 1);
                        if(backTracking(ticketNum)) {return true;}
                        res.removeLast();
                        target.setValue(count);
                    }
                }
            }
            return false;
        }
    }
}
