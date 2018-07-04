package lambda;

/**
 * 使用 jdk8 接口的 static 方法来创建工具类
 *
 * @author cheng
 *         2018/7/4 14:29
 */
public interface MyTool {
    static int add(int x, int y) {
        return x + y;
    }

    static int subtract(int x, int y) {
        return x - y;
    }
}
