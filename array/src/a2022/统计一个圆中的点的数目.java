package a2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/24 8:02
 */
//https://leetcode.cn/problems/queries-on-number-of-points-inside-a-circle/
public class 统计一个圆中的点的数目 {

    //方法一：
    public int[] countPoint1(int[][] points, int[][] queries){
        //存放points中 点在圆中的个数
        int count=0;
        int[] array = new int[queries.length];
        //两点间的距离公式，r的平方与(a-b)*(a-b)+(x-y)*(x-y)比值，确定点是否在圆内
        for (int i=0; i<queries.length; i++){
            //判断在园内的点
            int[] query=queries[i];//取queries的i行
            int x1 = query[0];
            int y1 = query[1];
            for (int j=0; j<points.length; j++){
                int[] point = points[j];//取points的第j行
                int x2 = point[0];
                int y2 = point[1];
                if ((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)<query[2]*query[2]){  //query[2]每行的第三个数，表示半径
                    count++;
                }
            }
            array[i]=count;//将刚进行完的一轮count值存入数组
            count=0;//count归零
        }
        return  array;
    }
    //方法二：
    public int[] countPoints2(int[][] points, int[][] queries) {
        int queriesNum = queries.length;
        int pointsNum = points.length;
        int[] ans = new int[queriesNum];

        // Brute force for each circle and iterate overall points and find those inside it.
        for (int i = 0; i < queriesNum; i++) {
            int cnt = 0;
            int[] query = queries[i];
            for (int j = 0; j < pointsNum; j++) {
                int[] point = points[j];
                // For a point to be inside a circle, the euclidean distance between it and the circle's center needs to be less than or equal to the radius.
                // a*2 + b*2 = c*2
                if (Math.sqrt(Math.pow((point[0] - query[0]), 2) + Math.pow((point[1] - query[1]), 2)) <= query[2]) {
                    cnt++;
                }
            }
            ans[i] = cnt;
        }
        return ans;

    }
}
