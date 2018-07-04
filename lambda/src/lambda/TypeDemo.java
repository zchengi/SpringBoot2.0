package lambda;

/**
 * 类型判断
 *
 * @author cheng
 *         2018/7/4 13:01
 */
@FunctionalInterface
interface lMath {
    int add(int x, int y);
}

@FunctionalInterface
interface lMath2 {
    int add2(int x, int y);
}

public class TypeDemo {
    public static void main(String[] args) {

        // 变量类型定义
        lMath lambda = (x, y) -> x + y;


        // 数组里
        lMath[] lambdas = {(x, y) -> x + y};

        // 强转
        Object lambda2 = (lMath) (x, y) -> x + y;

        // 通过返回类型
        lMath createLambda = createLambda();

        TypeDemo typeDemo = new TypeDemo();
        // 当有二义性的时候，使用强转对应的接口解决
        typeDemo.test((lMath) (x, y) -> x + y);
    }

    public void test(lMath math) {

    }

    public void test(lMath2 math) {

    }

    public static lMath createLambda() {
        return (x, y) -> x + y;
    }
}
