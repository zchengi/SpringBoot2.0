package stream;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * 验证stream运行机制
 * <p>
 * 1.所有操作是链式调用，一个元素只迭代一次
 * 2.每一个中间操作返回一个新的流，流里面有一个属性 sourceStage 指向同一个地方：Head
 * 3.Head -> nextStage -> nextStage -> ... -> null
 * 4.有状态操作会把无状态操作截断，单独处理
 * 5.并行环境下，有状态的中间操作不一定能并行操作
 * 6.parallel/sequential 这两个操作也是中间操作(也是返回stream)，但是他们不创建流，他们只修改Head的并行标准
 *
 * @author cheng
 *         2018/7/5 15:54
 */
public class RunStream {
    public static void main(String[] args) {

        Random random = new Random();
        // 随机产生数据
        Stream<Integer> stream = Stream.generate(random::nextInt)
                // 产生500个(无限流需要短路操作)
                .limit(500)
                // 第1个无状态操作
                .peek(s -> print("peek：" + s))
                // 第2个无状态操作
                .filter(s -> {
                    print("filter：" + s);
                    return s > 1000000;
                })
                // 有状态操作
                .sorted((i1, i2) -> {
                    print("排序：" + i1 + "," + i2);
                    return i1.compareTo(i2);
                })
                // 又一个无状态操作
                .peek(s -> print("peek2：" + s))
                // 添加并行
                .parallel();

        // 终止操作
        stream.count();
    }

    /**
     * 打印日志并 sleep 5ms
     *
     * @param s
     */
    public static void print(String s) {

        // 带线程名(测试并行情况)
        System.out.println(Thread.currentThread().getName() + " > " + s);
        try {
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
