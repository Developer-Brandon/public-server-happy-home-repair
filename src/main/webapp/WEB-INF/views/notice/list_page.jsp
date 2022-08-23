<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"
         contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page session="false" import="java.util.*" %>
<html>
<head>
	<title>Notice</title>
</head>
<body>
    <h1>
        NoticeList
    </h1>
    <c:forEach var="item" items="${list}">
        <c:out value="${item.noticeNo}"/>
        <c:out value="${item.title}"/>
        <c:out value="${item.content}"/>
        <c:out value="${item.useYnEnum}"/>
        <c:out value="${item.regDt}"/>
        <c:out value="${item.modDt}"/>
        <br />
    </c:forEach>
</body>
</html>
