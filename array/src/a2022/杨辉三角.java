package a2022;

import java.util.ArrayList;
import java.util.List;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/10/28 23:36
 */
//https://leetcode.cn/problems/pascals-triangle/
public class 杨辉三角 {
    public static void main(String[] args) {
        int a = 3;
        Solution solution = new Solution();
        System.out.println(solution.getRow(a));
    }
    static class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> ret = new ArrayList<List<Integer>>();  //ret最外层列表
            for (int i = 0; i < numRows; ++i) {
                List<Integer> row = new ArrayList<Integer>();  //row内层列表
                for (int j = 0; j <= i; ++j) {
                    if (j == 0 || j == i) {
                        row.add(1);
                    } else {
                        row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));  //ret为最外层列表，i-1控制层数，j-1控制第几个元素
                    }
                }
                ret.add(row);  //内层列表加一个数
            }
            return ret;
        }

        //杨辉三角II
        public List<Integer> getRow(int rowIndex) {
            List<Integer>  res = new ArrayList<Integer>();//结果列表

            for (int i = 0; i <= rowIndex; i++) {
                List<Integer> ret = new ArrayList<Integer>();//辅助列表
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {
                        ret.add(1);
                    } else {
                        ret.add(res.get(j - 1) + res.get(j));
                    }
                }
                res = ret;
            }
            return res;
        }
    }
}
