package Algorithm;

/**
 * https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/17 20:28
 */
public class 二维数组中的查找 {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[][] n = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        int[][] n ={{}};
        boolean res = solution.Find(7, n);
        System.out.println(res);
    }

    static class Solution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         * @param target int整型
         * @param array  int整型二维数组
         * @return bool布尔型
         */
        public boolean Find(int target, int[][] array) {
            // write code here

            int m = array.length;  // 高
            int n = array[0].length;  // 宽

            if (n==0) return false;  // 注意这一步一定要判断

            if (target < array[0][0]) return false;

            int s = 0;
            int count = 0;
            for (int i = 0; i < m; i++) {

                for (int j = 0; j < n; j++) {

                    if (array[i][j] == target) {
                        return true;
                    } else if (array[i][j] > target) {
                        break;
                    }
                }
            }
            return false;

        }
    }
}
