import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//https://leetcode.cn/problems/fizz-buzz/
public class FizzBuzz {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        String [] fizzBuzz = new String[n1];


        for(int i=1; i<=n1; i++){
            if(i%3==0 && i%5==0){
                fizzBuzz[i-1] = "FizzBuzz";
            }else if(i%3==0){
                fizzBuzz[i-1] = "Fizz";
            }else if(i%5==0){
                fizzBuzz[i-1] = "Buzz";
            }else{
                String s =  String.valueOf(i);
                fizzBuzz[i-1] = s;
            }
        }

        System.out.println(Arrays.toString(fizzBuzz));


        Solution solution = new Solution();
        System.out.println("列表:"+solution.fizzBuzz(n1));
    }

    static class Solution {
        public List<String> fizzBuzz(int n) {
            List<String> answer = new ArrayList<String>();
            for(int j=1;j<=n;j++){
                if(j%3==0&&j%5==0){
                    answer.add("FizzBuzz");
                }
                else if(j%3==0){
                    answer.add("Fizz");
                }
                else if(j%5==0){
                    answer.add("Buzz");
                }
                else{
                    answer.add(Integer.toString(j));
                }
            }
            return answer;
        }
    }

}
