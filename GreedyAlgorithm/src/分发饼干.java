import java.util.Arrays;

/**
 * https://leetcode.cn/problems/assign-cookies/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/6/21 20:48
 */
public class 分发饼干 {  // 455. 分发饼干
    class Solution {
        // 思路1：优先考虑饼干，小饼干先喂饱小胃口
        public int findContentChildren(int[] g, int[] s) { // g是胃口, s是饼干
            Arrays.sort(g);
            Arrays.sort(s);

            int start = 0 , count = 0;  // count用来记录得到满足的小孩个数

            // 遍历饼干
            for (int i=0; i<s.length && start<g.length; i++){
                if (s[i] >= g[start]){
                    start++;
                    count++;
                }
            }
            return count;
        }

        // 思路2：优先考虑胃口，先喂饱大胃口
        public int findContentChildren2(int[] g, int[] s) { // g是胃口, s是饼干
            Arrays.sort(g);
            Arrays.sort(s);
            int count = 0;
            int start = s.length - 1;
            // 遍历胃口
            for (int index = g.length - 1; index >= 0; index--) {
                if(start >= 0 && g[index] <= s[start]) {
                    start--;
                    count++;
                }
            }
            return count;
        }

    }
}
