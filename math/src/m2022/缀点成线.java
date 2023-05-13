package m2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/24 16:44
 */
//https://leetcode.cn/problems/check-if-it-is-a-straight-line/
public class 缀点成线 {
    public static void main(String[] args) {
        int[][] arr= new int[][]{{1,1},{2,2},{3,4},{4,5},{5,6},{7,7}};
        Solution s = new Solution();
        System.out.println(s.checkStraightLine2(arr));

    }
    static class Solution {
        //错误解法，判断的情况不全（斜率有不存在的情况）
        public boolean checkStraightLine1(int[][] coordinates) {
            int x0 = coordinates[0][0], y0 = coordinates[0][1];
            int x1 = coordinates[1][0], y1 = coordinates[1][1];
            double k=0;
            if (x1!=x0) {
                 k= (double)(y1 - y0) / (x1 - x0);
            }

            int n = coordinates.length;
            for(int i=2; i<n; i++){
                int y = coordinates[i][1];
                int x = coordinates[i][0];
                double k2=0;
                if (x!=x1) {
                    k2 = (double) (y - y0) / (x - x0);
                }
                if (k2 !=k){
                    return false;
                }
            }
            return true;
        }

        //解法二：斜率会有不存在的情况，所以把连个斜率相等的除法求法转化为乘法
        public boolean checkStraightLine2(int[][] coordinates) {
            if (coordinates == null) return false;//不存在点
            if (coordinates.length ==1 || coordinates.length==2) return true;
            //若在一条直线上，斜率必相等
            //即(y2-y1)/(x2-x1)=(yi-y2)/(xi-x2)
            //当直线平行y轴时有除零风险，固交叉相乘改变为乘法
            //即(y2-y1)(xi-x2)=(yi-y2)(x2-x1)

            int y = coordinates[1][1]-coordinates[0][1];
            int x = coordinates[1][0]-coordinates[0][0];
            for (int i=2; i<coordinates.length; i++){
                if (y*(coordinates[i][0]-coordinates[1][0]) != (coordinates[i][1]-coordinates[1][1])*x){
                    return false;
                }
            }
            return true;
        }
    }
}
