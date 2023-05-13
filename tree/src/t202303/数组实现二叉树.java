package t202303;

/**
 * @Version 1.0
 * @Author:MenFanys
 * @Date:2023/3/26 21:14
 */
public class 数组实现二叉树 {
    public static void main(String[] args) {
        int i, level;
        int[] data = {6,3,5,9,7,8,4,2};//原始数组
        int[] btree = new int[16];

        for (i = 0; i<btree.length; i++){
            btree[i] = 0;
        }
        System.out.println("原始数组的内容:");
        for (i=0; i<data.length; i++){
            System.out.print("["+data[i]+"]"); //打印原始数组
        }
        System.out.println();

        for (i=0; i<data.length; i++){  //把原始数组中的值逐一对比

            for (level=1; btree[level]!=0;){  //比较树根及数组内的值   这里level从1开始的

                if (data[i] > btree[level]){//如果数组内的值大于树根, 则往右子树比较
                    level = level*2+1;
                }else {  // 如果数组内的值小于或等于树根, 则往左子树比较
                    level=level*2;
                }
            }//  如果子树节点的值不为0, 则再与数组内的值比较一次

            btree[level] = data[i];//把数组值放入二叉树
        }
        System.out.println("二叉树的内容:");
        for (i = 1; i< btree.length; i++) {  //注意这里初始值 i=1;
            System.out.print("["+btree[i]+"]");
        }
    }
}
