功能： 

 - [x] 首页、分类页、搜索页、产品页
 - [x] 购物车页面、下单页、支付页及支付成功页
 - [x] 我的订单页、确认收货及成功页、评价页
 - [x] 登陆页、注册页
 - [x] 全部数据库的后台可视化管理
 - [ ] 网站设置

------------------
 安装使用：
 
  1. 若使用IDE打开，需将WEB-INF下的LIB文件夹和Tomcat文件夹下的LIB文件夹到库依赖即可
  2. 若在Tomcat中部署，直接复制文件夹到Tomcat相应位置即可
  3. 导入数据库small.sql，在 src\util\DBUtil.java中配置数据库
  4. 默认后台地址 /admin ，账户密码为 admin 123456 ，新建用户在前台注册，需要后台权限需要在数据库的User表的group_列中将该用户的用户组设置为 superAdmin
  5. JDK >= 1.8、数据库 Mysql


  [1]: https://small.ડ.com
  [2]: https://github.com/xenv/S-mall-ssh
  [3]: https://github.com/xenv/S-mall-ssm
  [4]: https://yuque.com/page/luan.ma/small-start
  [5]: https://yuque.com/page/luan.ma/servlet-filter-mapping
