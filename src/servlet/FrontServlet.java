//package servlet;
//
//import bean.*;
//import service.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//import org.apache.commons.lang3.*;
//import util.PasswordUtil;
//import util.ProductSort;
//
//@SuppressWarnings("deprecation")
//public class FrontServlet extends BaseServlet {
//    public String home(HttpServletRequest request, HttpServletResponse response) {
//        // 获取主页所需的分类列表
//        List<Category> categories = new CategoryService().listInHome();
//        // 将分类列表设置为请求属性
//        request.setAttribute("categories", categories);
//        // 返回主页的JSP页面
//        return "jsp/home.jsp";
//    }
//
//    public String register(HttpServletRequest request, HttpServletResponse response) {
//        // 返回注册页面的JSP
//        return "jsp/register.jsp";
//    }
//
//    public String registerAdd(HttpServletRequest request, HttpServletResponse response) {
//        // 获取表单中的推荐人信息
//        String refer = request.getParameter("refer");
//        // 将推荐人信息设置为请求属性
//        request.setAttribute("refer", refer);
//        // 注册失败时的页面URL
//        String failUrl = "jsp/register.jsp";
//        // 获取表单中的用户名和密码
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        // 对用户名进行HTML转义
//        name = StringEscapeUtils.escapeHtml4(name);
//        // 对密码进行加密
//        password = PasswordUtil.encryptPassword(password);
//        // 检查用户名是否已存在
//        boolean exist = new UserService().isExist(name);
//        if (exist) {
//            // 如果用户名已存在，设置错误信息并返回失败页面
//            request.setAttribute("msg", "用户名已经被使用了，请换一个用户名吧");
//            return failUrl;
//        }
//        if (name.isEmpty() || password.isEmpty()) {
//            // 如果用户名或密码为空，设置错误信息并返回失败页面
//            request.setAttribute("msg", "用户名或密码不能为空");
//            return failUrl;
//        }
//        // 创建新用户对象并设置用户名和密码
//        User user = new User();
//        user.setName(name);
//        user.setPassword(password);
//        // 添加新用户到数据库
//        new UserService().add(user);
//        // 返回注册成功页面
//        return "jsp/registerSuccess.jsp";
//    }
//
////public String login(HttpServletRequest request, HttpServletResponse response) {
////    return "jsp/login.jsp";
////}
//
//    public String login(HttpServletRequest request, HttpServletResponse response) {
//        // 重定向到登录页面
//        try {
//            response.sendRedirect("jsp/login.jsp");
//        } catch (IOException e) {
//            // 如果出现IO异常，抛出运行时异常
//            throw new RuntimeException(e);
//        }
//        // 返回根目录（防止方法返回空值）
//        return "@/";
//    }
//
//    public String loginIn(HttpServletRequest request, HttpServletResponse response) {
//        // 获取表单中的推荐人信息
//        String refer = request.getParameter("refer");
//        // 获取表单中的用户名和密码
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        // 对用户名和密码进行HTML转义和加密
//        name = StringEscapeUtils.escapeHtml4(name);
//        password = StringEscapeUtils.escapeHtml4(password);
//        password = PasswordUtil.encryptPassword(password);
//        // 根据用户名和密码获取用户信息
//        User user = new UserService().get(name, password);
//        if (user == null) {
//            // 如果用户不存在，设置错误信息并返回登录页面
//            request.setAttribute("msg", "用户名或密码错误");
//            request.setAttribute("refer", refer);
//            return "jsp/login.jsp";
//        }
//        // 将用户信息设置为会话属性
//        request.getSession().setAttribute("user", user);
//        // 如果推荐人信息为空，设置默认推荐人信息为根目录
//        if (refer == null || refer.equals("")) {
//            refer = "/";
//        }
//        // 返回推荐人信息
//        return "@" + refer;
//    }
//
//
//    public String checkLogin(HttpServletRequest request, HttpServletResponse response) {
//        // 获取session中的用户信息
//        User user = (User) request.getSession().getAttribute("user");
//        // 如果用户信息为null，返回失败标志，否则返回成功标志
//        return null == user ? "%fail" : "%success";
//    }
//
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        // 从session中移除用户信息，实现登出
//        request.getSession().removeAttribute("user");
//        // 返回首页
//        return "@/";
//    }
//
//    public String product(HttpServletRequest request, HttpServletResponse response) {
//        // 获取产品ID
//        int pid = Integer.parseInt(request.getParameter("pid"));
//        // 获取产品信息
//        Product product = new ProductService().get(pid);
//        // 获取产品评论列表
//        List<Comment> comments = new CommentService().list(pid);
//        // 获取产品的前5张展示图片
//        List<ProductImage> topImages = new ProductImageService().listTopImage(pid, 0, 5);
//        // 获取产品的所有细节图片
//        List<ProductImage> detailImages = new ProductImageService().listDetailImage(pid, 0, Short.MAX_VALUE);
//        // 获取产品属性及对应值
//        Map<Property, PropertyValue> propertyValues = new PropertyService().list(product);
//        // 获取前4个类别
//        List<Category> categories = new CategoryService().list(0, 4);
//        // 将获取的信息设置到request属性中
//        request.setAttribute("product", product);
//        request.setAttribute("comments", comments);
//        request.setAttribute("pvs", propertyValues);
//        request.setAttribute("topImages", topImages);
//        request.setAttribute("detailImages", detailImages);
//        request.setAttribute("categories", categories);
//        // 返回产品页面的路径
//        return "jsp/product.jsp";
//    }
//
//    public String category(HttpServletRequest request, HttpServletResponse response) {
//        // 获取类别ID
//        int cid = Integer.parseInt(request.getParameter("cid"));
//        // 获取排序参数
//        String sort = request.getParameter("sort");
//        // 获取类别下的产品列表
//        List<Product> products = new ProductService().listByCategory(cid);
//        // 对产品列表进行排序
//        ProductSort.sort(products, sort);
//        // 将产品列表和类别信息设置到request属性中
//        request.setAttribute("products", products);
//        request.setAttribute("category", new CategoryService().get(cid));
//        request.setAttribute("categories", new CategoryService().list(0, 7));
//        // 返回类别页面的路径
//        return "jsp/category.jsp";
//    }
//
//    public String search(HttpServletRequest request, HttpServletResponse response) {
//        // 获取搜索关键词
//        String keyword = request.getParameter("keyword");
//        // 获取排序参数
//        String sort = request.getParameter("sort");
//        // 根据关键词获取产品列表
//        List<Product> products = new ProductService().listBySearch(keyword, 0, 20);
//        // 对关键词进行HTML转义，防止XSS攻击
//        keyword = StringEscapeUtils.escapeHtml4(keyword);
//        // 将关键词和产品列表设置到request属性中
//        request.setAttribute("keyword", keyword);
//        request.setAttribute("products", products);
//        // 对产品列表进行排序
//        ProductSort.sort(products, sort);
//        // 获取前7个类别并设置到request属性中
//        request.setAttribute("categories", new CategoryService().list(0, 7));
//        // 返回搜索结果页面的路径
//        return "jsp/search.jsp";
//    }
//
//    public String buyOne(HttpServletRequest request, HttpServletResponse response) {
//        // 客户下单，在session中而不是在数据库中注册一个cartItem项目，标记后跳转到下单页面
//        int pid = Integer.parseInt(request.getParameter("pid"));
//        int num = Integer.parseInt(request.getParameter("num"));
//        Product product = new ProductService().get(pid);
//        User user = (User) request.getSession().getAttribute("user");
//        CartItem cartItem = new CartItem();
//        cartItem.setUser(user);
//        cartItem.setProduct(product);
//        cartItem.setNumber(num);
//        cartItem.setSum(cartItem.getProduct().getNowPrice().multiply(new BigDecimal(cartItem.getNumber())));
//        request.getSession().setAttribute("tempCartItem", cartItem);
//        return "@buy?ciid=-1"; // -1的话提醒buy页面从session取cartItem而不是从数据库中拿
//    }
//
//    public String buy(HttpServletRequest request, HttpServletResponse response) {
//        User user = (User) request.getSession().getAttribute("user");
//        String[] cartItemIdStrings = request.getParameterValues("ciid");
//        List<CartItem> cartItems = new ArrayList<>();
//        BigDecimal total = new BigDecimal(0);
//        for (String cartItemIdString : cartItemIdStrings) {
//            int cartItemId = Integer.parseInt(cartItemIdString);
//            if (cartItemId == -1) { // 点的是立即购买，从session中拿cartItem
//                CartItem cartItem = (CartItem) request.getSession().getAttribute("tempCartItem");
//                total = total.add(cartItem.getSum());
//                cartItem.setId(-1);
//                cartItems.add(cartItem);
//                break;
//            } else { // 从购物车中来的
//                List<CartItem> userList = new CartItemService().listByUser(user.getId());
//                for (CartItem userItem : userList) { // 判断是否是该用户的订单
//                    if (userItem.getId() == cartItemId) {
//                        CartItem cartItem = new CartItemService().get(cartItemId);
//                        total = total.add(cartItem.getSum());
//                        cartItems.add(cartItem);
//                        break;
//                    }
//                }
//            }
//        }
//
//        request.getSession().setAttribute("cartItems", cartItems);
//        request.setAttribute("total", total);
//        return "jsp/buy.jsp";
//    }
//
//    public String addCart(HttpServletRequest request, HttpServletResponse response) {
//        // ajax请求加购物车
//        int pid = Integer.parseInt(request.getParameter("pid"));
//        int num = Integer.parseInt(request.getParameter("num"));
//        Product product = new ProductService().get(pid);
//        User user = (User) request.getSession().getAttribute("user");
//        List<CartItem> cartItems = new CartItemService().listByUser(user.getId());
//        boolean found = false;
//        // 如果购物车中已经有相关项目就拿出来加数量，更新
//        for (CartItem item : cartItems) {
//            if (product.getId() == item.getProduct().getId()) {
//                int newNum = item.getNumber() + num;
//                // 判断库存是否足够
//                if (product.getStock() < newNum) {
//                    return "%OutOfStock";
//                }
//                item.setNumber(newNum);
//                item.setSum(item.getProduct().getNowPrice().multiply(new BigDecimal(item.getNumber())));
//                new CartItemService().update(item);
//                found = true;
//                break;
//            }
//        }
//        // 如果购物车中没有相关项目新建一个
//        if (!found) {
//            CartItem cartItem = new CartItem();
//            cartItem.setUser(user);
//            cartItem.setProduct(product);
//            if (product.getStock() < num) {
//                return "%OutOfStock";
//            }
//            cartItem.setNumber(num);
//            cartItem.setSum(cartItem.getProduct().getNowPrice().multiply(new BigDecimal(cartItem.getNumber())));
//            new CartItemService().add(cartItem);
//        }
//        return "%success";
//    }
//    public String cart(HttpServletRequest request, HttpServletResponse response) {
//        // 获取用户
//        User user = (User) request.getSession().getAttribute("user");
//        // 获取用户购物车中的所有项目
//        List<CartItem> cartItems = new CartItemService().listByUser(user.getId());
//        // 将购物车项目和类别列表设置到request属性中
//        request.setAttribute("cartItems", cartItems);
//        request.setAttribute("categories", new CategoryService().list(0, 4));
//        // 返回购物车页面路径
//        return "jsp/cart.jsp";
//    }
//
//    public String changeCartNum(HttpServletRequest request, HttpServletResponse response) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (null == user)
//            return "%fail"; // 用户未登录，返回失败标志
//        // 获取购物车项目ID和新的数量
//        int ciid = Integer.parseInt(request.getParameter("ciid"));
//        int num = Integer.parseInt(request.getParameter("num"));
//        // 获取用户购物车中的所有项目
//        List<CartItem> cartItems = new CartItemService().listByUser(user.getId());
//        for (CartItem item : cartItems) {
//            if (item.getId() == ciid) {
//                Product product = item.getProduct();
//                // 判断库存是否足够
//                if (product.getStock() >= num) {
//                    item.setNumber(num);
//                    new CartItemService().update(item);
//                    return "%success"; // 更新成功，返回成功标志
//                }
//                break;
//            }
//        }
//        return "%fail"; // 更新失败，返回失败标志
//    }
//
//    public String deleteCartItem(HttpServletRequest request, HttpServletResponse response) {
//        User user = (User) request.getSession().getAttribute("user");
//        if (null == user)
//            return "%fail"; // 用户未登录，返回失败标志
//        // 获取购物车项目ID
//        int ciid = Integer.parseInt(request.getParameter("ciid"));
//        // 获取用户购物车中的所有项目
//        List<CartItem> cartItems = new CartItemService().listByUser(user.getId());
//        for (CartItem item : cartItems) {
//            if (item.getId() == ciid) {
//                new CartItemService().delete(ciid);
//                return "%success"; // 删除成功，返回成功标志
//            }
//        }
//        return "%fail"; // 删除失败，返回失败标志
//    }
//    @SuppressWarnings("unchecked")
//    public String createOrder(HttpServletRequest request, HttpServletResponse response) {
//        // 获取当前用户
//        User user = (User) request.getSession().getAttribute("user");
//        // 获取购物车中的所有项目
//        List<CartItem> cartItems = (List<CartItem>) request.getSession().getAttribute("cartItems");
//        // 获取订单信息
//        String address = request.getParameter("address");
//        String post = request.getParameter("post");
//        String receiver = request.getParameter("receiver");
//        String mobile = request.getParameter("mobile");
//        String userMessage = request.getParameter("userMessage");
//
//        // 创建订单对象并设置属性
//        Order order = new Order();
//        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date()) + RandomUtils.nextInt();
//        order.setOrderCode(orderCode);
//        order.setAddress(address);
//        order.setPost(post);
//        order.setReceiver(receiver);
//        order.setMobile(mobile);
//        order.setUserMessage(userMessage);
//        order.setCreateDate(new Date());
//        order.setUser(user);
//        order.setStatus(OrderService.OrderType.WAIT_PAY);
//
//        // 计算订单总金额
//        BigDecimal sum = new BigDecimal(0);
//        for (CartItem item : cartItems) {
//            sum = sum.add(item.getSum());
//        }
//        order.setSum(sum);
//
//        // 将订单存入数据库
//        new OrderService().add(order);
//
//        // 将购物车中的每个项目转换为订单项，并存入数据库，同时删除购物车中的项目
//        for (CartItem item : cartItems) {
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            orderItem.setNumber(item.getNumber());
//            orderItem.setProduct(item.getProduct());
//            orderItem.setSum(item.getSum());
//            new CartItemService().delete(item.getId());
//            new OrderItemService().add(orderItem);
//        }
//
//        // 跳转到支付页面
//        return "@pay?oid=" + order.getId();
//    }
//
//    public String pay(HttpServletRequest request, HttpServletResponse response) {
//        // 获取订单ID
//        int orderId = Integer.parseInt(request.getParameter("oid"));
//        // 获取当前用户
//        User user = (User) request.getSession().getAttribute("user");
//        // 获取当前用户的所有订单
//        List<Order> orders = new OrderService().list(user.getId());
//        // 查找指定订单并设置到request属性中，用于支付页面显示
//        for (Order item : orders) {
//            if (orderId == item.getId()) {
//                request.setAttribute("order", item);
//                return "jsp/pay.jsp";
//            }
//        }
//        return "@/"; // 若未找到订单，返回首页
//    }
//
//    public String payed(HttpServletRequest request, HttpServletResponse response) {
//        // 获取当前用户
//        User user = (User) request.getSession().getAttribute("user");
//        // 获取当前用户的所有订单
//        List<Order> orders = new OrderService().list(user.getId());
//        // 获取支付完成的订单ID
//        int orderId = Integer.parseInt(request.getParameter("oid"));
//        // 查找指定订单并更新支付日期和状态，设置到request属性中，用于支付完成页面显示
//        for (Order item : orders) {
//            if (orderId == item.getId()) {
//                item.setPayDate(new Date());
//                item.setStatus(OrderService.OrderType.WAIT_DELIVERY);
//                new OrderService().update(item);
//                request.setAttribute("order", item);
//                return "jsp/payed.jsp";
//            }
//        }
//        return "@/"; // 若未找到订单，返回首页
//    }
//
//    public String myOrder(HttpServletRequest request, HttpServletResponse response) {
//        // 获取当前用户
//        User user = (User) request.getSession().getAttribute("user");
//        // 获取当前用户的所有订单
//        List<Order> orders = new OrderService().list(user.getId());
//        // 将订单列表设置到request属性中，用于我的订单页面显示
//        request.setAttribute("orders", orders);
//        return "jsp/myOrder.jsp";
//    }
//
//    public String confirmPay(HttpServletRequest request, HttpServletResponse response) {
//        // 获取当前用户
//        User user = (User) request.getSession().getAttribute("user");
//        // 获取当前用户的所有订单
//        List<Order> orders = new OrderService().list(user.getId());
//        // 获取待确认支付的订单ID
//        int oid = Integer.parseInt(request.getParameter("oid"));
//        // 查找指定订单并设置到request属性中，用于确认支付页面显示
//        for (Order order : orders) {
//            if (order.getId() == oid) {
//                request.setAttribute("order", order);
//                return "jsp/confirmPay.jsp";
//            }
//        }
//        return "@/"; // 若未找到订单，返回首页
//    }
//
//    public String confirmed(HttpServletRequest request, HttpServletResponse response) {
//        // 获取当前用户
//        User user = (User) request.getSession().getAttribute("user");
//        // 获取当前用户的所有订单
//        List<Order> orders = new OrderService().list(user.getId());
//        // 获取已确认支付的订单ID
//        int oid = Integer.parseInt(request.getParameter("oid"));
//        // 查找指定订单并更新确认日期和状态，设置到request属性中，用于确认完成页面显示
//        for (Order order : orders) {
//            if (order.getId() == oid) {
//                order.setConfirmDate(new Date());
//                order.setStatus(OrderService.OrderType.WAIT_REVIEW);
//                new OrderService().update(order);
//                request.setAttribute("order", order);
//                return "jsp/confirmed.jsp";
//            }
//        }
//        return "@/"; // 若未找到订单，返回首页
//    }
//    public String deleteOrder(HttpServletRequest request, HttpServletResponse response) {
//        // 获取当前用户
//        User user = (User) request.getSession().getAttribute("user");
//        // 获取当前用户的所有订单
//        List<Order> orders = new OrderService().list(user.getId());
//        // 获取要删除的订单ID
//        int oid = Integer.parseInt(request.getParameter("oid"));
//        // 查找指定订单并删除
//        for (Order order : orders) {
//            if (order.getId() == oid) {
//                new OrderService().delete(oid);
//                return "%success"; // 删除成功，返回成功标志
//            }
//        }
//        return "%fail"; // 删除失败，返回失败标志
//    }
//
//    public String comment(HttpServletRequest request, HttpServletResponse response) {
//        // 获取当前用户
//        User user = (User) request.getSession().getAttribute("user");
//        // 获取当前用户的所有订单
//        List<Order> orders = new OrderService().list(user.getId());
//        // 获取要评论的订单项ID
//        int oiid = Integer.parseInt(request.getParameter("oiid"));
//        // 查找订单中的指定订单项，并设置到request属性中，用于评论页面显示
//        for (Order order : orders) {
//            for (OrderItem item : order.getOrderItems()) {
//                if (oiid == item.getId()) {
//                    request.setAttribute("orderItem", item);
//                    request.setAttribute("order", order);
//                    return "jsp/comment.jsp"; // 转到评论页面
//                }
//            }
//        }
//        return "@/"; // 若未找到订单项，返回首页
//    }
//
//    public String addComment(HttpServletRequest request, HttpServletResponse response) {
//        // 获取当前用户
//        User user = (User) request.getSession().getAttribute("user");
//        // 获取当前用户的所有订单
//        List<Order> orders = new OrderService().list(user.getId());
//        // 获取要添加评论的订单项ID
//        int oiid = Integer.parseInt(request.getParameter("oiid"));
//        // 查找订单中的指定订单项，并添加评论
//        for (Order order : orders) {
//            for (OrderItem item : order.getOrderItems()) {
//                if (oiid == item.getId()) {
//                    int allCount = 0;
//                    int hasComment = 0;
//                    // 统计订单中已经评论的订单项数量
//                    for (OrderItem item2 : order.getOrderItems()) {
//                        Comment comment = new CommentService().get(item2.getProduct().getId(), user.getId());
//                        allCount++;
//                        if (comment != null) {
//                            hasComment++;
//                        }
//                    }
//                    // 创建评论对象并设置属性
//                    Comment comment = new Comment();
//                    comment.setContent(request.getParameter("content"));
//                    comment.setCreateDate(new Date());
//                    comment.setProduct(item.getProduct());
//                    comment.setUser(user);
//                    // 将评论存入数据库
//                    new CommentService().add(comment);
//                    // 若所有订单项都已评论，则更新订单状态为完成
//                    if (allCount - hasComment == 1) {
//                        order.setStatus(OrderService.OrderType.FINISH);
//                        new OrderService().update(order);
//                    }
//                    return "@myOrder"; // 返回我的订单页面
//                }
//            }
//        }
//        return "@/"; // 若未找到订单项，返回首页
//    }
//
//    public String delivery(HttpServletRequest request, HttpServletResponse response) {
//        // 获取当前用户
//        User user = (User) request.getSession().getAttribute("user");
//        // 获取当前用户的所有订单
//        List<Order> orders = new OrderService().list(user.getId());
//        // 获取要确认发货的订单ID
//        int oid = Integer.parseInt(request.getParameter("oid"));
//        // 查找指定订单并设置发货日期和状态为等待确认收货
//        for (Order order : orders) {
//            if (order.getId() == oid) {
//                order.setDeliverDate(new Date());
//                order.setStatus(OrderService.OrderType.WAIT_CONFIRM);
//                new OrderService().update(order);
//                return "@myOrder"; // 返回我的订单页面
//            }
//        }
//        return "@/"; // 若未找到订单，返回首页
//    }
//
//    public String cartNumber(HttpServletRequest request, HttpServletResponse response) {
//        // 获取当前用户
//        User user = (User) request.getSession().getAttribute("user");
//        // 获取当前用户购物车中的总数量
//        int number = new CartItemService().getTotal(user.getId());
//        return "%" + number; // 返回购物车中商品数量的响应标志
//    }
//}
package servlet;

