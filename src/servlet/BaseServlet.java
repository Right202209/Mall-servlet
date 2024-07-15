//package servlet;
//
//import util.Pagination;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.Method;
//
//public abstract class BaseServlet extends HttpServlet {
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // 从请求属性中获取方法名称
//        String method = (String)req.getAttribute("method");
//        try {
//            // 设置请求的字符编码为UTF-8
//            req.setCharacterEncoding("utf-8");
//            // 利用反射获取当前类中的指定方法
//            Method m = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
//            // 调用方法并获取返回值，将其转换为字符串
//            String redirect = m.invoke(this, req, resp).toString();
//
//            // 根据返回值的前缀处理重定向
//            if (redirect.startsWith("@")) {
//                // 如果返回值以 '@' 开头，进行重定向
//                resp.sendRedirect(redirect.substring(1));
//            } else if (redirect.startsWith("%")) {
//                // 如果返回值以 '%' 开头，直接向客户端输出内容
//                resp.getWriter().print(redirect.substring(1));
//            } else {
//                // 否则，转发请求
//                req.getRequestDispatcher(redirect).forward(req, resp);
//            }
//        } catch (Exception e) {
//            // 打印异常堆栈信息
//            e.printStackTrace();
//        }
//    }
//}
package servlet;

import util.Pagination;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = (String)req.getAttribute("method");
        try {
            //利用反射把url中的方法文本转化为方法进行调用
            req.setCharacterEncoding("utf-8");
            Method m = this.getClass().getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            String redirect = m.invoke(this,req,resp).toString();
            if(redirect.startsWith("@")){
                resp.sendRedirect(redirect.substring(1));
            }else if(redirect.startsWith("%"))
                resp.getWriter().print(redirect.substring(1));
            else
                req.getRequestDispatcher(redirect).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}