import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1002
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/20 15:49
 */
public class A加BIII {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (a == 0 && b == 0) {
                break;
            }
            System.out.println(a + b);
        }
    }
}
