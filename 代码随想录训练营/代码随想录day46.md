时间: 2023.6.24 周六
<a name="sPFyY"></a>
# 今日任务
第九章 动态规划part08, 139.单词拆分 , 关于多重背包，你该了解这些! ,  背包问题总结篇！  
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划


<a name="XBsr0"></a>
# 复习

- [ ] <br />

详细布置 <br />关于 多重背包，力扣上没有相关的题目，所以今天大家的重点就是回顾一波 自己做的背包题目吧。 
<a name="EvdHO"></a>
# 139.单词拆分 
:::info
视频讲解：[动态规划之完全背包，你的背包如何装满？| LeetCode：139.单词拆分_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1pd4y147Rh)<br />关于多重背包，你该了解这些！ [代码随想录](https://programmercarl.com/%E8%83%8C%E5%8C%85%E9%97%AE%E9%A2%98%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80%E5%A4%9A%E9%87%8D%E8%83%8C%E5%8C%85.html)
:::
对于多重背包，此时力扣上还没发现对应的题目，所以做一下简单介绍，大概了解一下。<br />有 N种物品 和 一个容量为V 的背包。第i种物品最多有Mi件可用，每件耗费的空间是Ci ，价值是Wi 。求解将哪些物品装入背包可使这些物品的耗费的空间 总和 不超过背包容量，且价值总和最大。<br />多重背包和01背包是非常像的， 为什么和01背包像呢？每件物品最多有Mi件可用，把Mi件摊开，其实就是一个01背包问题了。
:::info
例题<br />背包最大重量为10。<br />物品为：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1691414675275-7da4344f-3700-43f1-8d0d-c8cf5150d9c7.png#averageHue=%23f5f6f7&clientId=u78b28842-5c51-4&from=paste&height=152&id=u61034b42&originHeight=178&originWidth=333&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=7770&status=done&style=shadow&taskId=u3782bf05-6591-41cb-bee5-ee79cc10cd2&title=&width=284.6153950550151)<br />问背包能背的物品最大价值是多少？和如下情况有区别么？<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1691414772162-cc23c070-210e-4135-a63f-55cea830038a.png#averageHue=%23f2f3f2&clientId=u78b28842-5c51-4&from=paste&height=317&id=u1c92503a&originHeight=371&originWidth=316&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=14653&status=done&style=shadow&taskId=u1fa9ec04-9df1-4586-83f2-948b7307f73&title=&width=270.0854799921465)
:::
毫无区别，这就转成了一个01背包问题了，且每个物品只用一次。<br />这种方式来实现多重背包的代码如下：
```cpp
void test_multi_pack() {
    vector<int> weight = {1, 3, 4};
    vector<int> value = {15, 20, 30};
    vector<int> nums = {2, 3, 2};
    int bagWeight = 10;
    for (int i = 0; i < nums.size(); i++) {
        while (nums[i] > 1) { // nums[i]保留到1，把其他物品都展开
            weight.push_back(weight[i]);
            value.push_back(value[i]);
            nums[i]--;
        }
    }

    vector<int> dp(bagWeight + 1, 0);
    for(int i = 0; i < weight.size(); i++) { // 遍历物品
        for(int j = bagWeight; j >= weight[i]; j--) { // 遍历背包容量
            dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
        }
        for (int j = 0; j <= bagWeight; j++) {
            cout << dp[j] << " ";
        }
        cout << endl;
    }
    cout << dp[bagWeight] << endl;

}
int main() {
    test_multi_pack();
}

时间复杂度：O(m × n × k)，m：物品种类个数，n背包容量，k单类物品数量
```
也有另一种实现方式，就是把每种商品遍历的个数放在01背包里面在遍历一遍。代码如下：（详看注释）
```cpp
void test_multi_pack() {
    vector<int> weight = {1, 3, 4};
    vector<int> value = {15, 20, 30};
    vector<int> nums = {2, 3, 2};
    int bagWeight = 10;
    vector<int> dp(bagWeight + 1, 0);


    for(int i = 0; i < weight.size(); i++) { // 遍历物品
        for(int j = bagWeight; j >= weight[i]; j--) { // 遍历背包容量
            // 以上为01背包，然后加一个遍历个数
            for (int k = 1; k <= nums[i] && (j - k * weight[i]) >= 0; k++) { // 遍历个数
                dp[j] = max(dp[j], dp[j - k * weight[i]] + k * value[i]);
            }
        }
        // 打印一下dp数组
        for (int j = 0; j <= bagWeight; j++) {
            cout << dp[j] << " ";
        }
        cout << endl;
    }
    cout << dp[bagWeight] << endl;
}
int main() {
    test_multi_pack();
}

时间复杂度：O(m × n × k)，m：物品种类个数，n背包容量，k单类物品数量
```
从代码里可以看出是**01背包**里面在加一个for循环遍历一个每种商品的数量。 和01背包还是如出一辙的。<br />当然还有那种二进制优化的方法，其实就是把每种物品的数量，打包成一个个独立的包。<br />和以上在循环遍历上有所不同，因为是分拆为各个包最后可以组成一个完整背包，具体原理我就不做过多解释了，大家了解一下就行，面试的话基本不会考完这个深度了，感兴趣可以自己深入研究一波。
```java
public void testMultiPack1(){
    // 版本一：改变物品数量为01背包格式
    List<Integer> weight = new ArrayList<>(Arrays.asList(1, 3, 4));
    List<Integer> value = new ArrayList<>(Arrays.asList(15, 20, 30));
    List<Integer> nums = new ArrayList<>(Arrays.asList(2, 3, 2));
    int bagWeight = 10;

    for (int i = 0; i < nums.size(); i++) {
        while (nums.get(i) > 1) { // 把物品展开为i
            weight.add(weight.get(i));
            value.add(value.get(i));
            nums.set(i, nums.get(i) - 1);
        }
    }

    int[] dp = new int[bagWeight + 1];
    for(int i = 0; i < weight.size(); i++) { // 遍历物品
        for(int j = bagWeight; j >= weight.get(i); j--) { // 遍历背包容量
            dp[j] = Math.max(dp[j], dp[j - weight.get(i)] + value.get(i));
        }
        System.out.println(Arrays.toString(dp));
    }
}

public void testMultiPack2(){
    // 版本二：改变遍历个数
    int[] weight = new int[] {1, 3, 4};
    int[] value = new int[] {15, 20, 30};
    int[] nums = new int[] {2, 3, 2};
    int bagWeight = 10;

    int[] dp = new int[bagWeight + 1];
    for(int i = 0; i < weight.length; i++) { // 遍历物品
        for(int j = bagWeight; j >= weight[i]; j--) { // 遍历背包容量
            // 以上为01背包，然后加一个遍历个数
            for (int k = 1; k <= nums[i] && (j - k * weight[i]) >= 0; k++) { // 遍历个数
                dp[j] = Math.max(dp[j], dp[j - k * weight[i]] + k * value[i]);
            }
            System.out.println(Arrays.toString(dp));
        }
    }
}
```
<a name="IpVKz"></a>
## 总结
多重背包在面试中基本不会出现，力扣上也没有对应的题目，大家对多重背包的掌握程度知道它是一种01背包，并能在01背包的基础上写出对应代码就可以了。<br />至于背包九讲里面还有混合背包，二维费用背包，分组背包等等这些，大家感兴趣可以自己去学习学习，这里也不做介绍了，面试也不会考
<a name="JJ6Ei"></a>
# 背包问题总结篇！ 
[https://programmercarl.com/%E8%83%8C%E5%8C%85%E6%80%BB%E7%BB%93%E7%AF%87.html](https://programmercarl.com/%E8%83%8C%E5%8C%85%E6%80%BB%E7%BB%93%E7%AF%87.html)
