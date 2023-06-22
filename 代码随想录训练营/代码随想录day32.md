时间: 2023.6.10周六
<a name="sPFyY"></a>
# 今日任务
第八章 贪心算法 part02, 122.买卖股票的最佳时机II , 55. 跳跃游戏 , 45.跳跃游戏II 
<a name="CXkSh"></a>
# 收获
<a name="KOISD"></a>
# 明天计划

- [ ] 45. 跳跃游戏 II 自己再推导一下

<a name="XBsr0"></a>
# 复习

- [ ] 376. 摆动序列  的动态规划解法没看懂
- [ ] 455.分发饼干  两种不同的遍历判断方式

<a name="unUY6"></a>
# 122.买卖股票的最佳时机II  
:::info
本题解法很巧妙，大家可以看题思考一下，在看题解。 <br />[https://programmercarl.com/0122.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAII.html](https://programmercarl.com/0122.%E4%B9%B0%E5%8D%96%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E4%BD%B3%E6%97%B6%E6%9C%BAII.html)
:::
122. 买卖股票的最佳时机 II<br />明确  只能买一只股票, 当前只有买股票和卖股票的操作  想获得利润至少要两天为一个交易单元。
<a name="h7iZe"></a>
## 贪心算法
这道题目可能我们只会想，选一个低的买入，再选个高的卖，再选一个低的买入.....循环反复。如果想到其实最终利润是可以分解的，那么本题就很容易了！<br />如何分解呢？ 假如第 0 天买入，第 3 天卖出，那么利润为：`prices[3] - prices[0]`。相当于`(prices[3] - prices[2]) + (prices[2] - prices[1]) + (prices[1] - prices[0])`。**此时就是把利润分解为每天为单位的维度，而不是从 0 天到第 3 天整体去考虑！**<br />那么根据 prices 可以得到每天的利润序列：`(prices[i] - prices[i - 1]).....(prices[1] - prices[0])`。<br />如图：<br /> ![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687401191971-9367e188-3e75-45e6-9feb-85846f598e77.png#averageHue=%23f2f2f2&clientId=uc6de5e01-9acd-4&from=paste&height=287&id=ud63a2016&originHeight=336&originWidth=769&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=45467&status=done&style=none&taskId=udadc47fa-7db6-4340-9d5f-b8bffbdb18e&title=&width=657.2649813732932)<br />一些同学陷入：第一天怎么就没有利润呢，第一天到底算不算的困惑中。第一天当然没有利润，至少要第二天才会有利润，所以**利润的序列比股票序列少一天**！<br />从图中可以发现，其实我们需要收集每天的正利润就可以，收集正利润的区间，就是股票买卖的区间，而我们只需要关注最终利润，不需要记录区间。<br />那么只收集正利润就是贪心所贪的地方！

- **局部最优**：收集每天的正利润，
- **全局最优**：求得最大利润。

局部最优可以推出全局最优，找不出反例，试一试贪心！
```java
// 贪心思路
public int maxProfit(int[] prices) {
    int result = 0;
    for (int i=1; i<prices.length; i++){  // 注意这里的遍历是从 1 开始, 因为利润是后一天减前一天
        result += Math.max(prices[i]-prices[i-1], 0);
    }
    return result;
}
时间复杂度：O(n)
空间复杂度：O(1)
```
-
<a name="MerUD"></a>
# 55. 跳跃游戏 
:::info
本题如果没接触过，很难想到，所以不要自己憋时间太久，读题思考一会，没思路立刻看题解 <br />[https://programmercarl.com/0055.%E8%B7%B3%E8%B7%83%E6%B8%B8%E6%88%8F.html](https://programmercarl.com/0055.%E8%B7%B3%E8%B7%83%E6%B8%B8%E6%88%8F.html)
:::
:::info
本题如果没接触过，很难想到，所以不要自己憋时间太久，读题思考一会，没思路立刻看题解 (数组, 贪心, 动态规划)
:::
刚看到本题一开始可能想：当前位置元素如果是 3，我究竟是跳一步呢，还是两步呢，还是三步呢，究竟跳几步才是最优呢？其实跳几步无所谓，关键在于可跳的覆盖范围！<br />不一定非要明确一次究竟跳几步，每次取最大的跳跃步数，这个就是可以跳跃的覆盖范围。这个范围内，别管是怎么跳的，反正一定可以跳过来。<br />那么这个问题就转化为跳跃覆盖范围究竟可不可以覆盖到终点！每次移动取最大跳跃步数（得到最大的覆盖范围），每移动一个单位，就更新最大覆盖范围。

- 贪心算法**局部最优解**：每次取最大跳跃步数（取最大覆盖范围），
- **整体最优解**：最后得到整体最大覆盖范围，看是否能到终点。

局部最优推出**全局最优**，找不出反例，试试贪心！<br />如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687402971137-34820875-2147-4d56-bf89-17fc5be9bd7d.png#averageHue=%23fbe8e8&clientId=uc6de5e01-9acd-4&from=paste&height=266&id=ud52aee40&originHeight=311&originWidth=741&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=33470&status=done&style=none&taskId=ubf7ab497-32a9-4983-9f7f-809edb7793a&title=&width=633.3333565638624)<br />i 每次移动只能在 cover 的范围内移动，每移动一个元素，cover 得到该元素数值（新的覆盖范围）的补充，让 i 继续移动下去。而 cover 每次只取 max(该元素数值补充后的范围, cover 本身范围)。如果 cover 大于等于了终点下标，直接 return true 就可以了。
```java
class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        //覆盖范围, 初始覆盖范围应该是0，因为下面的迭代是从下标0开始的
        int coverRange = 0;
        //在覆盖范围内更新最大的覆盖范围
        for (int i = 0; i <= coverRange; i++) {
            coverRange = Math.max(coverRange, i + nums[i]);
            if (coverRange >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}

时间复杂度: O(n)
空间复杂度: O(1)
```
```java
public class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}

作者：力扣官方题解
链接：https://leetcode.cn/problems/jump-game/solutions/203549/tiao-yue-you-xi-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```
-
<a name="p2hI0"></a>
# 45.跳跃游戏II 
:::info
本题同样不容易想出来。贪心就是这样，有的时候 会感觉简单到离谱，有时候，难的不行，主要是不容易想到。<br />[https://programmercarl.com/0045.%E8%B7%B3%E8%B7%83%E6%B8%B8%E6%88%8FII.html](https://programmercarl.com/0045.%E8%B7%B3%E8%B7%83%E6%B8%B8%E6%88%8FII.html)
:::
:::info
贪心, 数组, 动态规划
:::
这个题目比 55. 跳跃游戏 是更难了的, 但思路是相似的，还是要看最大覆盖范围。<br />本题要计算最小步数，那么就要想清楚什么时候步数才一定要加一呢？<br />贪心的思路，**局部最优**：当前可移动距离尽可能多走，如果还没到终点，步数再加一。**整体最优**：一步尽可能多走，从而达到最小步数。<br />思路虽然是这样，但在写代码的时候还不能真的能跳多远就跳多远，那样就不知道下一步最远能跳到哪里了。<br />所以**真正解题的时候，要从覆盖范围出发，不管怎么跳，覆盖范围内一定是可以跳到的，以最小的步数增加覆盖范围，覆盖范围一旦覆盖了终点，得到的就是最小步数**！<br />这里需**要统计两个覆盖范围，当前这一步的最大覆盖和下一步最大覆盖**。<br />如果移动下标达到了当前这一步的最大覆盖最远距离了，还没有到终点的话，那么就必须再走一步来增加覆盖范围，直到覆盖范围覆盖了终点。如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687404226564-14752aae-edeb-49d4-931e-3ca1c579dcd4.png#averageHue=%23f7ecec&clientId=uc6de5e01-9acd-4&from=paste&height=548&id=u9c4bb5e5&originHeight=641&originWidth=767&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=98959&status=done&style=none&taskId=u4d80dd0b-4e79-4546-b156-70af31eef74&title=&width=655.5555796011909)<br />图中覆盖范围的意义在于，只要红色的区域，最多两步一定可以到！（不用管具体怎么跳，反正一定可以跳到）
<a name="K8OXS"></a>
## 方法一
从图中可以看出来，就是移动下标达到了当前覆盖的最远距离下标时，步数就要加一，来增加覆盖距离。最后的步数就是最少步数。<br />这里还是有个特殊情况需要考虑，当移动下标达到了当前覆盖的最远距离下标时

- 如果当前覆盖最远距离下标**不是**集合终点，步数就加一，还需要继续走。
- 如果当前覆盖最远距离下标**就是**集合终点，步数不用加一，因为不能再往后走了。
```java
// 版本一
class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        
        int count=0;  // 记录跳跃的次数
        int curDistance = 0;  // 当前的覆盖最大区域
        int maxDistance = 0;  // 最大的覆盖区域
        
        for (int i = 0; i < nums.length; i++) {
            maxDistance = Math.max(maxDistance,i+nums[i]);  // 在可覆盖区域内更新最大的覆盖区域
            
            //说明当前一步，再跳一步就到达了末尾
            if (maxDistance>=nums.length-1){ // 覆盖范围超过了终点
                count++;
                break;
            }
            
            //走到当前覆盖的最大区域时，更新下一步可达的最大区域
            if (i==curDistance){
                curDistance = maxDistance;
                count++;
            }
        }
        return count;
    }
}
时间复杂度: O(n)
空间复杂度: O(1)
```
![9A479EAA9D05B6216C45C01D71D2AEA3.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687406190734-372f0bdc-76e1-4101-b777-e61a5a44074d.png#averageHue=%23eae9e5&clientId=uc6de5e01-9acd-4&from=paste&height=547&id=u26bdcf5d&originHeight=640&originWidth=1636&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=123009&status=done&style=none&taskId=ua1e9e36a-f9ad-4413-9f61-f6e5198c8cf&title=&width=1398.2906495795937)-
<a name="sXtzW"></a>
## 方法二
(难理解)<br />依然是贪心，思路和方法一差不多，代码可以简洁一些。<br />针对于方法一的特殊情况，可以统一处理，即：移动下标只要遇到当前覆盖最远距离的下标，直接步数加一，不考虑是不是终点的情况。想要达到这样的效果，只要让移动下标，最大只能移动到 `nums.size - 2` 的地方就可以了。<br />因为当移动下标指向 `nums.size - 2` 时：<br />如果移动下标等于当前覆盖最大距离下标， 需要再走一步（即 ans++），因为最后一步一定是可以到的终点。（题目假设总是可以到达数组的最后一个位置），如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687405277564-56b9b4f7-a383-4953-9003-0101b652169d.png#averageHue=%23f6e8e8&clientId=uc6de5e01-9acd-4&from=paste&height=577&id=u5bd21c6b&originHeight=675&originWidth=573&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=82216&status=done&style=none&taskId=ub53ae4a2-580f-44fc-be4b-f00c6d2b94b&title=&width=489.7436077072782)<br />如果移动下标不等于当前覆盖最大距离下标，说明当前覆盖最远距离就可以直接达到终点了，不需要再走一步。如图：<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687405289045-7f2e0218-8a71-4a4f-89a8-51edcc394960.png#averageHue=%23f6e6e6&clientId=uc6de5e01-9acd-4&from=paste&height=591&id=u6a789834&originHeight=691&originWidth=586&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=79424&status=done&style=none&taskId=udaad4c8b-d4de-475d-a9ca-565b42b0c84&title=&width=500.85471922594246)
```java
// 版本二
class Solution {
    public int jump(int[] nums) {
       int result = 0;
        int end = 0;  // 当前覆盖的最远距离下标
        int temp = 0;  // 下一步覆盖的最远距离下标
        
        for (int i = 0; i<=end && end<nums.length-1; i++) {  // 注意这里end<nums.length-1用的是小于号, 一旦end和数组下标相等或者超过了就结束循环
            System.out.println("i="+i+" i+nums[i]="+(i + nums[i]));
            temp = Math.max(temp, i + nums[i]);
            // 可达位置的改变次数就是跳跃次数
            if (i == end) {
                System.out.println("进来一次");
                end = temp;
                result++;
            }
        }
        return result;
    }
}
时间复杂度: O(n)
空间复杂度: O(1)
```
可以看出版本二的代码相对于版本一简化了不少！<br />![image.png](https://cdn.nlark.com/yuque/0/2023/png/32832913/1687411806267-2b101842-1a12-463e-b6ce-77bbd64cb8bc.png#averageHue=%23373737&clientId=uc6de5e01-9acd-4&from=paste&height=355&id=uea179418&originHeight=415&originWidth=439&originalType=binary&ratio=1.1699999570846558&rotation=0&showTitle=false&size=15681&status=done&style=none&taskId=u33eb8f69-2889-4f14-b7c1-fbfdb444fe6&title=&width=375.2136889764313)
