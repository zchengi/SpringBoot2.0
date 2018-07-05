package servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 异步servlet
 */
@WebServlet(name = "asyncServlet")
public class AsyncServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        // 开启异步
        AsyncContext asyncContext = request.startAsync();
        // 执行业务代码
        CompletableFuture.runAsync(
                () -> doSomething(asyncContext, asyncContext.getRequest(), asyncContext.getResponse()));

        System.out.println("async use: " + (System.currentTimeMillis() - startTime));
    }

    private void doSomething(AsyncContext asyncContext, ServletRequest request, ServletResponse response) {

        // 模拟耗时操作
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            response.getWriter().append("done");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 业务代码处理完毕，通知结束
        asyncContext.complete();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
