package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/pictures/*")
public class PictureServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求的文件路径
        String filePath = request.getPathInfo();
        if (filePath == null || filePath.equals("/")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        // 基础路径：项目的真实路径
        String basePath = request.getServletContext().getRealPath("/img/category");
        File file = new File(basePath, filePath);

        if (!file.exists() || file.isDirectory()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        // 设置响应类型
        response.setContentType(getServletContext().getMimeType(file.getName()));
        response.setContentLength((int) file.length());

        try (FileInputStream in = new FileInputStream(file); OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
