//package servlet;
//
//import bean.Category;
//import service.CategoryService;
//import util.PaginationUtil;
//import util.Pagination;
//import util.ParseUploadUtil;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//public class CategoryServlet extends BaseServlet {
//    private CategoryService service = new CategoryService();
//    public String list(HttpServletRequest request,HttpServletResponse response)
//    {
//        Pagination pagination = PaginationUtil.createPagination(request,service.getTotal());
//        List<Category> categories = service.list(pagination.getStart(),pagination.getCount());
//        request.setAttribute("categories",categories);
//        request.setAttribute("pagination",pagination);
//        return "jsp/admin/listCategory.jsp";
//    }
////    public String addUpdate(HttpServletRequest request,HttpServletResponse response) {
////        Map<String, String> params = new HashMap<>();
////        InputStream is = ParseUploadUtil.parseUpload(request, params);
////        String name = params.get("name");
////        String id = params.get("id");
////        int recommend = Integer.parseInt(params.get("recommend"));
////        Category c = null;
////
////        if (id != null) {
////            //编辑模式
////            c = service.get(Integer.parseInt(id));
////            c.setName(name);
////            c.setRecommend(recommend);
////            service.update(c);
////        } else {
////            c = new Category();
////            c.setName(name);
////            c.setRecommend(recommend);
////            service.add(c);
////        }
////
////        File imageFolder = new File(request.getSession().getServletContext().getRealPath("D:\\数据库\\S-mall-servlet-master\\S-mall-servlet-master\\web\\pictures\\category"));
////        File file = new File(imageFolder, c.getId() + "1.jpg");
////        file.getParentFile().mkdirs();
////        try {
////            if (is.available() > 0) {
////                try (FileOutputStream fos = new FileOutputStream(file)) {
////                    byte b[] = new byte[1024 * 1024 * 10];
////                    int length = 0;
////                    while ((length = is.read(b)) != -1) {
////                        fos.write(b, 0, length);
////                    }
////                    fos.flush();
////
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        //return "@/admin/category_list";
////        return "jsp/admin/category_list";
////    }
//
//    public String addUpdate(HttpServletRequest request, HttpServletResponse response) {
//        Map<String, String> params = new HashMap<>();
//        InputStream is = ParseUploadUtil.parseUpload(request, params);
//        String name = params.get("name");
//        String id = params.get("id");
//        int recommend = Integer.parseInt(params.get("recommend"));
//        Category c = null;
//
//        if (id != null) {
//            // Edit mode
//            c = service.get(Integer.parseInt(id));
//            c.setName(name);
//            c.setRecommend(recommend);
//            service.update(c);
//        } else {
//            c = new Category();
//            c.setName(name);
//            c.setRecommend(recommend);
//            service.add(c);
//        }
//
//        // Save the uploaded file
//        String uploadDirectory = request.getServletContext().getRealPath("pictures/category");
//        File imageFolder = new File(uploadDirectory);
//        if (!imageFolder.exists()) {
//            imageFolder.mkdirs();
//        }
//        File file = new File(imageFolder, c.getId() + "1.jpg");
//
//        try {
//            if (is != null && is.available() > 0) {
//                try (FileOutputStream fos = new FileOutputStream(file)) {
//                    byte[] buffer = new byte[1024 * 1024 * 10];
//                    int length;
//                    while ((length = is.read(buffer)) != -1) {
//                        fos.write(buffer, 0, length);
//                    }
//                    fos.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "jsp/admin/category_list.jsp"; // 确保正确转发
//    }
//
//
//
//    public String delete(HttpServletRequest request , HttpServletResponse response){
//        int id = Integer.parseInt(request.getParameter("id"));
//        service.delete(id);
//        //return "@/admin/category_list";
//        return "jsp/admin/category_list.jsp";
//    }
//    public String edit(HttpServletRequest request,HttpServletResponse response){
//        int cid = Integer.parseInt(request.getParameter("cid"));
//        Category c = service.get(cid);
//        request.setAttribute("c", c);
//        return "jsp/admin/editCategory.jsp";
//    }
//}
package servlet;

import bean.Category;
import service.CategoryService;
import util.PaginationUtil;
import util.Pagination;
import util.ParseUploadUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryServlet extends BaseServlet {
    private CategoryService service = new CategoryService();

    public String list(HttpServletRequest request, HttpServletResponse response) {
        Pagination pagination = PaginationUtil.createPagination(request, service.getTotal());
        List<Category> categories = service.list(pagination.getStart(), pagination.getCount());
        request.setAttribute("categories", categories);
        request.setAttribute("pagination", pagination);
        return "jsp/admin/listCategory.jsp";
    }

    public String addUpdate(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> params = new HashMap<>();
        InputStream is = ParseUploadUtil.parseUpload(request, params);
        String name = params.get("name");
        String id = params.get("id");
        int recommend = Integer.parseInt(params.get("recommend"));
        Category c;

        if (id != null && !id.isEmpty()) {
            c = service.get(Integer.parseInt(id));
            c.setName(name);
            c.setRecommend(recommend);
            service.update(c);
        } else {
            c = new Category();
            c.setName(name);
            c.setRecommend(recommend);
            service.add(c);
        }

        String uploadDirectory = request.getServletContext().getRealPath("/img/category");
        File imageFolder = new File(uploadDirectory);
        if (!imageFolder.exists()) {
            imageFolder.mkdirs();
        }
        File file = new File(imageFolder, c.getId() + ".jpg");

        try {
            if (is != null && is.available() > 0) {
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    byte[] buffer = new byte[1024 * 1024 * 10];
                    int length;
                    while ((length = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, length);
                    }
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "@/S_mall_servlet_master_war_exploded/admin/";
    }


    public String delete(HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("cid"));
        service.delete(id);
        return "@/S_mall_servlet_master_war_exploded/admin/";
    }

    public String edit(HttpServletRequest request, HttpServletResponse response) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        Category c = service.get(cid);
        request.setAttribute("c", c);
        return "jsp/admin/editCategory.jsp";
    }
}
