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

            <td><a href="${addUrl}">Add</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>

<a href="${searchUrl}">Search</a>


<c:if test="${empty users}">
    There are currently no users in the list. <a href="${addUrl}">Add</a> a user.
</c:if>

</body>
</html>