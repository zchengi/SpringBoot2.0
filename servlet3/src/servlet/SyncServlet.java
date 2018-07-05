package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 同步servlet
 */
@WebServlet(name = "SyncServlet")
public class SyncServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 执行业务代码
        long startTime = System.currentTimeMillis();
        doSomething(request, response);
        System.out.println("sync use: " + (System.currentTimeMillis() - startTime));
    }

    private void doSomething(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 模拟耗时操作
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        response.getWriter().append("done");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
