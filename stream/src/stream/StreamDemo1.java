package stream;

import java.util.stream.IntStream;

/**
 * 外部迭代和内部迭代
 *
 * @author cheng
 *         2018/7/4 17:09
 */
public class StreamDemo1 {
    public static void main(String[] args) {

        int[] nums = {1, 3, 5};
        // 外部迭代
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        System.out.println("结果为：" + sum);

        // 使用stream的内部迭代
        int sum2 = IntStream.of(nums).sum();
        System.out.println("结果为：" + sum2);

        // map就是中间操作（返回stream的操作）
        // sum就是终止操作
        // int sum3 = IntStream.of(nums).map(i -> i * 2).sum();
        int sum3 = IntStream.of(nums).map(StreamDemo1::doubleNum).sum();
        System.out.println("结果为：" + sum3);

        System.out.println("惰性求值就是终止操作没有调用的情况下，中间操作不会执行");
        IntStream.of(nums).map(StreamDemo1::doubleNum);

    }

    public static int doubleNum(int i) {
        System.out.println("执行了乘以2");
        return i * 2;
    }
}
