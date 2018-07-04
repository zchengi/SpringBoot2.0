package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

/**
 * 方法引用
 *
 * @author cheng
 *         2018/7/4 10:37
 */
class Dog {

    public String name = "zzz";

    private int food;

    public Dog() {

    }

    public Dog(String name) {
        this.name = name;
    }

    public Dog(int food) {
        this.food = food;
    }

    /**
     * 狗叫，静态方法
     *
     * @param dog
     */
    public static void bark(Dog dog) {
        System.out.println(dog + "叫了");
    }

    /**
     * 吃狗粮
     * JDK 默认会把当前实例传入到非静态方法，参数名为this，位置是第一个；
     *
     * @param num
     * @return 还剩多少斤
     */
    public int eat(/*Dog this,*/ int num) {
        System.out.println("吃了" + num + "斤");
        food -= num;
        return food;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

public class MethodReferenceDemo {
    public static void main(String[] args) {

        // 方法引用
        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("接收一个字符串");

        // 1.静态方法的方发引用
        Consumer<Dog> consumer2 = Dog::bark;
        Dog dog = new Dog("哮天犬");
        consumer2.accept(dog);

        // 2.非静态方法，使用对象实例引用
        Dog dog1 = new Dog(10);
        dog1.eat(3);

        // Function<Integer, Integer> function = dog1::eat;
        // UnaryOperator<Integer> function = dog1::eat;
        // System.out.println("还剩下" + function.apply(2) + "斤");

        IntUnaryOperator function = dog1::eat;
        // java 中变量都是传值，而不是传引用，所以这里赋空，不会影响之后的处理
        // 实例置空，引用里的对象不会受到影响
        dog1 = null;
        System.out.println("还剩下" + function.applyAsInt(2) + "斤");
        IntUnaryOperator intUnaryOperator = new Dog(10)::eat;
        System.out.println("还剩下" + intUnaryOperator.applyAsInt(2) + "斤");


        // 使用类名引用非静态方法
        BiFunction<Dog, Integer, Integer> eatFunction = Dog::eat;
        System.out.println("还剩下" + eatFunction.apply(new Dog(3), 2) + "斤");

        // 构造函数的方法引用(无参数)
        Supplier<Dog> supplier = Dog::new;
        System.out.println("创建了新对象：" + supplier.get());

        // 构造函数的方法引用(带参数)
        Function<String, Dog> function2 = Dog::new;
        System.out.println("创建了新对象：" + function2.apply("cc"));


        List<String> list = new ArrayList<>();
        list.add("a");
        test(list);
        System.out.println(list);

        Dog dog2 = new Dog("c");
        test2(dog2);
        Dog dog3 = dog2;
        System.out.println(dog3);
        dog3 = null;
        System.out.println(dog2);
        System.out.println(dog3);
    }


    private static void test(List<String> list) {
        list = null;
    }

    private static void test2(Dog dog) {
        dog.name = "aa";
    }
}
