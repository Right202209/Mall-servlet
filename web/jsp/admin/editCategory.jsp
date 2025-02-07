<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>

<c:set var="title" value="编辑分类"/>
<%@include file="common/adminHeader.jsp" %>
<c:set var="light" value="1"/>
<%@include file="common/adminNavigator.jsp" %>
<div class="container" >
    <ol class="breadcrumb">
        <li><a href="category_list">所有分类</a></li>
        <li>${c.name}</li>
    </ol>
</div>
<div class="container">
    <h4 class="page-header">编辑分类</h4>
    <div class="row" >
        <div class="panel panel-default" style="width: 600px;margin:0 auto">
            <div class="panel-heading">编辑分类</div>
            <div class="panel-body">
                <form class="form-horizontal" method="post" id="add-form" action="category_addUpdate" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">分类名字</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="请输入分类名字" value="${c.name}">
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
                                   placeholder="默认为0不推荐，首页不显示产品大图。优先级越高排越前" value="${c.recommend}">
                        </div>
                    </div>
                    <input type="hidden" name="id" value="${c.id}">

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

<%@include file="common/adminFooter.jsp" %>