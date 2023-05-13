import java.util.Scanner;
//https://leetcode.cn/problems/richest-customer-wealth/
public class 最富有客户的资产总量 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int [][] a = new int[m][n];
        for (int i =0; i<m; i++){
            for (int j=0; j<n; j++){
                a[i][j] = in.nextInt();
            }
        }
        Solution solution = new Solution();

        int value = solution.maximumWealth(a);
        System.out.println(value);

    }

    static class Solution {
        public int maximumWealth(int[][] accounts) {
            int l = accounts.length, l2 = accounts[0].length, ans =0;

            for (int i = 0; i<l; i++){
                int cur =0;
                for (int j =0; j<l2; j++){
                    cur = cur+accounts[i][j];
                }
                ans = Math.max(ans,cur);

            }
            return ans;

        }
    }

}
