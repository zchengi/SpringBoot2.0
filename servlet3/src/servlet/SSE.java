package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * server-send events
 *
 * @author cheng
 *         2018/7/5 22:30
 */
@WebServlet(name = "SSE")
public class SSE extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应头
        response.setContentType("text/event-stream");
        // 设置编码类型
        response.setCharacterEncoding("utf-8");

        for (int i = 0; i < 5; i++) {

            // 指定事件标志
            response.getWriter().write("event:me\n");

            // 格式： data: + 数据 + 2个回车
            response.getWriter().write("data: " + i + "\n\n");
            response.getWriter().flush();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
