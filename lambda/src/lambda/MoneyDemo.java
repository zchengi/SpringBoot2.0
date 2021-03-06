package lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * 函数接口
 *
 * @author cheng
 *         2018/7/4 10:12
 */

class MyMoney {
    private final int money;

    public MyMoney(int money) {
        this.money = money;
    }

    public void printMoney(Function<Integer, String> moneyFormat) {
        System.out.println("存款：" + moneyFormat.apply(money));
    }
}

public class MoneyDemo {
    public static void main(String[] args) {

        MyMoney me = new MyMoney(99999999);
        Function<Integer, String> moneyFormat = i -> new DecimalFormat("#,###").format(i);

        // 函数接口链式操作
        me.printMoney(moneyFormat.andThen(s -> "人民币 " + s + "元"));
    }
}
