package kamacoder;

import java.util.Scanner;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/10 15:56
 */
public class 不相同的字符串 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i=0; i<n; i++){
            String str = sc.next();
            System.out.println(minOperations(str));
        }
    }

    /**
     * 查询最小次数
     * @param str
     * @return
     */
    private static int minOperations(String str){
        int[] hashMap = new int[26];

        // 初始化数组
        for (int i=0; i<str.length(); i++){  // 遍历字符串
            int position = str.charAt(i) - 'a';  // 寻找字符对应数组中的位置 a -> 0,b -> 1,...,z -> 25
            hashMap[position]++;
        }

        int charUseCount =0;   // 记录 26 个位置被使用的次数
        int result = 0;  // 需要操作的次数

        // 遍历数组
        for(int i=0; i<hashMap.length; i++){
            if (hashMap[i] == 1) {  // 字符只出现一次，不需要操作，占用掉一个位置
                charUseCount++;
            }
            if (hashMap[i] % 2 == 0) {  // 字符出现偶数次，需要操作 n/2 次，并且占用 n / 2 个位置
                charUseCount += hashMap[i] /2;
                result += hashMap[i] / 2;
            }
            if (hashMap[i] != 1 && hashMap[i] % 2 ==1){  // 字符出现奇数次，需要操作 n/2 次，并且占用 n / 2 + 1 个位置
                result += hashMap[i] / 2;
                charUseCount += hashMap[i] / 2 + 1;
            }
        }
        if (charUseCount > 26) {  // 二十六个位置不够使用时
            result += charUseCount - 26;
        }
        return result;
    }
}
