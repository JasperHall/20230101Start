package Algorithm;

/**
 * https://www.nowcoder.com/practice/70610bf967994b22bb1c26f9ae901fa2?tpId=13
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/17 20:00
 */
public class 数字在升序数组中出现的次数 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 二分法
     * @param nums int整型一维数组
     * @param k int整型
     * @return int整型
     */
    public int GetNumberOfK (int[] nums, int k) {
        // write code here

        // 非降序数组
        if (nums.length==0 || k < nums[0]) return 0;

        //分别查找 k+0.5 和 k-0.5 应该出现的位置，中间的部分就全是k
        int rightIndex = count(nums, k+0.5);
        int leftIndex = count(nums, k-0.5);
        return rightIndex - leftIndex;  // 右减左
    }

    public int count(int[] data, double k){

        int left = 0;
        int right = data.length-1;

        while (left <= right){  // 注意这里为 <=

            int mid = (left+right)/2;

            if (data[mid] < k){
                left = mid + 1;  // 注意这里是+1
            } else if (data[mid] > k){
                right = mid - 1;  // 这里是-1
            }
        }

        return left;
    }
}
