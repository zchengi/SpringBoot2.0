package lambda;

import java.util.function.Consumer;
import java.util.function.IntPredicate;

/**
 * @author cheng
 *         2018/7/4 10:31
 */
public class FunctionDemo {
    public static void main(String[] args) {

        // 断言函数接口
        // Predicate<Integer> predicate = i -> i > 0;

        // IntPredicate 不使用接口简写 Predicate<Integer>
        IntPredicate predicate = i -> i > 0;
        System.out.println(predicate.test(10));

        // 消费接口
        // Consumer<String> consumer = s -> System.out.println(s);
        Consumer<String> consumer = System.out::println;
        consumer.accept("输入的数据");
    }
}
