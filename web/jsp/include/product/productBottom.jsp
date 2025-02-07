<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<main class="product-content">
    <div class="product-bar">
        <div class="product-select selected" id="button-1">
            <a >商品详情</a>
        </div>
        <div class="product-select"  id="button-2">
            <a>
                累计评价
                <span class="product-comment-number">${product.commentCount}</span>
            </a>
        </div>
    </div>
    <div class="clear" id="main-1">
        <div class="product-top">
            <div class="product-key-title">产品参数：</div>
            <ul class="product-key">
                <c:forEach items="${pvs}" var="map" varStatus="vs">
                    <li>${map.key.name}: ${map.value.value}</li>
                </c:forEach>
            </ul>
        </div>
        <div class="product-content-part">
            <c:forEach items="${detailImages}" var="img" varStatus="vs">
                <img src="img/product/${img.id}.jpg">
            </c:forEach>
        </div>
    </div>
    <div class="clear" id="main-2" style="display: none;">
        <table class="comment">
            <c:forEach items="${comments}" var="c" varStatus="vs">
            <tr>
                <td class="comment-left">${c.content}
                    <span class="date">${c.createDate}</span></td>
                <td class="comment-right">${c.user.name}<span class="annoy">（匿名）</span></td>
            </tr>
            </c:forEach>
        </table>
    </div>
</main>