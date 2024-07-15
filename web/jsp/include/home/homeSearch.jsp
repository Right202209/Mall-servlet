<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>


<script src="js/index.js"></script>

<header class="index-top">
    <a href="/S_mall_servlet_master_war_exploded/" class="logo">
        <img src="img/logo.jpg">
    </a>
    <div class="search">
        <form action="search" >
        <input type="text" placeholder="搜索 天猫 商品/品牌/店铺" name="keyword">
        <button class="search-button" type="submit">搜索</button>
        </form>
        <ul class="search-below">
            <c:forEach items="${categories}" var="c" varStatus="vs">
                <c:if test="${vs.count>=1 and vs.count<=8}">
                    <li><a href="category?cid=${c.id}">${c.name}</a></li>
                </c:if>
            </c:forEach>
        </ul>

    </div>

</header>