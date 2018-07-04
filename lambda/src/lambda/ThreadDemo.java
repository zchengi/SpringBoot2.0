package lambda;

/**
 * 创建线程例子
 *
 * @author cheng
 *         2018/7/3 22:38
 */
public class ThreadDemo {

    public static void main(String[] args) {

        // jdk8 之前
        Object target = new Runnable() {
            @Override
            public void run() {
                System.out.println("ok1");
            }
        };

        new Thread((Runnable) target).start();

        // jdk8 lambda
        Object target2 = (Runnable) () -> System.out.println("ok2");
        Runnable target3 = () -> System.out.println("ok2");
        System.out.println(target2 == target3);

        new Thread((Runnable) target2).start();
    }
}
