package s2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/11 8:57
 */
public class 整数转罗马数字 {


    public static void main(String[] args) {
        int num = 58;
        solution solution = new solution();
        System.out.println(solution.intToRoman(num));
    }


    static class solution {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        public String intToRoman(int num) {
            String roman = "";
            for (int i = 0; i < values.length; i++) {

                while (num >= values[i]) {
                    num = num - values[i];
                    roman = roman + symbols[i];
                }

                if (num == 0) {
                    break;
                }
            }
            return roman;
        }
    }
}