import bean.*;
import service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.*;
import util.PasswordUtil;
import util.ProductSort;

@SuppressWarnings("deprecation")
public class FrontServlet extends BaseServlet {
    public String home(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categories = new CategoryService().listInHome();
        request.setAttribute("categories", categories);
        return "jsp/home.jsp";
    }

    public String register(HttpServletRequest request, HttpServletResponse response) {
        return "jsp/register.jsp";
    }

    public String registerAdd(HttpServletRequest request, HttpServletResponse response) {
        String refer = request.getParameter("refer");
        request.setAttribute("refer", refer);
        String failUrl = "jsp/register.jsp";
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        name = StringEscapeUtils.escapeHtml4(name);
        password = PasswordUtil.encryptPassword(password);
        boolean exist = new UserService().isExist(name);
        if (exist) {
            request.setAttribute("msg", "用户名已经被使用了，请换一个用户名吧");
            return failUrl;
        }
        if (name.isEmpty() || password.isEmpty()) {
            request.setAttribute("msg", "用户名或密码不能为空");
            return failUrl;
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        new UserService().add(user);
        return "jsp/registerSuccess.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) {
        return "jsp/login.jsp";
    }

    public String loginIn(HttpServletRequest request, HttpServletResponse response) {
        String refer = request.getParameter("refer");
        String name = request.getParameter("name");
        name = StringEscapeUtils.escapeHtml4(name);
        String password = request.getParameter("password");
        password = StringEscapeUtils.escapeHtml4(password);
        password = PasswordUtil.encryptPassword(password);
        User user = new UserService().get(name, password);
        if (user == null) {
            request.setAttribute("msg", "用户名或密码错误");
            request.setAttribute("refer", refer);
            return "jsp/login.jsp";
        }
        request.getSession().setAttribute("user", user);
        if(refer==null || refer.equals("")){
            refer = "/";
        }
        return "@" + refer;
    }

    public String checkLogin(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        return null == user ? "%fail" : "%success";
    }

    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
//        return "@/";
    return "@/S_mall_servlet_master_war_exploded/";
    }

