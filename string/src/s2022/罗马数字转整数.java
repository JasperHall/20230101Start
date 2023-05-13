package s2022;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2022/11/7 22:38
 */
public class 罗马数字转整数 {

    public int romanToInt1(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));//preNum代表前一个字符

        for (int i=1; i<s.length(); i++){
            int num = getValue(s.charAt(i));//num是从第二个字符开始获取

            if (preNum < num){  //如果前一个字符小，则ix=9  0-1=-1
                sum -= preNum;
            }else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;

    }
    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
