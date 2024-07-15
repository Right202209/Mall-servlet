
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>

<c:set var="title" value="分类管理"/>
<%@include file="common/adminHeader.jsp" %>
<c:set var="light" value="1"/>
<%@include file="common/adminNavigator.jsp" %>

<head>
    <style>
        /* General styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 20px;
        }

        .panel {
            margin-bottom: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .panel-heading {
            background-color: #007bff;
            color: #fff;
            padding: 10px 15px;
            border-radius: 5px 5px 0 0;
        }

        .panel-body {
            padding: 15px;
        }

        .table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 5px;
            overflow: hidden;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .table th,
        .table td {
            padding: 10px;
            text-align: left;
        }

        .table th {
            background-color: #f8f9fa;
            font-weight: bold;
        }

        .table tbody tr:hover {
            background-color: #f1f1f1;
        }

        .btn {
            padding: 8px 20px;
            border-radius: 4px;
            cursor: pointer;
        }

        .btn-success {
            background-color: #28a745;
            color: #fff;
            border: 1px solid #28a745;
        }

        .btn-success:hover {
            background-color: #218838;
            border: 1px solid #218838;
        }

        /* File input styling */
        .file {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            background-color: #fff;
            color: #000;
        }

        /* Breadcrumb styling */
        .breadcrumb {
            background-color: #f8f9fa;
            border-radius: 5px;
            padding: 8px 15px;
        }

        .breadcrumb .active {
            color: #007bff;
        }

        /* Form input styling */
        .form-control {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            background-color: #fff;
            color: #495057;
        }

        /* Responsive styles */
        @media (max-width: 768px) {
            .panel {
                margin-left: 0;
                margin-right: 0;
            }
        }

    </style>
</head>


<div class="container" >
    <ol class="breadcrumb">
        <li class="active">分类管理</li>
    </ol>
    <table class="table table-hover table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">推荐级</th>
            <th scope="col">图片</th>
            <th scope="col">分类名称</th>
            <th scope="col">属性管理</th>
            <th scope="col">产品管理</th>
            <th scope="col">编辑</th>
            <th scope="col">删除</th>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="categories" scope="request" type="java.util.List"/>
        <c:forEach items="${categories}" var="c" varStatus="vs">
            <tr>
                <th scope="row">${c.id}</th>
                <td>${c.recommend}</td>
                <td><img src="http://localhost:8080/S_mall_servlet_master_war_exploded/img/category/${c.id}.jpg" height="40px"></td>
<%--                <td><img src="${pageContext.request.contextPath}/pictures/category/${c.id}.jpg"></td>--%>
                <td>${c.name}</td>
                <td><a href="property_list?cid=${c.id}"><span class="glyphicon glyphicon-list-alt">属性</span></a></td>
                <td><a href="product_list?cid=${c.id}"><span class="glyphicon glyphicon-shopping-cart">产品管理</span></a></td>
                <td><a href="category_edit?cid=${c.id}"><span class="glyphicon glyphicon-edit">编辑</span></a></td>
                <td><a href="category_delete?cid=${c.id}" ><span class="glyphicon glyphicon-trash">删除</span></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    function checkEmpty(id,name){
        var value = $('#'+id).val();
        if(value.length===0){
            alert(name+"不能为空");
            $("#"+id)[0].focus();
            return false;
        }
        return true;
    }
    $(function(){
        $("#add-form").submit(function () {
            if(!checkEmpty("name","分类名称")){
                return false;
            }
        });
        $(".delete-button").click(function () {
            return !!confirm("确实删除？");
        });
    });
</script>



<%@include file="common/adminPage.jsp" %>


<div class="container">
    <div class="row" >
        <div class="panel panel-default" style="width: 600px;margin:auto">
            <div class="panel-heading">新增分类</div>
            <div class="panel-body">
                <form class="form-horizontal" method="post" id="add-form" action="category_addUpdate" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">分类名字</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="请输入分类名字">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="file" class="col-sm-2 control-label">图片文件</label>
                        <div class="col-sm-10">
                            <input id="file" name="file" type="file" class="file">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="recommend" class="col-sm-2 control-label">推荐优先级</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="recommend" name="recommend"
                                   placeholder="默认为0不推荐，首页不显示产品大图。优先级越高排越前" value="0">
                        </div>
                    </div>

                    <div class="form-group">
                        <div style="text-align: center">
                            <button type="submit" class="btn btn-success btn-sm">新建分类</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="common/adminFooter.jsp" %>