    public String product(HttpServletRequest request, HttpServletResponse response) {
        int pid = Integer.parseInt(request.getParameter("pid"));
        Product product = new ProductService().get(pid);
        List<Comment> comments = new CommentService().list(pid);
        List<ProductImage> topImages = new ProductImageService().listTopImage(pid, 0, 5);
        List<ProductImage> detailImages = new ProductImageService().listDetailImage(pid, 0, Short.MAX_VALUE);
        Map<Property, PropertyValue> propertyValues = new PropertyService().list(product);
        List<Category> categories = new CategoryService().list(0, 4);
        request.setAttribute("product", product);
        request.setAttribute("comments", comments);
        request.setAttribute("pvs", propertyValues);
        request.setAttribute("topImages", topImages);
        request.setAttribute("detailImages", detailImages);
        request.setAttribute("categories", categories);
        return "jsp/product.jsp";
    }

    public String category(HttpServletRequest request, HttpServletResponse response) {
        int cid = Integer.parseInt(request.getParameter("cid"));
        String sort = request.getParameter("sort");
        List<Product> products = new ProductService().listByCategory(cid);
        ProductSort.sort(products, sort);
        request.setAttribute("products", products);
        request.setAttribute("category", new CategoryService().get(cid));
        request.setAttribute("categories", new CategoryService().list(0, 7));
        return "jsp/category.jsp";
    }

