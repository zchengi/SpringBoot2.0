package lambda;

import java.util.function.Supplier;

/**
 * @author cheng
 *         2018/7/4 14:33
 */

class Log {

    /**
     * 打印 debug 级别的日志
     *
     * @return
     */
    public boolean isDebug() {
        return true;
    }

    public void debug(String str) {
        if (this.isDebug()) {
            System.out.println(str);
        }
    }

    /**
     * @param supplier 传入一个提供字符串的函数
     */
    public void debug(Supplier<String> supplier) {
        if (this.isDebug()) {
            // 真正要打印的时候，才调用
            System.out.println(supplier.get());
        }
    }
}

public class LazyDemo1 {
    public static void main(String[] args) {

        LazyDemo1 demo1 = new LazyDemo1();
        Log log = new Log();
        /*log.debug(new Supplier<String>() {
            @Override
            public String get() {
                return "打印日志之前必须判断日志级别：" + demo1.toString();
            }
        });*/
        log.debug(() -> "打印日志之前必须判断日志级别：" + demo1.toString());
    }

    @Override
    public String toString() {
        System.out.println("toString 被调用了");
        return super.toString();
    }
}
