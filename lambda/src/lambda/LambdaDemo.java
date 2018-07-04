package lambda;

/**
 * @author cheng
 *         2018/7/3 22:45
 */
@FunctionalInterface
interface Interface1 {
    // 一个接口只有一个方法————设计模式：单一责任制
    int doubleNum(int i);

    /**
     * jdk8新特性：接口的默认方法
     */
    default int add(int x, int y) {
        this.doubleNum(2);
        return x + y;
    }

    static int sub(int x, int y) {
        return x - y;
    }
}

@FunctionalInterface
interface Interface2 {
    int doubleNum(int i);

    default int add(int x, int y) {
        return x + y;
    }
}

@FunctionalInterface
interface Interface3 extends Interface2, Interface1 {

    @Override
    default int add(int x, int y) {
        return Interface1.super.add(x, y);
    }
}

public class LambdaDemo {

    public static void main(String[] args) {

        Interface1 i1 = (i) -> i * 2;

        System.out.println(Interface1.sub(3, 2));

        // 最常见的Lambda表达式写法
        Interface1 i2 = i -> i * 2;

        Interface1 i3 = (int i) -> i * 2;

        Interface1 i4 = (int i) -> {
            System.out.println("-------");
            return i * 2;
        };

        System.out.println(i1.add(3, 7));

        // 接口的默认方法
        System.out.println(i1.doubleNum(20));
    }
}