    public String search(HttpServletRequest request, HttpServletResponse response) {
        String keyword = request.getParameter("keyword");
        String sort = request.getParameter("sort");
        List<Product> products = new ProductService().listBySearch(keyword, 0, 20);
        keyword = StringEscapeUtils.escapeHtml4(keyword);
        request.setAttribute("keyword", keyword);
        request.setAttribute("products", products);
        ProductSort.sort(products, sort);
        request.setAttribute("categories", new CategoryService().list(0, 7));
        return "jsp/search.jsp";
    }

    public String buyOne(HttpServletRequest request, HttpServletResponse response) {
        //客户下单，在session里面而不是在数据库里面注册一个cartItem项目，打上标示跳转到下单页面
        int pid = Integer.parseInt(request.getParameter("pid"));
        int num = Integer.parseInt(request.getParameter("num"));
        Product product = new ProductService().get(pid);
        User user = (User) request.getSession().getAttribute("user");
        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setNumber(num);
        cartItem.setSum(cartItem.getProduct().getNowPrice().multiply(new BigDecimal(cartItem.getNumber())));
        request.getSession().setAttribute("tempCartItem", cartItem);
        return "@buy?ciid=-1"; //-1的话提醒buy页面从session取cartItem而不是从数据里面拿
    }

    public String buy(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        String[] cartItemIdStrings = request.getParameterValues("ciid");
        List<CartItem> cartItems = new ArrayList<>();
        BigDecimal total = new BigDecimal(0);
        for (String cartItemIdString : cartItemIdStrings) {
            int cartItemId = Integer.parseInt(cartItemIdString);
            if (cartItemId == -1) {//点的是立即购买，从session里面拿cartItem
                CartItem cartItem = (CartItem) request.getSession().getAttribute("tempCartItem");
                total = total.add(cartItem.getSum());
                cartItem.setId(-1);
                cartItems.add(cartItem);
                break;
            } else {//从购物车中来的
                List<CartItem> userList = new CartItemService().listByUser(user.getId());
                for(CartItem userItem:userList) { //判断是否是该用户的订单
                    if(userItem.getId()==cartItemId) {
                        CartItem cartItem = new CartItemService().get(cartItemId);
                        total = total.add(cartItem.getSum());
                        cartItems.add(cartItem);
                        break;
                    }
                }
            }
        }

        request.getSession().setAttribute("cartItems", cartItems);
        request.setAttribute("total", total);
        return "jsp/buy.jsp";
    }

