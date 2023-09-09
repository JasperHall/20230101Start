import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

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

        System.out.println(Arrays.toString(intArray));// Creating an empty Stack
        Stack<String> stack = new Stack<String>();

        // Use add() method to add elements into the Stack
        stack.add("Welcome");
        stack.add("To");
        stack.add("Geeks");
        stack.add("For");
        stack.add("Geeks");

        // Displaying the Stack
        System.out.println("The Stack:" + stack);

        // Creating the array and using toArray()
        Object[] arr = stack.toArray();

        // Displaying arr
        System.out.println("The arr[] is:");
        for (int j = 0; j < arr.length; j++) System.out.print(arr[j]+" ");
    }
}
