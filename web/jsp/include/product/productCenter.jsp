<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="${pageContext.request.contextPath}/js/product.js"></script>

<section class="property">
    <div class="gallery">
        <div class="pic-border">
            <img src="${pageContext.request.contextPath}/img/product/${product.firstProductImage.id}.jpg" id="big-show" alt="${product.name}">
        </div>
        <div class="pic-ls" id="pic-ls">
            <c:forEach items="${topImages}" var="img" varStatus="vs">
                <img src="${pageContext.request.contextPath}/img/product/${img.id}.jpg" big-image="${pageContext.request.contextPath}/img/product/${img.id}.jpg" class="sm-image ${vs.count == 1 ? 'selected' : ''}" alt="${product.name}">
            </c:forEach>
        </div>
        <div id="div4load" style="display:none;"></div>
    </div>
    <div class="detail">
        <div class="product-title">
            ${product.name}
        </div>
        <div class="price">
            <div class="sales">
                <img src="${pageContext.request.contextPath}/img/gouwu.png" alt="Sales">全天猫实物商品通用
            </div>
            <div class="top-line">
                <span class="o-price left">价格</span>
                <span class="o-price-num">￥${product.originalPrice}</span>
            </div>
            <div class="middle-line">
                <span class="s-price left">促销价</span>
                <span class="yen">￥</span><span class="s-price-num">${product.nowPrice}</span>
            </div>
        </div>
        <div class="history">
            <span class="m-sales item">月销量 <em>${product.saleCount}</em></span>
            <span class="c-num item">累计评价 <em>${product.commentCount}</em></span>
            <span class="t-score item">送天猫积分 <em>${product.id * 7}</em></span>
        </div>
        <div class="product-num">
            <span class="buy-num left">数量</span>
            <input type="text" class="input" value="1" id="buy-number">
            <span class="arrow">
                <a class="increase-number" id="increase-number" href="#nowhere">
                    <span class="updown arrow-png">
                       <img src="${pageContext.request.contextPath}/img/increase.png" alt="Increase">
                    </span>
                </a>
                <span class="updown-middle"></span>
                <a class="decrease-number" id="decrease-number" href="#nowhere">
                    <span class="updown arrow-png">
                        <img src="${pageContext.request.contextPath}/img/decrease.png" alt="Decrease">
                    </span>
                </a>
            </span>
            <span class="piece">件</span>
            <span class="inventory" id="inventory" stock="${product.stock}">库存${product.stock}件</span>
        </div>
        <div class="buy">
            <a href="buyOne?pid=${product.id}" id="buy-link" class="display:none;"></a>
            <a href="addCart?pid=${product.id}" id="cart-link" class="display:none;"></a>
            <button class="buy-button" id="buy-button">立即购买</button>
            <button class="car-button" id="cart-button"><span class="glyphicon glyphicon-shopping-cart"></span>加入购物车</button>
        </div>
        <div class="service">
            <span class="left">服务承诺</span>
            <span>正品保证</span>
            <span>极速退款</span>
            <span>七天无理由退换</span>
        </div>
    </div>
</section>
