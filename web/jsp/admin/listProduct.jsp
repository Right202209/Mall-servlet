
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>

<c:set var="title" value="分类管理 - 属性管理"/>
<%@include file="common/adminHeader.jsp" %>
<c:set var="light" value="1"/>
<%@include file="common/adminNavigator.jsp" %>


<div class="container" >
    <ol class="breadcrumb">
        <li><a href="category_list">分类管理</a></li>
        <li>${category.name}</li>
    </ol>
    <table class="table table-hover table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">图片</th>
            <th scope="col">产品名称</th>
            <th scope="col">产品小标题</th>
            <th scope="col">原价格</th>
            <th scope="col">现价格</th>
            <th scope="col">库存</th>
            <th scope="col">图片管理</th>
            <th scope="col">设置属性值</th>
            <th scope="col">编辑</th>
            <th scope="col">删除</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${products}" var="p" varStatus="vs">
            <tr>
                <th scope="row">${p.id}</th>
                <td><img src="http://localhost:8080/S_mall_servlet_master_war_exploded/img/product/${p.firstProductImage.id}.jpg" height="30px"></td>
                <td>${p.name}</td>
                <td>${p.subTitle}</td>
                <td>${p.originalPrice}</td>
                <td>${p.nowPrice}</td>
                <td>${p.stock}</td>
                <td><a href="productImage_list?pid=${p.id}&cid=${category.id}"><span class="glyphicon glyphicon-picture">图片管理</span></a></td>
                <td><a href="product_editPropertyValue?pid=${p.id}&cid=${category.id}"><span class="glyphicon glyphicon-th-list">设置属性值</span></a></td>
                <td><a href="product_edit?pid=${p.id}&cid=${category.id}"><span class="glyphicon glyphicon-edit">编辑</span></a></td>
                <td><a href="product_delete?pid=${p.id}&cid=${category.id}" class="delete-button"><span class="glyphicon glyphicon-trash">删除</span></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="common/adminPage.jsp" %>

<div class="container">
    <div class="row" >
        <div class="panel panel-default" style="width: 600px;margin:auto">
            <div class="panel-heading">新增属性</div>
            <div class="panel-body">
                <form class="form-horizontal" method="get" id="add-form" action="product_addUpdate">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">产品名字</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="请输入属性名字">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="subTitle" class="col-sm-2 control-label">产品小标题</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="subTitle" name="subTitle"
                                   placeholder="请输入产品小标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="originalPrice" class="col-sm-2 control-label">原价格</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="originalPrice" name="originalPrice"
                                   placeholder="请输入原价格">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nowPrice" class="col-sm-2 control-label">现价格</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="nowPrice" name="nowPrice"
                                   placeholder="请输入现价格">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">库存</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="stock" name="stock"
                                   placeholder="请输入库存">
                        </div>
                    </div>

                    <input name="cid" value="${category.id}" type="hidden">
                    <div class="form-group">
                        <div style="text-align: center">
                            <button type="submit" class="btn btn-success btn-sm">新建产品</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

