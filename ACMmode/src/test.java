import java.util.Arrays;
import java.util.Scanner;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/9/6 15:18
 */
public class test {
    public static void main(String[] args) {
        String s = "[1,4,2,4,2,6,2,7]";

        String[] stringArray = s.replaceAll("[\\[\\]\\s]", "").split(",");  // 注意这里是用的replaceAll()不是replace()

        int[] intArray = new int[stringArray.length];

        for (int i=0; i< stringArray.length; i++){
            intArray[i] = Integer.parseInt(stringArray[i]);
        }

        System.out.println(Arrays.toString(intArray));
    }
}
