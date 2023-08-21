import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * https://kamacoder.com/problem.php?id=1010
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/21 12:00
 */
public class 共同祖先 {
    public static void main(String[] args){
        Map<Integer, Integer> map = new HashMap<>();
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            for (int i=0; i<n; i++){
                int a = in.nextInt();
                int b = in.nextInt();
                map.put(a,b);
            }

            int xiaoming = 1;
            int count1 = 0;
            while(map.containsKey(xiaoming)){
                xiaoming = map.get(xiaoming); // 替换一下,把父亲替换过来
                count1++;
            }

            int xiaoyu = 2;  // 注意这里是2
            int count2 = 0;
            while(map.containsKey(xiaoyu)){
                xiaoyu = map.get(xiaoyu); // 替换一下,把父亲替换过来
                count2++;
            }

            if (count1 < count2){
                System.out.println("You are my younger");
            } else if (count1 > count2){
                System.out.println("You are my elder");
            } else {
                System.out.println("You are my brother");
            }
        }
    }
}
