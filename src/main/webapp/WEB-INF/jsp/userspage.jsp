<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>Users</h1>

<div id="pagination">

    <c:url var="addUrl" value="/semenov/main/users/add"/>
    <c:url var="searchUrl" value="/semenov/main/users/search"/>
    <table style="border: 1px solid; width: 500px; text-align:center">
        <thead style="background:#fcf">
        <tr>
            <th>Name</th>
            <th>Age</th>
            <th>is Admin</th>
            <th>Created Date</th>
            <th colspan="4"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <c:url var="editUrl" value="/semenov/main/users/edit?id=${user.id}"/>
            <c:url var="deleteUrl" value="/semenov/main/users/delete?id=${user.id}"/>

            <tr>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.age}"/></td>
                <td><c:out value="${user.isAdmin}"/></td>
                <td><c:out value="${user.createdDate}"/></td>

                <td><a href="${editUrl}">Edit</a></td>
                <form:form modelAttribute="${user.id}" method="DELETE" action="${deleteUrl}">
                    <td><a href="${deleteUrl}">Delete</a></td>
                </form:form>


            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>

    <p><a href="${addUrl}">Add</a> <br> <a href="${searchUrl}">Search</a></p>

    <c:url value="/semenov/main/users" var="prev">
        <c:param name="page" value="${page-1}"/>
    </c:url>
    <c:if test="${page > 1}">
        <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
    </c:if>

    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <c:choose>
            <c:when test="${page == i.index}">
                <span>${i.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="/semenov/main/users" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:url value="/semenov/main/users" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>
    <c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
</div>

<br>

<c:if test="${empty users}">
    There are currently no users in the list. <a href="${addUrl}">Add</a> a user.
</c:if>

</body>
</html>