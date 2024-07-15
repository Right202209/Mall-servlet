<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<!DOCTYPE html>

<htmL>
<head>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
            request.getServerPort() + path + "/";
%>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>${title}</title>
    <link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>css/style.css">
    <script src="<%=basePath%>js/jquery-3.2.1.min.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>

</head>
<body>