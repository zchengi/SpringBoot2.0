package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流的终止操作
 *
 * @author cheng
 *         2018/7/4 19:05
 */
public class StreamDemo4 {
    public static void main(String[] args) {

        String str = "my name is 007";

        // 使用并行流
        str.chars().parallel().forEach(i -> System.out.print((char) i));
        System.out.println();

        // 使用 forEachOrdered 保证顺序
        str.chars().parallel().forEachOrdered(i -> System.out.print((char) i));
        System.out.println();

        // 收集到list
        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
        System.out.println(list);


        // 使用 reduce 拼接字符串
        Optional<String> letters = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2);
        System.out.println(letters.orElse(""));

        // 带初始值的 reduce
        String reduce = Stream.of(str.split(" ")).reduce("", (s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce);

        // 计算所有单词总长度
        int length = Stream.of(str.split(" ")).map(String::length).reduce(0, (s1, s2) -> s1 + s2);
        System.out.println(length);

        // max的使用
        Optional<String> max = Stream.of(str.split(" ")).max(Comparator.comparingInt(String::length));
        System.out.println(max.get());


        // 使用findFirst短路操作
        OptionalInt findFirst = new Random().ints().findFirst();
        System.out.println(findFirst.getAsInt());
    }
}