    public String addCart(HttpServletRequest request, HttpServletResponse response) {
        //ajax请求加购物车
        int pid = Integer.parseInt(request.getParameter("pid"));
        int num = Integer.parseInt(request.getParameter("num"));
        Product product = new ProductService().get(pid);
        User user = (User) request.getSession().getAttribute("user");
        List<CartItem> cartItems = new CartItemService().listByUser(user.getId());
        boolean found = false;
        //如果购物车中已经有相关项目就拿出来加数量，更新
        for (CartItem item : cartItems) {
            if (product.getId() == item.getProduct().getId()) {
                int newNum = item.getNumber() + num;
                //判断库存是否足够
                if(product.getStock()<newNum){
                    return "%OutOfStock";
                }
                item.setNumber(newNum);
                item.setSum(item.getProduct().getNowPrice().multiply(new BigDecimal(item.getNumber())));
                new CartItemService().update(item);
                found = true;
                break;
            }
        }
        //如果购物车里面没有相关项目新建一个
        if(!found) {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            if(product.getStock()<num){
                return "%OutOfStock";
            }
            cartItem.setNumber(num);
            cartItem.setSum(cartItem.getProduct().getNowPrice().multiply(new BigDecimal(cartItem.getNumber())));
            new CartItemService().add(cartItem);
        }
        return "%success";
    }
    public String cart(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        List<CartItem> cartItems = new CartItemService().listByUser(user.getId());
        request.setAttribute("cartItems",cartItems);
        request.setAttribute("categories", new CategoryService().list(0, 4));
        return "jsp/cart.jsp";
    }
    public String changeCartNum(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        if(null==user)
            return "%fail";
        int ciid = Integer.parseInt(request.getParameter("ciid"));
        int num = Integer.parseInt(request.getParameter("num"));
        List<CartItem> cartItems = new CartItemService().listByUser(user.getId());
        for(CartItem item:cartItems){
            if(item.getId()==ciid){
                Product product = item.getProduct();
                if(product.getStock()>=num) {
                    item.setNumber(num);
                    new CartItemService().update(item);
                    return "%success";
                }
                break;
            }
        }
        return "%fail";
    }
    public String deleteCartItem(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        if(null==user)
            return "%fail";
        int ciid = Integer.parseInt(request.getParameter("ciid"));
        List<CartItem> cartItems = new CartItemService().listByUser(user.getId());
        for(CartItem item:cartItems){
            if(item.getId()==ciid){
                new CartItemService().delete(ciid);
                return "%success";
            }
        }
        return "%fail";
    }
    @SuppressWarnings("unchecked")
    public String createOrder(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        List<CartItem> cartItems = (List<CartItem>) request.getSession().getAttribute("cartItems");
        String address = request.getParameter("address");
        String post = request.getParameter("post");
        String receiver = request.getParameter("receiver");
        String mobile = request.getParameter("mobile");
        String userMessage = request.getParameter("userMessage");
        Order order = new Order();
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date())+RandomUtils.nextInt();
        order.setOrderCode(orderCode);
        order.setAddress(address);
        order.setPost(post);
        order.setReceiver(receiver);
        order.setMobile(mobile);
        order.setUserMessage(userMessage);
        order.setCreateDate(new Date());
        order.setUser(user);
        order.setStatus(OrderService.OrderType.WAIT_PAY);
        BigDecimal sum = new BigDecimal(0);
        for(CartItem item:cartItems){
            sum = sum.add(item.getSum());
        }
        order.setSum(sum);
        new OrderService().add(order);
        for(CartItem item:cartItems){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setNumber(item.getNumber());
            orderItem.setProduct(item.getProduct());
            orderItem.setSum(item.getSum());
            new CartItemService().delete(item.getId());
            new OrderItemService().add(orderItem);
        }


