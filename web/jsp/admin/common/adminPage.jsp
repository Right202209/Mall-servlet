<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>

<div class="container text-center" >
    <nav aria-label="..." >
        <br>
        <br>
        <ul class="pagination">
            <li class="page-item ${pagination.hasPrevious ? '':'disabled'}">
                <a class="page-link" href="?pageStart=0${pagination.param}" tabindex="-1">«</a>
            </li><li class="page-item ${pagination.hasPrevious ? '':'disabled'}">
            <a class="page-link" href="?pageStart=${pagination.start-pagination.count<0?0:pagination.start-pagination.count}${pagination.param}" tabindex="-1">‹</a>
        </li>

            <c:forEach begin="0" end="${pagination.totalPage}" varStatus="vs">
            <li class="page-item ${vs.index * pagination.count == pagination.start ? 'active':''}">
                <a class="page-link" href="?pageStart=${vs.index*pagination.count}${pagination.param}">
                        ${vs.count}
                </a>
            </li>
            </c:forEach >
            <li class="page-item" ${pagination.hasNext ? '':'disabled'}>
                <a class="page-link" href="?pageStart=${pagination.start+pagination.count}${pagination.param}">›</a>
            </li>
            <li class="page-item" ${pagination.hasNext ? '':'disabled'}>
                <a class="page-link" href="?pageStart=${pagination.lastPage}${pagination.param}">»</a>
            </li>
        </ul>
    </nav>
</div>
