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
<h1> Searched Users</h1>

<c:url var="searchUrl" value="/semenov/main/users/search"/>
<c:url var="mainUrl" value="/semenov/main/users"/>
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

        <tr>
            <td><c:out value="${user.name}"/></td>
            <td><c:out value="${user.age}"/></td>
            <td><c:out value="${user.isAdmin}"/></td>
            <td><c:out value="${user.createdDate}"/></td>

        </tr>
    </c:forEach>
    </tbody>
</table>
<br>

<p><a href="${searchUrl}">Search Again?</a></p>

<p>Return to <a href="${mainUrl}">Main List</a></p>


<c:if test="${empty users}">
    There are currently no users in the Databases.
</c:if>

</body>
</html>