        return "@pay?oid="+order.getId();
    }

    public String pay(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter("oid"));
        User user =(User) request.getSession().getAttribute("user");
        List<Order> orders = new OrderService().list(user.getId());
        for(Order item : orders){
            if(orderId == item.getId()){
                request.setAttribute("order",item);
                return "jsp/pay.jsp";
            }
        }
        return "@/";
    }
    public String payed(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        List<Order> orders = new OrderService().list(user.getId());
        int orderId = Integer.parseInt(request.getParameter("oid"));
        for(Order item : orders){
            if(orderId == item.getId()){
                item.setPayDate(new Date());
                item.setStatus(OrderService.OrderType.WAIT_DELIVERY);
                new OrderService().update(item);
                request.setAttribute("order",item);
                return "jsp/payed.jsp";
            }
        }
        return "@/";
    }
    public String myOrder(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        List<Order> orders = new OrderService().list(user.getId());
        request.setAttribute("orders",orders);
        return "jsp/myOrder.jsp";
    }
    public String confirmPay(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        List<Order> orders = new OrderService().list(user.getId());
        int oid = Integer.parseInt(request.getParameter("oid"));
        for(Order order:orders){
            if(order.getId()==oid){
                request.setAttribute("order",order);
                return "jsp/confirmPay.jsp";
            }
        }
        return "@/";
    }
    public String confirmed(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        List<Order> orders = new OrderService().list(user.getId());
        int oid = Integer.parseInt(request.getParameter("oid"));
        for(Order order:orders){
            if(order.getId()==oid){
                order.setConfirmDate(new Date());
                order.setStatus(OrderService.OrderType.WAIT_REVIEW);
                new OrderService().update(order);
                request.setAttribute("order",order);
                return "jsp/confirmed.jsp";
            }
        }
        return "@/";
    }
    public String deleteOrder(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        List<Order> orders = new OrderService().list(user.getId());
        int oid = Integer.parseInt(request.getParameter("oid"));
        for(Order order:orders){
            if(order.getId()==oid){
                new OrderService().delete(oid);
                return "%success";
            }
        }
        return "%fail";
    }
    public String comment(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        List<Order> orders = new OrderService().list(user.getId());
        int oiid = Integer.parseInt(request.getParameter("oiid"));
        for(Order order:orders){
            for(OrderItem item : order.getOrderItems()){
                if(oiid == item.getId()){
                    request.setAttribute("orderItem",item);
                    request.setAttribute("order",order);
                    return "jsp/comment.jsp";
                }
            }
        }
        return "@/";
    }
    public String addComment(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        List<Order> orders = new OrderService().list(user.getId());
        int oiid = Integer.parseInt(request.getParameter("oiid"));
        for(Order order:orders){
            for(OrderItem item : order.getOrderItems()){
                if(oiid == item.getId()) {
                    int allCount = 0;
                    int hasComment = 0;
                    for(OrderItem item2:order.getOrderItems()){
                        Comment comment = new CommentService().get(item2.getProduct().getId(), user.getId());
                        allCount++;
                        if(comment != null){
                            hasComment++;
                        }
                    }
                    Comment comment = new Comment();
                    comment.setContent(request.getParameter("content"));
                    comment.setCreateDate(new Date());
                    comment.setProduct(item.getProduct());
                    comment.setUser(user);
                    new CommentService().add(comment);
                    if(allCount-hasComment == 1){
                        order.setStatus(OrderService.OrderType.FINISH);
                        new OrderService().update(order);
                    }
                    return "@myOrder";
                }

            }
        }
        return "@/";
    }
    public String delivery(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        List<Order> orders = new OrderService().list(user.getId());
        int oid = Integer.parseInt(request.getParameter("oid"));
        for(Order order:orders){
            if(order.getId()==oid){
                order.setDeliverDate(new Date());
                order.setStatus(OrderService.OrderType.WAIT_CONFIRM);
                new OrderService().update(order);
                return "@myOrder";
            }
        }
        return "@/";
    }
    public String cartNumber(HttpServletRequest request, HttpServletResponse response) {
        User user =(User) request.getSession().getAttribute("user");
        int number = new CartItemService().getTotal(user.getId());
        return "%"+number;
    }
}