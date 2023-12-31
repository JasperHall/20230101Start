package kamacoder;

/**
 * 思路：直接模拟
 *  * 将鱼存入数组中，判断当前数组是否是递增的。
 *  * 如果不是，代表可以执行一次大鱼吃小鱼操作。
 *  *
 *  * 具体实现为从后向前扫描数组，如果某一位数字的
 *  * 前一位数字大于它，则代表该数字对应的鱼会被吃掉。
 *  * 将该数字从数组中移除。
 *  *
 *  * 再次判断数组是否递增，如果递增，则代表已经达到了
 *  * 继续操作后数量仍然不变的状态。
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/10 18:08
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class 大鱼吃小鱼 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();  // 读走回车
        List<Integer> list = new LinkedList<>();  // 列表的底层实现选择链表实现，方便删除操作
        for (int i = 0; i < N; i++) {
            list.add(scanner.nextInt());
        }
        int count = 0;  // 记录需要多少次大鱼吃小鱼操作
        while (!isIncreasing(list)) {
            for (int i = list.size() - 1; i >= 1; i--) {
                if (list.get(i) < list.get(i - 1)) { // 如果右边的小于左边的，会被吃掉
                    list.remove(i);
                }
            }
            count++;
        }
        System.out.println(count);
    }
    // 判断数组是否递增
    public static boolean isIncreasing(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

}
