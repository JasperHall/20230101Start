package a202301;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/find-k-closest-elements/
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/1/11 14:09
 */


public class 六五八题 {//题目:找到 K 个最接近的元素

    class Solution {
        /**
         * 方法一：二分+双指针
         * 没看懂
         * 容易想到先通过「二分」找到与 x 差值最小的位置 idx，然后从 idx 开始使用「双指针」
         * 往两边进行拓展（初始化左端点 i = idx - 1，右端点 j = idx + 1），
         * 含义为 [i + 1, j - 1] 范围内子数组为候选区间，不断根据两边界与 x 的差值关系进行扩充，
         * 直到候选区间包含 k 个数。
         *
         * 作者：AC_OIer
         * 链接：https://leetcode.cn/problems/find-k-closest-elements/solution/by-ac_oier-8xh5/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         * @param arr
         * @param k
         * @param x
         * @return
         */
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int n = arr.length, l=0, r = n-1;
            while (l < r){
                int mid = (l + r + 1) /2;//也可以写：int mid = l + r + 1 >> 1;
                if(arr[mid] <= x){
                    l =mid;
                }else {
                    r = mid-1;
                }
            }

            r = r+1 < n && Math.abs(arr[r+1]-x) < Math.abs(arr[r]-x) ? r+1 : r;
            int i = r-1, j=r+1;
            while (j-i-1 < k){
                if (i>=0 && j<n){
                    if (Math.abs(arr[j]-x) < Math.abs(arr[i]-x)) j++;
                    else i--;
                } else if (i >= 0) {
                    i--;
                }else {
                    j++;
                }
            }
            List<Integer> ans = new ArrayList<>();
            for (int p=i+1; p<=j-1; p++) ans.add(arr[p]);
            return ans;
        }

        /**
         * 二分+双指针
         * 假设数组长度为 n，注意到数组 arr 已经按照升序排序，我们可以将数组 arr 分成两部分，前一部分所有元素
         * [0,left] 都小于 x，后一部分所有元素 [right,n−1] 都大于等于 x，left 与 right 都可以通过二分查找获得。
         * left 和 right 指向的元素都是各自部分最接近 x 的元素，因此我们可以通过比较 left 和 right 指向的元素获取整体最接近 x 的元素。
         * 如果 x−arr[left]≤arr[right]−x，那么将 left 减一，否则将 right s2022.加一。
         * 相应地，如果 left 或 right 已经越界，那么不考虑对应部分的元素。
         * 最后，区间 [left+1,right−1] 的元素就是我们所要获得的结果，返回答案既可。
         *
         * 作者：LeetCode-s2022.Solution
         * 链接：https://leetcode.cn/problems/find-k-closest-elements/solution/zhao-dao-k-ge-zui-jie-jin-de-yuan-su-by-ekwtd/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         * @param arr
         * @param k
         * @param x
         * @return
         */
        public List<Integer> findClosestElements3(int[] arr, int k, int x) {
            int right = binarySearch(arr, x);
            int left = right - 1;
            while (k-- > 0) {//记录找几个数
                if (left < 0) {
                    right++;
                } else if (right >= arr.length) {
                    left--;
                } else if (x - arr[left] <= arr[right] - x) {
                    left--;
                } else {
                    right++;
                }
            }
            List<Integer> ans = new ArrayList<Integer>();
            for (int i = left + 1; i < right; i++) {
                ans.add(arr[i]);
            }
            return ans;
        }

        public int binarySearch(int[] arr, int x) {
            int low = 0, high = arr.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (arr[mid] >= x) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            return low;
        }

        /**
         * 直接双指针
         */
        public List<Integer> findClosestElements2(int[] arr, int k, int x) {
            List<Integer> list = new ArrayList<>();
            int n = arr.length;
            int left = 0;
            int right = n - 1;
            while (right - left + 1 != k) {
                if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                    right--;
                } else {
                    left++;
                }
            }
            for (int i = left; i <= right; i++) {
                list.add(arr[i]);
            }
            return list;
        }
    }
}
