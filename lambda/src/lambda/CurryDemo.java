package lambda;


import java.util.function.Function;

/**
 * 级联表达式和柯里化
 * 柯里化：把多个参数的函数转换为只有一个参数的函数
 * 柯里化的目的：函数标准化
 *高阶函数：返回函数的函数
 * @author cheng
 *         2018/7/4 13:41
 */
public class CurryDemo {
    public static void main(String[] args) {

        // 实现了 x+y 的级联表达式
        Function<Integer, Function<Integer, Integer>> function = x -> y -> x + y;
        System.out.println(function.apply(2).apply(3));

        Function<Integer, Function<Integer, Function<Integer, Integer>>> function2 = x -> y -> z -> x + y + z;
        System.out.println(function2.apply(2).apply(3).apply(4));

        int[] nums = {3, 4, 5};
        Function f = function2;
        for (int num : nums) {
            if (f instanceof Function) {
                Object obj = f.apply(num);
                if (obj instanceof Function) {
                    f = (Function) obj;
                } else {
                    System.out.println("调用结束，结果为：" + obj);
                }
            }
        }
    }
}
