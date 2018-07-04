package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 变量引用
 *
 * @author cheng
 *         2018/7/4 13:12
 */
public class VarDemo {
    public static void main(String[] args) {

        final String str = "time";
        // 匿名类引用外部类变量必须是final类型
        Consumer<String> consumer = s -> System.out.println(s + str);
        consumer.accept("1314");

        List<String> list = new ArrayList<>();
        list.add("c");
        Consumer<String> consumer2 = s -> System.out.println(s + list);
        consumer2.accept("~~");
    }
}
