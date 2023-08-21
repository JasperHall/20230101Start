import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/8/21 11:11
 */
public class TestDemo {
    public static void main(String[] args) {
        BufferedReader buf = null ;		// 声明对象
        buf = new BufferedReader(new InputStreamReader(System.in)) ;	// 将字节流变为字符流
        String str = null ;	// 接收输入内容
        System.out.print("请输入内容：") ;
        try{
            str = buf.readLine() ;	// 读取一行数据
        }catch(IOException e){
            e.printStackTrace() ;	// 输出信息
        }
        System.out.println("输入的内容为：" + str) ;
    }
}