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
        Notice
    </h1>
    <span>${notice.noticeNo}</span>
    <span>${notice.title}</span>
    <span>${notice.content}</span>
    <span>${notice.useYnEnum}</span>
    <span>${notice.regDt}</span>
    <span>${notice.modDt}</span>
</body>
</html>
