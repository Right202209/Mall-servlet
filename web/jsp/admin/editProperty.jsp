<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>

<c:set var="title" value="分类管理 - 属性管理 - 修改属性"/>
<%@include file="common/adminHeader.jsp" %>
<c:set var="light" value="1"/>
<%@include file="common/adminNavigator.jsp" %>

<div class="container" >
    <ol class="breadcrumb">
        <li><a href="category_list">分类管理</a></li>
        <li><a href="property_list?cid=${category.id}">${category.name}</a></li>
        <li>${prop.name}</li>
    </ol>
</div>

<div class="container">
    <div class="row" >
        <div class="panel panel-default" style="width: 500px;margin:auto">
            <div class="panel-heading">编辑属性</div>
            <div class="panel-body">
                <form class="form-horizontal" method="get" id="add-form" action="property_addUpdate">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">属性名字</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" name="name" placeholder="请输入属性名字" value="${prop.name}">
                        </div>
                        <input name="cid" value="${category.id}" type="hidden">

                        <input name="ptid" value="${prop.id}" type="hidden">
                    </div>
                    <div class="form-group">
                        <div style="text-align: center">
                            <button type="submit" class="btn btn-success btn-sm">保存</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
