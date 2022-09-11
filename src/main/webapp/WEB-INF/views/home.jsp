<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"
         contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page session="false" import="java.util.*" %>
<!doctype html>
<html lang="en">
<head>
    <!-- required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width
          , user-scalable=no
          , initial-scale=1.0
          , maximum-scale=1.0
          , minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Happy home repair admin home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
          crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/css/reset-font.css">
    <link rel="stylesheet" href="/resources/css/reset-elements.css">
</head>
<body>
    <%-- 해피 홈 리페어 메인 --%>
    <div class="wrap-announce-box">
        <h1 class="title">해피 홈 리페어<br /></h1>
        <p class="explain">어드민페이지에 오신것을 환영합니다</p>
        <p class="build-time text-primary">BuildTime<br />${serverTime}.</p>
        <br />
        <button type="button"
                class="btn btn-primary go-to-admin-button"
                onclick="location.href='main/index.jsp'">Go to Admin page
        </button>
    </div>

    <%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"--%>
    <%--        integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"--%>
    <%--        crossorigin="anonymous"></script>--%>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
            integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
            crossorigin="anonymous"></script>
    <style type="text/css">

        .wrap-announce-box{
            position: absolute;
            top: 50%;
            left: 50%;
            width: 320px;
            height: 240px;
            margin: -140px 0 0 -180px;
        }

        .title {
            text-align: right;
        }

        .explain {
            text-align: right;
        }

        .build-time {
            font-size: 13px;
            text-align: right;
        }

        .go-to-admin-button {
            float: right;
        }
    </style>
</body>
</html>
