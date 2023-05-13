package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/11 10:47
 */
//https://leetcode.cn/problems/island-perimeter/
public class 岛屿的周长 {

    static class Solution {
        /**
         * 方法一：迭代
         * 作者：LeetCode-s2022.Solution
         * 链接：https://leetcode.cn/problems/island-perimeter/solution/dao-yu-de-zhou-chang-by-leetcode-solution/
         */
        static int[] dx = {0, 1, 0, -1};  //dx和dy对应下标组合，分别控制右一格，下一格，左一格，上一格
        static int[] dy = {1, 0, -1, 0};

        public int islandPerimeter1(int[][] grid) {
            int n = grid.length, m = grid[0].length;
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (grid[i][j] == 1) {
                        int cnt = 0;
                        //当上面判断该格子是陆地后，判断周围的四个格子是什么
                        for (int k = 0; k < 4; ++k) {
                            int tx = i + dx[k];
                            int ty = j + dy[k];
                            //五个条件分别为；最左边列格子，最右边列格子，最上行格子，最下行的格子，边上的一个格子为水域。
                            //五个条件满足其一能周长加一
                            if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                                cnt += 1;
                            }
                        }
                        ans += cnt;
                    }
                }
            }
            return ans;
        }

        //方法二：双重for循环
        public int islandPerimeter2(int[][] grid) {
            int land = 0; //陆地计数
            int edge = 0; //陆地接触的边缘计数
            int m = grid.length, n = grid[0].length; //m为行数，n为列数
            for (int i=0; i<m; i++){
                for (int j=0; j<n; j++){
                    if (grid[i][j]==1){
                        land++;
                        //只判断每个格子的右侧格子 和 下方格子是什么，如果是陆地则边缘+1
                        if (i<m-1 && grid[i+1][j]==1) edge++;
                        if (j<n-1 && grid[i][j+1]==1) edge++;
                    }
                }
            }
            //一个陆地四个边界乘以四，每遇到一个陆地的话每个格子就少一个，因为相邻是两个就是edge*2
            return land*4 - edge*2;
        }

    }
}
